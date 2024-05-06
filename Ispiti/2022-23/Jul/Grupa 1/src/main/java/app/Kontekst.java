package app;


import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

// U ovoj klasi obradjujemo podatke
public class Kontekst {
    private final String IME_FAJLA = "vozila.txt";

    private List<Vozilo> svaVozila;

    private List<Tipovi> tipovi; // grupisanje vozila po tipovima

    private List<Rezervacija> rezervacije;


    public Kontekst(){
        svaVozila = citanjeVozilaIzFajla();
        tipovi = tipoviVozila();
        rezervacije = new ArrayList<>();
    }

    // vracamo listu vozila iz fajla
    private List<Vozilo> citanjeVozilaIzFajla(){
        List<Vozilo> rez = new ArrayList<>();
        try(Scanner sc = new Scanner(new File(IME_FAJLA))){
            // preskacemo prve dve linije fajla
            sc.nextLine();
            sc.nextLine();

            while (sc.hasNextLine()){
                // Toyota     RAV4                SUV          5                80.0
                while (sc.hasNextLine()){
                    String line = sc.nextLine();
                    // preskacemo prazan red
                    if(line.isEmpty()){
                        continue;
                    }
                    // spltujemo po razmacima i onda svaki deo trimujemo
                    String[] delovi = line.split("\\s+");
                    Vozilo v = new Vozilo(delovi[0].trim(),
                            delovi[1],
                            delovi[2],
                            Integer.parseInt(delovi[3]),
                            Double.parseDouble(delovi[4]));
                    rez.add(v);
                }
            }
        } catch (FileNotFoundException | NumberFormatException e) {
            e.printStackTrace();
        }
        return rez;
    }

    private List<Tipovi> tipoviVozila(){

        List<Tipovi> rez = new ArrayList<>();
        for(Vozilo v:svaVozila){
            int index = tipUListi(rez,v.getTip());
            // ako se nalazi u listi samo dodamo vozilo
            if(index != -1){
                rez.get(index).dodajVozilo(v);
            }
            // ako se ne nalazi pravimo novi tip
            else{
                rez.add(new Tipovi(v.getTip(),v));
            }
        }
        return rez;
    }

    private int tipUListi(List<Tipovi> l,String t){
        for(int i = 0; i<l.size();i++){
            if(l.get(i).getTip().equals(t)){
                return i;
            }
        }
        return -1;
    }

    public void dodajRezervaciju(Rezervacija r){
        rezervacije.add(r);
    }

    // vraca listu rezervacija filtriranu po datumu
    private List<Rezervacija> filterDatum(List<Rezervacija> rezervacije,LocalDate pocetak,LocalDate kraj){
        return rezervacije.stream().filter(el->el.rezervacijaURange(pocetak,kraj)).collect(Collectors.toList());
    }

    public List<Rezervacija> filtrirajRezervacije(String tipFilter, TextField pocetak, TextField kraj){
        List<Rezervacija> rez = rezervacije;
        // filtriramo po datumu ako nije prazno uneto
        if(!pocetak.getText().isEmpty() && !kraj.getText().isEmpty()){
            rez = filterDatum(this.rezervacije,GlavniProzor.vratiDatumIzTf(pocetak),GlavniProzor.vratiDatumIzTf(kraj));
        }

        // filter po tipu
        if(tipFilter == "Sve"){
            return rez;
        }
        return rez.stream().filter(el->el.getV().getTip().equals(tipFilter)).collect(Collectors.toList());
    }

    public HashMap<String,List<Rezervacija>> grupisiRezervacije(List<Rezervacija> lista){
        HashMap<String,List<Rezervacija>> rez = new HashMap<>();
        for(Rezervacija r:lista){
            if(rez.containsKey(r.getV().getTip())){
                rez.get(r.getV().getTip()).add(r);
            }
            else{
                List<Rezervacija> list = new ArrayList<>();
                list.add(r);
                rez.put(r.getV().getTip(),list);
            }
        }
        return rez;
    }

    public List<Rezervacija> getRezervacije() {
        return rezervacije;
    }

    public void setRezervacije(List<Rezervacija> rezervacije) {
        this.rezervacije = rezervacije;
    }

    public List<Vozilo> getSvaVozila() {
        return svaVozila;
    }

    public void setSvaVozila(List<Vozilo> svaVozila) {
        this.svaVozila = svaVozila;
    }

    public List<Tipovi> getTipovi() {
        return tipovi;
    }

    public void setTipovi(List<Tipovi> tipovi) {
        this.tipovi = tipovi;
    }
}
