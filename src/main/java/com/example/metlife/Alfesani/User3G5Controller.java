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

public class User3G5Controller
{
    @javafx.fxml.FXML
    private TableColumn<Policy_Manager,String> g5changecol;
    @javafx.fxml.FXML
    private TableView<Policy_Manager> G5Tableview;
    @javafx.fxml.FXML
    private TableColumn<Policy_Manager,Integer> g5reqcol;
    @javafx.fxml.FXML
    private TextField reqIDtextfield;
    @javafx.fxml.FXML
    private TextField DetailsTextfield;

    @javafx.fxml.FXML
    public void initialize() {
        g5reqcol.setCellValueFactory(new PropertyValueFactory<>("policyId"));
        g5changecol.setCellValueFactory(new PropertyValueFactory<>("description"));
    }

    @javafx.fxml.FXML
    public void approveOnclick(ActionEvent actionEvent) {
        String id = reqIDtextfield.getText();
        String details = DetailsTextfield.getText();

        if (id.isEmpty() || details.isEmpty()) {
            return;
        }

        Policy_Manager approvedReq = new Policy_Manager(
                id,
                "Policy Change",
                "N/A",
                "N/A",
                "Approved",
                details,
                0.0,
                0.0,
                java.time.LocalDate.now()
        );

        User3G2Controller.policyList.add(approvedReq);

        G5Tableview.getItems().clear();
        G5Tableview.getItems().addAll(User3G2Controller.policyList);

        reqIDtextfield.clear();
        DetailsTextfield.clear();
    }

    @javafx.fxml.FXML
    public void backdashboard(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("user3_Policymanager.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        Stage stage=new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @javafx.fxml.FXML
    public void rejectOnclick(ActionEvent actionEvent) {
        reqIDtextfield.clear();
        DetailsTextfield.clear();
    }
}