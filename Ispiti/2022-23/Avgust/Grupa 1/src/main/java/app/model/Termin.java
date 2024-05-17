package app.model;

import java.util.List;
import java.util.Set;

public class Termin {
    private String predmet;
    private String nastavnik;
    private String danUNedelji;
    private String ucionica;
    private Integer pocetak;
    private Integer kraj;
    private String tip;
    private Set<String> grupe;

    public Termin(String predmet, String nastavnik, String danUNedelji, String ucionica, Integer pocetak, Integer kraj, String tip, Set<String> grupe) {
        this.predmet = predmet;
        this.nastavnik = nastavnik;
        this.danUNedelji = danUNedelji;
        this.ucionica = ucionica;
        this.pocetak = pocetak;
        this.kraj = kraj;
        this.tip = tip;
        this.grupe = grupe;
    }

    public String getPredmet() {
        return predmet;
    }

    public void setPredmet(String predmet) {
        this.predmet = predmet;
    }

    public String getNastavnik() {
        return nastavnik;
    }

    public void setNastavnik(String nastavnik) {
        this.nastavnik = nastavnik;
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

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public Set<String> getGrupe() {
        return grupe;
    }

    public void setGrupe(Set<String> grupe) {
        this.grupe = grupe;
    }

    @Override
    public String toString() {
        return this.predmet + " " + this.nastavnik + " " + this.tip  + " " + this.danUNedelji  + " " + this.pocetak + " - " + this.kraj;
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
