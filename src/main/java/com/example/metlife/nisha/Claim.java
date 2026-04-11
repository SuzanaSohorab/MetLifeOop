package com.example.metlife.nisha;

import java.io.Serializable;

public class Claim implements Serializable {
    private String policyId;
    private String customerId;
    private String incidentDetails;
    private String status; // "Submitted", "Under Review", "Paid"

    public Claim(String policyId, String customerId, String incidentDetails, String status) {
        this.policyId = policyId;
        this.customerId = customerId;
        this.incidentDetails = incidentDetails;
        this.status = status;
    }


    public String getPolicyId() { return policyId; }
    public String getCustomerId() { return customerId; }
    public String getIncidentDetails() { return incidentDetails; }
    public String getStatus() { return status; }
}
