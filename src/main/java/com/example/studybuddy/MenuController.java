package com.example.studybuddy;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MenuController {
    // Ana sayfa butonları
    @FXML private Button exitButton;
    @FXML private Button btn1;
    @FXML private Button btn2;
    @FXML private Button btn3;

    // Motivasyon bölümü
    @FXML private Label mesajLabel;
    @FXML private Button prevButton;
    @FXML private Button nextButton;

    private List<String> motivasyonMesajlari;
    private List<String> karistirilmisMesajlar;
    private int currentIndex = -1;
    private final Random random = new Random();

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
    }

    private void sonrakiMesajiGoster() {
        if (currentIndex < karistirilmisMesajlar.size() - 1) {
            currentIndex++;
            mesajLabel.setText(karistirilmisMesajlar.get(currentIndex));

            // Buton durumlarını güncelle
            prevButton.setDisable(currentIndex <= 0);
            nextButton.setDisable(currentIndex >= karistirilmisMesajlar.size() - 1);
        }
    }

    private void oncekiMesajiGoster() {
        if (currentIndex > 0) {
            currentIndex--;
            mesajLabel.setText(karistirilmisMesajlar.get(currentIndex));

            // Buton durumlarını güncelle
            prevButton.setDisable(currentIndex <= 0);
            nextButton.setDisable(currentIndex >= karistirilmisMesajlar.size() - 1);
        }
    }
}
