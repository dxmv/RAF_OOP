package dodatak;

import klase.*;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        // Pod 7.
        Pesnik pesnik = new Pesnik("Vilijam","Sekspir",1450);
        ProzniPisac pisac1 = new ProzniPisac("Fjodor","Dostojevski",1850);
        ProzniPisac pisac2 = new ProzniPisac("Fidrih","Nice",1839);

        Sakupljac sakupljac = new Sakupljac("Vuk Karidzic");

        Biblioteka biblioteka = new Biblioteka("Glavna Biblioteka");

        Knjizevnost k1 = pesnik.objavljuje("Hamlet",1480,TipKnjizevnosti.LIRIKA);
        Knjizevnost k2 = pesnik.objavljuje("Hamlet",1495,TipKnjizevnosti.LIRIKA);
        Knjizevnost k3 = pesnik.objavljuje("Romeo I Julija",1490,TipKnjizevnosti.LIRIKA);

        Knjizevnost k4 = pisac1.objavljuje("Zli dusi",1889,TipKnjizevnosti.EPIKA);
        Knjizevnost k5 = pisac1.objavljuje("Zlocin I Kazna",1895,TipKnjizevnosti.EPIKA);
        Knjizevnost k6 = pisac1.objavljuje("Zlocin I Kazna",1895,TipKnjizevnosti.EPIKA);

        Knjizevnost k7 = pisac2.objavljuje("Gej Nauka",1890,TipKnjizevnosti.EPIKA);
        Knjizevnost k8 = pisac2.objavljuje("Zelja za moci",1888,TipKnjizevnosti.EPIKA);
        Knjizevnost k9 = pisac2.objavljuje("Zaratustra",1880,TipKnjizevnosti.EPIKA);

        biblioteka.dodajKnjigu(k1);
        biblioteka.dodajKnjigu(k2);
        biblioteka.dodajKnjigu(k3);

        biblioteka.dodajKnjigu(k4);
        biblioteka.dodajKnjigu(k5);
        biblioteka.dodajKnjigu(k6);

        biblioteka.dodajKnjigu(k7);
        biblioteka.dodajKnjigu(k8);
        biblioteka.dodajKnjigu(k9);

        Knjizevnost k10 = sakupljac.objavljuje("Proba 1",1,TipKnjizevnosti.LIRSKO_EPSKI);
        Knjizevnost k11 = sakupljac.objavljuje("Proba 2",1,TipKnjizevnosti.LIRSKO_EPSKI);

        biblioteka.dodajKnjigu(k10);
        biblioteka.dodajKnjigu(k11);

        biblioteka.ispisSvihKnjiga();
        System.out.println(biblioteka.najcitanijiPisac()); // Fjodor Dostojevski

        // Pod 8.
        TipskaLektira tl = new TipskaLektira(4,TipSkole.OSNOVNA);
        tl.dodaj(k4);
        tl.dodaj(k5);
        tl.dodaj(k7);
        tl.dodaj(k8);
        System.out.println(tl.getKnjige().size()); // 4

        KombinovanaLektira kl = new KombinovanaLektira(3,TipSkole.SREDNJA);
        kl.dodaj(k10);
        kl.dodaj(k5);
        kl.dodaj(k7);
        kl.dodaj(k9);
        kl.dodaj(k3);
        System.out.println(kl.getKnjige().size()); // 5
        kl.ispisUFajl("ispis.txt");

    }
}
