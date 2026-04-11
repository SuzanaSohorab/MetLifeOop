package com.example.metlife.Alfesani;

import java.time.LocalDate;

public class FinanceOfficer {
    private String id;
    private String name;
    private String status;
    private double amount;
    private String transactionType;
    private LocalDate date;

    public FinanceOfficer(String id, String name, String status, double amount, String transactionType, LocalDate date) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.amount = amount;
        this.transactionType = transactionType;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public int getDate() {
        return date.getYear();
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "FinanceOfficer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", amount=" + amount +
                ", transactionType='" + transactionType + '\'' +
                ", date=" + date +
                '}';
    }
}
