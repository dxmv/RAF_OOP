package app;

public class Kurs {
    private String naziv;
    private String kategorija;
    private String trajanje;
    private String cena;

    public Kurs(String naziv, String kategorija, String trajanje, String cena) {
        this.naziv = naziv;
        this.kategorija = kategorija;
        this.trajanje = trajanje;
        this.cena = cena;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getKategorija() {
        return kategorija;
    }

    public void setKategorija(String kategorija) {
        this.kategorija = kategorija;
    }

    public String getTrajanje() {
        return trajanje;
    }

    public void setTrajanje(String trajanje) {
        this.trajanje = trajanje;
    }

    public String getCena() {
        return cena;
    }

    public void setCena(String cena) {
        this.cena = cena;
    }

    public int getIntCena(){
        return Integer.parseInt(cena.substring(1));
    }

    @Override
    public String toString() {
        return kategorija + ", " + naziv + ", Trajanje: " + trajanje + "," + cena;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Kurs k){
            return this.naziv.equals(k.getNaziv()) && this.kategorija.equals(k.getKategorija());
        }
        return false;
    }
}
