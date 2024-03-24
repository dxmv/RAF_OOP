import java.util.ArrayList;
import java.util.List;

public class Ocenjivanje {
    private List<Ocenljivo> zaOcenjivanje;

    public Ocenjivanje() {
        this.zaOcenjivanje = new ArrayList<>();
    }

    public List<Ocenljivo> getZaOcenjivanje() {
        return zaOcenjivanje;
    }

    public void setZaOcenjivanje(List<Ocenljivo> zaOcenjivanje) {
        this.zaOcenjivanje = zaOcenjivanje;
    }

    // pod 5.
    public void dodaj(Ocenljivo o){
        this.zaOcenjivanje.add(o);
    }

    public void ispisRezultata(){
        // vrši ocenjivanje svih ocenljivih elemenata

        // ispisuje na standardni izlaz vrstu elementa koji se ocenjuje
        for(Ocenljivo o : zaOcenjivanje){
            // za odgovor se ispisuje tekst pitanja, vrsta pitanja, odgovor i broj poena
            // za test naziv testa i kandidata, broj ogovorenih pitanja i ukupan broj poena
            System.out.println(o);
        }
    }
}
