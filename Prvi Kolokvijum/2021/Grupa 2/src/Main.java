import klase.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // pod 7.
        Student s = new Student();
        s.setGodinaUpisa(2020);
        s.setGodinaUpisa(23);
        s.setStudProgramOznaka("RN");

        Predmet p1 = new Predmet("Prvi",30,1);
        Predmet p2 = new Predmet("Drugi",0,3);
        Predmet p3 = new Predmet("Treci",70,2);
        s.getPolozeniPredmeti().add(p1);
        s.getPolozeniPredmeti().add(p3);


        UpisGodine prva = new UpisGodine(2020,6,23);
        prva.setGodinaKojuUpisuje(1);


        UpisGodine druga = new UpisGodine(2021,6,22);
        druga.setGodinaKojuUpisuje(2);

        ObnovaGodine obnovaGodine = new ObnovaGodine(2022,6,21);
        obnovaGodine.setGodinaObnove(2);

        PromenaStudijskogPrograma promenaStudijskogPrograma = new PromenaStudijskogPrograma(2023,3,10);
        promenaStudijskogPrograma.setBrojIndexa(11);
        promenaStudijskogPrograma.setGodinaUpisa(2);
        promenaStudijskogPrograma.setNoviStudProgramOznaka("RI");

        s.dodajAktivnost(prva);
        s.dodajAktivnost(druga);
        s.dodajAktivnost(obnovaGodine);
        s.dodajAktivnost(promenaStudijskogPrograma);
        System.out.println(s.vratiGodinuStudija()); // 2
        System.out.println(s.jePonovac()); // true
        System.out.println(s.slusaPredmet(p1)); // false
        System.out.println(s.getBroj()); // 11
        System.out.println("\n\n\n");

        s.ispisAktivnosti(false,"");
        s.ispisAktivnosti(true,"ispis.txt");
    }
}