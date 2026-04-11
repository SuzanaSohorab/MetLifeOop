package com.example.metlife.nisha;

import java.io.Serializable;

public class Ticket implements Serializable {
    private String ticketId;
    private String customerId;
    private String issueDescription;
    private String priority;

    public Ticket(String ticketId, String customerId, String issueDescription, String priority) {
        this.ticketId = ticketId;
        this.customerId = customerId;
        this.issueDescription = issueDescription;
        this.priority = priority;
    }

    public String getTicketId() { return ticketId; }
    public String getCustomerId() { return customerId; }
    public String getIssueDescription() { return issueDescription; }
    public String getPriority() { return priority; }
}