package app.model;

import java.util.List;
import java.util.Set;

public class Termin {
    private String nastavnik;
    private String predmet;
    private String tip;
    private String danUNedelji;
    private String ucionica;
    private Integer pocetak;
    private Integer kraj;
    private Set<String> grupe;

    public Termin(String nastavnik, String predmet, String tip, String danUNedelji, String ucionica, Integer pocetak, Integer kraj, Set<String> grupe) {
        this.nastavnik = nastavnik;
        this.predmet = predmet;
        this.tip = tip;
        this.danUNedelji = danUNedelji;
        this.ucionica = ucionica;
        this.pocetak = pocetak;
        this.kraj = kraj;
        this.grupe = grupe;
    }

    public String getNastavnik() {
        return nastavnik;
    }

    public void setNastavnik(String nastavnik) {
        this.nastavnik = nastavnik;
    }

    public String getPredmet() {
        return predmet;
    }

    public void setPredmet(String predmet) {
        this.predmet = predmet;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getDanUNedelji() {
        return danUNedelji;
    }

    public void setDanUNedelji(String danUNedelji) {
        this.danUNedelji = danUNedelji;
    }

    public String getUcionica() {
        return ucionica;
    }

    public void setUcionica(String ucionica) {
        this.ucionica = ucionica;
    }

    public Integer getPocetak() {
        return pocetak;
    }

    public void setPocetak(Integer pocetak) {
        this.pocetak = pocetak;
    }

    public Integer getKraj() {
        return kraj;
    }

    public void setKraj(Integer kraj) {
        this.kraj = kraj;
    }

    public Set<String> getGrupe() {
        return grupe;
    }

    public void setGrupe(Set<String> grupe) {
        this.grupe = grupe;
    }

    @Override
    public String toString() {
        return this.nastavnik + " " + this.predmet + " " + this.tip  + " " + this.danUNedelji  + " " + this.pocetak + " - " + this.kraj;
    }

    // vraca int reprezentaciju dana u nedelji
    public int danNedelje(){
        return switch (danUNedelji) {
            case "PON" -> 1;
            case "UTO" -> 2;
            case "SRE" -> 3;
            case "ČET" -> 4;
            case "PET" -> 5;
            case "SUB" -> 6;
            case "NED" -> 7;
            default -> 8;
        };
    }
}
