package klase;

import java.util.*;

// pod 5.
public class Biblioteka {
    private String naziv;
    private List<Knjizevnost> knjige;

    public Biblioteka(String naziv) {
        this.naziv = naziv;
        this.knjige = new ArrayList<>();
    }

    public void dodajKnjigu(Knjizevnost knjizevnost){
        this.knjige.add(knjizevnost);
    }

    public void ispisSvihKnjiga(){
        // na konzolu ispisuje sve knjige sortirane po nazivu alfabetski rastuće
        System.out.println("Knjige na raspolaganju u biblioteci " + this.naziv + ":");
        this.knjige.sort(Comparator.comparing(Knjizevnost::getNaziv));
        for(Knjizevnost k:this.knjige){
            // Mozemo ovo posto smo u svakoj klasi pregazili toString
            System.out.println(k);
        }
    }

    public Pisac najcitanijiPisac(){
        // vraća instancu Pisca koji ima najveći broj knjiga u Biblioteci
        Map<Pisac,Integer> pisci = napraviMapuPiscaIDela();
        Pisac rez = null;
        int brojKnjiga=0;
        for(Map.Entry<Pisac,Integer> vrednost:pisci.entrySet()){
            // ako ima vise knjiga od prethodnog broja, onda je on novi max
            if(vrednost.getValue() > brojKnjiga){
                rez = vrednost.getKey();
                brojKnjiga = vrednost.getValue();
            }
            // ako ima isti broj gledamo godiste
            else if(vrednost.getValue() == brojKnjiga){
                if(rez == null || rez.getGodinaRodjenja() < vrednost.getKey().getGodinaRodjenja()){
                    rez = vrednost.getKey();
                    brojKnjiga = vrednost.getValue();
                }
            }
        }
        return rez;
    }

    private Map<Pisac,Integer> napraviMapuPiscaIDela(){
        Map<Pisac,Integer> pisci= new HashMap<>(); // Cuvamo koliko se dela nalazi za svakog pisca
        for(Knjizevnost k:knjige){
            if(k instanceof AutorskaKnjizevnost ak){
                // Ako se pisac nalazi u mapi onda samo povecavamo broj dela
                // Ako se ne nalazi, onda dodajemo u mapu
                if(pisci.containsKey(ak.getAutor())){
                    pisci.put(ak.getAutor(),pisci.get(ak.getAutor())+1);
                }
                else{
                    pisci.put(ak.getAutor(),1);
                }
            }
        }
        return pisci;
    }

    // pod 6.
    public boolean knjigaUBiblioteci(Knjizevnost k){
        for(Knjizevnost knjiga:knjige){
            if(knjiga.equals(k)){
                return true;
            }
        }
        return false;
    }




    // Getters And Setters
    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public List<Knjizevnost> getKnjige() {
        return knjige;
    }

    public void setKnjige(List<Knjizevnost> knjige) {
        this.knjige = knjige;
    }
}
