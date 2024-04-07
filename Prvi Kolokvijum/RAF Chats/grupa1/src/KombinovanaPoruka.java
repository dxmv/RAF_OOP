import java.util.ArrayList;
import java.util.List;

public class KombinovanaPoruka extends Poruka{
    private List<Poruka> sadrzina;
    public KombinovanaPoruka(String posiljalac) {
        super(posiljalac);
        this.sadrzina = new ArrayList<>();
    }

    public List<Poruka> getSadrzina() {
        return sadrzina;
    }

    public void setSadrzina(List<Poruka> sadrzina) {
        this.sadrzina = sadrzina;
    }

    // pod 3.
    @Override
    protected String formirajSadrzinu() {
        // vraća se tekst dobijen od sadrzine poruke (koju čine druge poruke)
        // u tom tekstu nalaze se sve poruke iz sadrzine, razdvojene razmakom (spejs)
        StringBuilder sb = new StringBuilder();
        for(Poruka p : sadrzina){
            sb.append(p.formirajSadrzinu()).append(" ");
        }
        return sb.toString();
    }

    // pod 4.
    public void dodajUSadrzinu(Poruka p){
        // dodaje poruku u sadrzinu na sledeći način:
        // poruka (kog god tipa) se ne može dodati u KombinovanuPoruku ako nemaju istog pošiljaoca
        if(this.getPosiljalac().equals(p.getPosiljalac())){
            // ukoliko je poruka tipa KombinovanaPoruka, sve poruke koje čine njenu sadrzinu se posebno dodaju
            if(p instanceof KombinovanaPoruka kp){
                sadrzina.addAll(kp.getSadrzina());
            }
            else if(p instanceof EmojisPoruka ep){
                // Prilikom dodavanja ove poruke u kombinovanu poruku, potrebno je dodati sve emoji-je
                // pojedinačno u okviru poruke kojoj je pošiljalac pošiljalac poruke koja se dodaje
                for(Emoji e : ep.getEmojiList()){
                    sadrzina.add(new EmojiPoruka(ep.getPosiljalac(),e));
                }
            }

            // ukoliko poruka nije KombinovanaPoruka, dodaje se samo poruka bez dodatnih operacija
            else{
                this.sadrzina.add(p);
            }
        }
    }
}
