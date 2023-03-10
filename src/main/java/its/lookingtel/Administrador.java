/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package its.lookingtel;

import java.sql.*;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Guest Mode
 */
public abstract class Administrador extends Querys {
    
   

    static boolean IniciarSesion(String correo_electronico, String contraseña) {

        Connection conn = Conexion_Remota.Conectar_BD();

        if (conn == null) {
            JOptionPane.showMessageDialog(null, "La conexion es nula no se puede iniciar sesion", "Error", 0);
            return false;
        }
        boolean statuslogin = false;
        try {
            // Statement st = conn.createStatement();
            //st.execute("""
            //    INSERT INTO RESERVACIONES (Id_Condominio,Id_Usuario,No_Personas,Dias_Estadia,Fecha_Reservacion,Fecha_Llegada,Fecha_Partida,Costo_Total) VALUES (1,2,13,45,NOW(),DATE_ADD(NOW(),INTERVAL 10 DAY),DATE_ADD(NOW(),INTERVAL 55 DAY),30000);
            //    """);
            PreparedStatement statement = conn.prepareStatement("SELECT Email,Contraseña FROM ADMINISTRADORES WHERE Email=?");
            statement.setString(1, correo_electronico);
            ResultSet rs = statement.executeQuery();
            //ResultSetMetaData rsmd = rs.getMetaData();
            //System.out.println(rsmd.getColumnName(1));

            try {
                rs.next();
                System.out.println(rs.getString(1));
                if (contraseña.equals(rs.getString(2))) {
                    statuslogin = true;
                    return statuslogin;
                }
                JOptionPane.showMessageDialog(null, "Contraseña incorrecta", "La contraseña introducida es incorrecta", 0);

            } 
            catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Correo electronico invalido", "No existe el correo electronico proporcionado", 0);
            }
            //while(rs.next()){
            //  System.out.println(rs.getString(1));
            // }
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
        return statuslogin;
    }

}
