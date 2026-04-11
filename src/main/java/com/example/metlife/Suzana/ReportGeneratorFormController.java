package com.example.metlife.Suzana;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.time.LocalDate;

public class ReportGeneratorFormController {

    @FXML
    private AnchorPane ReportPane;

    @FXML
    private TableView<Reports> reportTable;

    @FXML
    private TableColumn<Reports, Integer> reportIdCol;

    @FXML
    private TableColumn<Reports, String> reportTypeCol;

    @FXML
    private TableColumn<Reports, LocalDate> reportDateCol;

    @FXML
    private TableColumn<Reports, String> reportSummaryCol;

    @FXML
    private TextField reportIdField;

    @FXML
    private ComboBox<String> reportTypeCombo;

    @FXML
    private DatePicker reportDatePicker;

    @FXML
    private TextArea reportSummaryArea;

    private final ObservableList<Reports> reportList = FXCollections.observableArrayList();
    @FXML
    private Button reportToDashboardOA;

    @FXML
    public void initialize() {

        reportIdCol.setCellValueFactory(new PropertyValueFactory<>("reportID"));
        reportTypeCol.setCellValueFactory(new PropertyValueFactory<>("reportType"));
        reportDateCol.setCellValueFactory(new PropertyValueFactory<>("reportDate"));
        reportSummaryCol.setCellValueFactory(new PropertyValueFactory<>("reportSummary"));

        reportTypeCombo.setItems(FXCollections.observableArrayList(
                "Claim Report",
                "Risk Report",
                "Policy Report"
        ));

        reportTable.setItems(reportList);

        reportList.addAll(ReportFileHandler.readReports());
    }

    @FXML
    public void showReportInTable(ActionEvent actionEvent) {

        int id = Integer.parseInt(reportIdField.getText());
        String type = reportTypeCombo.getValue();
        LocalDate date = reportDatePicker.getValue();
        String summary = reportSummaryArea.getText();

        Reports report = new Reports(date, id, summary, type);

        ReportFileHandler.appendReport(report);

        reportList.add(report);

        clearFields();
    }

    private void clearFields() {
        reportIdField.clear();
        reportSummaryArea.clear();
        reportTypeCombo.getSelectionModel().clearSelection();
        reportDatePicker.setValue(null);
    }
}