package com.example.studybuddy;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.IOException;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.animation.PauseTransition;
import javafx.util.Duration;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

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

    // Kullanıcı bilgilerini saklayacak dosya yolu
    private final String KULLANICI_DOSYASI = "kullanicilar.txt";

    // Kullanıcı bilgilerini saklayacak Map (kullanıcı adı -> şifre)
    private Map<String, String> kullanicilar = new HashMap<>();

    @FXML
    private void initialize() {
        // Uygulama başladığında kullanıcı dosyasını yükle
        kullaniciDosyasiniYukle();

        // Şifre göster alanını başlangıçta gizle
        txtSifreGoster.setVisible(false);
        txtSifreGoster.setManaged(false);
    }

    // Kullanıcı dosyasını yükleyen metot
    private void kullaniciDosyasiniYukle() {
        File dosya = new File(KULLANICI_DOSYASI);
        if (!dosya.exists()) {
            // Dosya yoksa varsayılan admin kullanıcısını ekle
            kullanicilar.put("admin", "1234");
            kullaniciDosyasiniKaydet();
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(dosya))) {
            String satir;
            while ((satir = reader.readLine()) != null) {
                String[] parcalar = satir.split(":");
                if (parcalar.length == 2) {
                    kullanicilar.put(parcalar[0], parcalar[1]);
                }
            }
        } catch (IOException e) {
            System.err.println("Kullanıcı dosyası okunamadı: " + e.getMessage());
        }
    }

    // Kullanıcı dosyasını kaydeden metot
    private void kullaniciDosyasiniKaydet() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(KULLANICI_DOSYASI))) {
            for (Map.Entry<String, String> entry : kullanicilar.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Kullanıcı dosyası kaydedilemedi: " + e.getMessage());
        }
    }

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

        // Önce kullanıcılar map'inden kontrol et
        if (kullanicilar.containsKey(kullanici) && kullanicilar.get(kullanici).equals(sifre)) {
            basariliGiris();
        }
        // Eski statik kontrol yöntemi (geriye dönük uyumluluk için)
        else if (kullanici.equals("admin") && sifre.equals("1234")) {
            basariliGiris();
        }
        else {
            lblDurum.setVisible(true);
            lblDurum.setText("❌ Hatalı kullanıcı adı veya şifre.");
        }
    }

    // Başarılı giriş durumunda çağrılacak metot
    private void basariliGiris() {
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

                currentStage.setScene(menuScene);
                currentStage.setTitle("Study Buddy - Menü");
                currentStage.setFullScreenExitKeyCombination(null); // ESC çıkış anahtarını kaldır
                currentStage.setFullScreenExitHint("");             // Tam ekran çıkış ipucunu boş yap
                currentStage.setFullScreen(true);                    // Tam ekran yap
                currentStage.centerOnScreen();
                currentStage.show();



            } catch (IOException e) {
                lblDurum.setText("❌ Menü açılırken bir hata oluştu.");
                System.err.println("Menü sayfası açılamadı: " + e.getMessage());
                e.printStackTrace();
            }
        });
        pause.play(); // Beklemeyi başlat
    }

    @FXML
    private void kayitOl() {
        // Kayıt olma diyaloğunu göster
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Kayıt Ol");
        dialog.setHeaderText("Yeni Kullanıcı Kaydı");

        // Kayıt formunu oluştur
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField yeniKullaniciAdi = new TextField();
        yeniKullaniciAdi.setPromptText("Kullanıcı Adı");
        PasswordField yeniSifre = new PasswordField();
        yeniSifre.setPromptText("Şifre");
        PasswordField sifreTekrar = new PasswordField();
        sifreTekrar.setPromptText("Şifre Tekrar");

        grid.add(new Label("Kullanıcı Adı:"), 0, 0);
        grid.add(yeniKullaniciAdi, 1, 0);
        grid.add(new Label("Şifre:"), 0, 1);
        grid.add(yeniSifre, 1, 1);
        grid.add(new Label("Şifre Tekrar:"), 0, 2);
        grid.add(sifreTekrar, 1, 2);

        dialog.getDialogPane().setContent(grid);

        // Butonları ekle
        ButtonType kaydetButon = new ButtonType("Kaydet", ButtonBar.ButtonData.OK_DONE);
        ButtonType iptalButon = new ButtonType("İptal", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(kaydetButon, iptalButon);

        // Sonucu işle
        dialog.showAndWait().ifPresent(response -> {
            if (response == kaydetButon) {
                String yeniKullanici = yeniKullaniciAdi.getText();
                String yeniParola = yeniSifre.getText();
                String tekrarParola = sifreTekrar.getText();

                // Boş alan kontrolü
                if (yeniKullanici.isEmpty() || yeniParola.isEmpty()) {
                    lblDurum.setText("❌ Kullanıcı adı ve şifre boş olamaz!");
                    return;
                }

                // Şifre eşleşme kontrolü
                if (!yeniParola.equals(tekrarParola)) {
                    lblDurum.setText("❌ Şifreler eşleşmiyor!");
                    return;
                }

                // Kullanıcı adı benzersizlik kontrolü
                if (kullanicilar.containsKey(yeniKullanici)) {
                    lblDurum.setText("❌ Bu kullanıcı adı zaten kullanılıyor!");
                    return;
                }

                // Yeni kullanıcıyı ekle
                kullanicilar.put(yeniKullanici, yeniParola);
                kullaniciDosyasiniKaydet();

                lblDurum.setText("✅ Kayıt başarılı! Giriş yapabilirsiniz.");
            }
        });
    }

}