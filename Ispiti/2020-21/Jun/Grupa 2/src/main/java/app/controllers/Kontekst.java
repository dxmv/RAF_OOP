package app.controllers;


import app.model.Student;
import app.model.TerminPolaganja;
import app.model.Ucionica;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// U ovoj klasi obradjujemo podatke
public class Kontekst {

    private List<Ucionica> ucionice = new ArrayList<>();
    private List<TerminPolaganja> termini = new ArrayList<>();
    private final String IME_FAJLA_UCIONICE = "ucionice2.txt";
    private final String IME_FAJLA_RASPORED = "raspored2.txt";


    public Kontekst(){
        ucitajUcioniceIzFajla();
        ucitajRasporedIzFajla();
    }

    private void ucitajRasporedIzFajla(){
        try(Scanner sc = new Scanner(new File(IME_FAJLA_RASPORED))){
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                // RI,1,2020,Mitreski,Milan,12,RAF6
                if(!line.isEmpty()){
                    String[] delovi = line.split(",");
                    int indexUcionice = ucionice.indexOf(new Ucionica(delovi[6],1));
                    TerminPolaganja tp = new TerminPolaganja(ucionice.get(indexUcionice),Integer.parseInt(delovi[5]));

                    int indexTermina = termini.indexOf(tp);
                    Student s = new Student(delovi[0],Integer.parseInt(delovi[1]),Integer.parseInt(delovi[2]),delovi[3],delovi[4]);
                    // ako se ne nalazi u listi dodajemo
                    if(indexTermina == -1){
                        termini.add(tp);
                        tp.dodajStudenta(s);
                    }
                    // ako se nalazi u listi
                    else{
                        // i svakako na kraju dodajemo studenta u ovaj termin
                        termini.get(indexTermina).dodajStudenta(s);
                    }
                }
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void ucitajUcioniceIzFajla(){
        try(Scanner sc = new Scanner(new File(IME_FAJLA_UCIONICE))){
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                // RAF1-12
                if(!line.isEmpty()){
                    String[] delovi = line.split("-");
                    if(delovi.length == 2){
                        ucionice.add(new Ucionica(delovi[0],Integer.parseInt(delovi[1])));
                    }
                }
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<Ucionica> getUcionice() {
        return ucionice;
    }

    public void setUcionice(List<Ucionica> ucionice) {
        this.ucionice = ucionice;
    }

    public List<TerminPolaganja> getTermini() {
        return termini;
    }

    public void setTermini(List<TerminPolaganja> termini) {
        this.termini = termini;
    }
}
