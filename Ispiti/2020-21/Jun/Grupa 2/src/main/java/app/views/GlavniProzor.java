package app.views;


import app.controllers.Kontekst;
import app.model.Student;
import app.model.TerminPolaganja;
import app.model.Ucionica;
import javafx.beans.Observable;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;


// kreiramo glavni prozor aplikacije
public class GlavniProzor extends VBox{

    private Button btnPrebaci = new Button("Prebaci");
    private Button btnSacuvaj = new Button("Sacuvaj");

    private TextField tfNoviTermin = new TextField("15");

    private ListView<Ucionica> lvUcionice = new ListView<>();
    private Button btnDodaj = new Button("Dodaj");

    private TableView<TerminPolaganja> tvPodaci = new TableView<>();

    private Kontekst k = new Kontekst();

    private ObservableList<TerminPolaganja> terminPolaganja;

    public GlavniProzor(){
        setPadding(new Insets(10));
        terminPolaganja = FXCollections.observableList(k.getTermini());
        // gornji deo
        HBox hBoxDugmici = new HBox(10);
        hBoxDugmici.setAlignment(Pos.TOP_RIGHT);
        hBoxDugmici.getChildren().addAll(btnPrebaci,btnSacuvaj);

        VBox.setMargin(tvPodaci,new Insets(10,0,10,0));

        // za dodavanje
        GridPane gp = new GridPane();
        gp.setHgap(10);
        gp.setVgap(10);
        gp.setAlignment(Pos.CENTER);
        gp.addRow(0,new Label("Novi Termin"),tfNoviTermin);
        gp.addRow(1,new Label("Ucionice"),lvUcionice);
        gp.add(btnDodaj,1,2,1,1);


        getChildren().addAll(hBoxDugmici,tvPodaci,gp);
        initPodaci();
        initAkcije();
    }

    private void initPodaci(){
        // upisivanje u ListView ucionica
        lvUcionice.getItems().addAll(k.getUcionice());
        lvUcionice.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        // custom ispis ucionice za list view
        lvUcionice.setCellFactory(lv -> new ListCell<Ucionica>() {
            @Override
            protected void updateItem(Ucionica item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getIme() + " broj mesta: " + item.getDozvoljenBroj());
            }
        });

        // postavljanje kolona za table view
        TableColumn<TerminPolaganja,String> tcUcionica = new TableColumn<>("Ucionica");
        TableColumn<TerminPolaganja,Integer> tcTermin = new TableColumn<>("Termin");
        TableColumn<TerminPolaganja,Integer> tcBrojStudenata = new TableColumn<>("Broj studenata");
        TableColumn<TerminPolaganja,Integer> tcPrekoracenje = new TableColumn<>("Prekoracenje");
        TableColumn<TerminPolaganja,Integer> tcSlobodno = new TableColumn<>("Slobodno");

        tcUcionica.setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().getUcionica().getIme()));
        tcTermin.setCellValueFactory(cell->new SimpleObjectProperty<>(cell.getValue().getTermin()));
        tcBrojStudenata.setCellValueFactory(cell->new SimpleObjectProperty<>(cell.getValue().getStudenti().size()));
        tcPrekoracenje.setCellValueFactory(cell->new SimpleObjectProperty<>(cell.getValue().getPrekoracenje()));
        tcSlobodno.setCellValueFactory(cell->new SimpleObjectProperty<>(cell.getValue().getBrojSlobodnih()));

        tvPodaci.getColumns().addAll(tcUcionica,tcTermin,tcBrojStudenata,tcPrekoracenje,tcSlobodno);
        tvPodaci.setItems(terminPolaganja);
    }

    private void initAkcije(){
        // dodaje termin
        btnDodaj.setOnMouseClicked(event -> {
            try{
                List<Ucionica> ucionice = lvUcionice.getSelectionModel().getSelectedItems();
                int termin = Integer.parseInt(tfNoviTermin.getText());
                if(ucionice != null){
                    for(Ucionica u: ucionice){
                        TerminPolaganja tp = new TerminPolaganja(u,termin);
                        // gledamo da li se termin vec nalazi u listi
                        if(!k.getTermini().contains(tp)){
                            terminPolaganja.add(tp);
                        }
                    }
                }
            }
            catch (NumberFormatException e){
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText("Pogresan format broja");
                a.showAndWait();
            }

        });

        btnPrebaci.setOnMouseClicked(event -> {
            TerminPolaganja tp = tvPodaci.getSelectionModel().getSelectedItem();
            if(tp != null){
                PrebaciProzor prebaciProzor = new PrebaciProzor(tp,k,terminPolaganja);

                Stage s = new Stage();
                s.setScene(new Scene(prebaciProzor));
                s.setWidth(500);
                s.setHeight(500);
                s.showAndWait();
                tvPodaci.refresh();
            }
        });

        btnSacuvaj.setOnMouseClicked(event -> {
            String imeFajla = "izlaz.txt";
            k.getTermini().sort(Comparator.comparing(TerminPolaganja::getTermin).thenComparing(a -> a.getUcionica().getIme()));
            try (FileWriter fw = new FileWriter(new File(imeFajla))){
                for(TerminPolaganja tp : k.getTermini()){
                    for(Student s : tp.getStudenti()){
                        fw.write(s.toString() + "," + tp.getTermin() + "," + tp.getUcionica() + "\n");
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
