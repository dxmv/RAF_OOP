package app.views;

import app.controllers.Kontekst;
import app.model.Pitanje;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DodajPitanjeProzor extends Stage {
    private Kontekst kontekst;
    private TextField tfPitanje = new TextField();
    private TextField tfOdgovor = new TextField();
    private TextField tfPredmet = new TextField();

    private Button btnOdustani = new Button("Odustani");
    private Button btnDodaj = new Button("Dodaj");

    public DodajPitanjeProzor(Kontekst k){
        kontekst = k;
        this.setTitle("Dodaj pitanje");
        this.setAlwaysOnTop(true);

        // kreiranje scene
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.add(new Label("Pitanje"),1,0);
        gridPane.add(tfPitanje,2,0);
        gridPane.add(new Label("Odgovor"),1,1);
        gridPane.add(tfOdgovor,2,1);
        gridPane.add(new Label("Predmet"),1,2);
        gridPane.add(tfPredmet,2,2);

        gridPane.add(btnOdustani,0,3);
        gridPane.add(btnDodaj,3,3);
        Scene scene = new Scene(gridPane);

        btnOdustani.setOnMouseClicked(event -> {
            this.close();
        });

        btnDodaj.setOnMouseClicked(event -> {
            String pitanje = tfPitanje.getText();
            String odgovor = tfOdgovor.getText();
            String predmet = tfPredmet.getText();
            if(!pitanje.isEmpty() && !odgovor.isEmpty() && !predmet.isEmpty()){
                kontekst.getPitanja().add(new Pitanje(pitanje,odgovor,predmet));
                // dodaj predmet ako se ne nalazi u listi predmeta
                if(!kontekst.getPredmeti().contains(predmet)){
                    kontekst.getPredmeti().add(predmet);
                }
                this.close();
            }
        });

        this.setScene(scene);
    }
}
