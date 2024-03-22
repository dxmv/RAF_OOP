import klase.*;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Dnevnik dnevnik = new Dnevnik();

        Klasa klasa1 = new Klasa(1,1,"Dimitrije Stepanovic",Smer.LEKAR);
        Klasa klasa2 = new Klasa(1,2,"Future Hendrix",Smer.APOTEKAR);
        Klasa klasa3 = new Klasa(4,1,"Zvonimir Pavic",Smer.PEKAR);

        Aktivnost aktivnost1 = new Vizita("Doktor");
        Aktivnost aktivnost2 = new RadUPekari("Pekara");
        Aktivnost aktivnost3 = new RadUPekari("Apoteka");

        // Ucenici klase1
        Ucenik ucenik11 = new Ucenik("Zarko","Radovanovic","12",klasa1);
        Ucenik ucenik12 = new Ucenik("Petroslav","Petrovic","122434",klasa1);
        Ucenik ucenik13 = new Ucenik("Zvonka","Zad","123",klasa1);

        // Ucenici klase2
        Ucenik ucenik21 = new Ucenik("Srecko","Pavic","21",klasa2);
        Ucenik ucenik22 = new Ucenik("Milosava","Jad","222434",klasa2);
        Ucenik ucenik23 = new Ucenik("Zvonimirka","Zad","222223",klasa2);

        // Ucenici klase3
        Ucenik ucenik31 = new Ucenik("Muhamed","Mesimovic","1333333332",klasa3);
        Ucenik ucenik32 = new Ucenik("Radovan","Bok","33322434",klasa3);
        Ucenik ucenik33 = new Ucenik("Biserka","Cmok","389823",klasa3);


        // Isprobavanje dnevnika

        dnevnik.dodajKlasu(klasa1.getSmer(),klasa1.getRazred(),klasa1.getOdeljenje(),klasa1.getRazredniStaresina());
        dnevnik.dodajKlasu(klasa2.getSmer(),klasa2.getRazred(),klasa2.getOdeljenje(),klasa2.getRazredniStaresina());
        dnevnik.dodajKlasu(klasa3.getSmer(),klasa3.getRazred(),klasa3.getOdeljenje(),klasa3.getRazredniStaresina());

        dnevnik.dodajAktivnost(aktivnost1);
        dnevnik.dodajAktivnost(aktivnost2);
        dnevnik.dodajAktivnost(aktivnost3);

        dnevnik.dodajUcenika(ucenik11.getIme(),ucenik11.getPrezime(),ucenik11.getJmbg(),klasa1.getRazred(),klasa1.getOdeljenje(),klasa1.getSmer());
        dnevnik.dodajUcenika(ucenik12.getIme(),ucenik12.getPrezime(),ucenik12.getJmbg(),klasa1.getRazred(),klasa1.getOdeljenje(),klasa1.getSmer());
        dnevnik.dodajUcenika(ucenik13.getIme(),ucenik13.getPrezime(),ucenik13.getJmbg(),klasa1.getRazred(),klasa1.getOdeljenje(),klasa1.getSmer());

        dnevnik.dodajUcenika(ucenik21.getIme(),ucenik21.getPrezime(),ucenik21.getJmbg(),klasa1.getRazred(),klasa1.getOdeljenje(),klasa1.getSmer());
        dnevnik.dodajUcenika(ucenik22.getIme(),ucenik22.getPrezime(),ucenik22.getJmbg(),klasa1.getRazred(),klasa1.getOdeljenje(),klasa1.getSmer());
        dnevnik.dodajUcenika(ucenik23.getIme(),ucenik23.getPrezime(),ucenik23.getJmbg(),klasa1.getRazred(),klasa1.getOdeljenje(),klasa1.getSmer());

        dnevnik.dodajUcenika(ucenik31.getIme(),ucenik31.getPrezime(),ucenik31.getJmbg(),klasa1.getRazred(),klasa1.getOdeljenje(),klasa1.getSmer());
        dnevnik.dodajUcenika(ucenik32.getIme(),ucenik32.getPrezime(),ucenik32.getJmbg(),klasa1.getRazred(),klasa1.getOdeljenje(),klasa1.getSmer());
        dnevnik.dodajUcenika(ucenik33.getIme(),ucenik33.getPrezime(),ucenik33.getJmbg(),klasa1.getRazred(),klasa1.getOdeljenje(),klasa1.getSmer());

        dnevnik.dodajBelesku(ucenik11,aktivnost1,10,"Doktorisanje",1, LocalDate.now());
        dnevnik.dodajBelesku(ucenik12,aktivnost1,15,"Doktorisanje",2, LocalDate.now());
        dnevnik.dodajBelesku(ucenik13,aktivnost1,15,"Doktorisanje",4, LocalDate.now());

        dnevnik.dodajBelesku(ucenik21,aktivnost2,10,"Pekarisanje",5, LocalDate.now());
        dnevnik.dodajBelesku(ucenik22,aktivnost2,15,"Pekarisanje",7, LocalDate.now());
        dnevnik.dodajBelesku(ucenik23,aktivnost2,15,"Pekarisanje",10, LocalDate.now());

        dnevnik.dodajBelesku(ucenik31,aktivnost3,10,"Pekarisanje",57, LocalDate.now());
        dnevnik.dodajBelesku(ucenik32,aktivnost3,15,"Pekarisanje",74, LocalDate.now());
        dnevnik.dodajBelesku(ucenik33,aktivnost3,15,"Pekarisanje",99, LocalDate.now());

        dnevnik.ispisUFajl();
    }
}