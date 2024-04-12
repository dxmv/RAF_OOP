import java.util.ArrayList;
import java.util.List;

public class GroupChat implements Chat{
    private List<String> poruke;
    private List<Korisnik> grupa;
    private List<Korisnik> posiljaoci;

    public GroupChat() {
        this.poruke = new ArrayList<>();
        this.grupa = new ArrayList<>();
        this.posiljaoci = new ArrayList<>();
    }

    public List<String> getPoruke() {
        return poruke;
    }

    public void setPoruke(List<String> poruke) {
        this.poruke = poruke;
    }

    public List<Korisnik> getGrupa() {
        return grupa;
    }

    public void setGrupa(List<Korisnik> grupa) {
        this.grupa = grupa;
    }

    public List<Korisnik> getPosiljaoci() {
        return posiljaoci;
    }

    public void setPosiljaoci(List<Korisnik> posiljaoci) {
        this.posiljaoci = posiljaoci;
    }

    // pod 2.
    @Override
    public boolean mozeNapisatiPoruku(Korisnik korisnik) {
        // uslov je da korisnik mora da se nalazi u grupi
        return this.grupa.contains(korisnik);
    }

    // pod 3.
    @Override
    public void dodajPoruku(Korisnik korisnik, String poruka) {
        // u listi pošiljalaca pamti se ko ju je poslao, a u listi poruka sama poruka
        if(mozeNapisatiPoruku(korisnik)){
            posiljaoci.add(korisnik);
            poruke.add(poruka);
        }
        else{
            ChatApp.NEUSPESNE_PORUKE.add(poruka + " - Poslato od: "+korisnik.getKorisnickoIme());
        }
    }

    // pod 5.
    public void dodajUGrupu(Korisnik korisnik){
        // može se izvršiti samo ako korisnik još uvek ne postoji u grupi
        if(!grupa.contains(korisnik)){
            grupa.add(korisnik);
        }
    }

    public void ukloniIzGrupe(Korisnik korisnik){
        grupa.remove(korisnik);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Korisnik k : this.grupa){
            sb.append(k.getKorisnickoIme()+", ");
        }
        sb.append("\n");
        for(String s : this.poruke){
            sb.append("\t").append(s).append("\n");
        }
        sb.append("\n\n");
        return sb.toString();
    }
}
