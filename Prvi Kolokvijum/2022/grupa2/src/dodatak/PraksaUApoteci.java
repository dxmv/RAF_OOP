package dodatak;

import klase.Aktivnost;
import klase.Smer;

// pod 10.
public class PraksaUApoteci extends Aktivnost {
    public PraksaUApoteci(String naziv) {
        super(naziv, Smer.APOTEKAR, 0, 100);
    }

    @Override
    public int izracunajOcenu(double brojPoena) {
        // ocena se definiše tako što se za sve poene do 52 daje ocena 1
        // onda na svakih sledećih 12 poena po jedna ocena više
        if(brojPoena < 52){
            return 1;
        }
        int ocena = 2;
        for(int i = 52; i < 100; i+=12,ocena++){
        }
        return ocena;
    }
}
