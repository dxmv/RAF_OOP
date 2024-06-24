package app.models;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Kontekst {
    public final static Kontekst k = new Kontekst();
    private List<String> kontinenti = new ArrayList<>();

    private List<Drzava> drzave = new ArrayList<>();
    private List<Vanzemaljac> vanzemaljaci = new ArrayList<>();

    public final static String IME_FAJLA_CITANJE = "vanzemaljci.txt";
    public Kontekst(){
        citajFajl();
    }

    public void citajFajl(){
        try(Scanner sc = new Scanner(new File(IME_FAJLA_CITANJE))){
            String kontinent = ""; // trenutni kontinent
            boolean izgubljeni = false;
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                if(!line.isEmpty()){
                    // imamo nekoliko varijacija
                    // za kontinent npr. Kontinent: Evropa, ispod njega su drzave
                    if(line.contains(":")){ // ovo znaci da smo naisli na kontinent
                        String[] delovi = line.split(":");
                        kontinent = delovi[1].trim();
                        kontinenti.add(kontinent);
                    }
                    else if(line.equals("IZGUBLJENI")){
                        kontinent = "";
                        izgubljeni = true;
                    }
                    else if(line.equals("PRONADJENI")){
                        kontinent = "";
                        izgubljeni = false;
                    }
                    else if(!kontinent.isEmpty()) { // naisli smo na drzavu
                        drzave.add(new Drzava(line,kontinent));
                    }
                    // IZGUBLJENI/PRONADJENI, ispod su vanzemaljci (id i drzava ili kontinent)
                    else{ // vanzemaljac
                        String[] delovi = line.split(";");
                        Drzava d;
                        if (izgubljeni) { // imamo kontinent i drzavu
                           d = new Drzava(delovi[1]);
                        }
                        else { // imamo id i drzavu
                            d = drzave.stream()
                                    .filter(dr -> dr.getIme().equals(delovi[1].trim()))
                                    .findFirst()
                                    .orElseThrow(() -> new RuntimeException("Country not found: " + delovi[1]));
                        }
                        vanzemaljaci.add(new Vanzemaljac(Integer.parseInt(delovi[0]), d));
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Vanzemaljac> getVanzemaljaci() {
        return vanzemaljaci;
    }

    public void setVanzemaljaci(List<Vanzemaljac> vanzemaljaci) {
        this.vanzemaljaci = vanzemaljaci;
    }

    public List<String> getKontinenti() {
        return kontinenti;
    }

    public void setKontinenti(List<String> kontinenti) {
        this.kontinenti = kontinenti;
    }

    public List<Drzava> getDrzave() {
        return drzave;
    }

    public void setDrzave(List<Drzava> drzave) {
        this.drzave = drzave;
    }
}
