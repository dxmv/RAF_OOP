package klase;

// pod 2.
public abstract class Knjizevnost {
    private String naziv;
    private TipKnjizevnosti tipKnjizevnosti;

    public Knjizevnost(String naziv, TipKnjizevnosti tipKnjizevnosti) {
        this.naziv = naziv;
        this.tipKnjizevnosti = tipKnjizevnosti;
    }

    @Override
    public boolean equals(Object obj) {
        // 2 instance klase Knjizevnost su iste ako su im naziv i TipKnjizevnosti isti
        if(obj instanceof Knjizevnost knj){
            return knj.naziv.equals(this.naziv) && knj.tipKnjizevnosti.equals(this.tipKnjizevnosti);
        }
        return false;
    }



    // Getters And Setters
    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public TipKnjizevnosti getTipKnjizevnosti() {
        return tipKnjizevnosti;
    }

    public void setTipKnjizevnosti(TipKnjizevnosti tipKnjizevnosti) {
        this.tipKnjizevnosti = tipKnjizevnosti;
    }


}
