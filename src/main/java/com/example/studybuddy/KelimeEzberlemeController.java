package com.example.studybuddy;




import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.time.LocalDateTime;
import java.util.*;

public class KelimeEzberlemeController {

    // FXML arayüz bileşenleri
    @FXML private Label kartIcerikLabel;
    @FXML private VBox kartContainer;
    @FXML private TextField onYuzTextField;
    @FXML private TextField cevapYuzuTextField;
    @FXML private Button kartEkleButton;
    @FXML private Button sonrakiKartButton;
    @FXML private Label durumLabel;
    @FXML private HBox tekrarButtonBox;
    @FXML private Button birDkButton;
    @FXML private Button ucDkButton;
    @FXML private Button sekizDkButton;
    @FXML private Button onBesDkButton;
    @FXML private Button yirmiDortSaatButton;

    // Veri yapıları
    private ObservableList<FlashKart> tumKartlar;
    private Queue<FlashKart> tekrarSirasi;
    private FlashKart guncelKart;
    private boolean onYuzGosteriliyor;
    private Timer tekrarZamanlayici;

    /**
     * FXML yüklendiğinde otomatik çağrılır
     */
    public void initialize() {
        // Veri yapılarını başlat
        tumKartlar = FXCollections.observableArrayList();
        tumKartlar.addAll(varsayilanKartlariOlustur());
        tekrarSirasi = new LinkedList<>();
        onYuzGosteriliyor = true;

        // Event listenerları ayarla
        kartContainer.setOnMouseClicked(this::kartTiklama);
        kartEkleButton.setOnAction(this::kartEkleButonTiklama);
        sonrakiKartButton.setOnAction(this::sonrakiKartButonTiklama);

        // Tekrar butonlarını ayarla
        birDkButton.setOnAction(event -> {
            tekrarPlanla(1);
            sonrakiKartiGoster();
        });

        ucDkButton.setOnAction(event -> {
            tekrarPlanla(3);
            sonrakiKartiGoster();
        });

        sekizDkButton.setOnAction(event -> {
            tekrarPlanla(8);
            sonrakiKartiGoster();
        });

        onBesDkButton.setOnAction(event -> {
            tekrarPlanla(15);
            sonrakiKartiGoster();
        });

        yirmiDortSaatButton.setOnAction(event -> {
            tekrarPlanla(24 * 60);
            sonrakiKartiGoster();
        });


        // Başlangıçta tekrar butonlarını gizle
        tekrarButtonBox.setVisible(false);

        // Zamanlayıcıyı başlat
        baslangicDurumunuAyarla();
    }

