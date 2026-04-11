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

import java.io.IOException;

public class CustomerDownloadDocumentsController {


    @FXML private ComboBox<String> filterComboBox;
    @FXML private TableView<DocumentFile> documentTable;
    @FXML private TableColumn<DocumentFile, String> colDocName;
    @FXML private TableColumn<DocumentFile, String> colType;
    @FXML private TableColumn<DocumentFile, String> colDate;


    private ObservableList<DocumentFile> masterDocumentList;

    @FXML
    public void initialize() {
        colDocName.setCellValueFactory(new PropertyValueFactory<>("documentName"));
        colType.setCellValueFactory(new PropertyValueFactory<>("documentType"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("dateIssued"));


        masterDocumentList = FXCollections.observableArrayList(
                new DocumentFile("2025_Income_Tax_Certificate.pdf", "Tax Statements", "2026-01-15"),
                new DocumentFile("Receipt_March_2026.pdf", "Premium Receipts", "2026-03-01"),
                new DocumentFile("Claim_Approval_POL-123.pdf", "Claim Documents", "2026-02-20"),
                new DocumentFile("2024_Income_Tax_Certificate.pdf", "Tax Statements", "2025-01-10"),
                new DocumentFile("Receipt_February_2026.pdf", "Premium Receipts", "2026-02-01")
        );

        documentTable.setItems(masterDocumentList);
    }


    @FXML
    public void handleFilter(ActionEvent event) {
        String selectedOption = filterComboBox.getValue();

        if (selectedOption.equals("All Documents")) {

            documentTable.setItems(masterDocumentList);
        } else {

            ObservableList<DocumentFile> filteredList = FXCollections.observableArrayList();


            for (DocumentFile doc : masterDocumentList) {
                if (doc.getDocumentType().equals(selectedOption)) {
                    filteredList.add(doc);
                }
            }

            documentTable.setItems(filteredList);
        }
    }


    @FXML
    public void downloadDocument(ActionEvent event) {

        DocumentFile selectedDoc = documentTable.getSelectionModel().getSelectedItem();

        if (selectedDoc == null) {

            new Alert(Alert.AlertType.WARNING, "Please select a document from the table first!").showAndWait();
        } else {

            Alert success = new Alert(Alert.AlertType.INFORMATION);
            success.setTitle("Download Complete");
            success.setHeaderText(null);
            success.setContentText("Success! '" + selectedDoc.getDocumentName() + "' has been downloaded to your device.");
            success.showAndWait();
        }
    }


    @FXML
    public void goBack(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("CustomerDashboard.fxml"));
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
        window.show();
    }




    public static class DocumentFile {
        private String documentName;
        private String documentType;
        private String dateIssued;

        public DocumentFile(String documentName, String documentType, String dateIssued) {
            this.documentName = documentName;
            this.documentType = documentType;
            this.dateIssued = dateIssued;
        }

        public String getDocumentName() { return documentName; }
        public String getDocumentType() { return documentType; }
        public String getDateIssued() { return dateIssued; }
    }
}