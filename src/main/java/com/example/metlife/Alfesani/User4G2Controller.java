package com.example.metlife.Alfesani;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class User4G2Controller
{
    @javafx.fxml.FXML
    private TableColumn<FinanceOfficer, String> Goal2Namecol;
    @javafx.fxml.FXML
    private TableColumn<FinanceOfficer, Integer> goal2Idcol;
    @javafx.fxml.FXML
    private DatePicker g2datepciker;
    @javafx.fxml.FXML
    private TableView<FinanceOfficer> goal2Tableview;
    @javafx.fxml.FXML
    private TextField newNametextfield;
    @javafx.fxml.FXML
    private TextField newIdtextfield;

    @javafx.fxml.FXML
    public void initialize() {
        goal2Idcol.setCellValueFactory(new PropertyValueFactory<>("id"));
        Goal2Namecol.setCellValueFactory(new PropertyValueFactory<>("name"));

    }

    public static ArrayList<FinanceOfficer> financeList = new ArrayList<>();

    @javafx.fxml.FXML
    public void generateOnclick(ActionEvent actionEvent) {
        String idInput = newIdtextfield.getText();
        String nameInput = newNametextfield.getText();
        LocalDate selectedDate = g2datepciker.getValue();

        if (idInput.isEmpty() || nameInput.isEmpty() || selectedDate == null) {
            return;
        }

        FinanceOfficer newReport = new FinanceOfficer(
                idInput,
                nameInput,
                "Success",
                0.0,
                "Monthly Revenue Report",
                selectedDate
        );

        financeList.add(newReport);

        goal2Tableview.getItems().clear();
        goal2Tableview.getItems().addAll(financeList);

        newIdtextfield.clear();
        newNametextfield.clear();
        g2datepciker.setValue(null);
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