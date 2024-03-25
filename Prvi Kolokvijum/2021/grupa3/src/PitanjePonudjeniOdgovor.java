import java.util.ArrayList;
import java.util.List;

public class PitanjePonudjeniOdgovor extends Pitanje{
    private List<String> ponudjeniOdgovor;

    public PitanjePonudjeniOdgovor(String tekstPitanja) {
        super(tekstPitanja);
        this.ponudjeniOdgovor = new ArrayList<>();
    }

    public List<String> getPonudjeniOdgovor() {
        return ponudjeniOdgovor;
    }

    public void setPonudjeniOdgovor(List<String> ponudjeniOdgovor) {
        this.ponudjeniOdgovor = ponudjeniOdgovor;
    }

    public void dodajOdgovor(String odgovor){
        // ne dodajemo odgovor ako se nalazi u kolekciji
        if(ponudjeniOdgovor.contains(odgovor)){
            return;
        }
        ponudjeniOdgovor.add(odgovor);
    }

    // pod 2.
    @Override
    public String vratiZaStampu() {
        // za pitanje sa ponuđenim odgovorima ispisuju se svi ponuđeni odgovori
        StringBuilder sb = new StringBuilder(super.vratiZaStampu() + "\n");
        for(String odgovor:ponudjeniOdgovor){
            sb.append(odgovor + "\n");
        }
        return sb.toString();
    }

    @Override
    public boolean spremnoZaStampu() {
        // pitanje sa ponuđenim odgovorima je spremno za štampu ako ima barem dva ponuđena odgovora
        return super.spremnoZaStampu() && ponudjeniOdgovor.size()>=2;
    }
}
