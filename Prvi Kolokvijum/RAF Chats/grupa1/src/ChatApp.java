// pod 10.
public class ChatApp {
    public static void main(String[] args) {
        // 5 emojia
        Emojis emojis = Emojis.getEmojis();
        emojis.dodajEmoji("happy",":)");
        emojis.dodajEmoji("sad",":(");
        emojis.dodajEmoji("wink",";)");
        emojis.dodajEmoji("glasses","B)");
        emojis.dodajEmoji("happy",":)");

        System.out.println(emojis.getEmojiList().size()); // 4

        // 2 od svake poruke
        TekstualnaPoruka tp1 = new TekstualnaPoruka("P1","Tekst poruka 1");
        TekstualnaPoruka tp2 = new TekstualnaPoruka("P2","Tekst poruka 2");

        EmojiPoruka ep1 = new EmojiPoruka("P3",emojis.getEmojiList().get(1));
        EmojiPoruka ep2 = new EmojiPoruka("P1",emojis.getEmojiList().get(2));

        EmojisPoruka eps1 = new EmojisPoruka("P4");
        eps1.dodajEmoji(emojis.getEmojiList().get(1));
        eps1.dodajEmoji(emojis.getEmojiList().get(3));
        EmojisPoruka eps2 = new EmojisPoruka("P2");
        eps2.dodajEmoji(emojis.getEmojiList().get(2));
        eps2.dodajEmoji(emojis.getEmojiList().get(3));

        KombinovanaPoruka kp1 = new KombinovanaPoruka("P1");
        kp1.dodajUSadrzinu(tp1);
        kp1.dodajUSadrzinu(ep2);
        KombinovanaPoruka kp2 = new KombinovanaPoruka("P2");
        kp2.dodajUSadrzinu(tp2);
        kp2.dodajUSadrzinu(eps2);

        Chat chat = new Chat();
        chat.dodajPoruku(kp1);
        chat.dodajPoruku(kp2);
        chat.dodajPoruku(ep1);
        chat.dodajPoruku(eps1);
        System.out.println(chat.getPoruke().size()); // 4
        System.out.println(chat.getPosiljaoci().size()); // 4
        chat.ispisPoruka();

        // pod 11.
        emojis.sacuvajUFajlu("emojis.txt");
    }
}
