package com.oracle.oci.api.cloudguard.controller;

import java.text.SimpleDateFormat;

import com.oracle.bmc.cloudguard.CloudGuardClient;
import com.oracle.bmc.cloudguard.model.ProblemLifecycleDetail;
import com.oracle.bmc.cloudguard.model.ResponderActivitySummary;
import com.oracle.bmc.cloudguard.model.SortOrders;
import com.oracle.bmc.cloudguard.model.TriggerResponderDetails;
import com.oracle.bmc.cloudguard.model.UpdateProblemStatusDetails;
import com.oracle.bmc.cloudguard.requests.ListResponderActivitiesRequest;
import com.oracle.bmc.cloudguard.requests.TriggerResponderRequest;
import com.oracle.bmc.cloudguard.requests.UpdateProblemStatusRequest;
import com.oracle.bmc.cloudguard.responses.ListResponderActivitiesResponse;
import com.oracle.oci.api.cloudguard.dto.ResponderActivity;
import com.oracle.oci.api.cloudguard.util.AuthentificationProvider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class CloudGuardResponderController {

    @Autowired AuthentificationProvider authentificationProvider;
    
    Logger logger = LoggerFactory.getLogger(CloudGuardResponderController.class);

    @RequestMapping(value = "/responder/activity/{id}", method = RequestMethod.GET)
    public ResponderActivity getResponderActivity(@PathVariable("id") String id, @RequestParam("execution_status") String execution_status, @RequestParam("responder_type") String responder_type) throws Exception {
        CloudGuardClient client = new CloudGuardClient(authentificationProvider.getAuthenticationDetailsProvider());

        ListResponderActivitiesRequest listResponderActivitiesRequest = ListResponderActivitiesRequest.builder()
		.problemId(id)
		.limit(10)
		.sortOrder(SortOrders.Asc)
		.sortBy(ListResponderActivitiesRequest.SortBy.TimeCreated).build();

        /* Send request to the Client */
        ListResponderActivitiesResponse listResponderActivitiesResponse = client.listResponderActivities(listResponderActivitiesRequest);

        ResponderActivity responderActivity = new ResponderActivity();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for (ResponderActivitySummary responderActivitySummary : listResponderActivitiesResponse.getResponderActivityCollection().getItems()) {
            if(responderActivitySummary.getResponderExecutionStatus().getValue().equals(execution_status) && responderActivitySummary.getResponderType().getValue().equals(responder_type)) {
                responderActivity.setId(responderActivitySummary.getId());
                responderActivity.setResponderRuleId(responderActivitySummary.getResponderRuleId());
                responderActivity.setResponderRuleName(responderActivitySummary.getResponderRuleName());
                responderActivity.setMessage(responderActivitySummary.getMessage());
                responderActivity.setResponderActivityType(responderActivitySummary.getResponderActivityType().getValue());
                responderActivity.setResponderExecutionStatus(responderActivitySummary.getResponderExecutionStatus().getValue()); // STARTED, AWAITING_CONFIRMATION, AWAITING_INPUT, SUCCEEDED, FAILED, SKIPPED, ALL
                responderActivity.setResponderType(responderActivitySummary.getResponderType().getValue()); // REMEDIATION, NOTIFICATION
                responderActivity.setTimeCreated(df.format(responderActivitySummary.getTimeCreated()));

                break;
            } else {
                return null;
            }
        }
        
        return responderActivity;
    }

    @RequestMapping(value = "/responder/actions/trigger/{id}", method = RequestMethod.POST)
    public void remediate(@PathVariable("id") String id, @RequestParam("responder_rule_id") String responder_rule_id) throws Exception {
        CloudGuardClient client = new CloudGuardClient(authentificationProvider.getAuthenticationDetailsProvider());

        TriggerResponderDetails triggerResponderDetails = TriggerResponderDetails.builder()
		.responderRuleId(responder_rule_id).build();

        TriggerResponderRequest triggerResponderRequest = TriggerResponderRequest.builder()
		.problemId(id)
		.triggerResponderDetails(triggerResponderDetails).build();

        /* Send request to the Client */
        client.triggerResponder(triggerResponderRequest);
    }

    @RequestMapping(value = "/responder/actions/update/{id}", method = RequestMethod.POST)
    public void update(@PathVariable("id") String id, @RequestParam("status") String status) throws Exception {
        CloudGuardClient client = new CloudGuardClient(authentificationProvider.getAuthenticationDetailsProvider());

        /* Create a request and dependent object(s). */
	    UpdateProblemStatusDetails updateProblemStatusDetails = UpdateProblemStatusDetails.builder()
            .status(status.equals("dismissed") ? ProblemLifecycleDetail.Dismissed : ProblemLifecycleDetail.Resolved).build();

        UpdateProblemStatusRequest updateProblemStatusRequest = UpdateProblemStatusRequest.builder()
            .problemId(id)
            .updateProblemStatusDetails(updateProblemStatusDetails).build();

        client.updateProblemStatus(updateProblemStatusRequest);
    }

}                                                                    