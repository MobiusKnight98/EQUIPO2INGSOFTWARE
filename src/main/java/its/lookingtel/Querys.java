/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package its.lookingtel;



/**
 *
 * @author Guest Mode
 */
public abstract class Querys {
    
    // Gestionar Usuario
    
    abstract boolean IniciarSesion(String correo_electronico, String contraseña);
    
  
    abstract String [] Recuperar_Contraseña_Usuario(String correo_electronico);
    
  
     /*
    
     void Registrarse_Usuario(Usuario usuario){
         
     }
     
     
     Usuario Consultar_Usuario(int Id){
         
        
        return new Usuario();
     }
     
     Usuario Consultar_Usuario(String correo_electronico){
          
         return new Usuario();
     }
     
     void Borrar_Usuario_Admin(String correo_electronico){
         
     }
     
     void Borrar_Usuario_Admin(int Id){
         
     }
     void Actualizar_Usuario(String correo_electronico){
         
     }
     void Actualizar_Usuario(int Id){
         
     }
     
    
    //Gestionar Condominios
    
     ArrayList<Condominio> Consultar_Condominios_Usuario(){
         
         
         return new ArrayList<Condominio>();
         
     }
     void Registrar_Condominio_Admin(Condominio condomnio){
         
     }
     void Eliminar_Condominio_Admin(String CIF){
         
     }
     void Eliminar_Condominio_Admin(int Id){
         
     }
     void Actualizar_Condominio_Admin(String CIF){
         
     }
     void Actualizar_Condominio_Admin(int Id){ 
         
     }
     
    
     
    //Gestionar Reservaciones
    
      ArrayList<Reservacion> Consultar_Reservaciones_Usuario(int Id){
          
          
             
            return new ArrayList<Reservacion>();
         
     }
      
      Reservacion Consultar_Reservacion_Admin(String CIF){
          
               return new Reservacion();
         
     }
      
      void  Registrar_Reservacion_Usuario(int Id_Usuario,int Id_Condominio){
         
     }
      
      void Actualizar_Reservacion(String CIF){
          
      }
      
      void Eliminar_Reservacion(String CIF){
          
      }
    
    */
    
}
