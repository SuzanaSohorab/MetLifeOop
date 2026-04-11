package com.example.metlife.Suzana;

public class Claims {
    private int ClaimID, PolicyID;
    private String Status, Reason;
    private float ClaimAmount;

    public Claims() {
    }

    public Claims(float claimAmount, int claimID, int policyID, String reason, String status) {
        this.ClaimAmount = claimAmount;
        this.ClaimID = claimID;
        this.PolicyID = policyID;
        this.Reason = reason;
        this.Status = status;
    }

    public float getClaimAmount() {
        return ClaimAmount;
    }

    public void setClaimAmount(float claimAmount) {
        this.ClaimAmount = claimAmount;
    }

    public int getClaimID() {
        return ClaimID;
    }

    public void setClaimID(int claimID) {
        this.ClaimID = claimID;
    }

    public int getPolicyID() {
        return PolicyID;
    }

    public void setPolicyID(int policyID) {
        this.PolicyID = policyID;
    }

    public String getReason() {
        return Reason;
    }

    public void setReason(String reason) {
        this.Reason = reason;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        this.Status = status;
    }

    @Override
    public String toString() {
        return "Claims{" +
                "ClaimAmount=" + ClaimAmount +
                ", ClaimID=" + ClaimID +
                ", PolicyID=" + PolicyID +
                ", Status='" + Status + '\'' +
                ", Reason='" + Reason + '\'' +
                '}';
    }
}