/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.feria_poo;

import clases.Emprendedor;
import clases.Socials;
import clases.*;
import clasesEstaticas.AdministracionEmprendedores;
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
public class AdministracionEmprendedoresController implements Initializable {

    @FXML
    private BorderPane contenedorPrincipal;
    @FXML
    private ImageView btnregresar;
    @FXML
    private Button btndetalles;
    @FXML
    private Button btnnuevoempre;
    @FXML
    private Button byneditarempre;
    private TableView<Emprendedor> listaEmprendedor;
    private Emprendedor emprendedorSeleccionado = null;
    
    private String persona_responsable="";
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
        if (this.emprendedorSeleccionado!=null){
            System.out.println("Entro");
            mostrarVentanaDetalles();
        }else{
            Alert a=new Alert(Alert.AlertType.ERROR);
            a.setContentText("Selecciona un emprendedor");
            a.showAndWait();
            event.consume();
        }
    }

    @FXML
    private void nuevoEmpre(MouseEvent event) {
        interfaz_crear_emprendedor();
    }

    @FXML
    private void editarEmpre(MouseEvent event) {
         if (this.emprendedorSeleccionado!=null){
           
             interfaz_editar_emprendedor(emprendedorSeleccionado);
        }else{
            Alert a=new Alert(Alert.AlertType.ERROR);
            a.setContentText("Selecciona un emprendedor");
            a.showAndWait();
            event.consume();
        }
    }
    
    public void cargarEmprendedor(){
        listaEmprendedor = new TableView();
        
        
        TableColumn<Emprendedor, String> colNombre = new TableColumn<>("Nombre");
        TableColumn<Emprendedor, String> colCedula = new TableColumn<>("Cedula");
        TableColumn<Emprendedor, String> colTelefono = new TableColumn<>("Telefono");
        TableColumn<Emprendedor, String> colEmail = new TableColumn<>("Email");
        colNombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        colCedula.setCellValueFactory(new PropertyValueFactory<>("identificacion"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("Telefono"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
        
        listaEmprendedor.setItems(FXCollections.observableArrayList(App.emprendedores));
        listaEmprendedor.getColumns().addAll(colNombre,colCedula,colTelefono,colEmail);
        listaEmprendedor.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // Aquí puedes trabajar con el objeto seleccionado (newValue)
                this.emprendedorSeleccionado=newValue;
                System.out.println(emprendedorSeleccionado);
            }
        });
        contenedorPrincipal.setCenter(listaEmprendedor);
        BorderPane.setMargin(listaEmprendedor, new Insets(25));
    }
    
    public void mostrarVentanaDetalles(){
        Emprendedor emprendedorOg = (emprendedorSeleccionado!=null)? buscarEmprendedor():null;
        if(emprendedorOg!=null){
            Stage s=new Stage();
            BorderPane bp = new BorderPane();
            VBox contInf = new VBox();
            Label nombrePanel = new Label("Detalles de Emprendedores");
            contInf.getChildren().addAll(labelsInfoEmprendedor(emprendedorOg));
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
    //editar emprendedor//
    public void interfaz_crear_emprendedor(){
        //String id, String nombre, String telefono, String email, String direccion, String sitioWeb, 
        //String nomResponsable, String descripcion, ArrayList<Socials> redes 
        ArrayList<Socials> socials=new ArrayList<>();
        // id
        Label l_id = new Label("Numero de cedula: ");
        TextField id_field = new TextField();
        HBox cont_id = new HBox(l_id, id_field);
        // nombre
        Label l_name = new Label("Nombre del emprendimiento: ");
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
        //descripcion 
        Label l_descripcion=new Label("Descripcion del emprendimiento:");
        TextArea descripcion_field=new TextArea();
        descripcion_field.setMaxSize(160,80);
        HBox cont_descripcion=new HBox(l_descripcion,descripcion_field);
        //redes social
        HBox sociales=new HBox();
        sociales.setPadding(new Insets(5));
        sociales.setSpacing(5);
        ComboBox<String> redes=new ComboBox<>(FXCollections.observableArrayList("Facebook","Instagram","Twitter","Tiktok"));
        TextField username=new TextField();
        username.setPromptText("Ingresa tu username");
        Button registrarRed=new Button("Guardar red social");
        sociales.getChildren().addAll(redes,username,registrarRed);
        /*setea valor de red social*/
            redes.setOnAction(a->{
                red_selected=redes.getValue();
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
        Label titulo = new Label ("Regsitrar Emprendedor");
        bp.setTop(titulo);
        contLabels.getChildren().addAll(cont_id, cont_name, cont_telefono,cont_email,cont_direccion,cont_sitio, cont_name2, cont_descripcion,sociales);
        sp.setContent(contLabels);
        HBox.setMargin(l_id, new Insets(10));
        HBox.setMargin(l_name, new Insets(10));
        HBox.setMargin(l_name2, new Insets(10));
        HBox.setMargin(l_telefono, new Insets(10));
        HBox.setMargin(l_email, new Insets(10));
        HBox.setMargin(l_direccion, new Insets(10));
        HBox.setMargin(l_sitio, new Insets(10));
        HBox.setMargin(l_descripcion, new Insets(10));
        Button crear=new Button("Crear emprendedor");
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
            String id, nombre, telefono, email, direccion, sitioWeb, nomResponsable, descripcion;
            ArrayList<Socials> redes_sociales=new ArrayList();
            id = (id_field.getText().isBlank()) ? null: id_field.getText();
            nomResponsable=(name_field.getText().isBlank())? null:name_field.getText();
            nombre=(name2_field.getText().isBlank())? null:name2_field.getText();
            telefono=(telefono_field.getText().isBlank())? null:telefono_field.getText();
            email=(email_field.getText().isBlank())? null:email_field.getText();
            direccion=(direccion_field.getText().isBlank())? null:direccion_field.getText();
            descripcion=(descripcion_field.getText().isBlank())? null:descripcion_field.getText();
            sitioWeb=(sitio_field.getText().isBlank())? null:sitio_field.getText();
            redes_sociales=socials;
            
            if (nombre != null && id != null && descripcion != null && nomResponsable != null && telefono != null && email != null && direccion != null && sitioWeb != null) {
                AdministracionEmprendedores.registrarEmprendedor(id, nombre, telefono, email, direccion, sitioWeb, nomResponsable, descripcion,redes_sociales);
                 Alert a=new Alert(Alert.AlertType.CONFIRMATION);
                a.setContentText("Se registro un nuevo emprendedor");
                a.showAndWait();
                s.close();
                try {
                    App.setRoot("administracionEmprendedores");
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
    
    
    //Crear emprendedor
    public void interfaz_editar_emprendedor(Emprendedor e){
        //String id, String nombre, String telefono, String email, String direccion, String sitioWeb, 
        //String nomResponsable, String descripcion, ArrayList<Socials> redes 
        ArrayList<Socials> socials=new ArrayList<>();
        // id
        Label l_id = new Label("Numero de cedula: ");
        TextField id_field = new TextField();
        id_field.setText(e.getIdentificacion());
        id_field.setEditable(false);
        HBox cont_id = new HBox(l_id, id_field);
        // nombre
        Label l_name = new Label("Nombre del emprendimiento: ");
        TextField name_field = new TextField();
        name_field.setText(e.getNombre());
        HBox cont_name = new HBox(l_name, name_field);
        //telefono
        Label l_telefono = new Label("Telefono: ");
        TextField telefono_field = new TextField();
        telefono_field.setText(e.getTelefono());
        HBox cont_telefono = new HBox(l_telefono, telefono_field);
        //Email
        Label l_email = new Label("Email: ");
        TextField email_field = new TextField();
        email_field.setText(e.getEmail());
        HBox cont_email = new HBox(l_email, email_field);
        //Direccion 
        Label l_direccion = new Label("Direccion: ");
        TextField direccion_field = new TextField();
        direccion_field.setText(e.getDireccion());
        HBox cont_direccion = new HBox(l_direccion, direccion_field);
        //sitio web
        Label l_sitio = new Label("Sitio web: ");
        TextField sitio_field = new TextField();
        sitio_field.setText(e.getSitio_web());
        HBox cont_sitio = new HBox(l_sitio, sitio_field);
        //nombre responsable
        Label l_name2 = new Label("Nombre de persona responsable: ");
        TextField name2_field = new TextField();
        name2_field.setText(e.getNombre_de_responsable());
        HBox cont_name2 = new HBox(l_name2, name2_field);
        //descripcion 
        Label l_descripcion=new Label("Descripcion del emprendimiento:");
        TextArea descripcion_field=new TextArea();
        descripcion_field.setText(e.getDescripcion_servicio());
        descripcion_field.setMaxSize(160,80);
        HBox cont_descripcion=new HBox(l_descripcion,descripcion_field);
        //redes social
        HBox sociales=new HBox();
        sociales.setPadding(new Insets(5));
        sociales.setSpacing(5);
        ComboBox<String> redes=new ComboBox<>(FXCollections.observableArrayList("Facebook","Instagram","Twitter","Tiktok"));
        TextField username=new TextField();
        username.setPromptText("Ingresa tu username");
        Button registrarRed=new Button("Guardar red social");
        sociales.getChildren().addAll(redes,username,registrarRed);
        /*setea valor de red social*/
            redes.setOnAction(a->{
                red_selected=redes.getValue();
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
        Label titulo = new Label ("Editar Emprendedor");
        bp.setTop(titulo);
        contLabels.getChildren().addAll(cont_id, cont_name, cont_telefono,cont_email,cont_direccion,cont_sitio, cont_name2, cont_descripcion,sociales);
        sp.setContent(contLabels);
        HBox.setMargin(l_id, new Insets(10));
        HBox.setMargin(l_name, new Insets(10));
        HBox.setMargin(l_name2, new Insets(10));
        HBox.setMargin(l_telefono, new Insets(10));
        HBox.setMargin(l_email, new Insets(10));
        HBox.setMargin(l_direccion, new Insets(10));
        HBox.setMargin(l_sitio, new Insets(10));
        HBox.setMargin(l_descripcion, new Insets(10));
        Button crear=new Button("Guardar Cambios");
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
            String id, nombre, telefono, email, direccion, sitioWeb, nomResponsable, descripcion;
            ArrayList<Socials> redes_sociales=new ArrayList();
            
            nomResponsable=(name_field.getText().isBlank())? e.getNombre():name_field.getText();
            nombre=(name2_field.getText().isBlank())? e.getNombre_de_responsable():name2_field.getText();
            telefono=(telefono_field.getText().isBlank())? e.getTelefono():telefono_field.getText();
            email=(email_field.getText().isBlank())? e.getEmail():email_field.getText();
            direccion=(direccion_field.getText().isBlank())? e.getDireccion():direccion_field.getText();
            descripcion=(descripcion_field.getText().isBlank())? e.getDescripcion_servicio():descripcion_field.getText();
            sitioWeb=(sitio_field.getText().isBlank())? e.getSitio_web():sitio_field.getText();
            redes_sociales=socials;
            e.setNombre(nombre);
            e.setDescripcion_servicio(descripcion);
            e.setEmail(email);
            e.setNombre_de_responsable(nomResponsable);
            e.setSitio_web(sitioWeb);
            e.setDireccion(direccion);
            e.setTelefono(telefono);
            Alert a=new Alert(Alert.AlertType.CONFIRMATION);
            a.setContentText("Se actualizaron los datos del emprendedor");
             a.showAndWait();
                s.close();
                try {
                    App.setRoot("administracionEmprendedores");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
           

        });
        s.setScene(new_scene);
        s.showAndWait();
        
            
        
    }
    
    /*metodo del boton mostrar detalles de emprendedor*/
    public ArrayList<Label> labelsInfoEmprendedor(Emprendedor e){
        ArrayList<Label>labels = new ArrayList<>();
        Label nombre = new Label("Nombre: "+e.getNombre());
        Label cedula = new Label("Cedula: "+e.getIdentificacion());
        Label telefono = new Label("Telefono: "+e.getTelefono());
        Label email = new Label("Email: "+e.getEmail());
        
        labels.add(nombre);
        labels.add(cedula);
        labels.add(telefono);
        labels.add(email);
        return labels;
    
    }
    
    public Emprendedor buscarEmprendedor(){
        Emprendedor e = null;
        if(this.emprendedorSeleccionado!=null){
            for(Emprendedor emprendedor:App.emprendedores){
                e=(emprendedor.equals(emprendedorSeleccionado))?emprendedor:e;
            }
        }
        System.out.println("Emprendedor retornado por buscar mprendedor:"+e);
        return e;
    }
    
}
