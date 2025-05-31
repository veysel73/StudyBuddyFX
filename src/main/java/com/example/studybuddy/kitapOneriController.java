package com.example.studybuddy;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class kitapOneriController {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox kitapContainer;

    @FXML private Button btnAnaMenu;

    /**
     * Ana menüye dön butonu işlevi
     */
    @FXML
    private void anaMenuyeDon() {
        // Stage'i kapatarak önceki ekrana döner
        Stage stage = (Stage) btnAnaMenu.getScene().getWindow();
        stage.close();
    }
    @FXML
    public void initialize() {
        kitapEkle("1. İki Şehrin Hikayesi – Charles Dickens",
                "Özet: Fransız Devrimi sırasında Paris ve Londra'da geçen bu roman, aşk, fedakârlık ve devrim temalarını işler. Charles Darnay ve Sydney Carton'ın hayatları üzerinden, insan doğasının karmaşıklığı ve toplumun dönüşümü anlatılır.");

        kitapEkle("2. Küçük Prens – Antoine de Saint-Exupéry",
                "Özet: Bir pilotun çölde tanıştığı Küçük Prens'in, farklı gezegenlerde edindiği deneyimler aracılığıyla insan doğası, aşk ve dostluk üzerine derin felsefi düşünceler sunulur. Çocuk kitabı gibi görünse de her yaştan okuyucuya hitap eder.");

        kitapEkle("3. Simyacı – Paulo Coelho",
                "Özet: Endülüs'lü çoban Santiago'nun, rüyasında gördüğü hazineyi bulmak için çıktığı yolculuk, kişisel menkıbesini gerçekleştirme arayışını anlatır. Evrensel temaları ve sade anlatımıyla dikkat çeker.");

        kitapEkle("4. Harry Potter ve Felsefe Taşı – J.K. Rowling",
                "Özet: Yetim bir çocuk olan Harry Potter, Hogwarts Cadılık ve Büyücülük Okulu'na kabul edilir ve burada arkadaşlık, cesaret ve kendini keşfetme yolculuğuna başlar. Serinin ilk kitabıdır.");

        kitapEkle("5. On Küçük Zenci – Agatha Christie",
                "Özet: Issız bir adada toplanan on kişinin, geçmişte işledikleri suçlar nedeniyle birer birer öldürülmesini konu alan, gizem dolu bir dedektif romanıdır.");

        kitapEkle("6. Yüzüklerin Efendisi – J.R.R. Tolkien",
                "Özet: Orta Dünya'da geçen bu epik fantastik roman, hobbit Frodo Baggins'in, karanlık lord Sauron'un gücünü temsil eden Tek Yüzük'ü yok etme görevini konu alır. Üçlemenin kitapları: 'Yüzük Kardeşliği', 'İki Kule' ve 'Kralın Dönüşü'dür.");

        kitapEkle("7. Don Kişot – Miguel de Cervantes",
                "Özet: Kendini şövalye sanan Don Kişot'un, sadık yardımcısı Sancho Panza ile birlikte çıktığı maceralar anlatılır. Gerçeklik ve hayal arasındaki çizgiyi sorgulayan bu eser, modern romanın öncülerindendir.");

        kitapEkle("8. Rüzgar Gibi Geçti – Margaret Mitchell",
                "Özet: Amerikan İç Savaşı sırasında Güneyli Scarlett O'Hara'nın aşkları, kayıpları ve hayatta kalma mücadelesi anlatılır. 1937'de Pulitzer Ödülü kazanan bu roman, 30 milyondan fazla satmıştır.");

        kitapEkle("9. Bülbülü Öldürmek – Harper Lee",
                "Özet: 1930'ların Alabama'sında geçen hikâye, genç Scout Finch'in gözünden ırkçılık ve adaletsizlik temalarını işler. Babası Atticus Finch'in, haksız yere suçlanan siyahi bir adamı savunması merkezde yer alır. 1961'de Pulitzer Ödülü kazanmıştır.");

        kitapEkle("10. 1984 – George Orwell",
                "Özet: Totaliter bir rejimin hüküm sürdüğü distopik bir gelecekte, bireysel özgürlüklerin yok edildiği bir toplumda geçen bu roman, Büyük Birader'in gözetimi altında yaşayan Winston Smith'in isyanını konu alır.");
        kitapEkle("11. Hayvan Çiftliği – George Orwell",
                " Özet: Bir çiftlikteki hayvanlar, baskıcı çiftlik sahibine karşı isyan eder ve kendi yönetimlerini kurarlar. Ancak zamanla domuzlar diğer hayvanlar üzerinde baskı kurmaya başlar. Bu politik alegori, Sovyetler Birliği’ndeki güç yozlaşmasını ve totaliter rejimlerin çöküşünü hicveder.");
        kitapEkle("12. Jane Eyre – Charlotte Brontë",
                " Özet: Yetim Jane Eyre’nin zorlu geçen çocukluğu, içsel gücünü keşfetmesi ve Bay Rochester’a olan aşkı anlatılır. Roman, aşk, özgürlük, ahlak ve kadın hakları gibi temaları işlerken, gotik unsurlar da taşır.");
        kitapEkle("13. Brave New World – Aldous Huxley",
                "    Özet: Uzak bir gelecekte, insanların klonlandığı ve mutluluk için kimyasallarla uyuşturulduğu bir dünyada geçer. Birey olmanın anlamsızlaştığı bu toplumda, John adında “vahşi” bir karakterin varoluşu sistemi sorgulamasına neden olur.");
        kitapEkle("14. Bülbülü Öldürmek – Harper Lee",
                "    Özet:  1930’lar Amerika’sında küçük bir kasabada geçen hikâyede, çocuk Scout’un gözünden ırkçılık, adalet ve insan hakları işlenir. Babası Atticus Finch’in mahkemede bir siyahi adamı savunması olayların merkezindedir.");
        kitapEkle("15. Anne Frank’in Hatıra Defteri – Anne Frank",
                "    Özet:    Nazi işgali altındaki Hollanda’da, bir çatı katına saklanan genç Yahudi kız Anne Frank’in günlüğüdür. Saklanma süresince yaşadıkları, korkuları, umutları ve insanlığa dair gözlemleri samimi ve etkileyicidir.");
        kitapEkle("16. Sefiller – Victor Hugo",
                "    Özet:     Eski mahkum Jean Valjean’ın, iyilik yoluna girip hayatını değiştirme mücadelesi, Fransız toplumunun çeşitli kesimlerinden karakterlerle iç içe anlatılır. Adalet, merhamet, yoksulluk ve devrim gibi temalar işlenir.");
        kitapEkle("17. Aşk ve Gurur – Jane Austen",
                "    Özet: Elizabeth Bennet ile gururlu Bay Darcy arasındaki ilişkide, sınıf farklılıkları, toplumsal beklentiler ve önyargılar ele alınır. 19. yüzyıl İngiltere’sinin kadın-erkek ilişkilerine dair keskin bir eleştiri sunar.");
        kitapEkle("18. Dracula – Bram Stoker",
                "    Özet:  Transilvanya'dan Londra’ya gelen Kont Dracula'nın vampirlik faaliyetleri ve bir grup insanın onu durdurma çabası anlatılır. Gotik romanın başyapıtlarındandır ve vampir mitolojisinin temelini oluşturur.");
        kitapEkle("19. Frankenstein – Mary Shelley",
                "    Özet:  Victor Frankenstein adında bir bilim insanı, ölü dokulardan bir yaratık oluşturur. Ancak yaratığın yalnızlığı ve toplumdan dışlanması, onu acımasız bir intikama sürükler. Roman, bilim ve etik arasındaki dengeyi sorgular.");
        kitapEkle("20. Savaş ve Barış – Lev Tolstoy",
                "    Özet:  Napolyon Savaşları döneminde Rus aristokrasisinin yaşamını konu alır. Aşk, ölüm, tarih, savaş ve insanlık üzerine felsefi düşüncelerle doludur. Karakterleriyle psikolojik derinlik sunar ve gerçekçiliğiyle dikkat çeker.");
        kitapEkle("21. Vadideki Zambak – Honoré de Balzac",
                "    Özet:    Genç Félix de Vandenesse’in, evli bir kadın olan Henriette de Mortsauf’a duyduğu platonik aşkı anlatır. Saf bir duyguyla başlayan bu aşk, zamanla ruhsal bir arınmaya dönüşür. Fransız toplumunun ahlak anlayışı ve bireyin içsel çatışmaları işlenir.");
        kitapEkle("22. Uğultulu Tepeler – Emily Brontë",
                "    Özet:   Heathcliff ve Catherine Earnshaw’un tutkulu ama yıkıcı aşkı üzerine kurulu olan roman, intikam, sınıf farkı ve doğa ile medeniyet arasındaki çatışmayı gotik bir atmosferle ele alır. Karakterlerin duygusal karmaşası eserin temelini oluşturur.");
        kitapEkle("23. Büyük Umutlar – Charles Dickens",
                "    Özet:     Kimsesiz bir çocuk olan Pip’in, gizemli bir şekilde zenginleşerek aristokrasiye katılma çabasını ve Estella’ya olan karşılıksız aşkını anlatır. Viktorya dönemi İngiltere’sindeki sınıf sistemi ve kişisel gelişim temaları işlenir.");
        kitapEkle("24. Benim Adım Kırmızı – Orhan Pamuk",
                "    Özet: 16. yüzyıl Osmanlısında geçen romanda, bir minyatür ustasının ölümüyle başlayan gizemli olaylar zinciri, sanat, din ve bireysellik çatışmaları eşliğinde çözülmeye çalışılır. Roman, her bölümde farklı bir karakterin gözünden anlatılarak ilerler");
        kitapEkle("25. Gazap Üzümleri – John Steinbeck",
                "    Özet:     Amerika’daki Büyük Buhran döneminde, Joad ailesinin Kaliforniya’ya göç ederken yaşadığı zorlukları anlatır. Kapitalizmin adaletsizliği, göçmenlerin sefaleti ve insan dayanışması ön plandadır.");
        kitapEkle("26. Bülbül’ü Öldürmek – Harper Lee",
                "    Özet:   Scout Finch’in çocukluk anıları eşliğinde anlatılan hikâye, Amerika’nın güneyindeki ırkçılığı ve adalet sistemini sorgular. Babası Atticus Finch’in savunduğu adalet, romanın merkezindedir. (Listeye önceki sıralarda da eklenmişti, dilersen bu kitap yerine farklı bir kitap koyabilirim.)");
        kitapEkle("27. Karamazov Kardeşler – Fyodor Dostoyevski",
                "    Özet:     Baba Karamazov’un öldürülmesi ve üç oğlunun bu olayla yüzleşmeleri anlatılır. Ahlak, inanç, özgür irade ve insan doğasına dair felsefi tartışmalar eserin temelini oluşturur.");
        kitapEkle("28. Yabancı – Albert Camus",
                "    Özet: Meursault adlı karakterin, annesinin ölümü karşısında duygusuz tepkisi ve sonrasında işlediği cinayet, varoluşçuluğun temel meseleleriyle işlenir. Toplumun bireyden beklediği “normal” davranışlara karşı duruşu ele alınır.");
        kitapEkle("29. Suç ve Ceza – Fyodor Dostoyevski",
                "    Özet: Rodion Raskolnikov’un, işlediği bir cinayet sonrası yaşadığı vicdan azabı ve ruhsal çözülme süreci anlatılır. Suç, ceza, adalet, kurtuluş ve ahlak gibi derin konular işlenir.");
        kitapEkle("30. Lolita – Vladimir Nabokov",
                "    Özet:     Orta yaşlı Humbert Humbert’in, genç bir kız olan Dolores’e (Lolita) karşı duyduğu saplantılı aşkı anlatır. Tartışmalı temalarına rağmen edebi üslubu ve anlatıcı bakış açısıyla büyük yankı uyandırmıştır.");
        kitapEkle("31. Don Kişot – Miguel de Cervantes",
                "    Özet:    İspanyol soylusu Alonso Quijano, şövalye romanlarından etkilenerek “Don Kişot” kimliğine bürünür ve sadık yaveri Sancho Panza ile birlikte hayali kötülüklere karşı savaşmaya başlar. Gerçeklik ve hayal arasında geçen bu mizahi roman, idealizm ile gerçeklik çatışmasını ele alır.");
        kitapEkle("32. Yüzüklerin Efendisi – J.R.R. Tolkien",
                "    Özet:  Orta Dünya’da geçen epik bir yolculukta, genç hobbit Frodo, karanlık güçlerin eline geçmemesi gereken bir yüzüğü yok etmekle görevlendirilir. İyilik ile kötülük savaşı, dostluk, cesaret ve fedakârlık gibi temalar işlenir.");
        kitapEkle("33. Silmarillion – J.R.R. Tolkien",
                "    Özet:     Orta Dünya'nın yaratılışı, tanrılar, elfler, insanlar ve kötülüğün doğuşunu anlatır. Mitolojik bir dille kaleme alınan bu eser, Tolkien evreninin temelini oluşturur. Derinlikli tarih ve kozmoloji içerir.");
        kitapEkle("34. Zorba – Nikos Kazancakis",
                "    Özet:     Yunanistan’da geçen hikâyede, hayatı entelektüel bir şekilde yaşayan anlatıcı ile özgür ruhlu Alexis Zorba’nın dostluğu anlatılır. Zorba’nın yaşamı dolu dolu yaşama felsefesi, okuyucuyu hayata dair yeniden düşünmeye sevk eder.");
        kitapEkle("35. Vadideki Zambak – Honoré de Balzac",
                "    Özet:     (Önceki listede yer aldı ama burada da öneminden ötürü tekrar eklenmiştir.) Félix’in, evli ve soylu bir kadına duyduğu saf aşk, fedakârlık, bağlılık ve ruhsal olgunlaşmayı işler. Döneminin ahlak anlayışı eleştirilir.");
        kitapEkle("36. Anna Karenina – Lev Tolstoy",
                "    Özet:  Anna Karenina, tutkulu bir aşka sürüklendiği Kont Vronsky ile yaşadığı ilişki yüzünden toplumdan dışlanır. Aile, evlilik, ihanet ve kadınların toplumdaki rolü gibi temalar derinlemesine ele alınır.");
        kitapEkle("37. Babalar ve Oğullar – Ivan Turgenyev",
                "    Özet:  Rusya’nın değişen toplumsal yapısı içinde, kuşaklar arası çatışma anlatılır. Nihilist Bazarov ile gelenekçi babalar arasındaki fikir ayrılıkları, bireysel özgürlük ve inanç sorgulamalarıyla harmanlanır.");
        kitapEkle("38. İki Şehrin Hikâyesi – Charles Dickens",
                "    Özet:     Fransız Devrimi sırasında Paris ve Londra’da geçen hikâyede, halkın isyanı, adalet arayışı ve fedakârlık temaları öne çıkar. Sydney Carton’un aşk uğruna yaptığı özveri romanın zirve noktasıdır.");
        kitapEkle("39. Goriot Baba – Honoré de Balzac",
                "    Özet: Paris'te yaşayan eski tüccar Goriot'un, kızları için yaptığı fedakârlıklar ve onları kaybetmesi üzerinden toplumdaki yozlaşma, sınıf farklılıkları ve aile bağları işlenir. Realizmin başyapıtlarındandır.");
        kitapEkle("40. Cesur Yeni Dünya – Aldous Huxley",
                "    Özet:    (Tekrar vurgulanması gereken önemli bir eser olarak listede tekrar yer alır.) Teknoloji ve mutluluk için özgürlükten vazgeçmiş bir toplumda, birey olmanın anlamı sorgulanır. Distopik temasıyla modern yaşam eleştirisi yapar.");
        kitapEkle("41. Satranç – Stefan Zweig",
                "    Özet:     Bir gemi yolculuğunda, Nazi işkencesinden kurtulmuş bir adam ile dünya satranç şampiyonu arasında oynanan maç anlatılır. Satranç, psikolojik savaşın ve insan zihninin sınırlarını sembolize eder. İnsan direnci ve zihinsel esaret temaları işlenir.");
        kitapEkle("42. Martin Eden – Jack London",
                "    Özet:   Alt sınıftan gelen Martin Eden, aşık olduğu burjuva kız uğruna kendini eğiterek yazar olur. Ancak edebi başarıya ulaşınca, sistemin ikiyüzlülüğünü fark eder. Bireysellik, sınıf farklılıkları ve idealizmin çöküşü anlatılır.");
        kitapEkle("43. İnsan Neyle Yaşar – Lev Tolstoy",
                "    Özet:     Tolstoy’un kısa öykülerinden oluşan bu eser, insanın gerçek ihtiyaçlarının ne olduğu, sevgi, inanç, merhamet ve insan olmanın anlamı üzerine derin mesajlar içerir. Hristiyan ahlakı ve insanlık temaları öne çıkar.");
        kitapEkle("44. Dava – Franz Kafka",
                "    Özet:  Josef K. bir sabah sebepsiz yere tutuklanır ve nedenini asla öğrenemeden yargılanır. Bürokrasi, suçluluk duygusu, adaletin belirsizliği ve bireyin yalnızlığı işlenir. Kafka’nın “kafkaesk” atmosferiyle klasikleşmiştir.");
        kitapEkle("45. Simyacı – Paulo Coelho",
                "    Özet:  Endülüslü çoban Santiago’nun rüyasında gördüğü hazineyi bulmak için çıktığı yolculuk, kendi iç yolculuğuna dönüşür. Kader, kişisel efsane, evrenle uyum gibi spiritüel ve felsefi temaları sade bir dille sunar. ");
        kitapEkle("46. Otostopçunun Galaksi Rehberi – Douglas Adams",
                "    Özet: Dünyanın yok edilmesinin ardından galakside sürreal bir maceraya atılan Arthur Dent’in öyküsüdür. Mizah, bilim kurgu ve absürtlük iç içedir. Hayatın anlamı sorusu eğlenceli şekilde işlenir: ");
        kitapEkle("47. Beyaz Diş – Jack London",
                "    Özet:     Kutup bölgesinde geçen hikâyede, bir kurt köpeği olan Beyaz Diş’in doğayla ve insanlarla mücadelesi anlatılır. Doğal seçilim, evrim, uyum ve sevgiye duyulan ihtiyaç vurgulanır.");
        kitapEkle("48. Gurur ve Önyargı – Jane Austen",
                "    Özet: Elizabeth Bennet ile Darcy arasındaki ilişki, bireysel gelişim ve önyargıların aşılması temalarıyla süslenmiştir. Kadınların toplumdaki yeri öne çıkar.");
        kitapEkle("49. Peter Pan – J.M. Barrie",
                "    Özet:     Hiç büyümeyen çocuk Peter Pan’in, Düşler Ülkesi’nde Wendy ve kardeşleriyle yaşadığı maceralar anlatılır. Çocukluk, özgürlük, hayal gücü ve zamanın geçiciliği gibi temalar masalsı bir dille ele alınır.");
        kitapEkle("50. Küçük Prens – Antoine de Saint-Exupéry",
                "Özet: Bir pilotun çölde karşılaştığı Küçük Prens'in gezegenler arası yolculuğu ve yaşam hakkındaki düşünceleri anlatılır. Sevgi, arkadaşlık, masumiyet ve yetişkinlerin dünyasına yönelik eleştiriler, çocuk kitabı gibi görünen ama derin felsefi içeriğe sahip bu eserde toplanır.");

        // ScrollPane ayarları
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    }

    private void kitapEkle(String kitapAdi, String ozet) {
        // Kitap adı ve checkbox için HBox
        HBox hbox = new HBox();
        hbox.setSpacing(10);

        // Kitap adı label'ı
        Label adLabel = new Label(kitapAdi);
        adLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        adLabel.setStyle("-fx-text-fill: black;");
        HBox.setHgrow(adLabel, Priority.ALWAYS); // Sola yasla

        // CheckBox (sağ köşeye hizalanmış)
        CheckBox okunduCheck = new CheckBox("Okundu");
        okunduCheck.setStyle("-fx-padding: 0 10 0 0;");

        hbox.getChildren().addAll(adLabel, okunduCheck);

        // Özet label'ı
        Label ozetLabel = new Label(ozet);
        ozetLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
        ozetLabel.setStyle("-fx-text-fill: black; -fx-padding: 0 0 10 20;");
        ozetLabel.setWrapText(true);

        // Container'a ekle
        kitapContainer.getChildren().addAll(hbox, ozetLabel);
    }
}
