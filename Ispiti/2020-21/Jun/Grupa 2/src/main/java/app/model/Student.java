package app.model;

public class Student {
    private String studijskiProgram;
    private int brojIndexa;
    private int godinaUpisa;
    private String prezime;
    private String ime;

    public Student(String studijskiProgram, int brojIndexa, int godinaUpisa, String prezime, String ime) {
        this.studijskiProgram = studijskiProgram;
        this.brojIndexa = brojIndexa;
        this.godinaUpisa = godinaUpisa;
        this.prezime = prezime;
        this.ime = ime;
    }

    @Override
    public String toString() {
        return this.studijskiProgram + "," + this.brojIndexa + "," + this.godinaUpisa + "," + this.prezime + "," + this.ime;
    }

    public String getStudijskiProgram() {
        return studijskiProgram;
    }

    public void setStudijskiProgram(String studijskiProgram) {
        this.studijskiProgram = studijskiProgram;
    }

    public int getBrojIndexa() {
        return brojIndexa;
    }

    public void setBrojIndexa(int brojIndexa) {
        this.brojIndexa = brojIndexa;
    }

    public int getGodinaUpisa() {
        return godinaUpisa;
    }

    public void setGodinaUpisa(int godinaUpisa) {
        this.godinaUpisa = godinaUpisa;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }
}
