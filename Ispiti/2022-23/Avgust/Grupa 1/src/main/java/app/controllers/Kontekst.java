package app.controllers;


import app.model.StatistikaTermin;
import app.model.Termin;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

// U ovoj klasi obradjujemo podatke
public class Kontekst {
    public final static String IME_FAJLA = "rasporedPoPredmetima-grupa1.txt";
    private List<Termin> termini;

    public Kontekst(){
        this.termini = this.citanjeFajla();
    }

    private List<Termin> citanjeFajla(){
        List<Termin> res = new ArrayList<>();
        try(Scanner sc = new Scanner(new File(IME_FAJLA))){
            String trenutniPredmet = null;
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                // razlikujemo linije koje imaju samo ime predmeta, i linije koje sadrze informacije o terminu
                // razlika je to sto u liniji gde je ime predmeta nemamo ';'
                if(!line.contains(";")){
                    trenutniPredmet = line.trim();
                }

                else{
                    String[] delovi = line.split(";");
                    if(delovi.length == 7 && trenutniPredmet != null){
                        // set grupa za ovaj termin, koristimo set zbog brzine
                        Set<String> grupe = new HashSet<>(Arrays.stream(delovi[6].split(" ")).toList());
                        Termin t = new Termin(trenutniPredmet, delovi[0], delovi[1], delovi[2], Integer.parseInt(delovi[3]), Integer.parseInt(delovi[4]), delovi[5],grupe);
                        res.add(t);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        // sortiramo prvo po danu u nedelji
        // ako su dani isti onda po pocetku termina
        // ako u isto vreme pocinju, onda po vremenu zavrsetka
        res.sort(Comparator
                .comparing(Termin::danNedelje)
                .thenComparing(Termin::getPocetak)
                .thenComparing(Termin::getKraj));
        return res;
    }

    public List<String> grupeRasporeda(){
        Set<String> set = new HashSet<>();
        for(Termin t:termini){
            set.addAll(t.getGrupe());
        }
        return set.stream().sorted().collect(Collectors.toList());
    }

    public List<StatistikaTermin> statistikaTermina(){
        List<StatistikaTermin> res = new ArrayList<>();
        for(Termin t:termini){
            StatistikaTermin st = new StatistikaTermin(t.getUcionica(),t.getDanUNedelji(),1);
            int index = res.indexOf(st);
            // ako ne postoji dodajemo ga
            if(index == -1){
                res.add(st);
            }
            // ako postoji povecavamo broj casova
            else{
                res.get(index).setBrojCasova(res.get(index).getBrojCasova() + 1);
            }
        }
        res.sort(Comparator.comparing(StatistikaTermin::getBrojCasova).reversed());
        return res;
    }

    public List<Termin> getTermini() {
        return termini;
    }

    public void setTermini(List<Termin> termini) {
        this.termini = termini;
    }
}
