package com.example.studybuddy;


import java.time.LocalDateTime;

public class FlashKart {
    private String onYuz;
    private String cevapYuzu;
    private LocalDateTime tekrarZamani;

    public FlashKart(String onYuz, String cevapYuzu) {
        this.onYuz = onYuz;
        this.cevapYuzu = cevapYuzu;
        this.tekrarZamani = LocalDateTime.now();
    }

    public String getOnYuz() {
        return onYuz;
    }

    public String getCevapYuzu() {
        return cevapYuzu;
    }

    public LocalDateTime getTekrarZamani() {
        return tekrarZamani;
    }

    public void setTekrarZamani(LocalDateTime tekrarZamani) {
        this.tekrarZamani = tekrarZamani;
    }

    public boolean tekrarIcinHazirMi() {
        return LocalDateTime.now().isAfter(tekrarZamani);
    }
}