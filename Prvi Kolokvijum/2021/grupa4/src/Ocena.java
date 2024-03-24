// pod 7.
public class Ocena {
    private int donjaGranica;
    private int gornjaGranica;
    private int ocena;

    public Ocena(int donjaGranica, int gornjaGranica, int ocena) {
        this.donjaGranica = donjaGranica;
        this.gornjaGranica = gornjaGranica;
        this.ocena = ocena;
    }

    public int getDonjaGranica() {
        return donjaGranica;
    }

    public void setDonjaGranica(int donjaGranica) {
        this.donjaGranica = donjaGranica;
    }

    public int getGornjaGranica() {
        return gornjaGranica;
    }

    public void setGornjaGranica(int gornjaGranica) {
        this.gornjaGranica = gornjaGranica;
    }

    public int getOcena() {
        return ocena;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }


    public boolean brojPoenaUIntervalu(int brojPoena){
        // proverava da li zadati brojPoena pripada intervalu, da li je kandidat dobiju ovu ocenu
        return donjaGranica <= brojPoena && gornjaGranica >= brojPoena;
    }

    @Override
    public boolean equals(Object obj) {
        // proverava da li se intervali poklapaju
        if(obj instanceof Ocena o){
            return !(o.getDonjaGranica() > this.gornjaGranica || o.getGornjaGranica() < this.donjaGranica);
        }
        return false;
    }
}
