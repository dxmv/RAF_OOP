import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// pod 8.
public class ChatApp {
    public static List<String> NEUSPESNE_PORUKE = new ArrayList<>();
    public static void main(String[] args) {
        // Pet korisnika
        Sistem s = new Sistem();
        for(int i=1;i<=5;i++){
            Korisnik k = new Korisnik("Ime " + i, "Korisnicko ime" + i,"Lozinka"+i);
            s.registruj(k.getPrikazanoIme(),k.getKorisnickoIme(),k.getLozinka(),k.getLozinka());
            s.prijava(k.getKorisnickoIme(),k.getLozinka());
        }
        System.out.println(s.getRegistrovani().size()); // 5
        System.out.println(s.getPrijavljeni().size()); // 5

        // pod dva ceta
        List<Chat> chatovi = new ArrayList<Chat>();
        for(int i = 1; i<=2;i++){
            Korisnik k1 = s.getPrijavljeni().get(i);
            Korisnik k2 = s.getPrijavljeni().get(i + 1);
            Korisnik k3 = s.getPrijavljeni().get(i - 1);
            PublicChat publicChat = new PublicChat();
            publicChat.dodajPoruku(k1,"Public chat " + i);


            PrivateChat privateChat = new PrivateChat(k1,k2,null);
            privateChat.dodajPoruku(k1,"Private chat 1" + i);
            privateChat.dodajPoruku(k1,"Private chat 2" + i); // nece se dodati
            privateChat.dodajPoruku(k2,"Private chat 3" + i);

            JustMeChat justMeChat = new JustMeChat();
            justMeChat.setKorisnik(k2);
            justMeChat.dodajPoruku(k2,"Just me chat " + i);

            GroupChat gc = new GroupChat();
            gc.dodajUGrupu(k2);
            gc.dodajUGrupu(k3);
            gc.dodajPoruku(k3,"Group chat 1" + i);
            gc.dodajPoruku(k1,"Group chat 2" + i); // nece se dodati
            gc.dodajPoruku(k2,"Group chat 3"+i);

            chatovi.add(publicChat);
            chatovi.add(privateChat);
            chatovi.add(justMeChat);
            chatovi.add(gc);
        }

        // ispisivanje poruka
        for(Chat c:chatovi){
            System.out.println(c);
        }

        // pod 9.
        try(FileWriter fw = new FileWriter("neuspesne_poruke.txt")){
            for(String neuspeh:NEUSPESNE_PORUKE){
                fw.write(neuspeh);
                fw.write("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
