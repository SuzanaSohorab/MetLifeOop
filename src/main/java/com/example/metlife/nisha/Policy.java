package com.example.metlife.nisha;

import java.io.Serializable;

public class Policy implements Serializable {


    private String policyId;
    private String userId;
    private String policyName;
    private String basePremium;
    private String status;
    private String dateSubmitted;

    public Policy(String policyId, String userId, String policyName, String basePremium, String status, String dateSubmitted) {
        this.policyId = policyId;
        this.userId = userId;
        this.policyName = policyName;
        this.basePremium = basePremium;
        this.status = status;
        this.dateSubmitted = dateSubmitted;
    }

    public String getPolicyId() {
        return policyId;
    }
    public String getUserId() {
        return userId;
    }
    public String getPolicyName() {
        return policyName;
    }
    public String getBasePremium() {
        return basePremium;
    }
    public String getStatus() {
        return status;
    }
    public String getDateSubmitted() {
        return dateSubmitted;
    }

    public void setPolicyId(String policyId) {
        this.policyId = policyId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public void setPolicyName(String policyName) {
        this.policyName = policyName;
    }
    public void setBasePremium(String basePremium) {
        this.basePremium = basePremium;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public void setDateSubmitted(String dateSubmitted) {
        this.dateSubmitted = dateSubmitted;
    }
}