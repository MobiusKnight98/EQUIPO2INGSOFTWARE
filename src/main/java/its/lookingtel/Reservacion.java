/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package its.lookingtel;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
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

    public static boolean Verificar_Condominio_En_Reservacion_Admin(int id) {

        try (Connection conn = Conexion_Remota.hikaridatasource.getConnection()) {

            if (conn == null) {
                JOptionPane.showMessageDialog(null, "La conexion es nula no se puede iniciar sesion", "Error", 0);
                return false;
            }

            PreparedStatement statement = conn.prepareStatement("SELECT Id_Condominio FROM RESERVACIONES WHERE RESERVACIONES.Id_Condominio=?");
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {

                System.out.println("Reservacion existente en base al Id del condominio: " + rs.getInt(1));
                rs.close();
                statement.close();
                conn.close();
                return true;
            }

            System.out.println("Este condominio no esta ligado a ninguna reservacion");
            rs.close();
            statement.close();
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(Reservacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    public static int Actualizar_Reservacion_Admin(int id, int no_personas, int dias_estadia, Date fecha_llegada, Date fecha_partida, int costo_total) {

        try (Connection conn = Conexion_Remota.hikaridatasource.getConnection()) {

            if (conn == null) {
                JOptionPane.showMessageDialog(null, "La conexion es nula no se puede iniciar sesion", "Error", 0);
                return 0;
            }

            PreparedStatement statement = conn.prepareStatement("UPDATE RESERVACIONES SET No_Personas=?,Dias_Estadia=?,Fecha_Llegada=?,Fecha_Partida=?,Costo_Total=? WHERE Id=?");

            statement.setInt(1, no_personas);
            statement.setInt(2, dias_estadia);
            statement.setDate(3, fecha_llegada);
            statement.setDate(4, fecha_partida);
            statement.setInt(5, costo_total);
            statement.setInt(6, id);

            int statusprocess = statement.executeUpdate();
            statement.close();
            conn.close();

            if (statusprocess == 1) {
                JOptionPane.showMessageDialog(null, "Reservacion Actualizada Satisfactoriamente Codigo de Salida 1", "Success", 1);
                return 1;
            }

            JOptionPane.showMessageDialog(null, "No se pudo actualizar la Reservacion Codigo de Error 0", "Error", 0);

        } catch (SQLException ex) {
            Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;

    }

    public static int Eliminar_Reservacion(int id) {

        int status = 0;
        try (Connection conn = Conexion_Remota.hikaridatasource.getConnection()) {

            PreparedStatement statement = null;

            try {

                statement = conn.prepareStatement("DELETE FROM RESERVACIONES WHERE Id=?");
                statement.setInt(1, id);
                status = statement.executeUpdate();
                JOptionPane.showMessageDialog(null, "Reservacion eliminada satisfactoriamente", "Exito", 1);

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(null, "No se pudo eliminar la reservacion", "Error", 0);

            }

            statement.close();
            conn.close();

        } catch (SQLException ex) {
            System.out.println("Cannot establish connection");
        }

        return status;

    }

    public static void Eliminar_Reservacion_Admin(int id, String CIF) {

        try (Connection conn = Conexion_Remota.hikaridatasource.getConnection()) {

            PreparedStatement statement = null;

            try {

                statement = conn.prepareStatement("DELETE FROM RESERVACIONES WHERE Id=?");
                statement.setInt(1, id);
                statement.executeUpdate();
                JOptionPane.showMessageDialog(null, "Reservacion eliminada satisfactoriamente", "Exito", 1);
                Condominio.Actualizar_Status_Condominio_CIF(CIF, 1);
            } catch (Exception ex) {

                JOptionPane.showMessageDialog(null, "No se pudo eliminar la reservacion", "Error", 0);

            }

            statement.close();
            conn.close();

        } catch (SQLException ex) {
            System.out.println("Cannot establish connection");
        }

    }

    public static ArrayList<Object> Consultar_Reservacion_Admin(String CIF) {

        ArrayList<Object> reservacion = new ArrayList<Object>();

        try (Connection conn = Conexion_Remota.hikaridatasource.getConnection()) {

            if (conn == null) {
                JOptionPane.showMessageDialog(null, "La conexion es nula no se puede iniciar sesion", "Error", 0);
                return null;
            }

            PreparedStatement statement = conn.prepareStatement("SELECT RESERVACIONES.Id, CONDOMINIOS.Nombre, ( SELECT CONCAT( UBICATION.Pais, '/ ', UBICATION.Estado, '/', UBICATION.Ciudad ) FROM UBICATION WHERE UBICATION.Id = CONDOMINIOS.Id_Ubicacion ) AS Ubicacion, CONDOMINIOS.Direccion, CONDOMINIOS.Precio_x_Noche, USERS.Nombre, USERS.Email, USERS.Telefono, RESERVACIONES.Dias_Estadia, RESERVACIONES.No_Personas, RESERVACIONES.Fecha_Reservacion, RESERVACIONES.Fecha_Llegada, RESERVACIONES.Fecha_Partida, RESERVACIONES.Costo_Total, CONDOMINIOS.No_Habitaciones, CONDOMINIOS.CIF FROM RESERVACIONES, CONDOMINIOS, USERS WHERE RESERVACIONES.Id_Condominio = CONDOMINIOS.Id AND RESERVACIONES.Id_Usuario = USERS.Id AND CONDOMINIOS.CIF =?");
            statement.setString(1, CIF);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {

                System.out.println("Reservacion existente en base al CIF del condominio: " + CIF);
                reservacion.add(rs.getString(1));
                reservacion.add(rs.getString(2));
                reservacion.add(rs.getString(3));
                reservacion.add(rs.getString(4));
                reservacion.add(rs.getInt(5));
                reservacion.add(rs.getString(6));
                reservacion.add(rs.getString(7));
                reservacion.add(rs.getString(8));
                reservacion.add(rs.getInt(9));
                reservacion.add(rs.getInt(10));
                reservacion.add(rs.getDate(11));
                reservacion.add(rs.getDate(12));
                reservacion.add(rs.getDate(13));
                reservacion.add(rs.getInt(14));
                reservacion.add(rs.getInt(15));
                reservacion.add(rs.getString(16));

                return reservacion;
            }

            JOptionPane.showMessageDialog(null, "No existe ninguna reservacion con el CIF de condominio proporcionado", "Error", 0);
            statement.close();
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(Reservacion.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;

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
