package app.models;

public class Statistika {
    private String pitanje;
    private double max;

    private double ukupnoBodova;
    private int maxStudent;
    private int ukupnoStudenata;

    public Statistika(String pitanje, double max) {
        this.pitanje = pitanje;
        this.max = max;
        ukupnoBodova = 0;
        maxStudent = 0;
        ukupnoStudenata = 0;
    }

    public void dodajBodove(double p){
        // ako student ima max brojimo
        if(p==max){
            maxStudent ++;
        }
        ukupnoBodova += p;
        ukupnoStudenata ++;
    }

    public double prosek(){
        return (double) Math.round(ukupnoBodova / ukupnoStudenata * 100) / 100;
    }

    public double procenat(){
        return (double) Math.round((float) maxStudent / ukupnoStudenata * 100 * 100) / 100;
    }

    public String getPitanje() {
        return pitanje;
    }

    public void setPitanje(String pitanje) {
        this.pitanje = pitanje;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double getUkupnoBodova() {
        return ukupnoBodova;
    }

    public void setUkupnoBodova(double ukupnoBodova) {
        this.ukupnoBodova = ukupnoBodova;
    }

    public int getMaxStudent() {
        return maxStudent;
    }

    public void setMaxStudent(int maxStudent) {
        this.maxStudent = maxStudent;
    }

    public int getUkupnoStudenata() {
        return ukupnoStudenata;
    }

    public void setUkupnoStudenata(int ukupnoStudenata) {
        this.ukupnoStudenata = ukupnoStudenata;
    }
}
