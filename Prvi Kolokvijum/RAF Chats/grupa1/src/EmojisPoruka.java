import java.util.ArrayList;
import java.util.List;

// pod 8.
// dodati novu vrstu poruke – poruku sa više emoji-ja i bez teksta
public class EmojisPoruka extends Poruka{
    private List<Emoji> emojiList;
    public EmojisPoruka(String posiljalac) {
        super(posiljalac);
        this.emojiList = new ArrayList<>();
    }

    public List<Emoji> getEmojiList() {
        return emojiList;
    }

    public void setEmojiList(List<Emoji> emojiList) {
        this.emojiList = emojiList;
    }

    @Override
    protected String formirajSadrzinu() {
        //  Prilikom formiranja sadržine ove poruke potrebno je za svaki od emoji-ja izvući skraćenicu,
        //  pa sve te skraćenice razdvojiti po jednim razmakom
        StringBuilder sb = new StringBuilder();
        for(Emoji e : emojiList){
            sb.append(e.getSkracenica()).append(" ");
        }
        return sb.toString();
    }

    // Obezbediti operaciju koja dodaje jedan ili više emoji-ja u sadržinu poruke
    public void dodajEmoji(Emoji e){
        emojiList.add(e);
    }

    public void dodajEmojis(List<Emoji> es){
        emojiList.addAll(es);
    }

}
