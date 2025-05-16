package com.example.studybuddy;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PomodoroController implements Initializable {

    @FXML private Label timerLabel;
    @FXML private Button startButton;
    @FXML private Button resetButton;
    @FXML private Button closeButton;
    @FXML private Button minimizeButton;
    @FXML private Button option25Button;
    @FXML private Button option35Button;
    @FXML private Button option45Button;
    @FXML private HBox timerOptionsContainer;
    @FXML private Pane mainPane;

    private Timeline timeline;
    private int timeSeconds;
    private int selectedDuration = 25; // Default 25 minutes
    private boolean isRunning = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        resetTimer();

        // Set selected option style initially
        selectTimerOption(option25Button);
    }

    @FXML
    private void handleStartPause() {
        if (isRunning) {
            pauseTimer();
            startButton.setText("▶ Başlat");
        } else {
            startTimer();
            startButton.setText("⏸ Duraklat");
        }
        isRunning = !isRunning;
    }

    @FXML
    private void handleReset() {
        pauseTimer();
        resetTimer();
        startButton.setText("▶ Başlat");
        isRunning = false;
    }

    @FXML
    private void handleClose() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleMinimize() {
        Stage stage = (Stage) minimizeButton.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void handleTimerOptionClick(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();

        // Reset all button styles
        option25Button.getStyleClass().remove("selected-option");
        option35Button.getStyleClass().remove("selected-option");
        option45Button.getStyleClass().remove("selected-option");

        // Apply selected style to clicked button
        selectTimerOption(clickedButton);

        // Determine which option was selected
        if (clickedButton == option25Button) {
            selectedDuration = 25;
        } else if (clickedButton == option35Button) {
            selectedDuration = 35;
        } else if (clickedButton == option45Button) {
            selectedDuration = 45;
        }

        // Reset the timer with new duration
        pauseTimer();
        resetTimer();
        startButton.setText("▶ Başlat");
        isRunning = false;
    }

    private void selectTimerOption(Button button) {
        button.getStyleClass().add("selected-option");
    }

    private void startTimer() {
        if (timeline != null) {
            timeline.stop();
        }

        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(1), event -> {
                    timeSeconds--;
                    updateTimerLabel();
                    if (timeSeconds <= 0) {
                        timeline.stop();
                        timeSeconds = selectedDuration * 60;
                        updateTimerLabel();
                        startButton.setText("▶ Başlat");
                        isRunning = false;

                        // Play notification sound or show alert when timer finishes
                        playNotificationSound();
                    }
                }));
        timeline.playFromStart();
    }

    private void pauseTimer() {
        if (timeline != null) {
            timeline.stop();
        }
    }

    private void resetTimer() {
        timeSeconds = selectedDuration * 60;
        updateTimerLabel();
    }

    private void updateTimerLabel() {
        int minutes = timeSeconds / 60;
        int seconds = timeSeconds % 60;
        timerLabel.setText(String.format("%02d:%02d", minutes, seconds));
    }

    private void playNotificationSound() {
        // In a real application, you would implement sound notification here
        System.out.println("Time's up! Pomodoro completed!");
    }
}