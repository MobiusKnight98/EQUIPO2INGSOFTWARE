/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package its.lookingtel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;

/**
 *
 * @author Guest Mode
 */
public class Condominios_Usuario extends javax.swing.JFrame {

    static final HashMap<Integer, String> Ubicacion = new HashMap<Integer, String>();
    Login_Usuario pantalla_login_usuario;
    ActualizarDatos_Usuario pantalla_actualizardatos_usuario;

    public Condominios_Usuario() {

        pantalla_actualizardatos_usuario = new ActualizarDatos_Usuario();
        initComponents();
        getContentPane().setBackground(Color.white);
        jPanel4.setBackground(Color.white);
        jPanel7.setBackground(Color.white);
        this.setLocationRelativeTo(null);
        jScrollPane1.getVerticalScrollBar().setUnitIncrement(220);
        jScrollPane1.getVerticalScrollBar().setPreferredSize(new Dimension(15, 0));
        Reservacion_Usuario.captureScreen(this);
        Reservaciones_Usuario.captureScreen(this);
        Condominio_Component.captureScreen(this);
        jLabel3.setVisible(false);
        displaylogos();

    }

    void GetUbications() {

        try (Connection conn = Conexion_Remota.hikaridatasource.getConnection()) {

            PreparedStatement statement = conn.prepareStatement("SELECT * FROM UBICATION");
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {

                int Id = rs.getInt(1);
                String Pais = rs.getString(2);
                String Estado = rs.getString(3);
                String Ciudad = rs.getString(4);
                Ubicacion.put(Id, Pais + "/" + Estado + "/" + Ciudad);

            }
            rs.close();
            statement.close();
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(Gestion_Administrativa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void dynamicRender() {

        // 1) Query todos los condominios
        System.out.println("JPanel 2 height: " + jPanel2.getHeight());

        int increase = 0;

        for (Condominio condominio : Condominio.getAll()) {

            Condominio_Component comp = new Condominio_Component();
            comp.wireObject(condominio);
            comp.setLocation(jPanel2.getWidth() / 2 - comp.getWidth() / 2, increase);
            jPanel2.add(comp);
            jPanel2.add(Box.createRigidArea(new Dimension(0, 10)));
            increase += 220;

        }

        if (increase > jPanel2.getHeight()) {
            System.out.println("Value of increase reached size:" + increase);
            jPanel2.setPreferredSize(new Dimension(jPanel2.getWidth(), increase));

        }

        jPanel2.revalidate();
        jPanel2.repaint();

        SwingUtilities.invokeLater(() -> {
            jScrollPane1.getVerticalScrollBar().setValue(0);
           
        });

    }

    public void captureScreen(Login_Usuario login) {

        pantalla_login_usuario = login;

    }

    private void displaylogos() {
        try {
            // Load the image from the URL
            URL logout_pic = new URL("https://lookingtel.cellar-c2.services.clever-cloud.com/logout_icon.png");
            URL lookingtel = new URL("https://lookingtel.cellar-c2.services.clever-cloud.com/lookingtel.png");
            URL settings = new URL("https://lookingtel.cellar-c2.services.clever-cloud.com/settings_icon");
            URL bookings = new URL("https://lookingtel.cellar-c2.services.clever-cloud.com/bookings_icon");
            URL filters = new URL("https://lookingtel.cellar-c2.services.clever-cloud.com/filters_icon");

            Image logout_pic_image = ImageIO.read(logout_pic);
            Image lookingtel_image = ImageIO.read(lookingtel);
            Image settings_image = ImageIO.read(settings);
            Image bookings_image = ImageIO.read(bookings);
            Image filters_image = ImageIO.read(filters);

            Image scaledImage_logoutpic = logout_pic_image.getScaledInstance(45, 50,
                    Image.SCALE_SMOOTH);
            Image scaledImage_lookingtel = lookingtel_image.getScaledInstance(jPanel7.getWidth(),
                    jPanel7.getHeight(),
                    Image.SCALE_SMOOTH);

            Image scaledImage_settings = settings_image.getScaledInstance(jLabel1.getWidth(),
                    jLabel1.getHeight(),
                    Image.SCALE_SMOOTH);

            Image scaledImage_bookings = bookings_image.getScaledInstance(jLabel2.getWidth(),
                    jLabel2.getHeight(),
                    Image.SCALE_SMOOTH);

            Image scaledImage_filters = filters_image.getScaledInstance(jLabel3.getWidth(),
                    jLabel3.getHeight(),
                    Image.SCALE_SMOOTH);

            // Create an ImageIcon from the image
            ImageIcon icon_logoutpic = new ImageIcon(scaledImage_logoutpic);
            ImageIcon icon_lookingtel = new ImageIcon(scaledImage_lookingtel);
            ImageIcon icon_settings = new ImageIcon(scaledImage_settings);
            ImageIcon icon_bookings = new ImageIcon(scaledImage_bookings);
            ImageIcon icon_filters = new ImageIcon(scaledImage_filters);

            // Set the icon on the JLabel
            jLabel6.setIcon(icon_logoutpic);
            jLabel9.setIcon(icon_lookingtel);
            jLabel1.setIcon(icon_settings);
            jLabel2.setIcon(icon_bookings);
            jLabel3.setIcon(icon_filters);

        } catch (IOException ex) {
            ex.printStackTrace();
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
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Condominios Usuario");
        setPreferredSize(new java.awt.Dimension(866, 605));
        setResizable(false);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
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

        jLabel4.setFont(new java.awt.Font("Comic Sans MS", 1, 32)); // NOI18N
        jLabel4.setText("Reservar Condominio");

        jPanel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel4MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel4MousePressed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 102, 204));
        jLabel5.setText("Cerrar Sesión");
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel5MousePressed(evt);
            }
        });

        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
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

