/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package its.lookingtel;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Container;
import java.awt.Graphics2D;
import java.sql.Date;
import javax.swing.JLabel;

/**
 *
 * @author Guest Mode
 */
public class Gestion_Administrativa extends javax.swing.JFrame {
    
    Condominio cond;
    HashMap<Integer, String> Ubicacion = new HashMap<Integer, String>();
    String tracktab = "Usuarios";
    Registrar_Condominio pantalla_registrar_condominio;
    Login_Administrador pantalla_login_administrador;
    URL imagen_condominio;
    String nombre_imagen, new_nombre_imagen = "";
    static String picturepath = "";
    static final String lettersandnumbers = "^[a-zA-Z0-9]*$";
    static final String ValidName = "^[A-Z][a-záéíóúñ]+\\s*([ ][A-Z][a-záéíóúñ]+)*$";
    static final String numbersonly = "^[0-9]+$";
    static final String capitallettersandnumbers = "^[A-Z0-9]+$";
    static final String direccion = "^[A-Z][#,a-záéíóúñA-Z.()/0-9 ]+$";
    static final String email = "^[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}$";
    Usuario usuario, usuario2;
    
    public Gestion_Administrativa() {
        initComponents();
        KeyStroke escapeKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);

        // Get the content pane of the frame
        Container contentPane = getContentPane();
        
        JComponent contentPaneAsJComponent = (JComponent) contentPane;

