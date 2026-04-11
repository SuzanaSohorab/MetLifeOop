package com.example.metlife.Alfesani;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class User4G5Controller
{
    @javafx.fxml.FXML
    private Label CalculateLabel;
    @javafx.fxml.FXML
    private ComboBox<String> NewcomboBox;

    @javafx.fxml.FXML
    public void initialize() {
        NewcomboBox.getItems().addAll("Registered", "Unregistered");
    }

    @javafx.fxml.FXML
    public void backdashboard(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("user4_FinanceOfficer.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        Stage stage=new Stage();
        stage.setScene(scene);
        stage.show();
    }


    @javafx.fxml.FXML
    public void CalculateonClick(ActionEvent actionEvent) {
        String selectedOption = NewcomboBox.getValue();


        if (selectedOption.equals("Registered")) {
            CalculateLabel.setText("0");
        } else if (selectedOption.equals("Unregistered")) {
            CalculateLabel.setText("You need to register first");
        }
    }



}