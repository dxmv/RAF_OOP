import java.util.ArrayList;
import java.util.List;

public class PitanjeSlobodanOdgovor extends Pitanje{
    private List<String> kljucneReci;

    public PitanjeSlobodanOdgovor(String tekstPitanja) {
        super(tekstPitanja);
        this.kljucneReci = new ArrayList<>();
    }

    public List<String> getKljucneReci() {
        return kljucneReci;
    }

    // pod 2.
    public void setKljucneReci(List<String> kljucneReci) {
        // broj ključnih reči mora biti jednak broju poena koje nosi pitanje
        if(kljucneReci.size() != this.getBrojPoena()){
            return;
        }
        this.kljucneReci = kljucneReci;
    }

    // pod 3.
    @Override
    public int brojPoena(String odgovor) {
        // treba da proveri koliko ključnih reči se nalazi u odgovoru i taj broj se vraća kao rezultat
        int rez = 0;
        for(String rec:odgovor.split(" ")){
            if(kljucneReci.contains(rec)){
                rez++;
            }
        }
        return Math.min(rez, this.getBrojPoena()); // ako imamo dosta ponavljana kljucnih reci rez ce biti vece od broj poena za pitanje
    }
}
