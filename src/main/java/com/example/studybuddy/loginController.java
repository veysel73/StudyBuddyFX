package com.example.studybuddy;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.animation.PauseTransition;
import javafx.util.Duration;
import java.io.IOException;

public class loginController {

    @FXML
    private TextField txtKullaniciAd;

    @FXML
    private PasswordField txtSifre;

    @FXML
    private Label lblDurum;

    @FXML
    private TextField txtSifreGoster;

    @FXML
    private CheckBox chkGoster;

    @FXML
    private void sifreyiGoster() {
        if (chkGoster.isSelected()) {
            txtSifreGoster.setText(txtSifre.getText());
            txtSifreGoster.setVisible(true);
            txtSifreGoster.setManaged(true);
            txtSifre.setVisible(false);
            txtSifre.setManaged(false);
        } else {
            txtSifre.setText(txtSifreGoster.getText());
            txtSifre.setVisible(true);
            txtSifre.setManaged(true);
            txtSifreGoster.setVisible(false);
            txtSifreGoster.setManaged(false);
        }
    }

    @FXML
    private void girisYap() {
        String kullanici = txtKullaniciAd.getText();
        String sifre = txtSifre.isVisible() ? txtSifre.getText() : txtSifreGoster.getText();


        if (kullanici.equals("admin") && sifre.equals("1234")) {
            lblDurum.setText("✅ Giriş başarılı! 3 saniye sonra yönlendirileceksiniz...");

            if (kullanici.equals("admin") && sifre.equals("1234")) {
                lblDurum.setText("✅ Giriş başarılı! 3 saniye sonra yönlendiriliyorsunuz...");

                // 3 saniyelik bekleme süresi ayarla
                PauseTransition pause = new PauseTransition(Duration.seconds(3));
                pause.setOnFinished(event -> {
                    try {
                        // Menü sayfasını yükle
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/studybuddy/Menu.fxml"));
                        Parent menuRoot = loader.load();

                        // Yeni bir scene oluştur
                        Scene menuScene = new Scene(menuRoot);

                        // Mevcut pencereyi al
                        Stage currentStage = (Stage) lblDurum.getScene().getWindow();

                        // Sahneyi ayarla ve göster
                        currentStage.setScene(menuScene);
                        currentStage.setTitle("Study Buddy - Menü");
                        currentStage.centerOnScreen(); // Pencereyi ortala

                    } catch (IOException e) {
                        lblDurum.setText("❌ Menü açılırken bir hata oluştu.");
                        System.err.println("Menü sayfası açılamadı: " + e.getMessage());
                        e.printStackTrace();
                    }
                });
                pause.play(); // Beklemeyi başlat
            }
            else {
                lblDurum.setVisible(true);
                lblDurum.setText("❌ Hatalı kullanıcı adı veya şifre.");
            }
    }
    }

}
