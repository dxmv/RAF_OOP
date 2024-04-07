public class EmojiPoruka extends Poruka {
    private Emoji emoji;

    public EmojiPoruka(String posiljalac,Emoji emoji) {
        super(posiljalac);
        this.emoji = emoji;
    }

    public Emoji getEmoji() {
        return emoji;
    }

    public void setEmoji(Emoji emoji) {
        this.emoji = emoji;
    }


    // pod 3.
    @Override
    protected String formirajSadrzinu() {
        // vraća skracenicu emoji-ja
        return emoji.getSkracenica();
    }
}
