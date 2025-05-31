package com.example.studybuddy;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;
import javafx.event.ActionEvent;

public class AlanSecimController {

    // FXML ile bağlantılı bileşenler
    @FXML private Button backButton; // İçerik sayfasındaki geri düğmesi
    @FXML private Button mainBackButton; // Ana sayfadaki geri düğmesi
    @FXML private Label titleLabel; // Başlık etiketi
    @FXML private VBox mainBox; // Ana düzen kutusu
    @FXML private VBox buttonBox; // Butonların bulunduğu kutu
    @FXML private Button webButton; // Web geliştirme butonu
    @FXML private Button aiButton; // Yapay zeka butonu
    @FXML private Button cyberButton; // Siber güvenlik butonu
    @FXML private Button gameButton; // Oyun geliştirme butonu
    @FXML private Button linkedinButton; // LinkedIn butonu
    @FXML private Button exitButton; // Çıkış butonu
    @FXML private ScrollPane scrollPane; // Kaydırma paneli
    @FXML private VBox contentBox; // İçerik kutusu

    private String currentContentType = ""; // Şu an gösterilen içerik türü

    /**
     * Controller başlatıldığında çalışan metod
     */
    @FXML
    private void initialize() {
        // ScrollPane ayarları
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Yatay kaydırmayı kapat
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED); // Dikey kaydırmayı aç

