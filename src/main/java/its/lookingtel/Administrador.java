/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package its.lookingtel;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Guest Mode
 */
public class Administrador extends Querys {

    public static String correo_electronico;

    @Override
    public boolean IniciarSesion(String correo_electronico, String contraseña) {

        boolean statuslogin = false;
        
        try (Connection conn = Conexion_Remota.hikaridatasource.getConnection()) {

            if (conn == null) {
                JOptionPane.showMessageDialog(null, "La conexion es nula no se puede iniciar sesion", "Error", 0);
                return statuslogin;
            }

            PreparedStatement statement = conn.prepareStatement("SELECT Email,Contraseña FROM ADMINISTRADORES WHERE Email=?");
            statement.setString(1, correo_electronico);
            ResultSet rs = statement.executeQuery();

            try {
                rs.next();
                if (contraseña.equals(rs.getString(2))) {
                    statuslogin = true;
                    Administrador.correo_electronico = rs.getString(1);
                    return statuslogin;
                }
                JOptionPane.showMessageDialog(null, "Contraseña incorrecta", "La contraseña introducida es incorrecta", 0);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Correo electronico invalido", "No existe el correo electronico proporcionado", 0);
            }

            rs.close();
            statement.close();
            conn.close();

        } catch (SQLException ex) {
            System.out.println("Error no se pudo establecer la conexion");
            // Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
        }

        return statuslogin;

        // Statement st = conn.createStatement();
        //st.execute("""
        //    INSERT INTO RESERVACIONES (Id_Condominio,Id_Usuario,No_Personas,Dias_Estadia,Fecha_Reservacion,Fecha_Llegada,Fecha_Partida,Costo_Total) VALUES (1,2,13,45,NOW(),DATE_ADD(NOW(),INTERVAL 10 DAY),DATE_ADD(NOW(),INTERVAL 55 DAY),30000);
        //    """);
        //ResultSetMetaData rsmd = rs.getMetaData();
        //System.out.println(rsmd.getColumnName(1));
        //while(rs.next()){
        //  System.out.println(rs.getString(1));
        // }
        //  String output = rsmd.getColumnName(1) + " " + rsmd.getColumnName(2) + " " + rsmd.getColumnName(3) + " " + rsmd.getColumnName(4) + "\n";
        //    while (rs.next()) { 
        //    output += rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4) + " ";
        //   }
        //  System.out.print(output);
    }

    @Override
    String[] Recuperar_Contraseña_Usuario(String correo_electronico) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
