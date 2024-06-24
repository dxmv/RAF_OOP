package app.models;

import java.util.Objects;

public class Statistika {
    private Drzava drzava;
    private int brojVanzemaljaca;

    public Statistika(Drzava drzava, int brojVanzemaljaca) {
        this.drzava = drzava;
        this.brojVanzemaljaca = brojVanzemaljaca;
    }

    public void dodaj(){
        this.brojVanzemaljaca ++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Statistika that = (Statistika) o;

        return Objects.equals(drzava, that.drzava);
    }

    public Drzava getDrzava() {
        return drzava;
    }

    public void setDrzava(Drzava drzava) {
        this.drzava = drzava;
    }

    public int getBrojVanzemaljaca() {
        return brojVanzemaljaca;
    }

    public void setBrojVanzemaljaca(int brojVanzemaljaca) {
        this.brojVanzemaljaca = brojVanzemaljaca;
    }
}
