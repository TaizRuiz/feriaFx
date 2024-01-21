/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.feria_poo;

import clases.Auspiciante;
import clases.Socials;
import clasesEstaticas.AdministracionAuspiciantes;
import enums.TipoServicio;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author Kevin
 */
public class AdministracionAuspicianteController implements Initializable {


    @FXML
    private BorderPane contenedorPrincipal;
    @FXML
    private ImageView btnregresar;
    @FXML
    private Button btndetalles;
    @FXML
    private Button btnnuevoauspi;
    @FXML
    private Button btneditarauspi;
    @FXML
    private Button btnasignarauspi;
    private TableView<Auspiciante> listaAuspiciante;
    private Auspiciante auspicianteSeleccionado = null;
    private TipoServicio servicioOfertado=null;
    private String red_selected="";
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cargarEmprendedor();
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
        if (this.auspicianteSeleccionado!=null){
            System.out.println("Entro");
            mostrarVentanaDetalles();
        }else{
            Alert a=new Alert(Alert.AlertType.ERROR);
            a.setContentText("Selecciona un auspiciante");
            a.showAndWait();
            event.consume();
        }
    }

    @FXML
    private void nuevoAuspiciante(MouseEvent event) {
        interfaz_crear_auspiciante();
    }

    @FXML
    private void editarAuspiciante(MouseEvent event) {
    }

    @FXML
    private void AsignarAuspiciante(MouseEvent event) {
    }
    
    public void cargarEmprendedor(){
        listaAuspiciante = new TableView();
        
        
        TableColumn<Auspiciante, String> colNombre = new TableColumn<>("Nombre");
        TableColumn<Auspiciante, String> colCedula = new TableColumn<>("Cedula");
        TableColumn<Auspiciante, String> colTelefono = new TableColumn<>("Telefono");
        TableColumn<Auspiciante, String> colEmail = new TableColumn<>("Email");
        colNombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        colCedula.setCellValueFactory(new PropertyValueFactory<>("identificacion"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("Telefono"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
        
        listaAuspiciante.setItems(FXCollections.observableArrayList(App.auspiciantes));
        listaAuspiciante.getColumns().addAll(colNombre,colCedula,colTelefono,colEmail);
        listaAuspiciante.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // Aquí puedes trabajar con el objeto seleccionado (newValue)
                this.auspicianteSeleccionado=newValue;
                System.out.println(auspicianteSeleccionado);
            }
        });
        contenedorPrincipal.setCenter(listaAuspiciante);
        BorderPane.setMargin(listaAuspiciante, new Insets(25));
    }
    
    public void mostrarVentanaDetalles(){
        Auspiciante auspicianteOg = (auspicianteSeleccionado!=null)? buscarEmprendedor():null;
        if(auspicianteOg!=null){
            Stage s=new Stage();
            BorderPane bp = new BorderPane();
            VBox contInf = new VBox();
            Label nombrePanel = new Label("Detalles de Auspiciantes");
            contInf.getChildren().addAll(labelsInfoEmprendedor(auspicianteOg));
            BorderPane.setAlignment(nombrePanel, Pos.CENTER);
            BorderPane.setAlignment(contInf, Pos.BASELINE_LEFT);
            BorderPane.setMargin(nombrePanel, new Insets(15));
            BorderPane.setMargin(contInf, new Insets(20));
            bp.setTop(nombrePanel);
            bp.setCenter(contInf);
            Scene new_scene=new Scene(bp,250,200);
            s.initModality(Modality.APPLICATION_MODAL);
            s.setScene(new_scene);
            s.showAndWait();
            
            
        }
        
    }
    
    public void interfaz_crear_auspiciante(){
        //String id, String nombre, String telefono, String email, String direccion, String sitioWeb, 
        //String nomResponsable, String descripcion, ArrayList<Socials> redes 
        //String id, String nombre, String nombre_responsable, String telefono, String email, String direccion, String sitio_web, TipoServicio servicio,  ArrayList<Socials> redes_sociales
        ArrayList<Socials> socials=new ArrayList<>();
        // id
        Label l_id = new Label("Numero de cedula: ");
        TextField id_field = new TextField();
        HBox cont_id = new HBox(l_id, id_field);
        // nombre
        Label l_name = new Label("Nombre Aupicio: ");
        TextField name_field = new TextField();
        HBox cont_name = new HBox(l_name, name_field);
        //telefono
        Label l_telefono = new Label("Telefono: ");
        TextField telefono_field = new TextField();
        HBox cont_telefono = new HBox(l_telefono, telefono_field);
        //Email
        Label l_email = new Label("Email: ");
        TextField email_field = new TextField();
        HBox cont_email = new HBox(l_email, email_field);
        //Direccion 
        Label l_direccion = new Label("Direccion: ");
        TextField direccion_field = new TextField();
        HBox cont_direccion = new HBox(l_direccion, direccion_field);
        //sitio web
        Label l_sitio = new Label("Sitio web: ");
        TextField sitio_field = new TextField();
        HBox cont_sitio = new HBox(l_sitio, sitio_field);
        //nombre responsable
        Label l_name2 = new Label("Nombre de persona responsable: ");
        TextField name2_field = new TextField();
        HBox cont_name2 = new HBox(l_name2, name2_field);
        //servicio
        HBox serviciosh=new HBox();
        serviciosh.setPadding(new Insets(5));
        serviciosh.setSpacing(5);
        Label ser=new Label("Servicio: ");
        ComboBox<String> servicios=new ComboBox<>(FXCollections.observableArrayList("ALIMENTACIÓN", "EDUCACIÓN", "SALUD", "VESTIMENTA"));
        serviciosh.getChildren().addAll(ser,servicios);
        
        //redes social
        HBox sociales=new HBox();
        sociales.setPadding(new Insets(5));
        sociales.setSpacing(5);
        ComboBox<String> redes=new ComboBox<>(FXCollections.observableArrayList("Facebook","Instagram","Twitter","Tiktok"));
        TextField username=new TextField();
        username.setPromptText("Ingresa tu username");
        Button registrarRed=new Button("Guardar red social");
        sociales.getChildren().addAll(redes,username,registrarRed);
        /*setea valor de servicio ofertado*/
        servicios.setOnAction(eh->{
               servicioOfertado=TipoServicio.valueOf(servicios.getValue());
               System.out.println(servicioOfertado+" servicio ofertada");
            });
        /*setea valor de red social*/
            redes.setOnAction(a->{
            String red_selected = redes.getValue();
            });
            
           /*anade la red social a un array*/
            registrarRed.setOnAction(eh->{
                if (red_selected!="" && (!(username.getText().isBlank()|| username.getText().isEmpty()))){
                    Socials social=new Socials(red_selected, username.getText());
                    socials.add(social);
                }
                red_selected="";
                redes.getSelectionModel().clearSelection();
                username.clear();
            
            });
        VBox contLabels=new VBox();
        BorderPane bp=new BorderPane();
        ScrollPane sp=new ScrollPane();
        Label titulo = new Label ("Regsitrar Auspiciante");
        bp.setTop(titulo);
        contLabels.getChildren().addAll(cont_id, cont_name, cont_telefono,cont_email,cont_direccion,cont_sitio, cont_name2, serviciosh,sociales);
        sp.setContent(contLabels);
        HBox.setMargin(l_id, new Insets(10));
        HBox.setMargin(l_name, new Insets(10));
        HBox.setMargin(l_name2, new Insets(10));
        HBox.setMargin(l_telefono, new Insets(10));
        HBox.setMargin(l_email, new Insets(10));
        HBox.setMargin(l_direccion, new Insets(10));
        HBox.setMargin(l_sitio, new Insets(10));
        
        Button crear=new Button("Crear auspiciante");
        bp.setBottom(crear);
        Stage s=new Stage();  
        bp.setCenter(sp);
        BorderPane.setMargin(titulo, new Insets(15));
        BorderPane.setMargin(sp, new Insets(15));
        BorderPane.setMargin(crear, new Insets(15));
        BorderPane.setAlignment(crear, Pos.CENTER);
        BorderPane.setAlignment(titulo, Pos.CENTER);
        Scene new_scene=new Scene(bp,500,500);
        s.initModality(Modality.APPLICATION_MODAL);
        
        crear.setOnAction(eh->{
            String id, nombre, telefono, email, direccion, sitioWeb, nomResponsable;
            TipoServicio servicio;
            servicio=(servicioOfertado==null)?null:servicioOfertado;
            ArrayList<Socials> redes_sociales=new ArrayList();
            id = (id_field.getText().isBlank()) ? null: id_field.getText();
            nomResponsable=(name_field.getText().isBlank())? null:name_field.getText();
            nombre=(name2_field.getText().isBlank())? null:name2_field.getText();
            telefono=(telefono_field.getText().isBlank())? null:telefono_field.getText();
            email=(email_field.getText().isBlank())? null:email_field.getText();
            direccion=(direccion_field.getText().isBlank())? null:direccion_field.getText();
        
            sitioWeb=(sitio_field.getText().isBlank())? null:sitio_field.getText();
            redes_sociales=socials;
            
            if (nombre != null && id != null && nomResponsable != null && telefono != null && email != null && direccion != null && sitioWeb != null) {
                AdministracionAuspiciantes.registrar_auspiciante(id, nombre, nomResponsable, telefono, email, direccion, sitioWeb, servicio,redes_sociales);
                Alert a=new Alert(Alert.AlertType.CONFIRMATION);
                a.setContentText("Se registro un nuevo auspiciante");
                a.showAndWait();
                s.close();
                try {
                    App.setRoot("administracionAuspiciante");
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
    
    /*metodo del boton mostrar detalles de emprendedor*/
    public ArrayList<Label> labelsInfoEmprendedor(Auspiciante a){
        ArrayList<Label>labels = new ArrayList<>();
        Label nombre = new Label("Nombre: "+a.getNombre());
        Label cedula = new Label("Cedula: "+a.getIdentificacion());
        Label telefono = new Label("Telefono: "+a.getTelefono());
        Label email = new Label("Email: "+a.getEmail());
        
        labels.add(nombre);
        labels.add(cedula);
        labels.add(telefono);
        labels.add(email);
        return labels;
    
    }
    
    public Auspiciante buscarEmprendedor(){
        Auspiciante a = null;
        if(this.auspicianteSeleccionado!=null){
            for(Auspiciante auspiciante:App.auspiciantes){
                a=(auspiciante.equals(auspicianteSeleccionado))?auspiciante:a;
            }
        }
        System.out.println("Emprendedor retornado por buscar mprendedor:"+a);
        return a;
    }

}
