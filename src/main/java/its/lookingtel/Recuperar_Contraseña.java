/*
 * To change this license header, choose License Headers in Project Properties.
 * To thchange this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package its.lookingtel;


import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import javax.swing.WindowConstants;

/**
 *
 * @author Guest Mode
 */
public class Recuperar_Contraseña extends javax.swing.JFrame {

    Usuario user;
    Login_Usuario pantalla_login_usuario;

    public Recuperar_Contraseña() {

        user = new Usuario();
        initComponents();
        this.setLocationRelativeTo(null);
        Correo_Electronico.setName("Correo Electronico");
        Confirmar_Correo_Electronico.setName("Confirmar Correo Electronico");
        jLabel3.requestFocusInWindow();
        getContentPane().setBackground(Color.white);
        jButton1.setBackground(Color.WHITE);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(Color.white);

    }

    void captureScreen(Login_Usuario login) {

        pantalla_login_usuario = login;

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

        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        Correo_Electronico = new javax.swing.JTextField();
        Confirmar_Correo_Electronico = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Recuperar Contraseña");
        setResizable(false);
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

        jLabel3.setFont(new java.awt.Font("Comic Sans MS", 1, 28)); // NOI18N
        jLabel3.setText("Recuperar Contraseña");

        java.awt.GridBagLayout jPanel1Layout = new java.awt.GridBagLayout();
        jPanel1Layout.columnWidths = new int[] {150, 230};
        jPanel1Layout.rowHeights = new int[] {50, 50};
        jPanel1.setLayout(jPanel1Layout);

        jLabel4.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        jLabel4.setText("Confirmar Correo Electronico:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        jPanel1.add(jLabel4, gridBagConstraints);

        jLabel5.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        jLabel5.setText("Ingresar Correo Electronico:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel1.add(jLabel5, gridBagConstraints);

        Correo_Electronico.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        Correo_Electronico.setForeground(new java.awt.Color(102, 102, 102));
        Correo_Electronico.setText("example@something.com");
        Correo_Electronico.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        Correo_Electronico.setPreferredSize(new java.awt.Dimension(140, 22));
        Correo_Electronico.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                Correo_ElectronicoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                Correo_ElectronicoFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel1.add(Correo_Electronico, gridBagConstraints);
        Correo_Electronico.getAccessibleContext().setAccessibleName("");

        Confirmar_Correo_Electronico.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        Confirmar_Correo_Electronico.setForeground(new java.awt.Color(102, 102, 102));
        Confirmar_Correo_Electronico.setText("example@something.com");
        Confirmar_Correo_Electronico.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        Confirmar_Correo_Electronico.setPreferredSize(new java.awt.Dimension(140, 22));
        Confirmar_Correo_Electronico.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                Confirmar_Correo_ElectronicoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                Confirmar_Correo_ElectronicoFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        jPanel1.add(Confirmar_Correo_Electronico, gridBagConstraints);
        Confirmar_Correo_Electronico.getAccessibleContext().setAccessibleName("s");

        jButton1.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jButton1.setText("Enviar Contraseña");
        jButton1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton1MousePressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 575, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(180, 180, 180)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(126, 126, 126)
                        .addComponent(jLabel3)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Correo_ElectronicoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Correo_ElectronicoFocusLost
        // TODO add your handling code here:

        if (Correo_Electronico.getText().isEmpty()) {
            Correo_Electronico.setText("example@something.com");
            Correo_Electronico.setForeground(Color.gray);
        }

    }//GEN-LAST:event_Correo_ElectronicoFocusLost

    private void Correo_ElectronicoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Correo_ElectronicoFocusGained
        // TODO add your handling code here:
        if (Correo_Electronico.getText().equals("example@something.com")) {
            Correo_Electronico.setText("");
            Correo_Electronico.setForeground(Color.black);
        }

    }//GEN-LAST:event_Correo_ElectronicoFocusGained

    private void Confirmar_Correo_ElectronicoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Confirmar_Correo_ElectronicoFocusGained
        // TODO add your handling code here:
        if (Confirmar_Correo_Electronico.getText().equals("example@something.com")) {
            Confirmar_Correo_Electronico.setText("");
            Confirmar_Correo_Electronico.setForeground(Color.black);
        }
    }//GEN-LAST:event_Confirmar_Correo_ElectronicoFocusGained

