/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clasesEstaticas;

import clases.Auspiciante;
import clases.Feria;
import clases.Socials;
import com.mycompany.feria_poo.App;

import enums.TipoServicio;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author UserPC
 */
public class AdministracionAuspiciantes {
    
    
    public static void info_Auspiciante(){
        for(Feria feria: App.ferias){
            ArrayList<Auspiciante> listaAus = feria.getlAuspiciantes();
            if(!listaAus.isEmpty()){
                for(Auspiciante auspi: listaAus){
                    System.out.println(auspi.getNombre_de_responsable());
                }
            }
            
        }
    }
    
    public static void registrar_auspiciante(String id, String nombre, String nombre_responsable, String telefono, String email, String direccion, String sitio_web, TipoServicio servicio,  ArrayList<Socials> redes_sociales){
        if((direccion == null || direccion == "") && (sitio_web == null || sitio_web == "")){
            Auspiciante new_Auspiciante = new Auspiciante(id, nombre, telefono, email, nombre_responsable, redes_sociales, servicio);
            //new_Auspiciante.setIncluye_stand(incluye);
            App.auspiciantes.add(new_Auspiciante);
        } else if(direccion == null || direccion == "" ){
            Auspiciante new_Auspiciante = new Auspiciante(id, nombre, telefono, email, direccion, nombre_responsable, redes_sociales, servicio);
            //new_Auspiciante.setIncluye_stand(incluye);
            App.auspiciantes.add(new_Auspiciante);
        } else if(sitio_web == null || sitio_web == ""){
            Auspiciante new_Auspiciante = new Auspiciante(redes_sociales, id, nombre, telefono, email, sitio_web, nombre_responsable, servicio);
            //new_Auspiciante.setIncluye_stand(incluye);
            App.auspiciantes.add(new_Auspiciante);
        }
        
        
    
    }
    
    public static void modificarAuspiciante(Auspiciante A){
        

    }
    
    public static void asignarFeria(int codigo, String id){
        Scanner sc = new Scanner(System.in);
        for (Feria f: App.ferias){
            if(f.getCodigo() == codigo){
                for (Auspiciante A: App.auspiciantes){
                    if(A.getIdentificacion() == id){
                        System.out.println("Descripcion de lo que cubre el auspicio: ");
                        String descripcion = sc.nextLine();
                        System.out.println("Incluye stand en la feria: (Si/No)");
                        String boo = sc.nextLine();
                                
                        boolean incluye_stands = false;
                                
                        if(boo == "Si"){
                            incluye_stands = true;
                        }
                        
                        A.setIncluye_stand(incluye_stands);
                        f.getlAuspiciantes().add(A);
                    }
                } 
            }
        } 

        
        // No olvides cerrar el Scanner al final
        sc.close();
    }
    
}
