public class Odgovor implements Ocenljivo{
    private Pitanje pitanje;
    private String odgovor;

    public Odgovor(Pitanje pitanje, String odgovor) {
        this.pitanje = pitanje;
        this.odgovor = odgovor;
    }

    public Pitanje getPitanje() {
        return pitanje;
    }

    public void setPitanje(Pitanje pitanje) {
        this.pitanje = pitanje;
    }

    public String getOdgovor() {
        return odgovor;
    }

    public void setOdgovor(String odgovor) {
        this.odgovor = odgovor;
    }

    // pod 4.
    @Override
    public int oceni() {
        // treba da vrati broj poena koji odgovor dobija na pitanju
        // odnosno rezultat poziva metode brojPoena za klasu Pitanje sa prosleđenim odgovorom
        return pitanje.brojPoena(odgovor);
    }

    // pod 5.
    @Override
    public String toString() {
        // za odgovor se ispisuje tekst pitanja, vrsta pitanja, odgovor i broj poena
        return pitanje.getTekstPitanja() + " " + pitanje.getClass().getName() + " " + this.odgovor + " " + this.oceni();
    }
}
