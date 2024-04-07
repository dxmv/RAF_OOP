import java.util.Objects;

public class Emoji implements Comparable<Emoji>{
    private String naziv;
    private String skracenica;

    public Emoji(String naziv, String skracenica) {
        this.naziv = naziv;
        this.skracenica = skracenica;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getSkracenica() {
        return skracenica;
    }

    public void setSkracenica(String skracenica) {
        this.skracenica = skracenica;
    }

    // pod 2.
    @Override
    public boolean equals(Object o) {
        // Emoji su isti ako imaju isti naziv ili skracenicu
        if(o instanceof Emoji e){
            return e.getNaziv().equals(this.naziv) || e.getSkracenica().equals(this.skracenica);
        }
        return false;
    }

    // pod 11.
    @Override
    public int compareTo(Emoji o) {
        int compare = this.naziv.compareTo(o.getNaziv());
        if(compare == 0){
            return this.skracenica.compareTo(o.getSkracenica());
        }
        return compare;
    }

    @Override
    public String toString() {
        return this.naziv + ", " + this.skracenica;
    }
}
