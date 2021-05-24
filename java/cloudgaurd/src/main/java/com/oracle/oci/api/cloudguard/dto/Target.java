package com.oracle.oci.api.cloudguard.dto;

public class Target {
    private String id;
    private String displayName;
    private String description;
    private String compartmentId;
    private String lifecycleState;
    private String lifecycleDetail;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getDisplayName() {
        return displayName;
    }
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getCompartmentId() {
        return compartmentId;
    }
    public void setCompartmentId(String compartmentId) {
        this.compartmentId = compartmentId;
    }
    public String getLifecycleState() {
        return lifecycleState;
    }
    public void setLifecycleState(String lifecycleState) {
        this.lifecycleState = lifecycleState;
    }
    public String getLifecycleDetail() {
        return lifecycleDetail;
    }
    public void setLifecycleDetail(String lifecycleDetail) {
        this.lifecycleDetail = lifecycleDetail;
    }

    
}
