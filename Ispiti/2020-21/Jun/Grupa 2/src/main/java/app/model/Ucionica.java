package app.model;

public class Ucionica {
    private String ime;
    private int dozvoljenBroj;

    public Ucionica(String ime, int dozvoljenBroj) {
        this.ime = ime;
        this.dozvoljenBroj = dozvoljenBroj;
    }

    // ucionice su iste ako imaju isto ime
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Ucionica u){
            return u.getIme().equals(this.ime);
        }
        return false;
    }

    @Override
    public String toString(){
        return this.ime;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public int getDozvoljenBroj() {
        return dozvoljenBroj;
    }

    public void setDozvoljenBroj(int dozvoljenBroj) {
        this.dozvoljenBroj = dozvoljenBroj;
    }
}
