package app;


import javafx.beans.property.SimpleStringProperty;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;


// kreiramo glavni prozor aplikacije
public class GlavniProzor extends VBox {
    // labele
    private Label lblPmAlat = new Label("RAF PM alat");
    private Label lblOsobe = new Label("Osobe:");
    private Label lblRadniZadaci = new Label("Radni Zadaci:");
    private Label lblBrojSati = new Label("Broj sati:");
    private Label lblUradjeno = new Label("Uradjeno:");
    private Label lblUkupanBrojSati = new Label("Ukupan broj sati provedenih na projektu: 0");

    // buttons
    private Button btnEvident = new Button("Evidentiraj");
    private Button btnDetalji = new Button("Detalajn prikaz radnog zadatka");
    private Button btnSnimi = new Button("Snimi stanje");

    // text fields
    private TextField tfBrojSati = new TextField("8");
    private TextField tfUradjeno = new TextField("20");

    // list view
    private ListView<Osoba> lvOsobe = new ListView<>();
    private ListView<RadniZadatak> lvZadaci = new ListView<>();

    // table view
    private TableView<Akcija> tvAkcije = new TableView<>();

    // podaci
    private Kontekst k;

    public GlavniProzor(){
        k = new Kontekst();
        lvOsobe.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        setPadding(new Insets(10));
        setSpacing(10);
        setAlignment(Pos.TOP_CENTER);

        HBox hBoxGornjaPolovina = new HBox(20);
        // levi deo
        VBox vBoxLevo = new VBox(10);
        vBoxLevo.getChildren().addAll(lblOsobe,lvOsobe);

        // srednji gornji deo
        VBox vBoxSrednji = new VBox(10);
        vBoxSrednji.getChildren().addAll(lblRadniZadaci,lvZadaci);

        // desni deo
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.addRow(0,lblBrojSati,tfBrojSati);
        gridPane.addRow(1,lblUradjeno,tfUradjeno);
        gridPane.addRow(2,btnEvident);

        hBoxGornjaPolovina.getChildren().addAll(vBoxLevo,vBoxSrednji,gridPane);

        getChildren().addAll(lblPmAlat,hBoxGornjaPolovina,tvAkcije,lblUkupanBrojSati,btnDetalji,btnSnimi);

        initKolone();
        initPodaci();
        initEvents();
    }

    // kolone za table view
    private void initKolone(){
        TableColumn<Akcija,String> tcZadatak = new TableColumn<>("Zadatak");
        TableColumn<Akcija,String> tcUradjeno = new TableColumn<>("Uradjeno");
        TableColumn<Akcija,Integer> tcSati = new TableColumn<>("Utroseno sati");

        tcZadatak.setCellValueFactory((c)->new SimpleStringProperty(c.getValue().getZadatak().getNaziv()));
        tcUradjeno.setCellValueFactory((c)->new SimpleStringProperty(c.getValue().getUradjeno() + "%"));
        tcSati.setCellValueFactory(new PropertyValueFactory<Akcija,Integer>("brojSati"));

        tvAkcije.getColumns().addAll(tcZadatak,tcUradjeno,tcSati);
    }

    // popunjavanje elemenata podacima
    private void initPodaci(){
        lvOsobe.getItems().addAll(k.getOsobe());
        lvZadaci.getItems().addAll(k.getZadaci());
    }

    private void initEvents(){
        btnEvident.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                // uzimamo sve osobe
                List<Osoba> osobe = lvOsobe.getSelectionModel().getSelectedItems();
                if(osobe.isEmpty()){
                    return;
                }
                // uzimamo radni zadatak
                RadniZadatak radniZadatak = lvZadaci.getSelectionModel().getSelectedItem();
                // uzimamo broj sati i uradjeno
                int brojSati = Integer.parseInt(tfBrojSati.getText()) * osobe.size(); //  broj sati unet u polju se odnosi na jednu osobu
                int uradjeno = Integer.parseInt(tfUradjeno.getText());
                if(uradjeno < 0 || uradjeno>100){
                    return;
                }
                k.dodajAkciju(radniZadatak,osobe,brojSati,uradjeno);

                // update tekst za sate
                lblUkupanBrojSati.setText("Ukupan broj sati provedenih na projektu: " + k.getAkcije().stream().mapToInt(Akcija::getBrojSati).sum());
                // update tabele
                tvAkcije.getItems().clear();
                tvAkcije.getItems().addAll(k.getAkcije());
            }
        });

        // otvaranje novog prozora
        btnDetalji.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Akcija a = tvAkcije.getSelectionModel().getSelectedItem();
                if(a == null){
                    return;
                }
                Stage stage2 = new Stage();
                stage2.setTitle("Detalji");
                stage2.setHeight(600);
                stage2.setWidth(500);
                stage2.setScene(new Scene(new DetaljiProzor(a,stage2)));
                stage2.show();
            }
        });

        btnSnimi.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String imeFajla = "izlaz.txt";
                try(FileWriter fw = new FileWriter(imeFajla)){
                    for(Akcija a : k.getAkcije()){
                        fw.write(a.getZadatak().getNaziv() + " (" + a.getUradjeno() + "% - " + a.getBrojSati() +"h):\n");
                        for(Osoba o :a.getOsobe()){
                            fw.write(o.getImePrezime()+"\n");
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
