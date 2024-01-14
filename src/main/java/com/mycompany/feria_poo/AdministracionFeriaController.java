/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.feria_poo;

import clases.Auspiciante;
import clases.Emprendedor;
import clases.Feria;
import clases.Persona;
import clases.Seccion;
import clases.Stand;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
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
        colCantEmpr.setCellValueFactory(new PropertyValueFactory<>("cantEmprendedores"));
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
            ScrollPane sp=new ScrollPane();
            ArrayList<Label> info=labelsInfoFeria(feriaOg);
            Label nombrePanel=new Label("Detalles de feria");
            /*nombre*/
            HBox row_nombre=new HBox();
            Label nom=new Label("Nombre:" );
            nom.setStyle("-fx-background-color: gray;");
            Label nom_content=info.get(0);
            HBox.setMargin(nom,new Insets(15));
            HBox.setMargin(nom_content,new Insets(15));
            row_nombre.getChildren().addAll(nom,nom_content);
            /*fecha inicio*/
            HBox row_fInicio=new HBox();
            Label fInicio=new Label("Fecha inicio:" );
            fInicio.setStyle("-fx-background-color: gray;");
            Label fInicio_content=info.get(1);
            HBox.setMargin(fInicio,new Insets(15));
            HBox.setMargin(fInicio_content,new Insets(15));
            row_fInicio.getChildren().addAll(fInicio,fInicio_content);
            /*fecha fin*/
            HBox row_fFin=new HBox();
            Label fFin=new Label("Fecha fin:" );
            fFin.setStyle("-fx-background-color: gray;");
            Label fFin_content=info.get(2);
           
            HBox.setMargin(fFin,new Insets(15));
            HBox.setMargin(fFin_content,new Insets(15));
            row_fFin.getChildren().addAll(fFin,fFin_content);
            /*auspiciantes*/
            HBox row_fAusp=new HBox();
            Label Ausp=new Label("Auspiciantes:" );
            Ausp.setStyle("-fx-background-color: gray;");
            Label Ausp_content=info.get(3);
            HBox.setMargin(Ausp,new Insets(15));
            HBox.setMargin(Ausp_content,new Insets(15));
            row_fAusp.getChildren().addAll(Ausp,Ausp_content);
            /*emprendedores*/
            HBox row_Empr=new HBox();
            Label Empr=new Label("Emprendedores:" );
            Empr.setStyle("-fx-background-color: gray;");
            Label Empr_content=info.get(4);
            HBox.setMargin(Empr,new Insets(15));
            HBox.setMargin(Empr_content,new Insets(15));
            row_Empr.getChildren().addAll(Empr,Empr_content);
            /*horario*/
            HBox row_Horario=new HBox();
            Label horario=new Label("Horario:" );
            horario.setStyle("-fx-background-color: gray;");
            Label horario_content=info.get(5);
            HBox.setMargin(horario,new Insets(15));
            HBox.setMargin(horario_content,new Insets(15));
            row_Horario.getChildren().addAll(horario,horario_content);
            /*descripcion*/
            HBox row_descripcion=new HBox();
            Label descripcion=new Label("Descripcion:" );
            descripcion.setStyle("-fx-background-color: gray;");
            Label descripcion_content=info.get(6);
            HBox.setMargin(descripcion,new Insets(15));
            HBox.setMargin(descripcion_content,new Insets(15));
            row_descripcion.getChildren().addAll(descripcion,descripcion_content);
            /*Lugar*/
            HBox row_Lugar=new HBox();
            Label lugar=new Label("Lugar:" );
            lugar.setStyle("-fx-background-color: gray;");
            Label lugar_content=info.get(7);
            HBox.setMargin(lugar,new Insets(15));
            HBox.setMargin(lugar_content,new Insets(15));
            row_Lugar.getChildren().addAll(lugar,lugar_content);
            /*Stands*/
            HBox row_Stands=new HBox();
            Label stands=new Label("Stands:" );
            stands.setStyle("-fx-background-color: gray;");
            Label stands_content=info.get(8);
            HBox.setMargin(stands,new Insets(15));
            HBox.setMargin(stands_content,new Insets(15));
            row_Stands.getChildren().addAll(stands,stands_content);
            contInf.getChildren().addAll(row_nombre,row_fInicio,row_fFin, row_fAusp, row_Empr,row_Horario,row_descripcion,row_Lugar,row_Stands);
            sp.setContent(contInf);
            BorderPane.setAlignment(nombrePanel, Pos.CENTER);
            BorderPane.setAlignment(sp, Pos.CENTER);
            BorderPane.setMargin(nombrePanel, new Insets(15));
            BorderPane.setMargin(sp, new Insets(20));
            bp.setTop(nombrePanel);
            bp.setCenter(sp);
            Scene new_scene=new Scene(bp,500,500);
            s.initModality(Modality.APPLICATION_MODAL);
            s.setScene(new_scene);
            s.showAndWait();
        }
    }
    /*metodo del boton mostrar detalles de feria*/

    public ArrayList<Label> labelsInfoFeria(Feria f){
        ArrayList<Label>labels=new ArrayList<>();
        Label nombre=new Label(f.getNombre());
        Label fechaInicio=new Label(f.getfInicio().toString());
        Label horario=new Label(f.getHorario());
        Label descripcion=new Label(f.getDescripcion());
        Label Lugar=new Label(f.getLugar());
        Label fechaFin=new Label(f.getfFin().toString());
        Label auspiciantes=new Label((f.getlAuspiciantes().isEmpty())?" no hay auspiciantes":infoAuspiciantes(f));
        Label emprendedores=new Label((infoEmprendedores(f)=="")?"no emprendedor registrado":infoEmprendedores(f));
        Label stands=new Label(infoStands(f));
        labels.add(nombre);
        labels.add(fechaInicio);
        labels.add(fechaFin);
        labels.add(auspiciantes);
        labels.add(emprendedores);
        labels.add(horario);
        labels.add(descripcion);
        labels.add(Lugar);
        labels.add(stands);
        return labels;
    }
    /*retorna string con el nombre de los emprendedores registrados*/
    public String infoEmprendedores(Feria f){
        String s="";
        for(Seccion sec:f.getSecciones()){
            for (Stand st: sec.getStands()){
                Persona persona=(st.getPersona_responsable()!=null)?st.getPersona_responsable():null;
                
                if (persona!=null){
                        String infoEmp="";
                        System.out.println(persona);
                       String nombre_emprendedor=persona.getNombre();
                       String nombre=persona.getNombre_de_responsable();
                       String email=(persona.getEmail()!=null)?persona.getEmail():"no registrado";
                       String direccion=(persona.getDireccion()!=null)?persona.getDireccion():"no registrado";
                       String id=persona.getIdentificacion();
                       infoEmp="Nombre emprendimiento: "+nombre_emprendedor+"\n"+"Nombre: "+nombre+"\n"+
                               "Identificacion: "+id+"\n"+"Email: "+email+"\n"+"Direccion: "+direccion;
                       s+="\n"+infoEmp+"\n";
   
                }
                
            }
            
          
        }
        return s;
    }
    public String infoStands(Feria f){
        String s="";
        for(Seccion sec:f.getSecciones()){
            for (Stand st: sec.getStands()){
                
                Persona persona=(st.getPersona_responsable()!=null)?st.getPersona_responsable():null;
                
                if (persona!=null){
                       
                    s+="[*"+st.getCodigo()+"]"+"-->Reservado"+"\n";
                }else{
                    s+="["+st.getCodigo()+"]"+"-->Disponible"+"\n";
                }
                
            }
          
        }
        return s;
    }
    /*retorna un string formateado para los auspiciantes*/
    public String infoAuspiciantes(Feria f){
        String s="";
       
        if (f!=null){
            for (Auspiciante a: f.getlAuspiciantes()){
                String n=a.getNombre();
                String rep=a.getNombre_de_responsable();
                String sect=a.getSector_cubierto().toString();
                String info="Empresa: "+n+"\n"+"Nombre: "+rep+"\n"+"Sector: "+sect;
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
