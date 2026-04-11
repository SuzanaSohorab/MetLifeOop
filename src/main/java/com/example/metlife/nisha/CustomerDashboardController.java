package com.example.metlife.nisha;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CustomerDashboardController {

    @FXML
    public void openPolicyManagement(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("CustomerPolicyManagement.fxml"));
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
        window.show();
    }

    @FXML
    public void openPayPremium(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("CustomerPayPremium.fxml"));
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
        window.show();
    }

    @FXML
    public void openUpdateContact(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("CustomerUpdateContact.fxml"));
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
        window.show();
    }

    @FXML
    public void openFileClaim(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("CustomerFileClaim.fxml"));
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
        window.show();
    }

    @FXML
    public void openDownloadDocuments(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("CustomerDownloadDocuments.fxml"));
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
        window.show();
    }

    // --- NEW LOGOUT METHOD ---
    @FXML
    public void handleLogout(ActionEvent event) {
        UserSession.cleanSession();
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.close();
    }
}