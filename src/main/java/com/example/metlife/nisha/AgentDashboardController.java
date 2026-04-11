package com.example.metlife.nisha;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class AgentDashboardController {

    @FXML
    public void goToPendingTasks(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AgentPendingTasks.fxml"));
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
        window.show();
    }

    @FXML
    public void goToCustomerSearch(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AgentCustomerSearch.fxml"));
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
        window.show();
    }

    @FXML
    public void goToAgentTools(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AgentTools.fxml"));
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
        window.show();
    }

    @FXML
    public void logout(ActionEvent event) throws java.io.IOException {
        // 1. Wipe the Agent's identity from memory so no one else can access it!
        UserSession.clearSession();


        javafx.scene.Parent root = javafx.fxml.FXMLLoader.load(getClass().getResource("Login.fxml"));
        javafx.stage.Stage window = (javafx.stage.Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        window.setScene(new javafx.scene.Scene(root));
        window.show();
    }
}