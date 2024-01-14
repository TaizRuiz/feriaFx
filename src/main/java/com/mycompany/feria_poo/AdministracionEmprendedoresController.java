/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.feria_poo;

import clases.Emprendedor;
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
    }

    @FXML
    private void editarEmpre(MouseEvent event) {
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
