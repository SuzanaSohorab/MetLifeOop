package com.example.metlife.nisha;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class MainRunner extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        // Changed CustomerRunner.class to MainRunner.class here too!
        FXMLLoader fxmlLoader = new FXMLLoader(MainRunner.class.getResource("Login.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 600, 700);

        stage.setTitle("MetLife Portal");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}