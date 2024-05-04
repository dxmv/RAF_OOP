package app;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

// kreiramo glavni prozor aplikacije
public class GlavniProzor extends VBox {
    // Gornji deo - deo za filtriranje
    private Label lblFilter = new Label("Filter:");
    private Label lblImeFilma = new Label("Deo/Celo ime filma:");
    private Label lblGodina = new Label("Godina:");

    private ComboBox faze = new ComboBox();
    private CheckBox cbFilterFaze = new CheckBox("Primeni filter za faze");
    private TextField tfDeoImena = new TextField();
    private TextField tfGodina = new TextField();

    private ComboBox<String> cmbOperator = new ComboBox<>();
    private ComboBox<String> cmbAkcija = new ComboBox<>();

    private Button btnFiltriraj = new Button("Filtriraj");
    private Button btnPrikazSvih = new Button("Prikaz svih");

    // srednji deo - prikaz
    private ListView<Film> lvOdgledani = new ListView<>();
    private TableView<Film> tvFilmovi = new TableView<>();
    private Button btnOdgledani = new Button("Odgledani");
    private Button btnZapamti = new Button("Zapamti");

    // donji deo - statistika
    private Label lblStatistike = new Label("STATISTIKE");
    private Label lblSumarno = new Label("Sumarno");
    private Label lblOdgledani = new Label("Odgledani");
    private Label lblProsecnaOcena = new Label("Prosecna ocena:");
    private Label lblProsenaDuzina = new Label("Prosecna duzina trajanja: ");
    private TextField tfProsecnaOcenaSumrano = new TextField();
    private TextField tfProsecnaOcenaOdgledano = new TextField();
    private TextField tfProsecnaDuzinaSumrano = new TextField();
    private TextField tfProsecnaDuzinaOdgledano = new TextField();

    // podaci
    Kontekst k = new Kontekst();

    public GlavniProzor(){
        setPadding(new Insets(20));
        setSpacing(20);

        // gornji deo
        HBox hBoxTop = new HBox(10);
        hBoxTop.setAlignment(Pos.TOP_CENTER);

        lblFilter.setAlignment(Pos.TOP_LEFT);
        hBoxTop.getChildren().add(lblFilter);

        GridPane gpFilter = new GridPane();
        gpFilter.setHgap(10);
        gpFilter.setVgap(10);

        gpFilter.addRow(0,lblImeFilma,tfDeoImena);
        gpFilter.addRow(1,faze,cbFilterFaze);
        gpFilter.addRow(2,lblGodina,tfGodina,btnFiltriraj,btnPrikazSvih);

        hBoxTop.getChildren().add(gpFilter);
        // srednji deo
        HBox hBoxPrikaz = new HBox(10);
        hBoxPrikaz.setAlignment(Pos.CENTER);

        hBoxPrikaz.getChildren().addAll(tvFilmovi,lvOdgledani);
        tvFilmovi.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);

        HBox hBoxDugmaSredina = new HBox(10);
        hBoxDugmaSredina.setAlignment(Pos.CENTER);

        hBoxDugmaSredina.getChildren().addAll(btnOdgledani,btnZapamti);

        // donji deo - statistika
        GridPane gpStat = new GridPane();
        gpStat.setHgap(10);
        gpStat.setVgap(10);

        gpStat.addRow(0,lblStatistike);
        gpStat.addRow(1,lblSumarno,lblOdgledani);
        gpStat.addRow(2,lblProsecnaOcena,tfProsecnaOcenaSumrano,tfProsecnaOcenaOdgledano);
        gpStat.addRow(3,lblProsenaDuzina,tfProsecnaDuzinaSumrano,tfProsecnaDuzinaOdgledano);

        gpStat.setAlignment(Pos.CENTER);

        getChildren().addAll(hBoxTop,hBoxPrikaz,hBoxDugmaSredina,gpStat);

