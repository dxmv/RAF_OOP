public abstract class Poruka {
    private String posiljalac;

    public Poruka(String posiljalac) {
        this.posiljalac = posiljalac;
    }

    public String getPosiljalac() {
        return posiljalac;
    }

    public void setPosiljalac(String posiljalac) {
        this.posiljalac = posiljalac;
    }

    protected abstract String formirajSadrzinu();

    // pod 5.
    public String formirajIspis(){
        // treba da vrati string koji će zapravo biti ispisan prilikom prikazivanja poruke
        StringBuilder sb = new StringBuilder();
        // u prvom redu ispisa treba da stoji ko je pošiljalac poruke, nakon koga sledi jedan prazan red
        sb.append(this.posiljalac).append("\n");
        // u ostalim redovima ispisa treba da stoji sadržina poruke
        // između svaka dva reda sadržine (delovi sadržine poruke koji su odvojeni znakom za novi red) treba da stoji jedan prazan red
        for(String deo : this.formirajSadrzinu().split("\n")){
            sb.append("\t").append(deo).append("\n");
        }
        return sb.toString();
    }
}
