/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import enums.TipoServicio;
import java.util.ArrayList;

/**
 *
 * @author USUARIO
 */
public class Auspiciante extends Persona {
    public TipoServicio sector_cubierto;
    public boolean incluye_stand=false;
    public Auspiciante(String identificacion, String nombre, String telefono, String email, String nombre_de_responsable, ArrayList<Socials> redes_sociales, TipoServicio sector_cubierto){
        super(identificacion, nombre, telefono, email, nombre_de_responsable, redes_sociales);
        this.sector_cubierto=sector_cubierto;
    }
     public Auspiciante(String identificacion, String nombre, String telefono, String email, String direccion, String nombre_de_responsable, ArrayList<Socials> redes_sociales, TipoServicio sector_cubierto) {
      super(identificacion, nombre, telefono, email, direccion, nombre_de_responsable, redes_sociales);
      this.sector_cubierto=sector_cubierto;
    }
    public Auspiciante( ArrayList<Socials> socials,String id, String nom, String tel, String email, String sitio, String nomRespon, TipoServicio sector_cubierto){
       super(socials, id, nom, tel, email, sitio, nomRespon);
       this.sector_cubierto=sector_cubierto;
    }
    public Auspiciante(String identificacion, String nombre,String telefono, String email, String direccion, String sitio_web, String nombre_responsable, ArrayList<Socials> redes, TipoServicio sector_cubierto){
        super(identificacion, nombre, telefono, email, direccion, sitio_web, nombre_responsable, redes);
        this.sector_cubierto=sector_cubierto;
    }

    public TipoServicio getSector_cubierto() {
        return sector_cubierto;
    }

    public void setSector_cubierto(TipoServicio sector_cubierto) {
        this.sector_cubierto = sector_cubierto;
    }

    public boolean isIncluye_stand() {
        return incluye_stand;
    }

    public void setIncluye_stand(boolean incluye_stand) {
        this.incluye_stand = incluye_stand;
    }
    
    
}
