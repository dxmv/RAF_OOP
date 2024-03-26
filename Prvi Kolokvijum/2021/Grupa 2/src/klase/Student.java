package klase;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Student {
    private String studProgramOznaka;
    private int broj;
    private int godinaUpisa;

    private List<AktivnostStudenta> aktivnosti;

    private List<Predmet> polozeniPredmeti;

    public Student() {
        this.aktivnosti = new ArrayList<>();
        this.polozeniPredmeti = new ArrayList<>();
    }

    public String getStudProgramOznaka() {
        return studProgramOznaka;
    }

    public void setStudProgramOznaka(String studProgramOznaka) {
        this.studProgramOznaka = studProgramOznaka;
    }

    public int getBroj() {
        return broj;
    }

    public void setBroj(int broj) {
        this.broj = broj;
    }

    public int getGodinaUpisa() {
        return godinaUpisa;
    }

    public void setGodinaUpisa(int godinaUpisa) {
        this.godinaUpisa = godinaUpisa;
    }

    public List<AktivnostStudenta> getAktivnosti() {
        return aktivnosti;
    }

    public void setAktivnosti(List<AktivnostStudenta> aktivnosti) {
        this.aktivnosti = aktivnosti;
    }

    public List<Predmet> getPolozeniPredmeti() {
        return polozeniPredmeti;
    }

    public void setPolozeniPredmeti(List<Predmet> polozeniPredmeti) {
        this.polozeniPredmeti = polozeniPredmeti;
    }

    // pod 3.
    public int getOsvojenihEspb(){
        int rez = 0;
        for(Predmet p:polozeniPredmeti){
            rez += p.getEspb();
        }
        return rez;
    }

    // pod 4.
    public int vratiGodinuStudija(){
        // treba da vrati poslednju upisanu ili obnovljenu
        // godinu posmatrajući datume aktivnosti studenta
        return this.aktivnosti.getLast().getGodinaStudija();
    }

    public boolean jePonovac(){
        // treba da vrati true ako je poslednja aktivnost studenta obnova godine
        return this.aktivnosti.getLast() instanceof ObnovaGodine;
    }

    // pod 5.
    public boolean dodajAktivnost(AktivnostStudenta aktivnostStudenta){
        // prvo proverava da li je aktivnost moguća
        if(aktivnostStudenta.proveriUslov(this)){
            // i ako jeste, dodaje je u kolekciju aktivnosti studenta i vraća true
            this.aktivnosti.add(aktivnostStudenta);
            if(aktivnostStudenta instanceof PromenaStudijskogPrograma){
                // kada se dodaje PromenaStudijskogPrograma studentu se menja oznaka studijskog programa, godina upisa i broj
                PromenaStudijskogPrograma ps = (PromenaStudijskogPrograma) aktivnostStudenta;
                setBroj(ps.getBrojIndexa());
                setStudProgramOznaka(ps.getNoviStudProgramOznaka());
                setGodinaUpisa(ps.getGodinaStudija());

                // prilikom promene studijskog programa student mora i da upiše sledeću godinu što znači da se prilikom promene studijskog programa dodaje još jedna aktivnost za studenta
                UpisGodine novUpis = new UpisGodine(ps.getGodina(),ps.getMesec(),ps.getDan());
                novUpis.setGodinaKojuUpisuje(poslednjaUpisanaGodina()); // godina koja se upisuje se dobija na osnovu prethodno upisane godine iz liste aktivnosti
                aktivnosti.add(novUpis);
            }
            return true;
        }
        // ako nije moguća vraća false
        return false;
    }

    private int poslednjaUpisanaGodina(){
        for(int i = aktivnosti.size()-1 ; i>=0;i--){
            if(aktivnosti.get(i) instanceof UpisGodine){
                return aktivnosti.get(i).getGodinaStudija();
            }
        }
        return -1;
    }

    public boolean slusaPredmet(Predmet p){
        // vraća true ako student trenutno sluša prosleđeni predmet
        // a to može biti ako se predmet sluša u semestru koji pripada godini koju je student poslednju upisao,
        // ako ga je student preneo ili ga upisao u obnovljenu godinu

        AktivnostStudenta a=this.aktivnosti.getLast(); // posmatra se samo poslednja aktivnost
        // ako ga je polozio onda ga neslusa
        if(polozeniPredmeti.contains(p)){
            return false;
        }
        if(a instanceof UpisGodine){
            return ((UpisGodine) a).getGodinaKojuUpisuje() == p.getGodinaFromSemestar();
        }
        if(a instanceof ObnovaGodine){
            return ((ObnovaGodine) a).getPredmetiKojeUpisuje().contains(p);
        }
        return false;
    }


    // pod 6.
    public void ispisAktivnosti(boolean ispisFajl,String imeFajla){
        List<AktivnostStudenta> sortirano= new ArrayList<>(this.aktivnosti);
        sortirano.sort(AktivnostStudenta::compareTo);
        if(ispisFajl){
            if(imeFajla.isEmpty()){
                return;
            }
            ispisFajl(sortirano,imeFajla);
        }
        else{
            ispisNaKonzolu(sortirano);
        }
    }

    private void ispisNaKonzolu(List<AktivnostStudenta> sortirano){
        // ispisuje se datum, vrsta aktivnosti,
        // godina koja se upisuje
        // ili obnovlja
        // ili studijski program na koji se prelazi i novi indeks.
        for(AktivnostStudenta a : sortirano){
            System.out.println(a);
        }
    }

    private void ispisFajl(List<AktivnostStudenta> sortirano,String imeFajla){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(imeFajla));
            // ispisuje se datum, vrsta aktivnosti,
            // godina koja se upisuje
            // ili obnovlja
            // ili studijski program na koji se prelazi i novi indeks
            for(AktivnostStudenta a : sortirano){
                bw.write(a.toString() + "\n");
            }
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
