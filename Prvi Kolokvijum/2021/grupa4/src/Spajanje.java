import java.util.ArrayList;
import java.util.List;

// ovo koristimo u pitanjespajanjeodgovora klasi
// imamo jedan pojam koji povezujemo sa jednim ili vise odgovora
public class Spajanje {
    private String pojam;
    private List<String> odgovori;

    public Spajanje(String pojam) {
        this.pojam = pojam;
        this.odgovori = new ArrayList<>();
    }

    public Spajanje(String pojam, List<String> odgovori) {
        this.pojam = pojam;
        this.odgovori = odgovori;
    }

    public String getPojam() {
        return pojam;
    }

    public void setPojam(String pojam) {
        this.pojam = pojam;
    }

    public List<String> getOdgovori() {
        return odgovori;
    }

    public void setOdgovori(List<String> odgovori) {
        this.odgovori = odgovori;
    }

    public void dodajOdgovor(String s){
        // dodaje odgovor, ako taj odgovor ne postoji u listi odgovora
        if(this.spoji(s)){
            return;
        }
        odgovori.add(s);
    }

    public boolean spoji(String s){
        // vraca true ako se s nalazi u odgovorima, false ako ne
        return this.odgovori.contains(s);
    }
}
