package klase;

import java.util.ArrayList;
import java.util.List;

public class ObnovaGodine extends AktivnostStudenta{

    private int godinaObnove;
    private List<Predmet> predmetiKojeUpisuje;

    public ObnovaGodine(int godina, int mesec, int dan) {
        super(godina, mesec, dan);
        this.predmetiKojeUpisuje = new ArrayList<>();
    }

    public int getGodinaObnove() {
        return godinaObnove;
    }

    public void setGodinaObnove(int godinaObnove) {
        this.godinaObnove = godinaObnove;
    }

    public List<Predmet> getPredmetiKojeUpisuje() {
        return predmetiKojeUpisuje;
    }

    public void setPredmetiKojeUpisuje(List<Predmet> predmetiKojeUpisuje) {
        this.predmetiKojeUpisuje = predmetiKojeUpisuje;
    }

    // pod 3.
    @Override
    public boolean proveriUslov(Student student) {
        // da li je student upisan u godinu koju obnavlja
        // (ako mu je poslednja aktivnost upis ili obnova iste te godine)
        return student.getAktivnosti().getLast().getGodinaStudija() == this.getGodinaStudija();
    }

    @Override
    public int getGodinaStudija() {
        return godinaObnove;
    }

    // pod 6.


    @Override
    public String toString() {
        return super.toString() + " - Obnova Godine - " + this.godinaObnove;
    }
}
