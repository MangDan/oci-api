package com.oracle.oci.api.cloudguard.controller;

import java.util.ArrayList;
import java.util.List;

import com.oracle.bmc.cloudguard.CloudGuardClient;
import com.oracle.bmc.cloudguard.model.TargetSummary;
import com.oracle.bmc.cloudguard.requests.GetTargetRequest;
import com.oracle.bmc.cloudguard.requests.ListTargetsRequest;
import com.oracle.bmc.cloudguard.responses.ListTargetsResponse;
import com.oracle.oci.api.cloudguard.dto.Target;
import com.oracle.oci.api.cloudguard.util.AuthentificationProvider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class CloudGuardController {

    @Autowired AuthentificationProvider authentificationProvider;
    
    Logger logger = LoggerFactory.getLogger(CloudGuardController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String test() throws Exception {

        logger.info(authentificationProvider.getAuthenticationDetailsProvider().getFingerprint());
        return "Hello World";
    }

    @RequestMapping(value = "/target/list", method = RequestMethod.GET)
    public List<Target> listTargets() throws Exception {

        List<Target> targetList = new ArrayList<Target>();

        CloudGuardClient client = new CloudGuardClient(authentificationProvider.getAuthenticationDetailsProvider());
        
        ListTargetsRequest listTargetsRequest = ListTargetsRequest.builder().compartmentId("ocid1.compartment.oc1..aaaaaaaa2ps2cj6joosvscmkvmwtfabassubsiku6qf3yoy3hkchofmzdytq").build();

        // /* Send request to the Client */
        ListTargetsResponse listTargets = client.listTargets(listTargetsRequest);
        
        for (TargetSummary targetSummary : listTargets.getTargetCollection().getItems()) {
            Target target = new Target();

            target.setDisplayName(targetSummary.getDisplayName());

            target.setId(targetSummary.getId());
            target.setDisplayName(targetSummary.getDisplayName());
            target.setCompartmentId(targetSummary.getCompartmentId());
            target.setLifecycleState(targetSummary.getLifecycleState().getValue());
            target.setLifecycleDetail(targetSummary.getLifecyleDetails());

            targetList.add(target);
        }

        return targetList;
    }

    @RequestMapping(value = "/target/get", method = RequestMethod.GET)
    public Target getTarget() throws Exception {

        CloudGuardClient client = new CloudGuardClient(authentificationProvider.getAuthenticationDetailsProvider());
        
        GetTargetRequest getTargetRequest = GetTargetRequest.builder().targetId("ocid1.cloudguardtarget.oc1.iad.amaaaaaavsea7yiaurv7qrkgknntbsa6ecf54em4n3kgj2oyyq2rnyrulijq").build();

        /* Send request to the Client */
        //GetTargetResponse target = client.getTarget(getTargetRequest);

        Target target = new Target();
        target.setId(client.getTarget(getTargetRequest).getTarget().getId());
        target.setDisplayName(client.getTarget(getTargetRequest).getTarget().getDisplayName());
        target.setCompartmentId(client.getTarget(getTargetRequest).getTarget().getCompartmentId());
        target.setLifecycleState(client.getTarget(getTargetRequest).getTarget().getLifecycleState().getValue());
        target.setLifecycleDetail(client.getTarget(getTargetRequest).getTarget().getLifecyleDetails());
        
        return target;
    }
}