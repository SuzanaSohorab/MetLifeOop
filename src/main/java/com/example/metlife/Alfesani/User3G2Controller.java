package com.example.metlife.Alfesani;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class User3G2Controller
{
    @javafx.fxml.FXML
    private TextField premTextfield;
    @javafx.fxml.FXML
    private TextField policyIDtextfield;
    @javafx.fxml.FXML
    private TableColumn<Policy_Manager, Double> g2PremiumCol;
    @javafx.fxml.FXML
    private TableColumn<Policy_Manager,String> g2Idcol;
    @javafx.fxml.FXML
    private TableView<Policy_Manager> g2tableView;
    @javafx.fxml.FXML
    private TextField policynameTextfield;

    @javafx.fxml.FXML
    public void initialize() {
        g2Idcol.setCellValueFactory(new PropertyValueFactory<>("policyId"));
        g2PremiumCol.setCellValueFactory(new PropertyValueFactory<>("premiumAmount"));
    }
    static ArrayList<Policy_Manager> policyList = new ArrayList<>();

    @javafx.fxml.FXML
    public void g2updatebutton(ActionEvent actionEvent) {
        String id = policyIDtextfield.getText();
        String premiumStr = premTextfield.getText();

        String userName = policyIDtextfield.getText();


        if (id.isEmpty() || premiumStr.isEmpty()) {
            System.out.println("Error: Fields cannot be empty!");
            return;
        }

        try {
            double premium = Double.parseDouble(premiumStr);


            Policy_Manager updatedPolicy = new Policy_Manager(
                    id,
                    "Updated Name",
                    "N/A",
                    "N/A",
                    "Active",
                    "Update Entry",
                    premium,
                    0.0,
                    java.time.LocalDate.now()
            );


            policyList.add(updatedPolicy);


            g2tableView.getItems().clear();
            g2tableView.getItems().addAll(policyList);

            System.out.println("Success: Data added to shared list!");


            policyIDtextfield.clear();
            premTextfield.clear();

        } catch (NumberFormatException e) {
            System.out.println("Error: Premium must be a number!");
        }


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