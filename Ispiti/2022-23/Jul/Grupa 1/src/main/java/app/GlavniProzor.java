package app;


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

import java.time.LocalDate;
import java.util.regex.Pattern;


// kreiramo glavni prozor aplikacije
public class GlavniProzor extends VBox {
    // gornja polovina
    private Label lblRent = new Label("RENT-A-CAR");
    private Label lblTipovi = new Label("Tipovi");
    private Label lblOdaberitePeriod = new Label("Odaberite period rentiranja");
    private Label lblCrta = new Label("-");

    private ListView<Tipovi> lvTipovi = new ListView<>();
    private Button btnPretrazi = new Button("Pretrazi");

    private TextField tfPocetakPerioda = new TextField("2024-05-06");
    private TextField tfKrajPerioda = new TextField("2024-05-09");

    // donja polovina
    private Label lblDostupno = new Label("Dostupno za odabrani period:");
    private TableView<Vozilo> tvDostupno = new TableView<>();
    private Label lblCenaZaAutoPeriod = new Label("Cena za odabrani auto i period: $0.0");
    private Button btnRezervisi = new Button("Rezervisi");
    private Button btnSveRezervacije = new Button("Sve rezervacije");
    private Kontekst k;

    public GlavniProzor(){
        k = new Kontekst();

        setPadding(new Insets(10));
        setSpacing(10);
        setAlignment(Pos.TOP_CENTER);

        HBox hBoxGornjaPolovina = new HBox(20);

        // levi deo
        VBox vBoxGornja = new VBox(10);
        vBoxGornja.getChildren().addAll(lblTipovi,lvTipovi);

        // desni deo
        GridPane gridPaneGornja = new GridPane();
        gridPaneGornja.setHgap(5);
        gridPaneGornja.setVgap(10);
        gridPaneGornja.setAlignment(Pos.CENTER);
        gridPaneGornja.add(lblOdaberitePeriod,0,0,4,1);
        gridPaneGornja.addRow(1,tfPocetakPerioda,lblCrta,tfKrajPerioda,btnPretrazi);


        hBoxGornjaPolovina.getChildren().addAll(vBoxGornja,gridPaneGornja);
        // dodavanje elemenata
        btnSveRezervacije.setAlignment(Pos.BASELINE_RIGHT);

        getChildren().addAll(lblRent,hBoxGornjaPolovina,lblDostupno,tvDostupno,lblCenaZaAutoPeriod,btnRezervisi,btnSveRezervacije);

        initKolone();
        initPodaci();
        initEvents();
    }

    // popunjavanje elemenata podacima
    private void initPodaci(){
        lvTipovi.getItems().addAll(k.getTipovi());
    }

    // kolone za tableview
    private void initKolone(){
        TableColumn<Vozilo,String> tcMarka = new TableColumn<>("Marka");
        TableColumn<Vozilo,String> tcModel = new TableColumn<>("Model");
        TableColumn<Vozilo,Double> tcCena = new TableColumn<>("Cena po danu");

        tcMarka.setCellValueFactory(new PropertyValueFactory<Vozilo,String>("marka"));
        tcModel.setCellValueFactory(new PropertyValueFactory<Vozilo,String>("model"));
        tcCena.setCellValueFactory(new PropertyValueFactory<Vozilo,Double>("cenaPoDanu"));

        tvDostupno.getColumns().addAll(tcMarka,tcModel,tcCena);
    }

    private void initEvents(){
        btnPretrazi.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(!dobarFormatDatuma()){
                    return;
                }

                Tipovi tip = lvTipovi.getSelectionModel().getSelectedItem();
                // gledamo da li je nesto izabrano u tabeli tipovi
                if(tip == null){
                    return;
                }
                // prikazujemo sva vozila za izabrani tip
                tvDostupno.getItems().clear();
                tvDostupno.getItems().addAll(tip.getVozila());
            }
        });

        tvDostupno.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                // Samo 1 element izabran
                if(event.getClickCount() == 1 && dobarFormatDatuma()){
                    Vozilo v = tvDostupno.getSelectionModel().getSelectedItem();
                    // gledamo da li je izabrano vozilo
                    if(v==null){
                        return;
                    }
                    double cena = v.cenaZaPeriod(vratiDatumIzTf(tfPocetakPerioda),vratiDatumIzTf(tfKrajPerioda));
                    lblCenaZaAutoPeriod.setText("Cena za odabrani auto i period: $%.2f".formatted(cena));
                }
            }
        });

        // rezervisanje vozila
        btnRezervisi.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Vozilo v = tvDostupno.getSelectionModel().getSelectedItem();
                // gledamo da li je neki red selektovan
                if(v == null){
                    return;
                }
                LocalDate pocetakDatum = vratiDatumIzTf(tfPocetakPerioda);
                LocalDate krajDatum = vratiDatumIzTf(tfKrajPerioda);
                Rezervacija r = new Rezervacija(v,
                        v.cenaZaPeriod(pocetakDatum,krajDatum),
                        pocetakDatum,
                        krajDatum);
                // dodajemo u listu rezervacija
                k.dodajRezervaciju(r);
            }
        });

        // otvaramo novi prozor
        btnSveRezervacije.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Stage s2 = new Stage();
                s2.setTitle("Rezervacije");
                s2.setWidth(800);
                s2.setWidth(800);
                s2.setScene(new Scene(new RezervacijeProzor(k)));
                s2.show();
            }
        });
    }

    private boolean dobarFormatDatuma(){
        String pocetakPerioda = tfPocetakPerioda.getText();
        String krajPerioda = tfKrajPerioda.getText();
        String regex = "\\d{4}-\\d{2}-\\d{2}";
        // gledamo da li se stringovi poklapaju sa regexom
        return Pattern.matches(regex,pocetakPerioda) && Pattern.matches(regex,krajPerioda);
    }

    public static LocalDate vratiDatumIzTf(TextField tf){
        String[] delovi = tf.getText().split("-");
        return LocalDate.of(Integer.parseInt(delovi[0]),Integer.parseInt(delovi[1]),Integer.parseInt(delovi[2]));
    }
}
