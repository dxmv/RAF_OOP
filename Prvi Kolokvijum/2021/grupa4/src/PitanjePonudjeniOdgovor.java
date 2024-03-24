import java.util.ArrayList;
import java.util.List;

public class PitanjePonudjeniOdgovor extends Pitanje{
    private List<String> ponudjeniOdgovori;
    private List<Integer> indexTacnih;
    public PitanjePonudjeniOdgovor(String tekstPitanja) {
        super(tekstPitanja);
        this.ponudjeniOdgovori = new ArrayList<>();
        this.indexTacnih = new ArrayList<>();
    }

    public List<String> getPonudjeniOdgovori() {
        return ponudjeniOdgovori;
    }

    public void setPonudjeniOdgovori(List<String> ponudjeniOdgovori) {
        this.ponudjeniOdgovori = ponudjeniOdgovori;
    }

    public List<Integer> getIndexTacnih() {
        return indexTacnih;
    }

    public void setIndexTacnih(List<Integer> indexTacnih) {
        this.indexTacnih = indexTacnih;
    }

    // pod 2.
    public void dodajPonudjeniOdgovor(String tekst, boolean tacan){
        // odgovor se ne može dodati ako već postoji u listi
        // broj tačnih odgovora ne može biti veći od broja poena koje pitanje nosi
        if(this.ponudjeniOdgovori.contains(tekst) || (tacan && this.indexTacnih.size() >= this.getBrojPoena())){
            return;
        }

        this.ponudjeniOdgovori.add(tekst);
        // ako je u pitanju tačan odgovor njegov indeks se čuva u listi tačnih odgovora
        if(tacan){
            this.indexTacnih.add(this.ponudjeniOdgovori.size()-1);
        }
    }

    // pod 3.
    @Override
    public int brojPoena(String odgovor) { // kao argument se prosleđuje string koji sadrži sve odgovore razdvojene zarezom
        int rez = 0;
        for(String odg:odgovor.split(",")){
            int index = ponudjeniOdgovori.indexOf(odg); // vraca index zadatog odgovora
            if(indexTacnih.contains(index)){ // odgovor je tacan ako se njegov index nalazi u indexu tacnih
                rez++;
            }
        }
        // treba da vrati broj tačnih odgovora
        return Math.min(rez,this.getBrojPoena()); // ako imamo vise ponavljana tacnih odgovora, rez ce biti vece od broj poena za pitanje
    }



}
