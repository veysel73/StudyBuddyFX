package com.example.studybuddy;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TodoApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/studybuddy/todo.fxml"));
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("To-Do Listesi");
        primaryStage.setFullScreen(true); // EKRANI TAM EKRAN YAPAR
        primaryStage.setFullScreenExitHint(""); // ESC çıkış uyarısını gizler
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
