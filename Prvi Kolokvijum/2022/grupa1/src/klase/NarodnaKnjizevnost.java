package klase;

// pod 2.
public class NarodnaKnjizevnost extends Knjizevnost{
    private Sakupljac sakupljac;
    public NarodnaKnjizevnost(String naziv, TipKnjizevnosti tipKnjizevnosti) {
        super(naziv, tipKnjizevnosti);
    }



    // Getters And Setters
    public Sakupljac getSakupljac() {
        return sakupljac;
    }

    public void setSakupljac(Sakupljac sakupljac) {
        this.sakupljac = sakupljac;
    }

    // pod 5.
    @Override
    public String toString() {
        return this.getNaziv() + ", Sakupljac: " + this.sakupljac.toString();
    }
}
