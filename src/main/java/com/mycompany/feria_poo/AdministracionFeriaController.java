/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.feria_poo;

import clases.Feria;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

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
    @FXML
    private ListView<?> listaferia;
    private TableView<Feria> listaFeria=new TableView();
    private AnchorPane mainContainer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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

    @FXML
    private void listaFeria(MouseEvent event) {
    }
    
    public void cargarFerias(){
        ObservableList<Feria> ferias= FXCollections.observableArrayList();
        ArrayList<Feria> arFer=App.ferias;
        for (Feria f: arFer){
            System.out.println(f.toString());
            listaFeria.getItems().add(f);
        }
        
        mainContainer.getChildren().add(listaFeria);
    }
    
}
