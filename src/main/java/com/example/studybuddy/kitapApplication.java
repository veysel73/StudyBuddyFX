package com.example.studybuddy;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class kitapApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/studybuddy/kitapOneri.fxml"));

        Scene scene = new Scene(root, 800, 600);

        // CSS dosyasını burada ekliyoruz:
        scene.getStylesheets().add(getClass().getResource("/com/example/studybuddy/kitapOneri.css").toExternalForm());

        primaryStage.setTitle("Kitap Öneri Uygulaması");
        primaryStage.setScene(scene);
        primaryStage.setFullScreenExitHint("");
        primaryStage.setMaximized(true); // Tam ekran olarak başlat
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
