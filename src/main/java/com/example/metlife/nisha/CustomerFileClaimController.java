package com.example.metlife.nisha;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class CustomerFileClaimController {

    @FXML private TextField policyIdField;
    @FXML private TextArea descriptionArea;
    @FXML private TextField documentPathField;

    @FXML
    public void handleBrowse(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Supporting Document");


        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Document Files", "*.pdf", "*.png", "*.jpg", "*.docx")
        );

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(window);

        if (selectedFile != null) {
            if (documentPathField != null) {
                documentPathField.setText(selectedFile.getName());
            }
        }
    }

    @FXML
    public void handleSubmitClaim(ActionEvent event) {
        String policyId = "";
        String description = "";

        if (policyIdField != null) policyId = policyIdField.getText().trim();
        if (descriptionArea != null) description = descriptionArea.getText().trim();


        if (policyId.isEmpty() || !policyId.startsWith("POL-")) {
            new Alert(Alert.AlertType.ERROR, "Please enter a valid Policy ID (e.g., POL-123)!").showAndWait();
            return;
        }


        if (description.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please describe the incident or reason for your claim!").showAndWait();
            return;
        }


        try {
            String currentUser = UserSession.getCurrentUserId();
            if (currentUser == null) currentUser = "wallahiimfinished123";

            // Use the Claim blueprint we created
            Claim newClaim = new Claim(policyId, currentUser, description, "Submitted");

            File file = new File("Claims.bin");
            ArrayList<Claim> allClaims = new ArrayList<>();


            if (file.exists()) {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                    allClaims = (ArrayList<Claim>) ois.readObject();
                } catch (Exception e) {
                    System.out.println("Creating new claims file.");
                }
            }

            allClaims.add(newClaim);


            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                oos.writeObject(allClaims);


                Alert success = new Alert(Alert.AlertType.INFORMATION);
                success.setTitle("Claim Submitted");
                success.setHeaderText(null);
                success.setContentText("Your claim for " + policyId + " has been successfully submitted and is pending review.");
                success.showAndWait();


                if (policyIdField != null) policyIdField.clear();
                if (descriptionArea != null) descriptionArea.clear();
                if (documentPathField != null) documentPathField.clear();
            }

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "System Error: Could not save claim data.").showAndWait();
        }
    }

    @FXML
    public void goBack(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("CustomerDashboard.fxml"));
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
        window.show();
    }
}