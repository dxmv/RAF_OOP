package app;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

// U ovoj klasi obradjujemo podatke
public class Kontekst {

    private List<Film> sviFilmovi;
    private List<Film> odgledani;

    private final String IME_FAJLA = "data.txt";

    public Kontekst() {
        try{
            this.sviFilmovi = ucitajSveFilmove();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(this.sviFilmovi);
        odgledani = new ArrayList<>();
    }


    // vraca listu filmova iz fajla
    private List<Film> ucitajSveFilmove() throws IOException {
        List<Film> filmovi = new ArrayList<>();
        try (Scanner sc = new Scanner(new File(IME_FAJLA))) {
            // Ant-Man,2,7.3,2015,117
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                // preskacemo prazan red iz fajla:
                if (line.isEmpty()) {
                    continue;
                }

                // ako nije prazan red, nastavljamo parsiranje
                String[] split = line.split(",");
                String ime = split[0];
                int faza = Integer.parseInt(split[1]);
                double ocena = Double.parseDouble(split[2]);
                int godina = Integer.parseInt(split[3]);
                int duzina = Integer.parseInt(split[4]);
                Film f = new Film(ime,faza,ocena,godina,duzina);
                filmovi.add(f);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        filmovi.sort((f1, f2) -> f1.getIme().compareTo(f2.getIme()));
        return filmovi;
    }


    public List<Film> getSviFilmovi() {
        return sviFilmovi;
    }


    // STATISTIKA
    public double getProsecnaDuzinaFilmova(List<Film> filmovi){
        if(filmovi.isEmpty()){
            return 0;
        }
        double s = 0;
        for(Film f : filmovi){
            s += f.getDuzinaTrajanja();
        }
        return s/filmovi.size();
    }

    public double getProsecnaOcenaFilmova(List<Film> filmovi){
        if(filmovi.isEmpty()){
            return 0;
        }
        double s = 0;
        for(Film f : filmovi){
            s += f.getOcena();
        }
        return s/filmovi.size();
    }

    // vraca sve faze za combobox
    public Set<String> getFazeFilmova(){
        Set<String> faze = new HashSet<>();
        for(Film f:sviFilmovi){
            if(!faze.contains(f.getFaza())){
                faze.add(f.getFaza());
            }
        }
        faze.stream().sorted(String::compareTo);
        return faze;
    }

    // METODE ZA FILTRIRANJE SVIH FILMOVA
    public List<Film> filtriraniFilmovi(String deoImena){
        // vracamo na staro ako je polje prazno
        if(deoImena.isEmpty()){
            return sviFilmovi;
        }
        List<Film> rez = new ArrayList<>();
        for(Film f:sviFilmovi){
            // malo bolje provera za ime
            if(f.getIme().contains(deoImena)){
                rez.add(f);
            }
        }
        return rez;
    }

    public List<Film> filtriraniFilmovi(String deoImena,int godina){
        // prvo filtriramo imena
        List<Film> filtrirano = filtriraniFilmovi(deoImena);

        // pa onda godine
        List<Film> rez = new ArrayList<>();
        for(Film f:filtrirano){
            if(f.getGodinaIzlazka() == godina){
                rez.add(f);
            }
        }
        return rez;
    }

    public List<Film> filtriranjeFaza(String faza){
        List<Film> rez = new ArrayList<>();
        for(Film f:sviFilmovi){
            if(f.getFaza().equals(faza)){
                rez.add(f);
            }
        }
        return rez;
    }




    public List<Film> getOdgledani() {
        return odgledani;
    }

    public void setOdgledani(List<Film> odgledani) {
        this.odgledani = odgledani;
    }

    public void setSviFilmovi(List<Film> sviFilmovi) {
        this.sviFilmovi = sviFilmovi;
    }
}
