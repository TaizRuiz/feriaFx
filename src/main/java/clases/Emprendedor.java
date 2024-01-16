/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import java.util.ArrayList;

/**
 *
 * @author USUARIO
 */
public class Emprendedor extends Persona {
    private int num_reservas=0;
    private String descripcion_servicio;
    //id, nombre, telefono, email, nomResponsable, redes, descripcion
    public Emprendedor(String identificacion, String nombre, String telefono, String email, String nombre_de_responsable, ArrayList<Socials> redes_sociales, String des_service){
        super(identificacion, nombre, telefono, email, nombre_de_responsable, redes_sociales);
        this.descripcion_servicio=des_service;
    }
    
     public Emprendedor(String identificacion, String nombre, String telefono, String email, String direccion, String nombre_de_responsable, ArrayList<Socials> redes_sociales, String servicio) {
      super(identificacion, nombre, telefono, email, direccion, nombre_de_responsable, redes_sociales);
      this.descripcion_servicio=servicio;
    }
     //redes, id, nombre, telefono, email, sitioWeb, nomResponsable, descripcion
    public Emprendedor( ArrayList<Socials> socials,String id, String nom, String tel, String email, String sitio, String nomRespon, String servicio){
       super(socials, id, nom, tel, email, sitio, nomRespon);
       this.descripcion_servicio=servicio;
    }
    public Emprendedor(String identificacion, String nombre,String telefono, String email, String direccion, String sitio_web, String nombre_responsable, ArrayList<Socials> redes, String servicio){
        super(identificacion, nombre, telefono, email, direccion, sitio_web, nombre_responsable, redes);
        this.descripcion_servicio=servicio;
    }
    public Emprendedor(String identificacion, String nombre,String telefono, String email, String direccion, String sitio_web, String nombre_responsable, ArrayList<Socials> redes){
        super(identificacion, nombre, telefono, email, direccion, sitio_web, nombre_responsable, redes);
    }

    public String getDescripcion_servicio() {
        return descripcion_servicio;
    }

    public void setDescripcion_servicio(String descripcion_servicio) {
        this.descripcion_servicio = descripcion_servicio;
    }

    @Override
    public String toString() {
        String s=super.toString();
        return   "Emprendedor{"+s + "descripcion_servicio=" + descripcion_servicio + '}';
    }

    public int getNum_reservas() {
        return num_reservas;
    }

    public void setNum_reservas(int num_reservas) {
        this.num_reservas = num_reservas;
    }

    
    
    
}