    /**
     * Başlangıç durumunu ayarlar
     */
    private void baslangicDurumunuAyarla() {
        // Güncel kart yoksa başlangıç mesajını göster
        kartGoruntusunuGuncelle();

        // Durum etiketini güncelle
        durumEtiketiniGuncelle();

        // Zamanlayıcıyı başlat
        tekrarZamanlayici = new Timer(true);
        tekrarZamanlayici.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    tekrarSirasiniKontrolEt();
                    durumEtiketiniGuncelle();
                });
            }
        }, 0, 10000); // 10 saniyede bir
    }

    /**
     * Kart üzerine tıklandığında çağrılır
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
     * Yeni kart ekler
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

            // Durum etiketini güncelle
            durumEtiketiniGuncelle();
        }
    }

    /**
     * Kartı çevirir (ön yüz/arka yüz)
     */
    private void kartCevir() {
        if (guncelKart != null) {
            onYuzGosteriliyor = !onYuzGosteriliyor;
            kartGoruntusunuGuncelle();

            // Kart arka yüzündeyken tekrar butonlarını göster
            tekrarButtonBox.setVisible(!onYuzGosteriliyor);
        }
    }

    /**
     * Kart görüntüsünü günceller
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
        // Önce tekrar sırasını kontrol et
        tekrarSirasiniKontrolEt();

        // Tekrar zamanı gelmiş kart varsa onu göster
        if (!tekrarSirasi.isEmpty()) {
            guncelKart = tekrarSirasi.poll();
        }
        // Yoksa ve kartlar varsa rastgele bir kart seç
        else if (!tumKartlar.isEmpty()) {
            int rastgeleIndex = new Random().nextInt(tumKartlar.size());
            guncelKart = tumKartlar.get(rastgeleIndex);
        } else {
            // Hiç kart yoksa
            guncelKart = null;
        }

        // UI'ı güncelle
        onYuzGosteriliyor = true;
        kartGoruntusunuGuncelle();
        tekrarButtonBox.setVisible(false);
        durumEtiketiniGuncelle();
    }

    /**
     * Kartın tekrarını belirtilen dakika sonrasına planlar
     */
    private void tekrarPlanla(int dakika) {
        if (guncelKart != null) {
            // Kartı belirtilen zaman sonrası için planla
            guncelKart.setTekrarZamani(LocalDateTime.now().plusMinutes(dakika));
            tekrarSirasi.add(guncelKart);

            // Sonraki karta geç
            sonrakiKartiGoster();
        }
    }

    /**
     * Tekrar sırasındaki kartları kontrol eder
     */
    private void tekrarSirasiniKontrolEt() {
        // Tekrar zamanı gelmiş kartlar için geçici liste
        List<FlashKart> hazirKartlar = new ArrayList<>();

        // Tekrar sırasını kontrol et
        Iterator<FlashKart> iterator = tekrarSirasi.iterator();
        while (iterator.hasNext()) {
            FlashKart kart = iterator.next();
            if (kart.tekrarIcinHazirMi()) {
                hazirKartlar.add(kart);
                iterator.remove();
            }
        }

        // Hazır kartları yeniden ekle (önce gösterilmek üzere)
        for (FlashKart kart : hazirKartlar) {
            tekrarSirasi.add(kart);
        }
    }

    /**
     * Durum etiketini günceller
     */
    private void durumEtiketiniGuncelle() {
        int hazir = 0;
        for (FlashKart kart : tekrarSirasi) {
            if (kart.tekrarIcinHazirMi()) {
                hazir++;
            }
        }
        durumLabel.setText("Toplam: " + tumKartlar.size() + " | Tekrar Bekleyen: " + tekrarSirasi.size() + " | Hazır: " + hazir);
    }

    /**
     * Varsayılan İngilizce A1-A2 kelimelerinden oluşan listeyi döner
     */
    private List<FlashKart> varsayilanKartlariOlustur() {
        List<FlashKart> varsayilanKartlar = new ArrayList<>();

        varsayilanKartlar.add(new FlashKart("apple", "elma"));
        varsayilanKartlar.add(new FlashKart("book", "kitap"));
        varsayilanKartlar.add(new FlashKart("cat", "kedi"));
        varsayilanKartlar.add(new FlashKart("dog", "köpek"));
        varsayilanKartlar.add(new FlashKart("egg", "yumurta"));
        varsayilanKartlar.add(new FlashKart("fish", "balık"));
        varsayilanKartlar.add(new FlashKart("green", "yeşil"));
        varsayilanKartlar.add(new FlashKart("house", "ev"));
        varsayilanKartlar.add(new FlashKart("ice", "buz"));
        varsayilanKartlar.add(new FlashKart("juice", "meyve suyu"));
        varsayilanKartlar.add(new FlashKart("key", "anahtar"));
        varsayilanKartlar.add(new FlashKart("lemon", "limon"));
        varsayilanKartlar.add(new FlashKart("mother", "anne"));
        varsayilanKartlar.add(new FlashKart("night", "gece"));
        varsayilanKartlar.add(new FlashKart("orange", "portakal"));
        varsayilanKartlar.add(new FlashKart("pen", "kalem"));
        varsayilanKartlar.add(new FlashKart("queen", "kraliçe"));
        varsayilanKartlar.add(new FlashKart("rain", "yağmur"));
        varsayilanKartlar.add(new FlashKart("sun", "güneş"));
        varsayilanKartlar.add(new FlashKart("tree", "ağaç"));
        varsayilanKartlar.add(new FlashKart("umbrella", "şemsiye"));
        varsayilanKartlar.add(new FlashKart("village", "köy"));
        varsayilanKartlar.add(new FlashKart("water", "su"));
        varsayilanKartlar.add(new FlashKart("x-ray", "röntgen"));
        varsayilanKartlar.add(new FlashKart("yellow", "sarı"));
        varsayilanKartlar.add(new FlashKart("zoo", "hayvanat bahçesi"));
        varsayilanKartlar.add(new FlashKart("ant", "karınca"));
        varsayilanKartlar.add(new FlashKart("ball", "top"));
        varsayilanKartlar.add(new FlashKart("car", "araba"));
        varsayilanKartlar.add(new FlashKart("door", "kapı"));
        varsayilanKartlar.add(new FlashKart("ear", "kulak"));
        varsayilanKartlar.add(new FlashKart("face", "yüz"));
        varsayilanKartlar.add(new FlashKart("girl", "kız"));
        varsayilanKartlar.add(new FlashKart("hat", "şapka"));
        varsayilanKartlar.add(new FlashKart("island", "ada"));
        varsayilanKartlar.add(new FlashKart("jacket", "ceket"));
        varsayilanKartlar.add(new FlashKart("kite", "uçurtma"));
        varsayilanKartlar.add(new FlashKart("lamp", "lamba"));
        varsayilanKartlar.add(new FlashKart("milk", "süt"));
        varsayilanKartlar.add(new FlashKart("nose", "burun"));
        varsayilanKartlar.add(new FlashKart("octopus", "ahtapot"));
        varsayilanKartlar.add(new FlashKart("pencil", "kurşun kalem"));
        varsayilanKartlar.add(new FlashKart("rabbit", "tavşan"));
        varsayilanKartlar.add(new FlashKart("snake", "yılan"));
        varsayilanKartlar.add(new FlashKart("tiger", "kaplan"));
        varsayilanKartlar.add(new FlashKart("uncle", "amca/dayı"));
        varsayilanKartlar.add(new FlashKart("violin", "keman"));
        varsayilanKartlar.add(new FlashKart("window", "pencere"));
        varsayilanKartlar.add(new FlashKart("yard", "bahçe"));
        varsayilanKartlar.add(new FlashKart("zero", "sıfır"));
        varsayilanKartlar.add(new FlashKart("always", "her zaman"));
        varsayilanKartlar.add(new FlashKart("bad", "kötü"));
        varsayilanKartlar.add(new FlashKart("clean", "temiz"));
        varsayilanKartlar.add(new FlashKart("dance", "dans etmek"));
        varsayilanKartlar.add(new FlashKart("easy", "kolay"));
        varsayilanKartlar.add(new FlashKart("fast", "hızlı"));
        varsayilanKartlar.add(new FlashKart("good", "iyi"));
        varsayilanKartlar.add(new FlashKart("happy", "mutlu"));
        varsayilanKartlar.add(new FlashKart("important", "önemli"));
        varsayilanKartlar.add(new FlashKart("jump", "zıplamak"));
        varsayilanKartlar.add(new FlashKart("kind", "nazik"));
        varsayilanKartlar.add(new FlashKart("long", "uzun"));
        varsayilanKartlar.add(new FlashKart("morning", "sabah"));
        varsayilanKartlar.add(new FlashKart("new", "yeni"));
        varsayilanKartlar.add(new FlashKart("old", "eski"));
        varsayilanKartlar.add(new FlashKart("pretty", "güzel"));
        varsayilanKartlar.add(new FlashKart("quick", "çabuk"));
        varsayilanKartlar.add(new FlashKart("read", "okumak"));
        varsayilanKartlar.add(new FlashKart("short", "kısa"));
        varsayilanKartlar.add(new FlashKart("talk", "konuşmak"));
        varsayilanKartlar.add(new FlashKart("under", "altında"));
        varsayilanKartlar.add(new FlashKart("very", "çok"));
        varsayilanKartlar.add(new FlashKart("walk", "yürümek"));
        varsayilanKartlar.add(new FlashKart("young", "genç"));
        varsayilanKartlar.add(new FlashKart("zebra", "zebra"));
        varsayilanKartlar.add(new FlashKart("airplane", "uçak"));
        varsayilanKartlar.add(new FlashKart("bread", "ekmek"));
        varsayilanKartlar.add(new FlashKart("chair", "sandalye"));
        varsayilanKartlar.add(new FlashKart("doctor", "doktor"));
        varsayilanKartlar.add(new FlashKart("engine", "motor"));
        varsayilanKartlar.add(new FlashKart("flower", "çiçek"));
        varsayilanKartlar.add(new FlashKart("game", "oyun"));
        varsayilanKartlar.add(new FlashKart("hand", "el"));
        varsayilanKartlar.add(new FlashKart("ink", "mürekkep"));
        varsayilanKartlar.add(new FlashKart("job", "iş"));
        varsayilanKartlar.add(new FlashKart("king", "kral"));
        varsayilanKartlar.add(new FlashKart("lion", "aslan"));
        varsayilanKartlar.add(new FlashKart("moon", "ay"));
        varsayilanKartlar.add(new FlashKart("nest", "yuva"));
        varsayilanKartlar.add(new FlashKart("open", "açık"));
        varsayilanKartlar.add(new FlashKart("park", "park"));
        varsayilanKartlar.add(new FlashKart("quiet", "sessiz"));
        varsayilanKartlar.add(new FlashKart("road", "yol"));
        varsayilanKartlar.add(new FlashKart("sea", "deniz"));
        varsayilanKartlar.add(new FlashKart("train", "tren"));
        varsayilanKartlar.add(new FlashKart("use", "kullanmak"));
        varsayilanKartlar.add(new FlashKart("voice", "ses"));
        varsayilanKartlar.add(new FlashKart("watch", "izlemek/saat"));
        varsayilanKartlar.add(new FlashKart("extra", "ekstra"));

        // ... toplamda 100 tane olacak şekilde buraya devam edebilirsin

        return varsayilanKartlar;
    }


    /**
     * Uygulama kapanırken kaynakları temizler
     */
    public void shutdown() {
        if (tekrarZamanlayici != null) {
            tekrarZamanlayici.cancel();
        }
    }
}