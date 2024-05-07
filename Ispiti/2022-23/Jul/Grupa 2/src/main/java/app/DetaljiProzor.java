package app;

import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DetaljiProzor extends VBox {
    // lables
    private Label lblAnaliza = new Label("Analiza trzista");
    private Label lblZavrseno = new Label("Zavrseno");
    private Label lblUcinak = new Label("Ucinak na zadatku");

    private TableView<OsobaBrojSati> tvUcinak = new TableView<>();
    private Button btnPovratak = new Button("Povratak");

    private Akcija akcija;
    // za zatvarnaje prozora
    private Stage s;
    public DetaljiProzor(Akcija a, Stage s){
        this.s=s;
        this.akcija = a;
        setAlignment(Pos.CENTER);
        setSpacing(10);
        setPadding(new Insets(10));


        getChildren().addAll(lblAnaliza,lblZavrseno,lblUcinak,tvUcinak,btnPovratak);
        initKolone();
        initPodaci();
        initEvents();
    }

    private void initKolone(){
        TableColumn<OsobaBrojSati, String> tcOsoba = new TableColumn<>("Osoba");
        TableColumn<OsobaBrojSati,String> tcSati = new TableColumn<>("Broj sati");
        tcOsoba.setCellValueFactory(new PropertyValueFactory<>("ime"));
        tcSati.setCellValueFactory(new PropertyValueFactory<>("brojSati"));

        tvUcinak.getColumns().addAll(tcOsoba,tcSati);
    }

    private void initPodaci(){
        lblUcinak.setText("Zavrseno " + akcija.getUradjeno() + "% za " + akcija.getBrojSati() + " sati rada.");
        tvUcinak.getItems().addAll(pretvoriAkcijuUOsobe(akcija));
    }

    private List<OsobaBrojSati> pretvoriAkcijuUOsobe(Akcija akcija) {
        List<OsobaBrojSati> rez = new ArrayList<>();
        for(Osoba o:akcija.getOsobe()){
            rez.add(new OsobaBrojSati(o.getImePrezime(),akcija.getBrojSati()/akcija.getOsobe().size()));
        }
        return rez;
    }

    private void initEvents(){
        btnPovratak.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                s.close();
            }
        });
    }

}