    private void Confirmar_Correo_ElectronicoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Confirmar_Correo_ElectronicoFocusLost
        // TODO add your handling code here:
        if (Confirmar_Correo_Electronico.getText().isEmpty()) {
            Confirmar_Correo_Electronico.setText("example@something.com");
            Confirmar_Correo_Electronico.setForeground(Color.gray);
        }
    }//GEN-LAST:event_Confirmar_Correo_ElectronicoFocusLost

    private void jButton1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MousePressed
        // TODO add your handling code here:

        //validate que el correo electronico no sea el place holder
        List<Boolean> statuses = new ArrayList<Boolean>();

        statuses.add(ValidatePlaceHolder(Correo_Electronico));
        statuses.add(ValidatePlaceHolder(Confirmar_Correo_Electronico));

        if (statuses.contains(false)) {
            return;
        }

        // validar que el correo electronico introducido sea valido
        statuses.add(ValidateMail(Correo_Electronico));
        statuses.add(ValidateMail(Confirmar_Correo_Electronico));

        if (statuses.contains(false)) {
            return;
        }

        // validate that both mail are equal
        statuses.add(ValidateMailEquality(Correo_Electronico, Confirmar_Correo_Electronico));

        if (statuses.contains(false)) {
            return;
        }

        // validar que el correo electronico exista en la BD
        String data[] = user.Recuperar_Contraseña_Usuario(Correo_Electronico.getText());

        if (data[0] == null) {
            return;
        }

        // enviar el mail
        SendEmail.enviar_correo(data);

    }//GEN-LAST:event_jButton1MousePressed

    private void formWindowLostFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowLostFocus
        // TODO add your handling code here:
        this.requestFocus();
    }//GEN-LAST:event_formWindowLostFocus

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        pantalla_login_usuario.setEnabled(true);
        pantalla_login_usuario.requestFocus();
        Correo_Electronico.setText("example@something.com");
        Correo_Electronico.setForeground(Color.gray);
        Confirmar_Correo_Electronico.setForeground(Color.gray);
        Confirmar_Correo_Electronico.setText("example@something.com");
        jLabel3.requestFocus();
        Correo_Electronico.setBorder(BorderFactory.createLineBorder(Color.black, 3, false));
        Confirmar_Correo_Electronico.setBorder(BorderFactory.createLineBorder(Color.black, 3, false));
        this.dispose();
        
    }//GEN-LAST:event_formWindowClosed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        jLabel3.requestFocus();
    }//GEN-LAST:event_formWindowOpened
    boolean ValidatePlaceHolder(JTextField jtxt) {
        if (jtxt.getText().equals(("example@something.com")) || jtxt.getText().isEmpty()) {
            jtxt.setBorder(BorderFactory.createLineBorder(Color.red, 3, false));
            return false;
        }

        jtxt.setBorder(BorderFactory.createLineBorder(Color.black, 3, false));
        jtxt.repaint();
        return true;
    }

    boolean ValidateMail(JTextField jtxt) {

        if (!jtxt.getText().matches("^[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}$")) {
            jtxt.setBorder(BorderFactory.createLineBorder(Color.red, 3, false));
            JOptionPane.showMessageDialog(null, "El correo electronico introducido no es valido en " + jtxt.getName() + ": " + jtxt.getText(), "Error", 0);
            return false;
        }

        jtxt.setBorder(BorderFactory.createLineBorder(Color.black, 3, false));
        jtxt.repaint();
        return true;
    }

    boolean ValidateMailEquality(JTextField jtxt, JTextField jtxt2) {
        if (!jtxt.getText().equals(jtxt2.getText())) {
            jtxt.setBorder(BorderFactory.createLineBorder(Color.red, 3, false));
            jtxt2.setBorder(BorderFactory.createLineBorder(Color.red, 3, false));
            JOptionPane.showMessageDialog(null, "Los correos electronicos no concuerdan", "Error", 0);

            return false;
        }

        jtxt.setBorder(BorderFactory.createLineBorder(Color.black, 3, false));
        jtxt2.setBorder(BorderFactory.createLineBorder(Color.black, 3, false));
        jtxt.repaint();
        jtxt2.repaint();
        return true;
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
            java.util.logging.Logger.getLogger(Recuperar_Contraseña.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Recuperar_Contraseña.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Recuperar_Contraseña.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Recuperar_Contraseña.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Recuperar_Contraseña().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Confirmar_Correo_Electronico;
    private javax.swing.JTextField Correo_Electronico;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
