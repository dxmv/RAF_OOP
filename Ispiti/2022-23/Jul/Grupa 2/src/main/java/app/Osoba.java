package app;

// iz fajla osobe.txt
// Marko Markovic
public class Osoba {
    private String imePrezime;
    public static final String IME_FAJLA = "osobe.txt";

    public Osoba(String imePrezime) {
        this.imePrezime = imePrezime;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Osoba osoba){
            return this.imePrezime.equals(osoba.getImePrezime());
        }
        return false;
    }

    @Override
    public String toString() {
        return imePrezime;
    }

    public String getImePrezime() {
        return imePrezime;
    }

    public void setImePrezime(String imePrezime) {
        this.imePrezime = imePrezime;
    }
}
