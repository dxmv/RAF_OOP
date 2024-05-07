package app;

public class OsobaBrojSati{
    private String ime;
    private int brojSati;

    public OsobaBrojSati(String ime, int brojSati) {
        this.ime = ime;
        this.brojSati = brojSati;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public int getBrojSati() {
        return brojSati;
    }

    public void setBrojSati(int brojSati) {
        this.brojSati = brojSati;
    }
}
