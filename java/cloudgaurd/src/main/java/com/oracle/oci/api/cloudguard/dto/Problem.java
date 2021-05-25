package com.oracle.oci.api.cloudguard.dto;

public class Problem {
    private String id;
    private String description;
    private String recommendation;
    private String lifecycleDetail;
    private String lifecycleState;
    private String resourceName;
    private String resourceType;
    private String riskLevel;
    private String timeFirstDetected;
    private String timeLastDetected;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getRecommendation() {
        return recommendation;
    }
    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }
    public String getLifecycleDetail() {
        return lifecycleDetail;
    }
    public void setLifecycleDetail(String lifecycleDetail) {
        this.lifecycleDetail = lifecycleDetail;
    }
    public String getLifecycleState() {
        return lifecycleState;
    }
    public void setLifecycleState(String lifecycleState) {
        this.lifecycleState = lifecycleState;
    }
    public String getResourceName() {
        return resourceName;
    }
    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }
    public String getResourceType() {
        return resourceType;
    }
    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }
    public String getRiskLevel() {
        return riskLevel;
    }
    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }
    public String getTimeFirstDetected() {
        return timeFirstDetected;
    }
    public void setTimeFirstDetected(String timeFirstDetected) {
        this.timeFirstDetected = timeFirstDetected;
    }
    public String getTimeLastDetected() {
        return timeLastDetected;
    }
    public void setTimeLastDetected(String timeLastDetected) {
        this.timeLastDetected = timeLastDetected;
    }

    
}
