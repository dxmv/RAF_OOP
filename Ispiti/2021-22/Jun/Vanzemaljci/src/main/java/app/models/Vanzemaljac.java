package app.models;

public class Vanzemaljac {
    private int id;
    private Drzava drzava;

    public Vanzemaljac(int id, Drzava drzava) {
        this.id = id;
        this.drzava = drzava;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Drzava getDrzava() {
        return drzava;
    }

    public void setDrzava(Drzava drzava) {
        this.drzava = drzava;
    }
}
