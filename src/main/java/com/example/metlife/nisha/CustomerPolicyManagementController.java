package com.example.metlife.nisha;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class CustomerPolicyManagementController {


    @FXML private ComboBox<String> policyTypeComboBox;
    @FXML private VBox healthFieldsBox;
    @FXML private VBox lifeFieldsBox;


    @FXML private TextField healthNameField;
    @FXML private TextField healthPremiumField;
    @FXML private TextField lifeNameField;
    @FXML private TextField lifePremiumField;


    @FXML private TextField dependentNameField;
    @FXML private ComboBox<String> relationshipComboBox;
    @FXML private TextField dependentAgeField;


    @FXML private TextField cancelPolicyIdField;
    @FXML private TextArea cancelReasonArea;



    @FXML private TableView<Policy> policyTable;
    @FXML private TableColumn<Policy, String> colId;
    @FXML private TableColumn<Policy, String> colName;
    @FXML private TableColumn<Policy, Double> colPremium;
    @FXML private TableColumn<Policy, String> colStatus;

    private ObservableList<Policy> policyList;
    private final String BIN_FILE_PATH = "CustomerPolicies.bin";

    @FXML
    public void initialize() {
        // 1. Setup the ComboBoxes
        if (policyTypeComboBox != null) {
            policyTypeComboBox.getItems().addAll("Health", "Life");
        }


        if (relationshipComboBox != null) {
            relationshipComboBox.getItems().addAll("Spouse", "Child", "Parent");
        }


        if (colId != null) {
            colId.setCellValueFactory(new PropertyValueFactory<>("policyId"));
            colName.setCellValueFactory(new PropertyValueFactory<>("policyName"));
            colPremium.setCellValueFactory(new PropertyValueFactory<>("basePremium"));
            colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        }


        policyList = FXCollections.observableArrayList();
        loadPoliciesFromBin();


        if (policyTable != null) {
            policyTable.setItems(policyList);
            policyTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        }
    }

    @FXML
    public void handlePolicyTypeChange(ActionEvent event) {
        if (policyTypeComboBox != null && policyTypeComboBox.getValue() != null) {
            String selectedPolicy = policyTypeComboBox.getValue();
            if (selectedPolicy.equals("Health")) {
                if (healthFieldsBox != null) {
                    healthFieldsBox.setVisible(true);
                    healthFieldsBox.setManaged(true);
                }
                if (lifeFieldsBox != null) {
                    lifeFieldsBox.setVisible(false);
                    lifeFieldsBox.setManaged(false);
                }
            } else if (selectedPolicy.equals("Life")) {
                if (lifeFieldsBox != null) {
                    lifeFieldsBox.setVisible(true);
                    lifeFieldsBox.setManaged(true);
                }
                if (healthFieldsBox != null) {
                    healthFieldsBox.setVisible(false);
                    healthFieldsBox.setManaged(false);
                }
            }
        }
    }

    private void loadPoliciesFromBin() {
        File file = new File(BIN_FILE_PATH);
        String currentUser = UserSession.getCurrentUserId();

        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                ArrayList<Policy> loadedList = (ArrayList<Policy>) ois.readObject();
                for (Policy p : loadedList) {
                    if (p.getUserId() != null && p.getUserId().equals(currentUser)) {
                        policyList.add(p);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public void savePoliciesToBin() {
        File file = new File(BIN_FILE_PATH);
        ArrayList<Policy> masterList = new ArrayList<>();


        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                masterList = (ArrayList<Policy>) ois.readObject();
            } catch (Exception e) {

            }
        }


        String currentUser = UserSession.getCurrentUserId();
        masterList.removeIf(p -> p.getUserId() != null && p.getUserId().equals(currentUser));

        // 3. Add the current updated list back into the master list
        masterList.addAll(new ArrayList<>(policyList));


        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(BIN_FILE_PATH))) {
            oos.writeObject(masterList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void addDependent(ActionEvent event) {
        String name = "";
        String relationship = "";
        String ageText = "";

        if (dependentNameField != null) name = dependentNameField.getText();
        if (relationshipComboBox != null) relationship = relationshipComboBox.getValue();
        if (dependentAgeField != null) ageText = dependentAgeField.getText();

        if (name == null || name.trim().isEmpty() || relationship == null || ageText == null || ageText.trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please fill in all fields!").showAndWait();
            return;
        }

        try {
            int age = Integer.parseInt(ageText);
            double extraPremium = relationship.equals("Spouse") ? 2500.0 : relationship.equals("Child") ? 1000.0 : 3500.0;

            Alert success = new Alert(Alert.AlertType.INFORMATION, "Successfully added " + name + " (" + relationship + "). Monthly premium increases by " + extraPremium + " Taka.");
            success.showAndWait();

            if (dependentNameField != null) dependentNameField.clear();
            if (dependentAgeField != null) dependentAgeField.clear();
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Invalid Age!").showAndWait();
        }
    }

    @FXML
    public void uploadMedicalDocs(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(window);
        if (selectedFile != null) {
            new Alert(Alert.AlertType.INFORMATION, "File attached: " + selectedFile.getName()).showAndWait();
        }
    }

    @FXML
    public void submitApplication(ActionEvent event) {
        String selectedType = policyTypeComboBox.getValue();
        if (selectedType == null) {
            new Alert(Alert.AlertType.WARNING, "Select a Policy Type!").showAndWait();
            return;
        }

        String newId = "POL-" + (int)(Math.random() * 1000 + 100);
        String policyName = "";
        double premium = 0.0;

        try {
            if (selectedType.equals("Health")) {
                policyName = healthNameField.getText();
                premium = Double.parseDouble(healthPremiumField.getText());
            } else {
                policyName = lifeNameField.getText();
                premium = Double.parseDouble(lifePremiumField.getText());
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Invalid Premium amount!").showAndWait();
            return;
        }

        String currentUser = UserSession.getCurrentUserId();
        String todaysDate = java.time.LocalDate.now().toString(); // Added the date grabber


        Policy newPolicy = new Policy(newId, currentUser, policyName, String.valueOf(premium), "Pending Approval", todaysDate);

        policyList.add(newPolicy);
        savePoliciesToBin();

        new Alert(Alert.AlertType.INFORMATION, "Submitted Successfully!").showAndWait();
        if (healthNameField != null) healthNameField.clear();
        if (healthPremiumField != null) healthPremiumField.clear();
        if (lifeNameField != null) lifeNameField.clear();
        if (lifePremiumField != null) lifePremiumField.clear();
    }

    @FXML
    public void submitCancellation(javafx.event.ActionEvent event) {
        String policyId = cancelPolicyIdField.getText();
        String reason = cancelReasonArea.getText();

        if (policyId.isEmpty()) {
            new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.WARNING, "Please enter the Policy ID you want to cancel!").showAndWait();
            return;
        }


        String randomTicketId = "TKT-" + (int)(Math.random() * 9000 + 1000);
        String customerId = UserSession.getCurrentUserId();
        String fullIssue = "Cancellation Request for " + policyId + ". Reason: " + reason;

        Ticket newTicket = new Ticket(randomTicketId, customerId, fullIssue, "High");


        java.io.File file = new java.io.File("ServiceTickets.bin");
        java.util.ArrayList<Ticket> allTickets = new java.util.ArrayList<>();

        if (file.exists()) {
            try (java.io.ObjectInputStream ois = new java.io.ObjectInputStream(new java.io.FileInputStream(file))) {
                allTickets = (java.util.ArrayList<Ticket>) ois.readObject();
            } catch (Exception e) {
                System.out.println("Error reading tickets: " + e.getMessage());
            }
        }

        allTickets.add(newTicket);

        try (java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(new java.io.FileOutputStream(file))) {
            oos.writeObject(allTickets);
            new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION, "Cancellation request submitted! An agent will review your ticket.").showAndWait();

            // Clear the boxes
            cancelPolicyIdField.clear();
            cancelReasonArea.clear();
        } catch (Exception e) {
            System.out.println("Error saving ticket: " + e.getMessage());
        }
    }

    @FXML
    public void downloadPDF(ActionEvent event) {
        new Alert(Alert.AlertType.INFORMATION, "Your Policy PDF has been generated!").showAndWait();
    }

    @FXML
    public void goBack(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("CustomerDashboard.fxml"));
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
        window.show();
    }
}