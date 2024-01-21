/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import java.io.Serializable;

/**
 *
 * @author USUARIO
 */
public class Socials implements Serializable{
    private String nombre_de_red_social;
    private String username;

    public Socials(String nombre_de_red_social, String username) {
        this.nombre_de_red_social = nombre_de_red_social;
        this.username = username;
    }

    public String getNombre_de_red_social() {
        return nombre_de_red_social;
    }

    public void setNombre_de_red_social(String nombre_de_red_social) {
        this.nombre_de_red_social = nombre_de_red_social;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Socials{" + "nombre_de_red_social=" + nombre_de_red_social + ", username=" + username + '}';
    }
    
    
}
