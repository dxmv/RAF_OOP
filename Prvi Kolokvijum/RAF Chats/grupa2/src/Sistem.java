import java.util.ArrayList;
import java.util.List;

// pod 7.
public class Sistem {
    private List<Korisnik> registrovani;
    private List<Korisnik> prijavljeni;
    
    public Sistem(){
        this.registrovani = new ArrayList<>();
        this.prijavljeni = new ArrayList<>();
    }

    // vraca boolean na osnovu toga da li korisnik moze da se registruje
    public boolean registruj(String prikazanoIme, String korisnickoIme, String lozinka1, String lozinka2){
        // Proverava se nekoliko uslova:
        // 1. da li je oba puta uneta ista lozinka
        if(!lozinka1.equals(lozinka2)){
            return false;
        }

        // 2. da li ne postoji korisnik sa prosleđenim korisničkim imenom
        if(indexSaKorisnickimImenom(korisnickoIme,registrovani) != -1){
            return false;
        }

        // 3. da li lozinka ima bar 8 karaktera
        if(lozinka1.length() < 8){
            return false;
        }

        // 4. da li postoji bar jedan broj u okviru lozinke
        if(!sadrziBroj(lozinka1)){
            return false;
        }
        // 5. da li lozinka sadrži i mala i velika slova
        if(!sadrziVelikaMalaSlova(lozinka1)){
            return false;
        }


        Korisnik noviKorisnik = new Korisnik(prikazanoIme, korisnickoIme, lozinka1);
        registrovani.add(noviKorisnik);
        return true;
    }

    public Korisnik prijava(String korisnickoIme,String lozinka){
        int indexPrijava = indexSaKorisnickimImenom(korisnickoIme,prijavljeni);
        if(indexPrijava != -1){ // korisnik je prijavljen
            return null;
        }
        int indexRegistracija = indexSaKorisnickimImenom(korisnickoIme,registrovani);
        if(indexRegistracija == -1){
            return null;
        }
        if(registrovani.get(indexRegistracija).getLozinka().equals(lozinka)){
            prijavljeni.add(registrovani.get(indexRegistracija));
            return registrovani.get(indexRegistracija);
        }
        return null;
    }

    private int indexSaKorisnickimImenom(String korisnickoIme, List<Korisnik> lista){
        for(int i = 0; i < lista.size(); i++){
            if(lista.get(i).getKorisnickoIme().equals(korisnickoIme)){
                return i;
            }
        }
        return -1;
    }

    private boolean sadrziBroj(String tekst){
        for (char c : tekst.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }

    private boolean sadrziVelikaMalaSlova(String tekst){
        boolean velika = false;
        boolean mala = false;
        for (char c : tekst.toCharArray()) {
            if (Character.isLowerCase(c)) {
                mala = true;
            }
            else if (Character.isUpperCase(c)) {
                velika = true;
            }
        }
        return velika && mala;
    }

    public List<Korisnik> getRegistrovani() {
        return registrovani;
    }

    public void setRegistrovani(List<Korisnik> registrovani) {
        this.registrovani = registrovani;
    }

    public List<Korisnik> getPrijavljeni() {
        return prijavljeni;
    }

    public void setPrijavljeni(List<Korisnik> prijavljeni) {
        this.prijavljeni = prijavljeni;
    }
}
