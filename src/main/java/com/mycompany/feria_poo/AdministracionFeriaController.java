/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.feria_poo;

import clases.Feria;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

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
    }

    @FXML
    private void nuevaFeria(MouseEvent event) {
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
        TableColumn<Feria, String> colNombre = new TableColumn<>("Nombre");
        TableColumn<Feria, LocalDate> colFechaInicio = new TableColumn<>("Fecha de Inicio");
        TableColumn<Feria, String> colLugar = new TableColumn<>("Lugar");
        TableColumn<Feria, Integer> colCantAusp = new TableColumn<>("Cantidad de Auspiciantes");
        TableColumn<Feria, Integer> colCantEmpr = new TableColumn<>("Cantidad de Emprendedores");
        listaFeria.getColumns().addAll(colCodigo,colNombre,colFechaInicio,colLugar,colCantAusp,colCantEmpr);
        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colFechaInicio.setCellValueFactory(new PropertyValueFactory<>("fInicio"));
        colLugar.setCellValueFactory(new PropertyValueFactory<>("lugar"));
        colCantAusp.setCellValueFactory(new PropertyValueFactory<>("cantAusp"));
        colCantEmpr.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        
        ArrayList<Feria> arFer=App.ferias;
        for (Feria f: arFer){
            System.out.println(f.toString());
            listaFeria.getItems().add(f);
        }
        
        contenedorPrincipal.setCenter(listaFeria);
        BorderPane.setMargin(listaFeria, new Insets(25));
    }
    
}
