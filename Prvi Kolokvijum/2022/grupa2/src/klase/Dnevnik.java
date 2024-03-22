package klase;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Dnevnik implements ImaProsek{
    private List<Beleska> beleske;
    private List<Klasa> klase;
    private List<Aktivnost> aktivnosti;

    public Dnevnik() {
        this.beleske = new ArrayList<>();
        this.klase = new ArrayList<>();
        this.aktivnosti = new ArrayList<>();
    }

    public Klasa dodajKlasu(Smer smer, int razred, int odelenje, String razredni){
        Klasa k = new Klasa(razred,odelenje,razredni,smer);
        int index = this.indeksKlase(k);
        if(index == -1){
            klase.add(k);
            return k;
        }
        return klase.get(index);
    }

    // vraca sve Klase iz svoje kolekcije koje odgovaraju smeru zadatom u argumentu.
    public List<Klasa> nadjiKlaseZaSmer(Smer smer){
        List<Klasa> rez = new ArrayList<>();
        for(Klasa k:klase){
            if(k.getSmer().equals(smer)){
                rez.add(k);
            }
        }
        return rez;
    }

    public Ucenik dodajUcenika(String ime, String prezime, String jmbg, int razred, int odeljenje, Smer smer){
        Klasa k = this.dodajKlasu(smer,razred,odeljenje,"");
        Ucenik u = new Ucenik(ime,prezime,jmbg,k);
        return k.dodajUcenika(u) ? u : null;
    }

    public Aktivnost dodajAktivnost(Aktivnost aktivnost){
        int index = indexAktivnosti(aktivnost);
        if(index == -1){
            this.aktivnosti.add(aktivnost);
            return aktivnost;
        }
        return aktivnosti.get(index);
    }

    public Beleska dodajBelesku(Ucenik ucenik, Aktivnost aktivnost, int trajanje, String opis, double brojPoena, LocalDate datum){
        // učenik može učestvovati samo u aktivnosti predviđenoj za njegov smer
        if(!aktivnost.getSmer().equals(ucenik.getKlasa().getSmer())){
            return null;
        }
        // trajanje aktivnosti ne može biti kraće od 5 minuta
        if(trajanje < 5){
            return null;
        }
        Beleska b = new Beleska(ucenik,aktivnost,trajanje,opis,brojPoena, datum);
        beleske.add(b);
        ucenik.dodajBelesku(b);
        return b;
    }

    public int prosecnaOcenaAktivnostiUcenika(Ucenik u){
        int ukupno = 0, broj = 0;
        for(Beleska b:beleske){
            if(b.getUcenik().equals(u)){
                ukupno += b.getAktivnost().izracunajOcenu(b.getBrojPoena());
                broj++;
            }
        }
        return broj == 0 ? 0 : ukupno/broj;
    }

    public void ispisUFajl(){
        // sortiramo beleske po datumima
        String imeFajla = "ispis.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(imeFajla))) {
            for(Klasa k:klase){
                for(Ucenik u:k.getUcenici()){
                    writer.write(u.getIme() + " " + u.getPrezime() + "\n");
                    List<Beleska> bs = beleskeZaUcenika(u);
                    bs.sort(Comparator.comparing(Beleska::getDatum)); // sortrianje po datumu

                    if(bs.isEmpty()){
                        writer.write("„Nema zabelezenih aktivnosti“\n\n\n");
                    }
                    else{
                        int ukupanBrojPoena = 0;
                        for(Beleska b:bs){
                            writer.write(b.getDatum() + " - " + b.getAktivnost().getNaziv() + " - " + b.getBrojPoena() + "\n");
                        }
                        writer.write("Prosek ocena: " + prosecnaOcenaAktivnostiUcenika(u) + "\n" + "Ukupno poena: " + ukupanBrojPoena + "\n\n\n");
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
        }
    }

    private List<Beleska> beleskeZaUcenika(Ucenik u){
        // vraca sve beleske za ucenika
        List<Beleska> rez = new ArrayList<>();
        for(Beleska b:beleske){
            if(b.getUcenik().equals(u)){
                rez.add(b);
            }
        }
        return rez;
    }

    // vraca index trazene klase u listi 'klase'
    // ako ne postoji vraca -1
    private int indeksKlase(Klasa k){
        for(int i=0;i<klase.size();i++){
            if(klase.get(i).equals(k)){
                return i;
            }
        }
        return -1;
    }

    // vraca index trazene aktivnosti u listi 'aktivnosti'
    // ako ne postoji vraca -1
    private int indexAktivnosti(Aktivnost a){
        for(int i=0;i<aktivnosti.size();i++){
            if(aktivnosti.get(i).equals(a)){
                return i;
            }
        }
        return -1;
    }


    @Override
    public double prosek() {
        // prosek klase Dnevnik predstavlja prosečno trajanje svih beležaka o aktivnostima
        int ukupnoTrajanje=0;
        for(Beleska b:beleske){
            ukupnoTrajanje += b.getTrajanje();
        }
        return beleske.isEmpty() ? 0 : (double) ukupnoTrajanje / beleske.size();
    }
}
