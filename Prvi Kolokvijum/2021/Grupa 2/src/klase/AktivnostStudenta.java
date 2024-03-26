package klase;

public abstract class AktivnostStudenta implements Comparable<AktivnostStudenta>,Uslovljeno{
    private int godina;
    private int mesec;
    private int dan;

    public AktivnostStudenta(int godina, int mesec, int dan) {
        this.godina = godina;
        this.mesec = mesec;
        this.dan = dan;
    }

    public int getGodina() {
        return godina;
    }

    public void setGodina(int godina) {
        this.godina = godina;
    }

    public int getMesec() {
        return mesec;
    }

    public void setMesec(int mesec) {
        this.mesec = mesec;
    }

    public int getDan() {
        return dan;
    }

    public void setDan(int dan) {
        this.dan = dan;
    }

    // pod 2.


    @Override
    public int compareTo(AktivnostStudenta o) {
        if (this.godina != o.godina) { // uporedjujemo godine
            return Integer.compare(this.godina, o.godina);
        }
        if (this.mesec != o.mesec) { // godine su iste, meseci nisu, uporedjujemo mesece
            return Integer.compare(this.mesec, o.mesec);
        }
        return Integer.compare(this.dan, o.dan); // godine i meseci su iste, uporedjujemo dane
    }

    @Override
    public abstract boolean proveriUslov(Student student);

    public abstract int getGodinaStudija();

    // pod 6.
    @Override
    public String toString() {
        // ispis datuma
        return this.getDan() + "." + this.getMesec() + "." + this.getGodina();
    }
}
