package app;

import java.time.LocalDate;

public class Rezervacija {
    private Vozilo v;
    private double ukupnaCena;
    private LocalDate pocetakRezervacije;
    private LocalDate krajRezervacije;

    public Rezervacija(Vozilo v, double ukupnaCena, LocalDate pocetakRezervacije, LocalDate krajRezervacije) {
        this.v = v;
        this.ukupnaCena = ukupnaCena;
        this.pocetakRezervacije = pocetakRezervacije;
        this.krajRezervacije = krajRezervacije;
    }

    public boolean rezervacijaURange(LocalDate pocetak,LocalDate kraj){
       return pocetak.isBefore(krajRezervacije) && pocetakRezervacije.isBefore(kraj);
    }

    public Vozilo getV() {
        return v;
    }

    public void setV(Vozilo v) {
        this.v = v;
    }

    public double getUkupnaCena() {
        return ukupnaCena;
    }

    public void setUkupnaCena(double ukupnaCena) {
        this.ukupnaCena = ukupnaCena;
    }

    public LocalDate getPocetakRezervacije() {
        return pocetakRezervacije;
    }

    public void setPocetakRezervacije(LocalDate pocetakRezervacije) {
        this.pocetakRezervacije = pocetakRezervacije;
    }

    public LocalDate getKrajRezervacije() {
        return krajRezervacije;
    }

    public void setKrajRezervacije(LocalDate krajRezervacije) {
        this.krajRezervacije = krajRezervacije;
    }
}
