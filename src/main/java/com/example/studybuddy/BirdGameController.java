package com.example.studybuddy;


import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class BirdGameController {

    @FXML private AnchorPane gamePane;
    @FXML private Circle bird;
    @FXML private Label scoreLabel;
    @FXML private Label highScoreLabel;
    @FXML private Label gameOverLabel;
    @FXML private Button startButton;

    private double birdVelocity = -1;
    private double gravity = 0.3;
    private double jumpStrength = -7.5;
    private boolean gameRunning = false;
    private int score = 0;
    private int highScore = 0;

    private List<Rectangle> pipes = new ArrayList<>();
    private Random random = new Random();
    private AnimationTimer gameLoop;

    private long lastPipeTime = 0;
    private static final long PIPE_SPAWN_INTERVAL = 2_000_000_000L; // 2 saniye
    private static final String HIGH_SCORE_FILE = "flappy_bird_high_score.txt";

    @FXML
    public void initialize() {
        loadHighScore();
        setupGameLoop();
        setupInitialState();
    }

    private void setupInitialState() {
        bird.setCenterX(150);
        bird.setCenterY(300);
        gameOverLabel.setVisible(false);
        scoreLabel.setText("Score: 0");
        highScoreLabel.setText("High Score: " + highScore);
    }

    private void setupGameLoop() {
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (gameRunning) {
                    updateBird();

                    if (now - lastPipeTime > PIPE_SPAWN_INTERVAL) {
                        addPipe();
                        lastPipeTime = now;
                    }

                    updatePipes();
                    checkCollisions();
                }
            }
        };
    }

    @FXML
    public void handleMouseClick(MouseEvent event) {
        if (gameRunning) {
            jump();
        }
    }

    @FXML
    public void startGame() {
        if (!gameRunning) {
            resetGame();
            gameRunning = true;
            startButton.setVisible(false);
            gameOverLabel.setVisible(false);
            gameLoop.start();
        }
    }

    public void jump() {
        if (gameRunning) {
            birdVelocity = jumpStrength;
        }
    }

    private void resetGame() {
        // Kuşu başlangıç pozisyonuna ayarla
        bird.setCenterX(150);
        bird.setCenterY(300);
        birdVelocity = 0;

        // Boruları temizle
        for (Rectangle pipe : pipes) {
            gamePane.getChildren().remove(pipe);
        }
        pipes.clear();

        // Skoru sıfırla
        score = 0;
        scoreLabel.setText("Score: 0");

        lastPipeTime = 0;
    }

    private void updateBird() {
        birdVelocity += gravity;
        bird.setCenterY(bird.getCenterY() + birdVelocity);

        // Ekran dışına çıkmasını önle
        if (bird.getCenterY() - bird.getRadius() < 0) {
            bird.setCenterY(bird.getRadius());
            birdVelocity = 0;
        }

        // Aşağı düşerse oyunu bitir
        if (bird.getCenterY() + bird.getRadius() > gamePane.getHeight()) {
            endGame();
        }
    }

    private void addPipe() {
        // Boruların yükseklik aralığı
        double minHeight = 100;
        double maxHeight = gamePane.getHeight() - 200;
        double gapSize = 150; // Kuşun geçeceği boşluk

        // Alt ve üst boruların yüksekliklerini belirle
        double topHeight = minHeight + random.nextDouble() * (maxHeight - minHeight);
        double bottomY = topHeight + gapSize;
        double bottomHeight = gamePane.getHeight() - bottomY;

        // Üst boru
        Rectangle topPipe = createPipe(gamePane.getWidth(), 0, 60, topHeight);
        topPipe.getStyleClass().add("pipe");

        // Alt boru
        Rectangle bottomPipe = createPipe(gamePane.getWidth(), bottomY, 60, bottomHeight);
        bottomPipe.getStyleClass().add("pipe");

        // Puanı takip etmek için bir işaretçi
        Rectangle scoreCounter = createPipe(gamePane.getWidth() + 30, topHeight, 10, gapSize);
        scoreCounter.setOpacity(0); // Görünmez yap
        scoreCounter.setUserData("counter"); // Bu bir skor sayacı

        pipes.add(topPipe);
        pipes.add(bottomPipe);
        pipes.add(scoreCounter);

        gamePane.getChildren().addAll(topPipe, bottomPipe, scoreCounter);
    }

    private Rectangle createPipe(double x, double y, double width, double height) {
        Rectangle pipe = new Rectangle(x, y, width, height);
        return pipe;
    }

    private void updatePipes() {
        Iterator<Rectangle> iterator = pipes.iterator();
        while (iterator.hasNext()) {
            Rectangle pipe = iterator.next();

            // Boruyu sola doğru hareket ettir
            pipe.setX(pipe.getX() - 3);

            // Ekrandan çıkan boruları temizle
            if (pipe.getX() + pipe.getWidth() < 0) {
                gamePane.getChildren().remove(pipe);
                iterator.remove();
            }

            // Kuş skor noktasını geçtiyse puanı artır
            if ("counter".equals(pipe.getUserData()) &&
                    !pipe.isDisable() &&
                    bird.getCenterX() > pipe.getX() + pipe.getWidth()) {
                score++;
                scoreLabel.setText("Score: " + score);
                pipe.setDisable(true); // Bu sayacı devre dışı bırak
            }
        }
    }

    private void checkCollisions() {
        // Kuşun çarpışma sınırlarını hesapla
        double birdLeftX = bird.getCenterX() - bird.getRadius();
        double birdRightX = bird.getCenterX() + bird.getRadius();
        double birdTopY = bird.getCenterY() - bird.getRadius();
        double birdBottomY = bird.getCenterY() + bird.getRadius();

        for (Rectangle pipe : pipes) {
            // Skor sayaçlarını dikkate alma
            if ("counter".equals(pipe.getUserData())) {
                continue;
            }

            // Boru ile çarpışma kontrolü
            if (birdRightX > pipe.getX() &&
                    birdLeftX < pipe.getX() + pipe.getWidth() &&
                    birdBottomY > pipe.getY() &&
                    birdTopY < pipe.getY() + pipe.getHeight()) {

                endGame();
                break;
            }
        }
    }

    private void endGame() {
        gameRunning = false;
        gameLoop.stop();
        gameOverLabel.setVisible(true);
        startButton.setVisible(true);

        // En yüksek skoru güncelle
        if (score > highScore) {
            highScore = score;
            highScoreLabel.setText("High Score: " + highScore);
            saveHighScore();
        }
    }

    private void loadHighScore() {
        try {
            File file = new File(HIGH_SCORE_FILE);
            if (file.exists()) {
                Scanner scanner = new Scanner(file);
                if (scanner.hasNextInt()) {
                    highScore = scanner.nextInt();
                }
                scanner.close();
            }
        } catch (FileNotFoundException e) {
            // Dosya bulunamazsa, yeni bir tane oluşturulacak
        }
    }

    private void saveHighScore() {
        try {
            FileWriter writer = new FileWriter(HIGH_SCORE_FILE);
            writer.write(String.valueOf(highScore));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isGameRunning() {
        return gameRunning;
    }
}