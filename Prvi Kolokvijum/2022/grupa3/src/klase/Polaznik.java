package klase;

import java.util.ArrayList;
import java.util.List;

// pod 2.
public class Polaznik implements Registracija{
    private String ime;
    private String prezime;
    private String email;

    private List<ObukaPolaznika> obuke;

    public Polaznik(String ime, String prezime, String email) {
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.obuke = new ArrayList<>();
    }

    @Override
    public String toString() {
        //  Polaznika ispisujemo tako što ispišemo ime i prezime i u zagradama njegov email
        return ime + " " + prezime + "[" + email + "]";
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<ObukaPolaznika> getObuke() {
        return obuke;
    }

    public void setObuke(List<ObukaPolaznika> obuke) {
        this.obuke = obuke;
    }

    @Override
    public boolean equals(Object obj) {
        // isti su ako imaju isti email
        if(obj instanceof Polaznik){
            Polaznik p = (Polaznik) obj;
            return this.email.equals(p.getEmail());
        }
        return false;
    }

    // pod 6.

    @Override
    public boolean registruj(Obuka obuka) {
        boolean moze = mozeIciNaObuku(obuka);
        if(!moze){
            return false;
        }
        /*
         Prilikom registracije polaznika na obuku, polazniku i obuci se dodaje nova instanca klase
        ObukaPolaznika sa jedinstvenim registracionim brojem koji se generiše na osnovu brojača
        poslednjiBroj koji počinje od 0
         */
        ObukaPolaznika obukaPolaznika = new ObukaPolaznika(this);
        ObukaPolaznika.setPoslednjiBroj(ObukaPolaznika.getPoslednjiBroj() + 1);
        obukaPolaznika.setRegistracioniBroj(ObukaPolaznika.getPoslednjiBroj());
        obukaPolaznika.setObuka(obuka);

        this.obuke.add(obukaPolaznika);
        obuka.dodajObukuPolaznika(obukaPolaznika);
        return true;
    }

    @Override
    public boolean registrovan(Obuka obuka) {
        // vraća true ako je polaznik odnosno profesor već registrovan na prosleđenu obuku, odnosno false ako nije
        return naObuci(obuka);
    }

    private boolean validanMejl(){
        // mejl je validan ako nije null i ako sadrzi '@'
        return email != null && email.contains("@");
    }

    private boolean naObuci(Obuka obuka){
        if(this.obuke.isEmpty()){
            return false;
        }
        // vraca true ako je polaznik na obuci, false ako nije
        for(ObukaPolaznika op:this.obuke){
            if(op == null){
                return false;
            }
            if(op.getObuka().equals(obuka)){
                return true;
            }
        }
        return false;
    }

    private boolean mozeIciNaObuku(Obuka obuka){
        // vraca true ako moze ici na obuku, false ako ne moze

        // da bismo polaznika registrovali na obuku potrebno je da polaznik ima validan email
        // i da nije vec registrovan na tu obuku
        if(!this.validanMejl() || this.naObuci(obuka)){
            return false;
        }
        boolean moze = true; // da li moze da ide na obuku
        if(obuka instanceof Kurs){
            moze = mozeIciNaKurs((Kurs) obuka);
        }
        else if(obuka instanceof Projekat){
            moze = mozeIciNaProjekat((Projekat) obuka);
        }
        return moze;
    }

    private boolean mozeIciNaKurs(Kurs k){
        // na kurs možemo registrovati polaznike koji su prošli sve kurseve koji su preduslovi tog kursa
        for(Kurs uslov:k.getPreduslovi()){
            if(!naObuci(uslov)){
                return false; // nije polozio preduslove
            }
        }
        return true;
    }

    private boolean mozeIciNaProjekat(Projekat p){
        // za projekat imamo maksimalan broj polaznika koji mora biti ispoštovan
        return p.getObukePolaznika().size() + 1 <= p.getMaksimalanBrojPolaznika();
    }
}
