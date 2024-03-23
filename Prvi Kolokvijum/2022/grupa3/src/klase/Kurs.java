package klase;

import java.util.ArrayList;
import java.util.List;

// pod 5.
public class Kurs extends Obuka{
    private  boolean onlajn;

    private List<Kurs> preduslovi;
    public Kurs(String naziv, int minimalniBrojPolaznika, Oblast oblast,boolean onlajn) {
        super(naziv, minimalniBrojPolaznika, oblast);
        this.onlajn = onlajn;
        this.preduslovi = new ArrayList<>();
    }

    public boolean isOnlajn() {
        return onlajn;
    }

    public void setOnlajn(boolean onlajn) {
        this.onlajn = onlajn;
    }

    public List<Kurs> getPreduslovi() {
        return preduslovi;
    }

    public void setPreduslovi(List<Kurs> preduslovi) {
        this.preduslovi = preduslovi;
    }

    // pod 9.
    @Override
    public String toString() {
        return this.getNaziv() + " - " + this.getObukePolaznika().size() + " - Kurs - " + onlajn;
    }
}
