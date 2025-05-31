package com.example.studybuddy;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

public class KelimeEzberlemeController {

    // FXML ile bağlantılı bileşenler
    @FXML private Label kartIcerikLabel; // Kartın içeriğini gösteren etiket
    @FXML private VBox kartContainer; // Kartın bulunduğu ana container
    @FXML private TextField onYuzTextField; // Kart ön yüzü için metin kutusu
    @FXML private TextField cevapYuzuTextField; // Kart arka yüzü için metin kutusu
    @FXML private Button kartEkleButton; // Yeni kart ekleme butonu
    @FXML private Button sonrakiKartButton; // Sonraki kart butonu
    @FXML private Label durumLabel; // Durum bilgisi etiketi
    @FXML private HBox tekrarButtonBox; // Tekrar zamanı seçenekleri
    @FXML private Button birDkButton; // 1 dakika sonra tekrar butonu
    @FXML private Button ucDkButton; // 3 dakika sonra tekrar butonu
    @FXML private Button sekizDkButton; // 8 dakika sonra tekrar butonu
    @FXML private Button onBesDkButton; // 15 dakika sonra tekrar butonu
    @FXML private Button yirmiDortSaatButton; // 24 saat sonra tekrar butonu
    @FXML private Button geriDonButton; // Ana menüye dön butonu

    // Uygulama penceresi referansı
    private Stage stage;

    // Veri yapıları
    private ObservableList<FlashKart> tumKartlar; // Tüm kartların listesi
    private Queue<FlashKart> tekrarSirasi; // Tekrar sırasındaki kartlar
    private FlashKart guncelKart; // Şu an gösterilen kart
    private boolean onYuzGosteriliyor; // Kartın ön yüzünün mü gösterildiği
    private Timer tekrarZamanlayici; // Tekrar zamanlaması için timer

    /**
     * Stage referansını ayarlar
     * @param stage JavaFX stage nesnesi
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Controller başlatıldığında çalışan metod (FXML yüklendiğinde otomatik çağrılır)
     */
    public void initialize() {
        // Veri yapılarını başlat
        tumKartlar = FXCollections.observableArrayList();
        tumKartlar.addAll(varsayilanKartlariOlustur()); // Varsayılan kartları ekle
        tekrarSirasi = new LinkedList<>(); // Tekrar sırasını başlat
        onYuzGosteriliyor = true; // Başlangıçta ön yüz gösterilsin

        // Event listenerları ayarla
        kartContainer.setOnMouseClicked(this::kartTiklama); // Kart tıklama eventi
        kartEkleButton.setOnAction(this::kartEkleButonTiklama); // Kart ekle butonu
        sonrakiKartButton.setOnAction(this::sonrakiKartButonTiklama); // Sonraki kart butonu

        // Tekrar butonlarını ayarla
        birDkButton.setOnAction(event -> {
            tekrarPlanla(1); // 1 dakika sonra tekrar
            sonrakiKartiGoster();
        });

        ucDkButton.setOnAction(event -> {
            tekrarPlanla(3); // 3 dakika sonra tekrar
            sonrakiKartiGoster();
        });

        sekizDkButton.setOnAction(event -> {
            tekrarPlanla(8); // 8 dakika sonra tekrar
            sonrakiKartiGoster();
        });

        onBesDkButton.setOnAction(event -> {
            tekrarPlanla(15); // 15 dakika sonra tekrar
            sonrakiKartiGoster();
        });

        yirmiDortSaatButton.setOnAction(event -> {
            tekrarPlanla(24 * 60); // 24 saat sonra tekrar
            sonrakiKartiGoster();
        });

        // Geri Dön butonu event handler
        if (geriDonButton != null) {
            geriDonButton.setOnAction(this::geriDonButonTiklama);
        }

        // Başlangıçta tekrar butonlarını gizle (kartın arka yüzünde gösterilecek)
        tekrarButtonBox.setVisible(false);

        // Zamanlayıcıyı başlat ve başlangıç durumunu ayarla
        baslangicDurumunuAyarla();
    }

