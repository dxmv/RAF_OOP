import java.util.List;

public class Main {
    // pod 8.
    public static void main(String[] args) {
        Baza b = new Baza();

        // kreira 3 obična predmeta
        Predmet p1 = new Predmet("P1",1,OznakaPlana.RN,8);
        Predmet p2 = new Predmet("P2",1,OznakaPlana.RI,8);
        Predmet p3 = new Predmet("P3",1,OznakaPlana.S,8);

        // napraviti 5 izbornih predmeta
        IzborniPredmet ip1 = new IzborniPredmet("IP1",2,OznakaPlana.RN,8);
        IzborniPredmet ip2 = new IzborniPredmet("IP2",7,OznakaPlana.RN,8);
        IzborniPredmet ip3 = new IzborniPredmet("IP3",3,OznakaPlana.RI,8);
        IzborniPredmet ip4 = new IzborniPredmet("IP4",5,OznakaPlana.RI,8);
        IzborniPredmet ip5 = new IzborniPredmet("IP5",4,OznakaPlana.S,8);

        // dodavanje preduslova
        ip1.dodajPreduslov(p1);
        ip2.dodajPreduslov(p1);
        ip2.dodajPreduslov(ip1);

        ip3.dodajPreduslov(p2);
        ip4.dodajPreduslov(p2);
        ip4.dodajPreduslov(ip3);

        ip5.dodajPreduslov(p3);


        // 2 izborne grupe
        IzborGrupa ig1 = new IzborGrupa("301",OznakaPlana.RN);
        IzborGrupa ig2 = new IzborGrupa("302",OznakaPlana.RI);

        Student ponovac = new Student(OznakaPlana.RN,1,2012);
        Student nijePonovac = new Student(OznakaPlana.RI,23,2020);

        ponovac.setUpisaneGodine(List.of(1,2,3,4,4));
        nijePonovac.setUpisaneGodine(List.of(1,2,3));

        ponovac.dodajPolozeniPredmet(p1);
        ponovac.dodajPolozeniPredmet(ip1);

        nijePonovac.dodajPolozeniPredmet(p2);

        b.dodajIzbor(nijePonovac,ig2);
        b.dodajIzbor(ponovac,ip2);

        b.ispis("ispis.txt");





    }

}
