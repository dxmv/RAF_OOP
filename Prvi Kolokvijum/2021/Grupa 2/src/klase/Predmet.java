package klase;

public class Predmet {
    private String naziv;
    private int espb;
    private int semestar;

    public Predmet(String naziv, int espb, int semestar) {
        this.naziv = naziv;
        this.espb = espb;
        this.semestar = semestar;
    }

    public String getNaziv() {
        return naziv;
    }

    public int getEspb() {
        return espb;
    }

    public int getSemestar() {
        return semestar;
    }

    // pod 4.


    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Predmet){
            return ((Predmet) obj).getNaziv().equals(this.naziv);
        }
        return false;
    }

    public int getGodinaFromSemestar(){
        return switch (this.semestar) {
            case 1, 2 -> 1;
            case 3, 4 -> 2;
            case 5, 6 -> 3;
            case 7, 8 -> 4;
            default -> 0;
        };
    }
}
