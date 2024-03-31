package klase;

// pod 2.
public class AutorskaKnjizevnost extends Knjizevnost{
    private int godina;
    private Pisac autor;
    public AutorskaKnjizevnost(String naziv, TipKnjizevnosti tipKnjizevnosti) {
        super(naziv, tipKnjizevnosti);
    }




    // Getters And Setters
    public int getGodina() {
        return godina;
    }

    public void setGodina(int godina) {
        this.godina = godina;
    }

    public Pisac getAutor() {
        return autor;
    }

    public void setAutor(Pisac autor) {
        this.autor = autor;
    }

    // pod 5.
    @Override
    public String toString() {
        return this.getNaziv() + ", Autor: " + this.autor.toString();
    }
}
