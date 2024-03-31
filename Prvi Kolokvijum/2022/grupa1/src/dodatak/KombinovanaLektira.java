package dodatak;

import klase.Knjizevnost;
import klase.TipKnjizevnosti;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KombinovanaLektira extends Lektira{

    public KombinovanaLektira(int razred, TipSkole tipSkole) {
        super(razred, tipSkole);
    }

    @Override
    public void dodaj(Knjizevnost k) {
        // kombinovana lektira sadrži pet različitih knjiga od kojih
        // su barem dve Epike, barem jedna Lirika i najviše jedna Lirsko_epska
        if(this.knjigaULektiri(k)){
            return; // ako se knjiga vec nalazi u lektiri, ne dodajemo je
        }
        if(this.knjige.size() == 5){
            return;
        }

        int brojEpskih = brojKnjigaZaTip(TipKnjizevnosti.EPIKA);
        int brojLirskih = brojKnjigaZaTip(TipKnjizevnosti.LIRIKA);
        int brojLirskoEpskih = brojKnjigaZaTip(TipKnjizevnosti.LIRSKO_EPSKI);
        if(k.getTipKnjizevnosti().equals(TipKnjizevnosti.EPIKA)){
            // ako je ostalo mesta za jos jednu knjigu, a nemamo lirskih knjiga, onda ne dodajemo epsku
            if(knjige.size() == 4 && brojLirskih<1){
                return;
            }
        }
        if(k.getTipKnjizevnosti().equals(TipKnjizevnosti.LIRIKA)){
            // ako je ostalo mesta za dve knjige, a nemamo epskih, onda ne dodajemo lirsku
            if(knjige.size() == 3 && brojEpskih == 0){
                return;
            }
            // ako je ostalo mesta za 1 knjigu, a nemamo dovoljno epskih, onda ne dodajemo lirsku
            if(knjige.size() == 4 && brojEpskih == 1){
                return;
            }
        }
        if(k.getTipKnjizevnosti().equals(TipKnjizevnosti.LIRSKO_EPSKI) && brojLirskoEpskih == 1){
            return;
        }
        this.knjige.add(k);
    }

    private int brojKnjigaZaTip(TipKnjizevnosti tip){
        int br = 0;
        for(Knjizevnost k:knjige){
            if(k.getTipKnjizevnosti().equals(tip)){
                br++;
            }
        }
        return br;
    }
}
