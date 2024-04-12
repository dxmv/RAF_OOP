import java.util.ArrayList;
import java.util.List;

// pod 6.
public class JustMeChat implements Chat {
    private Korisnik korisnik;
    private List<String> poruke;

    public JustMeChat() {
        this.korisnik = null;
        this.poruke = new ArrayList<>();
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        if(this.korisnik == null){ // samo jedan korisnik ima pristup ovom cetu
            this.korisnik = korisnik;
        }
    }

    @Override
    public boolean mozeNapisatiPoruku(Korisnik korisnik) {
        return this.korisnik.equals(korisnik);
    }

    @Override
    public void dodajPoruku(Korisnik korisnik, String poruka) {
        if(mozeNapisatiPoruku(korisnik)){
            poruke.add(poruka);
        }
        else{
            ChatApp.NEUSPESNE_PORUKE.add(poruka + " - Poslato od: "+korisnik.getKorisnickoIme());
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(korisnik.getKorisnickoIme());
        sb.append("\n");
        for(String s : this.poruke){
            sb.append("\t").append(s).append("\n");
        }
        sb.append("\n\n");
        return sb.toString();
    }
}
