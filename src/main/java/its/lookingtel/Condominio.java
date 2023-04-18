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
import javax.swing.JOptionPane;

/**
 *
 * @author Guest Mode
 */
public class Condominio extends Querys {

    private int Id;
    private int Ubicacion;
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

    Condominio() {

    }

    Condominio(int Id, int Ubicacion, String Nombre, String CIF, int score, Date Fecha_Registro, int no_Habitaciones, String direccion,
            int precio_x_noche, String servicios, String image_lugar, int Status) {
        this.Id = Id;
        this.Ubicacion = Ubicacion;
        this.nombre = Nombre;
        this.CIF = CIF;
        this.score = score;
        this.Fecha_Registro = Fecha_Registro;
        this.No_Habitaciones = no_Habitaciones;
        this.direccion = direccion;
        this.precio_x_noche = precio_x_noche;
        this.servicios = servicios;
        this.image_lugar = image_lugar;
        this.Status = Status;

    }

    Condominio Consultar_Condominio_Admin(String CIForId) throws SQLException {

        Condominio condominio = null;

        try (Connection conn = Conexion_Remota.hikaridatasource.getConnection()) {

            PreparedStatement statement;

            try {

                Integer.parseInt(CIForId);
                statement = conn.prepareStatement("SELECT * FROM CONDOMINIOS WHERE Id=?");
                statement.setString(1, CIForId);

            } catch (Exception ex) {

                statement = conn.prepareStatement("SELECT * FROM CONDOMINIOS WHERE CIF=?");
                statement.setString(1, CIForId);

            }

            ResultSet rs = null;

            try {
                rs = statement.executeQuery();
                rs.next();
                condominio = new Condominio(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getInt(5),
                        Date.valueOf(rs.getString(6)), rs.getInt(7), rs.getString(8), rs.getInt(9), rs.getString(10), rs.getString(11), rs.getInt(12));
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "El CIF o Id proporcionado no existe", "Error", 0);
            }

            rs.close();
            statement.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println("Cannot establish connection");
        }

        /*private int Id;
    private HashMap<Integer, String> Ubicacion;
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
         */
        return condominio;
    }

    int Eliminar_Condominio_Administrador(int id) {
        int status = 0;
        try (Connection conn = Conexion_Remota.hikaridatasource.getConnection()) {

            PreparedStatement statement = null;

            try {

                statement = conn.prepareStatement("DELETE FROM CONDOMINIOS WHERE Id=?");
                statement.setInt(1, id);
                status = statement.executeUpdate();
                JOptionPane.showMessageDialog(null, "Condominio borrado satisfactoriamente", "Exito", 1);

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(null, "No se pudo borrar el condominio", "Error", 0);

            }

            statement.close();
            conn.close();

        } catch (SQLException ex) {
            System.out.println("Cannot establish connection");
        }

        return status;

    }

    public int getId() {
        return Id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCIF() {
        return CIF;
    }

    public int getScore() {
        return score;
    }

    public Date getFecha_Registro() {
        return Fecha_Registro;
    }

    public int getNo_Habitaciones() {
        return No_Habitaciones;
    }

    public String getDireccion() {
        return direccion;
    }

    public int getPrecio_x_noche() {
        return precio_x_noche;
    }

    public String getServicios() {
        return servicios;
    }

    public String getImage_lugar() {
        return image_lugar;
    }

    public int getStatus() {
        return Status;
    }

    public int getUbicacion() {
        return Ubicacion;
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
