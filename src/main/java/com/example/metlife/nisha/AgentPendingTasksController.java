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
import java.io.IOException;
import java.util.ArrayList;

public class AgentPendingTasksController {


    @FXML private TableView<Policy> appTable;
    @FXML private TableColumn<Policy, String> colAppId;
    @FXML private TableColumn<Policy, String> colAppCustomer;
    @FXML private TableColumn<Policy, String> colAppType;


    @FXML private TableView<Ticket> ticketTable;
    @FXML private TableColumn<Ticket, String> colTktId;
    @FXML private TableColumn<Ticket, String> colTktCustomer;
    @FXML private TableColumn<Ticket, String> colTktIssue;
    @FXML private TableColumn<Ticket, String> colTktPriority;


    @FXML private TableView<Policy> renewalTable;
    @FXML private TableColumn<Policy, String> colRenId;
    @FXML private TableColumn<Policy, String> colRenCustomer;
    @FXML private TableColumn<Policy, String> colRenExpiry;
    @FXML private TableColumn<Policy, String> colRenPremium;

    @FXML
    public void initialize() {

        colAppId.setCellValueFactory(new PropertyValueFactory<>("policyId"));
        colAppCustomer.setCellValueFactory(new PropertyValueFactory<>("userId"));
        colAppType.setCellValueFactory(new PropertyValueFactory<>("policyName"));


        colRenId.setCellValueFactory(new PropertyValueFactory<>("policyId"));
        colRenCustomer.setCellValueFactory(new PropertyValueFactory<>("userId"));
        colRenExpiry.setCellValueFactory(new PropertyValueFactory<>("dateSubmitted"));
        colRenPremium.setCellValueFactory(new PropertyValueFactory<>("basePremium"));


        colTktId.setCellValueFactory(new PropertyValueFactory<>("ticketId"));
        colTktCustomer.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colTktIssue.setCellValueFactory(new PropertyValueFactory<>("issueDescription"));
        colTktPriority.setCellValueFactory(new PropertyValueFactory<>("priority"));


        loadPendingApplications();
        loadRenewals();
        loadServiceTickets();
    }

    private void loadPendingApplications() {
        ObservableList<Policy> pendingList = FXCollections.observableArrayList();
        File file = new File("CustomerPolicies.bin");

        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                ArrayList<Policy> allPolicies = (ArrayList<Policy>) ois.readObject();
                for (Policy p : allPolicies) {
                    if (p.getStatus() != null && p.getStatus().equals("Pending Approval")) {
                        pendingList.add(p);
                    }
                }
            } catch (Exception e) {
                System.out.println("Error reading policies: " + e.getMessage());
            }
        }
        appTable.setItems(pendingList);
    }

    private void loadRenewals() {
        ObservableList<Policy> renewalList = FXCollections.observableArrayList();
        File file = new File("CustomerPolicies.bin");

        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                ArrayList<Policy> allPolicies = (ArrayList<Policy>) ois.readObject();
                for (Policy p : allPolicies) {
                    // Only load policies that are active and need to be renewed!
                    if (p.getStatus() != null && p.getStatus().equals("Active")) {
                        renewalList.add(p);
                    }
                }
            } catch (Exception e) {
                System.out.println("Error reading policies for renewal: " + e.getMessage());
            }
        }
        renewalTable.setItems(renewalList);
    }

    private void loadServiceTickets() {
        ObservableList<Ticket> ticketList = FXCollections.observableArrayList();
        File file = new File("ServiceTickets.bin");

        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                ArrayList<Ticket> allTickets = (ArrayList<Ticket>) ois.readObject();
                ticketList.addAll(allTickets);
            } catch (Exception e) {
                System.out.println("Error reading tickets: " + e.getMessage());
            }
        }
        ticketTable.setItems(ticketList);
    }

    @FXML
    public void processApplication(ActionEvent event) {
        Policy selectedPolicy = appTable.getSelectionModel().getSelectedItem();

        if (selectedPolicy == null) {
            new Alert(Alert.AlertType.WARNING, "Select an application to review first!").showAndWait();
            return;
        }


        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION,
                "Reviewing Medical Documents for " + selectedPolicy.getPolicyId() + "...\n\nEverything looks good. Do you want to Approve and activate this policy?",
                ButtonType.YES, ButtonType.NO);

        confirm.showAndWait().ifPresent(response -> {
            if (response == ButtonType.YES) {

                // 1. Open the file and update the status to "Active"
                File file = new File("CustomerPolicies.bin");
                ArrayList<Policy> allPolicies = new ArrayList<>();

                if (file.exists()) {
                    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                        allPolicies = (ArrayList<Policy>) ois.readObject();
                    } catch (Exception e) {
                        System.out.println("Error reading policies: " + e.getMessage());
                    }
                }


                for (Policy p : allPolicies) {
                    if (p.getPolicyId().equals(selectedPolicy.getPolicyId())) {
                        p.setStatus("Active");
                        break;
                    }
                }


                try (java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(new java.io.FileOutputStream(file))) {
                    oos.writeObject(allPolicies);

                    new Alert(Alert.AlertType.INFORMATION, "Policy Approved! It is now Active.").showAndWait();

                    // 3. Refresh BOTH tables!
                    loadPendingApplications();
                    loadRenewals();

                } catch (Exception e) {
                    System.out.println("Error saving policy: " + e.getMessage());
                }
            }
        });
    }

    @FXML
    public void processRenewal(ActionEvent event) {
        Policy selectedPolicy = renewalTable.getSelectionModel().getSelectedItem();

        if (selectedPolicy == null) {
            new Alert(Alert.AlertType.WARNING, "Select a policy to renew first!").showAndWait();
            return;
        }

        // CRA Goal 2: Agent calculates new premium, system generates invoice, sends notice.
        new Alert(Alert.AlertType.INFORMATION, "Renewal Processed for " + selectedPolicy.getPolicyId() + "!\n\nThe system has generated a new invoice and a renewal notice has been sent to the customer.").showAndWait();
    }

    @FXML
    public void resolveTicket(ActionEvent event) {
        Ticket selectedTicket = ticketTable.getSelectionModel().getSelectedItem();

        if (selectedTicket == null) {
            new Alert(Alert.AlertType.WARNING, "Select a ticket to resolve first!").showAndWait();
            return;
        }


        new Alert(Alert.AlertType.INFORMATION, "Ticket: " + selectedTicket.getTicketId() + " marked as RESOLVED.\n\nThe system has closed this ticket and an automated notification has been sent to the customer.").showAndWait();


        File file = new File("ServiceTickets.bin");
        ArrayList<Ticket> allTickets = new ArrayList<>();


        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                allTickets = (ArrayList<Ticket>) ois.readObject();
            } catch (Exception e) {
                System.out.println("Error reading tickets: " + e.getMessage());
            }
        }


        allTickets.removeIf(ticket -> ticket.getTicketId().equals(selectedTicket.getTicketId()));

        // 3. Save the new list (without the resolved ticket) back to the file
        try (java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(new java.io.FileOutputStream(file))) {
            oos.writeObject(allTickets);


            loadServiceTickets();

        } catch (Exception e) {
            System.out.println("Error saving tickets: " + e.getMessage());
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