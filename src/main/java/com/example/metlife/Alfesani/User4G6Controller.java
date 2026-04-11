package com.example.metlife.Alfesani;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class User4G6Controller
{
    @javafx.fxml.FXML
    private TableColumn<FinanceOfficer, Integer> goal5IDcol;
    @javafx.fxml.FXML
    private TableColumn<FinanceOfficer, String> goal6nameCol;
    @javafx.fxml.FXML
    private TableView<FinanceOfficer> goal6tableview;
    @javafx.fxml.FXML
    private Label newOutputlabel;

    @javafx.fxml.FXML
    public void initialize() {
        goal5IDcol.setCellValueFactory(new PropertyValueFactory<>("id"));
        goal6nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

    }

    @javafx.fxml.FXML
    public void approveOnclick(ActionEvent actionEvent) {
        goal6tableview.getItems().clear();
        newOutputlabel.setText("");

        if (User4G2Controller.financeList != null && !User4G2Controller.financeList.isEmpty()) {
            goal6tableview.getItems().addAll(User4G2Controller.financeList);
        } else {
            newOutputlabel.setText("You must register first");
        }
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