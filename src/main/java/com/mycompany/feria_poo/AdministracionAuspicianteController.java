/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.feria_poo;

import clases.Auspiciante;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
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
        colCedula.setCellValueFactory(new PropertyValueFactory<>("Cedula"));
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
