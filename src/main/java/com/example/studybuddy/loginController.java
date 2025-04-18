package com.example.studybuddy;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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
            lblDurum.setText("✅ Giriş başarılı!");
        } else {
            lblDurum.setText("❌ Hatalı kullanıcı adı veya şifre.");
        }
    }

}
