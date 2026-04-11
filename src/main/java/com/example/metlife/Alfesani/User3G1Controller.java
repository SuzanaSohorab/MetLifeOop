package com.example.metlife.Alfesani;

import java.time.LocalDate;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class User3G1Controller
{
    @javafx.fxml.FXML
    private Label successoutputlabel;
    @javafx.fxml.FXML
    private TextField policynameTextfield;
    @javafx.fxml.FXML
    private ComboBox<String> policyCombobox;

    @javafx.fxml.FXML
    public void initialize() {
        policyCombobox.getItems().addAll("Life Insurance", "Health Insurance", "Property Insurance", "Vehicle Insurance");
    }
    private ArrayList<Policy_Manager> policyList = new ArrayList<>();

    @javafx.fxml.FXML
    public void backdashboardOnclick(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("user3_Policymanager.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        Stage stage=new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @javafx.fxml.FXML
    public void createSavebutton(ActionEvent actionEvent) {
        String name = policynameTextfield.getText();
        String category = policyCombobox.getValue();

        if (name.isEmpty() || category == null) {
            successoutputlabel.setText("Error: Fill all fields!");
            successoutputlabel.setStyle("-fx-text-fill: red;");
            return;
        }

        try {

            Policy_Manager newPolicy = new Policy_Manager(
                    "ID-" + (policyList.size() + 1),
                    name,
                    "N/A",
                    "N/A",
                    "Active",
                    category,
                    0.0,
                    0.0,
                    java.time.LocalDate.now()
            );

            policyList.add(newPolicy);
            successoutputlabel.setText("Success: Saved to ArrayList!");
            successoutputlabel.setStyle("-fx-text-fill: green;");

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}