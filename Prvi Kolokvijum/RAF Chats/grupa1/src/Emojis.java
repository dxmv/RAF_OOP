import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Klasa Emojis treba da bude odrađena po singlton šablonu,
// što znači da će postojati samo jedna instanca te klase u svakom trenutku
public class Emojis {
    private static Emojis instance = null;
    private static List<Emoji> emojis;
    private Emojis(){
        emojis = new ArrayList<>();
    }

    // pod 2.
    public static Emojis getEmojis(){
        // u metodi getEmojis pitati da li instance već postoji,
        // ako ne postoji instancirati je pomoću odgovarajućeg konstruktora i
        // onda vratiti tu instancu
        if(instance == null){
            instance = new Emojis();
        }
        return instance;
    }

    public List<Emoji> getEmojiList(){
        return emojis;
    }

    public boolean dodajEmoji(String naziv,String skracenica){
        // dodaje emoji u listu ako ne postoji emoji istog naziva ili iste skraćenice
        Emoji e = new Emoji(naziv,skracenica);
        if(emojis.contains(e)){
            return false;
        }
        emojis.add(e);
        // ukoliko je emoji dodat vraća true, u suprotnom false
        return true;
    }

    // pod 10.
    public void sacuvajUFajlu(String imeFajla){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(imeFajla));
            List<Emoji> sortirano = new ArrayList<>(emojis);
            Collections.sort(sortirano);
            for (Emoji e : sortirano){
                bw.write(e.toString() + "\n");
            }
            bw.close();
        } catch (IOException e) {
            System.out.println("Doslo je do greske pri pisanju u fajl.");
        }
    }
}
