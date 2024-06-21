package app.models;

public class Ucionica {
    private String ime;
    private boolean amfiteatar;
    private boolean racunari;

    public Ucionica(String ime, boolean amfiteatar, boolean racunari) {
        this.ime = ime;
        this.amfiteatar = amfiteatar;
        this.racunari = racunari;
    }

    @Override
    public String toString() {
        if(!racunari && !amfiteatar){
            return ime;
        }
        else if(racunari){
            return ime + "-R";
        }
        return ime + "-A";
    }

    public Ucionica(String ime) {
        this(ime,false,false);
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public boolean isAmfiteatar() {
        return amfiteatar;
    }

    public void setAmfiteatar(boolean amfiteatar) {
        this.amfiteatar = amfiteatar;
    }

    public boolean isRacunari() {
        return racunari;
    }

    public void setRacunari(boolean racunari) {
        this.racunari = racunari;
    }
}
