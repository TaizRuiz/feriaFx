package com.mycompany.feria_poo;

import clases.Auspiciante;
import clases.Emprendedor;
import clases.Feria;
import clases.Persona;
import clases.Seccion;
import clases.Socials;
import enums.TipoServicio;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
        
        ferias=deser_ferias();
        emprendedores=deser_emprendedor();
        auspiciantes=deser_auspi();
        
        /*ferias = new ArrayList<>();
        emprendedores = new ArrayList<>();
        auspiciantes = new ArrayList<>();*/
        BackgroundFill backgroundFill = new BackgroundFill(Color.SKYBLUE, null, null);
        Background background = new Background(backgroundFill);

        // Aplicar el fondo a la escena
        scene.setFill(Color.SKYBLUE);  // O puedes usar setBackground(background) en lugar de setFill

        stage.setScene(scene);
        stage.show();
        
        stage.setOnCloseRequest(eh->{
            guardar_datos();
        });
       //cargarFeria();
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
        Emprendedor e1=new Emprendedor("0914463815","Tuty", "0981383239","reyes@live.com","Gabriel",redes,"Emprendimiento");
        ArrayList<Persona> p1=new ArrayList<>();
        p1.add(e1);
        feria2.getSecciones()[0].getStands()[0].setPersona_responsable(p1);
        feria2.getSecciones()[0].getStands()[0].setFechaAsignacion(LocalDate.now());
        emprendedores.add((Emprendedor)e1);
        
        Emprendedor e2=new Emprendedor("0914463815","Tut", "0981383239","escala@live.com","Jaime",redes,"Emprendimiento");
        ArrayList<Persona> p2=new ArrayList<>();
        feria2.getSecciones()[3].getStands()[0].setPersona_responsable(p2);
        feria2.getSecciones()[3].getStands()[0].setFechaAsignacion(LocalDate.now());
        emprendedores.add((Emprendedor)e2);
        p2.add(e2);
                
        
        
        Emprendedor e3=new Emprendedor("09506375557","Tia", "0968933048","toledo@espol.edu.ec","Newton",redes,"Emprendimiento");
        ArrayList<Persona> p3=new ArrayList<>();
        p3.add(e3);
        feria1.getSecciones()[1].getStands()[1].setPersona_responsable(p3);
        feria1.getSecciones()[1].getStands()[1].setFechaAsignacion(LocalDate.now());
        emprendedores.add((Emprendedor)e3);
        
        Emprendedor e4=new Emprendedor("0978787820","Espol", "09787878","Sancocho@espol.edu.ec","Gabriel",redes,"Emprendimiento");
         ArrayList<Persona> p4=new ArrayList<>();
        p4.add(e4);
        feria1.getSecciones()[2].getStands()[1].setPersona_responsable(p4);
        feria1.getSecciones()[2].getStands()[1].setFechaAsignacion(LocalDate.now());
        emprendedores.add((Emprendedor)e4);
        
       
        
        //String identificacion, String nombre, String telefono, String email, String nombre_de_responsable, ArrayList<Socials> redes_sociales, String sector_cubierto
        Auspiciante auspiciante = new Auspiciante("09121212", "ESPOL", "1234567891", "jaimeesa-2002@hotmail.com", "Jaime", redes, TipoServicio.ALIMENTACIÓN);
        auspiciante.setIncluye_stand(true);
        feria1.getlAuspiciantes().add(auspiciante);
        auspiciantes.add(auspiciante);
        
         Auspiciante auspiciante2 = new Auspiciante("09787878", "FRESH", "19872612", "jaime@hotmail.com", "Escala", redes, TipoServicio.ALIMENTACIÓN);
        auspiciante2.setIncluye_stand(true);
        feria2.getlAuspiciantes().add(auspiciante2);
        auspiciantes.add(auspiciante2);
        
    }
    
    public void guardar_datos(){
        try {
      System.out.println("SEREALIZADO ferias");
      ObjectOutputStream serializado = new ObjectOutputStream(
      new FileOutputStream("listaFerias.ser"));
      serializado.writeObject(ferias);
      serializado.close();
      
            System.out.println("serializado emprendedores");
      ObjectOutputStream serializado_emprendedores = new ObjectOutputStream(
      new FileOutputStream("listaEmprendedores.ser"));
      serializado_emprendedores.writeObject(emprendedores);
      serializado_emprendedores.close();
      
            System.out.println("serializado auspiciantes");
      ObjectOutputStream serializado_auspi = new ObjectOutputStream(
      new FileOutputStream("listaAuspiciantes.ser"));
      serializado_auspi.writeObject(auspiciantes);
      serializado_auspi.close();

    } catch (Exception e) {
      System.out.println(e);
    }
    }
    
    public ArrayList<Feria> deser_ferias(){
        ArrayList<Feria> s=new ArrayList<>();
        try {
      System.out.println("DESERIALIZADO");
      ObjectInputStream deserializado = new ObjectInputStream(
          new FileInputStream("listaFerias.ser"));
         s = (ArrayList<Feria>) deserializado.readObject();

      System.out.println(s);

    } catch (Exception e) {
      System.out.println(e);
    } 
        return s;
    }
    
    public ArrayList<Emprendedor> deser_emprendedor(){
        ArrayList<Emprendedor> s=new ArrayList<>();
        try {
      System.out.println("DESERIALIZADO");
      ObjectInputStream deserializado = new ObjectInputStream(
          new FileInputStream("listaEmprendedores.ser"));
         s = (ArrayList<Emprendedor>) deserializado.readObject();

      System.out.println(s);

    } catch (Exception e) {
      System.out.println(e);
    } 
        return s;
    }
    public ArrayList<Auspiciante> deser_auspi(){
        ArrayList<Auspiciante> s=new ArrayList<>();
        try {
      System.out.println("DESERIALIZADO");
      ObjectInputStream deserializado = new ObjectInputStream(
          new FileInputStream("listaAuspiciantes.ser"));
         s = (ArrayList<Auspiciante>) deserializado.readObject();

      System.out.println(s);

    } catch (Exception e) {
      System.out.println(e);
    } 
        return s;
    }
}