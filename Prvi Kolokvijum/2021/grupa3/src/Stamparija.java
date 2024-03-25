import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Stamparija {
    private List<ZaStampanje> zaStampanje;

    public Stamparija() {
        this.zaStampanje = new ArrayList<>();
    }

    public List<ZaStampanje> getZaStampanje() {
        return zaStampanje;
    }

    public void setZaStampanje(List<ZaStampanje> zaStampanje) {
        this.zaStampanje = zaStampanje;
    }

    public void dodajZaStampanje(ZaStampanje el){
        zaStampanje.add(el);
    }

    public void odstampajSve(boolean fajl,String imeFajla){
        if(fajl){
            odstampajFajl(imeFajla);
        }
        else{
            odstampajKonzola();
        }
    }

    private void odstampajKonzola(){
        for(ZaStampanje s: zaStampanje){
            if(s.spremnoZaStampu()){
                System.out.println(s.vratiZaStampu());
            }
            else{
                System.out.println("Nije spremno"); // ako nisu spremni za stampu
            }
        }
    }

    // pod 5.
    private void odstampajFajl(String imeFajla){
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(imeFajla));
            for(ZaStampanje s: zaStampanje){
                if(s.spremnoZaStampu()){
                    writer.write(s.vratiZaStampu());
                }
                else{
                    writer.write("Nije spremno"); // ako nisu spremni za stampu
                }
            }
            writer.close();
        }
        catch (IOException e){
            System.out.println("Greska pri pisanju fajla");
        }
    }

    // pod 3.
    public void odstampajOdDo(int odBroj, int doBroj){
        //  treba na standardni izlaz da odštampa elemente koje se nalaze u kolekciji elemenata za štampanje na indeksima između dva zadata argumenta
        for(int i=odBroj;i<doBroj;i++){
            if(zaStampanje.get(i).spremnoZaStampu()){
                System.out.println(zaStampanje.get(i).vratiZaStampu());
            }
            else{
                System.out.println("Nije spremno"); // ako nisu spremni za stampu
            }
        }
    }
}
