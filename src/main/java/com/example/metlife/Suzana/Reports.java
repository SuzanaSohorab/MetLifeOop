package com.example.metlife.Suzana;

import java.io.Serializable;
import java.time.LocalDate;

public class Reports implements Serializable {
    private int ReportID;
    private String ReportType,ReportSummary;
    private LocalDate ReportDate;

    public Reports() {
    }

    public Reports(LocalDate reportDate, int reportID, String reportSummary, String reportType) {
        ReportDate = reportDate;
        ReportID = reportID;
        ReportSummary = reportSummary;
        ReportType = reportType;
    }

    public LocalDate getReportDate() {
        return ReportDate;
    }

    public void setReportDate(LocalDate reportDate) {
        ReportDate = reportDate;
    }

    public int getReportID() {
        return ReportID;
    }

    public void setReportID(int reportID) {
        ReportID = reportID;
    }

    public String getReportSummary() {
        return ReportSummary;
    }

    public void setReportSummary(String reportSummary) {
        ReportSummary = reportSummary;
    }

    public String getReportType() {
        return ReportType;
    }

    public void setReportType(String reportType) {
        ReportType = reportType;
    }

    @Override
    public String toString() {
        return "Reports{" +
                "ReportDate=" + ReportDate +
                ", ReportID=" + ReportID +
                ", ReportType='" + ReportType + '\'' +
                ", ReportSummary='" + ReportSummary + '\'' +
                '}';
    }
}
