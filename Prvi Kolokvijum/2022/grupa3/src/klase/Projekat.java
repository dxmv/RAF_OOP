package klase;

// pod 5.
public class Projekat extends Obuka {
    private int maksimalanBrojPolaznika;
    public Projekat(String naziv, int minimalniBrojPolaznika, Oblast oblast, int maksimalanBrojPolaznika) {
        super(naziv, minimalniBrojPolaznika, oblast);
        this.maksimalanBrojPolaznika = maksimalanBrojPolaznika;
    }

    public int getMaksimalanBrojPolaznika() {
        return maksimalanBrojPolaznika;
    }

    public void setMaksimalanBrojPolaznika(int maksimalanBrojPolaznika) {
        this.maksimalanBrojPolaznika = maksimalanBrojPolaznika;
    }

    // pod 9.
    @Override
    public String toString() {
        return this.getNaziv() + " - " + this.getObukePolaznika().size() + " - Projekat - " + maksimalanBrojPolaznika;
    }
}
