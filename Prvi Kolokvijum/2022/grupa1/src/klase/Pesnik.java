package klase;

// pod 3.
public class Pesnik extends Pisac{
    public Pesnik(String ime, String prezime, int godinaRodjenja) {
        super(ime, prezime, godinaRodjenja);
    }

    @Override
    public Knjizevnost objavljuje(String naziv, int godina, TipKnjizevnosti tipKnjizevnosti) {
        // na osnovu naziva, godine, kao i samog objekta nad kojim se poziva,
        // kreira i vraća instancu klase Knjizevnost koja za TipKnjizevnosti ima Liriku
        AutorskaKnjizevnost ak = new AutorskaKnjizevnost(naziv,TipKnjizevnosti.LIRIKA);
        ak.setGodina(godina);
        ak.setAutor(this);
        return ak;
    }
}
