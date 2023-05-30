/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package its.lookingtel;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Guest Mode
 */
public class Condominio_Component extends JPanel {

    private Condominio condominio;
    Reservacion_Usuario pantalla_reservacion_usuario;
    private static Condominios_Usuario pantalla_condominios_usuario;

    Condominio_Component() {
        // set inchangeable default properties

        this.setPreferredSize(new Dimension(830, 210));
        this.setMaximumSize(new Dimension(830, 210));
        this.setMinimumSize(new Dimension(830, 210));
        this.setBackground(Color.white);
        pantalla_reservacion_usuario = new Reservacion_Usuario();

    }
    
    public static void captureScreen(Condominios_Usuario screen){
        pantalla_condominios_usuario = screen;
    }

    public void wireObject(Condominio condominio) {
        this.condominio = condominio;
        System.out.println("Component condominio name: " + this.condominio.nombre);
        FillComponent();
    }

    private void FillComponent() {

        this.setLayout(new GridBagLayout());

        JPanel left_side = new JPanel();
        left_side.setBackground(Color.white);
        left_side.setPreferredSize(new Dimension(300, 200));
        left_side.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        GridBagConstraints leftConstraints = new GridBagConstraints();
        leftConstraints.gridx = 0; // set the x position to 0
        leftConstraints.gridy = 0; // set the y position to 0
        //   leftConstraints.fill = GridBagConstraints.BOTH; // allow the panel to fill both horizontally and vertically
        leftConstraints.weightx = 0.0; // set the horizontal weight to 1
        leftConstraints.weighty = 0.0; // set the vertical weight to 1
        this.add(left_side, leftConstraints);
        prepopulateleft(left_side);

        JPanel right_side = new JPanel();
        right_side.setBackground(Color.white);
        right_side.setPreferredSize(new Dimension(500, 200));
        right_side.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        GridBagConstraints rightConstraints = new GridBagConstraints();
        rightConstraints.gridx = 1; // set the x position to 0
        rightConstraints.gridy = 0; // set the y position to 0
        //  rightConstraints.fill = GridBagConstraints.BOTH; // allow the panel to fill both horizontally and vertically
        rightConstraints.weightx = 0.0; // set the horizontal weight to 1
        rightConstraints.weighty = 0.0; // set the vertical weight to 1
        this.add(right_side, rightConstraints);
        prepopulateright(right_side);

    }

    private void prepopulateleft(JPanel comp) {

        try {
            JLabel pic = new JLabel();
            pic.setPreferredSize(new Dimension(290, 185));
            // Load the image from the URL
            URL imageUrl = new URL(this.condominio.image_lugar);
            Image image = ImageIO.read(imageUrl);
            Image scaledImage = image.getScaledInstance(pic.getPreferredSize().width,
                    pic.getPreferredSize().height,
                    Image.SCALE_SMOOTH);

            // Create an ImageIcon from the image
            ImageIcon icon = new ImageIcon(scaledImage);

            // Set the icon on the JLabel
            pic.setIcon(icon);
            comp.add(pic);

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "No se pueden mostrar las imagenes", "Error", 0);
        }

    }

    private void prepopulateright(JPanel comp) {

        comp.setLayout(new GridBagLayout());

        // title
        JLabel title = new JLabel(this.condominio.nombre);
        title.setForeground(Color.black);
        title.setFont(new Font("Comic Sans MS", Font.BOLD, 22));
        GridBagConstraints titleConstraints = new GridBagConstraints();
        titleConstraints.gridx = 0; // set the x position to 0
        titleConstraints.gridy = 0; // set the y position to 0
        comp.add(title, titleConstraints);

        // textarea
        JTextArea description = new JTextArea(this.condominio.direccion);
        description.setBackground(Color.white);
        description.setEditable(false);
        description.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        description.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(description);
        scrollPane.setPreferredSize(new Dimension(200, 50));
        description.setLineWrap(true);
        description.setWrapStyleWord(true);
        description.setPreferredSize(new Dimension(200, 50));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        GridBagConstraints descriptionConstraints = new GridBagConstraints();
        descriptionConstraints.gridx = 0; // set the x position to 0
        descriptionConstraints.gridy = 1; // set the y position to 0
        comp.add(description, descriptionConstraints);

        // jlabel price
        JLabel precio_noche = new JLabel("Precio x noche: " + " $ " + this.condominio.precio_x_noche);
        precio_noche.setForeground(Color.black);
        precio_noche.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
        GridBagConstraints precio_nocheConstraints = new GridBagConstraints();
        precio_nocheConstraints.gridx = 0; // set the x position to 0
        precio_nocheConstraints.gridy = 2; // set the y position to 0
        comp.add(precio_noche, precio_nocheConstraints);

        // jlabel ubicacion
        String ubicacion_map = Condominios_Usuario.Ubicacion.get(this.condominio.Ubicacion);
        JLabel ubicacion = new JLabel("Ubicación: " + ubicacion_map);
        ubicacion.setForeground(Color.black);
        ubicacion.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
        GridBagConstraints ubicacionConstraints = new GridBagConstraints();
        ubicacionConstraints.gridx = 0; // set the x position to 0
        ubicacionConstraints.gridy = 3; // set the y position to 0
        comp.add(ubicacion, ubicacionConstraints);

        // jlabel No.Habitaciones
        JLabel no_habitaciones = new JLabel("No.Habitaciones: " + String.valueOf(this.condominio.No_Habitaciones));
        no_habitaciones.setForeground(Color.black);
        no_habitaciones.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
        GridBagConstraints no_habitacionesConstraints = new GridBagConstraints();
        no_habitacionesConstraints.gridx = 0; // set the x position to 0
        no_habitacionesConstraints.gridy = 4; // set the y position to 0
        comp.add(no_habitaciones, no_habitacionesConstraints);

        // jlabel Score
        JLabel score = new JLabel("Puntaje: " + " %" + String.valueOf(this.condominio.score));
        score.setForeground(Color.black);
        score.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
        GridBagConstraints scoreConstraints = new GridBagConstraints();
        scoreConstraints.gridx = 0; // set the x position to 0
        scoreConstraints.gridy = 5; // set the y position to 0
        comp.add(score, scoreConstraints);

        // button reservar
        JButton reservar = new JButton("Reservar");
        reservar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        reservar.setBackground(Color.white);
        reservar.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
        reservar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        reservar.setPreferredSize(new Dimension(120, 40));
        GridBagConstraints reservarConstraints = new GridBagConstraints();
        reservarConstraints.gridx = 0; // set the x position to 0
        reservarConstraints.gridy = 6; // set the y position to 0
        comp.add(reservar, reservarConstraints);

        reservar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pantalla_condominios_usuario.setEnabled(false);
                Reservacion_Usuario.setUbicacion(ubicacion_map);
                pantalla_reservacion_usuario.displayData(condominio);
                pantalla_reservacion_usuario.setVisible(true);

            }
        });

    }

    @Override
    public void setLocation(int x, int y) {

        super.setLocation(x, y);

    }

}
