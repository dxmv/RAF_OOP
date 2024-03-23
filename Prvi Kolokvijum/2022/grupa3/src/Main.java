import klase.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // pod 10.
        Coursera c = new Coursera();

        Profesor profesor1 = new Profesor("Zvonimir","Pavic","1", Oblast.PROGRAMIRANJE);
        Profesor profesor2 = new Profesor("Zarko","Pizic","2", Oblast.STATISTIKA);

        Obuka projekat1 = new Projekat("Prvi projekat",1,Oblast.PROGRAMIRANJE,10);
        c.getObuke().add(projekat1);
        Obuka projekat2 = new Projekat("Drugi projekat",1,Oblast.STATISTIKA,5);
        c.getObuke().add(projekat2);


        Obuka k = new Kurs("Kurs 1",1,Oblast.PROGRAMIRANJE,true);
        c.getObuke().add(k);


        Polaznik p1 = new Polaznik("Dimitrije","Stepanovic","ds@gmail.com");
        Polaznik p2 = new Polaznik("Muhamed","Mesimovicc","mm@gmail.com");
        Polaznik p3 = new Polaznik("Ahmed","Rad","ar@gmail.com");
        Polaznik p4 = new Polaznik("Sofija","Mustafa","sm@gmail.com");
        Polaznik p5 = new Polaznik("Josip","A","aj@gmail.com");

        Tim tim = new Tim(p1);
        tim.getPolaznici().add(p3);
        tim.getPolaznici().add(p4);


        tim.registruj(projekat1);
        p1.registruj(projekat2);

        p2.registruj(k);
        p2.registruj(projekat1);
        p2.registruj(projekat2);

        p5.registruj(k);
        p5.registruj(projekat2);
        p5.registruj(projekat1);


        c.dodajRegistraciju(p1);
        c.dodajRegistraciju(p2);
        c.dodajRegistraciju(p3);
        c.dodajRegistraciju(p4);
        c.dodajRegistraciju(p5);


        c.ispisObuka(true,"ispis.txt");
        // c.ispisObuka(false,"ispis.txt");

    }
}