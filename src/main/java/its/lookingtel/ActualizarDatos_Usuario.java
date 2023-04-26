/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package its.lookingtel;

import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

/**
 *
 * @author Guest Mode
 */
public class ActualizarDatos_Usuario extends javax.swing.JFrame {

    static final String lettersandnumbers = "^[a-zA-Z0-9]*$";
    static final String ValidName = "^[A-Z][a-záéíóúñ]+\\s[A-Z][a-záéíóúñ]+(\\s[A-Z][a-záéíóúñ]+)*$";
    static final String numbersonly = "^[0-9]+$";
    static final String direccion = "^[A-Z][#,a-záéíóúñA-Z.()/0-9 ]+$";
    static final String email = "^[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}$";
    static HashMap<Integer, String> map = new HashMap<Integer, String>();

    Condominios_Usuario pantalla_condominios_usuario;

    /*
     * Creates new form Login_Administrador
     */

    void ClearForm() {

        jTextField1.setText("");
        jLabel14.setVisible(false);
        jLabel3.setForeground(Color.black);
        jLabel2.requestFocusInWindow();

    }

    void captureScreen(Condominios_Usuario pantalla) {
        pantalla_condominios_usuario = pantalla;
    }

    void enableFields() {

        jTextField1.setEditable(true);
        jComboBox1.setEnabled(true);
        jTextField2.setEditable(true);
        jTextField3.setEditable(true);
        jDateChooser1.setEnabled(true);
        jComboBox2.setEnabled(true);
        jTextArea1.setEditable(true);
        jPasswordField2.setEditable(true);
        jPasswordField1.setEditable(true);

    }

    void disableFields() {

        jTextField1.setEditable(false);
        jComboBox1.setEnabled(false);
        jTextField2.setEditable(false);
        jTextField3.setEditable(false);
        jDateChooser1.setEnabled(false);
        jComboBox2.setEnabled(false);
        jTextArea1.setEditable(false);
        jPasswordField2.setEditable(false);
        jPasswordField1.setEditable(false);

    }

    void FillData() {
        jTextField1.setText(Usuario.nombre);
        jTextField1.setCaretPosition(0);
        jComboBox1.setSelectedItem((String) map.get(Usuario.Id_Ubicacion));
        jTextField2.setText(Usuario.telefono);
        jTextField2.setCaretPosition(0);
        jTextField3.setText(Usuario.correo_electronico);
        jTextField3.setCaretPosition(0);
        jDateChooser1.setDate(Usuario.fecha_nacimiento);
        jComboBox2.setSelectedItem((String) Usuario.sexo);
        jTextArea1.setText(Usuario.direccion);
        jTextArea1.setCaretPosition(0);
        jPasswordField2.setText(Usuario.contraseña);
        jPasswordField2.setCaretPosition(0);
        jPasswordField1.setText(Usuario.contraseña);
        jPasswordField1.setCaretPosition(0);
    }

    void resetFields() {

        jLabel3.setForeground(Color.black);
        jLabel14.setVisible(false);

        jLabel4.setForeground(Color.black);
        jLabel16.setVisible(false);

        jLabel8.setForeground(Color.black);
        jLabel19.setVisible(false);

        jLabel11.setForeground(Color.black);
        jLabel17.setVisible(false);

        jLabel9.setForeground(Color.black);
        jLabel18.setVisible(false);

        jLabel15.setForeground(Color.black);
        jLabel20.setVisible(false);

        jLabel10.setForeground(Color.black);
        jLabel21.setVisible(false);

    }

