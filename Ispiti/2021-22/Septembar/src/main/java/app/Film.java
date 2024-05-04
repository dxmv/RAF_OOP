package app;

public class Film {
    private String ime;
    private String faza;
    private double ocena;
    private int godinaIzlazka;
    private int duzinaTrajanja;

    private boolean odgledan;

    public Film(String ime, int faza, double ocena, int godinaIzlazka, int duzinaTrajanja) {
        this.ime = ime;
        this.faza = "Phase " + faza;
        this.ocena = ocena;
        this.godinaIzlazka = godinaIzlazka;
        this.duzinaTrajanja = duzinaTrajanja;
        this.odgledan = false;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getFaza() {
        return faza;
    }

    public void setFaza(String faza) {
        this.faza = faza;
    }

    public double getOcena() {
        return ocena;
    }

    public void setOcena(double ocena) {
        this.ocena = ocena;
    }

    public int getGodinaIzlazka() {
        return godinaIzlazka;
    }

    public void setGodinaIzlazka(int godinaIzlazka) {
        this.godinaIzlazka = godinaIzlazka;
    }

    public int getDuzinaTrajanja() {
        return duzinaTrajanja;
    }

    public void setDuzinaTrajanja(int duzinaTrajanja) {
        this.duzinaTrajanja = duzinaTrajanja;
    }

    public boolean isOdgledan() {
        return odgledan;
    }

    public void setOdgledan(boolean odgledan) {
        this.odgledan = odgledan;
    }

    @Override
    public String toString() {
        return ime + "(" + godinaIzlazka + ")" + " - " + duzinaTrajanja;
    }
}
