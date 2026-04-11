package com.example.metlife.Alfesani;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class User4G1Controller
{
    @javafx.fxml.FXML
    private Label viewOutputlabel;
    @javafx.fxml.FXML
    private TextField CustomerIDtextfield;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void ConfirmOnclick(ActionEvent actionEvent) {
        String idInput = CustomerIDtextfield.getText();

        if (idInput.isEmpty()) {
            return;
        }

        viewOutputlabel.setText("0.0");

    }

    @javafx.fxml.FXML
    public void backdashboard(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("user4_FinanceOfficer.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        Stage stage=new Stage();
        stage.setScene(scene);
        stage.show();
    }
}