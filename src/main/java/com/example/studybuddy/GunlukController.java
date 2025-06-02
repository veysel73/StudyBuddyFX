package com.example.studybuddy;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class GunlukController {
    // Sabitler
    private static final String GUNLUK_DOSYA_YOLU = "gunluk_kayittari/gunlukler.txt";
    private List<Gunluk> gunlukler = new ArrayList<>();

    // FXML ile bağlantılı bileşenler
    @FXML private Button btnAnaMenu;
    @FXML private Button btnGeriDon; // Yeni eklenen geri dön butonu
    @FXML private Button btnYeniGunluk;
    @FXML private Button btnTarihAra;
    @FXML private ListView<String> listViewGunlukler;
    @FXML private TextArea txtGunlukIcerik;
    @FXML private VBox gunlukListePanel;
    @FXML private VBox gunlukYazmaPanel;
    @FXML private DatePicker datePicker;
    @FXML private DatePicker dateAramaPicker;

    /**
     * Controller başlatıldığında çalışan metod
     */
    @FXML
    public void initialize() {
        gunlukYazmaPanel.setVisible(false); // Yazma panelini başlangıçta gizle
        btnGeriDon.setVisible(false); // Geri dön butonunu başlangıçta gizle
        gunlukleriYukle(); // Kayıtlı günlükleri yükle
        gunlukleriListele(); // Günlükleri listeye ekle
    }

    /**
     * Ana menüye dön butonu işlevi
     */
    @FXML
    private void anaMenuyeDon(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/studybuddy/Menu.fxml"));

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setFullScreen(true);  // Tam ekran yapar
            stage.setMaximized(true);  // Maksimize eder (opsiyonel)
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    /**
     * Geri dön butonu işlevi (yazma panelinden liste paneline döner)
     */
    @FXML
    private void geriDon() {
        gunlukYazmaPanel.setVisible(false);
        gunlukListePanel.setVisible(true);
        btnGeriDon.setVisible(false);
    }

    /**
     * Yeni günlük ekleme butonu işlevi
     */
    @FXML
    private void yeniGunlukEkle() {
        gunlukListePanel.setVisible(false);
        gunlukYazmaPanel.setVisible(true);
        btnGeriDon.setVisible(true);
        txtGunlukIcerik.clear();
        datePicker.setValue(LocalDate.now());
    }

    /**
     * Günlük kaydetme butonu işlevi
     */
    @FXML
    private void gunlukKaydet() {
        String icerik = txtGunlukIcerik.getText().trim();
        LocalDate tarih = datePicker.getValue();

        if (icerik.isEmpty() || tarih == null) {
            uyariGoster("Uyarı", "Lütfen tarih seçin ve günlük içeriği girin!");
            return;
        }

        Gunluk yeniGunluk = new Gunluk(tarih, icerik);
        gunlukler.add(yeniGunluk);
        gunlukleriKaydet();
        geriDon();
        gunlukleriListele();
    }

    /**
     * Günlük silme butonu işlevi
     */
    @FXML
    private void gunlukSil() {
        int seciliIndex = listViewGunlukler.getSelectionModel().getSelectedIndex();
        if (seciliIndex >= 0 && seciliIndex < gunlukler.size()) {
            gunlukler.remove(seciliIndex);
            gunlukleriKaydet();
            gunlukleriListele();
        } else {
            uyariGoster("Uyarı", "Lütfen silmek istediğiniz günlüğü seçin!");
        }
    }

    /**
     * Günlük okuma işlevi (listeden seçilen günlüğü açar)
     */
    @FXML
    private void gunlukOku() {
        int seciliIndex = listViewGunlukler.getSelectionModel().getSelectedIndex();
        if (seciliIndex >= 0 && seciliIndex < gunlukler.size()) {
            Gunluk seciliGunluk = gunlukler.get(seciliIndex);
            gunlukListePanel.setVisible(false);
            gunlukYazmaPanel.setVisible(true);
            btnGeriDon.setVisible(true);
            datePicker.setValue(seciliGunluk.getTarih());
            txtGunlukIcerik.setText(seciliGunluk.getIcerik());
        }
    }

    /**
     * Tarihe göre arama butonu işlevi
     */
    @FXML
    private void tarihIleAra() {
        LocalDate arananTarih = dateAramaPicker.getValue();
        if (arananTarih == null) {
            uyariGoster("Uyarı", "Lütfen bir tarih seçin!");
            return;
        }

        for (int i = 0; i < gunlukler.size(); i++) {
            if (gunlukler.get(i).getTarih().equals(arananTarih)) {
                listViewGunlukler.getSelectionModel().select(i);
                listViewGunlukler.scrollTo(i);
                return;
            }
        }
        uyariGoster("Bilgi", "Bu tarihe ait günlük bulunamadı.");
    }

    /**
     * Günlükleri listeye ekler (tarih ve ilk 30 karakterle özet gösterir)
     */
    private void gunlukleriListele() {
        listViewGunlukler.getItems().clear();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        for (Gunluk gunluk : gunlukler) {
            String ozet = gunluk.getTarih().format(formatter) + " - " +
                    (gunluk.getIcerik().length() > 30 ?
                            gunluk.getIcerik().substring(0, 30) + "..." : gunluk.getIcerik());
            listViewGunlukler.getItems().add(ozet);
        }
    }

    /**
     * Dosyadan günlükleri yükler
     */
    private void gunlukleriYukle() {
        File dosya = new File(GUNLUK_DOSYA_YOLU);
        if (!dosya.exists()) {
            try {
                dosya.getParentFile().mkdirs();
                dosya.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(dosya))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Tarih:")) {
                    LocalDate tarih = LocalDate.parse(line.substring(7));
                    StringBuilder icerik = new StringBuilder();
                    reader.readLine(); // "Icerik:" satırını atla
                    while ((line = reader.readLine()) != null && !line.equals("----------")) {
                        icerik.append(line).append("\n");
                    }
                    if (icerik.length() > 0) {
                        icerik.deleteCharAt(icerik.length() - 1); // Son \n karakterini sil
                    }
                    gunlukler.add(new Gunluk(tarih, icerik.toString()));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Günlükleri dosyaya kaydeder
     */
    private void gunlukleriKaydet() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(GUNLUK_DOSYA_YOLU))) {
            for (Gunluk gunluk : gunlukler) {
                writer.println("Tarih: " + gunluk.getTarih());
                writer.println("Icerik:");
                writer.println(gunluk.getIcerik());
                writer.println("----------");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Uyarı mesajı gösterir
     */
    private void uyariGoster(String baslik, String mesaj) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(baslik);
        alert.setHeaderText(null);
        alert.setContentText(mesaj);
        alert.showAndWait();
    }

    /**
     * Günlük veri modeli (iç sınıf)
     */
    private static class Gunluk {
        private LocalDate tarih;
        private String icerik;

        public Gunluk(LocalDate tarih, String icerik) {
            this.tarih = tarih;
            this.icerik = icerik;
        }

        public LocalDate getTarih() {
            return tarih;
        }

        public String getIcerik() {
            return icerik;
        }
    }
}