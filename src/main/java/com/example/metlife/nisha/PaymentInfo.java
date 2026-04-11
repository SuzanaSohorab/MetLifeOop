package com.example.metlife.nisha;

import java.io.Serializable;

public class PaymentInfo implements Serializable {
    private String userId;
    private String cardDisplay; // e.g., "Visa ending in 4242"
    private String fullCardNum;
    private String expiry;

    public PaymentInfo(String userId, String cardDisplay, String fullCardNum, String expiry) {
        this.userId = userId;
        this.cardDisplay = cardDisplay;
        this.fullCardNum = fullCardNum;
        this.expiry = expiry;
    }

    public String getUserId() { return userId; }
    public String getCardDisplay() { return cardDisplay; }
}