package app.views;

import app.Baza;
import app.models.Statistika;
import app.models.Student;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class StatistikaProzor extends Stage {

    private Button btnPovratak = new Button("Povratak");
    private TableView<Statistika> tvStatistika = new TableView<>();
    private Baza baza;
    private String termin;
    public StatistikaProzor(Baza b, String termin){
        this.baza = b;
        this.termin = termin;


        VBox vBox = new VBox(10);
        vBox.getChildren().addAll(tvStatistika,btnPovratak);
        vBox.setAlignment(Pos.CENTER);

        setScene(new Scene(vBox));
        initAkicje();
        initPodaci();
    }

    private void initPodaci(){
        TableColumn<Statistika,String> tcPitanje = new TableColumn<>("Pitanje");
        TableColumn<Statistika,Double> tcMax = new TableColumn<>("Maks poena");
        TableColumn<Statistika,Double> tcProsek = new TableColumn<>("Prosek");
        TableColumn<Statistika,Double> tcProcenat = new TableColumn<>("Procenat");

        tcPitanje.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getPitanje()));
        tcMax.setCellValueFactory(cell->new SimpleObjectProperty<>(cell.getValue().getMax()));
        tcProsek.setCellValueFactory(cell->new SimpleObjectProperty<>(cell.getValue().prosek()));
        tcProcenat.setCellValueFactory(cell->new SimpleObjectProperty<>(cell.getValue().procenat()));

        tvStatistika.getColumns().addAll(tcPitanje,tcMax,tcProsek,tcProcenat);
        tvStatistika.getItems().addAll(baza.racunajStatistiku(termin));
    }

    private void initAkicje(){
        btnPovratak.setOnMouseClicked(event -> {
            this.close();
        });
    }
}
