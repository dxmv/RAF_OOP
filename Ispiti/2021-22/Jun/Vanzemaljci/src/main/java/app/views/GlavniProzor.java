package app.views;

import app.models.Drzava;
import app.models.Kontekst;
import app.models.Statistika;
import app.models.Vanzemaljac;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GlavniProzor extends VBox {
    private ComboBox<String> cbKontinenti = new ComboBox<>();
    private CheckBox chPronadjen = new CheckBox("Pronadjen");
    private CheckBox chIzgubljen = new CheckBox("Izgubljen");
    private Button btnFilter = new Button("Filtriraj");

    private TableView<Vanzemaljac> tvVanzemaljci = new TableView<>();
    private ComboBox<Drzava> cbDrzave = new ComboBox<>();
    private Button btnUpisi = new Button("Upisi");
    private Label lblGreska = new Label("");
    private Button btnSacuvaj = new Button("Sacuvaj");


    public GlavniProzor(){
        setSpacing(10);
        setPadding(new Insets(10));
        setAlignment(Pos.CENTER);

        // gornji deo
        HBox hBoxGornji = new HBox(10);
        hBoxGornji.getChildren().addAll(new Label("Kontinent"),cbKontinenti,chPronadjen,chIzgubljen,btnFilter);
        hBoxGornji.setAlignment(Pos.CENTER);

        // srednji deo
        HBox hBoxSrednji = new HBox(10);
        VBox vBoxSrednji = new VBox(10);
        vBoxSrednji.getChildren().addAll(new Label("Pronadjen"),cbDrzave,btnUpisi,lblGreska);
        hBoxSrednji.getChildren().addAll(tvVanzemaljci,vBoxSrednji);
        vBoxSrednji.setAlignment(Pos.CENTER);


        getChildren().addAll(hBoxGornji,hBoxSrednji,btnSacuvaj);
        initPodaci();
        initAkcije();
    }

    private void initPodaci(){
        cbKontinenti.getItems().addAll(Kontekst.k.getKontinenti());
        cbDrzave.getItems().addAll(Kontekst.k.getDrzave());

        // kolone za tabelu
        TableColumn<Vanzemaljac,Integer> tcId = new TableColumn<>("Id");
        TableColumn<Vanzemaljac,String> tcKontinent = new TableColumn<>("Kontinent");
        TableColumn<Vanzemaljac,String> tcDrzava = new TableColumn<>("Drzava");

        tcId.setCellValueFactory(cell -> new SimpleObjectProperty<>(cell.getValue().getId()));
        tcKontinent.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getDrzava().getKontinent()));
        tcDrzava.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getDrzava().getIme()));

        tvVanzemaljci.getColumns().addAll(tcId,tcKontinent,tcDrzava);
        tvVanzemaljci.setPrefWidth(600);
        tvVanzemaljci.getItems().addAll(Kontekst.k.getVanzemaljaci());
    }

    private void initAkcije(){
        btnFilter.setOnMouseClicked(event -> {
            String kontinent = cbKontinenti.getSelectionModel().getSelectedItem();
            if(kontinent == null){
                return;
            }
            boolean pronadjen = chPronadjen.isSelected();
            boolean izgubljen = chIzgubljen.isSelected();
            List<Vanzemaljac> filtrirano = Kontekst.k.getVanzemaljaci().stream().filter(el->el.getDrzava().getKontinent().equals(kontinent)).toList();
            // ako su oba stiklirana ili ni jedan nije, onda nista ne radimo
            if(pronadjen ^ izgubljen){ // xor
                System.out.println("a");
                filtrirano = filtrirano.stream().filter(el->{
                    // nije pronadjen ako je drzava prazna
                   return pronadjen ? !el.getDrzava().getIme().isEmpty() : el.getDrzava().getIme().isEmpty();
                }).toList();
            }
            tvVanzemaljci.getItems().clear();
            tvVanzemaljci.getItems().addAll(filtrirano);
        });

        btnUpisi.setOnMouseClicked(event -> {
            Drzava drzava = cbDrzave.getSelectionModel().getSelectedItem();
            Vanzemaljac v = tvVanzemaljci.getSelectionModel().getSelectedItem();
            if(drzava == null || v == null){
                return;
            }
            // gledamo da li je vanzemaljac pronadjen, tacnije da li mu je drzava neprazna
            if(!v.getDrzava().getIme().isEmpty()){
                lblGreska.setText(String.format("Vanzemaljac sa ID-jem %d je vec pronadjen",v.getId()));
                return;
            }
            // gledamo da li se slazu kontinenti
            if(!v.getDrzava().getKontinent().equals(drzava.getKontinent())){
                lblGreska.setText(String.format("Država %s ne pripada kontinentu %s već kontinentu %s",drzava.getIme(),v.getDrzava().getKontinent(),drzava.getKontinent()));
                return;
            }
            lblGreska.setText("");
            v.getDrzava().setIme(drzava.getIme());
            tvVanzemaljci.refresh();
        });

        btnSacuvaj.setOnMouseClicked(event -> {
            // gledamo samo pronadjene vanzemaljce
            List<Vanzemaljac> vanzemaljaci = Kontekst.k.getVanzemaljaci().stream().filter(el->!el.getDrzava().getIme().isEmpty()).toList();
            // racunamo statistiku
            List<Statistika> stat = new ArrayList<>();
            for(Vanzemaljac v : vanzemaljaci){
                int index = stat.indexOf(new Statistika(v.getDrzava(),0));
                if(index!=-1){ // nalazi se u nizu
                    stat.get(index).dodaj();
                }
                else { // ne nalazi se u nizu
                    stat.add(new Statistika(v.getDrzava(),1));
                }
            }

            try(FileWriter fw = new FileWriter("regije.txt")){
                // prvo ispisemo sortirane kontinente
                for(String kontinent:Kontekst.k.getKontinenti().stream().sorted().toList()){
                    // racunamo koliko ih ima za kontient
                    int rez = 0;
                    for(Statistika st: stat){
                        if(st.getDrzava().getKontinent().equals(kontinent)){
                            rez += st.getBrojVanzemaljaca();
                        }
                    }
                    fw.write(kontinent + ":" + rez + "\n");
                }
                fw.write("--------" + "\n");
                // onda ispisujemo drzave sortirano po broju nadjenih
                for(Statistika s:stat.stream().sorted(Comparator.comparing(Statistika::getBrojVanzemaljaca).reversed()).toList()){
                    fw.write(s.getDrzava() + ":" + s.getBrojVanzemaljaca() + "\n");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
