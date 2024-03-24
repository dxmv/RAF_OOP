public abstract class Pitanje {
    private String tekstPitanja;
    private int brojPoena;

    public Pitanje(String tekstPitanja) {
        this.tekstPitanja = tekstPitanja;
    }

    public Pitanje(String tekstPitanja, int brojPoena) {
        this.tekstPitanja = tekstPitanja;
        this.brojPoena = brojPoena;
    }

    public abstract int brojPoena(String odgovor);

    public String getTekstPitanja() {
        return tekstPitanja;
    }

    public void setTekstPitanja(String tekstPitanja) {
        this.tekstPitanja = tekstPitanja;
    }

    public int getBrojPoena() {
        return brojPoena;
    }

    public void setBrojPoena(int brojPoena) {
        this.brojPoena = brojPoena;
    }
}
