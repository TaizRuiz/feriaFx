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
import clases.Socials;
import clases.Stand;
import enums.TipoServicio;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Kevin
 */
public class AdministracionStansController implements Initializable {

    @FXML
    private ImageView btregresarS;
    private TableView<Feria> listaFeria=null;
    private Feria feria_seleccionada=null;
    private String persona_responsable="";
    private String red_selected="";
    private TipoServicio servicioOfertado=null;
    @FXML
    private BorderPane contenedorPrincipal;
    @FXML
    private Button btnVer;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarFerias();
    }    

    @FXML
    private void regresar4(MouseEvent event) {
        try {
            App.setRoot("primary");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    /*muestra la tabla con las ferias registradas*/
    public void cargarFerias(){
        listaFeria = new TableView();
        TableColumn<Feria,Integer> colCodigo = new TableColumn<>("Codigo");
        colCodigo.setMaxWidth(50);
        TableColumn<Feria, String> colNombre = new TableColumn<>("Nombre");
        TableColumn<Feria, String> colFechaInicio = new TableColumn<>("Fecha");
        TableColumn<Feria, String> colLugar = new TableColumn<>("Lugar");
        TableColumn<Feria, Integer> colCantAusp = new TableColumn<>("Num Auspiciantes");
        TableColumn<Feria, Integer> colCantEmpr = new TableColumn<>("Num Emprendedores");
        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colFechaInicio.setCellValueFactory(new PropertyValueFactory<>("fIn"));
        
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

    @FXML
    /*mdtodo de visualizacion y reserva*/
    private void verStands(ActionEvent event) {
        /*valida que el usuario seleccione una feria */
        if (this.feria_seleccionada!=null){
            panelStands(feria_seleccionada);
           
        }else{
            Alert a=new Alert(Alert.AlertType.ERROR);
            a.setContentText("Selecciona una feria");
            a.showAndWait();
            event.consume();
        }
    }
    /*Muestra los stands de la feria seleccionada*/
    private void panelStands(Feria f){
        Stage st=new Stage();
        BorderPane scena=new BorderPane();
        scena.setPrefSize(500, 350);
        scena.setPadding(new Insets(20));
        Scene escena=new Scene(scena);
        VBox cont_secciones=new VBox();
        for (Seccion sec:f.getSecciones()){
            for (Stand stand: sec.getStands()){
                /*si la lista de responsables NO esta vacia y su longitud es EXACTAMENTE 2 el stand esta full*/
               boolean condicion=(!(stand.getPersona_responsable().isEmpty())) && (stand.getPersona_responsable().size()==2);
               /*descripcion formato que define la disponibilidad de un stand*/
               Label descripcion=(condicion)?new Label("[*"+stand.getCodigo()+"]"):new Label("["+stand.getCodigo()+"]");
               cont_secciones.getChildren().add(descripcion);
               VBox.setMargin(descripcion, new Insets(15));
               /*si el stand esta full solo se muestra la info de las reservas*/
               if (condicion){
                    descripcion.setOnMouseClicked(a->{
                        /*Mostrar el código del stand, fecha de asignación y la información del emprendedor o
                        auspiciante que tiene reservado el stand. Se debe mostrar nombre, teléfono, email
                        del emprendedor o auspiciante.*/
                        VBox info_stand=new VBox();
                        Label reserva=new Label("Informacion de reserva");
                        Label cod=new Label("Codigo: ");
                        Label codigo=new Label(String.valueOf(stand.getCodigo()));
                        HBox cont_cod=new HBox(cod,codigo);
                        HBox.setMargin(cod, new Insets(5));
                        HBox.setMargin(codigo, new Insets(5));
                        Label fech=new Label("fecha de asignacion:");
                        Label fecha=new Label(stand.getFechaAsignacion().toString());
                        HBox cont_fecha=new HBox(fech,fecha);
                        HBox.setMargin(fech, new Insets(5));
                        HBox.setMargin(fecha, new Insets(5));
                        info_stand.getChildren().addAll(reserva,cont_cod,cont_fecha);
                        ArrayList<Persona> personas=stand.getPersona_responsable();
                        if (!personas.isEmpty()){
                           for (Persona p: personas){

                            info_stand.getChildren().add(infoPersona(p));
                        } 
                        }
                        
                       /*setea ese panel de informacion en la derecha del panel principal */
                        scena.setRight(info_stand);
                    });
               }else{
                   /*significa que todavia hay un puesto en el stands o el stand esta vacio*/
                   
                   descripcion.setOnMouseClicked(b->{

                        scena.setRight(ventana_reserva(stand,f));
                    });
               }
            }
        }
        
        scena.setCenter(cont_secciones);
        st.initModality(Modality.APPLICATION_MODAL);
        st.setScene(escena);
        st.showAndWait();
        
    }
    /*metodo que muestra la info de una persona */
    public VBox infoPersona(Persona p){
        Persona per=(p instanceof Emprendedor)? (Emprendedor)p:(Auspiciante) p;
                            Label nom=new Label("Persona o Institucion: ");
                            Label nombre=new Label(per.getNombre());
                            HBox cont_nom=new HBox(nom,nombre);
                            HBox.setMargin(nom, new Insets(5));
                            HBox.setMargin(nombre, new Insets(5));
                            Label tel=new Label("Telefono: ");
                            Label telefono=(per.getTelefono()!=null)?new Label(per.getTelefono()): new Label("no hay numero registrado");
                            HBox cont_tel=new HBox(tel,telefono);
                            HBox.setMargin(tel, new Insets(5));
                            HBox.setMargin(telefono, new Insets(5));
                            Label em=new Label("Email: ");
                            Label email=(per.getEmail()!=null)?new Label(per.getEmail()): new Label("no hay email registrado");
                            HBox cont_em=new HBox(em,email);
                            HBox.setMargin(em, new Insets(5));
                            HBox.setMargin(email, new Insets(5));
                            VBox infoPer=new VBox(cont_nom,cont_tel,cont_em);
                            
                            return infoPer;
    }
    public ScrollPane ventana_reserva(Stand st, Feria f){
        Button guardar=new Button("Reservar");
        ArrayList<Socials> socials=new ArrayList<>();
        ScrollPane sp=new ScrollPane();
        VBox container=new VBox();
        ComboBox<String> opciones=new ComboBox<>(FXCollections.observableArrayList("Emprendedor","Auspiciante"));
        /*caso donde hay un puesto disponible*/
        if (st.getPersona_responsable().size()==1){
             ArrayList<Persona> personas=st.getPersona_responsable();
                           for (Persona p: personas){
                           /*se muestra info de la persona que ya reservo*/
                            container.getChildren().add(infoPersona(p));
                       
                        }
        }
        /*luego de esto se da la opcion de registrar un emprendedor mas*/
        /*comboBox con opciones de tipo de usuario (Auspiciante o Emprendedor)*/
        container.getChildren().add(opciones);
        opciones.setOnAction(eh->{
            persona_responsable=opciones.getValue();
        });
        
      
        /*private String identificacion;
            private String nombre; 
            private String telefono;
            private String email; 
            private String direccion; 
            private String sitio_web;
            private String nombre_de_responsable;
            private ArrayList<Socials> redes_sociales;*/
        
             Label l_nombre=new Label("Nombre   :");
            TextField nombre_field=new TextField();
            HBox cont_name=new HBox(l_nombre,nombre_field);
            cont_name.setSpacing(5);
            /*identificacio*/
            Label l_ide=new Label("Identificacion   :");
            TextField ide_field=new TextField();
            HBox cont_ide=new HBox(l_ide,ide_field);
            cont_ide.setSpacing(5);
            /*telefono*/
            Label l_tel=new Label("Telefono   :");
            TextField tel_field=new TextField();
            HBox cont_tel=new HBox(l_tel,tel_field);
            cont_tel.setSpacing(5);
            /*email*/
            Label l_em=new Label("Email  :");
            TextField em_field=new TextField();
            HBox cont_em=new HBox(l_em,em_field);
            cont_em.setSpacing(5);
            /*direccion*/
            Label l_dir=new Label("Direccion  :");
            TextField dir_field=new TextField();
            HBox cont_dir=new HBox(l_dir,dir_field);
            cont_dir.setSpacing(5);
            /*Sitio Web*/
            Label l_web=new Label("Sitio web:   ");
            TextField web_field=new TextField();
            HBox cont_web=new HBox(l_web,web_field);
            cont_web.setSpacing(5);
            /*nom responsable*/
            Label l_resp=new Label("Nombre del responsable:");
            TextField resp_field=new TextField();
            HBox cont_resp=new HBox(l_resp,resp_field);
            cont_resp.setSpacing(5);
            /*redes sociales*/
            HBox sociales=new HBox();
            sociales.setPadding(new Insets(5));
            sociales.setSpacing(5);
            ComboBox<String> redes=new ComboBox<>(FXCollections.observableArrayList("Facebook","Instagram","Twitter","Tiktok"));
            TextField username=new TextField();
            username.setPromptText("Ingresa tu username");
            Button registrarRed=new Button("Guardar red social");
            sociales.getChildren().addAll(redes,username,registrarRed);
            /*servicios*/
            HBox serviciosh=new HBox();
            serviciosh.setPadding(new Insets(5));
            serviciosh.setSpacing(5);
            Label ser=new Label("Servicio: ");
            ComboBox<String> servicios=new ComboBox<>(FXCollections.observableArrayList("ALIMENTACIÓN", "EDUCACIÓN", "SALUD", "VESTIMENTA"));
            serviciosh.getChildren().addAll(ser,servicios);
            /*descripcion*/
             Label l_des=new Label("Descripcion de servicio  :");
            TextField des_field=new TextField();
            HBox cont_des=new HBox(l_des,des_field);
            cont_des.setSpacing(5);   
         /*setea valor de servicio ofertado*/
            servicios.setOnAction(eh->{
               servicioOfertado=TipoServicio.valueOf(servicios.getValue());
               System.out.println(servicioOfertado+" servicio ofertada");
            });
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
            
        
        /*boton de registro*/
        /*valores para crear el objeto*/
         /*private String identificacion;
            private String nombre; 
            private String telefono;
            private String email; 
            private String direccion; 
            private String sitio_web;
            private String nombre_de_responsable;
            private ArrayList<Socials> redes_sociales;*/
         
        

        guardar.setOnAction(e->{
            /*Accion de guardar*/
            /*toma los valores de los labels */
            TipoServicio tServ=(servicioOfertado==null)?null:servicioOfertado;
            String nombre=(nombre_field.getText().isEmpty())?null:nombre_field.getText();
            String iden=(ide_field.getText().isEmpty())?null:ide_field.getText();
            String tel=(tel_field.getText().isEmpty())?null:tel_field.getText();
            String email=(em_field.getText().isEmpty())?null:em_field.getText();
            String dir=(dir_field.getText().isEmpty())?null:dir_field.getText();
            String web=(web_field.getText().isEmpty())?null:web_field.getText();
            String resp=(resp_field.getText().isEmpty())?null:resp_field.getText();
            ArrayList<Socials> soc=(socials.isEmpty())?null:socials;
            String des=(des_field.getText().isEmpty())?null:des_field.getText();
           /*valida que selecciones el tipo de persona que reserva*/
            if (persona_responsable==""){
                Alert a=new Alert(Alert.AlertType.ERROR);
                a.setContentText("Deber seleccionar si es Auspiciante o Emprendedor");
                a.showAndWait();
                e.consume();
            }else{
               /*si el que reserva es emprendedor*/
                if (persona_responsable=="Emprendedor"){
                    /*me aseguro que los campos necesarios para emprendedor esten llenos el resto no son relevantes*/
                    if (nombre==null || iden==null || resp==null || des==null){
                        Alert a=new Alert(Alert.AlertType.ERROR);
                        a.setContentText("Llene todos los campos necesarios:"+"\n"+"");
                        a.showAndWait();
                        e.consume();
                    }else{
                        /*si los campos relevantes fueron ingresados crea el objeto emprendedor*/
                        Emprendedor emp=new Emprendedor(iden, nombre, tel, email, dir,resp, socials, des);
                        /*valida*/
                       if(registro_duplicado(st, f, emp)==false && excede_registros(st, f, emp)==false){
                           /*add a la lista de emprendedores de ese stand*/
                           st.getPersona_responsable().add(emp);
                           st.setFechaAsignacion(LocalDate.now());
                           Stage stage=(Stage)sp.getScene().getWindow();
                           stage.close();
                            try {
                                App.setRoot("administracionStans");
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                       }else{
                           /*muestra por que no se pudo realizar el registro*/
                           if (excede_registros(st, f, emp)){
                               Alert a=new Alert(Alert.AlertType.ERROR);
                                a.setContentText("Usted excede el numero de stands permitidos"+"\n"+"");
                                a.showAndWait();
                                e.consume();
                           }
                           if (registro_duplicado(st, f, emp)){
                               Alert a=new Alert(Alert.AlertType.ERROR);
                            a.setContentText("Usted ya esta registrado en este stand"+"\n"+"");
                            a.showAndWait();
                            e.consume();
                           }
                       }
                    }
                    
                }
                if(persona_responsable=="Auspiciante"){
                    System.out.println(servicioOfertado+" servicio ofertada dentro de auspiciante");
                    /*me aseguro que los datos necesarios esten obligatoriamente llenos */
                    Auspiciante auspiciante=new Auspiciante(iden, nombre, tel, email, dir, resp, socials,tServ);
                  
                     if (nombre==null || iden==null || resp==null || tServ==null){
                        Alert a=new Alert(Alert.AlertType.ERROR);
                        a.setContentText("Llene todos los campos necesarios:"+"\n"+"");
                        a.showAndWait();
                        e.consume();
                         
                     }else{
                         
                         
                        if (registro_duplicado(st, f, auspiciante)==false){
                           f.getlAuspiciantes().add(auspiciante);
                           Stage stage=(Stage)sp.getScene().getWindow();
                           stage.close();
                            try {
                                App.setRoot("administracionStans");
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }else{
                           Alert a=new Alert(Alert.AlertType.ERROR);
                            a.setContentText("Usted ya auspicia este stand"+"\n"+"");
                            a.showAndWait();
                            e.consume(); 
                        }
                         
                     }
                }
            }
        });
        container.getChildren().addAll(cont_name,cont_ide,cont_em,cont_resp,cont_web,cont_tel,cont_des,sociales,serviciosh,guardar);
        container.setSpacing(10);
        container.setPadding(new Insets(10));
        sp.setContent(container);
        return sp;
                        
    }
    public boolean excede_registros(Stand st, Feria f, Persona emp){
        boolean excedente=false;
        int contPersona=0;
                        for (Seccion s:f.getSecciones()){
                            for (Stand stnds: s.getStands()){
                                for (Persona persona: stnds.getPersona_responsable()){
                                    if (persona.equals(emp)){
                                        contPersona++;
                                    }
                                }
                            }
                        }
                        if (contPersona>2){
                            excedente=true;
                        }
        return excedente;
    }   
    public boolean registro_duplicado(Stand st, Feria f, Persona emp){
                        boolean yaExiste=false;
                        for (Persona p: st.getPersona_responsable()){
                            if (p.getIdentificacion().equals(emp.getIdentificacion())){
                                yaExiste=true;
                            }
                        }           
        return yaExiste;
    }
}
