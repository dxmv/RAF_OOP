package dodatak;

import klase.Knjizevnost;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// pod 6.
public abstract class Lektira implements ILektira{
    private int razred;

    private TipSkole tipSkole;

    protected List<Knjizevnost> knjige;

    public Lektira(int razred, TipSkole tipSkole) {
        this.razred = razred;
        this.tipSkole = tipSkole;
        this.knjige = new ArrayList<>();
    }

    public int getRazred() {
        return razred;
    }

    public void setRazred(int razred) {
        this.razred = razred;
    }

    public TipSkole getTipSkole() {
        return tipSkole;
    }

    public void setTipSkole(TipSkole tipSkole) {
        this.tipSkole = tipSkole;
    }

    public List<Knjizevnost> getKnjige() {
        return knjige;
    }

    public void setKnjige(List<Knjizevnost> knjige) {
        this.knjige = knjige;
    }

    // pod 6.
    protected boolean knjigaULektiri(Knjizevnost k){
        for(Knjizevnost knjizevnost:knjige){
            if(knjizevnost.equals(k)){
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return this.tipSkole + " " + this.razred + " (" + this.knjige.size() + "):";
    }

    public void ispisUFajl(String imeFajla){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(imeFajla));
            bw.write(this.toString() + "\n");
            for(Knjizevnost k:knjige){
                bw.write(k.toString() + "\n");
            }
            bw.close();
        } catch (IOException e) {
            System.out.println("Greska pri otvaranju");
        }
    }

    @Override
    public abstract void dodaj(Knjizevnost k);


}
