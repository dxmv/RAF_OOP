package klase;

// pod 3.
public class ProzniPisac extends Pisac{

    public ProzniPisac(String ime, String prezime, int godinaRodjenja) {
        super(ime, prezime, godinaRodjenja);
    }

    @Override
    public Knjizevnost objavljuje(String naziv, int godina, TipKnjizevnosti tipKnjizevnosti) {
        // na osnovu naziva, godine, kao i samog objekta nad kojim se poziva,
        // kreira i vraća instancu klase Knjizevnost koja za TipKnjizevnosti ima Epiku
        AutorskaKnjizevnost ak = new AutorskaKnjizevnost(naziv,TipKnjizevnosti.EPIKA);
        ak.setGodina(godina);
        ak.setAutor(this);
        return ak;
    }
}
