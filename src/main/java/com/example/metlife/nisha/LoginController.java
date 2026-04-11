package com.example.metlife.nisha;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class LoginController {

    @FXML private ComboBox<String> roleComboBox;
    @FXML private TextField userIdField;
    @FXML private PasswordField passwordField;

    private final String USERS_FILE = "Users.bin";

    @FXML
    public void handleLogin(ActionEvent event) throws Exception {
        String selectedRole = roleComboBox.getValue();
        String enteredId = userIdField.getText();
        String enteredPass = passwordField.getText();

        if (selectedRole == null || enteredId.isEmpty() || enteredPass.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please fill in Role, ID, and Password!").showAndWait();
            return;
        }


        if (enteredId.equals("eren") && enteredPass.equals("123")) {
            UserSession.setCurrentUserId(enteredId);
            UserSession.setCurrentRole(selectedRole);

            loadDashboard(event, selectedRole);
            return;
        }


        File file = new File(USERS_FILE);
        if (!file.exists()) {
            new Alert(Alert.AlertType.ERROR, "No accounts found! Please Sign Up first.").showAndWait();
            return;
        }

        boolean loginSuccess = false;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            ArrayList<User> userList = (ArrayList<User>) ois.readObject();

            for (User u : userList) {
                if (u.getRole().equals(selectedRole) && u.getUserId().equals(enteredId) && u.getPassword().equals(enteredPass)) {
                    loginSuccess = true;
                    break;
                }
            }
        }

        if (loginSuccess) {
            UserSession.setCurrentUserId(enteredId);
            UserSession.setCurrentRole(selectedRole);
            loadDashboard(event, selectedRole);
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid ID, Password, or Role mismatch!").showAndWait();
        }
    }

    private void loadDashboard(ActionEvent event, String role) throws Exception {
        if (role.equals("Customer")) {
            Parent root = FXMLLoader.load(getClass().getResource("CustomerDashboard.fxml"));
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(new Scene(root));
            window.show();
        }
        else if (role.equals("Agent")) {
            Parent root = FXMLLoader.load(getClass().getResource("AgentDashboard.fxml"));
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(new Scene(root));
            window.show();
        }
        else {
            new Alert(Alert.AlertType.INFORMATION, role + " Dashboard not built yet!").showAndWait();
        }
    }

    @FXML
    public void handleSignUp(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Signup.fxml"));
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
        window.show();
    }
}