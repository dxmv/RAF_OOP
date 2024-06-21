package app.views;

import app.controllers.Kontekst;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

// kreiramo glavni prozor aplikacije
public class GlavniProzor extends GridPane {

    private TextField tfBrTermina = new TextField("3");
    private TextField tfBrStudenata = new TextField("15");
    private CheckBox cbSamoRacunari = new CheckBox("samo ucionice sa racunarima");
    private Button btnPokreni = new Button("Pokreni");
    private Label lblPoruka = new Label("Poruka");
    // podaci
    Kontekst k = new Kontekst();

    public GlavniProzor(){
        setPadding(new Insets(10));
        setAlignment(Pos.CENTER);
        setVgap(10);
        setHgap(10);

        // sp1 za prvi label
        StackPane sp1 = new StackPane(new Label("Unesite potrebne podatke za raspodelu"));
        sp1.setAlignment(Pos.CENTER);

        // sp2 za check box
        StackPane sp2 = new StackPane(cbSamoRacunari);
        sp2.setAlignment(Pos.CENTER);

        // sp3 za dugme
        StackPane sp3 = new StackPane(btnPokreni);
        sp3.setAlignment(Pos.CENTER);

        // sp4 za label
        StackPane sp4 = new StackPane(lblPoruka);
        sp4.setAlignment(Pos.CENTER);

        add(sp1,0,0,4,1);
        addRow(1,new Label("Broj termina"),tfBrTermina);
        addRow(2,new Label("Broj studenta u ucionici"),tfBrStudenata);
        add(sp2,0,3,4,1);
        add(sp3,0,4,4,1);
        add(sp4,0,5,4,1);

        initDogadjaji();
    }

    private void initDogadjaji(){
        btnPokreni.setOnMouseClicked(event -> {
            try{
                int brTermina = Integer.parseInt(tfBrTermina.getText());
                int brStudenta = Integer.parseInt(tfBrStudenata.getText());

                // gledamo samo ucionice sa racunarima ako treba
                if(cbSamoRacunari.isSelected()){
                    k.filterUcionicaSaRacunarima();
                }

                // NIGDE NISU NAPISALI STA ZNACI DA LI SE STUDENTI MOGU RASPODELITI PO TERMINIMA I UCIONICAMA
                // da li je moguca raspodela studenta po terminima
                if(brTermina < 0){
                    lblPoruka.setText("Nije moguca raspodela");
                    return;
                }

                // da li je moguca raspodela studenta po ucionicama
                if(brStudenta < 0){
                    lblPoruka.setText("Nije moguca raspodela po ucionicama");
                    return;
                }

                k.setBrojTermina(brTermina);
                k.setBrojStudentaPoUcionici(brStudenta);
                k.terminiUcionice(); // kreiramo termine

                DetaljiProzor detaljiProzor = new DetaljiProzor(k);
                detaljiProzor.showAndWait();
            }
            catch(NumberFormatException e){
                lblPoruka.setText("Los format broja");
            }
        });

    }
}
