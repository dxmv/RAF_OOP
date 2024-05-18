package app.views;

import app.controllers.Kontekst;
import app.model.StatistikaTermin;
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
        TableColumn<StatistikaTermin, String> tcGrupa = new TableColumn<>("Grupa");
        TableColumn<StatistikaTermin, String> tcDan = new TableColumn<>("Dan");
        TableColumn<StatistikaTermin, Integer> tcBrojCasova = new TableColumn<>("Broj casova");

        tcGrupa.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getGrupa()));
        tcDan.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getDan()));
        tcBrojCasova.setCellValueFactory(cell -> new SimpleObjectProperty<Integer>(cell.getValue().getBrojCasova()));

        tableView.getColumns().addAll(tcGrupa,tcDan,tcBrojCasova);

        // popunjavanje tabele
        tableView.getItems().addAll(kontekst.getStatistika());
    }

    private void initEvents(){
        btnPovratak.setOnMouseClicked(event -> {
            this.close();
        });
    }
}

