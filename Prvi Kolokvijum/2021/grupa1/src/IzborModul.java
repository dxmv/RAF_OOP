import java.util.ArrayList;
import java.util.List;

// pod 5.
public class IzborModul implements IzborStudenata{
    private String naziv;

    private List<Predmet> predmeti;

    public IzborModul(String naziv) {
        this.naziv = naziv;
        this.predmeti = new ArrayList<>();
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public List<Predmet> getPredmeti() {
        return predmeti;
    }

    public void setPredmeti(List<Predmet> predmeti) {
        // sadrzi samo predmete sa 3 i 4 godine
        boolean mozeUbaciti = true;
        for(Predmet p : predmeti){
            if(p.getGodina() != 3 && p.getGodina()!=4){
                mozeUbaciti=false;
                break;
            }
        }
        if(mozeUbaciti){
            this.predmeti=predmeti;
        }
    }

    public void dodajPredmet(Predmet p){
        // sadrzi samo predmete sa 3 i 4 godine
        if(p.getGodina() == 3 || p.getGodina() == 4){
            this.predmeti.add(p);
        }
    }

    @Override
    public boolean mozeDaIzabere(Student student) {
        // izborni modul mogu da biraju
        // studenti 3. godine
        // koji nisu ponovci
        // ostvarili su određeni broj espb poena
        return student.vratiTrenutnuGodinuStudija() == 3 &&
                !student.jePonovac();
    }
}
