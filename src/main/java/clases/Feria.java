    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author USUARIO
 */
public class Feria {
    private static int cont_ferias=1;
    private int codigo;
    private String nombre;
    private LocalDate fInicio;
    private LocalDate fFin;
    private String descripcion;
    private String lugar;
    private String horario;
    private ArrayList<Auspiciante> lAuspiciantes;
    private Seccion[] secciones=new Seccion[4];

    public Feria(String nombre, LocalDate fInicio, LocalDate fFin, String lugar, String horario, String descripcion) {
        
        this.codigo=(cont_ferias++);
        this.nombre = nombre;
        this.fInicio = fInicio;
        this.fFin = fFin;
        this.lugar = lugar;
        this.horario = horario;
        this.descripcion=descripcion;
        this.lAuspiciantes = new ArrayList<>();
    }

    

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getfInicio() {
        return fInicio;
    }

    public void setfInicio(LocalDate fInicio) {
        this.fInicio = fInicio;
    }

    public LocalDate getfFin() {
        return fFin;
    }

    public void setfFin(LocalDate fFin) {
        this.fFin = fFin;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public ArrayList<Auspiciante> getlAuspiciantes() {
        return lAuspiciantes;
    }

    public void setlAuspiciantes(ArrayList<Auspiciante> lAuspiciantes) {
        this.lAuspiciantes = lAuspiciantes;
    }

    public Seccion[] getSecciones() {
        return secciones;
    }

    public void setSecciones(Seccion[] secciones) {
        this.secciones = secciones;
    }

    @Override
    public String toString() {
        return "Feria{" + "codigo=" + codigo + ", nombre=" + nombre + ", fInicio=" + fInicio + ", lugar=" + lugar + ", numero de auspiciantes=" + lAuspiciantes.size() + '}';
    }
    
   
}
