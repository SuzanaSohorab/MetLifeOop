package com.example.metlife.Suzana;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class ClaimOfficerDashboard {

    @FXML
    private TableColumn<Claims, Integer> ClaimIdCol;

    @FXML
    private TableColumn<Claims, String> StatusCol;

    @FXML
    private TableColumn<Claims, Integer> PolicyIdCol;

    @FXML
    private TableView<Claims> ClaimTable;

    @FXML
    private TableColumn<Claims, String> ReasonCol;

    @FXML
    private TableColumn<Claims, Float> ClaimAmountCol;

    @FXML
    private MenuBar menu;
    @FXML
    private AnchorPane ClaimDashboardPane;

    @FXML
    public void initialize() {

        ClaimIdCol.setCellValueFactory(new PropertyValueFactory<>("claimID"));
        PolicyIdCol.setCellValueFactory(new PropertyValueFactory<>("policyID"));
        ReasonCol.setCellValueFactory(new PropertyValueFactory<>("reason"));
        StatusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        ClaimAmountCol.setCellValueFactory(new PropertyValueFactory<>("claimAmount"));
    }

    @FXML
    public void allClaimButtonOA(ActionEvent actionEvent) {

        ClaimTable.getItems().clear();

        ClaimTable.getItems().addAll(
                new Claims(2000f, 1, 101, "Accident", "Pending"),
                new Claims(5000f, 2, 102, "Fire", "Approved"),
                new Claims(3000f, 3, 103, "Medical", "Rejected")
        );
    }

    @FXML
    public void pendingClaimButtonOA(ActionEvent actionEvent) {

        ClaimTable.getItems().clear();

        ClaimTable.getItems().addAll(
                new Claims(2000f, 1, 101, "Accident", "Pending"),
                new Claims(1500f, 4, 104, "Theft", "Pending")
        );
    }

    @FXML
    public void requestClaimOA(ActionEvent actionEvent) {
        System.out.println("Request Claim clicked");
    }

    @FXML
    public void passFinanceOA(ActionEvent actionEvent) {

        Claims selected = ClaimTable.getSelectionModel().getSelectedItem();

        if (selected != null) {
            selected.setStatus("Sent to Finance");
            ClaimTable.refresh();
        } else {
            System.out.println("No claim selected!");
        }
    }

    @FXML
    public void handleLogout(ActionEvent actionEvent) {
        System.out.println("Logout clicked");
    }

    @FXML

    public void generateReportOA(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ReportGeneratorForm.fxml"));


            Node node = loader.load();
            ClaimDashboardPane.getChildren().setAll(node);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}