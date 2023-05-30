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
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.sql.Date;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Guest Mode
 */
public class Usuario extends Querys {
    
    // perfil usuario

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
    
    // consultas de usuarios por admin

     int Id_user;
     int Id_Ubicacion_user;
     String nombre_user;
     String telefono_user;
     Date fecha_nacimiento_user;
     int edad_user;
     String direccion_user;
     String sexo_user;
     Date fecha_registro_user;
     String contraseña_user;
     String correo_electronico_user;

    Usuario() {

    }

    Usuario(int Id_user, int Id_Ubicacion_user, String nombre_user,
            String telefono_user, String correo_electronico_user, Date fecha_nacimiento_user, int edad_user, String direccion_user, String sexo_user,
            Date fecha_registro_user, String contraseña_user) {
        this.Id_user = Id_user;
        this.Id_Ubicacion_user = Id_Ubicacion_user;
        this.nombre_user = nombre_user;
        this.telefono_user = telefono_user;
        this.edad_user = edad_user;
        this.direccion_user = direccion_user;
        this.sexo_user = sexo_user;
        this.fecha_registro_user = fecha_registro_user;
        this.fecha_nacimiento_user = fecha_nacimiento_user;
        this.contraseña_user = contraseña_user;
        this.correo_electronico_user = correo_electronico_user;

    }

    int Id_user_get() {
        return this.Id_user;
    }

    void Id_user_set(int id_user) {
        this.Id_user = id_user;
    }

    int Ubicacion_user_get() {
        return this.Id_Ubicacion_user;
    }

    void Ubicacion_user_set(int Id_Ubicacion_user) {
        this.Id_Ubicacion_user = Id_Ubicacion_user;
    }

    String Nombre_user_get() {
        return this.nombre_user;
    }

    void Nombre_user_set(String nombre_user) {
        this.nombre_user = nombre_user;
    }

    String Telefono_user_get() {
        return this.telefono_user;
    }

    void Telefono_user_set(String telefono_user) {
        this.telefono_user = telefono_user;
    }

    int Edad_user_get() {
        return this.edad_user;
    }

    void Edad_user_set(int edad_user) {
        this.edad_user = edad_user;
    }

    String Direccion_user_get() {
        return this.direccion_user;
    }

    void Direccion_user_set(String direccion_user) {
        this.direccion_user = direccion_user;
    }

    String Sexo_user_get() {
        return this.sexo_user;
    }

    void Sexo_user_set(String sexo_user) {
        this.sexo_user = sexo_user;
    }

    Date Fecha_Registro_user_get() {
        return this.fecha_registro_user;
    }

    void Fecha_Registro_user_set(Date fecha_registro_user) {
        this.fecha_registro_user = fecha_registro_user;
    }

    Date Fecha_Nacimiento_user_get() {
        return this.fecha_nacimiento_user;
    }

    void Fecha_Nacimiento_user_set(Date fecha_nacimiento_user) {
        this.fecha_nacimiento_user = fecha_nacimiento_user;
    }

    String Contraseña_user_get() {
        return this.contraseña_user;
    }

    void Contraseña_user_set(String contraseña_user) {
        this.contraseña_user = contraseña_user;
    }

    String correo_electronico_user_get() {
        return this.correo_electronico_user;
    }

    void correo_electronico_user_set(String correo_electronico_user) {
        this.correo_electronico_user = correo_electronico_user;
    }

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

    static Usuario Consultar_Usuario(String id_o_correo) {

        Usuario user = null;
        try (Connection conn = Conexion_Remota.hikaridatasource.getConnection()) {

            if (conn == null) {
                JOptionPane.showMessageDialog(null, "La conexion es nula no se puede iniciar sesion", "Error", 0);
                return null;
            }

            PreparedStatement statement = null;
            ResultSet rs = null;
            System.out.println("Realizamos try");
            try {

                Integer.parseInt(id_o_correo);
                System.out.println("Es un id");
                statement = conn.prepareStatement("SELECT * FROM USERS WHERE Id=? ");
                statement.setString(1, id_o_correo);

            } catch (NumberFormatException ex) {
                System.out.println("Es un correo");
                statement = conn.prepareStatement("SELECT * FROM USERS WHERE Email=? ");
                statement.setString(1, id_o_correo);

            }

            rs = statement.executeQuery();

            try {
                rs.next();

                user = new Usuario(rs.getInt(1), rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getDate(6),
                        rs.getInt(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getDate(10),
                        rs.getString(11)
                );

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "No existe el correo electronico o Id proporcionado", "Error", 0);
            }

            rs.close();
            statement.close();
            conn.close();

        } catch (SQLException ex) {
            System.out.println("La conexion no se pudo establecer");
        }

        return user;
    }

