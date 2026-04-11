package com.example.metlife.Alfesani;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class User3G7Controller
{
    @javafx.fxml.FXML
    private TableColumn<Policy_Manager,String> g7dateCol;
    @javafx.fxml.FXML
    private TableView<Policy_Manager> G7Tableview;
    @javafx.fxml.FXML
    private TableColumn<Policy_Manager,Integer> g7idCol;
    @javafx.fxml.FXML
    private TextField newIDtextfield;

    @javafx.fxml.FXML
    public void initialize() {
       g7idCol.setCellValueFactory(new PropertyValueFactory<>("policyId"));
        g7dateCol.setCellValueFactory(new PropertyValueFactory<>("expiryDate"));
    }

    @javafx.fxml.FXML
    public void confirmOnclick(ActionEvent actionEvent) {
        String idStr = newIDtextfield.getText();

        if (idStr.isEmpty()) {
            return;
        }

        java.time.LocalDate defaultDate = java.time.LocalDate.of(2027, 12, 4);

        Policy_Manager deactivationEntry = new Policy_Manager(
                idStr,
                "Deactivation Policy",
                "N/A",
                "N/A",
                "Expired",
                "Deactivation Request",
                0.0,
                0.0,
                defaultDate
        );

        User3G2Controller.policyList.add(deactivationEntry);

        G7Tableview.getItems().clear();
        G7Tableview.getItems().addAll(User3G2Controller.policyList);

        newIDtextfield.clear();
    }

    @javafx.fxml.FXML
    public void backdashboard(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("user3_Policymanager.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        Stage stage=new Stage();
        stage.setScene(scene);
        stage.show();
    }
}