    void updateUserObject(int ubicacion_usuario) {

        Usuario.nombre = jTextField1.getText();
        Usuario.Id_Ubicacion = ubicacion_usuario;
        Usuario.telefono = jTextField2.getText();
        Usuario.correo_electronico = jTextField3.getText();
        Usuario.fecha_nacimiento = jDateChooser1.getCalendar().getTime();
        Usuario.sexo = (String) jComboBox2.getSelectedItem();
        Usuario.direccion = jTextArea1.getText();
        Usuario.contraseña = jPasswordField2.getText();

        Calendar cal = jDateChooser1.getCalendar();
        LocalDate currentDate = LocalDate.now();
        LocalDate dateToSubtract = LocalDate.of(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DATE));
        long daysdif = ChronoUnit.DAYS.between(dateToSubtract, currentDate);
        Usuario.edad = ((int) daysdif / 365);

    }

    public ActualizarDatos_Usuario() {

        initComponents();
        jButton4.setVisible(false);
        jLabel20.setHorizontalAlignment(SwingConstants.LEFT); // left-align the text
        jLabel20.setVerticalAlignment(SwingConstants.TOP); // 
        jLabel2.requestFocusInWindow();
        jLabel14.setVisible(false);
        jLabel18.setVisible(false);
        jLabel17.setVisible(false);
        jLabel19.setVisible(false);
        jLabel20.setVisible(false);
        jLabel21.setVisible(false);
        jButton2.setBackground(Color.white);
        jButton3.setBackground(Color.white);
        jLabel16.setVisible(false);
        jDateChooser1.setBackground(Color.white);
        getContentPane().setBackground(Color.white);
        jPanel2.setBackground(Color.white);
        jPanel3.setBackground(Color.white);
        jComboBox1.setBackground(Color.white);
        jComboBox2.setBackground(Color.white);
        jButton1.setBackground(Color.white);
        jButton4.setBackground(Color.white);
        displaylogos();
    }

    void actualizar_usuario(int key) {

        String nombre_usuario = jTextField1.getText();
        String telefono_usuario = jTextField2.getText();
        String email_usuario = jTextField3.getText();
        Calendar cal = jDateChooser1.getCalendar();
        String fecha_nacimiento_usuario = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DATE);
        int ubicacion_usuario = key;
        String direccion_usuario = jTextArea1.getText();
        String contraseña_usuario = jPasswordField2.getText();
        String sexo_usuario = (String) jComboBox2.getSelectedItem();
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

                statement.setInt(1, ubicacion_usuario);
                statement.setString(2, nombre_usuario);
                statement.setString(3, telefono_usuario);
                statement.setString(4, email_usuario);
                statement.setDate(5, Date.valueOf(fecha_nacimiento_usuario));
                statement.setInt(6, edad_usuario);
                statement.setString(7, direccion_usuario);
                statement.setString(8, sexo_usuario);
                statement.setString(9, contraseña_usuario);
                statement.setInt(10, Usuario.Id);

                int statusprocess = statement.executeUpdate();

                System.out.println(nombre_usuario);
                System.out.println(telefono_usuario);
                System.out.println(email_usuario);
                System.out.println(fecha_nacimiento_usuario);

                System.out.println(ubicacion_usuario);
                System.out.println(direccion_usuario);
                System.out.println(contraseña_usuario);
                System.out.println(sexo_usuario);
                System.out.println(edad_usuario);

                if (statusprocess == 1) {
                    JOptionPane.showMessageDialog(null, "Usuario Actualizado Satisfactoriamente Codigo de Salida 1", "Sucess", 1);
                    jButton4.setVisible(false);
                    updateUserObject(ubicacion_usuario);
                    //FillData();
                    disableFields();
                    resetFields();
                    jButton1.setText("Actualizar Datos");
                    return;
                }

                JOptionPane.showMessageDialog(null, "No se pudo actualizar Usuario Codigo de Error 0", "Error", 0);

                //ResultSetMetaData rsmd = rs.getMetaData();
                //System.out.println(rsmd.getColumnName(1))
                statement.close();
                conn.close();
                //  String output = rsmd.getColumnName(1) + " " + rsmd.getColumnName(2) + " " + rsmd.getColumnName(3) + " " + rsmd.getColumnName(4) + "\n";
                //    while (rs.next()) { 
                //    output += rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4) + " ";
                //   }
                //  System.out.print(output);

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

    private static boolean validate(String text, int minlength, int maxlength, String regular_exp, JLabel lbl, boolean ignore_length, JLabel errorlblmsg, String placeholder) {

        // Validate placeholder
        if (placeholder.equals(text) || text.isEmpty()) {

            lbl.setForeground(Color.red);
            errorlblmsg.setVisible(true);
            errorlblmsg.setText("Porfavor introduce  " + lbl.getText().substring(0, lbl.getText().length() - 1));
            return false;
        }

        // validate the data type
        if (!text.matches(regular_exp)) {

            lbl.setForeground(Color.red);
            errorlblmsg.setVisible(true);
            errorlblmsg.setText(lbl.getText().substring(0, lbl.getText().length() - 1) + " No Valido");
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
            errorlblmsg.setText("Longitud de " + lbl.getText().substring(0, lbl.getText().length() - 1) + " invalida");
            JOptionPane.showMessageDialog(null, "La longitud mininima deben de ser: " + minlength + " caracteres en: " + lbl.getText().substring(0, lbl.getText().length() - 1) + "\n"
                    + "La longitud maxima deben de ser: " + maxlength + " caracteres en: " + lbl.getText().substring(0, lbl.getText().length() - 1), "Aviso", 0);
            return false;
        }

        lbl.setForeground(Color.black);
        errorlblmsg.setVisible(false);
        return true;

    }

    private boolean validatecontraseña(String text, String text2, int minlength, int maxlength, JLabel lbl, JLabel errorlblmsg, String errormsg) {

        // Validate placeholder
        if (text.isEmpty()) {

            lbl.setForeground(Color.red);
            errorlblmsg.setVisible(true);
            errorlblmsg.setText(errormsg);
            return false;
        }

        // validate the length
        if (text.length() < minlength || text.length() - 1 > maxlength) {

            lbl.setForeground(Color.red);
            errorlblmsg.setVisible(true);
            errorlblmsg.setText("<html> Longitud de <br>" + lbl.getText().substring(0, lbl.getText().length() - 1) + " invalida</html>");
            JOptionPane.showMessageDialog(null, "La longitud mininima deben de ser: " + minlength + " caracteres en: " + lbl.getText().substring(0, lbl.getText().length() - 1) + "\n"
                    + "La longitud maxima deben de ser: " + maxlength + " caracteres en: " + lbl.getText().substring(0, lbl.getText().length() - 1), "Aviso", 0);
            return false;
        }

        // validar que las contraseñas sean iguales
        if (!text.equals(text2)) {
            lbl.setForeground(Color.red);
            errorlblmsg.setVisible(true);
            errorlblmsg.setText("<html> Las contraseñas <br>No Coinciden</html>");
            return false;
        }

        lbl.setForeground(Color.black);
        errorlblmsg.setVisible(false);
        return true;
    }

    private boolean validatefecha(boolean status) {
        try {
            Calendar cal = jDateChooser1.getCalendar();
            System.out.println(cal.get(Calendar.YEAR));
            System.out.println(cal.get(Calendar.MONTH) + 1);
            System.out.println(cal.get(Calendar.DATE));
            LocalDate currentDate = LocalDate.now();
            LocalDate dateToSubtract = LocalDate.of(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DATE));
            System.out.println(ChronoUnit.DAYS.between(dateToSubtract, currentDate));
            long daysdif = ChronoUnit.DAYS.between(dateToSubtract, currentDate);
            System.out.println(daysdif / 365);
            jLabel11.setForeground(Color.black);
            jLabel17.setVisible(false);
            status = true;

        } catch (NullPointerException ex) {
            System.out.println("No se selecciona fecha");
            jLabel11.setForeground(Color.red);
            jLabel17.setText("Fecha Invalida");
            jLabel17.setVisible(true);

        } catch (ArrayIndexOutOfBoundsException ex) {

        }
        return status;
    }

    private void displaylogos() {
        try {
            // Load the image from the URL

            URL lookingtel = new URL("https://lookingtel.cellar-c2.services.clever-cloud.com/lookingtel.png");
            URL close_button = new URL("https://lookingtel.cellar-c2.services.clever-cloud.com/close_button.png");

            Image lookingtel_image = ImageIO.read(lookingtel);
            Image close_button_image = ImageIO.read(close_button);

            Image scaledImage_lookingtel = lookingtel_image.getScaledInstance(jPanel2.getWidth(),
                    jPanel2.getHeight(),
                    Image.SCALE_SMOOTH);
            Image scaledImage_close_button_image = close_button_image.getScaledInstance(jPanel3.getWidth(),
                    jPanel3.getHeight(),
                    Image.SCALE_SMOOTH);

            // Create an ImageIcon from the image
            ImageIcon icon_lookingtel = new ImageIcon(scaledImage_lookingtel);
            ImageIcon icon_close_button = new ImageIcon(scaledImage_close_button_image);

            // Set the icon on the JLabel
            jLabel6.setIcon(icon_lookingtel);
            jLabel7.setIcon(icon_close_button);

        } catch (IOException ex) {
            // ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "No se pueden mostrar las imagenes", "Error", 0);
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

        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        jLabel9 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLayeredPane3 = new javax.swing.JLayeredPane();
        jLabel12 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLayeredPane4 = new javax.swing.JLayeredPane();
        jLabel4 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLayeredPane5 = new javax.swing.JLayeredPane();
        jLabel10 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jButton3 = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jLayeredPane7 = new javax.swing.JLayeredPane();
        jTextField3 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLayeredPane8 = new javax.swing.JLayeredPane();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLayeredPane9 = new javax.swing.JLayeredPane();
        jLabel11 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLayeredPane10 = new javax.swing.JLayeredPane();
        jLabel15 = new javax.swing.JLabel();
        jPasswordField2 = new javax.swing.JPasswordField();
        jButton2 = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Actualizar Usuario");
        setForeground(new java.awt.Color(255, 255, 0));
        setLocation(new java.awt.Point(123, 123));
        setPreferredSize(new java.awt.Dimension(600, 620));
        setResizable(false);
        setType(java.awt.Window.Type.POPUP);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
                formWindowLostFocus(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 0, 36)); // NOI18N
        jLabel2.setText("Mi Perfil");

        jPanel2.setPreferredSize(new java.awt.Dimension(129, 56));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel3MouseClicked(evt);
            }
        });

        jLabel7.setToolTipText("");
        jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                .addContainerGap())
        );

        jButton1.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jButton1.setText("Actualizar Datos");
        jButton1.setAlignmentY(3.0F);
        jButton1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton1MousePressed(evt);
            }
        });

        jLayeredPane1.setPreferredSize(new java.awt.Dimension(197, 87));
        java.awt.GridBagLayout jLayeredPane1Layout = new java.awt.GridBagLayout();
        jLayeredPane1Layout.columnWidths = new int[] {220};
        jLayeredPane1Layout.rowHeights = new int[] {0, 0, 20};
        jLayeredPane1.setLayout(jLayeredPane1Layout);

        jTextField1.setEditable(false);
        jTextField1.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(0, 0, 0));
        jTextField1.setText("Primernombre Segundonombre");
        jTextField1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        jTextField1.setCaretPosition(0);
        jTextField1.setPreferredSize(new java.awt.Dimension(182, 24));
        jTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField1FocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        jLayeredPane1.add(jTextField1, gridBagConstraints);

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

        jLayeredPane2.setPreferredSize(new java.awt.Dimension(200, 93));
        java.awt.GridBagLayout jLayeredPane2Layout = new java.awt.GridBagLayout();
        jLayeredPane2Layout.columnWidths = new int[] {240};
        jLayeredPane2Layout.rowHeights = new int[] {0, 0, 20};
        jLayeredPane2.setLayout(jLayeredPane2Layout);

        jLabel9.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel9.setText("Dirección:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jLayeredPane2.add(jLabel9, gridBagConstraints);

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
        jTextArea1.setText("Boulevard, Calle, #numero");
        jTextArea1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        jTextArea1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTextArea1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jTextArea1.setMinimumSize(new java.awt.Dimension(5, 5));
        jTextArea1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextArea1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextArea1FocusLost(evt);
            }
        });
        jScrollPane1.setViewportView(jTextArea1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jLayeredPane2.add(jScrollPane1, gridBagConstraints);

        jLayeredPane3.setPreferredSize(new java.awt.Dimension(76, 23));
        java.awt.GridBagLayout jLayeredPane3Layout = new java.awt.GridBagLayout();
        jLayeredPane3Layout.columnWidths = new int[] {100};
        jLayeredPane3Layout.rowHeights = new int[] {0, 0, 0};
        jLayeredPane3.setLayout(jLayeredPane3Layout);

        jLabel12.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel12.setText("Sexo:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jLayeredPane3.add(jLabel12, gridBagConstraints);

        jComboBox2.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        jComboBox2.setForeground(new java.awt.Color(0, 0, 0));
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "M", "F" }));
        jComboBox2.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jLayeredPane3.add(jComboBox2, gridBagConstraints);

        java.awt.GridBagLayout jLayeredPane4Layout = new java.awt.GridBagLayout();
        jLayeredPane4Layout.columnWidths = new int[] {239};
        jLayeredPane4Layout.rowHeights = new int[] {0, 0, 25};
        jLayeredPane4.setLayout(jLayeredPane4Layout);

        jLabel4.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel4.setText("Telefono:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.RELATIVE;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jLayeredPane4.add(jLabel4, gridBagConstraints);

        jTextField2.setEditable(false);
        jTextField2.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jTextField2.setForeground(new java.awt.Color(0, 0, 0));
        jTextField2.setText("8888888888");
        jTextField2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
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

        java.awt.GridBagLayout jLayeredPane5Layout = new java.awt.GridBagLayout();
        jLayeredPane5Layout.columnWidths = new int[] {100, 35};
        jLayeredPane5Layout.rowHeights = new int[] {0, 0, 66};
        jLayeredPane5.setLayout(jLayeredPane5Layout);

        jLabel10.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel10.setText("Confirmar Contraseña:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jLayeredPane5.add(jLabel10, gridBagConstraints);

        jPasswordField1.setEditable(false);
        jPasswordField1.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        jPasswordField1.setForeground(new java.awt.Color(0, 0, 0));
        jPasswordField1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        jPasswordField1.setMinimumSize(new java.awt.Dimension(100, 24));
        jPasswordField1.setPreferredSize(new java.awt.Dimension(100, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jLayeredPane5.add(jPasswordField1, gridBagConstraints);

        jButton3.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jButton3.setText("S");
        jButton3.setToolTipText("");
        jButton3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.setPreferredSize(new java.awt.Dimension(23, 23));
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton3MousePressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jLayeredPane5.add(jButton3, gridBagConstraints);

        jLabel21.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 51, 51));
        jLabel21.setText("Porfavor vuelve a");
        jLabel21.setAlignmentY(0.0F);
        jLabel21.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel21.setMaximumSize(new java.awt.Dimension(95, 20));
        jLabel21.setMinimumSize(new java.awt.Dimension(150, 20));
        jLabel21.setPreferredSize(new java.awt.Dimension(150, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jLayeredPane5.add(jLabel21, gridBagConstraints);

        java.awt.GridBagLayout jLayeredPane7Layout = new java.awt.GridBagLayout();
        jLayeredPane7Layout.columnWidths = new int[] {239};
        jLayeredPane7Layout.rowHeights = new int[] {25, 25, 25};
        jLayeredPane7.setLayout(jLayeredPane7Layout);

        jTextField3.setEditable(false);
        jTextField3.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jTextField3.setForeground(new java.awt.Color(0, 0, 0));
        jTextField3.setText("ejemplo@gmail.com");
        jTextField3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        jTextField3.setCaretPosition(0);
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

        jLabel8.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel8.setText("Email:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.RELATIVE;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jLayeredPane7.add(jLabel8, gridBagConstraints);

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

        java.awt.GridBagLayout jLayeredPane8Layout = new java.awt.GridBagLayout();
        jLayeredPane8Layout.columnWidths = new int[] {239};
        jLayeredPane8.setLayout(jLayeredPane8Layout);

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel1.setText("Ubicación:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jLayeredPane8.add(jLabel1, gridBagConstraints);

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

        java.awt.GridBagLayout jLayeredPane9Layout = new java.awt.GridBagLayout();
        jLayeredPane9Layout.columnWidths = new int[] {150};
        jLayeredPane9Layout.rowHeights = new int[] {3, 3, 23};
        jLayeredPane9.setLayout(jLayeredPane9Layout);

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

        jDateChooser1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
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

        java.awt.GridBagLayout jLayeredPane10Layout = new java.awt.GridBagLayout();
        jLayeredPane10Layout.columnWidths = new int[] {100, 35};
        jLayeredPane10Layout.rowHeights = new int[] {0, 0, 70};
        jLayeredPane10.setLayout(jLayeredPane10Layout);

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

        jButton2.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jButton2.setText("S");
        jButton2.setToolTipText("");
        jButton2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.setPreferredSize(new java.awt.Dimension(23, 23));
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton2MousePressed(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jLayeredPane10.add(jButton2, gridBagConstraints);

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

        jButton4.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jButton4.setText("Cancelar");
        jButton4.setAlignmentY(3.0F);
        jButton4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(46, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(111, 111, 111)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLayeredPane4)
                            .addComponent(jLayeredPane8)
                            .addComponent(jLayeredPane7)
                            .addComponent(jLayeredPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLayeredPane9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLayeredPane10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLayeredPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLayeredPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLayeredPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLayeredPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLayeredPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLayeredPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLayeredPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLayeredPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLayeredPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLayeredPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLayeredPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseClicked
        // TODO add your handli ng code here:
        jButton3.setText("S");
        jPasswordField1.setEchoChar((char) '\u2022');
        jButton2.setText("S");
        jPasswordField2.setEchoChar((char) '\u2022');
        jButton4.setVisible(false);
        jButton1.setText("Actualizar Datos");
        FillData();
        disableFields();
        resetFields();
        this.setVisible(false);
        pantalla_condominios_usuario.setEnabled(true);
        pantalla_condominios_usuario.requestFocus();

    }//GEN-LAST:event_jPanel3MouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        // TODO add your handling code here:
        jButton3.setText("S");
        jPasswordField1.setEchoChar((char) '\u2022');
        jButton2.setText("S");
        jPasswordField2.setEchoChar((char) '\u2022');
        jButton4.setVisible(false);
        jButton1.setText("Actualizar Datos");
        FillData();
        disableFields();
        resetFields();
        this.setVisible(false);
        pantalla_condominios_usuario.setEnabled(true);
        pantalla_condominios_usuario.requestFocus();

    }//GEN-LAST:event_jLabel7MouseClicked

    private void jTextField1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusLost
        // TODO add your handling code here:

        if (jTextField1.getText().isEmpty()) {
            jTextField1.setForeground(Color.gray);
            jTextField1.setText("Primernombre Segundonombre");
        }


    }//GEN-LAST:event_jTextField1FocusLost

    private void jTextField2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField2FocusLost
        // TODO add your handling code here:

        if (jTextField2.getText().isEmpty()) {
            jTextField2.setForeground(Color.gray);
            jTextField2.setText("8888888888");
        }


    }//GEN-LAST:event_jTextField2FocusLost


    private void jButton1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MousePressed

        jButton4.setVisible(true);
        enableFields();
        /*
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(jFormattedTextField1.getText(), format);
        LocalDate date2 = LocalDate.now();
        System.out.println(ChronoUnit.DAYS.between(date, date2));
         */

        if (jButton1.getText().equals("Salvar Datos")) {

            jButton4.setEnabled(false);

            // creamos las estructuras de datos para gestionar los textfields y los checkboxes
            List<Boolean> passedstatuses = new ArrayList<Boolean>();

            // verificamos que los textfields cumplan con la longitud y los caracteres posibles
            passedstatuses.add(validate(jTextField1.getText(), 5, 45, ValidName, jLabel3, false, jLabel14, "Primernombre Segundonombre"));

            passedstatuses.add(validate(jTextField2.getText(), 10, 10, numbersonly, jLabel4, false, jLabel16, "8888888888"));

            passedstatuses.add(validate(jTextField3.getText(), 5, 50, email, jLabel8, false, jLabel19, "ejemplo@gmail.com"));

            passedstatuses.add(validate(jTextArea1.getText(), 30, 50, direccion, jLabel9, false, jLabel18, "Boulevard, Calle, #numero"));

            passedstatuses.add(validatecontraseña(jPasswordField2.getText(), jPasswordField1.getText(), 10, 15, jLabel15, jLabel20, "<html> Porfavor introduce una <br> contraseña</html>"));

            passedstatuses.add(validatecontraseña(jPasswordField1.getText(), jPasswordField2.getText(), 10, 15, jLabel10, jLabel21, "<html> Porfavor vuelve <br> a introducir <br> la contraseña </html>"));

            passedstatuses.add(validatefecha(false));

            System.out.println(passedstatuses);

            if (passedstatuses.contains(false)) {
                return;
            }

            // obtenemos la llave primaria de la ubicacion en base al pais,estado y ciudad que es el valor
            String location = (String) jComboBox1.getSelectedItem();
            int key = 0;
            for (Map.Entry<Integer, String> entry : map.entrySet()) {
                if (entry.getValue().equals(location)) {
                    key = entry.getKey();
                    break;
                }
            }

            // handle method actualizar usuario
            actualizar_usuario(key);
        }
        
       jButton4.setEnabled(true);
        
       if(!jTextField1.isEditable()){
           jButton1.setText("Actualizar Datos");
           return;
       }
       jButton1.setText("Salvar Datos");
       
    }//GEN-LAST:event_jButton1MousePressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // Popular ubicaciones

        try (Connection conn = Conexion_Remota.hikaridatasource.getConnection()) {

            if (conn == null) {
                JOptionPane.showMessageDialog(null, "La conexion es nula no se puede iniciar sesion", "Error", 0);
                return;
            }

            PreparedStatement statement = conn.prepareStatement("SELECT * FROM UBICATION");
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {

                int Id = rs.getInt(1);
                String Pais = rs.getString(2);
                String Estado = rs.getString(3);
                String Ciudad = rs.getString(4);
                map.put(Id, Pais + "/" + Estado + "/" + Ciudad);
                jComboBox1.addItem(Pais + "/" + Estado + "/" + Ciudad);

            }

            rs.close();
            statement.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println("No se pudo establecer la conexion");
        }

        // Llenar los campos del usuario
        FillData();


    }//GEN-LAST:event_formWindowOpened

    private void jTextField1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusGained
        // TODO add your handling code here:

        if (jTextField1.getText().equals("Primernombre Segundonombre")) {
            jTextField1.setForeground(Color.black);
            jTextField1.setText("");
        }


    }//GEN-LAST:event_jTextField1FocusGained

    private void jTextField2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField2FocusGained
        // TODO add your handling code here:
        if (jTextField2.getText().equals("8888888888")) {
            jTextField2.setForeground(Color.black);
            jTextField2.setText("");
        }
    }//GEN-LAST:event_jTextField2FocusGained

    private void jTextArea1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextArea1FocusGained
        // TODO add your handling code here:

        if (jTextArea1.getText().equals("Boulevard, Calle, #numero")) {
            jTextArea1.setForeground(Color.black);
            jTextArea1.setText("");
        }

    }//GEN-LAST:event_jTextArea1FocusGained

    private void jTextArea1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextArea1FocusLost
        // TODO add your handling code here:
        if (jTextArea1.getText().isEmpty()) {
            jTextArea1.setForeground(Color.gray);
            jTextArea1.setText("Boulevard, Calle, #numero");
        }

    }//GEN-LAST:event_jTextArea1FocusLost

    private void jTextField3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField3FocusGained
        // TODO add your handling code here:

        if (jTextField3.getText().equals("ejemplo@gmail.com")) {
            jTextField3.setForeground(Color.black);
            jTextField3.setText("");
        }

    }//GEN-LAST:event_jTextField3FocusGained

    private void jTextField3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField3FocusLost
        // TODO add your handling code here:
        if (jTextField3.getText().isEmpty()) {
            jTextField3.setForeground(Color.gray);
            jTextField3.setText("ejemplo@gmail.com");
        }
    }//GEN-LAST:event_jTextField3FocusLost

    private void jButton2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MousePressed
        // TODO add your handling code here:
        if (jButton2.getText().equals("S")) {
            jButton2.setText("H");
            jPasswordField2.setEchoChar((char) 0);
            return;
        }
        jButton2.setText("S");
        jPasswordField2.setEchoChar((char) '\u2022');
    }//GEN-LAST:event_jButton2MousePressed

    private void jButton3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MousePressed
        // TODO add your handling code here:

        if (jButton3.getText().equals("S")) {
            jButton3.setText("H");
            jPasswordField1.setEchoChar((char) 0);
            return;
        }
        jButton3.setText("S");
        jPasswordField1.setEchoChar((char) '\u2022');
    }//GEN-LAST:event_jButton3MousePressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jPasswordField2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPasswordField2FocusGained
        // TODO add your handling code here:

    }//GEN-LAST:event_jPasswordField2FocusGained

    private void jPasswordField2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPasswordField2FocusLost
        // TODO add your handling code here:

    }//GEN-LAST:event_jPasswordField2FocusLost

    private void formWindowLostFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowLostFocus
        // TODO add your handling code here:
        this.requestFocus();
    }//GEN-LAST:event_formWindowLostFocus

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        jButton3.setText("S");
        jPasswordField1.setEchoChar((char) '\u2022');
        jButton2.setText("S");
        jPasswordField2.setEchoChar((char) '\u2022');
        System.out.println("Pantalla de condominios de usuario activa");
        jButton4.setVisible(false);
        jButton1.setText("Actualizar Datos");
        FillData();
        disableFields();
        resetFields();
        pantalla_condominios_usuario.setEnabled(true);
        pantalla_condominios_usuario.requestFocus();
        this.setVisible(false);
    }//GEN-LAST:event_formWindowClosed

    private void jButton4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MousePressed
        // TODO add your handling code here:
        jButton4.setVisible(false);
        jButton1.setText("Actualizar Datos");
        FillData();
        disableFields();
    }//GEN-LAST:event_jButton4MousePressed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(ActualizarDatos_Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ActualizarDatos_Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ActualizarDatos_Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ActualizarDatos_Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ActualizarDatos_Usuario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private com.toedter.calendar.JDateChooser jDateChooser1;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane10;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JLayeredPane jLayeredPane3;
    private javax.swing.JLayeredPane jLayeredPane4;
    private javax.swing.JLayeredPane jLayeredPane5;
    private javax.swing.JLayeredPane jLayeredPane7;
    private javax.swing.JLayeredPane jLayeredPane8;
    private javax.swing.JLayeredPane jLayeredPane9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPasswordField jPasswordField2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
