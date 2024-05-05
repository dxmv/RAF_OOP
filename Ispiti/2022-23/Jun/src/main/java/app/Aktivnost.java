package app;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;
import java.time.LocalTime;

public class Aktivnost {
    private Kurs k;
    private LocalDate pocetakDatum;
    private LocalTime pocetakVreme;

    private LocalDate krajDatum;
    private LocalTime krajVreme;
    private double kompletiranost;

    public Aktivnost(Kurs k, LocalDate pocetakDatum, LocalTime pocetakVreme, LocalDate krajDatum, LocalTime krajVreme,int trajanje) {
        this.k = k;
        this.pocetakDatum = pocetakDatum;
        this.pocetakVreme = pocetakVreme;
        this.krajDatum = krajDatum;
        this.krajVreme = krajVreme;
        this.kompletiranost = izracunajKompletiranost(trajanje);
    }

    private double izracunajKompletiranost(int trajanje){
        return (double) (trajanje / Integer.parseInt(k.getTrajanje().split(" ")[0])) * 100;
    }

    // za ispis u table view
    // Optionally, you can also create a property for naziv if needed
    public StringProperty nazivProperty() {
        return new SimpleStringProperty(k.getNaziv());
    }

    public StringProperty kategorijaProperty() {
        return new SimpleStringProperty(k.getKategorija());
    }

    public Kurs getK() {
        return k;
    }

    public void setK(Kurs k) {
        this.k = k;
    }

    public LocalDate getPocetakDatum() {
        return pocetakDatum;
    }

    public void setPocetakDatum(LocalDate pocetakDatum) {
        this.pocetakDatum = pocetakDatum;
    }

    public LocalTime getPocetakVreme() {
        return pocetakVreme;
    }

    public void setPocetakVreme(LocalTime pocetakVreme) {
        this.pocetakVreme = pocetakVreme;
    }

    public LocalDate getKrajDatum() {
        return krajDatum;
    }

    public void setKrajDatum(LocalDate krajDatum) {
        this.krajDatum = krajDatum;
    }

    public LocalTime getKrajVreme() {
        return krajVreme;
    }

    public void setKrajVreme(LocalTime krajVreme) {
        this.krajVreme = krajVreme;
    }

    public double getKompletiranost() {
        return kompletiranost;
    }

}
