package com.example.studybuddy;


import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

public class NotHesaplayiciController {

    @FXML private TextField vizeYuzdeInput;
    @FXML private TextField finalYuzdeInput;
    @FXML private TextField odevYuzdeInput;

    @FXML private TextField vizeInput;
    @FXML private TextField finalInput;
    @FXML private TextField butunlemeInput;
    @FXML private TextField odevInput;

    @FXML private Label sonucLabel;
    @FXML private Label hataLabel;
    @FXML private Label bilgiLabel;


    @FXML
    private void hesapla() {
        try {
            double vizeNot = Double.parseDouble(vizeInput.getText());
            double finalNot = Double.parseDouble(finalInput.getText());
            double butunlemeNot = 0;
            double odevNot = 0;

            double vizeYuzde = Double.parseDouble(vizeYuzdeInput.getText());
            double finalYuzde = Double.parseDouble(finalYuzdeInput.getText());
            double odevYuzde = Double.parseDouble(odevYuzdeInput.getText());

            // Eğer ödev notu boş değilse al
            if (!odevInput.getText().isEmpty()) {
                odevNot = Double.parseDouble(odevInput.getText());
            }

            double finalGecerlNot;

            // Final notu 50'nin altında ise bütünleme girilmeli
            if (finalNot < 50) {
                if (butunlemeInput.getText().isEmpty()) {
                    hataLabel.setText("Final 50'nin altında, lütfen bütünleme notunu giriniz.");
                    return;
                } else {
                    butunlemeInput.setDisable(false); // açık hale getir
                    finalGecerlNot = Double.parseDouble(butunlemeInput.getText());
                }
            } else {
                finalGecerlNot = finalNot;
                butunlemeInput.setDisable(true); // gizle veya etkisizleştir
            }

            double toplamYuzde = vizeYuzde + finalYuzde + odevYuzde;

            if (toplamYuzde != 100) {
                hataLabel.setText("Yüzdelerin toplamı 100 olmalıdır.");
                return;
            }

            double ortalama = (vizeNot * vizeYuzde / 100) +
                    (finalGecerlNot * finalYuzde / 100) +
                    (odevNot * odevYuzde / 100);

            String harfNotu;
            if (ortalama >= 90) harfNotu = "AA";
            else if (ortalama >= 85) harfNotu = "BA";
            else if (ortalama >= 80) harfNotu = "BB";
            else if (ortalama >= 75) harfNotu = "CB";
            else if (ortalama >= 70) harfNotu = "CC";
            else if (ortalama >= 65) harfNotu = "DC";
            else if (ortalama >= 60) harfNotu = "DD";
            else if (ortalama >= 50) harfNotu = "FD";
            else harfNotu = "FF";

            sonucLabel.setText(String.format("Ortalama: %.2f - Harf Notu: %s", ortalama, harfNotu));
            hataLabel.setText("");
            bilgiLabel.setText(finalNot < 50 ? "Bütünleme notu ile hesaplandı." : "Final notu ile hesaplandı.");
        } catch (NumberFormatException e) {
            hataLabel.setText("Lütfen tüm geçerli sayısal alanları doldurun.");
        }
    }

}
