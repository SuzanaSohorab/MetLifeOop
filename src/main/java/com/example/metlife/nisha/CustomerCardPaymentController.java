package com.example.metlife.nisha;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class CustomerCardPaymentController {

    @FXML
    public void payNow(ActionEvent event) {
        Alert success = new Alert(Alert.AlertType.INFORMATION);
        success.setTitle("Payment Successful");
        success.setHeaderText(null);
        success.setContentText("Your premium has been successfully paid via Secure Card Processing!");
        success.showAndWait();
    }

    @FXML
    public void goBack(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("CustomerPayPremium.fxml"));
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
        window.show();
    }
}