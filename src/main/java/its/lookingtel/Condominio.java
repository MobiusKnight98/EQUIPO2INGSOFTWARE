/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package its.lookingtel;

import static its.lookingtel.Registrar_Condominio.map;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Guest Mode
 */
public class Condominio extends Querys {
    
    private int Id;
    private HashMap<Integer,String> Ubicacion;
    private String nombre;
    private String CIF;
    private int score;
    private Date Fecha_Registro;
    private int No_Habitaciones;
    private String direccion;
    private int precio_x_noche;
    private String servicios;
    private String image_lugar;
    private int Status;
    
    
 
      void GetUbications(){
      Connection conn = Conexion_Remota.Conectar_BD();
      try {
            // Statement st = conn.createStatement();
            //st.execute("""
            //    INSERT INTO RESERVACIONES (Id_Condominio,Id_Usuario,No_Personas,Dias_Estadia,Fecha_Reservacion,Fecha_Llegada,Fecha_Partida,Costo_Total) VALUES (1,2,13,45,NOW(),DATE_ADD(NOW(),INTERVAL 10 DAY),DATE_ADD(NOW(),INTERVAL 55 DAY),30000);
            //    """);
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM UBICATION");
            ResultSet rs = statement.executeQuery();
            //ResultSetMetaData rsmd = rs.getMetaData();
            //System.out.println(rsmd.getColumnName(1));
            while (rs.next()) {

                int Id = rs.getInt(1);
                String Pais = rs.getString(2);
                String Estado = rs.getString(3);
                String Ciudad = rs.getString(4);
                Ubicacion.put(Id, Pais + "/" + Estado + "/" + Ciudad);
               
                ////////////jComboBox1.addItem(Pais + "/" + Estado + "/" + Ciudad);

            }

            rs.close();
            statement.close();
            conn.close();
            //  String output = rsmd.getColumnName(1) + " " + rsmd.getColumnName(2) + " " + rsmd.getColumnName(3) + " " + rsmd.getColumnName(4) + "\n";
            //    while (rs.next()) { 
            //    output += rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4) + " ";
            //   }
            //  System.out.print(output);
        } catch (SQLException ex) {
            Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
        }
      }

   
  
  
    Condominio Consultar_Condominio_Admin(String CIForId) {
         
        
        Connection conn = Conexion_Remota.Conectar_BD();
        try {
           
            
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM CONDOMINIOS WHERE Id=? OR CIF=?");
            statement.setString(1,CIForId);
            statement.setString(2,CIForId);
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Condominio.class.getName()).log(Level.SEVERE, null, ex);
        }
       return new Condominio();
    }

    /**
     * @return the Id
     */
    public int getId() {
        return Id;
    }

    /**
     * @param Id the Id to set
     */
    public void setId(int Id) {
        this.Id = Id;
    }

   
   
    public String getNombre() {
        return nombre;
    }

   
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

   
    public String getCIF() {
        return CIF;
    }

   
    public void setCIF(String CIF) {
        this.CIF = CIF;
    }

   
    public int getScore() {
        return score;
    }

    
    public void setScore(int score) {
        this.score = score;
    }

   
    public Date getFecha_Registro() {
        return Fecha_Registro;
    }

    
    public void setFecha_Registro(Date Fecha_Registro) {
        this.Fecha_Registro = Fecha_Registro;
    }

   
    public int getNo_Habitaciones() {
        return No_Habitaciones;
    }

    
    public void setNo_Habitaciones(int No_Habitaciones) {
        this.No_Habitaciones = No_Habitaciones;
    }

  
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the precio_x_noche
     */
    public int getPrecio_x_noche() {
        return precio_x_noche;
    }

    /**
     * @param precio_x_noche the precio_x_noche to set
     */
    public void setPrecio_x_noche(int precio_x_noche) {
        this.precio_x_noche = precio_x_noche;
    }

   
    public String getServicios() {
        return servicios;
    }

   
    public void setServicios(String servicios) {
        this.servicios = servicios;
    }

   
    public String getImage_lugar() {
        return image_lugar;
    }

   
    public void setImage_lugar(String image_lugar) {
        this.image_lugar = image_lugar;
    }

   
    public int getStatus() {
        return Status;
    }

    /**
     * @param Status the Status to set
     */
    public void setStatus(int Status) {
        this.Status = Status;
    }
    
    
    

    @Override
    boolean IniciarSesion(String correo_electronico, String contraseña) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    String[] Recuperar_Contraseña_Usuario(String correo_electronico) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
    
}



