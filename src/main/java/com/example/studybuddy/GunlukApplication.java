package com.example.studybuddy;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GunlukApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GunlukApplication.class.getResource("Gunluk.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root, 600, 500);
        stage.setTitle("Günlük Uygulaması");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}