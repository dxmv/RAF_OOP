package app.views;

import app.controllers.Kontekst;
import app.model.Pitanje;
import app.model.Test;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.List;

public class IgrajProzor extends Stage {
    private Label lblPitanje = new Label("");
    private TextField tfOdgovor = new TextField();

    private Button btnDalje = new Button("Dalje");

    private int index = 0;

    private Kontekst kontekst;

    private int score = 0;
    public IgrajProzor(Kontekst k, List<Pitanje> pitanja,String predmet){
        kontekst = k;
        this.setTitle("Igraj");
        this.setAlwaysOnTop(true);


        VBox vBox = new VBox(10);
        vBox.setPadding(new Insets(20));
        // za centriranje dugmeta
        StackPane sp = new StackPane();
        sp.getChildren().addAll(btnDalje);
        sp.setAlignment(Pos.CENTER);
        // postavljanje prvog pitanja
        lblPitanje.setText(pitanja.get(index).getPitanje());
        vBox.getChildren().addAll(new Label("Pitanje"),lblPitanje,new Label("Odgovor"),tfOdgovor,sp);
        vBox.setMargin(tfOdgovor,new Insets(20,0,0,0));
        tfOdgovor.setPrefWidth(400);

        btnDalje.setOnMouseClicked(event -> {
            // provera da li je odgovor tacan
            if(pitanja.get(index).odgovorJeTacan(tfOdgovor.getText())){
                score ++;
            }
            // idemo na sledece pitanje
            index++;

            // gledamo da li je to kraj liste
            if(index >= pitanja.size()) {
                // ako jeste pravimo novi objekat test, dodajemo ga kontekstu i zatvaramo prozor
                Test t = new Test(predmet, LocalDate.now(),score);
                kontekst.getTestovi().add(t);
                this.close();
                return;
            }
            tfOdgovor.setText("");
            lblPitanje.setText(pitanja.get(index).getPitanje());
        });

        this.setScene(new Scene(vBox));
    }
}
