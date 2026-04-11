package com.example.metlife.Alfesani;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class User3G4Controller
{
    @javafx.fxml.FXML
    private DatePicker G4datepick;
    @javafx.fxml.FXML
    private TableColumn<Policy_Manager,String> G4namecol;
    @javafx.fxml.FXML
    private TableView<Policy_Manager> g4Tableview;

    @javafx.fxml.FXML
    public void initialize() {
        G4namecol.setCellValueFactory(new PropertyValueFactory<>("policyName"));


    }

    @Deprecated
    public void G4datepicker(ActionEvent actionEvent) {
        if (G4datepick.getValue() == null) return;

        String selectedDate = G4datepick.getValue().toString();

        Policy_Manager newSaleEntry = new Policy_Manager(
                "CUST-" + (User3G2Controller.policyList.size() + 1),
                selectedDate,
                "New Customer",
                "Agent",
                "Unpaid",
                "Sale Entry",
                0.0,
                0.0,
                G4datepick.getValue()
        );

        User3G2Controller.policyList.add(newSaleEntry);

        g4Tableview.getItems().clear();
        g4Tableview.getItems().addAll(User3G2Controller.policyList);

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