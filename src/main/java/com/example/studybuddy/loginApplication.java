package com.example.studybuddy;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class loginApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // FXML dosyasını yükle
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/studybuddy/Login.fxml"));
            Parent root = loader.load();

            // CSS dosyasını ekle
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/com/example/studybuddy/newLogin.css").toExternalForm());

            // Sahneyi ayarla
            primaryStage.setTitle("Giriş / Kayıt Sistemi");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}