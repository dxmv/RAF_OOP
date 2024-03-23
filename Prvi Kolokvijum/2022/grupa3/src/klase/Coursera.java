package klase;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// pod 7.
public class Coursera {
    private List<Registracija> registracije;

    private List<Obuka> obuke;

    public Coursera(){
        this.registracije = new ArrayList<>();
        this.obuke = new ArrayList<>();
    }

    public List<Registracija> getRegistracije() {
        return registracije;
    }

    public void setRegistracije(List<Registracija> registracije) {
        this.registracije = registracije;
    }

    public List<Obuka> getObuke() {
        return obuke;
    }

    public void setObuke(List<Obuka> obuke) {
        this.obuke = obuke;
    }

    public void sveObukePolaznika(Polaznik p){
        //  treba da ispiše sve obuke za koje je registrovan prosleđeni polaznik
        for(Registracija r:registracije){
            if(r instanceof Polaznik){
                if(r.equals(p)){
                    for(ObukaPolaznika op:((Polaznik) r).getObuke()){
                        System.out.println(op.getObuka().getNaziv());
                    }
                }
            }
        }
    }

    public boolean dodajRegistraciju(Registracija r){
        this.registracije.add(r);
        return true;
    }

    // pod 9.
    public void ispisObuka(boolean ispisFajla, String imeFajla){
        List<Obuka> aktivne = obukeKojeSeDrze();

        // Obuke se ispisuju sortirano po broju polaznika rastuće, a za isti broj polaznika po nazivu
        Collections.sort(aktivne, new Comparator<Obuka>() {
            @Override
            public int compare(Obuka obuka1, Obuka obuka2) {
                int compareByParticipants = Integer.compare(obuka1.getObukePolaznika().size(), obuka2.getObukePolaznika().size());
                if (compareByParticipants != 0) {
                    return compareByParticipants;
                }
                return obuka1.getNaziv().compareTo(obuka2.getNaziv());
            }
        });

        if(ispisFajla){
            // ispis u fajl
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(imeFajla));
                for(Obuka o:aktivne){
                    writer.write(o.toString() + "\n");
                    for(ObukaPolaznika op : o.getObukePolaznika()){
                        writer.write(op.getPolaznik().getEmail() + " - " + op.getRegistracioniBroj() + "\n");
                    }
                }
                writer.close();
            }
            catch (IOException e){
                System.out.println("Greska pri pisanju u fajl");
            }
        }
        else{
            // ispis na konzolu
            for(Obuka o:aktivne){
                System.out.println(o);
                for(ObukaPolaznika op : o.getObukePolaznika()){
                    System.out.println(op.getPolaznik().getEmail() + " - " + op.getRegistracioniBroj());
                }
            }
        }
    }

    private List<Obuka> obukeKojeSeDrze(){
        // Obuka se drži ukoliko ima minimalan broj polaznika
        List<Obuka> rez = new ArrayList<>();
        for(Obuka o:obuke){
            if(o.getObukePolaznika().size() >= o.getMinimalniBrojPolaznika()){
                rez.add(o);
            }
        }
        return rez;
    }
}
