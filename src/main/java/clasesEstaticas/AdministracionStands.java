///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package clasesEstaticas;
//import clases.Emprendedor;
//import clases.Feria;
//import clases.Auspiciante;
//import clases.Seccion;
//import clases.Stand;
//import clases.Persona;
//import clases.Socials;
//import com.mycompany.feria_poo.App;
//import java.time.LocalDate;
//import java.time.Month;
//import java.util.ArrayList;
//import java.util.Scanner;
///**
// *
// * @author Usuario
// */
//public class AdministracionStands {
//   
//    public static void distribucionStands(int cod){
//        for(Feria f: App.ferias){
//            if (f.getCodigo()==cod){
//                Seccion[] secciones = f.getSecciones();
//                for (Seccion s: secciones){
//                    Stand[] arr = s.getStands();
//                    for (Stand stand: arr){
//                        if (stand.getPersona_responsable()!=null){
//                            System.out.println("["+stand.getCodigo()+"*]");
//                        }
//                        else{
//                            System.out.println("["+stand.getCodigo()+"]");
//                        }
//                        
//                    }
//                }
//            }
//        }
//    }
//    
//    public static void reservarStand(int codStand, int codFeria){
//        for(Feria f: App.ferias){
//            if (f.getCodigo()==codFeria){
//                Seccion[] secciones = f.getSecciones();
//                for (Seccion s: secciones){
//                    Stand[] arr = s.getStands();
//                    for (Stand stand: arr){
//                        if(stand.getCodigo()==codStand){
//                            if (stand.getPersona_responsable().equals(null)){
//                                Scanner sc2 = new Scanner(System.in);
//                                System.out.println("Para auspiciante ingrese 1, para emprendedor ingrese 2: ");
//                                int op = sc2.nextInt();
//                                if (op==1){
//                                    System.out.println("Ingrese el codigo del auspiciante: ");
//                                    String id = sc2.nextLine();
//                                    ArrayList<Auspiciante>lista1 = f.getlAuspiciantes();
//                                    for(Auspiciante a: lista1){
//                                        if(id.equals(a.getIdentificacion())){
//                                            stand.setPersona_responsable(a);
//                                        }
//                                        else{
//                                            System.out.println("Registro no permitido");
//                                        }
//                                    }
//                                }
//                                else if (op==2){
//                                    System.out.println("Ingrese el codigo del emprendedor: ");
//                                    String id2 = sc2.nextLine();
//                                    ArrayList<Emprendedor>lista2 = App.emprendedores;
//                                    int contador=0;
//                                    for (Emprendedor e:lista2){
//                                        if((id2.equals(e.getIdentificacion()))&&(contador<=2)){
//                                            stand.setPersona_responsable(e);
//                                        }
//                                        else{
//                                            System.out.println("Registro no permitido");
//                                        }
//                                    }
//                                }
//                                else{
//                                    System.out.println("Opcion invalida");
//                                }
//                                
//                            }
//                            else{
//                                System.out.println("Registro no permitido, stand ocupado");
//                            }
//                        }
//                        else{
//                            System.out.println("Codigo de stand invalido");
//                        }
//                    }
//                }
//            }
//            else{
//                System.out.println("Codigo de feria invalido");
//            }
//        }
//    }
//    
//    public static void mostrarInfoStand(int codStand, int codFeria){
//        for(Feria f: App.ferias){
//            if (f.getCodigo()==codFeria){
//                Seccion[] secciones = f.getSecciones();
//                for (Seccion s: secciones){
//                    Stand[] arr = s.getStands();
//                    for (Stand stand: arr){
//                        if(stand.getCodigo()==codStand){
//                            System.out.println(stand.toString());
//                        }
//                        else{
//                        System.out.println("Codigo de stand invalido");
//                        }
//                    }
//                }
//            }
//            else{
//                System.out.println("Codigo de feria invalido");
//            }
//        }
//    }
//}
