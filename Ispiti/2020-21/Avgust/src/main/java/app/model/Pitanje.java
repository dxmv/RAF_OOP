package app.model;

public class Pitanje {
    private String pitanje;
    private String odgovor;
    private String predmet;

    public Pitanje(String pitanje, String odgovor, String predmet) {
        this.pitanje = pitanje;
        this.odgovor = odgovor;
        this.predmet = predmet;
    }

    public boolean odgovorJeTacan(String odgovor){
        // case insensitive provera
        return odgovor.equalsIgnoreCase(this.odgovor);
    }

    @Override
    public String toString() {
        return pitanje;
    }

    public String getPitanje() {
        return pitanje;
    }

    public void setPitanje(String pitanje) {
        this.pitanje = pitanje;
    }

    public String getOdgovor() {
        return odgovor;
    }

    public void setOdgovor(String odgovor) {
        this.odgovor = odgovor;
    }

    public String getPredmet() {
        return predmet;
    }

    public void setPredmet(String predmet) {
        this.predmet = predmet;
    }
}
