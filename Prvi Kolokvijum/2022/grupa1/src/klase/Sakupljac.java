package klase;

// pod 3.
public class Sakupljac implements Objavljivanje{
    private String pseudonim;

    public Sakupljac(String pseudonim) {
        this.pseudonim = pseudonim;
    }
    @Override
    public Knjizevnost objavljuje(String naziv, int godina, TipKnjizevnosti tipKnjizevnosti) {
        // ne uzima u obzir godinu koja se prosleđuje
        // samo na osnovu naziva, tipaKnjizevnosti i samog objekta nad kojim se poziva, kreira i vraća instancu klase Knjizevnost koja za
        // tipKnjizevnosti može da ima bilo koju od tri vrednosti i postavlja se vrednost prosleđenog argumenta.
        NarodnaKnjizevnost nk = new NarodnaKnjizevnost(naziv,tipKnjizevnosti);
        nk.setSakupljac(this);
        return nk;
    }


    // Getters And Setters
    public String getPseudonim() {
        return pseudonim;
    }

    public void setPseudonim(String pseudonim) {
        this.pseudonim = pseudonim;
    }

    @Override
    public String toString() {
        return this.pseudonim;
    }
}
