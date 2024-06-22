package app.models;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String ime;
    private String prezime;

    private String email;
    private List<Double> brojPoena = new ArrayList<>();

    private String termin;
    private double ukupnoPoena;
    private double umlPoeni;

    public Student(String ime, String prezime,String email, String termin) {
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.termin = termin;
        ukupnoPoena = 0;
        this.brojPoena = new ArrayList<>();
        umlPoeni = 0;
    }

    public void dodajPoene(Double p){
        ukupnoPoena += p;
        brojPoena.add(p);
    }

    @Override
    public String toString() {
        return this.ime + " " + this.prezime + " " + this.email + " " + this.ukupnoPoena;
    }

    public double getUkupnoPoena() {
        return ukupnoPoena;
    }

    public void setUkupnoPoena(double ukupnoPoena) {
        this.ukupnoPoena = ukupnoPoena;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTermin() {
        return termin;
    }

    public void setTermin(String termin) {
        this.termin = termin;
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

    public List<Double> getBrojPoena() {
        return brojPoena;
    }

    public void setBrojPoena(List<Double> brojPoena) {
        this.brojPoena = brojPoena;
    }

    public double getUmlPoeni() {
        return umlPoeni;
    }

    public void setUmlPoeni(double umlPoeni) {
        this.umlPoeni = umlPoeni;
    }
}
