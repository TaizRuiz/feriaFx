/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.feria_poo;

import clases.Auspiciante;
import clases.Feria;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author Kevin
 */
public class AdministracionFeriaController implements Initializable {

    @FXML
    private ImageView btregresar;
    @FXML
    private Button btdetalles;
    @FXML
    private Button btnuevaferia;
    @FXML
    private Button bteditar;
    @FXML
    private Button btverempre;
    @FXML
    private Button btverauspi;
    @FXML
    private Button btverstans;
    private TableView<Feria> listaFeria;
    
    @FXML
    private BorderPane contenedorPrincipal;
    private Feria feria_seleccionada=null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       cargarFerias();
    }    

    @FXML
    private void regresar(MouseEvent event) {
        try {
            App.setRoot("primary");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void detalles(MouseEvent event) {
        if (this.feria_seleccionada!=null){
            System.out.println("Entro");
            mostrarVentanaDetalles();
        }else{
            Alert a=new Alert(Alert.AlertType.ERROR);
            a.setContentText("Selecciona una feria");
            a.showAndWait();
            event.consume();
        }
    }

    @FXML
    private void nuevaFeria(MouseEvent event) {
        if (this.feria_seleccionada!=null){
           
        }else{
            Alert a=new Alert(Alert.AlertType.ERROR);
            a.setContentText("Selecciona una feria");
            a.showAndWait();
            event.consume();
        }
    }

    @FXML
    private void editar(MouseEvent event) {
    }

    @FXML
    private void verEmprendedores(MouseEvent event) {
    }

    @FXML
    private void verAuspiciantes(MouseEvent event) {
    }

    @FXML
    private void verStans(MouseEvent event) {
    }

    
    public void cargarFerias(){
        listaFeria = new TableView();
        TableColumn<Feria,Integer> colCodigo = new TableColumn<>("Codigo");
        colCodigo.setMaxWidth(50);
        TableColumn<Feria, String> colNombre = new TableColumn<>("Nombre");
        TableColumn<Feria, LocalDate> colFechaInicio = new TableColumn<>("Fecha");
        TableColumn<Feria, String> colLugar = new TableColumn<>("Lugar");
        TableColumn<Feria, Integer> colCantAusp = new TableColumn<>("Num Auspiciantes");
        TableColumn<Feria, Integer> colCantEmpr = new TableColumn<>("Num Emprendedores");
        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colFechaInicio.setCellValueFactory(cellData -> cellData.getValue().getfIn());
        colLugar.setCellValueFactory(new PropertyValueFactory<>("lugar"));
        colCantAusp.setCellValueFactory(new PropertyValueFactory<>("cantAusp"));
        colCantEmpr.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        listaFeria.setItems(FXCollections.observableArrayList(App.ferias));
        listaFeria.getColumns().addAll(colCodigo,colNombre,colFechaInicio,colLugar,colCantAusp,colCantEmpr);
        listaFeria.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // Aquí puedes trabajar con el objeto seleccionado (newValue)
                this.feria_seleccionada=newValue;
                System.out.println(feria_seleccionada);
            }
        });
        contenedorPrincipal.setCenter(listaFeria);
        BorderPane.setMargin(listaFeria, new Insets(25));
    }
    /*metodo que muestra el pop-up donde se muestran los detalles de una feria seleccionada*/
    public void mostrarVentanaDetalles(){
        Feria feriaOg=(feria_seleccionada!=null)? buscarFeria():null;
        if (feriaOg!=null){
            Stage s=new Stage();
            BorderPane bp=new BorderPane();
            VBox contInf=new VBox();
            Label nombrePanel=new Label("Detalles de feria");
            contInf.getChildren().addAll(labelsInfoFeria(feriaOg));
            BorderPane.setAlignment(nombrePanel, Pos.CENTER);
            BorderPane.setAlignment(contInf, Pos.BASELINE_LEFT);
            BorderPane.setMargin(nombrePanel, new Insets(15));
            BorderPane.setMargin(contInf, new Insets(20));
            bp.setTop(nombrePanel);
            bp.setCenter(contInf);
            Scene new_scene=new Scene(bp,300,300);
            s.initModality(Modality.APPLICATION_MODAL);
            s.setScene(new_scene);
            s.showAndWait();
        }
    }
    /*metodo del boton mostrar detalles de feria*/
    public ArrayList<Label> labelsInfoFeria(Feria f){
        ArrayList<Label>labels=new ArrayList<>();
        Label nombre=new Label("Nombre de feria: "+f.getNombre());
        Label fechaInicio=new Label("Fecha de Inicio: "+f.getfInicio().toString());
        Label horario=new Label("Horario: "+f.getHorario());
        Label descripcion=new Label("Descripcion: "+f.getDescripcion());
        Label Lugar=new Label("Lugar: "+f.getLugar());
        Label fechaFin=new Label("Nombre de Fin: "+f.getfFin());
        Label auspiciantes=new Label((f.getlAuspiciantes().isEmpty())?"Auspiciantes: no hay auspiciantes":infoAuspiciantes(f));
 
        labels.add(nombre);
        labels.add(fechaInicio);
        labels.add(fechaFin);
        labels.add(auspiciantes);
        labels.add(horario);
        labels.add(descripcion);
        labels.add(Lugar);
       
        return labels;
    }
    /*retorna string con el nombre de los emprendedores registrados*/
    public String infoEmprendedores(Feria f){
        String s="";
        
        return s;
    }
    /*retorna un string formateado para los auspiciantes*/
    public String infoAuspiciantes(Feria f){
        String s="";
        int cont=1;
        if (f!=null){
            for (Auspiciante a: f.getlAuspiciantes()){
                String n=a.getNombre();
                String rep=a.getNombre_de_responsable();
                String sect=a.getSector_cubierto().toString();
                String info=String.valueOf(cont)+" ->"+"Empresa: "+n+"\n"+"Nombre: "+rep+"\n"+"Sector: "+sect;
                s+=info+"\n";
                
            }
        }
        return s;
    }
    /*Este metodo busca la feria seleccionada dentro del arrayList original de la clase App*/
    public Feria buscarFeria(){
        Feria f=null;
        if (this.feria_seleccionada!=null){
            for (Feria feria:App.ferias){
                f=(feria.equals(feria_seleccionada))?feria:f;
            }
        }
        System.out.println("feria retornada por buscar feria:"+f);
        return f;
    }
}
