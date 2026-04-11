package com.example.metlife.nisha;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AgentToolsController {


    @FXML private TextField reportMonthField, reportYearField;
    @FXML private TableView<?> commissionTable;
    @FXML private TextField followupSearchField, aptDateField, aptTimeField, aptNotesField;

    @FXML
    public void generateReport(ActionEvent event) {

        String month = (reportMonthField != null) ? reportMonthField.getText() : "N/A";
        String year = (reportYearField != null) ? reportYearField.getText() : "N/A";
        new Alert(Alert.AlertType.INFORMATION, "Fetching policies and calculating commissions for " + month + "/" + year).showAndWait();
    }

    @FXML
    public void downloadCommissionPDF(ActionEvent event) {

        new Alert(Alert.AlertType.INFORMATION, "Generating PDF Report...").showAndWait();
    }

    /**
     * FIXED: Renamed back to handleConfirmAppointment to match your FXML!
     */
    @FXML
    public void handleConfirmAppointment(ActionEvent event) {

        String customerId = (followupSearchField != null) ? followupSearchField.getText().trim() : "";
        String date = (aptDateField != null) ? aptDateField.getText().trim() : "";
        String time = (aptTimeField != null) ? aptTimeField.getText().trim() : "";
        String notes = (aptNotesField != null) ? aptNotesField.getText().trim() : "";


        if (customerId.isEmpty() || date.isEmpty() || time.isEmpty() || notes.isEmpty()) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Validation Error");
            errorAlert.setHeaderText("Missing Fields");
            errorAlert.setContentText("Please fill in all appointment details before confirming.");
            errorAlert.showAndWait();
            return;
        }


        Alert success = new Alert(Alert.AlertType.INFORMATION);
        success.setTitle("Success");
        success.setHeaderText(null);
        success.setContentText("Appointment scheduled! Invitation sent to Customer: " + customerId);
        success.showAndWait();


        if (followupSearchField != null) followupSearchField.clear();
        if (aptDateField != null) aptDateField.clear();
        if (aptTimeField != null) aptTimeField.clear();
        if (aptNotesField != null) aptNotesField.clear();
    }

    @FXML
    public void goBack(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AgentDashboard.fxml"));
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
        window.show();
    }
}