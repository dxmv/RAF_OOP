package app.model;

import java.util.ArrayList;
import java.util.List;

public class TerminPolaganja {
    private Ucionica ucionica;
    private List<Student> studenti = new ArrayList<>();

    private int termin;

    private int brojSlobodnih;
    private int prekoracenje;

    public TerminPolaganja(Ucionica ucionica, int termin) {
        this.ucionica = ucionica;
        this.studenti = new ArrayList<>();
        this.termin = termin;
        this.brojSlobodnih = ucionica.getDozvoljenBroj();
        this.prekoracenje = 0;
    }

    // isti su ako su ucionice i termin isti
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof TerminPolaganja tp){
            return tp.ucionica.equals(this.ucionica) && tp.termin == this.termin;
        }
        return false;
    }

    // dodaje studenta u termin
    public void dodajStudenta(Student s){
        this.studenti.add(s);
        // smanjujemo broj slobodnih i povecavamo prekoracenje ako je potrebno
        this.brojSlobodnih = brojSlobodnih - 1 >=0 ? brojSlobodnih - 1 : 0;
        this.prekoracenje = this.studenti.size() > this.ucionica.getDozvoljenBroj() ? prekoracenje + 1 : 0;

    }

    public void izbaciStudenta(Student s){
        this.studenti.remove(s);
        this.prekoracenje = this.studenti.size() > this.ucionica.getDozvoljenBroj() ? this.studenti.size() - this.ucionica.getDozvoljenBroj() : 0;
        this.brojSlobodnih = this.studenti.size() > this.ucionica.getDozvoljenBroj() ? 0 : this.ucionica.getDozvoljenBroj() - this.studenti.size();
    }

    @Override
    public String toString() {
        return this.studenti.toString() + "," + this.termin + "," + this.ucionica.getIme();
    }

    public int getBrojSlobodnih() {
        return brojSlobodnih;
    }

    public void setBrojSlobodnih(int brojSlobodnih) {
        this.brojSlobodnih = brojSlobodnih;
    }

    public int getPrekoracenje() {
        return prekoracenje;
    }

    public void setPrekoracenje(int prekoracenje) {
        this.prekoracenje = prekoracenje;
    }

    public Ucionica getUcionica() {
        return ucionica;
    }

    public void setUcionica(Ucionica ucionica) {
        this.ucionica = ucionica;
    }

    public List<Student> getStudenti() {
        return studenti;
    }

    public void setStudenti(List<Student> studenti) {
        this.studenti = studenti;
    }

    public int getTermin() {
        return termin;
    }

    public void setTermin(int termin) {
        this.termin = termin;
    }
}
