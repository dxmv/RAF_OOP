package app.views;

import app.controllers.Kontekst;
import app.models.Student;
import app.models.TerminPolaganja;
import app.models.TerminUcionica;
import app.models.Ucionica;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.stream.Collectors;

public class DetaljiProzor extends Stage {

    private ListView<Student> lvStudent = new ListView<>();
    private ListView<TerminUcionica> lvUcionice = new ListView<>();

    private Button btnDodeli = new Button("Dodeli");
    private Label lblStanjeStudenta = new Label("Broj studenta");
    private ComboBox<String> cbTermini = new ComboBox<>();

    private TableView<TerminPolaganja> tvTermini = new TableView<>();
    private Kontekst kontekst;
    public DetaljiProzor(Kontekst k){
        this.kontekst = k;
        setTitle("Detalji");
        setWidth(1024);
        setHeight(728);
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));

        // za prikaz dve liste
        HBox hBox = new HBox(20);
        // vbox za studenta
        VBox vBoxStudent = new VBox(10);
        lvStudent.getItems().addAll(kontekst.getStudenti());
        lvStudent.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE); // da moze vise da se izabere
        lblStanjeStudenta.setText("Broj nerasporedjenih studenta: " + kontekst.getStudenti().size());
        vBoxStudent.getChildren().addAll(lblStanjeStudenta,lvStudent);

        // vbox za ucionice
        VBox vBoxUcionice = new VBox(10);
        lvUcionice.getItems().addAll(kontekst.getTerminUcionica());
        cbTermini.getItems().addAll(kontekst.getTerminUcionica().stream().map(TerminUcionica::getTermin).collect(Collectors.toSet()));
        vBoxUcionice.getChildren().addAll(cbTermini,lvUcionice);

        hBox.getChildren().addAll(vBoxStudent,btnDodeli,vBoxUcionice);
        hBox.setAlignment(Pos.CENTER);

        tvTermini.getItems().addAll(kontekst.getTerminPolaganja());
        vbox.getChildren().addAll(hBox);
        setScene(new Scene(vbox));
        initDogadjaji();
    }

    private void initDogadjaji(){
        cbTermini.valueProperty().addListener((observable, oldValue, newValue) -> {
            lvUcionice.getItems().clear();
            lvUcionice.getItems().addAll(kontekst.getTerminUcionica().stream().filter(el->el.getTermin().equals(cbTermini.getValue())).toList());
        });

        btnDodeli.setOnMouseClicked(event -> {

        });
    }
}
