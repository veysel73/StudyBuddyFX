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

            Scene scene = new Scene(root, 650, 500);
            scene.getStylesheets().add(getClass().getResource("/com/example/studybuddy/KelimeEzberleme.css").toExternalForm());

            primaryStage.setTitle("Kelime Ezberleme Sistemi");
            primaryStage.setScene(scene);
            primaryStage.show();

            // Controller sınıfını al (eğer initialize zaten @FXML içinde çağrılıyorsa gerekmez)
            KelimeEzberlemeController controller = loader.getController();
            controller.initialize();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
