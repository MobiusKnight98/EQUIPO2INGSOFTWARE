/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package its.lookingtel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Guest Mode
 */
public class Usuario extends Querys {

    public static int Id;
    public static int Id_Ubicacion;
    public static String nombre;
    public static String telefono;
    public static String correo_electronico;
    public static Date fecha_nacimiento;
    public static int edad;
    public static String direccion;
    public static String sexo;
    public static Date fecha_registro;
    public static String contraseña;

    @Override
    boolean IniciarSesion(String correo_electronico, String contraseña) {

        boolean statuslogin = false;

        try (Connection conn = Conexion_Remota.hikaridatasource.getConnection()) {

            if (conn == null) {
                JOptionPane.showMessageDialog(null, "La conexion es nula no se puede iniciar sesion", "Error", 0);
                return statuslogin;
            }

            // Statement st = conn.createStatement();
            //st.execute("""
            //    INSERT INTO RESERVACIONES (Id_Condominio,Id_Usuario,No_Personas,Dias_Estadia,Fecha_Reservacion,Fecha_Llegada,Fecha_Partida,Costo_Total) VALUES (1,2,13,45,NOW(),DATE_ADD(NOW(),INTERVAL 10 DAY),DATE_ADD(NOW(),INTERVAL 55 DAY),30000);
            //    """);
            PreparedStatement statement = conn.prepareStatement("SELECT Id,Id_Ubicacion,Nombre,Telefono,Email,Fecha_Nacimiento,Edad,Direccion,Sexo,Fecha_Registro,Contraseña FROM USERS WHERE Email=?");
            statement.setString(1, correo_electronico);
            ResultSet rs = statement.executeQuery();
            //ResultSetMetaData rsmd = rs.getMetaData();
            //System.out.println(rsmd.getColumnName(1));
            try {
                rs.next();
                if (contraseña.equals(rs.getString(11))) {
                    statuslogin = true;
                    Usuario.Id = rs.getInt(1);
                    Usuario.Id_Ubicacion = rs.getInt(2);
                    Usuario.nombre = rs.getString(3);
                    Usuario.telefono = rs.getString(4);
                    Usuario.correo_electronico = rs.getString(5);
                    Usuario.fecha_nacimiento = rs.getDate(6);
                    Usuario.edad = rs.getInt(7);
                    Usuario.direccion = rs.getString(8);
                    Usuario.sexo = rs.getString(9);
                    Usuario.fecha_registro = rs.getDate(10);
                    Usuario.contraseña = rs.getString(11);
                    return statuslogin;
                }
                JOptionPane.showMessageDialog(null, "Contraseña incorrecta", "La contraseña introducida es incorrecta", 0);

            } catch (Exception ex) {
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
            System.out.println("La conexion no se pudo establecer");
        }
        return statuslogin;
    }

    @Override
    String[] Recuperar_Contraseña_Usuario(String correo_electronico) {

        String[] data = new String[2];
        try (Connection conn = Conexion_Remota.hikaridatasource.getConnection()) {

            if (conn == null) {
                JOptionPane.showMessageDialog(null, "La conexion a la BD no se puede establecer por lo que el correo no se puede verificar", "Error", 0);
                return data;
            }
            try {

                PreparedStatement statement = conn.prepareStatement("SELECT Email, Contraseña FROM USERS WHERE Email=?");
                statement.setString(1, correo_electronico);
                ResultSet rs = statement.executeQuery();
                rs.next();
                data[0] = rs.getString(1);
                data[1] = rs.getString(2);
                rs.close();
                statement.close();
                conn.close();

            } catch (SQLException ex) {
                if (ex.toString().contains("Illegal operation on empty result set")) {

                    JOptionPane.showMessageDialog(null, "Correo electronico no existente en la BD", "Error", 0);

                }
                // System.out.println(ex);
                // Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            System.out.println("No se pudo establecer la conexion con la BD");
        }
        return data;

    }

}
