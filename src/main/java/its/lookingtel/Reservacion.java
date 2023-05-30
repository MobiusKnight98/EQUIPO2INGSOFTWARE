/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package its.lookingtel;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Guest Mode
 */
public class Reservacion extends Querys {

    private int Id_Condominio;
    private int Id_Usuario;
    private int No_Personas;
    private int dias_estadia;
    private Date fecha_llegada;
    private Date fecha_partida;
    private int costo_total;

    Reservacion(int Id_Condominio, int Id_Usuario, int No_Personas, int dias_estadia,
            Date fecha_llegada, Date fecha_partida, int costo_total) {

        this.Id_Condominio = Id_Condominio;
        this.Id_Usuario = Id_Usuario;
        this.No_Personas = No_Personas;
        this.dias_estadia = dias_estadia;
        this.fecha_llegada = fecha_llegada;
        this.fecha_partida = fecha_partida;
        this.costo_total = costo_total;

    }

    public static int Realizar_Reservacion(Reservacion reservacion) {

        try (Connection conn = Conexion_Remota.hikaridatasource.getConnection()) {

            if (conn == null) {
                JOptionPane.showMessageDialog(null, "La conexion es nula no se puede iniciar sesion", "Error", 0);
                return 0;
            }

            System.out.println("Id condominio: " + reservacion.Id_Condominio);
            System.out.println("Id del usuario: " + reservacion.Id_Usuario);

            PreparedStatement statement = conn.prepareStatement("INSERT INTO RESERVACIONES (Id_Condominio,Id_Usuario,No_Personas,Dias_Estadia,Fecha_Reservacion,Fecha_Llegada,Fecha_Partida,Costo_Total) VALUES (?,?,?,?,NOW(),?,?,?)");
            statement.setInt(1, reservacion.Id_Condominio);
            statement.setInt(2, reservacion.Id_Usuario);
            statement.setInt(3, reservacion.No_Personas);
            statement.setInt(4, reservacion.dias_estadia);
            statement.setDate(5, reservacion.fecha_llegada);
            statement.setDate(6, reservacion.fecha_partida);
            statement.setInt(7, reservacion.costo_total);

            int statusprocess = statement.executeUpdate();

            if (statusprocess == 1) {
                JOptionPane.showMessageDialog(null, "Reservacion Realizada exitosamente !. Codigo de Salida 1", "Success", 1);
                return 1;
            }

            JOptionPane.showMessageDialog(null, "No se pudo realizar la reservacion Codigo de Error 0", "Error", 0);
            statement.close();
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(Reservacion.class.getName()).log(Level.SEVERE, null, ex);
        }

        return 0;
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