        initKolone();
        initPodaci();
        initDogadjaji();
    }

    private void initKolone(){
        TableColumn<Film, String> tcIme = new TableColumn<>("Ime");
        TableColumn<Film, String> tcFaza = new TableColumn<>("Faza");
        TableColumn<Film, Double> tcOcena = new TableColumn<>("Ocena");
        TableColumn<Film, Integer> tcGodina = new TableColumn<>("Godina");
        TableColumn<Film, Integer> tcDuzina = new TableColumn<>("Duzina");
        TableColumn<Film, Boolean> tcOdgledano = new TableColumn<>("Odgledano");

        tcIme.setCellValueFactory(new PropertyValueFactory<Film,String>("ime"));
        tcFaza.setCellValueFactory(new PropertyValueFactory<Film,String>("faza"));
        tcOcena.setCellValueFactory(new PropertyValueFactory<Film,Double>("ocena"));
        tcGodina.setCellValueFactory(new PropertyValueFactory<Film,Integer>("godinaIzlaska"));
        tcDuzina.setCellValueFactory(new PropertyValueFactory<Film,Integer>("duzinaTrajanja"));
        tcOdgledano.setCellValueFactory(new PropertyValueFactory<Film,Boolean>("odgledan"));

        tvFilmovi.getColumns().addAll(tcIme, tcFaza, tcOcena, tcDuzina, tcOdgledano);
    }

    private void initPodaci(){
        // statistika
        tfProsecnaOcenaSumrano.setText("%.2f".formatted(k.getProsecnaOcenaFilmova(k.getSviFilmovi())));
        tfProsecnaDuzinaSumrano.setText("%.2f".formatted(k.getProsecnaDuzinaFilmova(k.getSviFilmovi())));
        tfProsecnaOcenaOdgledano.setText("0.00");
        tfProsecnaDuzinaOdgledano.setText("0.00");

        // faze
        faze.getItems().addAll(k.getFazeFilmova());
    }

    private void initDogadjaji(){
        // kada se klikne na ovo dugme pokazi sve
        btnPrikazSvih.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                tvFilmovi.getItems().clear();
                tvFilmovi.getItems().addAll(k.getSviFilmovi());
            }
        });

        // kada se klikne na ovo dugme uzmes sve selektovane i dodas u listview
        btnOdgledani.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                List<Film> selectedItems = tvFilmovi.getSelectionModel().getSelectedItems();
                for(Film f : selectedItems){
                    // film je odgledan i dodajemo ga u listu u kontekstu
                    f.setOdgledan(true);
                    k.getOdgledani().add(f);
                }
                lvOdgledani.getItems().addAll(selectedItems);
                // refreshujemo statistiku odgledanih
                tfProsecnaOcenaOdgledano.setText("%.2f".formatted(k.getProsecnaOcenaFilmova(k.getOdgledani())));
                tfProsecnaDuzinaOdgledano.setText("%.2f".formatted(k.getProsecnaDuzinaFilmova(k.getOdgledani())));
                // refreshujemo table view
                tvFilmovi.refresh();
            }
        });

        // filtriranje
        btnFiltriraj.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(cbFilterFaze.isSelected()){
                    // samo po combo boxu, kada nije prazan
                    if(faze.getValue() != null){
                        tvFilmovi.getItems().clear();
                        tvFilmovi.getItems().addAll(k.filtriranjeFaza(faze.getValue()+""));
                    }
                }
                else{
                    // ako godina nije zadata onda samo filtriramo po imenu
                    if(tfGodina.getText().isEmpty()){
                        tvFilmovi.getItems().clear();
                        tvFilmovi.getItems().addAll(k.filtriraniFilmovi(tfDeoImena.getText()));
                    }
                    else{
                        try{
                            tvFilmovi.getItems().clear();
                            tvFilmovi.getItems().addAll(k.filtriraniFilmovi(tfDeoImena.getText(),Integer.parseInt(tfGodina.getText())));
                        }
                        catch (RuntimeException e){
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        // pisanje u fajl
        btnZapamti.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String imeFajla = "pregled.txt";
                // prvo pravimo hash mapu filmova
                TreeMap<Integer,List<String>> godineFilmova = new TreeMap<>();

                // prolazimo kroz sve filmove i za svaki gledamo da li se njegova godina nalazi u mapi
                for(Film f:k.getOdgledani()) {
                    // ako se godina nalazi dodajemo ga u listu
                    if(godineFilmova.containsKey(f.getGodinaIzlazka())){
                        godineFilmova.get(f.getGodinaIzlazka()).add(f.getIme());
                    }
                    // ako se ne nalazi, pravimo novu listu i dodajemo trenutni film
                    else{
                        List<String> lista =new ArrayList<>();
                        lista.add(f.getIme());
                        godineFilmova.put(f.getGodinaIzlazka(),lista);
                    }
                }

                // pisanje u fajl
                try(FileWriter fw = new FileWriter(imeFajla)){
                    // prvo pisemo godinu pa filmove te godine
                    for(Map.Entry<Integer,List<String>> el: godineFilmova.entrySet()){
                        fw.write(el.getKey()+"\n");
                        for(String s : el.getValue()){
                            fw.write("\t" + s + "\n");
                        }
                        fw.write("\n");
                    }
                    // za statistiku samo napisemo statistiku odgledanih filmova
                    fw.write("\n\nSTATISTIKE >>>\n");
                    fw.write("\tProsecna ocena: %.2f\n".formatted(k.getProsecnaOcenaFilmova(k.getOdgledani())));
                    fw.write("\tProsecna duzina: %.2f".formatted(k.getProsecnaDuzinaFilmova(k.getOdgledani())));
                }
                catch (IOException e){
                    e.printStackTrace();
                }

            }
        });
    }
}