        java.awt.GridBagLayout jLayeredPane1Layout = new java.awt.GridBagLayout();
        jLayeredPane1Layout.rowHeights = new int[] {35};
        jLayeredPane1.setLayout(jLayeredPane1Layout);

        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel1.setPreferredSize(new java.awt.Dimension(35, 0));
        jLabel1.setRequestFocusEnabled(false);
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel1MousePressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.RELATIVE;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 12);
        jLayeredPane1.add(jLabel1, gridBagConstraints);

        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel2.setPreferredSize(new java.awt.Dimension(35, 0));
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel2MousePressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.RELATIVE;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 12);
        jLayeredPane1.add(jLabel2, gridBagConstraints);

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel3.setMinimumSize(new java.awt.Dimension(35, 0));
        jLabel3.setPreferredSize(new java.awt.Dimension(35, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.RELATIVE;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.weightx = 5.0;
        jLayeredPane1.add(jLabel3, gridBagConstraints);

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setAutoscrolls(true);
        jScrollPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(100, 850));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setAutoscrolls(true);
        jPanel2.setMaximumSize(new java.awt.Dimension(0, 32767));
        jPanel2.setPreferredSize(new java.awt.Dimension(815, 500));
        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.PAGE_AXIS));
        jScrollPane1.setViewportView(jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 531, Short.MAX_VALUE)
                .addContainerGap())
        );

        getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseClicked
        // TODO add your handling code here:
        resetPanel();
        this.setVisible(false);
        pantalla_actualizardatos_usuario.setVisible(false);
        pantalla_login_usuario.setVisible(true);


    }//GEN-LAST:event_jPanel4MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        // TODO add your handling code here:
        resetPanel();
        this.setVisible(false);
        pantalla_actualizardatos_usuario.setVisible(false);
        pantalla_login_usuario.setVisible(true);
        pantalla_login_usuario.requestFocus();
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        // TODO add your handling code here:
        resetPanel();
        this.setVisible(false);
        pantalla_actualizardatos_usuario.setVisible(false);
        pantalla_login_usuario.setVisible(true);
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jPanel4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MousePressed
        // TODO add your handling code here:
        resetPanel();
        this.setVisible(false);
        pantalla_actualizardatos_usuario.setVisible(false);
        pantalla_login_usuario.setVisible(true);

    }//GEN-LAST:event_jPanel4MousePressed

    private void jLabel5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MousePressed
        // TODO add your handling code here:
        resetPanel();
        this.setVisible(false);
        pantalla_actualizardatos_usuario.setVisible(false);
        pantalla_login_usuario.setVisible(true);
    }//GEN-LAST:event_jLabel5MousePressed

    private void jLabel6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MousePressed
        // TODO add your handling code here:
        resetPanel();
        this.setVisible(false);
        pantalla_actualizardatos_usuario.setVisible(false);
        pantalla_login_usuario.setVisible(true);
    }//GEN-LAST:event_jLabel6MousePressed

    private void jLabel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MousePressed
        // TODO add your handling code here:
        pantalla_actualizardatos_usuario.FillData();
        pantalla_actualizardatos_usuario.setVisible(true);
        pantalla_actualizardatos_usuario.captureScreen(this);
        this.setEnabled(false);
    }//GEN-LAST:event_jLabel1MousePressed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        resetPanel();
        pantalla_login_usuario.setVisible(true);
        pantalla_login_usuario.requestFocus();

    }//GEN-LAST:event_formWindowClosed
    public void resetPanel() {
        jPanel2.removeAll();
        jPanel2.setPreferredSize(new Dimension(jPanel2.getWidth(), 500));
        jPanel2.setSize(new Dimension(jPanel2.getWidth(),500));
    }
    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:


    }//GEN-LAST:event_formWindowOpened

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        new Thread(() -> {
            GetUbications();
        }).start();
        SwingUtilities.invokeLater(this::dynamicRender);


    }//GEN-LAST:event_formComponentShown

    private void jLabel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MousePressed
        // TODO add your handling code here:
        this.setEnabled(false);
        Reservaciones_Usuario pantalla_reservaciones_usuario = new Reservaciones_Usuario();
        pantalla_reservaciones_usuario.setVisible(true);
    }//GEN-LAST:event_jLabel2MousePressed

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
            java.util.logging.Logger.getLogger(Condominios_Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Condominios_Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Condominios_Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Condominios_Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Condominios_Usuario().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
