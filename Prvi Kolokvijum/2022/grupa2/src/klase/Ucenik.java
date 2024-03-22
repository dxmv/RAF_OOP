package klase;

import java.util.ArrayList;
import java.util.List;

public class Ucenik {
    private String ime;
    private String prezime;
    private String jmbg;

    private Klasa klasa;

    private List<Beleska> beleske;

    public Ucenik(String ime, String prezime, String jmbg, Klasa klasa) {
        this.ime = ime;
        this.prezime = prezime;
        this.jmbg = jmbg;
        this.klasa = klasa;
        this.beleske = new ArrayList<>();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Ucenik u){
            // Dva učenika su ista ako imaju isti jmbg.
            return this.jmbg.equals(u.getJmbg());
        }
        return false;
    }

    public void dodajBelesku(Beleska b){
        beleske.add(b);
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public Klasa getKlasa() {
        return klasa;
    }

    public void setKlasa(Klasa klasa) {
        this.klasa = klasa;
    }

    public List<Beleska> getBeleske() {
        return beleske;
    }

    public void setBeleske(List<Beleska> beleske) {
        this.beleske = beleske;
    }
}
