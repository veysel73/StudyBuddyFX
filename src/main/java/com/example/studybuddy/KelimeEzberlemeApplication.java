package com.example.studybuddy;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class KelimeEzberlemeApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/studybuddy/KelimeEzberleme.fxml"));

            Parent root = loader.load();

            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/com/example/studybuddy/KelimeEzberleme.css").toExternalForm());

            primaryStage.setTitle("Kelime Ezberleme Sistemi");
            primaryStage.setScene(scene);
            primaryStage.setMaximized(true); // Tam ekran olarak başlat
            primaryStage.show();

            // Controller sınıfını al
            KelimeEzberlemeController controller = loader.getController();
            controller.initialize();
            controller.setStage(primaryStage); // Stage'i controller'a gönder

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}