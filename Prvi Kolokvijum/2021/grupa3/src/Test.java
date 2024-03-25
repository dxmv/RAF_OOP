import java.util.ArrayList;
import java.util.List;

public class Test implements ZaStampanje{
    private List<Pitanje> pitanja;
    private String naziv;

    public Test() {
        this.pitanja = new ArrayList<>();
    }

    public List<Pitanje> getPitanja() {
        return pitanja;
    }

    public void setPitanja(List<Pitanje> pitanja) {
        this.pitanja = pitanja;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public boolean dodajPitanje(Pitanje p){
        // ne dodajemo pitanje ako vec postoji u testu
        if(this.pitanja.contains(p)){
            return false;
        }
        this.pitanja.add(p);
        return true;
    }

    // pod 2.
    @Override
    public String vratiZaStampu() {
        // za test treba da vrati string koji ima u jednoj liniji naslov testa
        // zatim u drugoj liniji ukupan broj poena koje test nosi
        StringBuilder sb = new StringBuilder(naziv + "\n" + this.ukupanBrojPoena() + "\n");
        for(Pitanje p : pitanja){
            sb.append(p.vratiZaStampu() + "\n\n");
        }
        return sb.toString();
    }

    private int ukupanBrojPoena(){
        int rez = 0;
        for(Pitanje p : pitanja){
            rez += p.getBrojPoena();
        }
        return rez;
    }

    @Override
    public boolean spremnoZaStampu() {
        // ako nema rupa u rednim brojevima pitanja i ako su sva njegova pitanja spremna za štampu
        return  nemaRupaRedniBrojevi() && this.svaPitanjaSpremna();
    }

    private boolean nemaRupaRedniBrojevi(){
        int ocekivaniRedniBroj = pitanja.getFirst().getRedniBroj() + 1; // ocekivani sledeci redni broj je redni broj 1. pitanja + 1
        for(int i=1;i<pitanja.size();i++,ocekivaniRedniBroj++){
            if(pitanja.get(i).getRedniBroj() != ocekivaniRedniBroj){
                return false;
            }
        }
        return true;
    }

    private boolean svaPitanjaSpremna(){
        for(Pitanje p:pitanja){
            if(!p.spremnoZaStampu()){
                return false;
            }
        }
        return true;
    }
}
