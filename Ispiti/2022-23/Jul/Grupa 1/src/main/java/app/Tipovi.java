package app;

import java.util.ArrayList;
import java.util.List;

public class Tipovi {
    private List<Vozilo> vozila;
    private String tip;
    private double najnizaCena;
    private double najvisaCena;

    public Tipovi(String tip,Vozilo v){
        this.tip = tip;
        List<Vozilo> l = new ArrayList<>();
        l.add(v);
        this.vozila = l;
        this.najnizaCena = v.getCenaPoDanu();
        this.najnizaCena = v.getCenaPoDanu();
    }

    public void dodajVozilo(Vozilo v){
        najnizaCena = Math.min(v.getCenaPoDanu(),this.najnizaCena);
        najvisaCena = Math.max(v.getCenaPoDanu(),this.najvisaCena);
        vozila.add(v);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Tipovi t){
            return this.tip.equals(t.getTip());
        }
        return false;
    }

    @Override
    public String toString() {
        return this.tip + " " + " cena po danu: $" + this.najnizaCena + "-$" + this.najvisaCena;
    }

    public List<Vozilo> getVozila() {
        return vozila;
    }

    public void setVozila(List<Vozilo> vozila) {
        this.vozila = vozila;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public double getNajnizaCena() {
        return najnizaCena;
    }

    public void setNajnizaCena(double najnizaCena) {
        this.najnizaCena = najnizaCena;
    }

    public double getNajvisaCena() {
        return najvisaCena;
    }

    public void setNajvisaCena(double najvisaCena) {
        this.najvisaCena = najvisaCena;
    }
}
