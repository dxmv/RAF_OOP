package klase;

import java.time.LocalDate;

public class Beleska {

    private Ucenik ucenik;
    private Aktivnost aktivnost;
    private int trajanje;
    private String opis;
    private double brojPoena;

    private LocalDate datum;

    public Beleska(Ucenik ucenik, Aktivnost aktivnost, int trajanje, String opis, double brojPoena, LocalDate datum) {
        this.ucenik = ucenik;
        this.aktivnost = aktivnost;
        this.trajanje = trajanje;
        this.opis = opis;
        this.brojPoena = brojPoena;
        this.datum = datum;
    }

    public Ucenik getUcenik() {
        return ucenik;
    }

    public void setUcenik(Ucenik ucenik) {
        this.ucenik = ucenik;
    }

    public Aktivnost getAktivnost() {
        return aktivnost;
    }

    public void setAktivnost(Aktivnost aktivnost) {
        this.aktivnost = aktivnost;
    }

    public int getTrajanje() {
        return trajanje;
    }

    public void setTrajanje(int trajanje) {
        this.trajanje = trajanje;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public double getBrojPoena() {
        return brojPoena;
    }

    public void setBrojPoena(double brojPoena) {
        this.brojPoena = brojPoena;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }
}
