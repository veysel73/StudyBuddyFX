package com.example.studybuddy;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TodoApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Todo.fxml"));
        primaryStage.setTitle("Modern Not Tutucusu");
        primaryStage.setScene(new Scene(root, 600, 450));
        primaryStage.setMinWidth(500);
        primaryStage.setMinHeight(400);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
