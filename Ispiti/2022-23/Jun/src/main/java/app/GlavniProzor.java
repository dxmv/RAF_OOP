package app;


import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


// kreiramo glavni prozor aplikacije
public class GlavniProzor extends VBox {
    // gornji deo
    private Label lblNaziv = new Label("RAF Coursera");
    private Label lblRaspolozivo = new Label("Raspolozivo: $403");

    // srednji deo
    private Label lblDostupni = new Label("Dostupni kursevi");
    private ListView<Kurs> lvDostupniKursevi = new ListView<>();

    private Button btnDodajKurs = new Button("Dodaj kurs");
    private Label lblMojiKursevi = new Label("Moji kursevi");
    private ListView<Kurs> lvMojiKursevi = new ListView<>();

    private Label lblPocetak = new Label("Pocetak aktivnosti");
    private Label lblSati = new Label("Sati");
    private Label lblMinuti = new Label("Minuti");
    private Label lblTrajanje = new Label("Trajanje aktivnosti");
    private Label lblMax = new Label("(max 120 min)");

    private ComboBox<Integer> comboBoxSati = new ComboBox<>();
    private ComboBox<Integer> comboBoxMinuti = new ComboBox<>();
    private TextField tfTrajanje = new TextField();
    private Button btnOdgledaj = new Button("Odgledaj");

    private Label lblGreska = new Label("");
    // donja polovina
    private TableView<Aktivnost> tvAktivnost = new TableView<>();
    private Button btnSnimiAktivnosti = new Button("Snimi aktivnosti");

    private Kontekst k;

    public GlavniProzor(){
        k = new Kontekst();

        setAlignment(Pos.TOP_CENTER);
        setSpacing(10);
        setPadding(new Insets(10));
        lblRaspolozivo.setText("Raspolozivo: $%d".formatted(k.getNovac()));

        // srednji deo
        HBox hBoxSredina = new HBox(10);
        hBoxSredina.setAlignment(Pos.CENTER);

        // dostupni kursevi
        VBox vBoxDostupni = new VBox(10);
        vBoxDostupni.setAlignment(Pos.CENTER);
        vBoxDostupni.getChildren().addAll(lblDostupni,lvDostupniKursevi);


        // moji kursevi
        VBox vBoxMoji = new VBox(10);
        vBoxMoji.setAlignment(Pos.CENTER);
        vBoxMoji.getChildren().addAll(lblMojiKursevi,lvMojiKursevi);

        // forma
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(10);
        gridPane.setHgap(5);

        gridPane.add(lblPocetak,0,0,4,1);
        // 2. red
        gridPane.addRow(1,lblSati,comboBoxSati,lblMinuti,comboBoxMinuti);
        gridPane.add(lblTrajanje,0,2,4,1);
        // 4.red
        gridPane.add(tfTrajanje,1,3,1,1);
        gridPane.add(lblMax,2,3,1,1);

        gridPane.add(btnOdgledaj,0,4,4,1);
        gridPane.add(lblGreska,0,5,4,1);

        GridPane.setHalignment(lblPocetak, HPos.CENTER);
        GridPane.setHalignment(lblTrajanje, HPos.CENTER);
        GridPane.setHalignment(btnOdgledaj, HPos.CENTER);


        hBoxSredina.getChildren().addAll(vBoxDostupni,btnDodajKurs,vBoxMoji,gridPane);

        getChildren().addAll(lblNaziv,lblRaspolozivo,hBoxSredina,tvAktivnost,btnSnimiAktivnosti);

        initKolone();
        initPodaci();
        initEvents();
    }

    // popunjavanje elemenata podacima
    private void initPodaci(){
        // popunjavanje liste za dostupne kurseve
        lvDostupniKursevi.getItems().addAll(k.getSviKursevi());

        // popunjavanje comboBox za sate
        // koristi 24-casovni sat
        for(int i=0;i<24;i++){
            comboBoxSati.getItems().add(i);
        }

        // popunjavanje comboBox za minute
        // minute 0,5,10,15,20,…,60 prilikom popunjavanja kombo boksova.
        for(int i = 0; i<60;i+=5){
            comboBoxMinuti.getItems().add(i);
        }
    }

