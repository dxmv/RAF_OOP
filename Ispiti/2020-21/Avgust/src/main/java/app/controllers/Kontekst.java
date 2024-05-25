package app.controllers;


import app.model.Pitanje;
import app.model.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

// U ovoj klasi obradjujemo podatke
public class Kontekst {

    public final static String IME_FAJLA = "pitanja.txt";
    private Set<String> predmeti = new HashSet<>(); // koristimo set zbog brzine pristupa
    private List<Pitanje> pitanja = new ArrayList<>();
    private List<Test> testovi = new ArrayList<>();

    public Kontekst(){
        ucitajPitanjaPredmete(pitanja,predmeti);
    }

    private void ucitajPitanjaPredmete(List<Pitanje> pitanja, Set<String> predmeti){
        try(Scanner sc = new Scanner(new File(IME_FAJLA))) {
            while (sc.hasNextLine()){
                String line = sc.nextLine();
                if(!line.isEmpty()){
                    // Primer pitanja u fajlu
                    // Da li se u konstruktoru može koristiti rezervisana reč this?;Da;OOP
                    String[] delovi = line.trim().split(";");
                    if(delovi.length != 3){
                        continue;
                    }
                    Pitanje p = new Pitanje(delovi[0],delovi[1],delovi[2]);
                    // dodajemo pitanje u listu
                    pitanja.add(p);
                    // ako se predmet ne nalazi u listi dodajemo ga
                    if(!predmeti.contains(delovi[2])){
                        predmeti.add(delovi[2]);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public List<Pitanje> filtrirajPitanjaPoPredmetu(String predmet){
        // gledamo da li je selektovano "Svi predmeti", ako jeste onda samo ucitamo sve predmete
        if(predmet.equals("Svi predmeti")){
            return pitanja;
        }
        // u suprotnom filtriramo pitanja po predmetu
        return pitanja.stream().filter(el->el.getPredmet().equals(predmet)).toList();
    }

    public Set<String> getPredmeti() {
        return predmeti;
    }

    public void setPredmeti(Set<String> predmeti) {
        this.predmeti = predmeti;
    }

    public List<Pitanje> getPitanja() {
        return pitanja;
    }

    public void setPitanja(List<Pitanje> pitanja) {
        this.pitanja = pitanja;
    }

    public List<Test> getTestovi() {
        return testovi;
    }

    public void setTestovi(List<Test> testovi) {
        this.testovi = testovi;
    }
}
