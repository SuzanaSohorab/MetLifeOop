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

public class User3G8Controller
{
    @javafx.fxml.FXML
    private TableColumn<Policy_Manager,String> g8ChnagedesCol;
    @javafx.fxml.FXML
    private TableColumn<Policy_Manager,String> g8changeCol;
    @javafx.fxml.FXML
    private TableView<Policy_Manager> g8Tableview;
    @javafx.fxml.FXML
    private TableColumn<Policy_Manager,Integer> NewauditID;
    @javafx.fxml.FXML
    private TextField policyIDtextfield;

    @javafx.fxml.FXML
    public void initialize() {
        g8changeCol.setCellValueFactory(new PropertyValueFactory<>("policyId"));
        g8ChnagedesCol.setCellValueFactory(new PropertyValueFactory<>("expiryDate"));
        NewauditID.setCellValueFactory(new PropertyValueFactory<>("description"));


    }

    @javafx.fxml.FXML
    public void viewAuditOnclick(ActionEvent actionEvent) {

            String idInput = policyIDtextfield.getText();

            if (idInput.isEmpty()) {
                return;
            }

            Policy_Manager auditEntry = new Policy_Manager(
                    idInput,
                    "Audit",
                    "N/A",
                    "N/A",
                    "Pending",
                    "pending",
                    0.0,
                    0.0,
                    java.time.LocalDate.of(2027, 1, 1) // এখানে তারিখ সেট করা হয়েছে
            );

            User3G2Controller.policyList.add(auditEntry);

            g8Tableview.getItems().clear();
            g8Tableview.getItems().addAll(User3G2Controller.policyList);

            policyIDtextfield.clear();

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