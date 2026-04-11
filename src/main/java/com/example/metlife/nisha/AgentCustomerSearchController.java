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
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class AgentCustomerSearchController {


    @FXML private TableView<Policy> upgradeTable;
    @FXML private TableColumn<Policy, String> colUpdCustomer;
    @FXML private TableColumn<Policy, String> colUpdCurrent;
    @FXML private TableColumn<Policy, String> colUpdRequested;

    @FXML private TextField newCoverageField;
    @FXML private TextField newPremiumField;


    @FXML private TextField claimPolicySearchField;
    @FXML private TextArea incidentDetailsArea;
    @FXML private TextField claimAmountField;


    @FXML private TextField billingPolicySearchField;
    @FXML private Label currentMethodLabel;
    @FXML private TextField cardAccountField;
    @FXML private TextField expiryField;
    @FXML private TextField cvvField;

    @FXML
    public void initialize() {

        if (colUpdCustomer != null) colUpdCustomer.setCellValueFactory(new PropertyValueFactory<>("userId"));
        if (colUpdCurrent != null) colUpdCurrent.setCellValueFactory(new PropertyValueFactory<>("policyName"));
        if (colUpdRequested != null) colUpdRequested.setCellValueFactory(new PropertyValueFactory<>("basePremium"));


        loadUpgrades();
    }

    private void loadUpgrades() {
        if (upgradeTable == null) return;

        ObservableList<Policy> upgradeList = FXCollections.observableArrayList();
        File file = new File("CustomerPolicies.bin");

        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                ArrayList<Policy> allPolicies = (ArrayList<Policy>) ois.readObject();
                for (Policy p : allPolicies) {
                    if ("Active".equals(p.getStatus())) {
                        upgradeList.add(p);
                    }
                }
            } catch (Exception e) {
                System.out.println("Error reading policies for upgrades: " + e.getMessage());
            }
        }
        upgradeTable.setItems(upgradeList);
    }

    @FXML
    public void sendUpgradeProposal(ActionEvent event) {
        Policy selectedPolicy = upgradeTable.getSelectionModel().getSelectedItem();

        if (selectedPolicy == null) {
            new Alert(Alert.AlertType.WARNING, "Please select a customer from the table first!").showAndWait();
            return;
        }

        String newCoverage = newCoverageField.getText();
        String newPremium = newPremiumField.getText();

        if (newCoverage.isEmpty() || newPremium.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please enter the calculated coverage and premium amounts!").showAndWait();
            return;
        }

        new Alert(Alert.AlertType.INFORMATION, "Upgrade Proposal Sent!\n\nCustomer: " + selectedPolicy.getUserId() + "\nNew Coverage: " + newCoverage + "\nNew Premium: " + newPremium + " Taka").showAndWait();

        newCoverageField.clear();
        newPremiumField.clear();
    }

    @FXML
    public void fetchPolicyForClaim(ActionEvent event) {
        String searchId = claimPolicySearchField.getText();
        File file = new File("Claims.bin");

        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                ArrayList<Claim> allClaims = (ArrayList<Claim>) ois.readObject();
                for (Claim c : allClaims) {
                    if (c.getPolicyId().equals(searchId)) {
                        incidentDetailsArea.setText(c.getIncidentDetails());
                        new Alert(Alert.AlertType.INFORMATION, "Claim details retrieved for " + searchId).showAndWait();
                        return;
                    }
                }
                new Alert(Alert.AlertType.ERROR, "No claim found for this Policy ID!").showAndWait();
            } catch (Exception e) { e.printStackTrace(); }
        }
    }

    @FXML
    public void submitClaimToAdjuster(ActionEvent event) {
        new Alert(Alert.AlertType.INFORMATION, "Claim successfully submitted to the Adjuster queue!").showAndWait();
    }

    // --- UPDATED BILLING METHODS (Goal 7) ---

    @FXML
    public void fetchBillingDetails(ActionEvent event) {
        String pId = billingPolicySearchField.getText();
        if (pId.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please enter a Policy ID to fetch billing info!").showAndWait();
        } else {
            // Simulation for the report: Show the current saved method
            if (currentMethodLabel != null) {
                currentMethodLabel.setText("Current Method: Visa ending in 4242");
            }
        }
    }

    @FXML
    public void updatePaymentMethod(ActionEvent event) {
        String pId = billingPolicySearchField.getText();
        String cardNum = (cardAccountField != null) ? cardAccountField.getText() : "";
        String expiry = (expiryField != null) ? expiryField.getText() : "";

        if (pId.isEmpty() || cardNum.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please enter Policy ID and new card details!").showAndWait();
            return;
        }


        String lastFour = cardNum.length() > 4 ? cardNum.substring(cardNum.length() - 4) : cardNum;
        String displayString = "Visa ending in " + lastFour;


        PaymentInfo newInfo = new PaymentInfo("wallahiimfinished123", displayString, cardNum, expiry);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("PaymentInfo.bin"))) {
            oos.writeObject(newInfo);
            new Alert(Alert.AlertType.INFORMATION, "Payment Information Updated! Customer dashboard will be updated.").showAndWait();


            if(cardAccountField != null) cardAccountField.clear();
            if(expiryField != null) expiryField.clear();
            if(cvvField != null) cvvField.clear();

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "System Error: Could not update billing record.").showAndWait();
        }
    }

    @FXML
    public void goBack(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AgentDashboard.fxml"));
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
        window.show();
    }
}