package klase;

public class Vizita extends Aktivnost{
    public Vizita(String naziv) {
        super(naziv, Smer.LEKAR, 1, 5);
    }

    @Override
    public int izracunajOcenu(double brojPoena) {
        // ocena se računa kao ceo deo od (broj poena * 0.75 + 1.25).
        return (int)(brojPoena * 0.75 + 1.25);
    }
}
