package klase;

public abstract class Aktivnost {
    private String naziv;

    private Smer smer;
    private double minimum;
    private double maximum;

    public Aktivnost(String naziv, Smer smer, double minimum, double maximum) {
        this.naziv = naziv;
        this.smer = smer;
        this.minimum = minimum;
        this.maximum = maximum;
    }

    public abstract int izracunajOcenu(double brojPoena);


    // Dve konkretne aktivnosti su iste ako imaju isti naziv i smer.
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Aktivnost){
            Aktivnost a = (Aktivnost) obj;
            return this.naziv.equals(a.getNaziv()) && this.smer.equals(a.getSmer());
        }
        return false;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Smer getSmer() {
        return smer;
    }

    public void setSmer(Smer smer) {
        this.smer = smer;
    }

    public double getMinimum() {
        return minimum;
    }

    public void setMinimum(double minimum) {
        this.minimum = minimum;
    }

    public double getMaximum() {
        return maximum;
    }

    public void setMaximum(double maximum) {
        this.maximum = maximum;
    }
}
