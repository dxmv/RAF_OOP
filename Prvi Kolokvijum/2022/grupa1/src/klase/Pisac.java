package klase;

// pod 4.
public abstract class Pisac implements Objavljivanje{
    private String ime;
    private String prezime;
    private int godinaRodjenja;

    public Pisac(String ime, String prezime, int godinaRodjenja) {
        this.ime = ime;
        this.prezime = prezime;
        this.godinaRodjenja = godinaRodjenja;
    }


    @Override
    public abstract Knjizevnost objavljuje(String naziv, int godina, TipKnjizevnosti tipKnjizevnosti);


    // Getters And Setters
    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public int getGodinaRodjenja() {
        return godinaRodjenja;
    }

    public void setGodinaRodjenja(int godinaRodjenja) {
        this.godinaRodjenja = godinaRodjenja;
    }


    @Override
    public String toString() {
        return this.ime + " " + this.prezime;
    }
}
