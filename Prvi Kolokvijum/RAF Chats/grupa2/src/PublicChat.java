import java.util.ArrayList;
import java.util.List;

public class PublicChat implements Chat{
    private List<String> poruke;

    public PublicChat() {
        this.poruke = new ArrayList<>();
    }

    public List<String> getPoruke() {
        return poruke;
    }

    public void setPoruke(List<String> poruke) {
        this.poruke = poruke;
    }

    // pod 2.
    @Override
    public boolean mozeNapisatiPoruku(Korisnik korisnik) {
        // za public chat nema uslova
        return true;
    }

    // pod 3.
    @Override
    public void dodajPoruku(Korisnik korisnik, String poruka) {
        // prilikom dodavanja poruke u PublicChat potrebno je novi element liste formirati na sledeći način:
        // [prikazano ime korisnika] ==> [poruka]
        if(mozeNapisatiPoruku(korisnik)){
            poruke.add(korisnik.getPrikazanoIme() + " ===> " + poruka);
        }
        else{
            ChatApp.NEUSPESNE_PORUKE.add(poruka + " - Poslato od: "+korisnik.getKorisnickoIme());
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(String s : this.poruke){
            sb.append("\t").append(s).append("\n");
        }
        sb.append("\n\n");
        return sb.toString();
    }
}
