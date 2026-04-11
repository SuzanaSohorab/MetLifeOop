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

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class CustomerPayPremiumController {


    @FXML private TableView<Policy> invoiceTable;
    @FXML private RadioButton cardRadio;
    @FXML private RadioButton bkashRadio;
    @FXML private RadioButton bankRadio;

    private ToggleGroup paymentGroup;

    @FXML
    public void initialize() {

        paymentGroup = new ToggleGroup();
        if (cardRadio != null) cardRadio.setToggleGroup(paymentGroup);
        if (bkashRadio != null) bkashRadio.setToggleGroup(paymentGroup);
        if (bankRadio != null) bankRadio.setToggleGroup(paymentGroup);


        if (invoiceTable != null) {
            TableColumn<Policy, String> colId = new TableColumn<>("Policy ID");
            colId.setCellValueFactory(new PropertyValueFactory<>("policyId"));
            colId.setPrefWidth(150);

            TableColumn<Policy, String> colName = new TableColumn<>("Policy Name");
            colName.setCellValueFactory(new PropertyValueFactory<>("policyName"));
            colName.setPrefWidth(250);


            TableColumn<Policy, String> colPremium = new TableColumn<>("Premium Amount");
            colPremium.setCellValueFactory(new PropertyValueFactory<>("basePremium"));
            colPremium.setPrefWidth(150);


            invoiceTable.getColumns().clear();
            invoiceTable.getColumns().addAll(colId, colName, colPremium);


            ObservableList<Policy> policyList = FXCollections.observableArrayList();
            File file = new File("CustomerPolicies.bin");


            String currentUser = UserSession.getCurrentUserId();
            if (currentUser == null || currentUser.isEmpty()) currentUser = "wallahiimfinished123";

            if (file.exists()) {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                    ArrayList<Policy> loadedList = (ArrayList<Policy>) ois.readObject();


                    for (Policy p : loadedList) {
                        if (p.getUserId() != null && p.getUserId().equals(currentUser)) {
                            // FIX: Only show approved/active policies!
                            if ("Active".equals(p.getStatus())) {
                                policyList.add(p);
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            invoiceTable.setItems(policyList);
        }
    }

    @FXML
    public void proceedToPayment(ActionEvent event) throws Exception {
        if (invoiceTable != null && invoiceTable.getSelectionModel().getSelectedItem() == null) {
            new Alert(Alert.AlertType.WARNING, "Please select an invoice from the table first!").showAndWait();
            return;
        }


        if (cardRadio != null && cardRadio.isSelected()) {
            // IF CARD IS SELECTED -> OPEN YOUR CUSTOM CARD SCREEN
            Parent root = FXMLLoader.load(getClass().getResource("CustomerCardPayment.fxml"));
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(new Scene(root));
            window.show();
        } else if (bkashRadio != null && bkashRadio.isSelected()) {
            new Alert(Alert.AlertType.INFORMATION, "bKash API not connected yet.").showAndWait();
        } else if (bankRadio != null && bankRadio.isSelected()) {
            new Alert(Alert.AlertType.INFORMATION, "Bank Transfer API not connected yet.").showAndWait();
        } else {
            new Alert(Alert.AlertType.WARNING, "Please select a Payment Method!").showAndWait();
        }
    }

    @FXML
    public void goBack(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("CustomerDashboard.fxml"));
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
        window.show();
    }
}