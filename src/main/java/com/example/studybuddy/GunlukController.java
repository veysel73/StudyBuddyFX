package com.example.studybuddy;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class GunlukController {
    private static final String GUNLUK_DOSYA_YOLU = "gunluk_kayittari/gunlukler.txt";
    private List<Gunluk> gunlukler = new ArrayList<>();

    @FXML private Button btnAnaMenu;
    @FXML private Button btnYeniGunluk;
    @FXML private Button btnTarihAra;
    @FXML private ListView<String> listViewGunlukler;
    @FXML private TextArea txtGunlukIcerik;
    @FXML private VBox gunlukListePanel;
    @FXML private VBox gunlukYazmaPanel;
    @FXML private DatePicker datePicker;
    @FXML private DatePicker dateAramaPicker;

    @FXML
    public void initialize() {
        gunlukYazmaPanel.setVisible(false);
        gunlukleriYukle();
        gunlukleriListele();
    }

    @FXML
    private void anaMenuyeDon() {
        gunlukYazmaPanel.setVisible(false);
        gunlukListePanel.setVisible(true);
    }

    @FXML
    private void yeniGunlukEkle() {
        gunlukListePanel.setVisible(false);
        gunlukYazmaPanel.setVisible(true);
        txtGunlukIcerik.clear();
        datePicker.setValue(LocalDate.now());
    }

    @FXML
    private void gunlukKaydet() {
        String icerik = txtGunlukIcerik.getText().trim();
        LocalDate tarih = datePicker.getValue();

        if (icerik.isEmpty() || tarih == null) {
            showAlert("Uyarı", "Lütfen tarih seçin ve günlük içeriği girin!");
            return;
        }

        Gunluk yeniGunluk = new Gunluk(tarih, icerik);
        gunlukler.add(yeniGunluk);
        gunlukleriKaydet();
        anaMenuyeDon();
        gunlukleriListele();
    }

    @FXML
    private void gunlukSil() {
        int selectedIndex = listViewGunlukler.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0 && selectedIndex < gunlukler.size()) {
            gunlukler.remove(selectedIndex);
            gunlukleriKaydet();
            gunlukleriListele();
        } else {
            showAlert("Uyarı", "Lütfen silmek istediğiniz günlüğü seçin!");
        }
    }

    @FXML
    private void gunlukOku() {
        int selectedIndex = listViewGunlukler.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0 && selectedIndex < gunlukler.size()) {
            Gunluk seciliGunluk = gunlukler.get(selectedIndex);
            gunlukListePanel.setVisible(false);
            gunlukYazmaPanel.setVisible(true);
            datePicker.setValue(seciliGunluk.getTarih());
            txtGunlukIcerik.setText(seciliGunluk.getIcerik());
        }
    }

    @FXML
    private void tarihIleAra() {
        LocalDate arananTarih = dateAramaPicker.getValue();
        if (arananTarih == null) {
            showAlert("Uyarı", "Lütfen bir tarih seçin!");
            return;
        }

        for (int i = 0; i < gunlukler.size(); i++) {
            if (gunlukler.get(i).getTarih().equals(arananTarih)) {
                listViewGunlukler.getSelectionModel().select(i);
                listViewGunlukler.scrollTo(i);
                return;
            }
        }
        showAlert("Bilgi", "Bu tarihe ait günlük bulunamadı.");
    }

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
                    reader.readLine(); // Icerik: satırını atla
                    while ((line = reader.readLine()) != null && !line.equals("----------")) {
                        icerik.append(line).append("\n");
                    }
                    if (icerik.length() > 0) {
                        icerik.deleteCharAt(icerik.length() - 1); // Son \n'i sil
                    }
                    gunlukler.add(new Gunluk(tarih, icerik.toString()));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

    private void showAlert(String baslik, String mesaj) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(baslik);
        alert.setHeaderText(null);
        alert.setContentText(mesaj);
        alert.showAndWait();
    }

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