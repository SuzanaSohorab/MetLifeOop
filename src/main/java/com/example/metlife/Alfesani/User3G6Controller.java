package com.example.metlife.Alfesani;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class User3G6Controller
{
    @javafx.fxml.FXML
    private TableView<Policy_Manager> G6tableview;
    @javafx.fxml.FXML
    private TableColumn<Policy_Manager,String> g6clientcol;
    @javafx.fxml.FXML
    private TableColumn<Policy_Manager,Integer> g6idcol;
    @javafx.fxml.FXML
    private ComboBox<String> g6Combobox;
    @javafx.fxml.FXML
    private TextField clientnameTextfield;

    @javafx.fxml.FXML
    public void initialize() {
        g6idcol.setCellValueFactory(new PropertyValueFactory<>("policyId"));
        g6clientcol.setCellValueFactory(new PropertyValueFactory<>("policyName"));

        g6Combobox.getItems().addAll("user", "new");
    }

    @javafx.fxml.FXML
    public void assignOnclick(ActionEvent actionEvent) {
        String selectedOption = g6Combobox.getValue();
        String name = clientnameTextfield.getText();

        if (selectedOption == null || name.isEmpty()) {
            return;
        }

        String assignedId = "00";
        if (selectedOption.equals("user")) {
            assignedId = "01";
        } else if (selectedOption.equals("new")) {
            assignedId = "02";
        }

        Policy_Manager newAssignment = new Policy_Manager(
                assignedId,
                name,
                "N/A",
                selectedOption,
                "Assigned",
                "Policy Assignment",
                0.0,
                0.0,
                java.time.LocalDate.now()
        );

        User3G2Controller.policyList.add(newAssignment);

        G6tableview.getItems().clear();
        G6tableview.getItems().addAll(User3G2Controller.policyList);

        clientnameTextfield.clear();
        g6Combobox.setValue(null);


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