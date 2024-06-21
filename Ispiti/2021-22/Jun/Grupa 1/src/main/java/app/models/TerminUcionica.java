package app.models;

public class TerminUcionica {
    private String termin;
    private Ucionica ucionica;
    private int brojUcenika;

    @Override
    public String toString() {
        return ucionica.toString() + " - " + brojUcenika;
    }

    public TerminUcionica(String termin, Ucionica ucionica, int brojUcenika) {
        this.termin = termin;
        this.ucionica = ucionica;
        this.brojUcenika = brojUcenika;
    }

    public String getTermin() {
        return termin;
    }

    public void setTermin(String termin) {
        this.termin = termin;
    }

    public Ucionica getUcionica() {
        return ucionica;
    }

    public void setUcionica(Ucionica ucionica) {
        this.ucionica = ucionica;
    }

    public int getBrojUcenika() {
        return brojUcenika;
    }

    public void setBrojUcenika(int brojUcenika) {
        this.brojUcenika = brojUcenika;
    }
}
