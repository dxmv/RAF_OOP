public abstract class Pitanje implements ZaStampanje{
    private String tekstPitanja;
    private int redniBroj;
    private int brojPoena;

    public Pitanje(String tekstPitanja) {
        this.tekstPitanja = tekstPitanja;
    }

    public String getTekstPitanja() {
        return tekstPitanja;
    }

    public void setTekstPitanja(String tekstPitanja) {
        this.tekstPitanja = tekstPitanja;
    }

    public int getRedniBroj() {
        return redniBroj;
    }

    public void setRedniBroj(int redniBroj) {
        this.redniBroj = redniBroj;
    }

    public int getBrojPoena() {
        return brojPoena;
    }

    public void setBrojPoena(int brojPoena) {
        this.brojPoena = brojPoena;
    }

    @Override
    public boolean equals(Object obj) {
        // dva pitanja su ista ako je isti redniBroj i tekstPitanja
        if(obj instanceof Pitanje){
            Pitanje p = (Pitanje) obj;
            return this.tekstPitanja.equals(p.getTekstPitanja()) && this.redniBroj == p.getRedniBroj();
        }
        return false;
    }

    // pod 2.
    @Override
    public String vratiZaStampu() {
        // za svako pitanje prvo se ispisuje redni broj (sa tačkom), zatim u zagradi broj poena i onda tekst pitanja
        return this.redniBroj + ". (" + this.brojPoena + ") " + this.tekstPitanja;
    }

    @Override
    public boolean spremnoZaStampu() {
        // pitanja su spremna za štampu ako im tekst nije null ili prazan string
        return tekstPitanja != null && !tekstPitanja.isEmpty();
    }
}
