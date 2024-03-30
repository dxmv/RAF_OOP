
import java.util.*;
public class Predmet {
    private String naziv;
    private int semestar;
    private int espb;
    private OznakaPlana oznakaPlana;

    private List<Predmet> preduslovi;
    public Predmet(String naziv,int semestar,OznakaPlana oznakaPlana,int espb){
        this.naziv=naziv;
        this.semestar=semestar;
        this.oznakaPlana=oznakaPlana;
        this.espb=espb;
        this.preduslovi = new ArrayList<>();
    }

    // pod 3.
    public void dodajPreduslov(Predmet p){
        // treba da proveri da li je predmet koji se dodaje na istom studijskom programu
        // da li se sluša u ranijem semestru od predmeta kome se dodaje kao preduslov
        if(p.oznakaPlana != this.oznakaPlana || p.semestar > this.semestar){
            return;
        }
        this.preduslovi.add(p);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Predmet){
            Predmet p = (Predmet) obj;
            return p.getNaziv().equals(this.naziv) && p.getEspb() == this.espb && p.semestar == this.semestar;
        }
        return false;
    }

    protected boolean studentPolozioSvePreduslove(Student s){
        // prolazi kroz sve preduslove i gleda da li ih je student polozio
        for(Predmet p : getPreduslovi()){
            if(!s.getPolozeniPredmeti().contains(p)){
                return false;
            }
        }
        return true;
    }

    public int getGodina(){
        if(this.semestar == 1 || this.semestar == 2){
            return 1;
        }
        if(this.semestar == 3 || this.semestar == 4){
            return 2;
        }
        if(this.semestar == 5 || this.semestar == 6){
            return 3;
        }
        return 4;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getSemestar() {
        return semestar;
    }

    public void setSemestar(int semestar) {
        this.semestar = semestar;
    }

    public int getEspb() {
        return espb;
    }

    public void setEspb(int espb) {
        this.espb = espb;
    }

    public OznakaPlana getOznakaPlana() {
        return oznakaPlana;
    }

    public void setOznakaPlana(OznakaPlana oznakaPlana) {
        this.oznakaPlana = oznakaPlana;
    }

    public List<Predmet> getPreduslovi() {
        return preduslovi;
    }

    public void setPreduslovi(List<Predmet> preduslovi) {
        this.preduslovi = preduslovi;
    }
}
