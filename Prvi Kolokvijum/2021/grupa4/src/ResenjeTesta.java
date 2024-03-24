import java.util.ArrayList;
import java.util.List;

public class ResenjeTesta implements Ocenljivo{
    private List<Odgovor> odgovori;

    private List<Ocena> intervaliOcene;
    private String nazivTesta;
    private String kandidat;

    public ResenjeTesta(String nazivTesta, String kandidat) {
        this.nazivTesta = nazivTesta;
        this.kandidat = kandidat;
        this.odgovori = new ArrayList<>();

        this.intervaliOcene = new ArrayList<>();
    }

    public List<Odgovor> getOdgovori() {
        return odgovori;
    }

    public void setOdgovori(List<Odgovor> odgovori) {
        this.odgovori = odgovori;
    }

    public String getNazivTesta() {
        return nazivTesta;
    }

    public void setNazivTesta(String nazivTesta) {
        this.nazivTesta = nazivTesta;
    }

    public String getKandidat() {
        return kandidat;
    }

    public void setKandidat(String kandidat) {
        this.kandidat = kandidat;
    }

    public void odgovori(Pitanje p, String s){
        this.odgovori.add(new Odgovor(p,s));
    }

    // pod 4.
    @Override
    public int oceni() {
        // zbir poena svih odgovora
        int rez = 0;
        for(Odgovor o:odgovori){
            rez += o.oceni();
        }
        System.out.println(rez);
        int ocena = 1;
        // pod 7.
        // proveriti za koju ocenu radi
        for(Ocena o: intervaliOcene){
            if(o.brojPoenaUIntervalu(rez)){
                ocena = o.getOcena();
                break;
            }
        }
        return ocena;
    }

    // pod 5.

    @Override
    public String toString() {
        // za test naziv testa i kandidata, broj ogovorenih pitanja i ukupan broj poena
        return this.nazivTesta + " " + this.kandidat + " " + this.odgovori.size() + " " + this.oceni();
    }

    // pod 7.

    public List<Ocena> getIntervaliOcene() {
        return intervaliOcene;
    }

    public void setIntervaliOcene(List<Ocena> intervaliOcene) {
        this.intervaliOcene = intervaliOcene;
    }

    public void dodajOcenu(Ocena o){
        if(intervaliOcene.contains(o)){
            return;
        }
        intervaliOcene.add(o);
    }
}