        // Add a key binding for the escape key to the content pane
        contentPaneAsJComponent.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(escapeKeyStroke, "escapeKeyPressed");
        contentPaneAsJComponent.getActionMap().put("escapeKeyPressed", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                
                if (jButton4.getText().equals("Guardar Datos")) {
                    
                    jButton4.setText("Actualizar Condominio");
                    jButton4.setForeground(Color.black);
                    jTextField9.setEnabled(true);
                    jButton5.setEnabled(true);
                    ClearChanges();
                    MostrarCondominio();
                    MostrarImagenCondominio();
                    resetFields();
                    jLabel4.requestFocus();
                }
                
            }
        });
        jDateChooser1.setBorder(BorderFactory.createLineBorder(Color.black, 3, false));
        jDateChooser2.setBorder(BorderFactory.createLineBorder(Color.black, 3, false));
        getContentPane().setBackground(Color.white);
        jTabbedPane1.setBackground(Color.white);
        jPanel1.setBackground(Color.white);
        jPanel2.setBackground(Color.white);
        jPanel3.setBackground(Color.white);
        jPanel4.setBackground(Color.white);
        jPanel7.setBackground(Color.white);
        jButton1.setBackground(Color.white);
        jButton11.setBackground(Color.white);
        jButton3.setBackground(Color.white);
        jButton4.setBackground(Color.white);
        jButton5.setBackground(Color.white);
        jButton7.setBackground(Color.white);
        jButton8.setBackground(Color.white);
        jComboBox123.setBackground(Color.white);
        jComboBox9.setBackground(Color.white);
        jComboBox14.setBackground(Color.white);
        jComboBox13.setBackground(Color.white);
        jComboBox16.setBackground(Color.white);
        jComboBox17.setBackground(Color.white);
        jComboBox18.setBackground(Color.white);
        jComboBox19.setBackground(Color.white);
        jComboBox20.setBackground(Color.white);
        jComboBox21.setBackground(Color.white);
        jComboBox22.setBackground(Color.white);
        jComboBox23.setBackground(Color.white);
        jComboBox24.setBackground(Color.white);
        jPanel5.setBackground(Color.white);
        jPanel6.setBackground(Color.white);
        jPanel9.setBackground(Color.white);
        jPanel10.setBackground(Color.white);
        jPanel11.setBackground(Color.white);
        jPanel12.setBackground(Color.white);
        jPanel13.setBackground(Color.white);
        jPanel14.setBackground(Color.white);
        jPanel15.setBackground(Color.white);
        jPanel8.setBackground(Color.white);
        jSpinner1.setBackground(Color.white);
        jSpinner2.setBackground(Color.white);
        jButton9.setBackground(Color.white);
        jButton6.setBackground(Color.white);
        
        resetFields();
        resetFieldsUser();
        displaylogos();
        
    }
    
    public void captureScreen(Login_Administrador login) {
        
        pantalla_login_administrador = login;
        
    }
    
    void GetUbications() {
        
        try (Connection conn = Conexion_Remota.hikaridatasource.getConnection()) {
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
                jComboBox14.addItem(Pais + "/" + Estado + "/" + Ciudad);
                jComboBox1.addItem(Pais + "/" + Estado + "/" + Ciudad);
                
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
            Logger.getLogger(Gestion_Administrativa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void MostrarCondominio() {
        
        jTextField6.setText(String.valueOf(cond.Id));
        jTextField10.setText(cond.nombre);
        jTextField11.setText(String.valueOf(cond.precio_x_noche));
        jComboBox9.setSelectedIndex(cond.Status);
        jTextField13.setText(cond.CIF);
        jFormattedTextField1.setText(String.valueOf(cond.Fecha_Registro));
        jTextArea2.setText(cond.direccion);
        System.out.println(String.valueOf(cond.score));
        jTextField12.setText(cond.score + "%");
        String habitaciones = String.valueOf(cond.No_Habitaciones);
        if (cond.No_Habitaciones == 10) {
            habitaciones = habitaciones + "+";
        }
        jComboBox13.setSelectedItem(habitaciones);
        
        String[] servicios = cond.servicios.split(",");
        List<String> services = Arrays.asList(servicios);
        
        System.out.println(services);
        
        for (Map.Entry<Integer, String> entry : Ubicacion.entrySet()) {
            
            if (entry.getKey() == cond.Ubicacion) {
                jComboBox14.setSelectedItem((String) entry.getValue());
                break;
            }
            
        }
        
        if (services.contains("Agua")) {
            jCheckBox1.setSelected(true);
        } else {
            jCheckBox1.setSelected(false);
        }
        
        if (services.contains("Gas")) {
            jCheckBox4.setSelected(true);
        } else {
            jCheckBox4.setSelected(false);
        }
        
        if (services.contains("Internet")) {
            jCheckBox5.setSelected(true);
        } else {
            jCheckBox5.setSelected(false);
        }
        
        if (services.contains("Luz")) {
            jCheckBox3.setSelected(true);
        } else {
            jCheckBox3.setSelected(false);
        }
        
        if (services.contains("Cocina")) {
            jCheckBox7.setSelected(true);
        } else {
            jCheckBox7.setSelected(false);
        }
        
        if (services.contains("Televisión")) {
            jCheckBox6.setSelected(true);
        } else {
            jCheckBox6.setSelected(false);
        }
        
    }
    
    void MostrarImagenCondominio() {
        try {
            
            nombre_imagen = cond.image_lugar.substring(cond.image_lugar.lastIndexOf("/") + 1);
            System.out.println("Nombre completo de la imagen" + nombre_imagen);
            imagen_condominio = new URL(cond.image_lugar);
            BufferedImage buffered_condominio_image = ImageIO.read(imagen_condominio);
            
            Image scaledImage_condominio = buffered_condominio_image.getScaledInstance(jPanel6.getWidth(),
                    jPanel6.getHeight(),
                    Image.SCALE_SMOOTH);
            
            ImageIcon icon_condominio = new ImageIcon(scaledImage_condominio);
            
            jLabel7.setIcon(icon_condominio);
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(Gestion_Administrativa.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Gestion_Administrativa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void actualizar_condominio(int key, ArrayList<String> services) throws IOException {
        
        int id_condominio = Integer.valueOf(jTextField6.getText());
        String nombre_condominio = jTextField10.getText();
        String direccion_condominio = jTextArea2.getText();
        String habitaciones_condominio = (String) jComboBox13.getSelectedItem();
        if (habitaciones_condominio.contains("+")) {
            habitaciones_condominio = habitaciones_condominio.substring(0, habitaciones_condominio.length() - 1);
        }
        int precio_condominio_noche = Integer.parseInt(jTextField11.getText());
        int ubicacion_condominio = key;
        int status = jComboBox9.getSelectedIndex();
        String cif_condominio = jTextField13.getText();
        int score = Integer.valueOf(jTextField12.getText().substring(0, jTextField12.getText().length() - 1));
        // convertir a string los servicios
        String stringifiedservices = "";
        for (String service : services) {
            stringifiedservices += service + ",";
        }
        
        stringifiedservices = stringifiedservices.substring(0, stringifiedservices.length() - 1);
        
        String url, pictureformat;
        
        if (!new_nombre_imagen.isEmpty()) {
            pictureformat = new_nombre_imagen.substring(new_nombre_imagen.lastIndexOf(".") + 1);
            url = "https://lookingtel.cellar-c2.services.clever-cloud.com/" + cif_condominio + "." + pictureformat;
            
        } else {
            pictureformat = nombre_imagen.substring(nombre_imagen.lastIndexOf(".") + 1);
            url = "https://lookingtel.cellar-c2.services.clever-cloud.com/" + cif_condominio + "." + pictureformat;
            
        }
        
        String outputpicname = cif_condominio + "." + pictureformat;
        System.out.println("Output pic name:" + outputpicname);
        
        System.out.println("Output picture path: " + url);

        // Creamos el query
        try (Connection conn = Conexion_Remota.hikaridatasource.getConnection()) {
            
            if (conn == null) {
                JOptionPane.showMessageDialog(null, "La conexion es nula no se puede iniciar sesion", "Error", 0);
                return;
            }
            
            System.out.println(ubicacion_condominio);
            System.out.println(nombre_condominio);
            System.out.println(cif_condominio);
            System.out.println(habitaciones_condominio);
            System.out.println(direccion_condominio);
            System.out.println(score);
            System.out.println(precio_condominio_noche);
            System.out.println(stringifiedservices);
            System.out.println(url);
            System.out.println(status);
            PreparedStatement statement = conn.prepareStatement("UPDATE CONDOMINIOS SET Id_Ubicacion=?,Nombre=?,Puntaje=?,No_Habitaciones=?,Direccion=?,Precio_x_Noche=?,Servicios_Incluidos=?,Imagen_Lugar=?,Status=? WHERE Id=?");
            statement.setInt(1, ubicacion_condominio);
            statement.setString(2, nombre_condominio);
            statement.setInt(3, score);
            statement.setInt(4, Integer.valueOf(habitaciones_condominio));
            statement.setString(5, direccion_condominio);
            statement.setInt(6, precio_condominio_noche);
            statement.setString(7, stringifiedservices);
            statement.setString(8, url);
            statement.setInt(9, status);
            statement.setInt(10, id_condominio);
            
            int statusprocess = statement.executeUpdate();
            
            if (statusprocess == 1) {
                JOptionPane.showMessageDialog(null, "Condominio Actualizado Satisfactoriamente Codigo de Salida 1", "Success", 1);
                updateCondominioObject(ubicacion_condominio);
                cond.image_lugar = url;
                cond.servicios = stringifiedservices;
                jButton4.setForeground(Color.black);
                jButton4.setText("Actualizar Condominio");
                jButton5.setEnabled(true);
                jTextField9.setEnabled(true);
                disableFields();
                
                new Thread(()
                        -> {
                    try {
                        handleCellar(outputpicname);
                    } catch (IOException ex) {
                        Logger.getLogger(Gestion_Administrativa.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }).start();
                
                return;
            }
            
            JOptionPane.showMessageDialog(null, "No se pudo actualizar el condominio Codigo de Error 0", "Error", 0);
            jTextField9.setEnabled(true);
            statement.close();
            conn.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    void updateCondominioObject(int ubicacion_condominio) {
        
        cond.Id = Integer.valueOf(jTextField6.getText());
        cond.nombre = jTextField10.getText();
        cond.precio_x_noche = Integer.valueOf(jTextField11.getText());
        cond.Status = jComboBox9.getSelectedIndex();
        cond.CIF = jTextField13.getText();
        cond.Fecha_Registro = Date.valueOf(jFormattedTextField1.getText());
        cond.Ubicacion = ubicacion_condominio;
        cond.score = Integer.valueOf(jTextField12.getText().substring(0, jTextField12.getText().length() - 1));
        cond.direccion = jTextArea2.getText();
        
        if (jComboBox13.getSelectedItem().toString().contains("+")) {
            cond.No_Habitaciones = Integer.valueOf(jComboBox13.getSelectedItem().toString().substring(0, jComboBox13.getSelectedItem().toString().length() - 1));
            return;
        }
        cond.No_Habitaciones = Integer.valueOf(jComboBox13.getSelectedItem().toString());
        
    }
    
    void handleCellar(String picture) throws IOException {
        
        if (!new_nombre_imagen.isEmpty()) {
            System.out.println("IMAGEN CAMBIO");
            DeleteFromCellar();
            InsertPictureAtCellar(picture);
            nombre_imagen = picture;
            new_nombre_imagen = "";
            picturepath = "";
            
        }
    }
    
    void resetFields() {
        jLabel24.setForeground(Color.black);
        jLabel63.setVisible(false);
        
        jLabel25.setForeground(Color.black);
        jLabel68.setVisible(false);
        
        jLabel30.setForeground(Color.black);
        jLabel64.setVisible(false);
        
        jLabel29.setForeground(Color.black);
        jLabel65.setVisible(false);
        
        jLabel33.setForeground(Color.black);
        jLabel66.setVisible(false);
        
        jLabel34.setForeground(Color.black);
        jLabel67.setVisible(false);
        
    }
    
    void resetFieldsUser() {
        jLabel14.setForeground(Color.black);
        jLabel14.setVisible(false);
        
        jLabel16.setForeground(Color.black);
        jLabel16.setVisible(false);
        
        jLabel17.setForeground(Color.black);
        jLabel17.setVisible(false);
        
        jLabel19.setForeground(Color.black);
        jLabel19.setVisible(false);
        
        jLabel20.setForeground(Color.black);
        jLabel20.setVisible(false);
        
        jLabel70.setForeground(Color.black);
        jLabel70.setVisible(false);
        
        jLabel18.setForeground(Color.black);
        jLabel18.setVisible(false);
        
    }
    
    void disableFields() {
        
        jLabel36.setEnabled(false);
        
        jTextField10.setEditable(false);
        
        jTextField11.setEditable(false);
        
        jComboBox9.setEnabled(false);
        
        jTextField13.setEditable(false);
        
        jTextArea2.setEditable(false);
        
        jTextField12.setEditable(false);
        
        jComboBox14.setEnabled(false);
        
        jCheckBox1.setEnabled(false);
        
        jCheckBox4.setEnabled(false);
        
        jCheckBox5.setEnabled(false);
        
        jCheckBox3.setEnabled(false);
        
        jCheckBox7.setEnabled(false);
        
        jCheckBox6.setEnabled(false);
        jComboBox13.setEnabled(false);
    }
    
    void ClearChanges() {
        jTextField6.setText("");
        jTextField10.setText("");
        jTextField10.setEditable(false);
        jTextField11.setText("");
        jTextField11.setEditable(false);
        jComboBox9.setSelectedItem("");
        jComboBox9.setEnabled(false);
        jTextField13.setText("");
        jTextField13.setEditable(false);
        jFormattedTextField1.setText("");
        jTextArea2.setText("");
        jTextArea2.setEditable(false);
        
        jTextField12.setText("");
        jTextField12.setEditable(false);
        
        jComboBox14.setSelectedItem("");
        jComboBox14.setEnabled(false);
        
        jCheckBox1.setSelected(false);
        jCheckBox1.setEnabled(false);
        
        jCheckBox4.setSelected(false);
        jCheckBox4.setEnabled(false);
        
        jCheckBox5.setSelected(false);
        jCheckBox5.setEnabled(false);
        
        jCheckBox3.setSelected(false);
        jCheckBox3.setEnabled(false);
        
        jCheckBox7.setSelected(false);
        jCheckBox7.setEnabled(false);
        
        jCheckBox6.setSelected(false);
        jCheckBox6.setEnabled(false);
        
        jComboBox13.setSelectedItem("");
        jComboBox13.setEnabled(false);
        
        jLabel36.setEnabled(false);
        
        new_nombre_imagen = "";
        picturepath = "";
        
        try {
            
            imagen_condominio = new URL("https://lookingtel.cellar-c2.services.clever-cloud.com/user_pic.png");
            Image condominio_image = ImageIO.read(imagen_condominio);
            
            Image scaledImage_condominio = condominio_image.getScaledInstance(jPanel6.getWidth(),
                    jPanel6.getHeight(),
                    Image.SCALE_SMOOTH);
            
            ImageIcon icon_userpic = new ImageIcon(scaledImage_condominio);
            
            jLabel7.setIcon(icon_userpic);
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(Gestion_Administrativa.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Gestion_Administrativa.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        
        jLabel36.setEnabled(false);
        
    }
    
    void enableFields() {
        
        jTextField10.setEditable(true);
        jTextField11.setEditable(true);
        jComboBox9.setEnabled(true);
        jTextField13.setEditable(true);
        jComboBox14.setEnabled(true);
        jComboBox13.setEnabled(true);
        jTextArea2.setEditable(true);
        jCheckBox1.setEnabled(true);
        jCheckBox4.setEnabled(true);
        jCheckBox5.setEnabled(true);
        jCheckBox6.setEnabled(true);
        jCheckBox7.setEnabled(true);
        jCheckBox3.setEnabled(true);
        jLabel36.setEnabled(true);
        
    }
    
    private static boolean validate(String text, int minlength, int maxlength, String regular_exp, JLabel lbl, boolean ignore_length, JLabel errorlblmsg) {

        // validate the data type
        if (!text.matches(regular_exp)) {
            
            lbl.setForeground(Color.red);
            errorlblmsg.setVisible(true);
            errorlblmsg.setText("Error " + lbl.getText().substring(0, lbl.getText().length() - 1) + " no Valido");
            return false;
            
        }
        
        if (ignore_length) {
            lbl.setForeground(Color.black);
            errorlblmsg.setVisible(false);
            return true;
        }

        // validate the length
        if (text.length() < minlength || text.length() > maxlength) {
            
            lbl.setForeground(Color.red);
            errorlblmsg.setVisible(true);
            errorlblmsg.setText("Longitud de " + lbl.getText() + " invalida");
            JOptionPane.showMessageDialog(null, "La longitud mininima deben de ser: " + minlength + " caracteres en: " + lbl.getText().substring(0, lbl.getText().length() - 1) + "\n"
                    + "La longitud maxima deben de ser: " + maxlength + " caracteres en: " + lbl.getText().substring(0, lbl.getText().length() - 1), "Aviso", 0);
            
            return false;
        }
        
        lbl.setForeground(Color.black);
        errorlblmsg.setVisible(false);
        return true;
        
    }
    
    void validarCondominio() {

        // creamos las estructuras de datos para gestionar los textfields y los checkboxes
        List<Boolean> passedstatuses = new ArrayList<Boolean>();
        ArrayList<String> services = new ArrayList<String>();

        // verificamos que los textfields cumplan con la longitud y los caracteres posibles
        passedstatuses.add(validate(jTextField10.getText(), 5, 45, ValidName, jLabel24, false, jLabel63));
        
        passedstatuses.add(validate(jTextArea2.getText(), 30, 65, direccion, jLabel29, false, jLabel65));
        
        passedstatuses.add(validate(jTextField11.getText(), 2, 3, numbersonly, jLabel25, false, jLabel68));
        
        passedstatuses.add(validate(jTextField13.getText(), 10, 10, capitallettersandnumbers, jLabel30, false, jLabel64));

        // validamos que al menos un solo checkbox haya sido seleccionado 
        boolean atleastone = false;
        
        if (jCheckBox1.isSelected()) {
            services.add(jCheckBox1.getText());
            atleastone = true;
        }
        if (jCheckBox3.isSelected()) {
            services.add(jCheckBox3.getText());
            atleastone = true;
        }
        if (jCheckBox4.isSelected()) {
            services.add(jCheckBox4.getText());
            atleastone = true;
        }
        if (jCheckBox5.isSelected()) {
            services.add(jCheckBox5.getText());
            atleastone = true;
        }
        if (jCheckBox6.isSelected()) {
            services.add(jCheckBox6.getText());
            atleastone = true;
        }
        if (jCheckBox7.isSelected()) {
            services.add(jCheckBox7.getText());
            atleastone = true;
        }

        // si no se selecciono ningun checkbox marcamos el error para que sea visto por el usuario
        if (!atleastone) {
            jLabel66.setText("No se selecciono al menos un servicio");
            jLabel66.setVisible(true);
        } else {
            jLabel66.setVisible(false);
        }
        
        if (!atleastone) {
            return;
        }
        if (passedstatuses.contains(false)) {
            return;
        }

        // obtenemos la llave primaria de la ubicacion en base al pais,estado y ciudad que es el valor
        String location = (String) jComboBox14.getSelectedItem();
        int key = 0;
        for (Map.Entry<Integer, String> entry : Ubicacion.entrySet()) {
            if (entry.getValue().equals(location)) {
                key = entry.getKey();
                System.out.println("The key is: " + key);
                break;
            }
        }
        
        try {
            // handle method registrar condominio
            actualizar_condominio(key, services);
        } catch (IOException ex) {
            Logger.getLogger(Registrar_Condominio.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    void displaylogos() {
        try {
            // Load the image from the URL
            URL logout_pic = new URL("https://lookingtel.cellar-c2.services.clever-cloud.com/logout_icon.png");
            URL lookingtel = new URL("https://lookingtel.cellar-c2.services.clever-cloud.com/lookingtel.png");
            URL hr_line = new URL("https://lookingtel.cellar-c2.services.clever-cloud.com/hr_line.png");
            imagen_condominio = new URL("https://lookingtel.cellar-c2.services.clever-cloud.com/user_pic.png");
            URL add_button = new URL("https://lookingtel.cellar-c2.services.clever-cloud.com/add_button.png");
            Image logout_pic_image = ImageIO.read(logout_pic);
            Image lookingtel_image = ImageIO.read(lookingtel);
            Image hr_line_image = ImageIO.read(hr_line);
            Image condominio_image = ImageIO.read(imagen_condominio);
            Image add_button_image = ImageIO.read(add_button);
            
            Image scaledImage_logoutpic = logout_pic_image.getScaledInstance(45, 50,
                    Image.SCALE_SMOOTH);
            Image scaledImage_lookingtel = lookingtel_image.getScaledInstance(jPanel7.getWidth(),
                    jPanel7.getHeight(),
                    Image.SCALE_SMOOTH);
            Image scaledImage_hr_line = hr_line_image.getScaledInstance(815, 3,
                    Image.SCALE_SMOOTH);
            Image scaledImage_condominio = condominio_image.getScaledInstance(jPanel6.getWidth(),
                    jPanel6.getHeight(),
                    Image.SCALE_SMOOTH);
            Image scaledImage_add_button_image = add_button_image.getScaledInstance(jPanel9.getWidth(),
                    jPanel9.getHeight(),
                    Image.SCALE_SMOOTH);

            // Create an ImageIcon from the image
            ImageIcon icon_logoutpic = new ImageIcon(scaledImage_logoutpic);
            ImageIcon icon_lookingtel = new ImageIcon(scaledImage_lookingtel);
            ImageIcon icon_hr_line = new ImageIcon(scaledImage_hr_line);
            ImageIcon icon_userpic = new ImageIcon(scaledImage_condominio);
            ImageIcon icon_add_button = new ImageIcon(scaledImage_add_button_image);

            // Set the icon on the JLabel
            jLabel6.setIcon(icon_logoutpic);
            jLabel9.setIcon(icon_lookingtel);
            jLabel10.setIcon(icon_hr_line);
            jLabel22.setIcon(icon_hr_line);
            jLabel7.setIcon(icon_userpic);
            jLabel36.setIcon(icon_add_button);
            jLabel37.setIcon(icon_hr_line);
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    void DeleteFromCellar() {
        
        try {
            
            List<String> command = new ArrayList<>();
            command.add("cmd.exe");
            command.add("/c");
            command.add("aws s3api delete-object --bucket lookingtel --key " + "\"" + nombre_imagen + "\"" + " --endpoint-url https://cellar-c2.services.clever-cloud.com");
            System.out.println(command);
            ProcessBuilder builder = new ProcessBuilder(command);
            builder.redirectErrorStream(true);
            Process process = builder.start();
            InputStream inputStream = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
            System.out.println("Imagen eliminada con exito");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
    void InsertPictureAtCellar(String picture) throws IOException {

        // scale image
        File initialpicture = new File(picturepath);
        
        File finalpicture = new File(picturepath);
        
        BufferedImage originalImage = ImageIO.read(initialpicture);
        
        BufferedImage resizedImage = new BufferedImage(jLabel7.getWidth(), jLabel7.getHeight(), originalImage.getType());
        
        Graphics2D g2d = resizedImage.createGraphics();

        // Scale the image to fit the new dimensions
        double scaleX = (double) jLabel7.getWidth() / originalImage.getWidth();
        double scaleY = (double) jLabel7.getHeight() / originalImage.getHeight();
        g2d.scale(scaleX, scaleY);

        // Draw the original image onto the new BufferedImage
        g2d.drawImage(originalImage, 0, 0, null);

        // Dispose of the Graphics2D object
        g2d.dispose();

        // Save the new resized image
        ImageIO.write(resizedImage, picture.substring(picture.lastIndexOf(".") + 1), finalpicture);
        
        try {
            
            List<String> command = new ArrayList<>();
            command.add("cmd.exe");
            command.add("/c");
            command.add("aws s3api put-object --bucket lookingtel --key " + "\"" + picture + "\"" + " --body " + "\"" + picturepath + "\"" + " --acl public-read --endpoint-url https://cellar-c2.services.clever-cloud.com");
            System.out.println(command);
            ProcessBuilder builder = new ProcessBuilder(command);
            builder.redirectErrorStream(true);
            Process process = builder.start();
            InputStream inputStream = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jLabel4 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jTextField16 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        jLabel62 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLayeredPane3 = new javax.swing.JLayeredPane();
        jLabel12 = new javax.swing.JLabel();
        jComboBox123 = new javax.swing.JComboBox<>();
        jLayeredPane4 = new javax.swing.JLayeredPane();
        jLabel8 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLayeredPane5 = new javax.swing.JLayeredPane();
        jLabel69 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jButton6 = new javax.swing.JButton();
        jLabel70 = new javax.swing.JLabel();
        jLayeredPane7 = new javax.swing.JLayeredPane();
        jTextField3 = new javax.swing.JTextField();
        jLabel71 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLayeredPane8 = new javax.swing.JLayeredPane();
        jLabel72 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLayeredPane9 = new javax.swing.JLayeredPane();
        jLabel11 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLayeredPane10 = new javax.swing.JLayeredPane();
        jLabel15 = new javax.swing.JLabel();
        jPasswordField2 = new javax.swing.JPasswordField();
        jButton9 = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        jLayeredPane6 = new javax.swing.JLayeredPane();
        jLabel73 = new javax.swing.JLabel();
        jTextField17 = new javax.swing.JTextField();
        jLayeredPane12 = new javax.swing.JLayeredPane();
        jLabel75 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jLayeredPane11 = new javax.swing.JLayeredPane();
        jLabel74 = new javax.swing.JLabel();
        jTextField18 = new javax.swing.JTextField();
        jButton11 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jTextField9 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        jComboBox9 = new javax.swing.JComboBox<>();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jTextField12 = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jComboBox13 = new javax.swing.JComboBox<>();
        jComboBox14 = new javax.swing.JComboBox<>();
        jLabel32 = new javax.swing.JLabel();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jLabel2 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jLabel63 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jLabel68 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jTextField13 = new javax.swing.JTextField();
        jLabel64 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jLabel65 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jCheckBox5 = new javax.swing.JCheckBox();
        jCheckBox6 = new javax.swing.JCheckBox();
        jCheckBox7 = new javax.swing.JCheckBox();
        jLabel66 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jLabel35 = new javax.swing.JLabel();
        jTextField14 = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jTextField15 = new javax.swing.JTextField();
        jLabel55 = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner();
        jLabel56 = new javax.swing.JLabel();
        jSpinner2 = new javax.swing.JSpinner();
        jLabel57 = new javax.swing.JLabel();
        jComboBox16 = new javax.swing.JComboBox<>();
        jComboBox17 = new javax.swing.JComboBox<>();
        jComboBox18 = new javax.swing.JComboBox<>();
        jLabel58 = new javax.swing.JLabel();
        jComboBox19 = new javax.swing.JComboBox<>();
        jComboBox20 = new javax.swing.JComboBox<>();
        jComboBox21 = new javax.swing.JComboBox<>();
        jLabel59 = new javax.swing.JLabel();
        jComboBox22 = new javax.swing.JComboBox<>();
        jComboBox23 = new javax.swing.JComboBox<>();
        jComboBox24 = new javax.swing.JComboBox<>();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestion Administrativa");
        setPreferredSize(new java.awt.Dimension(950, 640));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Comic Sans MS", 0, 36)); // NOI18N
        jLabel4.setText("Gestionar Usuarios");

        jTabbedPane1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jTabbedPane1.setToolTipText("");
        jTabbedPane1.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(850, 500));
        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });

        jPanel1.setPreferredSize(new java.awt.Dimension(850, 500));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jLabel1.setText("Filtrar Por:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 10, -1, 24));

        jButton1.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jButton1.setText("Actualizar Usuario");
        jButton1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, 182, 35));

        jTextField1.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(102, 102, 102));
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setText("Id Usuario o email");
        jTextField1.setAlignmentX(0.9F);
        jTextField1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        jTextField1.setMargin(new java.awt.Insets(12, 6, 2, 6));
        jTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField1FocusLost(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 10, 245, -1));
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, 840, 10));

        jLayeredPane1.setPreferredSize(new java.awt.Dimension(197, 87));
        jLayeredPane1.setLayout(new java.awt.GridBagLayout());

        jTextField16.setEditable(false);
        jTextField16.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jTextField16.setForeground(new java.awt.Color(0, 0, 0));
        jTextField16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        jTextField16.setCaretPosition(0);
        jTextField16.setEnabled(false);
        jTextField16.setPreferredSize(new java.awt.Dimension(182, 24));
        jTextField16.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField16FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField16FocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        jLayeredPane1.add(jTextField16, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel3.setText("Nombre:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jLayeredPane1.add(jLabel3, gridBagConstraints);

        jLabel14.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 51, 51));
        jLabel14.setText("Caracteres invalidos introducidos");
        jLabel14.setAlignmentY(0.0F);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jLayeredPane1.add(jLabel14, gridBagConstraints);

        jPanel1.add(jLayeredPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 152, 239, 69));

        jLayeredPane2.setPreferredSize(new java.awt.Dimension(200, 93));
        jLayeredPane2.setLayout(new java.awt.GridBagLayout());

        jLabel62.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel62.setText("Dirección:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jLayeredPane2.add(jLabel62, gridBagConstraints);

        jLabel18.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 51, 51));
        jLabel18.setText("Longitud de direcccion muy corta muy larga");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jLayeredPane2.add(jLabel18, gridBagConstraints);

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setAutoscrolls(true);
        jScrollPane1.setEnabled(false);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(200, 80));
        jScrollPane1.setRequestFocusEnabled(false);

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(17);
        jTextArea1.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jTextArea1.setForeground(new java.awt.Color(0, 0, 0));
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setTabSize(5);
        jTextArea1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        jTextArea1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTextArea1.setEnabled(false);
        jTextArea1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jTextArea1.setMinimumSize(new java.awt.Dimension(5, 5));
        jScrollPane1.setViewportView(jTextArea1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jLayeredPane2.add(jScrollPane1, gridBagConstraints);

        jPanel1.add(jLayeredPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 80, 245, 131));

        jLayeredPane3.setPreferredSize(new java.awt.Dimension(76, 23));
        java.awt.GridBagLayout jLayeredPane3Layout = new java.awt.GridBagLayout();
        jLayeredPane3Layout.columnWidths = new int[] {0};
        jLayeredPane3Layout.rowHeights = new int[] {0, 0, 0};
        jLayeredPane3.setLayout(jLayeredPane3Layout);

        jLabel12.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel12.setText("Sexo:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jLayeredPane3.add(jLabel12, gridBagConstraints);

        jComboBox123.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        jComboBox123.setForeground(new java.awt.Color(0, 0, 0));
        jComboBox123.setMaximumRowCount(2);
        jComboBox123.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "M", "F" }));
        jComboBox123.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        jComboBox123.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jLayeredPane3.add(jComboBox123, gridBagConstraints);

        jPanel1.add(jLayeredPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 330, 60, 70));

        jLayeredPane4.setLayout(new java.awt.GridBagLayout());

        jLabel8.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel8.setText("Telefono:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.RELATIVE;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jLayeredPane4.add(jLabel8, gridBagConstraints);

        jTextField2.setEditable(false);
        jTextField2.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jTextField2.setForeground(new java.awt.Color(0, 0, 0));
        jTextField2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        jTextField2.setEnabled(false);
        jTextField2.setPreferredSize(new java.awt.Dimension(80, 23));
        jTextField2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField2FocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.RELATIVE;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jLayeredPane4.add(jTextField2, gridBagConstraints);

        jLabel16.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 51, 51));
        jLabel16.setText("Longitud de Telefono muy corta o muy larga");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.RELATIVE;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        jLayeredPane4.add(jLabel16, gridBagConstraints);

        jPanel1.add(jLayeredPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, -1, -1));

        jLayeredPane5.setLayout(new java.awt.GridBagLayout());

        jLabel69.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel69.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel69.setText("Confirmar Contraseña:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jLayeredPane5.add(jLabel69, gridBagConstraints);

        jPasswordField1.setEditable(false);
        jPasswordField1.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        jPasswordField1.setForeground(new java.awt.Color(0, 0, 0));
        jPasswordField1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        jPasswordField1.setEnabled(false);
        jPasswordField1.setMinimumSize(new java.awt.Dimension(100, 24));
        jPasswordField1.setPreferredSize(new java.awt.Dimension(100, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jLayeredPane5.add(jPasswordField1, gridBagConstraints);

        jButton6.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jButton6.setText("S");
        jButton6.setToolTipText("");
        jButton6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton6.setPreferredSize(new java.awt.Dimension(23, 23));
        jButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton6MousePressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jLayeredPane5.add(jButton6, gridBagConstraints);

        jLabel70.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jLabel70.setForeground(new java.awt.Color(255, 51, 51));
        jLabel70.setText("Porfavor vuelve a");
        jLabel70.setAlignmentY(0.0F);
        jLabel70.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel70.setMaximumSize(new java.awt.Dimension(95, 20));
        jLabel70.setMinimumSize(new java.awt.Dimension(150, 20));
        jLabel70.setPreferredSize(new java.awt.Dimension(150, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jLayeredPane5.add(jLabel70, gridBagConstraints);

        jPanel1.add(jLayeredPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 360, -1, -1));

        jLayeredPane7.setLayout(new java.awt.GridBagLayout());

        jTextField3.setEditable(false);
        jTextField3.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jTextField3.setForeground(new java.awt.Color(0, 0, 0));
        jTextField3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        jTextField3.setCaretPosition(0);
        jTextField3.setEnabled(false);
        jTextField3.setPreferredSize(new java.awt.Dimension(182, 24));
        jTextField3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField3FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField3FocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jLayeredPane7.add(jTextField3, gridBagConstraints);

        jLabel71.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel71.setText("Email:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.RELATIVE;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jLayeredPane7.add(jLabel71, gridBagConstraints);

        jLabel19.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 51, 51));
        jLabel19.setText("Caracteres invalidos introducidos");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.RELATIVE;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        jLayeredPane7.add(jLabel19, gridBagConstraints);

        jPanel1.add(jLayeredPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 150, -1, -1));

        jLayeredPane8.setLayout(new java.awt.GridBagLayout());

        jLabel72.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel72.setText("Ubicación:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jLayeredPane8.add(jLabel72, gridBagConstraints);

        jComboBox1.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jComboBox1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        jComboBox1.setEnabled(false);
        jComboBox1.setPreferredSize(new java.awt.Dimension(220, 28));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jLayeredPane8.add(jComboBox1, gridBagConstraints);

        jPanel1.add(jLayeredPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 240, -1, 62));

        jLayeredPane9.setLayout(new java.awt.GridBagLayout());

        jLabel11.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel11.setText("Fecha De Nacimiento:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jLayeredPane9.add(jLabel11, gridBagConstraints);

        jLabel17.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 51, 51));
        jLabel17.setText("Caracteres invalidos introducidos");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        jLayeredPane9.add(jLabel17, gridBagConstraints);

        jDateChooser1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        jDateChooser1.setDateFormatString("yyyy/MM/dd");
        jDateChooser1.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        jDateChooser1.setEnabled(false);
        jDateChooser1.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jDateChooser1.setMaxSelectableDate(new java.util.Date(1136098909000L));
        jDateChooser1.setMinSelectableDate(new java.util.Date(-1514735891000L));
        jDateChooser1.setMinimumSize(new java.awt.Dimension(150, 30));
        jDateChooser1.setPreferredSize(new java.awt.Dimension(150, 28));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jLayeredPane9.add(jDateChooser1, gridBagConstraints);

        jPanel1.add(jLayeredPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 394, -1, 102));

        jLayeredPane10.setLayout(new java.awt.GridBagLayout());

        jLabel15.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel15.setText("Contraseña:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jLayeredPane10.add(jLabel15, gridBagConstraints);

        jPasswordField2.setEditable(false);
        jPasswordField2.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        jPasswordField2.setForeground(new java.awt.Color(0, 0, 0));
        jPasswordField2.setToolTipText("");
        jPasswordField2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        jPasswordField2.setEnabled(false);
        jPasswordField2.setMinimumSize(new java.awt.Dimension(100, 24));
        jPasswordField2.setPreferredSize(new java.awt.Dimension(100, 24));
        jPasswordField2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jPasswordField2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jPasswordField2FocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jLayeredPane10.add(jPasswordField2, gridBagConstraints);

        jButton9.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jButton9.setText("S");
        jButton9.setToolTipText("");
        jButton9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        jButton9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton9.setPreferredSize(new java.awt.Dimension(23, 23));
        jButton9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton9MousePressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jLayeredPane10.add(jButton9, gridBagConstraints);

        jLabel20.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 51, 51));
        jLabel20.setText("Contraseña muy");
        jLabel20.setAlignmentY(0.0F);
        jLabel20.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel20.setMinimumSize(new java.awt.Dimension(100, 20));
        jLabel20.setPreferredSize(new java.awt.Dimension(100, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jLayeredPane10.add(jLabel20, gridBagConstraints);

        jPanel1.add(jLayeredPane10, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 240, -1, -1));

        java.awt.GridBagLayout jLayeredPane6Layout = new java.awt.GridBagLayout();
        jLayeredPane6Layout.columnWidths = new int[] {239};
        jLayeredPane6Layout.rowHeights = new int[] {0, 0, 0};
        jLayeredPane6.setLayout(jLayeredPane6Layout);

        jLabel73.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel73.setText("Id");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.RELATIVE;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jLayeredPane6.add(jLabel73, gridBagConstraints);

        jTextField17.setEditable(false);
        jTextField17.setColumns(3);
        jTextField17.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jTextField17.setForeground(new java.awt.Color(0, 0, 0));
        jTextField17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        jTextField17.setEnabled(false);
        jTextField17.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField17FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField17FocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.RELATIVE;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jLayeredPane6.add(jTextField17, gridBagConstraints);

        jPanel1.add(jLayeredPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 77, -1, -1));

        jLayeredPane12.setEnabled(false);
        java.awt.GridBagLayout jLayeredPane12Layout = new java.awt.GridBagLayout();
        jLayeredPane12Layout.columnWidths = new int[] {150};
        jLayeredPane12Layout.rowHeights = new int[] {3, 3, 0};
        jLayeredPane12.setLayout(jLayeredPane12Layout);

        jLabel75.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel75.setText("Fecha De Registro:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jLayeredPane12.add(jLabel75, gridBagConstraints);

        jDateChooser2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 6, true));
        jDateChooser2.setDateFormatString("yyyy/MM/dd");
        jDateChooser2.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        jDateChooser2.setEnabled(false);
        jDateChooser2.setFocusable(false);
        jDateChooser2.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jDateChooser2.setMaxSelectableDate(new java.util.Date(1136098909000L));
        jDateChooser2.setMinSelectableDate(new java.util.Date(-1514735891000L));
        jDateChooser2.setMinimumSize(new java.awt.Dimension(150, 30));
        jDateChooser2.setPreferredSize(new java.awt.Dimension(150, 28));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jLayeredPane12.add(jDateChooser2, gridBagConstraints);

        jPanel1.add(jLayeredPane12, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 227, -1, 62));

        java.awt.GridBagLayout jLayeredPane11Layout = new java.awt.GridBagLayout();
        jLayeredPane11Layout.columnWidths = new int[] {239};
        jLayeredPane11Layout.rowHeights = new int[] {0, 0, 0};
        jLayeredPane11.setLayout(jLayeredPane11Layout);

        jLabel74.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel74.setText("Edad:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.RELATIVE;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jLayeredPane11.add(jLabel74, gridBagConstraints);

        jTextField18.setEditable(false);
        jTextField18.setColumns(4);
        jTextField18.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jTextField18.setForeground(new java.awt.Color(0, 0, 0));
        jTextField18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        jTextField18.setEnabled(false);
        jTextField18.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField18FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField18FocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.RELATIVE;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jLayeredPane11.add(jTextField18, gridBagConstraints);

        jPanel1.add(jLayeredPane11, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 80, -1, -1));

        jButton11.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jButton11.setText("Borrar Usuario");
        jButton11.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        jButton11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton11MousePressed(evt);
            }
        });
        jPanel1.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(194, 6, 182, 35));

        jTabbedPane1.addTab("Usuarios", jPanel1);

        jButton3.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jButton3.setText("Registrar Condominio");
        jButton3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton3MousePressed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jButton4.setText("Actualizar Condominio");
        jButton4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton4MousePressed(evt);
            }
        });
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jTextField9.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jTextField9.setForeground(new java.awt.Color(102, 102, 102));
        jTextField9.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField9.setText("Id Condominio o CIF");
        jTextField9.setAlignmentX(0.9F);
        jTextField9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        jTextField9.setMargin(new java.awt.Insets(12, 6, 2, 6));
        jTextField9.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField9FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField9FocusLost(evt);
            }
        });
        jTextField9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField9KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField9KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField9KeyTyped(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jLabel21.setText("Filtrar Por:");

        jButton5.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jButton5.setText("Borrar Condominio");
        jButton5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton5MousePressed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jLabel26.setText("Status:");

        jComboBox9.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jComboBox9.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "No Disponible", "Disponible" }));
        jComboBox9.setSelectedIndex(1);
        jComboBox9.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        jComboBox9.setEnabled(false);
        jComboBox9.setFocusable(false);

        jLabel27.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jLabel27.setText("Fecha Registro:");

        jLabel28.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jLabel28.setText("Puntaje:");

        jTextField12.setEditable(false);
        jTextField12.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jTextField12.setForeground(new java.awt.Color(0, 0, 0));
        jTextField12.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField12.setAlignmentX(0.9F);
        jTextField12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        jTextField12.setEnabled(false);
        jTextField12.setFocusable(false);
        jTextField12.setMargin(new java.awt.Insets(12, 6, 2, 6));

        jLabel31.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jLabel31.setText("No.Habitaciones:");

        jComboBox13.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jComboBox13.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10+" }));
        jComboBox13.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        jComboBox13.setEnabled(false);
        jComboBox13.setFocusable(false);

        jComboBox14.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jComboBox14.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        jComboBox14.setEnabled(false);
        jComboBox14.setFocusable(false);

        jLabel32.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jLabel32.setText("Ubicación:");

        jFormattedTextField1.setEditable(false);
        jFormattedTextField1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        jFormattedTextField1.setForeground(new java.awt.Color(0, 0, 0));
        jFormattedTextField1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("yyyy-MM-dd"))));
        jFormattedTextField1.setEnabled(false);
        jFormattedTextField1.setFocusable(false);
        jFormattedTextField1.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jFormattedTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextField1ActionPerformed(evt);
            }
        });

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("0");

        java.awt.GridBagLayout jPanel8Layout = new java.awt.GridBagLayout();
        jPanel8Layout.columnWidths = new int[] {50};
        jPanel8.setLayout(jPanel8Layout);

        jLabel23.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jLabel23.setText("Id:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel8.add(jLabel23, gridBagConstraints);

        jTextField6.setEditable(false);
        jTextField6.setColumns(3);
        jTextField6.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jTextField6.setForeground(new java.awt.Color(0, 0, 0));
        jTextField6.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTextField6.setAlignmentX(0.9F);
        jTextField6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        jTextField6.setEnabled(false);
        jTextField6.setFocusable(false);
        jTextField6.setMargin(new java.awt.Insets(12, 6, 2, 6));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel8.add(jTextField6, gridBagConstraints);

        java.awt.GridBagLayout jPanel10Layout = new java.awt.GridBagLayout();
        jPanel10Layout.columnWidths = new int[] {288};
        jPanel10Layout.rowHeights = new int[] {20, 20, 20};
        jPanel10.setLayout(jPanel10Layout);

        jLabel24.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jLabel24.setText("Nombre:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel10.add(jLabel24, gridBagConstraints);

        jTextField10.setEditable(false);
        jTextField10.setColumns(25);
        jTextField10.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jTextField10.setForeground(new java.awt.Color(0, 0, 0));
        jTextField10.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField10.setAlignmentX(0.9F);
        jTextField10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        jTextField10.setMargin(new java.awt.Insets(12, 6, 2, 6));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel10.add(jTextField10, gridBagConstraints);

        jLabel63.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(255, 51, 51));
        jLabel63.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel63.setText("Caracteres invalidos introducidos");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel10.add(jLabel63, gridBagConstraints);

        java.awt.GridBagLayout jPanel11Layout = new java.awt.GridBagLayout();
        jPanel11Layout.columnWidths = new int[] {288};
        jPanel11Layout.rowHeights = new int[] {20, 20, 20};
        jPanel11.setLayout(jPanel11Layout);

        jLabel25.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jLabel25.setText("$ Precio x Noche:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel11.add(jLabel25, gridBagConstraints);

        jTextField11.setEditable(false);
        jTextField11.setColumns(5);
        jTextField11.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jTextField11.setForeground(new java.awt.Color(0, 0, 0));
        jTextField11.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField11.setAlignmentX(0.9F);
        jTextField11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        jTextField11.setMargin(new java.awt.Insets(12, 6, 2, 6));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel11.add(jTextField11, gridBagConstraints);

        jLabel68.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jLabel68.setForeground(new java.awt.Color(255, 51, 51));
        jLabel68.setText("Caracteres invalidos introducidos");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel11.add(jLabel68, gridBagConstraints);

        java.awt.GridBagLayout jPanel12Layout = new java.awt.GridBagLayout();
        jPanel12Layout.columnWidths = new int[] {288};
        jPanel12Layout.rowHeights = new int[] {20, 20, 20};
        jPanel12.setLayout(jPanel12Layout);

        jLabel30.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jLabel30.setText("CIF:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel12.add(jLabel30, gridBagConstraints);

        jTextField13.setEditable(false);
        jTextField13.setColumns(10);
        jTextField13.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jTextField13.setForeground(new java.awt.Color(0, 0, 0));
        jTextField13.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField13.setAlignmentX(0.9F);
        jTextField13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        jTextField13.setEnabled(false);
        jTextField13.setMargin(new java.awt.Insets(12, 6, 2, 6));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel12.add(jTextField13, gridBagConstraints);

        jLabel64.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jLabel64.setForeground(new java.awt.Color(255, 51, 51));
        jLabel64.setText("Longitud de \" + lbl.getText() + \" muy corta o muy larga");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel12.add(jLabel64, gridBagConstraints);

        jPanel13.setLayout(new java.awt.GridBagLayout());

        jLabel29.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jLabel29.setText("Direccción:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel13.add(jLabel29, gridBagConstraints);

        jTextArea2.setEditable(false);
        jTextArea2.setColumns(23);
        jTextArea2.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jTextArea2.setForeground(new java.awt.Color(0, 0, 0));
        jTextArea2.setLineWrap(true);
        jTextArea2.setRows(5);
        jTextArea2.setTabSize(1);
        jTextArea2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        jScrollPane2.setViewportView(jTextArea2);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel13.add(jScrollPane2, gridBagConstraints);

        jLabel65.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(255, 51, 51));
        jLabel65.setText("Longitud de \" + lbl.getText() + \" muy corta o muy larga");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel13.add(jLabel65, gridBagConstraints);

        java.awt.GridBagLayout jPanel14Layout = new java.awt.GridBagLayout();
        jPanel14Layout.rowHeights = new int[] {20, 20, 20};
        jPanel14.setLayout(jPanel14Layout);

        jLabel33.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jLabel33.setText("Servicios Incluidos:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel14.add(jLabel33, gridBagConstraints);

        jPanel5.setFocusable(false);

        jCheckBox1.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jCheckBox1.setText("Agua");
        jCheckBox1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        jCheckBox1.setEnabled(false);
        jCheckBox1.setFocusable(false);
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jCheckBox3.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jCheckBox3.setText("Luz");
        jCheckBox3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        jCheckBox3.setEnabled(false);
        jCheckBox3.setFocusable(false);
        jCheckBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox3ActionPerformed(evt);
            }
        });

        jCheckBox4.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jCheckBox4.setText("Gas");
        jCheckBox4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        jCheckBox4.setEnabled(false);
        jCheckBox4.setFocusable(false);
        jCheckBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox4ActionPerformed(evt);
            }
        });

        jCheckBox5.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jCheckBox5.setText("Internet");
        jCheckBox5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        jCheckBox5.setEnabled(false);
        jCheckBox5.setFocusable(false);
        jCheckBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox5ActionPerformed(evt);
            }
        });

        jCheckBox6.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jCheckBox6.setText("Televisión");
        jCheckBox6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        jCheckBox6.setEnabled(false);
        jCheckBox6.setFocusable(false);
        jCheckBox6.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jCheckBox6FocusLost(evt);
            }
        });
        jCheckBox6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox6ActionPerformed(evt);
            }
        });

        jCheckBox7.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jCheckBox7.setText("Cocina");
        jCheckBox7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        jCheckBox7.setEnabled(false);
        jCheckBox7.setFocusable(false);
        jCheckBox7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jCheckBox1)
                        .addGap(18, 18, 18)
                        .addComponent(jCheckBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBox5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBox3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(12, 12, 12))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jCheckBox7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBox6, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox1)
                    .addComponent(jCheckBox4)
                    .addComponent(jCheckBox5)
                    .addComponent(jCheckBox3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBox7)
                    .addComponent(jCheckBox6))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        jPanel14.add(jPanel5, gridBagConstraints);

        jLabel66.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(255, 51, 51));
        jLabel66.setText("No se selecciono al menos un servicio");
        jLabel66.setIconTextGap(2);
        jLabel66.setPreferredSize(new java.awt.Dimension(201, 10));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        jPanel14.add(jLabel66, gridBagConstraints);

        jPanel15.setLayout(new java.awt.GridBagLayout());

        jLabel34.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jLabel34.setText("Imagen del lugar:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel15.add(jLabel34, gridBagConstraints);

        jLabel67.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(255, 51, 51));
        jLabel67.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel67.setText("No se selecciono ninguna imagen");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        jPanel15.add(jLabel67, gridBagConstraints);

        jPanel9.setFocusable(false);
        jPanel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel9MouseClicked(evt);
            }
        });

        jLabel36.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel36.setEnabled(false);
        jLabel36.setFocusable(false);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        jPanel15.add(jPanel9, gridBagConstraints);

        jPanel6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        jPanel6.setPreferredSize(new java.awt.Dimension(310, 174));
        jPanel6.setLayout(new java.awt.GridBagLayout());

        jLabel7.setBackground(new java.awt.Color(255, 204, 153));
        jLabel7.setForeground(new java.awt.Color(51, 255, 51));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setAlignmentY(0.0F);
        jLabel7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel7.setIconTextGap(0);
        jLabel7.setPreferredSize(new java.awt.Dimension(310, 174));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel6.add(jLabel7, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        jPanel15.add(jPanel6, gridBagConstraints);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox9, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel26))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel28)
                                            .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(59, 59, 59)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel31)
                                            .addComponent(jComboBox13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBox14, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel32)
                                    .addComponent(jLabel27)
                                    .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(14, 14, 14)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(36, 36, 36))
                                            .addComponent(jComboBox13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addGap(16, 16, 16)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(12, 12, 12)
                                                .addComponent(jComboBox14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 19, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(23, Short.MAX_VALUE))))
        );

        jTabbedPane1.addTab("Condominios", jPanel2);

        jButton7.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jButton7.setText("Actualizar Reservación");
        jButton7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jButton8.setText("Eliminar Reservación");
        jButton8.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jLabel35.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jLabel35.setText("Filtrar Por:");

        jTextField14.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jTextField14.setForeground(new java.awt.Color(102, 102, 102));
        jTextField14.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField14.setText("CIF Condominio");
        jTextField14.setAlignmentX(0.9F);
        jTextField14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        jTextField14.setMargin(new java.awt.Insets(12, 6, 2, 6));

        jLabel39.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        jLabel39.setText("_______________________");

        jLabel40.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jLabel40.setText("Nombre del Condominio:");

        jLabel41.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jLabel41.setText("Dirección del Condominio:");

        jLabel42.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        jLabel42.setText("________________________");

        jLabel43.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jLabel43.setText("Precio x Noche del Condominio:");

        jLabel44.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        jLabel44.setText("________________________");

        jLabel45.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jLabel45.setText("CIF:");

        jLabel46.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        jLabel46.setText("________________________");

        jLabel47.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jLabel47.setText("Ubicación:");

        jLabel48.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        jLabel48.setText("________________________");

        jLabel49.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jLabel49.setText("Nombre Usuario:");

        jLabel50.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        jLabel50.setText("________________________");

        jLabel51.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jLabel51.setText("E-mail Usuario:");

        jLabel52.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        jLabel52.setText("________________________");

        jLabel53.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jLabel53.setText("Telefóno Usuario:");

        jLabel54.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        jLabel54.setText("________________________");

        jLabel38.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jLabel38.setText("Id:");

        jTextField15.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jTextField15.setForeground(new java.awt.Color(102, 102, 102));
        jTextField15.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField15.setAlignmentX(0.9F);
        jTextField15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        jTextField15.setEnabled(false);
        jTextField15.setMargin(new java.awt.Insets(12, 6, 2, 6));

        jLabel55.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jLabel55.setText("No.Personas:");

        jSpinner1.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jSpinner1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));

        jLabel56.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jLabel56.setText("Dias.Estadía:");

        jSpinner2.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jSpinner2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));

        jLabel57.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jLabel57.setText("Fecha Reservación:");

        jComboBox16.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jComboBox16.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        jComboBox16.setAutoscrolls(true);
        jComboBox16.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        jComboBox16.setEnabled(false);
        jComboBox16.setPreferredSize(new java.awt.Dimension(57, 28));

        jComboBox17.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jComboBox17.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        jComboBox17.setAutoscrolls(true);
        jComboBox17.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        jComboBox17.setEnabled(false);
        jComboBox17.setPreferredSize(new java.awt.Dimension(57, 28));

        jComboBox18.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jComboBox18.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1923", "1924", "1925", "1926", "1927" }));
        jComboBox18.setAutoscrolls(true);
        jComboBox18.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        jComboBox18.setEnabled(false);
        jComboBox18.setPreferredSize(new java.awt.Dimension(57, 28));

        jLabel58.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jLabel58.setText("Fecha Llegada:");

        jComboBox19.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jComboBox19.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        jComboBox19.setAutoscrolls(true);
        jComboBox19.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        jComboBox19.setPreferredSize(new java.awt.Dimension(57, 28));

        jComboBox20.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jComboBox20.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        jComboBox20.setAutoscrolls(true);
        jComboBox20.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        jComboBox20.setPreferredSize(new java.awt.Dimension(57, 28));

        jComboBox21.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jComboBox21.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1923", "1924", "1925", "1926", "1927" }));
        jComboBox21.setAutoscrolls(true);
        jComboBox21.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        jComboBox21.setPreferredSize(new java.awt.Dimension(57, 28));

        jLabel59.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jLabel59.setText("Fecha Partida:");

        jComboBox22.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jComboBox22.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        jComboBox22.setAutoscrolls(true);
        jComboBox22.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        jComboBox22.setEnabled(false);
        jComboBox22.setPreferredSize(new java.awt.Dimension(57, 28));

        jComboBox23.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jComboBox23.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        jComboBox23.setAutoscrolls(true);
        jComboBox23.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        jComboBox23.setEnabled(false);
        jComboBox23.setPreferredSize(new java.awt.Dimension(57, 28));

        jComboBox24.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jComboBox24.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1923", "1924", "1925", "1926", "1927" }));
        jComboBox24.setAutoscrolls(true);
        jComboBox24.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        jComboBox24.setEnabled(false);
        jComboBox24.setPreferredSize(new java.awt.Dimension(57, 28));

        jLabel60.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jLabel60.setText("Costo Total:");

        jLabel61.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        jLabel61.setText("_______________");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel35)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel41)
                                        .addComponent(jLabel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel42, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE))
                                    .addComponent(jLabel40))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel51)
                                    .addComponent(jLabel49)
                                    .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel43)
                                    .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel53)
                                    .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel47)
                            .addComponent(jLabel45)
                            .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(87, 87, 87)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel38)
                                    .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel55, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jSpinner1))
                                .addGap(31, 31, 31)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel56, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jSpinner2))
                                .addContainerGap())
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel58)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addComponent(jComboBox19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jComboBox20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jComboBox21, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel57)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addComponent(jComboBox16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jComboBox17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jComboBox18, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel59)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addComponent(jComboBox22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jComboBox23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jComboBox24, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel60)
                                        .addGap(41, 41, 41))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel61)
                                        .addGap(15, 15, 15))))))
                    .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(63, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                            .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                            .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(16, 16, 16)
                        .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        jTabbedPane1.addTab("Reservaciones", jPanel3);

        jPanel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel4MouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 102, 204));
        jLabel5.setText("Cerrar Sesión");
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel5MousePressed(evt);
            }
        });

        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel6MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 10, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addGap(8, 8, 8)))
                .addContainerGap())
        );

        jPanel7.setPreferredSize(new java.awt.Dimension(129, 56));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 129, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 56, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addGap(5, 5, 5)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 226, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 527, Short.MAX_VALUE)
                .addContainerGap())
        );

        getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseClicked
        // TODO add your handling code here:
        pantalla_registrar_condominio.dispose();
        this.dispose();
        pantalla_login_administrador.setVisible(true);
        

    }//GEN-LAST:event_jPanel4MouseClicked

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        // TODO add your handling code here:
        jLabel4.setText("Gestionar" + " " + jTabbedPane1.getTitleAt(jTabbedPane1.getSelectedIndex()));
        
        if (!jTabbedPane1.getTitleAt(jTabbedPane1.getSelectedIndex()).equals(tracktab)) {
            ClearChanges();
            resetFields();
            jButton4.setText("Actualizar Condominio");
            jButton4.setForeground(Color.black);
            jTextField9.setEnabled(true);
        }

    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:

        pantalla_registrar_condominio = new Registrar_Condominio();
        GetUbications();
        

    }//GEN-LAST:event_formWindowOpened
    boolean ValidatePlaceHolder(JTextField jtxt
    ) {
        return !(jtxt.getText().equals(("Id Condominio o CIF")) || jtxt.getText().isEmpty());
    }
    
    boolean ValidateCIFOrID(JTextField jtxt
    ) {

        //validamos la longitud del ID
        // si contiene letras y numeros la longitud debe de ser menor a 11
        // si contiene numeros la longiutd debe de ser menor a 3
        try {
            Integer.parseInt(jtxt.getText());
            return HandleID(jtxt.getText());
        } catch (Exception ex) {
            return HandleCIF(jtxt.getText());
        }
        
    }
    
    boolean HandleCIF(String text
    ) {
        
        if (!text.matches("^[A-Z0-9]+$")) {
            JOptionPane.showMessageDialog(null, "El CIF no es valido. Solo se aceptan:\n - Letras mayusculas con numeros sin espacios", "Error", 0);
            return false;
        }
        if (text.length() < 10 || text.length() > 10) {
            
            JOptionPane.showMessageDialog(null, "El CIF es demasiado corto o demasiado largo\n - Longitud requerida 10 caracteres", "Error", 0);
            return false;
        }
        
        return true;
        
    }
    
    boolean HandleID(String text
    ) {
        if (text.length() > 2) {
            JOptionPane.showMessageDialog(null, "El Id es demasiado largo", "Error", 0);
            return false;
        }
        return true;
    }
    

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:

        pantalla_login_administrador.setVisible(true);
        pantalla_registrar_condominio.dispose();
        
        ClearChanges();
        resetFields();
        jButton4.setText("Actualizar Condominio");
        jButton4.setForeground(Color.black);
        jTextField9.setEnabled(true);
        

    }//GEN-LAST:event_formWindowClosed

    private void jLabel6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MousePressed
        // TODO add your handling code here:

        pantalla_registrar_condominio.dispose();
        this.dispose();
        pantalla_login_administrador.setVisible(true);
    }//GEN-LAST:event_jLabel6MousePressed

    private void jLabel5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MousePressed
        // TODO add your handling code here:

        pantalla_registrar_condominio.dispose();
        this.dispose();
        pantalla_login_administrador.setVisible(true);
    }//GEN-LAST:event_jLabel5MousePressed

    private void jPanel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel9MouseClicked
        // Handle image upload to cellar
        if (jLabel36.isEnabled()) {
            JFileChooser filechooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "png", "jfif", "webp", "jpeg");
            filechooser.setFileFilter(filter);
            JFrame frame = new JFrame("File Browser");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);
            int result = filechooser.showOpenDialog(frame);
            
            if (result == JFileChooser.APPROVE_OPTION) {
                
                File selectedfile = filechooser.getSelectedFile();
                try {
                    BufferedImage isapic = ImageIO.read(selectedfile);
                    if (isapic == null) {
                        new_nombre_imagen = "";
                        picturepath = "";
                        if (cond != null) {
                            MostrarImagenCondominio();
                        }
                        return;
                    }
                    new_nombre_imagen = selectedfile.getName();
                    picturepath = selectedfile.getAbsolutePath();
                    String filepath = selectedfile.getAbsolutePath();
                    System.out.println(filepath);
                    System.out.println(selectedfile.getName());
                    // Create an ImageIcon from the image
                    ImageIcon icon_condominio = new ImageIcon(filepath);
                    Image image = icon_condominio.getImage();
                    Image scaledImage_condominio = image.getScaledInstance(jPanel6.getWidth(), jPanel6.getHeight(), Image.SCALE_SMOOTH);
                    ImageIcon scaledImageIcon_condominio = new ImageIcon(scaledImage_condominio);
                    // Set the icon on the JLabel
                    jLabel7.setIcon(scaledImageIcon_condominio);
                } catch (IOException ex) {
                    System.out.println("Error reading file: " + ex.getMessage());
                }
            }
        }
    }//GEN-LAST:event_jPanel9MouseClicked

    private void jCheckBox7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox7ActionPerformed
        // TODO add your handling code here:
        if (jCheckBox7.isSelected()) {
            cond.score += 15;
            jTextField12.setText(cond.score + "%");
            return;
        }
        cond.score -= 15;
        jTextField12.setText(cond.score + "%");
    }//GEN-LAST:event_jCheckBox7ActionPerformed

    private void jCheckBox6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox6ActionPerformed
        // TODO add your handling code here:
        if (jCheckBox6.isSelected()) {
            cond.score += 15;
            jTextField12.setText(cond.score + "%");
            return;
        }
        cond.score -= 15;
        jTextField12.setText(cond.score + "%");
    }//GEN-LAST:event_jCheckBox6ActionPerformed

    private void jCheckBox6FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jCheckBox6FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox6FocusLost

    private void jCheckBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox5ActionPerformed
        // TODO add your handling code here:
        if (jCheckBox5.isSelected()) {
            cond.score += 15;
            jTextField12.setText(cond.score + "%");
            return;
        }
        cond.score -= 15;
        jTextField12.setText(cond.score + "%");
    }//GEN-LAST:event_jCheckBox5ActionPerformed

    private void jCheckBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox4ActionPerformed
        // TODO add your handling code here:
        if (jCheckBox4.isSelected()) {
            cond.score += 15;
            jTextField12.setText(cond.score + "%");
            return;
        }
        cond.score -= 15;
        jTextField12.setText(cond.score + "%");
    }//GEN-LAST:event_jCheckBox4ActionPerformed

    private void jCheckBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox3ActionPerformed
        // TODO add your handling code here:
        if (jCheckBox3.isSelected()) {
            cond.score += 25;
            jTextField12.setText(cond.score + "%");
            return;
        }
        cond.score -= 25;
        jTextField12.setText(cond.score + "%");
    }//GEN-LAST:event_jCheckBox3ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
        if (jCheckBox1.isSelected()) {
            cond.score += 15;
            jTextField12.setText(cond.score + "%");
            return;
        }
        cond.score -= 15;
        jTextField12.setText(cond.score + "%");
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jFormattedTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextField1ActionPerformed

    private void jButton5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MousePressed
        // TODO add your handling code here:
        //check if textfield have reference id in order to delete
        if (jTextField6.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No se ha consultado un condominio previamente para borrar", "Error", 0);
            return;
        }
        //check that button is enable to avoid deletion if not
        if (!jButton5.isEnabled()) {
            return;
        }
        // check if the condomine exists
        if (cond.Eliminar_Condominio_Administrador(Integer.valueOf(jTextField6.getText())) == 1) {
            ClearChanges();
            new Thread(() -> {
                
                DeleteFromCellar();
                
            }).start();
            
        }
    }//GEN-LAST:event_jButton5MousePressed

    private void jTextField9KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField9KeyTyped
        // TODO add your handling code here:

        if (evt.getKeyChar() == KeyEvent.VK_ENTER && !jTextField6.getText().equals(jTextField9.getText()) && !jTextField13.getText().equals(jTextField9.getText())) {

            //validate que el CIF o Id no sea el place holder
            List<Boolean> statuses = new ArrayList<Boolean>();
            
            statuses.add(ValidatePlaceHolder(jTextField9));
            
            if (statuses.contains(false)) {
                jTextField9.setBorder(BorderFactory.createLineBorder(Color.red, 3, false));
                return;
            }

            // validar que el CIF o ID sea valido
            statuses.add(ValidateCIFOrID(jTextField9));
            
            if (statuses.contains(false)) {
                jTextField9.setBorder(BorderFactory.createLineBorder(Color.red, 3, false));
                return;
            }
            
            jTextField9.setBorder(BorderFactory.createLineBorder(Color.black, 3, false));

            //validar que el CIF o ID exista en la BD
            try {
                cond = Condominio.Consultar_Condominio_Admin(jTextField9.getText());
                if (cond != null) {
                    System.out.println("El id del condominio:" + cond.Id);
                    System.out.println("Los servicios del condominio son:" + cond.servicios);
                    System.out.println("La imagen del condominio es:" + cond.image_lugar);
                    new Thread(() -> {
                        
                        MostrarImagenCondominio();
                        
                    }).start();
                    
                    MostrarCondominio();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Gestion_Administrativa.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }//GEN-LAST:event_jTextField9KeyTyped

    private void jTextField9KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField9KeyReleased
        // TODO add your handling code here:

        jLabel2.setText(String.valueOf(jTextField9.getText().length()));
        System.out.println("Released key:" + jTextField9.getText().length());
    }//GEN-LAST:event_jTextField9KeyReleased

    private void jTextField9KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField9KeyPressed
        // TODO add your handling code here:
        jLabel2.setText(String.valueOf(jTextField9.getText().length()));
        System.out.println("Pressed key:" + jTextField9.getText().length());
    }//GEN-LAST:event_jTextField9KeyPressed

    private void jTextField9FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField9FocusLost
        // TODO add your handling code here:
        if (jTextField9.getText().isEmpty()) {
            jTextField9.setText("Id Condominio o CIF");
            jTextField9.setForeground(Color.gray);
        }
        jTextField9.setBorder(BorderFactory.createLineBorder(Color.black, 3, false));
    }//GEN-LAST:event_jTextField9FocusLost

    private void jTextField9FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField9FocusGained
        // TODO add your handling code here:
        if (jTextField9.getText().equals("Id Condominio o CIF")) {
            jTextField9.setText("");
            jTextField9.setForeground(Color.black);
        }
    }//GEN-LAST:event_jTextField9FocusGained

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MousePressed
        // TODO add your handling code here:
        if (jTextField6.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No se ha consultado un condominio para actualizar", "Error", 0);
            return;
        }
        if (jButton4.getText().equals("Actualizar Condominio")) {
            jButton4.setForeground(Color.blue);
            jButton4.setText("Guardar Datos");
            jTextField9.setEnabled(false);
            jButton5.setEnabled(false);
            enableFields();
            return;
        }
        
        if (jButton4.getText().equals("Guardar Datos")) {

            // validar datos
            validarCondominio();
            
        }
    }//GEN-LAST:event_jButton4MousePressed

    private void jButton3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MousePressed
        // TODO add your handling code here:

        pantalla_registrar_condominio.setVisible(true);
        pantalla_registrar_condominio.captureScreen(this);
        this.setEnabled(false);
    }//GEN-LAST:event_jButton3MousePressed

    private void jTextField18FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField18FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField18FocusLost

    private void jTextField18FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField18FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField18FocusGained

    private void jTextField17FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField17FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField17FocusLost

    private void jTextField17FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField17FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField17FocusGained

    private void jButton9MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton9MousePressed
        // TODO add your handling code here:
        if (jButton9.getText().equals("S")) {
            jButton9.setText("H");
            jPasswordField2.setEchoChar((char) 0);
            return;
        }
        jButton9.setText("S");
        jPasswordField2.setEchoChar((char) '\u2022');
    }//GEN-LAST:event_jButton9MousePressed

    private void jPasswordField2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPasswordField2FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordField2FocusLost

    private void jPasswordField2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPasswordField2FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordField2FocusGained

    private void jTextField3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField3FocusLost
        // TODO add your handling code here:
        if (jTextField3.getText().isEmpty()) {
            jTextField3.setForeground(Color.gray);
            jTextField3.setText("ejemplo@gmail.com");
        }
    }//GEN-LAST:event_jTextField3FocusLost

    private void jTextField3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField3FocusGained
        // TODO add your handling code here:

        if (jTextField3.getText().equals("ejemplo@gmail.com")) {
            jTextField3.setForeground(Color.black);
            jTextField3.setText("");
        }
    }//GEN-LAST:event_jTextField3FocusGained

    private void jButton6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MousePressed
        // TODO add your handling code here:

        if (jButton6.getText().equals("S")) {
            jButton6.setText("H");
            jPasswordField1.setEchoChar((char) 0);
            return;
        }
        jButton6.setText("S");
        jPasswordField1.setEchoChar((char) '\u2022');
    }//GEN-LAST:event_jButton6MousePressed

    private void jTextField2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField2FocusLost
        // TODO add your handling code here:

        if (jTextField2.getText().isEmpty()) {
            jTextField2.setForeground(Color.gray);
            jTextField2.setText("8888888888");
        }
    }//GEN-LAST:event_jTextField2FocusLost

    private void jTextField2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField2FocusGained
        // TODO add your handling code here:
        if (jTextField2.getText().equals("8888888888")) {
            jTextField2.setForeground(Color.black);
            jTextField2.setText("");
        }
    }//GEN-LAST:event_jTextField2FocusGained

    private void jTextField16FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField16FocusLost
        // TODO add your handling code here:

        if (jTextField1.getText().isEmpty()) {
            jTextField1.setForeground(Color.gray);
            jTextField1.setText("Primernombre Segundonombre");
        }
    }//GEN-LAST:event_jTextField16FocusLost

    private void jTextField16FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField16FocusGained
        // TODO add your handling code here:

        if (jTextField1.getText().equals("Primernombre Segundonombre")) {
            jTextField1.setForeground(Color.black);
            jTextField1.setText("");
        }
    }//GEN-LAST:event_jTextField16FocusGained
    private void ValidarLongitudID() {
        
        if (!jTextField1.getText().contains("@") && jTextField1.getText().length() > 3) {
            JOptionPane.showMessageDialog(null, "El Id debe de estar en un rango maximo de 3", "Error", 1);
            jTextField1.setBorder(BorderFactory.createLineBorder(Color.red, 3, false));
            return;
        }

        // Validar que el id o email exista
        // consultar usuario
        usuario = Usuario.Consultar_Usuario(jTextField1.getText());
        if (usuario != null) {
            //Popular campos
            jTextField1.setBorder(BorderFactory.createLineBorder(Color.black, 3, false));
            
            LlenarCamposUsuario();

            //Actualizar objeto
        }
    }
    
    private void LlenarCamposUsuario() {
        
        jTextField3.setCaretPosition(0);
        jTextField17.setText(String.valueOf(usuario.Id_user_get()));
        jTextField16.setText(usuario.Nombre_user_get());
        jDateChooser2.setDate(usuario.Fecha_Registro_user_get());
        jTextField2.setText(usuario.Telefono_user_get());
        jDateChooser1.setDate(usuario.Fecha_Nacimiento_user_get());
        jTextField18.setText(String.valueOf(usuario.Edad_user_get()));
        jTextField3.setText(usuario.correo_electronico_user_get());
        jPasswordField1.setText(usuario.Contraseña_user_get());
        jPasswordField2.setText(usuario.Contraseña_user_get());
        jTextArea1.setText(usuario.Direccion_user_get());
        
        jComboBox1.setSelectedItem(Ubicacion.get(usuario.Ubicacion_user_get()));
        jComboBox123.setSelectedItem(usuario.Sexo_user_get());
    }
    
    private void ValidarIdEmail() {
        // validar que sea un email o un Id valido
        if (jTextField1.getText().matches(email) || jTextField1.getText().matches(numbersonly)) {
            
            ValidarLongitudID();
            return;
        }
        jTextField1.setBorder(BorderFactory.createLineBorder(Color.red, 3, false));
        JOptionPane.showMessageDialog(null, "El Id o email introducidos son invalidos", "Error", 1);
        
    }
    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped
        // TODO add your handling code here:

        if (evt.getKeyChar() == KeyEvent.VK_ENTER && !jTextField1.getText().isEmpty() && !jTextField1.getText().equals(jTextField17.getText()) && !jTextField1.getText().equals(jTextField3.getText())) {
            System.out.println("Entro en key typed");
            ValidarIdEmail();
            
        }

    }//GEN-LAST:event_jTextField1KeyTyped

    private void jTextField1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusGained
        // TODO add your handling code here:

        if (jTextField1.getText().equals("Id Usuario o email")) {
            jTextField1.setText("");
            jTextField1.setForeground(Color.black);
        }
    }//GEN-LAST:event_jTextField1FocusGained

    private void jTextField1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusLost
        // TODO add your handling code here:
        if (jTextField1.getText().equals("")) {
            jTextField1.setText("Id Usuario o email");
            jTextField1.setForeground(Color.gray);
            
        }
    }//GEN-LAST:event_jTextField1FocusLost

    private void jButton11MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton11MousePressed
        // TODO add your handling code here:
        if (jTextField17.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No se ha consultado un usuario para borrar", "Error", 1);
            return;
        }
        
        if (usuario.Eliminar_Usuario(Integer.valueOf(jTextField17.getText())) == 1) {
            LimpiarCamposUsuario();
        }
    }//GEN-LAST:event_jButton11MousePressed
    private void LimpiarCamposUsuario() {
        
        jTextField17.setText("");
        jTextField16.setText("");
        jTextField2.setText("");
        jDateChooser2.setDate(null);
        jDateChooser1.setDate(null);
        jTextField12.setText("");
        jTextField18.setText("");
        jTextField3.setText("");
        jPasswordField1.setText("");
        jPasswordField2.setText("");
        jTextArea1.setText("");
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Gestion_Administrativa.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Gestion_Administrativa.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Gestion_Administrativa.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Gestion_Administrativa.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Gestion_Administrativa().setVisible(true);
                
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JCheckBox jCheckBox6;
    private javax.swing.JCheckBox jCheckBox7;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox123;
    private javax.swing.JComboBox<String> jComboBox13;
    private javax.swing.JComboBox<String> jComboBox14;
    private javax.swing.JComboBox<String> jComboBox16;
    private javax.swing.JComboBox<String> jComboBox17;
    private javax.swing.JComboBox<String> jComboBox18;
    private javax.swing.JComboBox<String> jComboBox19;
    private javax.swing.JComboBox<String> jComboBox20;
    private javax.swing.JComboBox<String> jComboBox21;
    private javax.swing.JComboBox<String> jComboBox22;
    private javax.swing.JComboBox<String> jComboBox23;
    private javax.swing.JComboBox<String> jComboBox24;
    private javax.swing.JComboBox<String> jComboBox9;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane10;
    private javax.swing.JLayeredPane jLayeredPane11;
    private javax.swing.JLayeredPane jLayeredPane12;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JLayeredPane jLayeredPane3;
    private javax.swing.JLayeredPane jLayeredPane4;
    private javax.swing.JLayeredPane jLayeredPane5;
    private javax.swing.JLayeredPane jLayeredPane6;
    private javax.swing.JLayeredPane jLayeredPane7;
    private javax.swing.JLayeredPane jLayeredPane8;
    private javax.swing.JLayeredPane jLayeredPane9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPasswordField jPasswordField2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables
}
