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
public class Persona {
    private String identificacion;
    private String nombre; 
    private String telefono;
    private String email; 
    private String direccion; 
    private String sitio_web;
    private String nombre_de_responsable;
    private ArrayList<Socials> redes_sociales;

    public Persona(String identificacion, String nombre, String telefono, String email, String nombre_de_responsable, ArrayList<Socials> redes_sociales) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.nombre_de_responsable = nombre_de_responsable;
        this.redes_sociales = redes_sociales;
    }

    public Persona(String identificacion, String nombre, String telefono, String email, String direccion, String nombre_de_responsable, ArrayList<Socials> redes_sociales) {
       this(identificacion, nombre, telefono, email, nombre_de_responsable, redes_sociales);
       this.direccion = direccion;
    }
    public Persona( ArrayList<Socials> socials,String id, String nom, String tel, String email, String sitio, String nomRespon){
        this(id, nom, tel, email, nomRespon,socials);
        this.sitio_web=sitio;
    }
    public Persona(String identificacion, String nombre,String telefono, String email, String direccion, String sitio_web, String nombre_responsable, ArrayList<Socials> redes){
        this(redes, email, nombre, email, email, sitio_web, nombre);
        this.sitio_web=sitio_web;
        this.direccion=direccion;
        
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getSitio_web() {
        return sitio_web;
    }

    public void setSitio_web(String sitio_web) {
        this.sitio_web = sitio_web;
    }

    public String getNombre_de_responsable() {
        return nombre_de_responsable;
    }

    public void setNombre_de_responsable(String nombre_de_responsable) {
        this.nombre_de_responsable = nombre_de_responsable;
    }

    public ArrayList<Socials> getRedes_sociales() {
        return redes_sociales;
    }

    public void setRedes_sociales(ArrayList<Socials> redes_sociales) {
        this.redes_sociales = redes_sociales;
    }

    @Override
    public String toString() {
        return "Persona{" + "identificacion=" + identificacion + ", nombre=" + nombre + ", telefono=" + telefono + ", email=" + email + ", direccion=" + direccion + ", sitio_web=" + sitio_web + ", nombre_de_responsable=" + nombre_de_responsable + ", redes_sociales=" + redes_sociales + '}';
    }

    
    
    
    
    
    
        
    
    
    
    
}
