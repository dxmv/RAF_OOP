import java.util.ArrayList;
import java.util.List;

// pod 6.
public class PitanjeSpajanjeOdgovora extends Pitanje{
    private List<Spajanje> spajanje;
    private List<String> odgovori;

    public PitanjeSpajanjeOdgovora(String tekstPitanja) {
        super(tekstPitanja);
        this.spajanje = new ArrayList<>();
        this.odgovori = new ArrayList<>();
    }

    public List<Spajanje> getSpajanje() {
        return spajanje;
    }

    public void setSpajanje(List<Spajanje> spajanje) {
        this.spajanje = spajanje;
    }

    public List<String> getOdgovori() {
        return odgovori;
    }

    public void setOdgovori(List<String> odgovori) {
        this.odgovori = odgovori;
    }

    public void dodajPojam(String pojam){
        if(indexPojma(pojam) != -1){ // pojam se nalazi u listi
            return;
        }
        Spajanje s = new Spajanje(pojam);
        spajanje.add(s);
    }

    private int indexPojma(String pojam){
        for(int i=0;i<spajanje.size();i++){
            // gledamo da li se pojam vec nalazi u listi
            if(spajanje.get(i).getPojam().equals(pojam)){
                return i;
            }
        }
        return -1;
    }

    public void dodajOdgovor(String pojam,String odgovor){
        // ne smemo imati dva ista odgovora, takodje ne mozemo dodati odgovor za pojam koji nije u listi
        int index = indexPojma(pojam);
        if(odgovori.contains(odgovor) && index == -1){
            return;
        }
        // takodje mozemo imati samo onoliko odgovora koliko poena nosi pitanje
        if(odgovori.size() >= this.getBrojPoena()){
            return;
        }
        spajanje.get(index).dodajOdgovor(odgovor);
        odgovori.add(odgovor);
    }

    @Override
    public int brojPoena(String odgovor) {
        // odgovor se sastoji od redova koji sadrze u sebi dva broja, i razdovojeni su ';', prvi broj, predstavlja index pojma, dok drugi broj predstavlja index odgovora
        // npr. 1 3; znaci da gledamo prvi pojam i 3 odgovor
        int rez = 0;
        for(String red:odgovor.split(";")){
            String[] splitovano = red.split(" ");
            int pojamIndex = Integer.parseInt(splitovano[0]);
            int odgovorIndex = Integer.parseInt(splitovano[1]);
            if(spajanje.get(pojamIndex-1).spoji(odgovori.get(odgovorIndex-1))){
                rez++;
            }
        }
        return rez;
    }
}
