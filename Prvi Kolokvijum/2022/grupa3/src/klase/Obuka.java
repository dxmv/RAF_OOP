package klase;

import java.util.ArrayList;
import java.util.List;

// pod 4.
public abstract class Obuka {
    private String naziv;
    private int minimalniBrojPolaznika;
    private Oblast oblast;

    private List<Profesor> predavaci;

    private List<ObukaPolaznika> obukePolaznika;

    public Obuka(String naziv, int minimalniBrojPolaznika, Oblast oblast) {
        this.naziv = naziv;
        this.minimalniBrojPolaznika = minimalniBrojPolaznika;
        this.oblast = oblast;
        this.predavaci = new ArrayList<>();
        this.obukePolaznika = new ArrayList<>();
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getMinimalniBrojPolaznika() {
        return minimalniBrojPolaznika;
    }

    public void setMinimalniBrojPolaznika(int minimalniBrojPolaznika) {
        this.minimalniBrojPolaznika = minimalniBrojPolaznika;
    }

    public Oblast getOblast() {
        return oblast;
    }

    public void setOblast(Oblast oblast) {
        this.oblast = oblast;
    }

    public List<Profesor> getPredavaci() {
        return predavaci;
    }

    public void setPredavaci(List<Profesor> predavaci) {
        this.predavaci = predavaci;
    }

    public List<ObukaPolaznika> getObukePolaznika() {
        return obukePolaznika;
    }

    public void setObukePolaznika(List<ObukaPolaznika> obukePolaznika) {
        this.obukePolaznika = obukePolaznika;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Obuka){
            // dve obuke su iste ako imaju isti naziv
            return this.naziv.equals(((Obuka) obj).getNaziv());
        }
        return false;
    }

    // pod 6.
    public void dodajPredavaca(Profesor profesor) {
        this.predavaci.add(profesor);
    }

    public void dodajObukuPolaznika(ObukaPolaznika obukaPolaznika) { this.obukePolaznika.add(obukaPolaznika); }

}


