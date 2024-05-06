package app;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RezervacijeProzor extends VBox {
    // deo filter
    private ComboBox<String> cBoxtTip = new ComboBox<>();
    private TextField tfPocetak = new TextField("2024-05-06");

    private Label lblCrta = new Label("-");
    private TextField tfKraj = new TextField("2024-05-07");

    private Button btnPretrazi = new Button("Pretrazi");
    // ostalo
    private TableView<Rezervacija> tvRezervacije = new TableView<>();
    private Button btnSnimi = new Button("Snimi");
    private Kontekst k;
    public RezervacijeProzor(Kontekst k){
        this.k = k;

        setAlignment(Pos.CENTER);
        setPadding(new Insets(10));
        setSpacing(15);

        // gornji deo
        HBox hBoxTop = new HBox(10);
        hBoxTop.setAlignment(Pos.CENTER);
        hBoxTop.getChildren().addAll(cBoxtTip,tfPocetak,lblCrta,tfKraj);

        getChildren().addAll(hBoxTop,btnPretrazi,tvRezervacije,btnSnimi);
        initKolone();
        initPodaci();
        initAkcije();

    }

    private void initKolone(){
        TableColumn<Rezervacija,String> tcTip = new TableColumn<>("Tip");
        TableColumn<Rezervacija,String> tcMarka = new TableColumn<>("Marka");
        TableColumn<Rezervacija,String> tcModel = new TableColumn<>("Model");
        TableColumn<Rezervacija,Double> tcCenaPoDanu = new TableColumn<>("Cena po danu");
        TableColumn<Rezervacija, LocalDate> tcOd = new TableColumn<>("Od");
        TableColumn<Rezervacija, LocalDate> tcDo = new TableColumn<>("Do");
        TableColumn<Rezervacija, Double> tcUkupno = new TableColumn<>("Ukupna cena");

        tcTip.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getV().getTip()));
        tcMarka.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getV().getMarka()));
        tcModel.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getV().getModel()));
        tcCenaPoDanu.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getV().getCenaPoDanu()).asObject());
        tcOd.setCellValueFactory(new PropertyValueFactory<Rezervacija,LocalDate>("pocetakRezervacije"));
        tcDo.setCellValueFactory(new PropertyValueFactory<Rezervacija,LocalDate>("krajRezervacije"));
        tcUkupno.setCellValueFactory(new PropertyValueFactory<Rezervacija,Double>("ukupnaCena"));

        tvRezervacije.getColumns().addAll(tcTip,tcMarka,tcModel,tcCenaPoDanu,tcOd,tcDo,tcUkupno);
    }
    private void initPodaci(){
        tvRezervacije.getItems().addAll(k.getRezervacije());

        // dodajemo elemente u combo box
        cBoxtTip.getItems().add("Sve");
        // prolazimo kroz sve tipove u kontekstu i onda samo dodajemo
        cBoxtTip.getItems().addAll(k.getTipovi().stream().map(mp->mp.getTip()).toList());
    }
    private void initAkcije(){
        btnPretrazi.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String tipFilter = cBoxtTip.getValue();
                // ako nista nije selektovano u combo boxu izlazimo iz fn
                if(tipFilter == null){
                    return;
                }

                try{
                    List<Rezervacija> filtrirano = k.filtrirajRezervacije(tipFilter,tfPocetak,tfKraj);
                    tvRezervacije.getItems().clear();
                    tvRezervacije.getItems().addAll(filtrirano);
                }catch (RuntimeException ex){
                    ex.printStackTrace();
                }

            }
        });

        // ispis rezervacija u tekstualni fajl izlaz.txt iz tabele
        btnSnimi.setOnMouseClicked(new EventHandler<MouseEvent>() {
            private final String IME_FAJLA = "izlaz.txt";
            @Override
            public void handle(MouseEvent event) {
                String tipFilter = cBoxtTip.getValue();
                // ako nista nije selektovano u combo boxu izlazimo iz fn
                if(tipFilter == null){
                    return;
                }
                // ispis treba grupisati po tipu vozila
                // Ukoliko je pretražen bio konkretan tip vozila, ispis će biti grupisan po tom jednom tipu
                if(tipFilter != "Sve"){
                    // ovo znaci da su tv prikazane samo rezervacije zadatog tipa
                    List<Rezervacija> rezervacije = new java.util.ArrayList<>(tvRezervacije.getItems().stream().toList());

                    // Rezervacije treba da budu sortirane po početnom
                    // datumu iznajmljivanja i marki rastuće
                    rezervacije.sort((e1, e2) -> {
                        if (e1.getPocetakRezervacije().equals(e2.getPocetakRezervacije())) {
                            return e1.getV().getMarka().compareTo(e2.getV().getMarka());
                        }
                        return e1.getPocetakRezervacije().compareTo(e2.getPocetakRezervacije());
                    });
                    ispisSamoJedanTip(rezervacije,tipFilter);
                }
                // kada je izabrano sve treba grupisati po svim tipovima
                else{
                    HashMap<String,List<Rezervacija>> grupisano = k.grupisiRezervacije(tvRezervacije.getItems().stream().toList());

                    // Rezervacije treba da budu sortirane po početnom
                    // datumu iznajmljivanja i marki rastuće
                    for(Map.Entry<String,List<Rezervacija>> r : grupisano.entrySet()){
                        r.getValue().sort((e1, e2) -> {
                            if (e1.getPocetakRezervacije().equals(e2.getPocetakRezervacije())) {
                                return e1.getV().getMarka().compareTo(e2.getV().getMarka());
                            }
                            return e1.getPocetakRezervacije().compareTo(e2.getPocetakRezervacije());
                        });
                    }
                    ispisSve(grupisano);
                }
            }
            private void ispisSamoJedanTip(List<Rezervacija> lista, String tip){
                try(FileWriter fw = new FileWriter(IME_FAJLA)){
                    ispisTipa(fw,lista,tip);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            private void ispisSve(HashMap<String,List<Rezervacija>> grupisano){
                try(FileWriter fw = new FileWriter(IME_FAJLA)){
                    double rez = 0;
                    for(Map.Entry<String,List<Rezervacija>> entry : grupisano.entrySet()){
                        rez += ispisTipa(fw,entry.getValue(),entry.getKey());
                        fw.write("--------------------------------------------------------\n");
                    }
                    fw.write("Ukupna zarada: $" + rez);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            private double ispisTipa(FileWriter fw,List<Rezervacija> lista,String tip) throws IOException {
                fw.write("Kategorije: " + tip + "\n");
                double ukupno = 0;
                for(Rezervacija r:lista){
                    fw.write(r.getV().getMarka() + ", " + r.getV().getModel() + ":$" + r.getV().getCenaPoDanu() +
                            "-> od:" + r.getPocetakRezervacije() + "do: " + r.getKrajRezervacije() + "-----" + r.getUkupnaCena() + "\n");
                    ukupno += r.getUkupnaCena();
                }
                fw.write("Ukupno: " + ukupno + "\n");
                return ukupno;
            }
        });
    }
}
