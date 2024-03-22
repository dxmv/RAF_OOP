package klase;

public class RadUPekari extends Aktivnost{

    public RadUPekari(String naziv) {
        super(naziv, Smer.PEKAR, 5, 10);
    }

    @Override
    public int izracunajOcenu(double brojPoena) {
        // Ocena se računa kao ceo deo od (broj poena * 0.75 – 1.75).
        return (int)(brojPoena * 0.75 - 1.75);
    }
}
