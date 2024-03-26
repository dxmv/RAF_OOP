package klase;

import java.util.ArrayList;
import java.util.List;

public class UpisGodine extends AktivnostStudenta{
    private int godinaKojuUpisuje;

    private List<Predmet> prenetiPredmeti;
    public UpisGodine(int godina, int mesec, int dan) {
        super(godina, mesec, dan);
        this.prenetiPredmeti = new ArrayList<>();
    }

    public int getGodinaKojuUpisuje() {
        return godinaKojuUpisuje;
    }

    public void setGodinaKojuUpisuje(int godinaKojuUpisuje) {
        this.godinaKojuUpisuje = godinaKojuUpisuje;
    }

    public List<Predmet> getPrenetiPredmeti() {
        return prenetiPredmeti;
    }

    public void setPrenetiPredmeti(java.util.List<Predmet> prenetiPredmeti) {
        this.prenetiPredmeti = prenetiPredmeti;
    }

    // pod 3.
    @Override
    public boolean proveriUslov(Student student) {
        // proverava da li prosleđeni student zadovoljava uslov upisa,
        // a to je broj osvojenih espb na osnovu položenih predmeta studanta
        // za upis
        // u drugu godinu potrebno je 30,
        // u treću 90,
        // a u četvrtu 150 espb,
        // za prvu godinu ne postoji uslov
        int potrebanBroj = this.godinaKojuUpisuje == 1 ? 0
                : this.godinaKojuUpisuje == 2 ? 30
                : this.godinaKojuUpisuje == 3 ? 90 : 150;
        return student.getOsvojenihEspb() >= potrebanBroj;
    }

    @Override
    public int getGodinaStudija() {
        return godinaKojuUpisuje;
    }

    // pod 6.
    @Override
    public String toString() {
        return super.toString() + " - Upis Godine - " + this.godinaKojuUpisuje;

    }
}

