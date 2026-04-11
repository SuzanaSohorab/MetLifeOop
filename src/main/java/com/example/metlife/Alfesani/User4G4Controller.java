package com.example.metlife.Alfesani;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class User4G4Controller
{
    @javafx.fxml.FXML
    private TableColumn<FinanceOfficer, String> goal4nameCol;
    @javafx.fxml.FXML
    private ComboBox<String> statusCombobox;
    @javafx.fxml.FXML
    private TableColumn<FinanceOfficer, Integer> goal4IDcol;
    @javafx.fxml.FXML
    private TableView<FinanceOfficer> goal4Tableview;

    @javafx.fxml.FXML
    public void initialize() {
        goal4IDcol.setCellValueFactory(new PropertyValueFactory<>("id"));
        goal4nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        statusCombobox.getItems().addAll("Registered", "Unregistered");
    }

    @javafx.fxml.FXML
    public void updateOnclick(ActionEvent actionEvent) {
        String selectedStatus = statusCombobox.getValue();
        goal4Tableview.getItems().clear();

        if ("Registered".equals(selectedStatus)) {
            if (User4G2Controller.financeList != null && !User4G2Controller.financeList.isEmpty()) {
                goal4Tableview.getItems().addAll(User4G2Controller.financeList);
            }
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