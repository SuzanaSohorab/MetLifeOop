package com.example.metlife.Alfesani;

import java.io.Serializable;
import java.time.LocalDate;

public class Policy_Manager implements Serializable {
    private String policyId, policyName, clientName, agentName, status, description;
    private double premiumAmount, coverageAmount;
    private LocalDate expiryDate;
    private int saleCount = 1;

    public Policy_Manager(String policyId, String policyName, String clientName, String agentName, String status, String description, double premiumAmount, double coverageAmount, LocalDate expiryDate) {
        this.policyId = policyId;
        this.policyName = policyName;
        this.clientName = clientName;
        this.agentName = agentName;
        this.status = status;
        this.description = description;
        this.premiumAmount = premiumAmount;
        this.coverageAmount = coverageAmount;
        this.expiryDate = expiryDate;
    }

    public String getPolicyId() {
        return policyId;
    }

    public void setPolicyId(String policyId) {
        this.policyId = policyId;
    }

    public String getPolicyName() {
        return policyName;
    }

    public void setPolicyName(String policyName) {
        this.policyName = policyName;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPremiumAmount() {
        return premiumAmount;
    }

    public void setPremiumAmount(double premiumAmount) {
        this.premiumAmount = premiumAmount;
    }

    public double getCoverageAmount() {
        return coverageAmount;
    }

    public void setCoverageAmount(double coverageAmount) {
        this.coverageAmount = coverageAmount;
    }

    public int getExpiryDate() {
        return expiryDate.getYear();
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public String toString() {
        return "Policy_Manager{" +
                "policyId='" + policyId + '\'' +
                ", policyName='" + policyName + '\'' +
                ", clientName='" + clientName + '\'' +
                ", agentName='" + agentName + '\'' +
                ", status='" + status + '\'' +
                ", description='" + description + '\'' +
                ", premiumAmount=" + premiumAmount +
                ", coverageAmount=" + coverageAmount +
                ", expiryDate=" + expiryDate +
                '}';
    }
}
