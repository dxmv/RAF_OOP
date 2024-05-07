package app;

import java.util.ArrayList;
import java.util.List;

public class Akcija {
    private RadniZadatak zadatak;
    private List<Osoba> osobe;
    private int brojSati;
    private int uradjeno;

    public Akcija(RadniZadatak zadatak,List<Osoba> osobe, int brojSati, int uradjeno) {
        this.zadatak = zadatak;
        this.brojSati = brojSati;
        this.uradjeno = uradjeno;
        this.osobe = osobe;
    }


    // akcije su iste ako je isti radni zadatak
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Akcija akcija){
            return this.zadatak.equals(akcija.getZadatak());
        }
        return false;
    }

    public RadniZadatak getZadatak() {
        return zadatak;
    }

    public void setZadatak(RadniZadatak zadatak) {
        this.zadatak = zadatak;
    }

    public List<Osoba> getOsobe() {
        return osobe;
    }

    public void setOsobe(List<Osoba> osobe) {
        this.osobe = osobe;
    }

    public int getBrojSati() {
        return brojSati;
    }

    public void setBrojSati(int brojSati) {
        this.brojSati = brojSati;
    }

    public int getUradjeno() {
        return uradjeno;
    }

    public void setUradjeno(int uradjeno) {
        this.uradjeno = uradjeno;
    }
}
