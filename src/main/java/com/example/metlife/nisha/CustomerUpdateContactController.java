package com.example.metlife.nisha;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class CustomerUpdateContactController {

    @FXML private TextField phoneField;
    @FXML private TextField emailField;
    @FXML private TextField addressField;

    @FXML
    public void saveChanges(ActionEvent event) {

        String phone = "";
        String email = "";
        String address = "";

        if (phoneField != null) phone = phoneField.getText().trim();
        if (emailField != null) email = emailField.getText().trim();
        if (addressField != null) address = addressField.getText().trim();


        if (phone.isEmpty() || email.isEmpty() || address.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please fill in all contact fields!").showAndWait();
            return;
        }


        Alert success = new Alert(Alert.AlertType.INFORMATION);
        success.setTitle("Update Successful");
        success.setHeaderText(null);
        success.setContentText("Your contact information has been updated successfully.");
        success.showAndWait();


        if (phoneField != null) phoneField.clear();
        if (emailField != null) emailField.clear();
        if (addressField != null) addressField.clear();
    }

    @FXML
    public void goBack(ActionEvent event) throws IOException {
        // Load the Dashboard and switch the screen
        Parent root = FXMLLoader.load(getClass().getResource("CustomerDashboard.fxml"));
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
        window.show();
    }
}