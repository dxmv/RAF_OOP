import java.util.List;

// pod 6.
public class Main {
    public static void main(String[] args) {
        Stamparija s = new Stamparija();

        PitanjePonudjeniOdgovor po1 = new PitanjePonudjeniOdgovor("Ponudjeni odgovori 1?");
        po1.setRedniBroj(1);
        po1.setBrojPoena(2);
        po1.dodajOdgovor("Prvi");
        po1.dodajOdgovor("Drugi");
        po1.dodajOdgovor("Treci");

        PitanjePonudjeniOdgovor po2 = new PitanjePonudjeniOdgovor("Ponudjeni odgovori 2?");
        po2.setRedniBroj(2);
        po2.setBrojPoena(2);
        po2.dodajOdgovor("Prvi");
        po2.dodajOdgovor("Drugi");
        po2.dodajOdgovor("Treci");
        po2.dodajOdgovor("Cetvrti");

        PitanjeSlobodanOdgovor ps1 = new PitanjeSlobodanOdgovor("Slobodan odgovor 2?");
        ps1.setRedniBroj(3);
        ps1.setBrojPoena(1);
        ps1.setBrojLinija(4);

        PitanjeSlobodanOdgovor ps2 = new PitanjeSlobodanOdgovor("Slobodan odgovor 1?");
        ps2.setRedniBroj(4);
        ps2.setBrojPoena(1);
        ps2.setBrojLinija(2);

        PitanjePopunjavanje pp1 = new PitanjePopunjavanje("Pitanje koje je potrebno popuniti prvo");
        pp1.setRedniBroj(5);
        pp1.setBrojPoena(2);
        pp1.setDuzinaCrtice(8);
        pp1.dodajIndexCrtice(1); // crta za rec koje
        pp1.dodajIndexCrtice(3); // crta za rec potrebno

        PitanjePopunjavanje pp2 = new PitanjePopunjavanje("Pitanje koje je potrebno popuniti drugo");
        pp2.setRedniBroj(6);
        pp2.setBrojPoena(1);
        pp2.setDuzinaCrtice(8);
        pp2.dodajIndexCrtice(2); // crta za rec je

        PitanjePovezivanje pov1 = new PitanjePovezivanje("Povezivanje 1");
        pov1.setRedniBroj(6);
        pov1.setBrojPoena(2);
        pov1.setBrojPraznihMesta(10);
        pov1.setPrvaKolona(List.of("Kolona 1","Kolona 1"));
        pov1.setDrugaKolona(List.of("Kolona 2","Kolona 2"));

        PitanjePovezivanje pov2 = new PitanjePovezivanje("Povezivanje 2");
        pov2.setRedniBroj(8);
        pov2.setBrojPoena(2);
        pov2.setBrojPraznihMesta(10);
        pov2.setPrvaKolona(List.of("Kolona 1","Kolona 1"));
        pov2.setDrugaKolona(List.of("Kolona 2","Kolona 2"));

        Test spremno = new Test();
        spremno.setNaziv("Test 1");
        spremno.dodajPitanje(po1);
        spremno.dodajPitanje(po2);
        spremno.dodajPitanje(ps1);
        spremno.dodajPitanje(ps2);
        spremno.dodajPitanje(pp1);
        spremno.dodajPitanje(pov1);

        Test nijeSpremno = new Test();
        nijeSpremno.setNaziv("Test 2");
        nijeSpremno.dodajPitanje(po2);
        nijeSpremno.dodajPitanje(ps2);
        nijeSpremno.dodajPitanje(pov2);

        s.dodajZaStampanje(spremno);
        s.dodajZaStampanje(nijeSpremno);
        s.odstampajSve(true,"ispis.txt"); // ispis u fajl
        // s.odstampajSve(false,"");
    }
}
