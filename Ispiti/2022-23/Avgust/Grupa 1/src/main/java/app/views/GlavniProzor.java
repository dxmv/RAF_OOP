package app.views;


import app.controllers.Kontekst;
import app.model.Termin;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


// kreiramo glavni prozor aplikacije
public class GlavniProzor extends VBox {

    private Label lblBrojGrupa = new Label("Ukupan broj grupa: 0");
    // dugmad
    private Button btnPrikazi = new Button("Prikazi");
    private Button btnStatistika = new Button("Statistika");
    private Button btnStampa = new Button("Stampa");

    private ComboBox<String> cbGrupe = new ComboBox<>();
    private TableView<Termin> tvRaspored = new TableView<>();
    private Kontekst kontekst;

    public GlavniProzor(){
        kontekst = new Kontekst();
        setAlignment(Pos.CENTER);
        setSpacing(10);
        setPadding(new Insets(10));

        // gornji deo prozora
        HBox hBoxGornji = new HBox(10);
        hBoxGornji.setAlignment(Pos.CENTER);
        hBoxGornji.getChildren().addAll(cbGrupe,btnPrikazi,lblBrojGrupa,btnStatistika);

        getChildren().addAll(hBoxGornji,tvRaspored,btnStampa);

        initPodaci();
        initEvents();
        restartStranice();
    }

    // inicijalizacija kolona u tableview , dodavanje grupa u combobox i promena labela
    private void initPodaci(){
        // dodavanje kolona u table view
        TableColumn<Termin,String> tcPredmet = new TableColumn<>("Predmet");
        TableColumn<Termin,String> tcNastavnik = new TableColumn<>("Nastavnik");
        TableColumn<Termin,String> tcVrsta = new TableColumn<>("Vrsta");
        TableColumn<Termin,String> tcUcionica = new TableColumn<>("Ucionica");
        TableColumn<Termin,String> tcTermin = new TableColumn<>("Termin");
        TableColumn<Termin,String> tcDan = new TableColumn<>("Dan");

        tcPredmet.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getPredmet()));
        tcNastavnik.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getNastavnik()));
        tcVrsta.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getTip()));
        tcUcionica.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getUcionica()));
        tcTermin.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getPocetak() + "-" + cell.getValue().getKraj()));
        tcDan.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getDanUNedelji()));

        tvRaspored.getColumns().addAll(tcPredmet,tcNastavnik,tcVrsta,tcUcionica,tcTermin,tcDan);

        List<String> grupe = kontekst.grupeRasporeda();
        // dodavanja grupa u combobox
        cbGrupe.getItems().addAll(grupe);
        // ispisivanje grupa u labelu
        lblBrojGrupa.setText("Ukupan broj grupa: " + grupe.size());
    }

    private void restartStranice(){
        // dodavanje podatak u table view
        tvRaspored.getItems().addAll(kontekst.getTermini());
    }

    private void initEvents(){
        btnStatistika.setOnMouseClicked(e->{
            DetaljiProzor detaljiProzor = new DetaljiProzor(kontekst);
            detaljiProzor.show();
        });

        btnPrikazi.setOnMouseClicked(event -> {
            String grupa = cbGrupe.getSelectionModel().getSelectedItem();
            if(grupa == null){
                restartStranice();
                return;
            }

            tvRaspored.getItems().clear();
            for(Termin t:kontekst.getTermini()){
                if(t.getGrupe().contains(grupa)){
                    tvRaspored.getItems().add(t);
                }
            }
        });

        btnStampa.setOnMouseClicked(event -> {
            String grupa = cbGrupe.getSelectionModel().getSelectedItem();
            if(grupa == null){
                Alert a = new Alert(Alert.AlertType.ERROR,"Morate izabrati grupu");
                a.show();
                return;
            }

            String IME_FAJLA = "izlaz-" + grupa + ".txt";
            try(FileWriter fw = new FileWriter(IME_FAJLA)){
                List<Termin> termini = tvRaspored.getItems();
                for(Termin t : termini){
                    fw.write(t + "\n");
                }
            } catch (IOException e) {
                Alert a = new Alert(Alert.AlertType.ERROR,"Greska pri pisanju u fajl");
                a.show();
            }
        });
    }
}
