package app.models;

public class Drzava {
    private String ime;
    private String kontinent;

    public Drzava(String kontinent) {
        this("",kontinent);
    }

    public Drzava(String ime, String kontinent) {
        this.ime = ime;
        this.kontinent = kontinent;
    }

    @Override
    public String toString() {
        return this.ime;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Drzava d){
            return d.ime.equals(this.ime);
        }
        return false;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getKontinent() {
        return kontinent;
    }

    public void setKontinent(String kontinent) {
        this.kontinent = kontinent;
    }
}
