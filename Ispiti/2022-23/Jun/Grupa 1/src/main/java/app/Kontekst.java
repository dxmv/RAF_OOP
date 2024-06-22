package app;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

// U ovoj klasi obradjujemo podatke
public class Kontekst {
    private final String IME_FAJLA="RAF_Coursera.txt";
    private int novac;

    private List<Kurs> sviKursevi;
    private List<Kurs> mojiKursevi;

    private List<Aktivnost> aktivnosti;

    public Kontekst(){
        this.novac = 1000; // na pocetku je 1000 dolara
        this.sviKursevi = ucitajKurseve();
        this.mojiKursevi = new ArrayList<>();
        this.aktivnosti = new ArrayList<>();
    }

    // ucitava kurseve iz fajla, vraca listu svih kurseva
    private List<Kurs> ucitajKurseve(){
        List<Kurs> res = new ArrayList<>();
        try(Scanner sc = new Scanner(new File(IME_FAJLA))){
            // Introduction to Web Development,Technology>480 minutes:$99
            while (sc.hasNextLine()){
                String line = sc.nextLine();
                // preskacemo prazan red
                if(line.isEmpty()){
                    continue;
                }
                // splitovanje ':'
                String[] splitDveTacke = line.split(":");
                // dobijanje cene
                String cena = splitDveTacke[1];
                // splitovanje ','
                String[] splitZarez = splitDveTacke[0].split(",");
                // dobijanje naziva
                String naziv = splitZarez[0];
                // splitovanje srednjeg dela '>'
                String[] splitVece = splitZarez[1].split(">");
                // kategorija i trajanje
                String kategorija = splitVece[0];
                String trajanje = splitVece[1];
                Kurs k = new Kurs(naziv,kategorija,trajanje,cena);
                res.add(k);
            }
        }
        catch (FileNotFoundException ex){
            ex.printStackTrace();
        }
        return res;
    }

    public void dodajKursUMojeKurseve(Kurs k) throws RuntimeException{
        if(mojiKursevi.contains(k)){
            throw new RuntimeException("Element vec u listi");
        }
        if(this.novac - k.getIntCena() < 0){
            throw new RuntimeException("Nemate novca");
        }
        mojiKursevi.add(k);
        this.setNovac(novac - k.getIntCena());
    }

    // vraca dodatu aktivnost
    public Aktivnost dodajAktivnost(Kurs k,int pocetakSati,int pocetakMinuti,int trajanje) throws RuntimeException{
        // danasnji datum sa datim vremenom
        LocalDateTime vreme = LocalDateTime.of(LocalDate.now(),LocalTime.of(pocetakSati,pocetakMinuti,0));
        LocalDateTime kraj = vreme.plusMinutes(trajanje);

        // nije moguće gledati dva kursa u isto vreme
        // tako da ukoliko se pokuša gledanje nekog kursa koje se poklapa sa već
        // nekom postojećom aktivnošću treba ispisati poruku o grešci
        for(Aktivnost aktivnost:aktivnosti){
            LocalDateTime trenutnoPocetno = LocalDateTime.of(aktivnost.getPocetakDatum(),aktivnost.getPocetakVreme());
            LocalDateTime trenutnoKraj = LocalDateTime.of(aktivnost.getKrajDatum(),aktivnost.getKrajVreme());
            if(preklapanjeVremena(vreme,kraj,trenutnoPocetno,trenutnoKraj)){
                throw new RuntimeException("U ovom periodu se gleda " + aktivnost.getK().getNaziv());
            }

            //  Nije moguće ni da se doda
            // aktivnost za neki kurs čiji početak je pre početka postojeće aktivnosti za taj isti kurs tog dana.
            if(aktivnost.getK().equals(k)){
                if(vreme.isBefore(trenutnoPocetno)){
                    throw new RuntimeException("Ne moze da se doda aktivnost gde je pocetak pre pocetka iste aktivnosti.");
                }
            }
        }

        Aktivnost a = new Aktivnost(k,vreme.toLocalDate(),vreme.toLocalTime(),kraj.toLocalDate(),kraj.toLocalTime(),trajanje);
        aktivnosti.add(a);
        return a;
    }

    private boolean preklapanjeVremena(LocalDateTime start1, LocalDateTime end1, LocalDateTime start2, LocalDateTime end2) {
        return start1.isBefore(end2) || end1.isAfter(start2);
    }

    // vraca novu mapu
    public HashMap<String,List<Aktivnost>> kategorijeAktivnosti(){
        HashMap<String,List<Aktivnost>> rez = new HashMap<>();
        // prolazimo kroz sve aktivnosti
        for(Aktivnost a:aktivnosti) {
            // gledamo da li se kategorija nalzi u mapi
            if(rez.containsKey(a.getK().getKategorija())){
                // nalazi se, samo dodamo u listu
                rez.get(a.getK().getKategorija()).add(a);
            }
            else{
                // ne nalazi se, pravimo listu
                List<Aktivnost> aks = new ArrayList<>();
                aks.add(a);
                rez.put(a.getK().getKategorija(),aks);
            }
        }
        return rez;
    }

    public List<Kurs> getSviKursevi() {
        return sviKursevi;
    }

    public void setSviKursevi(List<Kurs> sviKursevi) {
        this.sviKursevi = sviKursevi;
    }

    public int getNovac() {
        return novac;
    }

    public void setNovac(int novac) {
        this.novac = novac;
    }

    public List<Kurs> getMojiKursevi() {
        return mojiKursevi;
    }

    public void setMojiKursevi(List<Kurs> mojiKursevi) {
        this.mojiKursevi = mojiKursevi;
    }

    public List<Aktivnost> getAktivnosti() {
        return aktivnosti;
    }

    public void setAktivnosti(List<Aktivnost> aktivnosti) {
        this.aktivnosti = aktivnosti;
    }
}
