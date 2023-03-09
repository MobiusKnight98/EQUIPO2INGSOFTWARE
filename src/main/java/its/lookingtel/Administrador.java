/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package its.lookingtel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Guest Mode
 */
public class Administrador extends Querys {
    
    @Override
    void IniciarSesion(String correo_electronico, String contraseña, Connection conn) {
     
        
        if (conn == null) {
            JOptionPane.showMessageDialog(null, "La conexion es nula no se puede iniciar sesion", "Error", 0);
            return;
        }

        try {
            Statement st = conn.createStatement();
            //st.execute("""
            //    INSERT INTO RESERVACIONES (Id_Condominio,Id_Usuario,No_Personas,Dias_Estadia,Fecha_Reservacion,Fecha_Llegada,Fecha_Partida,Costo_Total) VALUES (1,2,13,45,NOW(),DATE_ADD(NOW(),INTERVAL 10 DAY),DATE_ADD(NOW(),INTERVAL 55 DAY),30000);
            //    """);
            ResultSet rs = st.executeQuery("SELECT * FROM ADMINISTRADORES");
            ResultSetMetaData rsmd = rs.getMetaData();
            System.out.println(rsmd.getColumnName(1));
            while(rs.next()){
                System.out.println(rs.getString(1));
            }
            rs.close();
            st.close();
            conn.close();
            //  String output = rsmd.getColumnName(1) + " " + rsmd.getColumnName(2) + " " + rsmd.getColumnName(3) + " " + rsmd.getColumnName(4) + "\n";
            //    while (rs.next()) { 
            //    output += rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4) + " ";
            //   }
            //  System.out.print(output);
        } catch (SQLException ex) {
            Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // close the connection brou
        }

    }

}
