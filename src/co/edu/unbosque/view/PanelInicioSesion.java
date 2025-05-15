// PanelInicioSesion.java
package co.edu.unbosque.view;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PanelInicioSesion extends JPanel {

    private JLabel etiquetaBienvenido;
    private JLabel etiquetaUsuario;
    private JTextField campoUsuario;
    private JLabel etiquetaContrasena;
    private JPasswordField campoContrasena;
    private JButton botonIniciarSesion;
    private JLabel etiquetaDisponibilidadEquipos;
    private JLabel etiquetaEmparejamientos;
    private JLabel etiquetaHistoricoPartidas;
    private JLabel mensajeError;
    private JComboBox<String> comboBoxFechasTorneos;
    private JLabel etiquetaFechasTorneos;

    public PanelInicioSesion() {
        setLayout(null);

        etiquetaBienvenido = new JLabel("¡Bienvenido!");
        etiquetaUsuario = new JLabel("Usuario:");
        campoUsuario = new JTextField(20);
        etiquetaContrasena = new JLabel("Contraseña:");
        campoContrasena = new JPasswordField(20);
        botonIniciarSesion = new JButton("Iniciar Sesión");
        etiquetaDisponibilidadEquipos = new JLabel("Disponibilidad Equipos");
        etiquetaEmparejamientos = new JLabel("Emparejamientos");
        etiquetaHistoricoPartidas = new JLabel("Histórico Partidas");
        mensajeError = new JLabel("");
        mensajeError.setForeground(Color.RED);
        etiquetaFechasTorneos = new JLabel("Fechas de Torneos:");
        comboBoxFechasTorneos = new JComboBox<>();
        comboBoxFechasTorneos.addItem("No hay torneos registrados");

        etiquetaBienvenido.setBounds(50, 20, 100, 20);
        etiquetaUsuario.setBounds(50, 60, 80, 20);
        campoUsuario.setBounds(150, 60, 150, 25);
        etiquetaContrasena.setBounds(50, 100, 80, 20);
        campoContrasena.setBounds(150, 100, 150, 25);
        botonIniciarSesion.setBounds(150, 140, 120, 30);
        etiquetaDisponibilidadEquipos.setBounds(50, 190, 150, 20); // Ajuste de posición
        etiquetaEmparejamientos.setBounds(50, 220, 120, 20); // Ajuste de posición
        etiquetaHistoricoPartidas.setBounds(50, 250, 120, 20); // Ajuste de posición
        mensajeError.setBounds(50, 175, 300, 20);
        etiquetaFechasTorneos.setBounds(50, 280, 120, 20); // Ajuste de posición
        comboBoxFechasTorneos.setBounds(180, 280, 200, 30); // Ajuste de posición

        add(etiquetaBienvenido);
        add(etiquetaUsuario);
        add(campoUsuario);
        add(etiquetaContrasena);
        add(campoContrasena);
        add(botonIniciarSesion);
        add(etiquetaDisponibilidadEquipos);
        add(etiquetaEmparejamientos);
        add(etiquetaHistoricoPartidas);
        add(mensajeError);
        add(etiquetaFechasTorneos);
        add(comboBoxFechasTorneos);
    }

    public JTextField getCampoUsuario() {
        return campoUsuario;
    }

    public JPasswordField getCampoContrasena() {
        return campoContrasena;
    }

    public JButton getBotonIniciarSesion() {
        return botonIniciarSesion;
    }

    public void limpiarFormulario() {
        campoUsuario.setText("");
        campoContrasena.setText("");
        mensajeError.setText("");
    }

    public void mostrarMensaje(String mensaje) {
        mensajeError.setText(mensaje);
    }

    public void actualizarComboBoxTorneos(List<String> fechas) {
        comboBoxFechasTorneos.removeAllItems();
        if (fechas == null || fechas.isEmpty()) {
            comboBoxFechasTorneos.addItem("No hay torneos registrados");
        } else {
            for (String fecha : fechas) {
                comboBoxFechasTorneos.addItem(fecha);
            }
        }
    }
    


public JComboBox<String> getComboBoxFechasTorneos() {
    return comboBoxFechasTorneos;
}
}