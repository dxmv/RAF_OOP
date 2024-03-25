import java.util.ArrayList;
import java.util.List;

// pod 4.
public class PitanjePopunjavanje extends Pitanje{
    private List<Integer> crtice;

    private int duzinaCrtice;
    public PitanjePopunjavanje(String tekstPitanja) {
        super(tekstPitanja);
        this.crtice = new ArrayList<>();
    }

    public List<Integer> getCrtice() {
        return crtice;
    }

    public void setCrtice(List<Integer> crtice) {
        if(crtice.size() != this.getBrojPoena() && !crtice.isEmpty()){
            // ima onoliko crtica koliko poena
            return;
        }
        // index crtica mora biti validan
        for(int c:crtice){
            if(!validniIndexCrtice(c)){
                return;
            }
        }
        this.crtice = crtice;
    }

    public int getDuzinaCrtice() {
        return duzinaCrtice;
    }

    public void setDuzinaCrtice(int duzinaCrtice) {
        this.duzinaCrtice = duzinaCrtice;
    }

    public void dodajIndexCrtice(int index){
        if(crtice.size() >= this.getBrojPoena()){
            return; // ima onoliko crtica koliko poena
        }
        // index crtice mora biti validan, da bi ga dodali
        if(validniIndexCrtice(index)){
            crtice.add(index);
        }
    }

    private boolean validniIndexCrtice(int index){
        return index >= 0 && index <= this.getTekstPitanja().length()-1; // index crtice je validan ako je veci od 0 i manji od duzine teksta
    }

    private void dodajCrte(StringBuilder stringBuilder){
        for(int i=0;i<duzinaCrtice;i++){
            stringBuilder.append("_");
        }
        stringBuilder.append(" ");
    }

    @Override
    public String vratiZaStampu() {
        StringBuilder sb = new StringBuilder(this.getRedniBroj() + ". (" + this.getBrojPoena() + ")\n");
        String[] reci = this.getTekstPitanja().split(" ");
        for(int i=0;i<reci.length;i++){
            boolean pisiCrtu = false;
            // da li se index nalazi u indexCrtica
            for(int crtica : crtice){
                if(crtica == i){
                    pisiCrtu = true;
                }
            }
            if(pisiCrtu){
                dodajCrte(sb);
            }
            else{
                sb.append(reci[i] + " ");
            }
        }
        sb.append("\n");
        return sb.toString();
    }

    @Override
    public boolean spremnoZaStampu() {
        // ovakvo pitanje može da se odštampa ako ima bar jedno mesto koje treba da se popuni
        return super.spremnoZaStampu() && !this.getCrtice().isEmpty();
    }
}
