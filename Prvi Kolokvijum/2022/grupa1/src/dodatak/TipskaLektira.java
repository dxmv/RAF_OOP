package dodatak;

import klase.Knjizevnost;

import java.util.ArrayList;
import java.util.List;

public class TipskaLektira extends Lektira{
    public TipskaLektira(int razred, TipSkole tipSkole) {
        super(razred, tipSkole);
    }

    @Override
    public void dodaj(Knjizevnost k) {
        // Tipska lektira treba da sadrži četiri različite knjige (Knjizevnosti) koje imaju isti TipKnjizevnosti,

        // ako vec imamo 4 knjige izlazimo
        if(knjige.size() == 4){
            return;
        }

        if(this.knjigaULektiri(k)){
            // ako se knjiga vec nalazi u listi, necemo opet da je dodajemo
            return;
        }

        // ako nema knjiga u lektiri, onda dodajemo ovu knjigu, nije nam bitan tip
        if(knjige.isEmpty()){
            knjige.add(k);
        }
        // ako ima knjiga u lektiri, moramo da proverivom da li se poklapaju tipovi knjizevnosti
        else{
            if(knjige.getFirst().getTipKnjizevnosti() == k.getTipKnjizevnosti()){
                knjige.add(k);
            }
        }
    }


}
