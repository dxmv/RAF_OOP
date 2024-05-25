package app.views;


import app.controllers.Kontekst;
import app.model.Pitanje;
import app.model.Test;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


// kreiramo glavni prozor aplikacije
public class GlavniProzor extends VBox {
    // gornji deo
    private ListView<Pitanje> lvPitanja = new ListView<>();
    private TableView<Test> tvPokusaji = new TableView<>();
    // donji deo
    private ComboBox<String> cbPredmeti = new ComboBox<>();
    private Button btnUcitajZaPredmet = new Button("Ucitaj za predmet");
    private Button btnDodajPitanje = new Button("Dodaj pitanje");
    private Button btnSnimi = new Button("Snimi");
    private Button btnIgraj = new Button("Igraj");

    private Kontekst kontekst = new Kontekst();

    public GlavniProzor() {
        setPadding(new Insets(50));
        // gornji deo
        HBox hBoxGornji = new HBox(30);
        lvPitanja.setPrefWidth(700);
        hBoxGornji.getChildren().addAll(lvPitanja, tvPokusaji);



        // donji deo
        HBox hBoxDonji = new HBox(30);
        hBoxDonji.setAlignment(Pos.CENTER);
        hBoxDonji.getChildren().addAll(cbPredmeti, btnUcitajZaPredmet, btnDodajPitanje, btnSnimi, btnIgraj);


        setMargin(hBoxDonji,new Insets(30,0,0,0));
        getChildren().addAll(hBoxGornji, hBoxDonji);
        initKolone();
        initPodaci();
        initAkcije();
    }

    private void initKolone(){
        TableColumn<Test,String> tcPredmet = new TableColumn<>("Predmet");
        TableColumn<Test, LocalDate> tcDatum = new TableColumn<>("Datum");
        TableColumn<Test, Integer> tcRezultat = new TableColumn<>("Skor");

        tcPredmet.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getPredmet()));
        tcDatum.setCellValueFactory(cell ->  new SimpleObjectProperty<LocalDate>(cell.getValue().getDatum()));
        tcRezultat.setCellValueFactory(cell -> new SimpleObjectProperty<Integer>(cell.getValue().getRezultat()));

        tvPokusaji.getColumns().addAll(tcPredmet,tcDatum,tcRezultat);
    }
    private void initPodaci(){
        // popuni listu podacima
        lvPitanja.getItems().clear();
        lvPitanja.getItems().addAll(kontekst.getPitanja());

        // popuni combobox
        cbPredmeti.getItems().clear();
        cbPredmeti.getItems().add("Svi predmeti");
        for(String s : kontekst.getPredmeti()){
            cbPredmeti.getItems().add(s);
        }
        cbPredmeti.setValue("Svi predmeti");

        tvPokusaji.getItems().clear();
        tvPokusaji.getItems().addAll(kontekst.getTestovi());
    }

    private void initAkcije(){
        btnUcitajZaPredmet.setOnMouseClicked(event -> {
            String predmet = cbPredmeti.getSelectionModel().getSelectedItem();
            if(predmet == null){
                return;
            }
            lvPitanja.getItems().clear();
            lvPitanja.getItems().addAll(kontekst.filtrirajPitanjaPoPredmetu(predmet));
        });

        btnDodajPitanje.setOnMouseClicked(event -> {
            DodajPitanjeProzor dp = new DodajPitanjeProzor(kontekst);
            dp.showAndWait();
            // refreshujemo prozor
            initPodaci();
        });

        btnSnimi.setOnMouseClicked(event -> {
            try(FileWriter fw = new FileWriter("pitanja.txt")){
                for(Pitanje p : kontekst.getPitanja()){
                    fw.write(p.getPitanje() + ";" + p.getOdgovor() + ";" + p.getPredmet() + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        });

        btnIgraj.setOnMouseClicked(event -> {
            String predmet = cbPredmeti.getSelectionModel().getSelectedItem();
            if(predmet == null){
                return;
            }

            List<Pitanje> pitanjaZaKviz = kontekst.filtrirajPitanjaPoPredmetu(predmet);
            IgrajProzor ip = new IgrajProzor(kontekst,pitanjaZaKviz,predmet);
            ip.showAndWait();
            initPodaci();
        });
    }

}
