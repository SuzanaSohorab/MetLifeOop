package com.example.metlife.nisha;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class SignupController {

    @FXML private TextField signupIdField;
    @FXML private PasswordField signupPasswordField;

    private final String USERS_FILE = "Users.bin";

    @FXML
    public void createAccount(ActionEvent event) {
        String role = "Customer";
        String id = signupIdField.getText();
        String pass = signupPasswordField.getText();

        if (id.isEmpty() || pass.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please fill in all fields!").showAndWait();
            return;
        }

        ArrayList<User> userList = new ArrayList<>();
        File file = new File(USERS_FILE);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                userList = (ArrayList<User>) ois.readObject();
            } catch (Exception e) { e.printStackTrace(); }
        }

        userList.add(new User(role, id, pass));

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USERS_FILE))) {
            oos.writeObject(userList);
            new Alert(Alert.AlertType.INFORMATION, "Account created successfully! You can now log in.").showAndWait();
            goBackToLogin(event);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void goBackToLogin(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
        window.show();
    }
}