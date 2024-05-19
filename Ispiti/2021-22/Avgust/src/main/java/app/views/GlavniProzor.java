package app.views;


import app.controllers.Kontekst;
import app.model.Paket;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


// kreiramo glavni prozor aplikacije
public class GlavniProzor extends BorderPane {
    // gornji deo
    private TextField tfPretraga = new TextField("l");
    private CheckBox chbNepoznatoOd = new CheckBox("Nepoznato OD");
    private CheckBox chbNepoznatoZa = new CheckBox("Nepoznato ZA");
    private Button btnFiltriraj = new Button("Filtriraj");

    // levo
    private ComboBox<String> cbGradovi = new ComboBox<>();
    private Button btnDodeli = new Button("Dodeli grad");
    private Button btnIspis = new Button("Ispis");


    private TableView<Paket> tvPaketi = new TableView<>();
    private Label lblPoruka = new Label("");
    private Kontekst kontekst;

    public GlavniProzor(){
        kontekst = new Kontekst();
        setPadding(new Insets(10));

        // filter
        HBox hBoxFilter = new HBox(10);
        hBoxFilter.setAlignment(Pos.CENTER);
        hBoxFilter.getChildren().addAll(new Label("Pretraga"),tfPretraga,chbNepoznatoOd,chbNepoznatoZa,btnFiltriraj);

        // leva strana
        VBox vBoxLevo = new VBox(10);
        vBoxLevo.setAlignment(Pos.CENTER);
        vBoxLevo.setPadding(new Insets(10));
        vBoxLevo.getChildren().addAll(cbGradovi,btnDodeli,btnIspis);

        // centralni deo
        VBox vBoxCentar = new VBox(10);
        vBoxCentar.getChildren().addAll(hBoxFilter,tvPaketi);
        setCenter(vBoxCentar);
        setLeft(vBoxLevo);
        setBottom(lblPoruka);

        initPodaci();
        initEvents();
    }

    // inicijalizacija kolona u tableview, dodavanje u combo box, dodavanje u table view
    private void initPodaci(){
        TableColumn<Paket,String> tcId = new TableColumn<>("Id");
        TableColumn<Paket,String> tcOd = new TableColumn<>("Od");
        TableColumn<Paket,String> tcZa = new TableColumn<>("Za");

        tcId.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getId()));
        tcOd.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getGradOd()));
        tcZa.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getGradZa()));

        tvPaketi.getColumns().addAll(tcId,tcOd,tcZa);
        // dodavanje podataka
        tvPaketi.getItems().addAll(kontekst.getPaketi());
        // dodavanje podataka combobox
        cbGradovi.getItems().addAll(kontekst.getGradovi());
    }

    private void initEvents(){
        btnFiltriraj.setOnMouseClicked(event -> {
            String pretraga = tfPretraga.getText();
            tvPaketi.getItems().clear();
            tvPaketi.getItems().addAll(kontekst.filter(pretraga.toLowerCase(),chbNepoznatoOd.isSelected(),chbNepoznatoZa.isSelected()));
        });

        btnDodeli.setOnMouseClicked(event -> {
            String grad = cbGradovi.getSelectionModel().getSelectedItem();
            // Ako nije odabran grad, prikazati poruku: "Morate izabrati grad za dodelu."
            if(grad == null){
                lblPoruka.setText("Morate izabrati grad za dodelu.");
                return;
            }
            Paket p = tvPaketi.getSelectionModel().getSelectedItem();
            if(p == null){
                lblPoruka.setText("Morate izabrati paket za dodelu.");
                return;
            }
            try{
                p.promeniGrad(grad);
                refresh();
            }
            catch (RuntimeException e){
                lblPoruka.setText(e.getMessage());
            }
        });

        // U tekstualni fajl izlaz.txt treba
        // ispisati sve pakete iz tabele (sa ažuriranim gradovima) i to grupisano po statusima
        btnIspis.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                // Paketi sa nepoznatim polazistem (broj paketa gde je OD=X):
                // lista paketa gde je OD=X (jedan ispod drugog, ispisuju se OD-ZA,ID)
                // Paketi sa nepoznatim odredistem (broj paketa gde je ZA=X):
                // lista paketa gde je ZA=X
                // Sredjeni paketi (broj paketa sa oba poznata grada):
                // lista paketa sa oba poznata grada.

                List<Paket> nepoznatoPolaziste = kontekst.nepoznatiPaketi(tvPaketi.getItems(),true,false);
                List<Paket> nepoznatoOdrediste = kontekst.nepoznatiPaketi(tvPaketi.getItems(),false,true);
                List<Paket> poznato = tvPaketi.getItems().stream().filter(el->!el.getGradOd().equals("X") && !el.getGradZa().equals("X")).toList();

                try(FileWriter fw = new FileWriter("izlaz.txt")){
                    // prvo pisemo pakete sa nepoznatim polazistem
                    ispisiKategoriju(fw,nepoznatoPolaziste,"Paketi sa nepoznatim polazistem (%d):".formatted(nepoznatoPolaziste.size()));
                    ispisiKategoriju(fw,nepoznatoOdrediste,"Paketi sa nepoznatim odredistem  (%d):".formatted(nepoznatoOdrediste.size()));
                    ispisiKategoriju(fw,poznato,"Sredjeni paketi (%d):".formatted(poznato.size()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            public void ispisiKategoriju(FileWriter fw,List<Paket> paketi,String header) throws IOException {
                fw.write(header + "\n");
                for(Paket p:paketi){
                    fw.write(p.toString() + "\n");
                }
            }
        });
    }

    private void refresh(){
        tvPaketi.getItems().clear();
        tvPaketi.getItems().addAll(kontekst.getPaketi());
    }
}
