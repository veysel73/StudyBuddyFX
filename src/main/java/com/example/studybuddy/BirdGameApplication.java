package com.example.studybuddy;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BirdGameApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("birdGame.fxml"));
            Parent root = loader.load();

            BirdGameController controller = loader.getController();

            Scene scene = new Scene(root, 800, 600);
            scene.getStylesheets().add(getClass().getResource("birdGame.css").toExternalForm());

            // Tuş olaylarını dinle
            scene.setOnKeyPressed(event -> {
                switch (event.getCode()) {
                    case SPACE:
                        controller.jump();
                        break;
                    case ENTER:
                        if (!controller.isGameRunning()) {
                            controller.startGame();
                        }
                        break;
                    default:
                        break;
                }
            });

            primaryStage.setTitle("Flappy Bird Game");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();

            // Sahnede odaklan ki klavye olayları çalışsın
            root.requestFocus();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}