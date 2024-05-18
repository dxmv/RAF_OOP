package app.controllers;


import app.model.StatistikaTermin;
import app.model.Termin;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

// U ovoj klasi obradjujemo podatke
public class Kontekst {
    public final static String IME_FAJLA = "rasporedPoNastavnicima-grupa2.txt";
    private List<Termin> termini;
    private List<StatistikaTermin> statistika;

    private List<String> ucionice;

    public Kontekst(){
        this.termini = this.citanjeFajla();
        this.statistika = this.statistikaTermina();
        this.ucionice = listaUcionica();
    }

    private List<Termin> citanjeFajla(){
        List<Termin> res = new ArrayList<>();
        try(Scanner sc = new Scanner(new File(IME_FAJLA))){
            String trenutniNastavnik = null;
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                // razlikujemo linije koje imaju samo ime nastavnika, i linije koje sadrze informacije o terminu
                // razlika je to sto u liniji gde je ime nastavnika nemamo ','
                if(!line.contains(",")){
                    trenutniNastavnik = line.trim();
                }

                // Interakcija covek-racunar,Predavanja,SRE,Raf21,9-11,301 302 303 304a 304b 305 306 307 308
                else{
                    String[] delovi = line.split(",");
                    if(trenutniNastavnik != null && delovi.length == 6){
                        // set grupa za ovaj termin, koristimo set zbog brzine
                        Set<String> grupe = new HashSet<>(Arrays.stream(delovi[5].split(" ")).toList());
                        String[] vremena = delovi[4].split("-");
                        Termin t = new Termin(trenutniNastavnik,delovi[0],delovi[1],delovi[2],delovi[3],Integer.parseInt(vremena[0]),Integer.parseInt(vremena[1]),grupe);
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

    // vraca listu ucioica iz liste termina, potrebno za combo box
    private List<String> listaUcionica(){
        Set<String> uc = new TreeSet<>();
        for(Termin t:termini){
            uc.add(t.getUcionica());
        }
        return uc.stream().toList();
    }

    private List<StatistikaTermin> statistikaTermina(){
        List<StatistikaTermin> res = new ArrayList<>();
        // prodji kroz sve termine
        for (Termin t:termini){
            // prodji kroz sve grupe termina
            for(String s:t.getGrupe()){
                // kreiraj novi objekat i gledaj da li se nalazi u listi
                StatistikaTermin st = new StatistikaTermin(s,t.getDanUNedelji(),1);
                int index = res.indexOf(st);
                if(index == -1){
                    res.add(st);
                }
                else{
                    res.get(index).setBrojCasova(res.get(index).getBrojCasova() + 1);
                }
            }
        }
        res.sort(Comparator.comparing(StatistikaTermin::getGrupa).thenComparing(StatistikaTermin::danNedelje));
        return res;
    }


    public List<String> getUcionice() {
        return ucionice;
    }

    public void setUcionice(List<String> ucionice) {
        this.ucionice = ucionice;
    }

    public List<Termin> getTermini() {
        return termini;
    }

    public void setTermini(List<Termin> termini) {
        this.termini = termini;
    }

    public List<StatistikaTermin> getStatistika() {
        return statistika;
    }

    public void setStatistika(List<StatistikaTermin> statistika) {
        this.statistika = statistika;
    }
}
