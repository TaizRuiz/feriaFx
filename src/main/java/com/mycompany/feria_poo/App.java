package com.mycompany.feria_poo;

import clases.Auspiciante;
import clases.Emprendedor;
import clases.Feria;
import clases.Seccion;
import clases.Socials;
import enums.TipoServicio;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;

/**
 * JavaFX App
 */
public class App extends Application {
    public static ArrayList<Feria> ferias;
    public static ArrayList<Emprendedor> emprendedores;
    public static ArrayList<Auspiciante> auspiciantes;
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"),800,500);
        
        ferias=new ArrayList<>();
        emprendedores=new ArrayList<>();
        auspiciantes=new ArrayList<>();
        cargarFeria();
        BackgroundFill backgroundFill = new BackgroundFill(Color.SKYBLUE, null, null);
        Background background = new Background(backgroundFill);

        // Aplicar el fondo a la escena
        scene.setFill(Color.SKYBLUE);  // O puedes usar setBackground(background) en lugar de setFill

        stage.setScene(scene);
        stage.show();
        
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
    public static void cargarFeria(){
        //ArrayList<Feria> listaFeria =  new ArrayList<>();
        Feria feria1 = new Feria("Feria agricola", LocalDate.now(), LocalDate.now(), "Guayaquil", "14:00 p.m", "Feria dedicada a la agricultura");
        ferias.add(feria1);
        feria1.getSecciones()[0] = new Seccion(2);
        feria1.getSecciones()[1] = new Seccion(2);
        feria1.getSecciones()[2] = new Seccion(2);
        feria1.getSecciones()[3] = new Seccion(2);
         
        Feria feria2 = new Feria("Feria Gastronomica", LocalDate.now(), LocalDate.now(), "Quito", "10:00 a.m", "Feria dedicada a la Gatronomia");
        ferias.add(feria2);
        feria2.getSecciones()[0] = new Seccion(1);
        feria2.getSecciones()[1] = new Seccion(2);
        feria2.getSecciones()[2] = new Seccion(3);
        feria2.getSecciones()[3] = new Seccion(4);
        ArrayList<Socials> redes=new ArrayList<>();
        redes.add(new Socials("Facebook","user"));
        Emprendedor e1=new Emprendedor("0914463815","Tuty", "0981383239","dtruiz@live.com","Dafne",redes,"Emprendimiento");
        feria2.getSecciones()[0].getStands()[0].setPersona_responsable(e1);
        feria2.getSecciones()[0].getStands()[0].setFechaAsignacion(LocalDate.now());
        emprendedores.add(e1);
        
        Emprendedor e2=new Emprendedor("09506375557","Tia", "0968933048","kerimaga@espol.edu.ec","Kevin",redes,"Emprendimiento");
        feria1.getSecciones()[1].getStands()[1].setPersona_responsable(e2);
        feria1.getSecciones()[1].getStands()[1].setFechaAsignacion(LocalDate.now());
        emprendedores.add(e2);
       
        Emprendedor e3=new Emprendedor("0978787820","Espol", "09787878","kerimaga@espol.edu.ec","Ricardo",redes,"Emprendimiento");
        feria1.getSecciones()[2].getStands()[1].setPersona_responsable(e3);
        feria1.getSecciones()[2].getStands()[1].setFechaAsignacion(LocalDate.now());
        emprendedores.add(e3);
        
        Emprendedor e4=new Emprendedor("0993939393","Comisariato", "0978989632","kerimaga@espol.edu.ec","Thayz",redes,"Emprendimiento");
        feria1.getSecciones()[3].getStands()[0].setPersona_responsable(e4);
        feria1.getSecciones()[3].getStands()[0].setFechaAsignacion(LocalDate.now());
        emprendedores.add(e4);
        
        //String identificacion, String nombre, String telefono, String email, String nombre_de_responsable, ArrayList<Socials> redes_sociales, String sector_cubierto
        Auspiciante auspiciante = new Auspiciante("09121212", "ESPOL", "1234567891", "kevinricki-2002@hotmail.com", "Kevin", redes, TipoServicio.ALIMENTACIÓN);
        auspiciante.setIncluye_stand(true);
        feria1.getlAuspiciantes().add(auspiciante);
        auspiciantes.add(auspiciante);
        
    }
    /*
    public void cargarFerias(){
        ObservableList<Feria> ferias= FXCollections.observableArrayList();
        ArrayList<Feria> arFer=App.ferias;
        for (Feria f: arFer){
            System.out.println(f.toString());
            listaFerias.getItems().add(f);
        }
        
        mainContainer.getChildren().add(listaFerias);
    }
    */

}