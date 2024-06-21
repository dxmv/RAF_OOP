package app.views;

import app.controllers.Kontekst;
import app.model.Student;
import app.model.TerminPolaganja;
import app.model.Ucionica;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.List;
import java.util.stream.Collectors;

public class PrebaciProzor extends VBox {
    private Label lblStanje = new Label("");
    private ListView<Student> lvStudenti = new ListView<>();

    private ComboBox<Integer> cbTermin = new ComboBox<>();
    private ComboBox<Ucionica> cbUcionica = new ComboBox<>();

    private Button btnPrebaci = new Button("Prebaci");

    private Label lblPoruka = new Label("Poruka");

    private Kontekst kontekst;
    private TerminPolaganja terminPolaganja;
    private ObservableList<Student> studenti;

    private ObservableList<TerminPolaganja> termini;
    public PrebaciProzor(TerminPolaganja tp, Kontekst k,ObservableList<TerminPolaganja> tps){
        this.kontekst = k;
        this.terminPolaganja = tp;
        this.termini = tps;
        studenti = FXCollections.observableList(tp.getStudenti());

        setPadding(new Insets(10));

        lblStanje.setText("Ucionica: " + tp.getUcionica().getIme() + ", termin: " + tp.getTermin() + ", prekoraceno: " + tp.getPrekoracenje());
        VBox.setMargin(lvStudenti,new Insets(10,0,10,0));


        GridPane gp = new GridPane();
        gp.setVgap(10);
        gp.setHgap(10);
        gp.setAlignment(Pos.CENTER);
        gp.addRow(0,new Label("Termin"),cbTermin);
        gp.addRow(1,new Label("Ucionica"),cbUcionica);
        gp.add(btnPrebaci,1,2,1,1);
        gp.add(lblPoruka,1,3,1,1);

        getChildren().addAll(lblStanje,lvStudenti,gp);
        initPodaci();
        initAkcije();
    }

    private void initPodaci(){
        lvStudenti.setItems(studenti);
        lvStudenti.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        // kako izgledaju elementi
        lvStudenti.setCellFactory(lv->new ListCell<Student>(){
            @Override
            protected void updateItem(Student item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? "" : item.getIme() + " " + item.getPrezime() + " " + item.getBrojIndexa() + "/" + item.getStudijskiProgram() + "-" + item.getGodinaUpisa());
            }
        });

        cbUcionica.getItems().addAll(kontekst.getUcionice());
        cbTermin.getItems().addAll(kontekst.getTermini().stream().map(TerminPolaganja::getTermin).collect(Collectors.toSet()));
    }

    private void initAkcije(){

        btnPrebaci.setOnMouseClicked(event -> {
            try{
                int termin = cbTermin.getSelectionModel().getSelectedItem();
                Ucionica u = cbUcionica.getValue();
                List<Student> studentList = lvStudenti.getSelectionModel().getSelectedItems();
                if(u != null && studentList != null){
                    TerminPolaganja t = new TerminPolaganja(u,termin);
                    int index = kontekst.getTermini().indexOf(t);
                    int trenutniIndex = kontekst.getTermini().indexOf(terminPolaganja);
                    // mozemo samo raditi sa terminom koji postoji
                    if(index != -1){
                        // gledamo da li ima mesta za studente
                        if(kontekst.getTermini().get(index).getBrojSlobodnih() >= studentList.size()){
                            // dodajemo studente u termin
                            for(Student s:studentList){
                                termini.get(index).dodajStudenta(s);
                                // izbacujemo iz ovog termina
                                termini.get(trenutniIndex).izbaciStudenta(s);
                                studenti.remove(s);
                            }
                            lblPoruka.setText("");
                        }
                        else{
                            lblPoruka.setText("Nema mesta za studente");
                        }
                    }
                    else{
                        lblPoruka.setText("Ovaj termin ne postoji");
                    }
                }
            }
            catch (NumberFormatException e){
                lblPoruka.setText(e.getMessage());
            }
            lvStudenti.refresh();
            lblStanje.setText("Ucionica: " + terminPolaganja.getUcionica().getIme() + ", termin: " + terminPolaganja.getTermin() + ", prekoraceno: " + terminPolaganja.getPrekoracenje());
        });
    }
}

