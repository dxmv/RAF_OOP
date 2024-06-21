package app.models;

public class Student {
    private String smer;
    private int brojIndexa;
    private int godina;
    private String ime;
    private String prezime;

    public Student(String smer, int brojIndexa, int godina, String ime, String prezime) {
        this.smer = smer;
        this.brojIndexa = brojIndexa;
        this.godina = godina;
        this.ime = ime;
        this.prezime = prezime;
    }

    @Override
    public String toString() {
        return this.ime + " " + this.prezime + " " + this.brojIndexa + "/" + this.smer + "-" + this.godina;
    }

    public String getSmer() {
        return smer;
    }

    public void setSmer(String smer) {
        this.smer = smer;
    }

    public int getBrojIndexa() {
        return brojIndexa;
    }

    public void setBrojIndexa(int brojIndexa) {
        this.brojIndexa = brojIndexa;
    }

    public int getGodina() {
        return godina;
    }

    public void setGodina(int godina) {
        this.godina = godina;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }
}
