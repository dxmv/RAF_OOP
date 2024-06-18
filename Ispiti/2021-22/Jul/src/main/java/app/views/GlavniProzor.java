package app.views;

import app.controllers.Kontekst;
import app.models.Paket;
import app.models.StatusPosiljke;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

// kreiramo glavni prozor aplikacije
public class GlavniProzor extends BorderPane {
    // gornji deo
    private TextField tfPretraga = new TextField();
    private ComboBox<String> cbStatus = new ComboBox<>();
    private Button btnFilter = new Button("Filtriraj");

    private TableView<Paket> tvPaketi = new TableView<>();

    // desni deo
    private Button btnPosalji = new Button("Posalji");
    private Button btnPotvrdiPrijem = new Button("Potvrdi prijem");
    private Button btnPotvrdiVracanje = new Button("Potvrdi vracanje");
    private ComboBox<String> cbGradovi = new ComboBox<>();
    private Button btnIspis = new Button("Ispis");

    // za ispisivanje gresaka
    private Label lblError = new Label("");

    // podaci
    Kontekst k = new Kontekst();

    public GlavniProzor(){
        setPadding(new Insets(10));

        // gornji deo forme
        HBox hBoxGornji = new HBox(10);
        hBoxGornji.getChildren().addAll(new Label("Pretraga: "),tfPretraga,cbStatus,btnFilter);
        setTop(hBoxGornji);

        setCenter(tvPaketi);
        BorderPane.setMargin(tvPaketi,new Insets(10,0,10,0));

        // desni deo
        VBox vBoxDesno = new VBox(10);
        vBoxDesno.setPadding(new Insets(10));
        vBoxDesno.setAlignment(Pos.CENTER);
        HBox hBox = new HBox(10);
        hBox.getChildren().addAll(cbGradovi,btnIspis);

        vBoxDesno.getChildren().addAll(btnPosalji,btnPotvrdiPrijem,btnPotvrdiVracanje,hBox);
        VBox.setMargin(hBox,new Insets(30,0,0,0));

        setRight(vBoxDesno);


        setBottom(lblError);
        intiPodaci();
        initDogadjaji();
    }

    private void intiPodaci(){
        // prvo dodavanje kolona u table view
        TableColumn<Paket,String> tcId = new TableColumn<>("Id");
        TableColumn<Paket,String> tcOd = new TableColumn<>("Od");
        TableColumn<Paket,String> tcZa = new TableColumn<>("Za");
        TableColumn<Paket,StatusPosiljke> tcStatus = new TableColumn<>("Status");

        tcId.setCellValueFactory((c)-> new SimpleStringProperty(c.getValue().getId()));
        tcOd.setCellValueFactory((c)-> new SimpleStringProperty(c.getValue().getGradOd()));
        tcZa.setCellValueFactory((c)-> new SimpleStringProperty(c.getValue().getGradZa()));
        tcStatus.setCellValueFactory((c)-> new SimpleObjectProperty<>(c.getValue().getStatus()));

        tvPaketi.getColumns().addAll(tcId,tcOd,tcZa,tcStatus);

        // onda dodajemo sve pakete u table view
        tvPaketi.getItems().addAll(k.getPaketi());

        // dodavanje elemenata u cbStatusi
        cbStatus.getItems().addAll(k.getStatusi());
        cbStatus.setValue(k.getStatusi().get(0));

        // dodavanje elemenata u cbGradovi
        cbGradovi.getItems().addAll(k.getGradovi());
        cbGradovi.setValue("SVI GRADOVI");
    }

    private void initDogadjaji(){
        btnFilter.setOnMouseClicked(event -> {
            String tekst = tfPretraga.getText();
            String status = cbStatus.getSelectionModel().getSelectedItem();

            // ako su izabrani svi statusi, to racunamo kao null, da se nebi primenilo filtriranje po statusu
            List<Paket> paketi = k.filter(status.equals("SVI STATUSI") ? null : StatusPosiljke.valueOf(status),tekst);
            tvPaketi.getItems().clear();
            tvPaketi.getItems().addAll(paketi);
        });

        // menja status posiljke na POSLAT
        btnPosalji.setOnMouseClicked(event -> {
            Paket paket = tvPaketi.getSelectionModel().getSelectedItem();
            // gledamo da li je izabran paket
            if(paket == null){
                return;
            }
            // samo paket koji je status spreman moze biti poslat
            if(paket.getStatus().equals(StatusPosiljke.SPREMAN)){
                paket.setStatus(StatusPosiljke.POSLAT);
                tvPaketi.refresh();
                lblError.setText("");
            }
            else{
                lblError.setText("Nije moguce poslati paket koji nije spreman");
            }
        });

        // menja status posiljke na VRACANJE
        btnPotvrdiVracanje.setOnMouseClicked(event -> {
            Paket paket = tvPaketi.getSelectionModel().getSelectedItem();
            // gledamo da li je izabran paket
            if(paket == null){
                return;
            }
            // samo paket koji je status poslat moze da bude vracen
            if(paket.getStatus().equals(StatusPosiljke.POSLAT)){
                paket.setStatus(StatusPosiljke.VRACEN);
                tvPaketi.refresh();
                lblError.setText("");
            }
            else{
                lblError.setText("Nije moguce vratiti paket koji nije poslat");
            }
        });

        // menja status posiljke na PRIJEM
        btnPotvrdiPrijem.setOnMouseClicked(event -> {
            Paket paket = tvPaketi.getSelectionModel().getSelectedItem();
            // gledamo da li je izabran paket
            if(paket == null){
                return;
            }
            // samo paket koji je status poslat moze da bude primljen
            if(paket.getStatus().equals(StatusPosiljke.POSLAT)){
                paket.setStatus(StatusPosiljke.PRIMLJEN);
                tvPaketi.refresh();
                lblError.setText("");
            }
            else{
                lblError.setText("Nije moguce primiti paket koji nije poslat");
            }
        });

        // ispis u fajl
        btnIspis.setOnMouseClicked(event -> {
                String izabranGrad = cbGradovi.getSelectionModel().getSelectedItem();
                // prvo gledamo da li je izabran odredjeni grad ili su izabrani svi
                String imeFajla = "izlaz.txt";
                List<Paket> paketiZaIspis = k.getPaketi();
                if(!izabranGrad.equals("SVI GRADOVI")){
                    // izabran je grad tako da filtriramo pakete po tom gradu
                    paketiZaIspis = paketiZaIspis.stream().filter(el->el.getGradZa().equals(izabranGrad) || el.getGradOd().equals(izabranGrad)).toList();
                    imeFajla = "izlaz_" + izabranGrad.toLowerCase() + ".txt";
                }
                // grupisemo po statusu
                try(FileWriter fw = new FileWriter(imeFajla)){
                    for(StatusPosiljke sp : StatusPosiljke.values()){
                        List<Paket> trenutnaLista = paketiZaIspis.stream().filter(el->el.getStatus().equals(sp)).toList();
                        // Status paketi (broj paketa u statusu)
                        fw.write(sp.toString().substring(0, 1).toUpperCase() + sp.toString().substring(1).toLowerCase() + "i paketi (" + trenutnaLista.size() + "):\n");
                        // lista paketa u statusu SPREMAN (jedan ispod drugog, ispisuju se samo ID,OD-ZA)
                        for(Paket p : trenutnaLista){
                            fw.write(p.getId() + ", " + p.getGradOd() + " - " + p.getGradZa() +" \n");
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        );


    }
}