    /**
     * Geri dön butonuna tıklandığında çağrılır (ana menüye döner)
     */
    private void geriDonButonTiklama(ActionEvent event) {
        try {
            // Geçerli pencereyi kapat
            if (stage != null) {
                stage.close();
            }

            // Ana menüyü aç
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/studybuddy/AnaMenu.fxml"));
            Parent root = loader.load();
            Stage anaMenuStage = new Stage();
            anaMenuStage.setScene(new Scene(root));
            anaMenuStage.show();

        } catch (IOException e) {
            e.printStackTrace();
            uyariGoster("Hata", "Ana menüye dönüş sırasında bir hata oluştu!");
        }
    }

    /**
     * Başlangıç durumunu ayarlar (ilk kartı gösterir ve zamanlayıcıyı başlatır)
     */
    private void baslangicDurumunuAyarla() {
        // İlk kartı göster
        kartGoruntusunuGuncelle();
        durumEtiketiniGuncelle();

        // Zamanlayıcıyı başlat (10 saniyede bir kontrol edecek)
        tekrarZamanlayici = new Timer(true);
        tekrarZamanlayici.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    tekrarSirasiniKontrolEt();
                    durumEtiketiniGuncelle();
                });
            }
        }, 0, 10000);
    }

    /**
     * Kart üzerine tıklandığında çağrılır (kartı çevirir)
     */
    private void kartTiklama(MouseEvent event) {
        kartCevir();
    }

    /**
     * Kart ekleme butonuna tıklandığında çağrılır
     */
    private void kartEkleButonTiklama(ActionEvent event) {
        yeniKartEkle();
    }

    /**
     * Sonraki kart butonuna tıklandığında çağrılır
     */
    private void sonrakiKartButonTiklama(ActionEvent event) {
        sonrakiKartiGoster();
    }

    /**
     * Yeni kart ekler (ön yüz ve arka yüz metinlerini alarak)
     */
    private void yeniKartEkle() {
        String onYuz = onYuzTextField.getText().trim();
        String cevapYuzu = cevapYuzuTextField.getText().trim();

        if (!onYuz.isEmpty() && !cevapYuzu.isEmpty()) {
            FlashKart yeniKart = new FlashKart(onYuz, cevapYuzu);
            tumKartlar.add(yeniKart);
            onYuzTextField.clear();
            cevapYuzuTextField.clear();

            // İlk kart eklendiğinde hemen göster
            if (tumKartlar.size() == 1 && guncelKart == null) {
                sonrakiKartiGoster();
            }

            durumEtiketiniGuncelle();
        } else {
            uyariGoster("Uyarı", "Lütfen kartın hem ön hem de arka yüzünü doldurun!");
        }
    }

    /**
     * Kartı çevirir (ön yüz/arka yüz)
     */
    private void kartCevir() {
        if (guncelKart != null) {
            onYuzGosteriliyor = !onYuzGosteriliyor;
            kartGoruntusunuGuncelle();
            tekrarButtonBox.setVisible(!onYuzGosteriliyor); // Arka yüzde tekrar butonlarını göster
        }
    }

    /**
     * Kart görüntüsünü günceller (ön veya arka yüzü gösterir)
     */
    private void kartGoruntusunuGuncelle() {
        if (guncelKart != null) {
            kartIcerikLabel.setText(onYuzGosteriliyor ? guncelKart.getOnYuz() : guncelKart.getCevapYuzu());
        } else {
            kartIcerikLabel.setText("Kart bulunmamaktadır. Lütfen yeni kart ekleyin.");
            tekrarButtonBox.setVisible(false);
        }
    }

    /**
     * Sonraki kartı gösterir
     */
    private void sonrakiKartiGoster() {
        tekrarSirasiniKontrolEt();

        if (!tekrarSirasi.isEmpty()) {
            guncelKart = tekrarSirasi.poll(); // Tekrar zamanı gelmiş kart varsa onu göster
        } else if (!tumKartlar.isEmpty()) {
            // Rastgele bir kart seç
            int rastgeleIndex = new Random().nextInt(tumKartlar.size());
            guncelKart = tumKartlar.get(rastgeleIndex);
        } else {
            guncelKart = null; // Hiç kart yoksa
        }

        onYuzGosteriliyor = true;
        kartGoruntusunuGuncelle();
        tekrarButtonBox.setVisible(false);
        durumEtiketiniGuncelle();
    }

    /**
     * Kartın tekrarını belirtilen dakika sonrasına planlar
     * @param dakika Kaç dakika sonra tekrar edileceği
     */
    private void tekrarPlanla(int dakika) {
        if (guncelKart != null) {
            guncelKart.setTekrarZamani(LocalDateTime.now().plusMinutes(dakika));
            tekrarSirasi.add(guncelKart);
        }
    }

    /**
     * Tekrar sırasındaki kartları kontrol eder (zamanı gelenleri hazır listesine alır)
     */
    private void tekrarSirasiniKontrolEt() {
        List<FlashKart> hazirKartlar = new ArrayList<>();

        Iterator<FlashKart> iterator = tekrarSirasi.iterator();
        while (iterator.hasNext()) {
            FlashKart kart = iterator.next();
            if (kart.tekrarIcinHazirMi()) {
                hazirKartlar.add(kart);
                iterator.remove();
            }
        }

        for (FlashKart kart : hazirKartlar) {
            tekrarSirasi.add(kart);
        }
    }

    /**
     * Durum etiketini günceller (toplam kart, bekleyen tekrar sayısı vb.)
     */
    private void durumEtiketiniGuncelle() {
        int hazir = 0;
        for (FlashKart kart : tekrarSirasi) {
            if (kart.tekrarIcinHazirMi()) {
                hazir++;
            }
        }
        durumLabel.setText(String.format("Toplam: %d | Tekrar Bekleyen: %d | Hazır: %d",
                tumKartlar.size(), tekrarSirasi.size(), hazir));
    }

    /**
     * Varsayılan kartları oluşturur (A1-A2 İngilizce-Türkçe kelimeler)
     */
    private List<FlashKart> varsayilanKartlariOlustur() {
        List<FlashKart> kartlar = new ArrayList<>();

        // Temel kelimeler
        kartlar.add(new FlashKart("apple", "elma"));
        kartlar.add(new FlashKart("book", "kitap"));
        // ... diğer kartlar

        return kartlar;
    }

    /**
     * Uyarı mesajı gösterir
     */
    private void uyariGoster(String baslik, String mesaj) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle(baslik);
            alert.setHeaderText(null);
            alert.setContentText(mesaj);
            alert.showAndWait();
        });
    }

    /**
     * Uygulama kapanırken kaynakları temizler
     */
    public void shutdown() {
        if (tekrarZamanlayici != null) {
            tekrarZamanlayici.cancel();
        }
    }

    /**
     * FlashKart iç sınıfı (kart veri modeli)
     */
    public static class FlashKart {
        private String onYuz;
        private String cevapYuzu;
        private LocalDateTime tekrarZamani;

        public FlashKart(String onYuz, String cevapYuzu) {
            this.onYuz = onYuz;
            this.cevapYuzu = cevapYuzu;
        }

        // Getter ve Setter metodları
        public String getOnYuz() { return onYuz; }
        public String getCevapYuzu() { return cevapYuzu; }
        public LocalDateTime getTekrarZamani() { return tekrarZamani; }

        public void setTekrarZamani(LocalDateTime tekrarZamani) {
            this.tekrarZamani = tekrarZamani;
        }

        /**
         * Kartın tekrar zamanının gelip gelmediğini kontrol eder
         */
        public boolean tekrarIcinHazirMi() {
            return tekrarZamani == null || LocalDateTime.now().isAfter(tekrarZamani);
        }
    }
}