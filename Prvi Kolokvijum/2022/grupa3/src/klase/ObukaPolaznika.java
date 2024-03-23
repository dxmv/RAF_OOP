package klase;

// pod 6.
public class ObukaPolaznika {
    private static int poslednjiBroj;
    private int registracioniBroj;

    private Polaznik polaznik;

    private Obuka obuka;

    public ObukaPolaznika(Polaznik polaznik) {
        this.polaznik = polaznik;
    }

    public static int getPoslednjiBroj() {
        return poslednjiBroj;
    }

    public static void setPoslednjiBroj(int poslednjiBroj) {
        ObukaPolaznika.poslednjiBroj = poslednjiBroj;
    }

    public int getRegistracioniBroj() {
        return registracioniBroj;
    }

    public void setRegistracioniBroj(int registracioniBroj) {
        this.registracioniBroj = registracioniBroj;
    }

    public Polaznik getPolaznik() {
        return polaznik;
    }

    public void setPolaznik(Polaznik polaznik) {
        this.polaznik = polaznik;
    }

    public Obuka getObuka() {
        return obuka;
    }

    public void setObuka(Obuka obuka) {
        this.obuka = obuka;
    }
}
