package com.example.metlife.Alfesani;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class User3G3Controller
{
    @javafx.fxml.FXML
    private TableColumn<Policy_Manager,String> G3namecol;
    @javafx.fxml.FXML
    private TableColumn<Policy_Manager,Double> G3idcol;
    @javafx.fxml.FXML
    private TableView<Policy_Manager> g3tableView;

    @javafx.fxml.FXML
    public void initialize() {
        G3idcol.setCellValueFactory(new PropertyValueFactory<>("policyId"));
        G3namecol.setCellValueFactory(new PropertyValueFactory<>("clientName"));

    }

    @javafx.fxml.FXML
    public void suspendOnclick(ActionEvent actionEvent) {
        g3tableView.getItems().clear();
        g3tableView.getItems().addAll(User3G2Controller.policyList);



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