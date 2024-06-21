package app.controllers;

import app.models.Student;
import app.models.TerminPolaganja;
import app.models.TerminUcionica;
import app.models.Ucionica;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

// U ovoj klasi obradjujemo podatke
public class Kontekst {

    private List<Student> studenti = new ArrayList<>();

    private List<Ucionica> ucionice = new ArrayList<>();

    private List<TerminUcionica> terminUcionica = new ArrayList<>();

    private List<TerminPolaganja> terminPolaganja = new ArrayList<>();

    private int brojTermina;
    private int brojStudentaPoUcionici;

    private final static String IME_FAJLA_STUDENTI = "studenti1.txt";
    private final static String IME_FAJLA_UCIONICE = "ucionice1.txt";

    public Kontekst(){
        ucitajStudente();
        ucitajUcionice();
    }

    // dodaje elemente iz fajla u listu ucionica
    private void ucitajUcionice() {
        try (Scanner sc = new Scanner(new File(IME_FAJLA_UCIONICE))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                // preskacemo prazan red iz fajla:
                if (line.isEmpty()) {
                    continue;
                }
                // ako nije prazan red, nastavljamo parsiranje

                // RAF1-R
                // RAF8
                String[] delovi = line.split("-");
                Ucionica uc = new Ucionica(delovi[0]);
                if (delovi.length == 2){
                    if(delovi[1].equals("R")){
                        uc.setRacunari(true);
                    }
                    else{
                        uc.setAmfiteatar(true);
                    }
                }
                ucionice.add(uc);
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    // dodaje elemente iz fajla u listu studenta
    private void ucitajStudente() {
        try (Scanner sc = new Scanner(new File(IME_FAJLA_STUDENTI))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                // preskacemo prazan red iz fajla:
                if (line.isEmpty()) {
                    continue;
                }
                // ako nije prazan red, nastavljamo parsiranje

                // RI,1,2020,Mitreski,Milan
                String[] delovi = line.split(",");
                if(delovi.length == 5){
                    Student s = new Student(delovi[0],Integer.parseInt(delovi[1]),Integer.parseInt(delovi[2]),delovi[4],delovi[3]);
                    studenti.add(s);
                }
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    // dodaju u listu terminUcionca
    public void terminiUcionice() {
        for(int i = 1;i <= brojTermina; i++){
            for(Ucionica u : ucionice){
                TerminUcionica tu = new TerminUcionica("termin"+i,u,brojStudentaPoUcionici);
                // U amfiteatar može da stane duplo više studenata od  unetog broja
                if(u.isAmfiteatar()){
                       tu.setBrojUcenika(tu.getBrojUcenika() * 2);
                }
                terminUcionica.add(tu);
            }
        }
    }

    public void filterUcionicaSaRacunarima(){
        ucionice = ucionice.stream().filter(Ucionica::isRacunari).toList();
    }

    public List<Student> getStudenti() {
        return studenti;
    }

    public void setStudenti(List<Student> studenti) {
        this.studenti = studenti;
    }

    public List<Ucionica> getUcionice() {
        return ucionice;
    }

    public void setUcionice(List<Ucionica> ucionice) {
        this.ucionice = ucionice;
    }

    public int getBrojTermina() {
        return brojTermina;
    }

    public void setBrojTermina(int brojTermina) {
        this.brojTermina = brojTermina;
    }

    public int getBrojStudentaPoUcionici() {
        return brojStudentaPoUcionici;
    }

    public void setBrojStudentaPoUcionici(int brojStudentaPoUcionici) {
        this.brojStudentaPoUcionici = brojStudentaPoUcionici;
    }


    public List<TerminUcionica> getTerminUcionica() {
        return terminUcionica;
    }

    public void setTerminUcionica(List<TerminUcionica> terminUcionica) {
        this.terminUcionica = terminUcionica;
    }

    public List<TerminPolaganja> getTerminPolaganja() {
        return terminPolaganja;
    }

    public void setTerminPolaganja(List<TerminPolaganja> terminPolaganja) {
        this.terminPolaganja = terminPolaganja;
    }
}
