package co.edu.unbosque.util;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class CorreoService {

    private static final String REMITENTE = "tu_correo@example.com";  // Cambiar
    private static final String CONTRASENA = "tu_contrasena";  // Cambiar (¡NUNCA GUARDAR EN EL CÓDIGO!)
    private static final String SMTP_HOST = "smtp.example.com";  // Cambiar
    private static final String SMTP_PORT = "587";           // Cambiar, usualmente 587 para TLS o 465 para SSL
    private static final boolean USAR_TLS = true; // Cambiar a false si tu servidor usa SSL

    /**
     * Envía un correo electrónico.
     *
     * @param destinatario La dirección de correo electrónico del destinatario.
     * @param asunto El asunto del correo electrónico.
     * @param cuerpo El cuerpo del correo electrónico.
     * @return true si el correo se envió con éxito, false si hubo un error.
     */
    public static boolean enviarCorreo(String destinatario, String asunto, String cuerpo) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        if (USAR_TLS) {
            props.put("mail.smtp.starttls.enable", "true"); // Para TLS
        } else {
            props.put("mail.smtp.ssl.enable", "true");       // Para SSL
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

    public static void main(String[] args) {
        // Ejemplo de uso
        boolean exito = enviarCorreo(
                "b.talero02@gmail.com",  // Reemplazar con un correo real para probar
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
