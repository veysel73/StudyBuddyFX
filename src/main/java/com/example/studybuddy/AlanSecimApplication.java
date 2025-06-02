package com.example.studybuddy;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AlanSecimApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AlanSecimApplication.class.getResource("/com/example/studybuddy/AlanSecim.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 900, 700);
        scene.getStylesheets().add(getClass().getResource("/com/example/studybuddy/AlanSecim.css").toExternalForm());
        stage.setTitle("Kariyer Geliştirme Yolu");
        stage.setScene(scene);

        stage.setFullScreen(true); // ➤ EKRANI TAM EKRAN YAPAR
        stage.setFullScreenExitHint(""); // ➤ (İsteğe bağlı) ESC çıkış uyarısını gizler

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
