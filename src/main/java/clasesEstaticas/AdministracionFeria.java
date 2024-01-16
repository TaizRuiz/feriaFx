/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clasesEstaticas;

import clases.Emprendedor;
import clases.Feria;
import clases.Seccion;
import clases.Stand;
import clases.Persona;
import com.mycompany.feria_poo.App;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author USUARIO
 */
public class AdministracionFeria{
//      
//    //ArrayList<Socials> redesSocial = new ArrayList<>(); 
//    
//    public static void info_ferias(){
//        for (Feria f: App.ferias){
//            System.out.println(f.toString());
//        }
//    }
//    public static void ver_info_feria(int codigo){
//        for (Feria f: App.ferias){
//            if(f.getCodigo()==codigo){
//                System.out.println(f.toString()+" lista auspiciantes: "+ f.getlAuspiciantes()+" fecha fin: "+f.getfFin()+"horario: "+f.getHorario()+"descripcion: "+f.getDescripcion());
//                int cont=0;
//                for (Seccion s:f.getSecciones()){
//                    cont++;
//                    int numStands=s.getNum_stands();
//                    System.out.println(" Stands en seccion "+cont+": "+numStands );
//                }
//            }
//        }
//    }
//    
    public static void registrarFeria(String nombre, String lugar, String descripcion, LocalDate fInicio, LocalDate fFin, String horario, int sec1, int sec2, int sec3, int sec4){
        
        Feria new_feria=new Feria(nombre, fInicio, fFin, lugar, horario, descripcion);
        new_feria.getSecciones()[0]=new Seccion(sec1);
        new_feria.getSecciones()[1]=new Seccion(sec2);
        new_feria.getSecciones()[2]=new Seccion(sec3);
        new_feria.getSecciones()[3]=new Seccion(sec4); 
        App.ferias.add(new_feria);
    }
//    
//    public static void modificarFeria(Feria f){
//        System.out.println("Que campo desea modificar:");
//               
//        boolean salir = false;
//        int opcion;
//        Scanner sc=new Scanner(System.in);
//        System.out.println("Ingresa una opcion:");
//        opcion=sc.nextInt();
//        
//        Scanner sc1 = new Scanner(System.in);
//        while(!salir){
//                
//                
//            switch (opcion) {
//                
//                case 1:
//                    System.out.println("Ingresa el valor de nombre:");
//                    String nombre=sc1.nextLine();
//                    f.setNombre(nombre);
//                    salir = true;
//                    break;
//                case 2:
//                    System.out.println("Ingresa el valor de descripcion:");
//                    String descripcion=sc1.nextLine();
//                    f.setDescripcion(descripcion);
//                    salir = true;
//                    break;
//                case 3:
//                    System.out.println("Ingresa el valor de lugar:");
//                    String lugar=sc1.nextLine();
//                    f.setLugar(lugar);
//                    salir = true;
//                    break;
//                case 4:
//                    System.out.println("Ingresa el valor de la fecha de inicio aa/mm/dd :");
//                    LocalDate ld_current=f.getfInicio();
//                    String fInicio=sc1.nextLine();
//                    String[] partesFecha=fInicio.split("-");
//                    Integer aa=Integer.parseInt(partesFecha[0]);
//                    Integer mm=Integer.parseInt(partesFecha[1]);
//                    Integer dd=Integer.parseInt(partesFecha[2]);
//                    LocalDate fNueva=LocalDate.of(aa,mm, dd);
//                    f.setfInicio(fNueva);
//                    salir = true;
//                    break;
//                case 5:
//                    System.out.println("Ingresa el valor de la fecha de inicio aa/mm/dd :");
//                    LocalDate ld_currentFin=f.getfFin();
//                    String fFin=sc1.nextLine();
//                    String[] partesFechaFin=fFin.split("-");
//                    Integer aaF=Integer.parseInt(partesFechaFin[0]);
//                    Integer mmF=Integer.parseInt(partesFechaFin[1]);
//                    Integer ddF=Integer.parseInt(partesFechaFin[2]);
//                    LocalDate fNuevaF=LocalDate.of(aaF,mmF, ddF);
//                    f.setfFin(fNuevaF);
//                    salir = true;
//                    break;
//                case 6:
//                    System.out.println("Ingresa el valor de horario:");
//                    String horario=sc1.nextLine();
//                    f.setHorario(horario);
//                    salir = true;
//                    break;
//                case 7:
//
//                    System.out.println("Saliendo del menu...");
//                    salir = true;
//
//                    break;
//                default:
//                    System.out.println("Opcion ingresada no valida");
//                    break;
//              
//            }
//        }
//
//    }
//    public static void info_emprendedores(Feria f){
//        int cont=0;
//        for (Seccion s: f.getSecciones()){
//         cont++;
//            
//            for (Stand st: s.getStands()){
//                if (st.getPersona_responsable()!=null){
//                    System.out.println("Seccion" +"["+cont+"]"+ "stand "+"["+st.getCodigo()+"] :");
//                    Emprendedor e=(Emprendedor)st.getPersona_responsable();
//                    String nombre_emprendimiento=e.getNombre();
//                    String descripcion=e.getDescripcion_servicio();
//                    System.out.println("Nombre de emprendimiento:"+nombre_emprendimiento);
//                    System.out.println("Descripcion de emprendimiento: "+descripcion);
//                    
//                } 
//               
//            }
//        }
//    }
//    
//  
//    
//   
//    
//    
// 
//    
}