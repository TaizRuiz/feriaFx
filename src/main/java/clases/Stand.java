/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author USUARIO
 */
public class Stand  implements Serializable{

    private static int num_codigo=1;
    private int codigo;
    private LocalDate fechaAsignacion;
    private ArrayList<Persona> persona_responsable;
    public Stand (){
        this.codigo=(num_codigo++);
        this.persona_responsable=new ArrayList();
        
    }

    public static int getNum_stand() {
        return num_codigo;
    }

    public static void setNum_stand(int num_stand) {
        Stand.num_codigo = num_stand;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public LocalDate getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(LocalDate fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    public ArrayList<Persona> getPersona_responsable() {
        return persona_responsable;
    }

    public void setPersona_responsable(ArrayList<Persona> persona_responsable) {
        this.persona_responsable = persona_responsable;
    }
    
    public String toString(){
        return "Stand{codigo: "+this.codigo+" fecha_asignacion: "+this.fechaAsignacion+" infopersona_responsable: "+this.persona_responsable.toString();
    }

    
    
}
