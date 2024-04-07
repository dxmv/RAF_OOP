import java.util.ArrayList;
import java.util.List;

// pod 9.
public class Chat {
    private List<Poruka> poruke;
    private List<String> posiljaoci;

    public Chat() {
        this.poruke = new ArrayList<>();
        this.posiljaoci = new ArrayList<>();
    }

    public List<Poruka> getPoruke() {
        return poruke;
    }

    public void setPoruke(List<Poruka> poruke) {
        this.poruke = poruke;
    }

    public List<String> getPosiljaoci() {
        return posiljaoci;
    }

    public void setPosiljaoci(List<String> posiljaoci) {
        this.posiljaoci = posiljaoci;
    }

    public void dodajPoruku(Poruka p){
        // ova metoda dodaje poruku u listi i posiljaoca, ako je ovo prva poruka posiljaoca
        poruke.add(p);
        if(!posiljaoci.contains(p.getPosiljalac())){
            posiljaoci.add(p.getPosiljalac());
        }
    }

    public void ispisPoruka(){
        for(Poruka p : poruke){
            System.out.println(p.formirajIspis());
        }
    }
}
