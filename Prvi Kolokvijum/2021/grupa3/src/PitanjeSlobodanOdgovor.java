public class PitanjeSlobodanOdgovor extends Pitanje{
    private int brojLinija;


    public PitanjeSlobodanOdgovor(String tekstPitanja) {
        super(tekstPitanja);
    }

    public int getBrojLinija() {
        return brojLinija;
    }

    public void setBrojLinija(int brojLinija) {
        this.brojLinija = brojLinija;
    }


    // pod 2.
    @Override
    public String vratiZaStampu() {
        // za pitanja sa slobodnim odgovorom ispisuje se broj linija upisan kao vrednost polja
        StringBuilder sb = new StringBuilder(super.vratiZaStampu() + "\n");
        for (int i = 0; i < brojLinija; i++) {
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public boolean spremnoZaStampu() {
        // pitanje sa slobodnim tekstom je spremno ako je broj linija veći od nula
        return super.spremnoZaStampu() && brojLinija>0;
    }
}
