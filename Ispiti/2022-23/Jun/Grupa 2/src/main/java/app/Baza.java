package app;

import app.models.Statistika;
import app.models.Student;
import app.views.StatistikaProzor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Baza {
    private List<Student> studenti = new ArrayList<>();

    private final String IME_FAJLA_TERMIN1 = "OOP-Test-prvi termin.txt";
    private final String IME_FAJLA_TERMIN2 = "OOP-Test-drugi termin.txt";

    public Baza(){
        // ucitavanje prvog termina
        ucitajTermin(IME_FAJLA_TERMIN1,"termin1");
        // ucitavanje drugog termina
        ucitajTermin(IME_FAJLA_TERMIN2,"termin2");
        studenti.sort(Comparator.comparing(Student::getPrezime));

    }

    private void ucitajTermin(String imeFajla,String termin){
        try(Scanner sc = new Scanner(new File(imeFajla))){
            sc.nextLine(); // preskacemo prvu liniju sa podacima
            while (sc.hasNextLine()){
                String line = sc.nextLine();
                if(!line.isEmpty()){
                    // Prezime,Ime,Adresa e-pošte, poeni...
                    String[] delovi = line.split(",");
                    Student s = new Student(delovi[1],delovi[0],delovi[2],termin);

                    // citanje poena
                    for(int i = 3;i<delovi.length;i++){
                        if(delovi[i].equals("-")){
                            s.dodajPoene(0.0);
                        }
                        else{
                        s.dodajPoene(Double.parseDouble(delovi[i]));
                        }
                    }
                    studenti.add(s);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    // cita prvu liniju fajla
    private List<Statistika> dodajPitanjaStatistika(String termin){
        List<Statistika> rez = new ArrayList<>();
        // citaj odgovarajuci fajl
        try(Scanner sc = new Scanner(new File(termin.equals("termin1")?IME_FAJLA_TERMIN1:IME_FAJLA_TERMIN2))){
            String line = sc.nextLine();
            // Prezime,Ime,Adresa e-pošte,P. 1 /3.00,P. 2 /1.50,P. 3 /1.50,P. 4 /0.50,P. 5 /1.00,P. 6 /1.50,P. 7 /0.50,P. 8 /0.50,P. 9 /1.00,P. 10 /1.00,P. 11 /1.50,P. 12 /1.00,P. 13 /1.00,P. 14 /1.00,P. 15 /1.00,P. 16 /2.50,P. 17 /1.00,P. 18 /2.00,P. 19 /1.00,P. 20 /1.50,P. 21 /1.00,P. 22 /0.50
            String[] delovi = line.split(",");
            for(int i = 3;i<delovi.length;i++){
                String[] pitanje = delovi[i].split("/");
                Statistika s = new Statistika(pitanje[0],Double.parseDouble(pitanje[1]));
                rez.add(s);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return rez;
    }

    public List<Statistika> racunajStatistiku(String termin){
        // procitaj prvu liniju odgovarajuceg termina i napravi pitanja
        List<Statistika> rez = dodajPitanjaStatistika(termin);
        List<Student> students = filtrirajTermin(termin);
        // prodji kroz studente i racunaj
        for(Student s:students){
            for(int i = 0;i<s.getBrojPoena().size();i++){
                rez.get(i).dodajBodove(s.getBrojPoena().get(i));
            }
        }

        // jos racunjanje za uml
        Statistika uml = new Statistika("UML",3);
        for(Student s:students){
            uml.dodajBodove(s.getUmlPoeni());
        }
        rez.add(uml);
        return rez;
    }

    public List<Student> filtrirajTermin(String termin){
        return this.studenti.stream().filter(el->el.getTermin().equals(termin)).toList();
    }

    public List<Student> getStudenti() {
        return studenti;
    }

    public void setStudenti(List<Student> studenti) {
        this.studenti = studenti;
    }
}
