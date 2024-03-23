package klase;

import java.util.ArrayList;
import java.util.List;

// pod 3.
public class Profesor implements Registracija{
    private String ime;
    private String prezime;
    private String id;
    private Oblast oblast;

    private List<Obuka> obuke;

    public Profesor(String ime, String prezime, String id, Oblast oblast) {
        this.ime = ime;
        this.prezime = prezime;
        this.id = id;
        this.oblast = oblast;
        this.obuke = new ArrayList<>();
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Oblast getOblast() {
        return oblast;
    }


    public void setOblast(Oblast oblast) {
        this.oblast = oblast;
    }

    // pod 6.

    @Override
    public boolean registruj(Obuka obuka) {
        // da bi profesor bio registrovan mora da bude iz iste oblasti kao i obuka
        // da nije već registrovan za obuku
        // da nije registrovan na više od 3 obuke do sada
        if(!this.oblast.equals(obuka.getOblast()) || this.obuke.contains(obuka) || this.obuke.size() >= 3){
            return false;
        }

        // Ukoliko je moguća registracija, profesoru se dodaje nova obuka, a obuci predavač
        this.obuke.add(obuka);
        obuka.dodajPredavaca(this);
        return true;
    }

    @Override
    public boolean registrovan(Obuka obuka) {
        // vraća true ako je profesor već registrovan na prosleđenu obuku, odnosno false ako nije
        return this.obuke.contains(obuka);
    }
}
