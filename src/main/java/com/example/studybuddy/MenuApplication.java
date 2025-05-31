package com.example.studybuddy;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MenuApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/studybuddy/menu.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Ana Sayfa");

        Scene scene = new Scene(root, 600, 400);

        // 🔥 Tam ekran modu aktif
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);             // → Ekranı tam ekran yap
        primaryStage.setFullScreenExitHint("");       // → "ESC ile çıkılır" yazısını gizle
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
