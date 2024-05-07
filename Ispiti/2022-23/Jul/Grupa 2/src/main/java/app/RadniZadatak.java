package app;

import java.util.ArrayList;
import java.util.List;

// iz fajla radniZadaci.txt
public class RadniZadatak {
    private String naziv;
    private List<RadniZadatak> preduslovi;
    private List<RadniZadatak> predusloviOvogZadatka;

    public static final String IME_FAJLA = "radniZadaci.txt";

    public RadniZadatak(String naziv) {
        this.naziv = naziv;
        this.preduslovi = new ArrayList<>();
        this.predusloviOvogZadatka = new ArrayList<>();
    }

    @Override
    public String toString() {
        return naziv;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof RadniZadatak rz){
            return this.naziv.equals(rz.getNaziv());
        }
        return false;
    }

    // prima zavrsene zadatke kao argument
    public boolean ispunjeniUslovi(List<RadniZadatak> zadaci){
        for(RadniZadatak pred:predusloviOvogZadatka){
            if(!zadaci.contains(pred)){
                return false;
            }
        }
        return true;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public List<RadniZadatak> getPreduslovi() {
        return preduslovi;
    }

    public void setPreduslovi(List<RadniZadatak> preduslovi) {
        this.preduslovi = preduslovi;
    }

    public List<RadniZadatak> getPredusloviOvogZadatka() {
        return predusloviOvogZadatka;
    }

    public void setPredusloviOvogZadatka(List<RadniZadatak> predusloviOvogZadatka) {
        this.predusloviOvogZadatka = predusloviOvogZadatka;
    }
}
