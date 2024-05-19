package app.controllers;



import app.model.Paket;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

// U ovoj klasi obradjujemo podatke
public class Kontekst {
    private List<Paket> paketi = new ArrayList<>();
    private List<String> gradovi = new ArrayList<>();

    public Kontekst(){
        ucitajPakete(paketi);
        ucitajGradove(gradovi);
    }

    private void ucitajPakete(List<Paket> lista){
        try (Scanner sc = new Scanner(new File(Paket.IME_FAJLA))){
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                // primer linije
                // Leskovac-X,LK14214
                if(line != null && !line.isEmpty()){
                    String[] delovi = line.split(",");
                    String[] gradovi = delovi[0].split("-");
                    Paket p = new Paket(gradovi[0],gradovi[1],delovi[1]);
                    lista.add(p);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void ucitajGradove(List<String> lista){
        try (Scanner sc = new Scanner(new File("gradovi.txt"))){
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                if(line != null && !line.isEmpty()){
                    lista.add(line.trim());
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    // id ili jedan od gradova (od, za) počinje unetim tekstom
    private List<Paket> pocinjeSlovima(List<Paket> lista, String s){
        // filtiranje je case insensitive
        return lista.stream().filter(p->
            p.getGradOd().toLowerCase().startsWith(s)
                    || p.getGradZa().toLowerCase().startsWith(s)
                    || p.getId().toLowerCase().startsWith(s)
        ).collect(Collectors.toList());
    }

    public List<Paket> nepoznatiPaketi(List<Paket> lista, boolean nepoznatoOd,boolean nepoznatoZa){
        // ako oba nisu stiklirana vracamo samo dobijeno
        if(!nepoznatoOd && !nepoznatoZa){
            return lista;
        }
        return lista.stream().filter(el->{
            // ako su oba stiklirana
            if(nepoznatoOd && nepoznatoZa){
                // gledamo da li su oba 'X'
                return el.getGradOd().equals("X") && el.getGradZa().equals("X");
            }
            // ako je stiklirano samo nepoznato od
            else if(nepoznatoOd){
                // gledamo da li je gradOd jednak "X"
                return el.getGradOd().equals("X");
            }
            // stiklirano je nepoznato za
            return el.getGradZa().equals("X");
        }).collect(Collectors.toList());
    }

    public List<Paket> filter(String s,boolean nepoznatoOd,boolean nepoznatoZa){
        // prvo filtriramo po slovima
        List<Paket> res = pocinjeSlovima(paketi,s);

        // filtriramo po nepoznatom
        return nepoznatiPaketi(res,nepoznatoOd,nepoznatoZa);
    }

    public List<Paket> getPaketi() {
        return paketi;
    }

    public void setPaketi(List<Paket> paketi) {
        this.paketi = paketi;
    }

    public List<String> getGradovi() {
        return gradovi;
    }

    public void setGradovi(List<String> gradovi) {
        this.gradovi = gradovi;
    }
}
