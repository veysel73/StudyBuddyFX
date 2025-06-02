package com.example.studybuddy;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class loginApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Debug resource loading
            debugResourcePaths();

            // Load FXML with multiple fallback approaches
            Parent root = loadFXMLWithFallbacks();

            // Setup and show scene
            setupAndShowStage(primaryStage, root);

        } catch (Exception e) {
            handleStartupError(e);
        }
    }

    private Parent loadFXMLWithFallbacks() throws IOException {
        // Try multiple ways to locate the FXML file
        URL fxmlUrl = getClass().getResource("/com/example/studybuddy/login.fxml");

        if (fxmlUrl == null) {
            fxmlUrl = getClass().getClassLoader().getResource("com/example/studybuddy/login.fxml");
        }

        if (fxmlUrl == null) {
            // Try absolute path as last resort
            Path path = Paths.get("src/main/resources/com/example/studybuddy/login.fxml").toAbsolutePath();
            if (Files.exists(path)) {
                fxmlUrl = path.toUri().toURL();
            } else {
                throw new IOException("FXML file not found in any of the searched locations");
            }
        }

        return FXMLLoader.load(fxmlUrl);
    }

    private void setupAndShowStage(Stage primaryStage, Parent root) {
        Scene scene = new Scene(root);

        // Try to load CSS
        URL cssUrl = getClass().getResource("/com/example/studybuddy/newLogin.css");
        if (cssUrl != null) {
            scene.getStylesheets().add(cssUrl.toExternalForm());
        }

        primaryStage.setTitle("StudyBuddy Login");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private void debugResourcePaths() {
        try {
            System.out.println("Debugging resource paths:");
            System.out.println("1. ClassLoader resources:");
            var resources = getClass().getClassLoader()
                    .getResources("com/example/studybuddy/login.fxml");
            while (resources.hasMoreElements()) {
                System.out.println("  Found: " + resources.nextElement());
            }

            System.out.println("2. Absolute resource path:");
            Path resPath = Paths.get("src/main/resources").toAbsolutePath();
            System.out.println("  Resources dir: " + resPath);

        } catch (IOException e) {
            System.err.println("Debug failed: " + e.getMessage());
        }
    }

    private void handleStartupError(Exception e) {
        System.err.println("Application startup failed:");
        e.printStackTrace();

        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Startup Error");
            alert.setHeaderText("Application failed to start");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            Platform.exit();
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}