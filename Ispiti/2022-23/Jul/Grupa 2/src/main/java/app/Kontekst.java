package app;


import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

// U ovoj klasi obradjujemo podatke
public class Kontekst {
    private List<Osoba> osobe;
    private List<RadniZadatak> zadaci;

    private List<Akcija> akcije;
    private List<RadniZadatak> zavrseno;
    private List<Akcija> naCekanju;

    public Kontekst(){
        osobe = ucitavanjeOsoba();
        zadaci = ucitavanjeZadataka();
        akcije = new ArrayList<>();
        zavrseno = new ArrayList<>();
        naCekanju = new ArrayList<>();
    }

    // citanje osoba
    private List<Osoba> ucitavanjeOsoba(){
        List<Osoba> rez = new ArrayList<>();
        try (Scanner sc = new Scanner(new File(Osoba.IME_FAJLA))) {
            // primer: Marko Markovic
            while (sc.hasNextLine()){
                String line = sc.nextLine();
                Osoba o = new Osoba(line);
                rez.add(o);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return rez;
    }

    // citanje radnih zadataka
    private List<RadniZadatak> ucitavanjeZadataka(){
        List<RadniZadatak> rez = new ArrayList<>();
        try (Scanner sc = new Scanner(new File(RadniZadatak.IME_FAJLA))) {
            // primer: Reklamiranje:Analiza trzista;Trazenje potencijalnih klijenata
            while (sc.hasNextLine()){
                String line = sc.nextLine();
                String[] delovi = line.split(":");
                RadniZadatak o = new RadniZadatak(delovi[0]);
                // da li ima preduslove
                if(delovi.length > 1){
                    String[] preduslovi = delovi[1].split(";");
                    for(String s:preduslovi){
                        RadniZadatak rz = new RadniZadatak(s);
                        int index = rez.indexOf(rz);
                        if(index != -1){
                            // dodaj preduslove
                            o.getPredusloviOvogZadatka().add(rez.get(index));
                            rez.get(index).getPreduslovi().add(o);
                        }
                    }
                }
                rez.add(o);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return rez;
    }

    public void dodajAkciju(RadniZadatak rz,List<Osoba> osobe,int brojSati,int uradjeno){
        Akcija a = new Akcija(rz,osobe,brojSati,uradjeno);
        // provera da li su svi preduslovi uradjeni
        if(!rz.ispunjeniUslovi(zavrseno)){
            // ako nisu ovu akciju dodajemo na cekanje
            naCekanju.add(a);
            return;
        }
        int index = akcije.indexOf(a);
        if(index == -1){
            // ako se ne nalazi samo dodaj akciju
            akcije.add(a);
        }
        else{
            // ako se nalazi povecaj uradjeno i broj sati
            akcije.get(index).setUradjeno(akcije.get(index).getUradjeno() + uradjeno);
            akcije.get(index).setBrojSati(akcije.get(index).getBrojSati() + brojSati);

            // proveri da li je vise od 100
            if(akcije.get(index).getUradjeno() >= 100){
                zavrsetakAkcije(akcije.get(index));
            }
        }
    }

    private void zavrsetakAkcije(Akcija a){
        zavrseno.add(a.getZadatak());
        // proveri da li je za neke od onih na cekanju ispunjen uslov
        List<Akcija> akcijeNaCekanjuBrisanje = new ArrayList<>();
        for(Akcija cekanje:naCekanju){
            if(cekanje.getZadatak().ispunjeniUslovi(zavrseno)){
                akcije.add(cekanje);
                akcijeNaCekanjuBrisanje.add(cekanje);
            }
        }
        for(Akcija brisi:akcijeNaCekanjuBrisanje){
            naCekanju.remove(brisi);
        }
        // izbrisi iz liste
        akcije.remove(a);
    }

    public List<Osoba> getOsobe() {
        return osobe;
    }

    public void setOsobe(List<Osoba> osobe) {
        this.osobe = osobe;
    }

    public List<RadniZadatak> getZadaci() {
        return zadaci;
    }

    public void setZadaci(List<RadniZadatak> zadaci) {
        this.zadaci = zadaci;
    }

    public List<Akcija> getAkcije() {
        return akcije;
    }

    public void setAkcije(List<Akcija> akcije) {
        this.akcije = akcije;
    }

    public List<RadniZadatak> getZavrseno() {
        return zavrseno;
    }

    public void setZavrseno(List<RadniZadatak> zavrseno) {
        this.zavrseno = zavrseno;
    }
}
