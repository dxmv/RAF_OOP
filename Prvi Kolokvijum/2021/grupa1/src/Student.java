
import java.util.*;

public class Student {
    private int brojIndeksa;
    private int godinaUpisa;
    private OznakaPlana plan;
    private List<Integer> upisaneGodine;
    private List<Predmet> polozeniPredmeti;

    public Student(OznakaPlana oznakaPlana,int brojIndeksa,int godinaUpisa){
        this.plan = oznakaPlana;
        this.brojIndeksa = brojIndeksa;
        this.godinaUpisa = godinaUpisa;
        this.upisaneGodine = new ArrayList<>();
        this.polozeniPredmeti = new ArrayList<>();
    }

    // pod 2.
    public int vratiTrenutnuGodinuStudija(){
        // treba da vrati poslednju upisanu godinu
        return this.upisaneGodine.isEmpty()?this.godinaUpisa:this.upisaneGodine.get(upisaneGodine.size() - 1);
    }

    public boolean jePonovac(){
        //  treba da vrati true ako je poslednja upisana godina upisana više od jednom
        if(upisaneGodine.isEmpty()){
            return false;
        }
        int poslednjaGodina = this.vratiTrenutnuGodinuStudija();
        return upisaneGodine.indexOf(poslednjaGodina) != upisaneGodine.lastIndexOf(poslednjaGodina);
    }

    // pod 3.
    public boolean polozioPredmet(Predmet p){
        return this.polozeniPredmeti.contains(p);
    }

    public boolean predmetUTrenutnojGodini(Predmet p){
        return this.vratiTrenutnuGodinuStudija() == p.getGodina();
    }

    public int getBrojIndeksa() {
        return brojIndeksa;
    }

    public void setBrojIndeksa(int brojIndeksa) {
        this.brojIndeksa = brojIndeksa;
    }

    public int getGodinaUpisa() {
        return godinaUpisa;
    }

    public void setGodinaUpisa(int godinaUpisa) {
        this.godinaUpisa = godinaUpisa;
    }

    public List<Integer> getUpisaneGodine() {
        return upisaneGodine;
    }

    public void setUpisaneGodine(List<Integer> upisaneGodine) {
        this.upisaneGodine = upisaneGodine;
    }

    public OznakaPlana getPlan() {
        return plan;
    }

    public void setPlan(OznakaPlana plan) {
        this.plan = plan;
    }

    public List<Predmet> getPolozeniPredmeti() {
        return polozeniPredmeti;
    }

    public void setPolozeniPredmeti(List<Predmet> polozeniPredmeti) {
        this.polozeniPredmeti = polozeniPredmeti;
    }

    // pod 8.
    public void dodajPolozeniPredmet(Predmet p){
         if(p.getOznakaPlana().equals(this.plan)){
             this.polozeniPredmeti.add(p);
         }
    }
}
