package klase;

import java.util.ArrayList;
import java.util.List;

public class Klasa implements ImaProsek{
    private int razred;
    private int odeljenje;
    private String razredniStaresina;
    private Smer smer;

    private List<Ucenik> ucenici;

    public Klasa(int razred, int odeljenje, String razredniStaresina, Smer smer) {
        this.razred = razred;
        this.odeljenje = odeljenje;
        this.razredniStaresina = razredniStaresina;
        this.smer = smer;
        this.ucenici = new ArrayList<>();
    }

    // vraca index trazenog ucenika u listi
    // ako ne postoji vraca -1
    private int indexUcenika(Ucenik u){
        for(int i=0;i<ucenici.size();i++){
            if(u.equals(ucenici.get(i))){
                return i;
            }
        }
        return -1;
    }

    // vraca true ako je ucenik upisan
    // false ako nije upisan
    public boolean dodajUcenika(Ucenik u){
        int index = this.indexUcenika(u);
        if(index == -1){
            ucenici.add(u);
            return true;
        }
        return false;
    }


    @Override
    public double prosek() {
        // prosek klase Klasa predstavlja prosečan broj beleški za učenika
        int ukupanBroj=0;
        for(Ucenik u:ucenici){
            ukupanBroj += u.getBeleske().size();
        }
        return ucenici.isEmpty() ? 0 : (double) ukupanBroj / ucenici.size();
    }


    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Klasa){
            Klasa k = (Klasa) obj;
            // Dve klase su iste ako imaju isti smer, razred i odeljenje.
            return this.smer.equals(k.getSmer()) && this.razred == k.getRazred() && this.odeljenje == k.getOdeljenje();
        }
        return false;
    }

    public int getRazred() {
        return razred;
    }

    public void setRazred(int razred) {
        this.razred = razred;
    }

    public int getOdeljenje() {
        return odeljenje;
    }

    public void setOdeljenje(int odeljenje) {
        this.odeljenje = odeljenje;
    }

    public String getRazredniStaresina() {
        return razredniStaresina;
    }

    public void setRazredniStaresina(String razredniStaresina) {
        this.razredniStaresina = razredniStaresina;
    }

    public Smer getSmer() {
        return smer;
    }

    public void setSmer(Smer smer) {
        this.smer = smer;
    }

    public List<Ucenik> getUcenici() {
        return ucenici;
    }

    public void setUcenici(List<Ucenik> ucenici) {
        this.ucenici = ucenici;
    }
}
