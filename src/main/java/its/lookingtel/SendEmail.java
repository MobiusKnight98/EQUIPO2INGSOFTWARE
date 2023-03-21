/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package its.lookingtel;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

/**
 *
 * @author Guest Mode
 */
public class SendEmail {

    public static void enviar_correo(String[] data) {

        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("enriquehernandez.pereyra@gmail.com", "ypilqtzfdwywdmtp");

            }

        });

        // Used to debug SMTP issues
        session.setDebug(true) ;

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress("enriquehernandez.pereyra@gmail.com"));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(data[0]));

            // Set Subject: header field
            message.setSubject("LookingTel Recuperacion de contraseña");

            // Now set the actual message
            message.setText("Hola Cliente su contraseña es: "+data[1]);
            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
            JOptionPane.showMessageDialog(null, "Contraseña enviada satisfactoriamente al correo electronico proporcionado","Success",1);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }

}
