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

        Connection conn = Conexion_Remota.Conectar_BD();PreparedStatement statement;
        try {

            Integer.parseInt(CIForId);
            statement = conn.prepareStatement("SELECT * FROM CONDOMINIOS WHERE Id=?");
            statement.setString(1, CIForId);

        } catch (Exception ex) {

            statement = conn.prepareStatement("SELECT * FROM CONDOMINIOS WHERE CIF=?");
            statement.setString(1, CIForId);

        }

        ResultSet rs = null;Condominio condominio = null;
        try {
            rs = statement.executeQuery();
            rs.next();
            condominio = new Condominio(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getInt(5),
                    Date.valueOf(rs.getString(6)), rs.getInt(7), rs.getString(8), rs.getInt(9), rs.getString(10), rs.getString(11), rs.getInt(12));
        } 
        catch (SQLException ex) {

        }

        rs.close();
        statement.close();
        conn.close();


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

    public int getId() {
        return Id;
    }

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

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getPrecio_x_noche() {
        return precio_x_noche;
    }

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
