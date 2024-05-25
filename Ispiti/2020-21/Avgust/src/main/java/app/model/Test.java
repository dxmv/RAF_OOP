package app.model;

import java.time.LocalDate;

public class Test {
    private String predmet;
    private LocalDate datum;
    private int rezultat;

    public Test(String predmet, LocalDate datum, int rezultat) {
        this.predmet = predmet;
        this.datum = datum;
        this.rezultat = rezultat;
    }

    public String getPredmet() {
        return predmet;
    }

    public void setPredmet(String predmet) {
        this.predmet = predmet;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public int getRezultat() {
        return rezultat;
    }

    public void setRezultat(int rezultat) {
        this.rezultat = rezultat;
    }
}
