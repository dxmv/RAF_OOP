public class TekstualnaPoruka extends Poruka{
    private String tekst;
    public TekstualnaPoruka(String posiljalac,String tekst) {
        super(posiljalac);
        this.tekst = tekst;
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    // pod 3.
    @Override
    protected String formirajSadrzinu() {
        // metoda vraća tekst
        return tekst;
    }
}
