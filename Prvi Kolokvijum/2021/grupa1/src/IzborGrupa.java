
import java.util.ArrayList;
import java.util.List;

public class IzborGrupa implements IzborStudenata{
    private String oznaka;
    private List<Predmet> predmeti;

    private OznakaPlana plan;

    public IzborGrupa() {
        this.predmeti = new ArrayList<>();
    }

    public IzborGrupa(String oznaka, OznakaPlana plan) {
        this.predmeti = new ArrayList<>();
        this.oznaka = oznaka;
        this.plan = plan;
    }

    public String getOznaka() {
        return oznaka;
    }

    public void setOznaka(String oznaka) {
        this.oznaka = oznaka;
    }

    public List<Predmet> getPredmeti() {
        return predmeti;
    }

    public void setPredmeti(List<Predmet> predmeti) {
        this.predmeti = predmeti;
    }

    public OznakaPlana getPlan() {
        return plan;
    }

    public void setPlan(OznakaPlana plan) {
        this.plan = plan;
    }

    // pod 4.

    public boolean dodajPredmet(Predmet izborniPredmet){
        // svi predmeti u izbornoj grupi moraju biti različiti
        if(predmeti.contains(izborniPredmet)){
            return false;
        }

        // dodaje predmet u grupu:
        // ako se predmet sluša u semestru koji odgovara godini studija kojoj pripada grupa
        // ako pripada istom planu
        return predmetOdgovaraGodini(izborniPredmet) && this.plan.equals(izborniPredmet.getOznakaPlana());
    }

    private boolean predmetOdgovaraGodini(Predmet p){
        // izborna grupa ima oznaku čija prva cifra odgovara godini studija (301 - 3. godina)
        int trenutnaGodina = this.oznaka.charAt(0);
        return trenutnaGodina == p.getGodina();
    }

    @Override
    public boolean mozeDaIzabere(Student student) {
        // treba da vrati true
        // ako student nije ponovac
        // ako je na odgovarajućoj godini studija kojoj pripada grupa
        // ako pripada istom planu
        // ako je položio sve preduslove svih predmeta iz izborne grupe
        return !student.jePonovac() &&
                studentNaOdgovarajucojGodini(student) &&
                this.plan.equals(student.getPlan()) &&
                studentPolozioSvePreduslove(student);
    }

    private boolean studentNaOdgovarajucojGodini(Student s){
        return Character.getNumericValue(this.oznaka.charAt(0)) == s.vratiTrenutnuGodinuStudija();
    }

    private boolean studentPolozioSvePreduslove(Student s){
        for(Predmet p : predmeti){
            if(!p.studentPolozioSvePreduslove(s)){
                return false;
            }
        }
        return true;
    }
}
