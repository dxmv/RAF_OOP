package app;

import java.time.LocalDate;
import java.time.Period;

// Za svako vozilo u fajlu su dati marka, model, tip, broj takvih vozila za iznajmljivanje i cena iznajmljivanja po danu.
public class Vozilo {
    private String marka;
    private String model;
    private String tip;
    private int brojVozila;
    private double cenaPoDanu;

    public Vozilo(String marka, String model, String tip, int brojVozila, double cenaPoDanu) {
        this.marka = marka;
        this.model = model;
        this.tip = tip;
        this.brojVozila = brojVozila;
        this.cenaPoDanu = cenaPoDanu;
    }

    // vraca cenu za odredjeni period
    public double cenaZaPeriod(LocalDate pocetakDatum,LocalDate krajDatum){
        // racunamo broj dana Period.between(date1, date2).getDays()
        // pomnozimo broj dana sa cenom po danu
        return Period.between(pocetakDatum,krajDatum).getDays() * cenaPoDanu;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Vozilo v){
            return this.marka.equals(v.getMarka()) && this.model.equals(v.getModel()) && this.tip.equals(v.getTip());
        }
        return false;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public int getBrojVozila() {
        return brojVozila;
    }

    public void setBrojVozila(int brojVozila) {
        this.brojVozila = brojVozila;
    }

    public double getCenaPoDanu() {
        return cenaPoDanu;
    }

    public void setCenaPoDanu(double cenaPoDanu) {
        this.cenaPoDanu = cenaPoDanu;
    }

}
