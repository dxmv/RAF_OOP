package app.views;


import app.Baza;
import app.models.Student;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
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
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;


// kreiramo glavni prozor aplikacije
public class GlavniProzor extends BorderPane {

    private ComboBox<String> cbTermini = new ComboBox<>(FXCollections.observableArrayList("Svi","termin1","termin2"));

    private Button btnPrikazi = new Button("Prikazi");
    private Button btnStatistika = new Button("Statistika");
    private Button btnSnimi = new Button("Snimi ukupno");
    private TextField tfPoeni = new TextField("3");
    private Button btnUnesi = new Button("Unesi");

    private Label lblPoruka = new Label("");

    private TableView<Student> tvRadovi = new TableView<>();
    private Baza baza;
    private ObservableList<Student> studenti; // lista za tableview

    public GlavniProzor(){
        baza = new Baza();
        studenti = FXCollections.observableList(baza.getStudenti());
        setPadding(new Insets(10));

        // gornji deo prozora
        HBox hBoxGornji = new HBox(10);
        hBoxGornji.setAlignment(Pos.CENTER);
        hBoxGornji.getChildren().addAll(new Label("Termin"),cbTermini,btnPrikazi,btnStatistika,btnSnimi);
        setTop(hBoxGornji);

        setCenter(tvRadovi);
        BorderPane.setMargin(tvRadovi, new Insets(10,0,10,0));

        // donji deo prozora
        HBox hBoxDonji = new HBox(10);
        hBoxDonji.setAlignment(Pos.CENTER);
        hBoxDonji.getChildren().addAll(new Label("UML poeni"),tfPoeni,new Label("(0-3)"),btnUnesi,lblPoruka);
        setBottom(hBoxDonji);

        initPodaci();
        initAkcije();
    }

    private void initPodaci(){
        TableColumn<Student,String> tcIme = new TableColumn<>("Ime");
        TableColumn<Student,String> tcPrezime = new TableColumn<>("Prezime");
        TableColumn<Student,Double> tcPoeni = new TableColumn<>("Poeni moodle");
        TableColumn<Student,Double> tcUml = new TableColumn<>("Poeni UML");

        tcIme.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getIme()));
        tcPrezime.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getPrezime()));
        tcPoeni.setCellValueFactory(cell -> new SimpleObjectProperty<>(cell.getValue().getUkupnoPoena()));
        tcUml.setCellValueFactory(cell -> new SimpleObjectProperty<>(cell.getValue().getUmlPoeni()));

        tvRadovi.getColumns().addAll(tcIme,tcPrezime,tcPoeni,tcUml);
        tvRadovi.setItems(studenti);
        cbTermini.setValue("Svi");
    }

    private void initAkcije(){
        // dodavanje uml poena
        btnUnesi.setOnMouseClicked(event -> {
            try{
                double uml = Double.parseDouble(tfPoeni.getText());
                Student s = tvRadovi.getSelectionModel().getSelectedItem();
                if(uml < 0 || uml > 3){
                    lblPoruka.setText("Vrednost mora biti izmedju 0 i 3");
                }
                else if(s != null){
                    s.setUmlPoeni(uml);
                    tvRadovi.refresh();
                    lblPoruka.setText("");
                }
            }
            catch (NumberFormatException e){
                lblPoruka.setText("Pogresan format broja");
            }
        });

        // snimanje u fajl
        btnSnimi.setOnMouseClicked(event -> {
            String imeFajla = "izlaz.txt";
            try(FileWriter fw = new FileWriter(imeFajla)){
                List<Student> res = new ArrayList<>();
                // pravimo novu kopiju liste studenti, gde sabiramo za ukupne poene sve
                for(Student s : baza.getStudenti()){
                    Student novi = new Student(s.getIme(),s.getPrezime(),s.getEmail(),s.getTermin());
                    novi.setUkupnoPoena(s.getUkupnoPoena() + s.getUmlPoeni());
                    res.add(novi);
                }
                res.sort(Comparator.comparing(Student::getUkupnoPoena).reversed());
                for(Student s:res){
                    fw.write(s.toString() + "\n");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        btnStatistika.setOnMouseClicked(event -> {
            String termin = cbTermini.getValue();
            if(termin == null || termin.isEmpty() || termin.equals("Svi")) {
                return;
            }
            StatistikaProzor sp = new StatistikaProzor(baza,cbTermini.getValue());
            sp.setWidth(500);
            sp.setHeight(500);
            sp.showAndWait();
        });

        btnPrikazi.setOnMouseClicked(event -> {
            String termin = cbTermini.getValue();
            if(termin == null || termin.isEmpty()) {
                return;
            }
            if(termin.equals("Svi")){
                studenti = FXCollections.observableList(baza.getStudenti());
            }
            else{
                studenti = FXCollections.observableList(baza.filtrirajTermin(termin));
            }
            tvRadovi.setItems(studenti);
        });
    }
}
