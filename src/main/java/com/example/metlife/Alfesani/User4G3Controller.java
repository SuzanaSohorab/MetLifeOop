package com.example.metlife.Alfesani;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class User4G3Controller
{
    @javafx.fxml.FXML
    private TableView<FinanceOfficer> goal3tableview;
    @javafx.fxml.FXML
    private TableColumn<FinanceOfficer, String> goal3Namecol;
    @javafx.fxml.FXML
    private TableColumn<FinanceOfficer, Integer> goal3iDcol;

    @javafx.fxml.FXML
    public void initialize() {
        goal3iDcol.setCellValueFactory(new PropertyValueFactory<>("id"));
        goal3Namecol.setCellValueFactory(new PropertyValueFactory<>("name"));
    }

    @javafx.fxml.FXML
    public void verifyOnclick(ActionEvent actionEvent) {
        goal3tableview.getItems().clear();

        if (User4G2Controller.financeList != null && !User4G2Controller.financeList.isEmpty()) {
            goal3tableview.getItems().addAll(User4G2Controller.financeList);
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