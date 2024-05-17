package app.model;

public class StatistikaTermin {
    private String ucionica;
    private String danNedelje;
    private int brojCasova;

    public StatistikaTermin(String ucionica, String danNedelje, int brojCasova) {
        this.ucionica = ucionica;
        this.danNedelje = danNedelje;
        this.brojCasova = brojCasova;
    }

    public String getUcionica() {
        return ucionica;
    }

    public void setUcionica(String ucionica) {
        this.ucionica = ucionica;
    }

    public String getDanNedelje() {
        return danNedelje;
    }

    public void setDanNedelje(String danNedelje) {
        this.danNedelje = danNedelje;
    }

    public int getBrojCasova() {
        return brojCasova;
    }

    public void setBrojCasova(int brojCasova) {
        this.brojCasova = brojCasova;
    }

    // isti su ako je isti dan nedelje i ista ucionica
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof StatistikaTermin termin){
            return this.danNedelje.equals(termin.getDanNedelje()) && this.ucionica.equals(termin.getUcionica());
        }
        return false;
    }
}
