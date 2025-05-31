package com.example.studybuddy;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MenuController {
    // Ana sayfa butonları
    @FXML private Button exitButton;
    @FXML private Button btn1;  // Pomodoro
    @FXML private Button btn2;  // To-Do List (şu an event yok)
    @FXML private Button btn3;  // Kelime Ezberleme

    // Yeni eklenen butonlar
    @FXML private Button btnNotHesaplama;
    @FXML private Button btnKitapOneri;
    @FXML private Button btnGunlukTutma;
    @FXML private Button btnAlanGelistirme;

    // Motivasyon bölümü
    @FXML private Label mesajLabel;
    @FXML private Button prevButton;
    @FXML private Button nextButton;

    private List<String> motivasyonMesajlari;
    private List<String> karistirilmisMesajlar;
    private int currentIndex = -1;
    @FXML
    private StackPane contentPane;

    @FXML
    private void notHesaplayiciAc() {
        try {
            VBox notHesaplayici = FXMLLoader.load(getClass().getResource("NotHesaplayici.fxml"));
            contentPane.getChildren().clear();
            contentPane.getChildren().add(notHesaplayici);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void initialize() {
        // Motivasyon mesajlarını yükle
        motivasyonMesajlari = List.of(
                "Başarı, küçük hatalarla dolu bir yoldur.",
                "Bugünün işini yarına bırakma!",
                "Hayallerin peşinden git, asla vazgeçme!",
                "Her gün yeni bir fırsattır.",
                "Zorluklar, seni daha güçlü yapar.",
                "Kendine inan, başaracaksın!",
                "Küçük adımlar büyük sonuçlar doğurur."
        );

        karistirilmisMesajlar = new ArrayList<>(motivasyonMesajlari);
        Collections.shuffle(karistirilmisMesajlar);

        // Buton olaylarını bağla
        nextButton.setOnAction(event -> sonrakiMesajiGoster());
        prevButton.setOnAction(event -> oncekiMesajiGoster());

        // İlk mesajı göster
        sonrakiMesajiGoster();
        prevButton.setDisable(true);

        // Çıkış butonu işlevi
        exitButton.setOnAction(event -> System.exit(0));

        // Pomodoro uygulaması için btn1 aksiyonu
        btn1.setOnAction(event -> pomodoroUygulamasiniAc());

        // Kelime Ezberleme uygulaması için btn3 aksiyonu
        btn3.setOnAction(event -> kelimeEzberlemeUygulamasiniAc());

        // Yeni butonlara tıklama olaylarını bağla
        btnNotHesaplama.setOnAction(event -> notHesaplamaUygulamasiniAc());
        btnKitapOneri.setOnAction(event -> kitapOneriUygulamasiniAc());
        btnGunlukTutma.setOnAction(event -> gunlukTutmaUygulamasiniAc());
        btnAlanGelistirme.setOnAction(event -> alanGelistirmeUygulamasiniAc());
    }

    private void sonrakiMesajiGoster() {
        if (currentIndex < karistirilmisMesajlar.size() - 1) {
            currentIndex++;
            mesajLabel.setText(karistirilmisMesajlar.get(currentIndex));
            prevButton.setDisable(currentIndex <= 0);
            nextButton.setDisable(currentIndex >= karistirilmisMesajlar.size() - 1);
        }
    }

    private void oncekiMesajiGoster() {
        if (currentIndex > 0) {
            currentIndex--;
            mesajLabel.setText(karistirilmisMesajlar.get(currentIndex));
            prevButton.setDisable(currentIndex <= 0);
            nextButton.setDisable(currentIndex >= karistirilmisMesajlar.size() - 1);
        }
    }

    // Örnek açma metotları — kendi uygulamalarına göre değiştir

    public void pomodoroUygulamasiniAc() {
        try {
            Stage stage = new Stage();
            PomodoroApplication app = new PomodoroApplication();
            app.start(stage);
        } catch (Exception e) {
            System.err.println("Pomodoro uygulaması açılamadı: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void kelimeEzberlemeUygulamasiniAc() {
        try {
            Stage stage = new Stage();
            KelimeEzberlemeApplication app = new KelimeEzberlemeApplication();
            app.start(stage);
        } catch (Exception e) {
            System.err.println("Kelime Ezberleme uygulaması açılamadı: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void notHesaplamaUygulamasiniAc() {
        try {
            Stage stage = new Stage();
            NotHesaplayiciApplication app = new NotHesaplayiciApplication();
            app.start(stage);
        } catch (Exception e) {
            System.err.println("Not Hesaplama uygulaması açılamadı: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void kitapOneriUygulamasiniAc() {
        try {
            Stage stage = new Stage();
            kitapApplication app = new kitapApplication();
            app.start(stage);
        } catch (Exception e) {
            System.err.println("Kitap Öneri uygulaması açılamadı: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void gunlukTutmaUygulamasiniAc() {
        try {
            Stage stage = new Stage();
            GunlukApplication app = new GunlukApplication();
            app.start(stage);
        } catch (Exception e) {
            System.err.println("Günlük Tutma uygulaması açılamadı: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void alanGelistirmeUygulamasiniAc() {
        try {
            Stage stage = new Stage();
            AlanSecimApplication app = new AlanSecimApplication();
            app.start(stage);
        } catch (Exception e) {
            System.err.println("Alan Geliştirme uygulaması açılamadı: " + e.getMessage());
            e.printStackTrace();
        }
    }
    @FXML
    private void notHesaplayiciAc(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("notHesaplayici.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);

        // Tam ekran yap
        stage.setFullScreen(true);
    }
    @FXML
    private void kitapOneriUygulamasiniAc(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("btnKitapOneri.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);

        // Tam ekran yap
        stage.setFullScreen(true);
    }
    @FXML
    private void gunlukTutmaUygulamasiniAc(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("btnGunlukTutma.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);

        // Tam ekran yap
        stage.setFullScreen(true);
    }

    @FXML
    private void pomodoroUygulamasiniAc(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("btn1.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);

        // Tam ekran yap
        stage.setFullScreen(true);
    } @FXML
    private void alanGelistirmeUygulamasiniAc(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("btnAlanGelistirme.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);

        // Tam ekran yap
        stage.setFullScreen(true);
    }
    @FXML
    private void kelimeEzberlemeUygulamasiniAc(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("btn2.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);

        // Tam ekran yap
        stage.setFullScreen(true);
    }
}
