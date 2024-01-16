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
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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

    @FXML
    private void verStands(ActionEvent event) {
        if (this.feria_seleccionada!=null){
            panelStands(feria_seleccionada);
           
        }else{
            Alert a=new Alert(Alert.AlertType.ERROR);
            a.setContentText("Selecciona una feria");
            a.showAndWait();
            event.consume();
        }
    }
    
    private void panelStands(Feria f){
        Stage st=new Stage();
        BorderPane scena=new BorderPane();
        scena.setPrefSize(500, 350);
        scena.setPadding(new Insets(20));
        Scene escena=new Scene(scena);
        VBox cont_secciones=new VBox();
        for (Seccion sec:f.getSecciones()){
            for (Stand stand: sec.getStands()){
               Label descripcion=(stand.getPersona_responsable()!=null)?new Label("[*"+stand.getCodigo()+"]"):new Label("["+stand.getCodigo()+"]");
               cont_secciones.getChildren().add(descripcion);
               VBox.setMargin(descripcion, new Insets(15));
               if (stand.getPersona_responsable()!=null){
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
                        Persona p=(stand.getPersona_responsable() instanceof Emprendedor)? (Emprendedor) stand.getPersona_responsable():(Auspiciante) stand.getPersona_responsable();
                        Label nom=new Label("Persona o Institucion: ");
                        Label nombre=new Label(p.getNombre());
                        HBox cont_nom=new HBox(nom,nombre);
                        HBox.setMargin(nom, new Insets(5));
                        HBox.setMargin(nombre, new Insets(5));
                        Label tel=new Label("Telefono: ");
                        Label telefono=(p.getTelefono()!=null)?new Label(p.getTelefono()): new Label("no hay numero registrado");
                        HBox cont_tel=new HBox(tel,telefono);
                        HBox.setMargin(tel, new Insets(5));
                        HBox.setMargin(telefono, new Insets(5));
                        Label em=new Label("Email: ");
                        Label email=(p.getEmail()!=null)?new Label(p.getEmail()): new Label("no hay email registrado");
                        HBox cont_em=new HBox(em,email);
                        HBox.setMargin(em, new Insets(5));
                        HBox.setMargin(email, new Insets(5));
                        info_stand.getChildren().addAll(reserva,cont_cod,cont_fecha,cont_nom,cont_em,cont_tel);
                        scena.setRight(info_stand);
                    });
               }else{
                   descripcion.setOnMouseClicked(b->{
                       
                        Label l=new Label("no reservado");
                        scena.setRight(l);
                    });
               }
            }
        }
        
        scena.setCenter(cont_secciones);
        st.initModality(Modality.APPLICATION_MODAL);
        st.setScene(escena);
        st.showAndWait();
        
    }
    
}
