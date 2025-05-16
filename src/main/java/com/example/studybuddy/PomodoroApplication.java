package com.example.studybuddy;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PomodoroApplication extends Application {

    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/studybuddy/PomodoroView.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/com/example/studybuddy/Pomodoro.css").toExternalForm());

            // Make window draggable
            root.setOnMousePressed(event -> {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            });
            root.setOnMouseDragged(event -> {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            });

            primaryStage.setScene(scene);
            primaryStage.setTitle("Pomodoro Timer");
            primaryStage.initStyle(StageStyle.UNDECORATED); // Removes default window decoration
            primaryStage.setResizable(true);

            // Tam ekran ayarı
            primaryStage.setMaximized(true);

            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}