    // kolone za tableview
    private void initKolone(){
        TableColumn<Aktivnost,String> tcNaziv = new TableColumn<>("Kurs");
        TableColumn<Aktivnost,String> tcKategorija = new TableColumn<>("Kategorija");
        TableColumn<Aktivnost, LocalDate> tcPocetakDatum = new TableColumn<>("Pocetak (Datum)");
        TableColumn<Aktivnost, LocalTime> tcPocetakVreme = new TableColumn<>("Pocetak (Vreme)");
        TableColumn<Aktivnost, LocalDate> tcKrajDatum = new TableColumn<>("Kraj (Datum)");
        TableColumn<Aktivnost, LocalTime> tcKrajVreme = new TableColumn<>("Kraj (Vreme)");
        TableColumn<Aktivnost,Double> tcKompletirano = new TableColumn<>("Kompletiranost");

        tcNaziv.setCellValueFactory(new PropertyValueFactory<Aktivnost,String>("naziv"));
        tcKategorija.setCellValueFactory(new PropertyValueFactory<Aktivnost,String>("kategorija"));
        tcPocetakDatum.setCellValueFactory(new PropertyValueFactory<Aktivnost,LocalDate>("pocetakDatum"));
        tcPocetakVreme.setCellValueFactory(new PropertyValueFactory<Aktivnost,LocalTime>("pocetakVreme"));
        tcKrajDatum.setCellValueFactory(new PropertyValueFactory<Aktivnost,LocalDate>("pocetakDatum"));
        tcKrajVreme.setCellValueFactory(new PropertyValueFactory<Aktivnost,LocalTime>("krajVreme"));
        tcKompletirano.setCellValueFactory(new PropertyValueFactory<Aktivnost,Double>("kompletiranost"));
        tvAktivnost.getColumns().addAll(tcNaziv,tcKategorija,tcPocetakDatum,tcPocetakVreme,tcKrajDatum,tcKrajVreme,tcKompletirano);
    }

    private void initEvents(){
        // dodaje kurs u moje kurseve
        btnDodajKurs.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Kurs selectedKurs = lvDostupniKursevi.getSelectionModel().getSelectedItem();
                try{
                    k.dodajKursUMojeKurseve(selectedKurs);
                    // refresh raspolozivo i listview
                    lvMojiKursevi.getItems().add(selectedKurs);
                    lblRaspolozivo.setText("Raspolozivo: $" + k.getNovac());
                }
                catch (RuntimeException ex){
                    ex.printStackTrace();
                }
            }
        });

        // dodaje novu aktivnost
        btnOdgledaj.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                // provera da li su prazni comboboxovi i textfield, vracamo se ako jesu
                if(comboBoxSati.getValue() == null || comboBoxMinuti.getValue() == null || tfTrajanje.getText().isEmpty()){
                    lblGreska.setText("Morate popuniti sve vrednosti");
                    return;
                }
                // uzimamo podatke
                int pocetakSati = comboBoxSati.getValue();
                int pocetakMinuti = comboBoxMinuti.getValue();
                int trajanje = 0;
                try{
                    trajanje = Integer.parseInt(tfTrajanje.getText());
                }
                catch (NumberFormatException ex){
                    lblGreska.setText(ex.getMessage());
                    return;
                }

                // mora da traje od 1 do 120 minuta
                if(trajanje < 1 || trajanje > 120){
                    lblGreska.setText("Aktivnost mora trajati izmedju 1 i 120 minuta");
                    return;
                }


                Kurs kurs = lvMojiKursevi.getSelectionModel().getSelectedItem();
                if(kurs == null){
                    lblGreska.setText("Morate izabrati kurs iz liste moji kursevi");
                    return;
                }
                // pokusavamo da dodamo
                try{
                    Aktivnost a = k.dodajAktivnost(kurs,pocetakSati,pocetakMinuti,trajanje);
                    // refresh tableview
                    tvAktivnost.getItems().add(a);
                    lblGreska.setText("");
                }
                catch (RuntimeException ex){
                    lblGreska.setText(ex.getMessage());
                }
            }
        });

        // zapis u fajl
        btnSnimiAktivnosti.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                // mapa za kategoriju i aktivnost
                HashMap<String, List<Aktivnost>> mapa = k.kategorijeAktivnosti();
                // pisanje u fajl
                try (FileWriter fw = new FileWriter("izlaz_moj.txt")){
                    for(Map.Entry<String,List<Aktivnost>> entry : mapa.entrySet()){
                        fw.write("Kategorija: " + entry.getKey() + "\n");
                        for(Aktivnost a : entry.getValue()){
                            fw.write("Naziv kursa: " + a.getK().getNaziv() + "\n");
                            // nisam ovo uradio lepo
                        }
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        });

    }
}
