package app.views;

import app.controllers.Kontekst;
import app.model.StatistikaTermin;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DetaljiProzor extends Stage {
    private TableView<StatistikaTermin> tableView = new TableView<>();
    private Button btnPovratak = new Button("Povratak");
    private Kontekst kontekst;

    public DetaljiProzor(Kontekst kontekst){
        this.kontekst = kontekst;

        VBox vBoxPrikaz = new VBox(10);
        vBoxPrikaz.setAlignment(Pos.CENTER);
        vBoxPrikaz.setPadding(new Insets(10));
        vBoxPrikaz.getChildren().addAll(tableView,btnPovratak);
        setAlwaysOnTop(true);
        setScene(new Scene(vBoxPrikaz));

        initPodaci();
        initEvents();
    }

    private void initPodaci(){
        // dodavanje kolona
        TableColumn<StatistikaTermin, String> tcUcionica = new TableColumn<>("Ucionica");
        TableColumn<StatistikaTermin, String> tcDan = new TableColumn<>("Dan");
        TableColumn<StatistikaTermin, Integer> tcBrojCasova = new TableColumn<>("Broj casova");

        tcUcionica.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getUcionica()));
        tcDan.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getDanNedelje()));
        tcBrojCasova.setCellValueFactory(cell -> new SimpleObjectProperty<Integer>(cell.getValue().getBrojCasova()));

        tableView.getColumns().addAll(tcUcionica,tcDan,tcBrojCasova);
        // popunjavanje tabele
        tableView.getItems().addAll(kontekst.statistikaTermina());
    }

    private void initEvents(){
        btnPovratak.setOnMouseClicked(event -> {
            this.close();
        });
    }
}