    static int Eliminar_Usuario(int Id) {
        int status = 0;
        try (Connection conn = Conexion_Remota.hikaridatasource.getConnection()) {

            if (conn == null) {
                JOptionPane.showMessageDialog(null, "La conexion es nula no se puede borrar el usuario", "Error", 0);
                return status;
            }

            PreparedStatement statement = null;

            try {

                statement = conn.prepareStatement("DELETE FROM USERS WHERE Id=?");
                statement.setInt(1, Id);
                status = statement.executeUpdate();
                JOptionPane.showMessageDialog(null, "Usuario borrado satisfactoriamente", "Exito", 1);

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(null, "No se pudo borrar el usuario", "Error", 0);

            }

            statement.close();
            conn.close();

        } catch (SQLException ex) {
            System.out.println("Cannot establish connection");
        }

        return status;

    }

    static void Actualizar_Usuario(Usuario usuario) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(usuario.Fecha_Nacimiento_user_get());
        String fecha_nacimiento_usuario = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DATE);

        LocalDate currentDate = LocalDate.now();
        LocalDate dateToSubtract = LocalDate.of(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DATE));
        long daysdif = ChronoUnit.DAYS.between(dateToSubtract, currentDate);
        int edad_usuario = ((int) daysdif / 365);

        // Creamos el query
        try (Connection conn = Conexion_Remota.hikaridatasource.getConnection()) {

            if (conn == null) {
                JOptionPane.showMessageDialog(null, "La conexion es nula no se puede iniciar sesion", "Error", 0);
                return;
            }

            try {

                PreparedStatement statement = conn.prepareStatement("UPDATE USERS SET Id_Ubicacion=?,Nombre=?,Telefono=?,Email=?,Fecha_Nacimiento=?,Edad=?,Direccion=?,Sexo=?,Contraseña=? WHERE Id=?");

                statement.setInt(1, usuario.Ubicacion_user_get());
                statement.setString(2, usuario.Nombre_user_get());
                statement.setString(3, usuario.Telefono_user_get());
                statement.setString(4, usuario.correo_electronico_user_get());
                statement.setDate(5, Date.valueOf(fecha_nacimiento_usuario));
                statement.setInt(6, edad_usuario);
                statement.setString(7, usuario.Direccion_user_get());
                statement.setString(8, usuario.Sexo_user_get());
                statement.setString(9, usuario.Contraseña_user_get());
                statement.setInt(10, usuario.Id_user_get());

                int statusprocess = statement.executeUpdate();
                System.out.println(usuario.Id_user_get());
                System.out.println(usuario.Ubicacion_user_get());
                System.out.println(usuario.Nombre_user_get());
                System.out.println(usuario.Telefono_user_get());
                System.out.println(usuario.correo_electronico_user_get());
                System.out.println(fecha_nacimiento_usuario);

                System.out.println(edad_usuario);
                System.out.println(usuario.Direccion_user_get());
                System.out.println(usuario.Sexo_user_get());
                System.out.println(usuario.Contraseña_user_get());

                if (statusprocess == 1) {
                    JOptionPane.showMessageDialog(null, "Usuario Actualizado Satisfactoriamente Codigo de Salida 1", "Sucess", 1);
                    return;
                }

                JOptionPane.showMessageDialog(null, "No se pudo actualizar Usuario Codigo de Error 0", "Error", 0);

                statement.close();
                conn.close();

            } catch (SQLIntegrityConstraintViolationException ex) {

                if (ex.toString().contains("Email")) {
                    JOptionPane.showMessageDialog(null, "No se puede actualizar usuario, existe un usuario actual con el mail proporcionado", "Error", 0);
                    return;
                }
                if (ex.toString().contains("Telefono")) {
                    JOptionPane.showMessageDialog(null, "No se puede actualizar usuario, existe un usuario actual con el telefono proporcionado", "Error", 0);

                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
