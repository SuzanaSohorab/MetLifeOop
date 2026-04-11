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
import java.util.ArrayList;

public class User4G7Controller
{
    @javafx.fxml.FXML
    private TextField filterTextfield;
    @javafx.fxml.FXML
    private TableView<FinanceOfficer> goal7Tableview;
    @javafx.fxml.FXML
    private TableColumn<FinanceOfficer, String> goal7nameCol;
    @javafx.fxml.FXML
    private TableColumn<FinanceOfficer, Integer> goal7IDcol;
    @javafx.fxml.FXML
    private TextField NewnametextField;

    @javafx.fxml.FXML
    public void initialize() {
        goal7IDcol.setCellValueFactory(new PropertyValueFactory<>("id"));
        goal7nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
    }

    public static ArrayList<FinanceOfficer> financeList = new ArrayList<>();

    @javafx.fxml.FXML
    public void ShowOnclick(ActionEvent actionEvent) {
        String idInput = filterTextfield.getText();
        String nameInput = NewnametextField.getText();

        if (idInput.isEmpty() || nameInput.isEmpty()) {
            return;
        }

        FinanceOfficer newTransaction = new FinanceOfficer(
                idInput,
                nameInput,
                "Completed",
                0.0,
                "Standard Transaction",
                java.time.LocalDate.now()
        );

        financeList.add(newTransaction);

        goal7Tableview.getItems().clear();
        goal7Tableview.getItems().addAll(financeList);

        filterTextfield.clear();
        NewnametextField.clear();
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