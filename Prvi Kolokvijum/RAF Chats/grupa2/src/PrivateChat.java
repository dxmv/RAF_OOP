import java.util.ArrayList;
import java.util.List;

public class PrivateChat implements Chat{
    private List<String> poruke;

    private Korisnik k1;
    private Korisnik k2;
    private Korisnik prethodniPosiljalac;

    public PrivateChat(Korisnik k1, Korisnik k2, Korisnik prethodniPosiljalac) {
        this.k1 = k1;
        this.k2 = k2;
        this.prethodniPosiljalac = prethodniPosiljalac;
        this.poruke = new ArrayList<>();
    }

    public List<String> getPoruke() {
        return poruke;
    }

    public void setPoruke(List<String> poruke) {
        this.poruke = poruke;
    }

    public Korisnik getK1() {
        return k1;
    }

    public void setK1(Korisnik k1) {
        this.k1 = k1;
    }

    public Korisnik getK2() {
        return k2;
    }

    public void setK2(Korisnik k2) {
        this.k2 = k2;
    }

    public Korisnik getPrethodniPosiljalac() {
        return prethodniPosiljalac;
    }

    public void setPrethodniPosiljalac(Korisnik prethodniPosiljalac) {
        this.prethodniPosiljalac = prethodniPosiljalac;
    }

    // pod 2.
    @Override
    public boolean mozeNapisatiPoruku(Korisnik korisnik) {
        // uslov u klasi PrivatniChat jeste da je pošiljalac k1 ili k2.
        // Pomenuti korisnici poruke moraju slati naizmenično,
        // što znači da ako je prethodni pošiljalac bio k2, sada poruku može slati samo k1, i obratno
        if(prethodniPosiljalac == null){
            return korisnik.equals(k1) || korisnik.equals(k2);
        }
        if(k1.equals(korisnik)){
            return prethodniPosiljalac.equals(k2);
        }
        else if(k2.equals(korisnik)){
            return prethodniPosiljalac.equals(k1);
        }
        return false; // nije jednak ni k1, ni k2
    }

    @Override
    public void dodajPoruku(Korisnik korisnik, String poruka) {
        // poruka se u PrivateChat dodaje samo pamćenjem poruke i prethodnog pošiljaoca
        if(mozeNapisatiPoruku(korisnik)){
            prethodniPosiljalac = korisnik;
            poruke.add(poruka);
        }
        else{
            ChatApp.NEUSPESNE_PORUKE.add(poruka + " - Poslato od: "+korisnik.getKorisnickoIme());
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(k1.getKorisnickoIme()+", ");
        sb.append(k2.getKorisnickoIme());
        sb.append("\n");
        for(String s : this.poruke){
            sb.append("\t").append(s).append("\n");
        }
        sb.append("\n\n");
        return sb.toString();
    }
}
