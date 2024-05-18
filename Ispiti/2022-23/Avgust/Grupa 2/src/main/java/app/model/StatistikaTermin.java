package app.model;

public class StatistikaTermin {
    private String grupa;
    private String dan;
    private int brojCasova;

    public StatistikaTermin(String grupa, String dan, int brojCasova) {
        this.grupa = grupa;
        this.dan = dan;
        this.brojCasova = brojCasova;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof StatistikaTermin termin){
            return termin.getDan().equals(this.dan) && termin.getGrupa().equals(this.grupa);
        }
        return false;
    }

    // vraca int reprezentaciju dana u nedelji
    public int danNedelje(){
        return switch (dan) {
            case "PON" -> 1;
            case "UTO" -> 2;
            case "SRE" -> 3;
            case "ČET" -> 4;
            case "PET" -> 5;
            case "SUB" -> 6;
            case "NED" -> 7;
            default -> 8;
        };
    }

    public String getGrupa() {
        return grupa;
    }

    public void setGrupa(String grupa) {
        this.grupa = grupa;
    }

    public String getDan() {
        return dan;
    }

    public void setDan(String dan) {
        this.dan = dan;
    }

    public int getBrojCasova() {
        return brojCasova;
    }

    public void setBrojCasova(int brojCasova) {
        this.brojCasova = brojCasova;
    }
}
