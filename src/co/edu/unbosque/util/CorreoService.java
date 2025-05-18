package co.edu.unbosque.util;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class CorreoService {

    private static final String REMITENTE = "tu_correo@example.com";
    private static final String CONTRASENA = "tu_contrasena";
    private static final String SMTP_HOST = "smtp.example.com";
    private static final String SMTP_PORT = "587";
    private static final boolean USAR_TLS = true;

    // Configura las propiedades necesarias para enviar correos electrónicos y gestiona el proceso de envío.
    public static boolean enviarCorreo(String destinatario, String asunto, String cuerpo) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        if (USAR_TLS) {
            props.put("mail.smtp.starttls.enable", "true");
        } else {
            props.put("mail.smtp.ssl.enable", "true");
        }
        props.put("mail.smtp.host", SMTP_HOST);
        props.put("mail.smtp.port", SMTP_PORT);

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(REMITENTE, CONTRASENA);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(REMITENTE));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            message.setSubject(asunto);
            message.setText(cuerpo);

            Transport.send(message);
            System.out.println("Correo enviado a " + destinatario + " usando el servidor " + SMTP_HOST);
            return true;

        } catch (MessagingException e) {
            System.err.println("Error al enviar correo a " + destinatario + " usando el servidor " + SMTP_HOST + ": " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Método principal para probar la funcionalidad de envío de correos electrónicos.
    public static void main(String[] args) {
        boolean exito = enviarCorreo(
                "b.talero02@gmail.com",
                "Correo de prueba ",
                "Este es un mensaje de prueba  ."
        );

        if (exito) {
            System.out.println("El correo se envió correctamente.");
        } else {
            System.out.println("Hubo un error al enviar el correo.");
        }
    }
}