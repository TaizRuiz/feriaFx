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
import clasesEstaticas.AdministracionFeria;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
           interfaz_crear_feria();
      
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
    /*metodos para crear una feria nueva*/
    
    public void interfaz_crear_feria(){
        /*String nombre, String lugar, String descripcion, LocalDate fInicio, LocalDate fFin, String horario, int sec1, int sec2, int sec3, int sec4*/
        
        Label l_nombre=new Label("Nombre de la feria:");
        TextField nombre_field=new TextField();
        HBox cont_name=new HBox(l_nombre,nombre_field);
        /*lugar*/
        Label l_lugar=new Label("Lugar de la feria:");
        TextField lugar_field=new TextField();
        HBox cont_lugar=new HBox(l_lugar,lugar_field);
        /*descripcion*/
        Label l_descripcion=new Label("Descripcion de la feria:");
        TextArea descripcion_field=new TextArea();
        descripcion_field.setMaxSize(160,80);
        HBox cont_descripcion=new HBox(l_descripcion,descripcion_field);
        
        /*fechas*/
        Label l_fecha=new Label("Fecha de inicio:");
        DatePicker fechaInicio = new DatePicker(); 
       fechaInicio.setOnAction(e -> System.out.println( fechaInicio.getValue()));
        HBox cont_fInicio=new HBox(l_fecha,fechaInicio);
        /*fecha fin*/
         Label l_fechaFin=new Label("Fecha de Fin:");
        DatePicker fechaFin = new DatePicker(); 
        fechaFin.setOnAction(e -> System.out.println( fechaFin.getValue()));
        HBox cont_fFin=new HBox(l_fechaFin,fechaFin);
        Stage s=new Stage();
        BorderPane bp=new BorderPane();
        VBox contLabels=new VBox();
        
        /*Hora de inicio*/
        Label l_horario=new Label("Horario de la feria:");
        TextField horario_field=new TextField();
        horario_field.setMaxWidth(50);
        HBox cont_horario=new HBox(l_horario,horario_field);
        /*Numero de stands*/
        Label stands=new Label("Ingresa la cantidad de stands por seccion");
        Label l_sec1=new Label("Seccion 1:");
        TextField sec1_field=new TextField();
        sec1_field.setMaxSize(50,10);
        Label l_sec2=new Label("Seccion 2:");
        TextField sec2_field=new TextField();
        sec2_field.setMaxSize(50,10);
        Label l_sec3=new Label("Seccion 3:");
        TextField sec3_field=new TextField();
        sec3_field.setMaxSize(50,10);
        Label l_sec4=new Label("Seccion 4:");
        TextField sec4_field=new TextField();
        sec4_field.setMaxSize(50,10);
        HBox cont_sec1=new HBox(l_sec1,sec1_field);
        HBox cont_sec2=new HBox(l_sec2,sec2_field);
        HBox cont_sec3=new HBox(l_sec3,sec3_field);
        HBox cont_sec4=new HBox(l_sec4,sec4_field);
        
        ScrollPane sp=new ScrollPane();
        Label titulo=new Label("Registrar nueva feria");
        bp.setTop(titulo);
        contLabels.getChildren().addAll(cont_name, cont_lugar,cont_descripcion,cont_fInicio, cont_fFin,cont_horario,stands,cont_sec1
        ,cont_sec2,cont_sec3,cont_sec4);
        sp.setContent(contLabels);
        VBox.setMargin(stands, new Insets(10));
        HBox.setMargin(l_horario, new Insets(10));
        HBox.setMargin(horario_field, new Insets(10));
        HBox.setMargin(l_sec1, new Insets(10));
        HBox.setMargin(sec1_field, new Insets(10));
        HBox.setMargin(l_sec2, new Insets(10));
        HBox.setMargin(sec2_field, new Insets(10));
        HBox.setMargin(l_sec3, new Insets(10));
        HBox.setMargin(sec3_field, new Insets(10));
        HBox.setMargin(l_sec4, new Insets(10));
        HBox.setMargin(sec4_field, new Insets(10));
        HBox.setMargin(l_nombre, new Insets(10));
        HBox.setMargin(nombre_field, new Insets(10));
        HBox.setMargin(l_descripcion, new Insets(10));
        HBox.setMargin(descripcion_field, new Insets(10));
        HBox.setMargin(l_lugar ,new Insets(10));
        HBox.setMargin(lugar_field, new Insets(10));
        HBox.setMargin(l_fecha, new Insets(10));
        HBox.setMargin(fechaInicio, new Insets(10));
        HBox.setMargin(l_fechaFin, new Insets(10));
        HBox.setMargin(fechaFin, new Insets(10));
        Button crear=new Button("Crear feria");
        bp.setBottom(crear);
        
        bp.setCenter(sp);
        BorderPane.setMargin(titulo, new Insets(15));
        BorderPane.setMargin(sp, new Insets(15));
        BorderPane.setMargin(crear, new Insets(15));
        BorderPane.setAlignment(crear, Pos.CENTER);
        BorderPane.setAlignment(titulo, Pos.CENTER);
        Scene new_scene=new Scene(bp,500,500);
        s.initModality(Modality.APPLICATION_MODAL);
        
        crear.setOnAction(eh->{
            String nombre,lugar,descripcion,horario;
            LocalDate fInicio,fFin;
            int sec1,sec2,sec3,sec4;
            nombre=(nombre_field.getText().isBlank())? null:nombre_field.getText();
            lugar=(lugar_field.getText().isBlank())? null:lugar_field.getText();
            descripcion=(descripcion_field.getText().isBlank())? null:descripcion_field.getText();
            horario=(horario_field.getText().isBlank())? null:horario_field.getText();;
            fInicio=(fechaInicio.getValue()==null)? null:fechaInicio.getValue();
            fFin=(fechaFin.getValue()==null)? null:fechaFin.getValue();
            sec1=(sec1_field.getText().isBlank())? 0: Integer.parseInt(sec1_field.getText());
            sec2=(sec2_field.getText().isBlank())? 0: Integer.parseInt(sec2_field.getText());
            sec3=(sec3_field.getText().isBlank())? 0: Integer.parseInt(sec3_field.getText());
            sec4=(sec4_field.getText().isBlank())? 0: Integer.parseInt(sec4_field.getText());
            if (nombre != null && lugar != null && descripcion != null && horario != null && fInicio != null && fFin != null && sec1 !=0 && sec2 != 0 && sec3 != 0 && sec4 != 0) {
                AdministracionFeria.registrarFeria(nombre, lugar, descripcion, fInicio, fFin, horario, sec1, sec2, sec3, sec4);
                 Alert a=new Alert(Alert.AlertType.CONFIRMATION);
                a.setContentText("Se creo una nueva Feria");
                a.showAndWait();
                s.close();
                try {
                    App.setRoot("administracionFeria");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } else {
                // Al menos uno de los valores es null, maneja la lógica correspondiente aquí
                 Alert a=new Alert(Alert.AlertType.ERROR);
                a.setContentText("Debe llenar todos los campos");
                a.showAndWait();
                eh.consume();
                
            }

        });
        s.setScene(new_scene);
        s.showAndWait();
        
        
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
