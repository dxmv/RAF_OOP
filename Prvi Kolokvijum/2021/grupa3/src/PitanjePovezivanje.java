import java.util.ArrayList;
import java.util.List;

public class PitanjePovezivanje extends Pitanje{
    private List<String> prvaKolona;
    private List<String> drugaKolona;
    private int brojPraznihMesta;


    public PitanjePovezivanje(String tekstPitanja) {
        super(tekstPitanja);
        this.prvaKolona = new ArrayList<>();
        this.drugaKolona = new ArrayList<>();
    }

    public List<String> getPrvaKolona() {
        return prvaKolona;
    }

    public void setPrvaKolona(List<String> prvaKolona) {
        this.prvaKolona = prvaKolona;
    }

    public List<String> getDrugaKolona() {
        return drugaKolona;
    }

    public void setDrugaKolona(List<String> drugaKolona) {
        this.drugaKolona = drugaKolona;
    }

    public int getBrojPraznihMesta() {
        return brojPraznihMesta;
    }

    public void setBrojPraznihMesta(int brojPraznihMesta) {
        this.brojPraznihMesta = brojPraznihMesta;
    }

    // pod 2.
    @Override
    public String vratiZaStampu() {
        // za pitanje sa povezivanjem ispisuju se redom vrednosti iz obe kolone, jedna pored druge sa razmakom od
        // određenog broja praznih mesta (spejsova) koji je upisan kao vrednost polja
        StringBuilder sb = new StringBuilder(super.vratiZaStampu() + "\n");
        for (int i = 0; i < prvaKolona.size(); i++) {
            String prva = prvaKolona.get(i);
            String druga = drugaKolona.get(i);
            sb.append(prva);
            // dodavanje praznih mesta
            for (int j = 0; j < brojPraznihMesta; j++) {
                sb.append(" ");
            }
            sb.append(druga + "\n");
        }
        return sb.toString();
    }

    @Override
    public boolean spremnoZaStampu() {
        // pitanje sa spajanjem je spremno, ako je broj elemenata u obe kolone jednak i veći od 1
        return super.spremnoZaStampu() && prvaKolona.size()>=1 && drugaKolona.size()>=1;
    }
}
