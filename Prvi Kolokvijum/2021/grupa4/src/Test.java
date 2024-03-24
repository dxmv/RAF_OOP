import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        // pod 8.
        Ocenjivanje ocenjivanje = new Ocenjivanje();

        PitanjePonudjeniOdgovor pp1 = new PitanjePonudjeniOdgovor("Ponudjeni odgovori 1?");
        pp1.setBrojPoena(2);
        pp1.dodajPonudjeniOdgovor("a",true);
        pp1.dodajPonudjeniOdgovor("b",true);

        PitanjePonudjeniOdgovor pp2 = new PitanjePonudjeniOdgovor("Ponudjeni odgovori 2?");
        pp2.setBrojPoena(1);
        pp1.dodajPonudjeniOdgovor("a",true);
        pp1.dodajPonudjeniOdgovor("b",false);


        PitanjeSlobodanOdgovor ps1 = new PitanjeSlobodanOdgovor("Slobodan odgovor 1?");
        ps1.setBrojPoena(3);
        ps1.setKljucneReci(List.of("rec1","rec2","rec3"));

        PitanjeSlobodanOdgovor ps2 = new PitanjeSlobodanOdgovor("Slobodan odgovor 2?");
        ps2.setBrojPoena(1);
        ps1.setKljucneReci(List.of("rec1"));


        PitanjeSpajanjeOdgovora p1 = new PitanjeSpajanjeOdgovora("Spoji sinonime1");
        p1.setBrojPoena(3);
        p1.dodajPojam("test01");
        p1.dodajPojam("test02");
        p1.dodajOdgovor("test01","odg01");
        p1.dodajOdgovor("test01","odg02");
        p1.dodajOdgovor("test02","odg03");

        PitanjeSpajanjeOdgovora p2 = new PitanjeSpajanjeOdgovora("Spoji sinonime2");
        p2.setBrojPoena(3);
        p2.dodajPojam("test1");
        p2.dodajPojam("test2");
        p2.dodajOdgovor("test1","odg1");
        p2.dodajOdgovor("test1","odg2");
        p2.dodajOdgovor("test2","odg3");

        ResenjeTesta rt = new ResenjeTesta("Resenje Testa","Kandidat");
        rt.dodajOcenu(new Ocena(1,5,1));
        rt.dodajOcenu(new Ocena(6,10,3));
        rt.dodajOcenu(new Ocena(11,15,5));
        rt.odgovori(pp1,"a,b");
        rt.odgovori(pp1,"a,b");
        rt.odgovori(ps1,"rec1 proba rec2");
        rt.odgovori(ps2,"rec1 p");
        rt.odgovori(p1,"1 1;1 2;2 3;");

        System.out.println(rt.oceni());
        // sve do ovde treba izbaciti 9 3

        Odgovor o =new Odgovor(p2,"1 3;1 2;");
        System.out.println(o.oceni()); // ovde ispisuje 1

    }
}
