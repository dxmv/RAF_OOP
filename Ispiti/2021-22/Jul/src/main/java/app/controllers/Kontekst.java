package app.controllers;

import app.models.Paket;
import app.models.StatusPosiljke;

import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

// U ovoj klasi obradjujemo podatke
public class Kontekst {

    private List<Paket> paketi = new ArrayList<>();

    private List<String> statusi = new ArrayList<>();

    private Set<String> gradovi = new HashSet<>();
    private final static String IME_FAJLA = "paketi1.txt";

    public Kontekst(){
        // dodavanje statusa
        // prvo dodajemo "SVI STATUSI"
        statusi.add("SVI STATUSI");
        for(StatusPosiljke sp : StatusPosiljke.values()){
            statusi.add(sp.toString());
        }

        ucitajPakete();
    }


    // dodaje elemente iz fajla u listu paketa
    // uporedo dodajemo i gradove iz fajla
    private void ucitajPakete() {
        gradovi.add("SVI GRADOVI");
        try (Scanner sc = new Scanner(new File(IME_FAJLA))) {

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                // preskacemo prazan red iz fajla:
                if (line.isEmpty()) {
                    continue;
                }
                // ako nije prazan red, nastavljamo parsiranje

                // ZP10968,Zrenjanin-Prokuplje;POSLAT
                String[] zarez = line.split(",");
                String id = zarez[0];

                String[] tackaZarez = zarez[1].strip().split(";");
                StatusPosiljke status = StatusPosiljke.valueOf(tackaZarez[1]);

                String[] crta = tackaZarez[0].strip().split("-");
                Paket p = new Paket(id,crta[0],crta[1],status);
                paketi.add(p);

                // dodavanje gradova
                gradovi.add(crta[0]);
                gradovi.add(crta[1]);
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    // filter po statusu, ako je status null onda vracamo sve
    private List<Paket> filterPoStatusu(List<Paket> paketi,StatusPosiljke statusPosiljke){
        return statusPosiljke==null ? paketi : paketi.stream().filter(el->el.getStatus().equals(statusPosiljke)).collect(Collectors.toList());
    }

    // filter po unetom tekstu
    // ako je tekst prazan onda vracamo sve pakete
    private List<Paket> filterPoTekstu(List<Paket> paketi,String tekst){
        return tekst.isEmpty() ? paketi :
                paketi.stream()
                .filter(el->el.getId().toLowerCase().startsWith(tekst.toLowerCase()) || el.getGradOd().toLowerCase().startsWith(tekst.toLowerCase()) || el.getGradZa().toLowerCase().startsWith(tekst.toLowerCase()))
                .collect(Collectors.toList());
    }

    // filter
    public List<Paket> filter(StatusPosiljke statusPosiljke,String tekst){
        List<Paket> res = filterPoStatusu(paketi,statusPosiljke);
        return filterPoTekstu(res,tekst);
    }

    public List<Paket> getPaketi() {
        return paketi;
    }

    public void setPaketi(List<Paket> paketi) {
        this.paketi = paketi;
    }

    public List<String> getStatusi() {
        return statusi;
    }

    public void setStatusi(List<String> statusi) {
        this.statusi = statusi;
    }

    public Set<String> getGradovi() {
        return gradovi;
    }

    public void setGradovi(Set<String> gradovi) {
        this.gradovi = gradovi;
    }
}
