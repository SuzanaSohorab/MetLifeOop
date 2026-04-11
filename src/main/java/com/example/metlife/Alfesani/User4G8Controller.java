package com.example.metlife.Alfesani;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class User4G8Controller
{
    @javafx.fxml.FXML
    private TableColumn<FinanceOfficer, String> goal8TransactionCol;
    @javafx.fxml.FXML
    private TableColumn<FinanceOfficer, Double> goal8amountCol;
    @javafx.fxml.FXML
    private TableView<FinanceOfficer> goal8Tableview;
    @javafx.fxml.FXML
    private ComboBox<String> New2comboBox;
    @javafx.fxml.FXML
    private Label New2outPutlabel;

    @javafx.fxml.FXML
    public void initialize() {
        goal8TransactionCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        goal8amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
        New2comboBox.getItems().addAll("Registered", "Unregistered");
    }

    @javafx.fxml.FXML
    public void CheckOnClick(ActionEvent actionEvent) {
        String status = New2comboBox.getValue();
        goal8Tableview.getItems().clear();
        New2outPutlabel.setText("");

        if ("Registered".equals(status)) {
            if (User4G7Controller.financeList != null && !User4G7Controller.financeList.isEmpty()) {
                goal8Tableview.getItems().addAll(User4G7Controller.financeList);
            }
        } else if ("Unregistered".equals(status)) {
            New2outPutlabel.setText("check goal 7 first");
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