        // Başlangıçta geri düğmelerini ayarla
        backButton.setVisible(false); // İçerik geri düğmesini gizle
        mainBackButton.setVisible(true); // Ana menü geri düğmesini göster
    }

    /**
     * Ana menüdeki geri düğmesi işlevi
     */
    @FXML
    private void handleMainBack(ActionEvent event) {
        // Önceki ekrana dönmek için stage'i kapat
        Stage stage = (Stage) mainBackButton.getScene().getWindow();
        stage.close();
    }

    /**
     * İçerik sayfasındaki geri düğmesi işlevi
     */
    @FXML
    private void handleBack(ActionEvent event) {
        // Buton kutusunu göster
        buttonBox.setVisible(true);
        buttonBox.setManaged(true);

        // ScrollPane'i gizle
        scrollPane.setVisible(false);
        scrollPane.setManaged(false);

        // Geri düğmelerini ayarla
        backButton.setVisible(false);
        mainBackButton.setVisible(true);

        // Geçerli içerik türünü sıfırla
        currentContentType = "";

        // Başlığı güncelle
        titleLabel.setText("🎯 Kariyer Geliştirme Yolu");
    }

    @FXML
    private void handleLinkedIn(ActionEvent event) {

        if (!currentContentType.equals("linkedin")) {
            String linkedinContent = "🌟 LinkedIn Profilini Uçuracak Detaylı Rehber\n\n"
                    + "🧠 1. Profil Fotoğrafı: İlk İzlenim Her Şeydir\n"
                    + "• Net ve yüksek çözünürlüklü olmalı\n"
                    + "• Arka plan sade, yüzün net görünmeli\n"
                    + "• Gülümseyen ama profesyonel bir ifade\n"
                    + "• Giyimin sektörünle uyumlu olsun (örnek: takım elbise, smart casual)\n"
                    + "📸 Bonus: PhotoFeeler gibi sitelerden geribildirim al\n\n"

                    + "🖼️ 2. Arka Plan Fotoğrafı (Banner)\n"
                    + "• Seni yansıtan bir görsel seç\n"
                    + "• Örnekler: Kod satırları, grafikler, mühendislik şemaları, ilham veren sözler\n\n"

                    + "✍️ 3. İsim ve Başlık (Headline)\n"
                    + "⚠️ Sadece \"Öğrenci\", \"Mühendis\" yazma!\n"
                    + "🔹 Örnek Başlık:\n"
                    + "\"🎓 Bilgisayar Mühendisliği Öğrencisi | 🌐 Web Geliştirici Adayı | 💡 Yapay Zekâ Meraklısı | 🚀 Öğrenmeye Açık\"\n\n"

                    + "📖 4. Hakkında (About / Summary)\n"
                    + "Bu alan seni tanıtan bir mini hikâyedir. Şablon:\n"
                    + "🙋‍♂️ Ben Kimim?\n"
                    + "[Eğitim durumun] ve [uzmanlık alanların]. [Tutkuların] ve [öğrenme yaklaşımın].\n\n"
                    + "🛠️ Yeteneklerim\n"
                    + "[Teknik becerilerin] (HTML, CSS, JavaScript, Java, Python gibi)\n\n"
                    + "🎯 Hedefim\n"
                    + "[Kariyer hedeflerin] ve [neyi başarmak istediğin].\n\n"
                    + "🤝 İletişime Geç!\n"
                    + "Yeni fikirler, staj veya proje önerileri için ulaşın!\n\n"

                    + "💼 5. Deneyim (Experience)\n"
                    + "• Freelance işler, gönüllü projeler, stajlar\n"
                    + "• Her iş için:\n"
                    + "  - Ne yaptın?\n"
                    + "  - Hangi teknolojileri kullandın?\n"
                    + "  - Ne öğrendin?\n\n"

                    + "🎓 6. Eğitim (Education)\n"
                    + "• Okul ve bölüm bilgisi\n"
                    + "• Not ortalaması (iyiyse)\n"
                    + "• Kulüp etkinlikleri veya proje detayları\n\n"

                    + "📜 7. Sertifikalar ve Kurslar\n"
                    + "• LinkedIn Learning, Udemy, Coursera, Google sertifikaları\n"
                    + "🔹 Örnekler:\n"
                    + "- Google UX Design Certificate\n"
                    + "- IBM AI Foundations\n"
                    + "- Java Programming – Coursera\n"
                    + "- FreeCodeCamp Full Stack Web Development\n\n"

                    + "💬 8. Yetenekler (Skills)\n"
                    + "• En çok bildiklerinden başla (max 50)\n"
                    + "• Öne çıkan 3 yetenek seç (Örnek: Java, HTML/CSS, React)\n\n"

                    + "👍 9. Tavsiye (Recommendations)\n"
                    + "• Eski iş arkadaşlarından, hocalarından tavsiye iste\n"
                    + "• Karşılıklı tavsiye yaz\n\n"

                    + "📁 10. Projeler & Bağlantılar\n"
                    + "• GitHub profili bağlantısı\n"
                    + "• Web sitesi/portfolio varsa ekle\n"
                    + "• Proje formatı:\n"
                    + "  - Proje ismi\n"
                    + "  - Kısa açıklama\n"
                    + "  - Kullanılan teknolojiler\n"
                    + "  - GitHub linki\n\n"

                    + "📢 11. Etkinlik ve Paylaşım\n"
                    + "• Haftada 1 sektörel içerik paylaş\n"
                    + "• Örnek: \"Spring Boot ile REST API Oluşturmak\"\n"
                    + "• Beğeni, yorum ve network kur\n\n"

                    + "🧑‍🤝‍🧑 12. Ağını Genişlet!\n"
                    + "• Tanıdıkları, sınıf arkadaşlarını ekle\n"
                    + "• Profesyonellere özel mesajla bağlantı isteği gönder:\n"
                    + "\"Merhaba, [sektör] alanında ilerliyorum. Paylaşımlarınızı takip ediyorum. Ağımda olmanızdan memnuniyet duyarım.\"\n\n"

                    + "🚀 BONUS: Profil İpuçları\n"
                    + "┌───────────────────┬─────────────────────────────────────────────┐\n"
                    + "│ ✨ URL Özelleştir  │ linkedin.com/in/adsoyad şeklinde sadeleştir │\n"
                    + "│ 🌐 İngilizce Ekle │ Global fırsatlar için İngilizce profil oluştur │\n"
                    + "│ 📍 Konum Ekle     │ Şehir/ülke bilgisi ekle                     │\n"
                    + "│ 🔎 SEO Optimize   │ \"Frontend Developer, Python\" gibi anahtar kelimeler kullan │\n"
                    + "└───────────────────┴─────────────────────────────────────────────┘";

            showFormattedContent("🔗 LinkedIn Profil Oluşturma", linkedinContent);
            currentContentType = "linkedin";
        }
        showContentArea();
    }

    @FXML
    private void handleWebDev(ActionEvent event) {
        if (!currentContentType.equals("web")) {
            String content = "🌐 Web Geliştirme\n\n"
                    + "🛠 Öğrenmen Gerekenler:\n"
                    + "- HTML: Web sayfalarının temel iskeleti.\n"
                    + "- CSS: Sayfaların görünümünü güzelleştirmek.\n"
                    + "- JavaScript: Web sayfalarına dinamiklik katmak.\n"
                    + "- Backend: \n"
                    + "  - Node.js: JavaScript ile hızlı sunucu uygulamaları geliştirme.\n"
                    + "  - Django: Python tabanlı web uygulamaları için güçlü bir framework.\n"
                    + "  - Spring Boot: Java ile profesyonel backend sistemleri kurma.\n"
                    + "- Veritabanı Yönetimi:\n"
                    + "  - MySQL: Tablolu veri yapısı ile geleneksel veritabanı.\n"
                    + "  - MongoDB: Esnek ve hızlı NoSQL veritabanı.\n"
                    + "\n"
                    + "🧰 Kullanılacak Araçlar:\n"
                    + "- Visual Studio Code: Gelişmiş ve hafif bir kod editörü.\n"
                    + "- Git & GitHub: Sürüm kontrolü ve proje yönetimi.\n"
                    + "- Postman: API testleri için önemli bir araç.\n"
                    + "- Docker: Uygulamaları kapsüllenip taşınabilir hale getirme.\n"
                    + "- Webpack/Vite: Modern frontend geliştirme araçları.\n"
                    + "- Chrome DevTools: Web uygulamalarını debug etmek ve optimize etmek.\n"
                    + "\n"
                    + "📜 Sertifikalar:\n"
                    + "- FreeCodeCamp Web Development: Pratik ve uygulamalı eğitim.\n"
                    + "- Google Professional Web Developer: Profesyonel web geliştirici yetkinlik belgesi.\n"
                    + "- AWS Certified Developer: Cloud tabanlı web uygulamaları geliştirme.\n"
                    + "- Microsoft Certified: Azure Developer Associate.\n"
                    + "\n"
                    + "📌 Bilgi:\n"
                    + "Web geliştirme; hem estetik tasarım hem de güçlü işlevsellik oluşturmayı kapsar.\n"
                    + "Frontend ve backend becerilerini iyi bir şekilde geliştirmek çok önemlidir.\n"
                    + "Freelance çalışmadan büyük firmalara kadar geniş kariyer fırsatları sunar.\n"
                    + "Responsive tasarım, PWA ve web güvenliği konularında uzmanlaşmak fark yaratır.\n"
                    + "JavaScript framework'leri (React, Angular, Vue) sektörde yüksek talep görüyor.";

            showFormattedContent("🌐 Web Geliştirme", content);
            currentContentType = "web";
        }
        showContentArea();
    }

    @FXML
    private void handleAI(ActionEvent event) {
        if (!currentContentType.equals("ai")) {
            String content = "🤖 Yapay Zekâ\n\n"
                    + "🛠 Öğrenmen Gerekenler:\n"
                    + "- Python: Yapay zeka ve veri bilimi için temel programlama dili.\n"
                    + "- Veri Bilimi: Verileri analiz edip anlamlı sonuçlar çıkarma.\n"
                    + "- Makine Öğrenmesi: Verilerden öğrenen modeller kurmak.\n"
                    + "- Derin Öğrenme:\n"
                    + "  - TensorFlow: Büyük ölçekli yapay zeka projeleri için kütüphane.\n"
                    + "  - PyTorch: Deneysel ve esnek AI geliştirme kütüphanesi.\n"
                    + "- Büyük Dil Modelleri:\n"
                    + "  - LLM'lerin çalışma prensipleri ve prompt engineering.\n"
                    + "  - Fine-tuning ve model optimizasyonu.\n"
                    + "\n"
                    + "🧰 Kullanılacak Araçlar:\n"
                    + "- Jupyter Notebook: Hızlı ve interaktif kodlama ortamı.\n"
                    + "- Google Colab: Ücretsiz GPU ile model eğitimi yapabileceğin bulut platformu.\n"
                    + "- VSCode & Anaconda: Projeleri düzenlemek ve ortamları yönetmek.\n"
                    + "- Hugging Face: Model paylaşımı ve kullanımı için platform.\n"
                    + "- MLflow: Model yönetimi ve takibi için araç.\n"
                    + "- CUDA: NVIDIA GPU'larında hızlı model eğitimi.\n"
                    + "\n"
                    + "📜 Sertifikalar:\n"
                    + "- Coursera AI Specialization: Stanford, deeplearning.ai gibi kuruluşlardan dersler.\n"
                    + "- IBM AI Professional Certificate: Yapay zeka alanında ileri seviye yetkinlik sertifikası.\n"
                    + "- Microsoft Certified: Azure AI Engineer Associate.\n"
                    + "- TensorFlow Developer Certificate: Google'ın resmi TensorFlow sertifikası.\n"
                    + "- NVIDIA Deep Learning Institute Sertifikaları.\n"
                    + "\n"
                    + "📌 Bilgi:\n"
                    + "Yapay zeka, makinelerin insan gibi öğrenmesini ve karar vermesini sağlar.\n"
                    + "Geleceğin mesleklerinin merkezinde yer almaktadır.\n"
                    + "Model geliştirme, veri analizi ve yapay sinir ağları gibi konulara hâkim olmak gerekir.\n"
                    + "Etik AI ilkeleri ve sorumlu yapay zeka geliştirme giderek önem kazanıyor.\n"
                    + "Sektördeki AI uzmanları, problem çözme ve matematiksel düşünme yeteneklerini güçlü tutmalıdır.\n"
                    + "Büyük veri setleriyle çalışma ve model eğitim süreçlerini iyileştirme becerileri kritiktir.";

            showFormattedContent("🤖 Yapay Zekâ", content);
            currentContentType = "ai";
        }
        showContentArea();
    }

    @FXML
    private void handleCyberSecurity(ActionEvent event) {
        if (!currentContentType.equals("cyber")) {
            String content = "🛡️ Siber Güvenlik\n\n"
                    + "🛠 Öğrenmen Gerekenler:\n"
                    + "- Ağ Güvenliği: Bilgi akışını koruma.\n"
                    + "- Şifreleme Yöntemleri: Verileri güvenli hale getirmek.\n"
                    + "- Penetrasyon Testleri: Zayıf noktaları bulmak için saldırı simülasyonları.\n"
                    + "- Güvenlik Duvarı Yönetimi: İzinsiz erişimi önleme.\n"
                    + "- Malware Analizi: Zararlı yazılımları tespit etme ve etkisiz hale getirme.\n"
                    + "- SIEM (Security Information and Event Management): Güvenlik olaylarını yönetme ve analiz etme.\n"
                    + "- Bulut Güvenliği: AWS, Azure ve GCP güvenlik protokolleri.\n"
                    + "\n"
                    + "🧰 Kullanılacak Araçlar:\n"
                    + "- Wireshark: Ağ trafiğini izlemek ve analiz etmek.\n"
                    + "- Kali Linux: Sızma testi araçlarını içeren özel Linux dağıtımı.\n"
                    + "- Metasploit: Güvenlik açıklarını test etmek için kullanılan framework.\n"
                    + "- Burp Suite: Web uygulamaları için güvenlik testi aracı.\n"
                    + "- Nmap: Ağ keşfi ve güvenlik denetimi için tarama aracı.\n"
                    + "- Splunk: Güvenlik bilgileri ve olay yönetimi için platform.\n"
                    + "- OWASP ZAP: Açık kaynaklı web uygulama güvenliği test aracı.\n"
                    + "\n"
                    + "📜 Sertifikalar:\n"
                    + "- CompTIA Security+: Temel güvenlik bilgilerini ölçen uluslararası sertifika.\n"
                    + "- CEH (Certified Ethical Hacker): Etik hackerlık ve saldırı öncesi risk analizi eğitimi.\n"
                    + "- CISSP: Bilgi güvenliği profesyonelleri için ileri düzey sertifika.\n"
                    + "- OSCP: Pratik sızma testi becerileri için zorlu bir sertifika.\n"
                    + "- Certified Cloud Security Professional (CCSP): Bulut güvenliği uzmanlığı.\n"
                    + "\n"
                    + "📌 Bilgi:\n"
                    + "Siber güvenlik, verileri ve sistemleri koruma sanatıdır.\n"
                    + "Etik hackerlık, sistem analizi ve güvenlik protokolleri öğrenilmelidir.\n"
                    + "Giderek büyüyen dijital tehditlere karşı kritik bir alandır.\n"
                    + "Sürekli kendini geliştirmek ve güncel tehdit vektörlerini takip etmek çok önemlidir.\n"
                    + "Siber tehditler sürekli evrildiği için, yaşam boyu öğrenme bu alanda şarttır.\n"
                    + "Defansif ve ofansif güvenlik yaklaşımlarını birlikte uygulayabilmek büyük avantaj sağlar.";

            showFormattedContent("🛡️ Siber Güvenlik", content);
            currentContentType = "cyber";
        }
        showContentArea();
    }

    @FXML
    private void handleGameDev(ActionEvent event) {
        if (!currentContentType.equals("game")) {
            String content = "🎮 Oyun Geliştirme\n\n"
                    + "🛠 Öğrenmen Gerekenler:\n"
                    + "- C# Programlama (Unity): Oyun hareketleri ve kontrolleri için.\n"
                    + "- C++ Programlama (Unreal Engine): Gerçekçi ve büyük ölçekli oyunlar için.\n"
                    + "- Oyun Fiziği: Çarpışma, hız ve yer çekimi gibi fizik motorlarını kullanmak.\n"
                    + "- Oyunlarda Yapay Zeka: Düşman karakterlerin hareketleri ve karar mekanizmalarını oluşturmak.\n"
                    + "- Grafik Programlama: Shader yazımı ve render pipeline yönetimi.\n"
                    + "- Ses Tasarımı: Oyun içi ses efektleri ve müzik entegrasyonu.\n"
                    + "- Oyun Ekonomisi ve Monetizasyon: In-app purchase ve oyun içi ekonomi tasarımı.\n"
                    + "\n"
                    + "🧰 Kullanılacak Araçlar:\n"
                    + "- Unity: 2D ve 3D oyunlar geliştirmek için popüler bir motor.\n"
                    + "- Unreal Engine: AAA kalitede grafikler ve geniş proje yönetimi için güçlü bir motor.\n"
                    + "- Blender: 3D modelleme ve animasyon oluşturmak için açık kaynak bir yazılım.\n"
                    + "- Godot: Açık kaynaklı, hafif ve güçlü oyun motoru.\n"
                    + "- Substance Painter: 3D model dokulandırma için endüstri standardı.\n"
                    + "- FMOD/Wwise: Profesyonel oyun ses sistemleri için araçlar.\n"
                    + "- GitHub/Perforce: Oyun projeleri için versiyon kontrol sistemleri.\n"
                    + "\n"
                    + "📜 Sertifikalar:\n"
                    + "- Unity Certified Developer: Unity'de profesyonel uygulamalar geliştirdiğini belgeleyen sertifika.\n"
                    + "- Unreal Engine Training: Epic Games tarafından verilen resmi eğitimler.\n"
                    + "- NVIDIA Game Development Certification: Yüksek performanslı grafik programlama.\n"
                    + "- Unity Certified Expert - Gameplay Programmer: İleri seviye oyun mekanikleri geliştirme.\n"
                    + "\n"
                    + "📌 Bilgi:\n"
                    + "Oyun geliştirme; kodlama, sanat ve hikâye anlatımı yeteneklerinin birleşimidir.\n"
                    + "Mobil oyunlardan dev konsol projelerine kadar çok geniş bir sektör alanı vardır.\n"
                    + "İyi bir oyun geliştirici, hem performansı hem de oyuncu deneyimini göz önünde bulundurur.\n"
                    + "Oyun mekaniği tasarımı, animasyon ilkeleri ve kullanıcı arayüzü konularında bilgi sahibi olmak önemlidir.\n"
                    + "Game jam'lere katılarak hızlı prototipleme becerileri geliştirilebilir.\n"
                    + "İndie oyun geliştiriciliği veya büyük stüdyolarda çalışma fırsatları mevcuttur.\n"
                    + "Topluluk ile etkileşim ve oyuncu geri bildirimleri oyun geliştirme sürecinin önemli bir parçasıdır.";

            showFormattedContent("🎮 Oyun Geliştirme", content);
            currentContentType = "game";
        }
        showContentArea();
    }

    @FXML
    private void handleExit(ActionEvent event) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    private void showContentArea() {
        buttonBox.setVisible(false);
        buttonBox.setManaged(false);
        scrollPane.setVisible(true);
        scrollPane.setManaged(true);
        backButton.setVisible(true);
    }

    private void showFormattedContent(String title, String content) {
        TextFlow textFlow = new TextFlow();
        textFlow.setLineSpacing(10);
        textFlow.setPadding(new Insets(20));
        textFlow.setStyle("-fx-background-color: #f9f9f9;");

        // Ana başlık (Büyük ve koyu siyah)
        Text titleText = new Text(title + "\n\n");
        titleText.setFont(Font.font("System", FontWeight.BOLD, 24));
        titleText.setFill(Color.rgb(0, 0, 0)); // Tam siyah
        textFlow.getChildren().add(titleText);

        String[] paragraphs = content.split("\n\n");
        for (String paragraph : paragraphs) {
            // Tüm başlık emojilerinin listesi
            boolean isHeader = paragraph.startsWith("🌟") || paragraph.startsWith("🧠") ||
                    paragraph.startsWith("🖼️") || paragraph.startsWith("✍️") ||
                    paragraph.startsWith("📖") || paragraph.startsWith("💼") ||
                    paragraph.startsWith("🎓") || paragraph.startsWith("📜") ||
                    paragraph.startsWith("💬") || paragraph.startsWith("👍") ||
                    paragraph.startsWith("📁") || paragraph.startsWith("📢") ||
                    paragraph.startsWith("🧑‍🤝‍🧑") || paragraph.startsWith("🚀") ||
                    paragraph.startsWith("🛠") || paragraph.startsWith("🧰") ||
                    paragraph.startsWith("📌") || paragraph.startsWith("📸") ||
                    paragraph.startsWith("🔹") || paragraph.startsWith("⚠️") ||
                    paragraph.startsWith("🙋‍♂️") || paragraph.startsWith("🛠️") ||
                    paragraph.startsWith("🎯") || paragraph.startsWith("🤝");

            if (isHeader) {
                // Başlık kısmı (Koyu siyah ve bold)
                String[] lines = paragraph.split("\n");
                Text header = new Text(lines[0] + "\n");
                header.setFont(Font.font("System", FontWeight.BOLD, 18)); // Başlık boyutu
                header.setFill(Color.rgb(0, 0, 0)); // Tam siyah
                textFlow.getChildren().add(header);

                // İçerik kısmı (Koyu gri)
                if (lines.length > 1) {
                    Text body = new Text(paragraph.substring(lines[0].length() + 1) + "\n\n");
                    body.setFont(Font.font("System", FontWeight.NORMAL, 16));
                    body.setFill(Color.rgb(70, 70, 70)); // Koyu gri
                    textFlow.getChildren().add(body);
                }
            }
            else if (paragraph.startsWith("•") || paragraph.startsWith("┌")) {
                // Madde işaretleri ve tablolar (Koyu gri)
                Text text = new Text(paragraph + "\n\n");
                text.setFont(Font.font("System", FontWeight.NORMAL, 16));
                text.setFill(Color.rgb(70, 70, 70)); // Koyu gri
                textFlow.getChildren().add(text);
            }
            else {
                // Normal metin (Koyu gri)
                Text text = new Text(paragraph + "\n\n");
                text.setFont(Font.font("System", FontWeight.NORMAL, 16));
                text.setFill(Color.rgb(70, 70, 70)); // Koyu gri
                textFlow.getChildren().add(text);
            }
        }

        contentBox.getChildren().clear();
        contentBox.getChildren().add(textFlow);
        titleLabel.setText(title);
        scrollPane.setVvalue(0);
    }
}
