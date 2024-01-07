package com.mycompany.feria_poo;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class PrimaryController {

    @FXML
    private ImageView iFeria;
    @FXML
    private ImageView iEmpre;
    @FXML
    private ImageView iAuspi;
    @FXML
    private ImageView iStans;

    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @FXML
    private void administrarFeria(MouseEvent event) {
        try {
            App.setRoot("administracionFeria");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void administrarEmprendedores(MouseEvent event) {
        try {
            App.setRoot("administracionEmprendedores");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void administradorAuspiciantes(MouseEvent event) {
        try {
            App.setRoot("administracionAuspiciante");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void administradorStans(MouseEvent event) {
        try {
            App.setRoot("administracionStans");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
