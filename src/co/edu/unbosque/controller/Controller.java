// Controller.java
package co.edu.unbosque.controller;

import co.edu.unbosque.view.*;
import co.edu.unbosque.model.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class Controller {

    private VentanaPrincipal ventanaPrincipal;
    private static final String USUARIO_CORRECTO = "admin";
    private static final String CONTRASENA_CORRECTA = "1234";

    public Controller(VentanaPrincipal ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
        configurarListeners();
    }

    private void configurarListeners() {
        // --- Listeners del Panel Inicio Sesion ---
        PanelInicioSesion panelInicioSesion = ventanaPrincipal.getPanelInicioSesion();
        panelInicioSesion.getBotonIniciarSesion().addActionListener(e -> {
            System.out.println("Botón Iniciar Sesión clickeado");
            String usuario = panelInicioSesion.getCampoUsuario().getText();
            char[] passwordChars = panelInicioSesion.getCampoContrasena().getPassword();
            String contrasena = new String(passwordChars);

            System.out.println("Usuario ingresado: " + usuario);
            System.out.println("Contraseña ingresada: " + contrasena);

            if (verificarCredenciales(usuario, contrasena)) {
                ventanaPrincipal.mostrarPanelPrincipal();
            } else {
                JOptionPane.showMessageDialog(ventanaPrincipal, "Inicio de sesión fallido. Usuario o contraseña incorrectos.", "Error", JOptionPane.ERROR_MESSAGE);
            }

            // Limpia el array de caracteres por seguridad
            java.util.Arrays.fill(passwordChars, ' ');
        });

        // --- Listeners del Panel Principal ---
        PanelPrincipal panelPrincipal = ventanaPrincipal.getPanelPrincipal();
        panelPrincipal.getBotonRegistrar().addActionListener(e -> ventanaPrincipal.mostrarPanelRegistro());
        panelPrincipal.getBotonAnalisis().addActionListener(e -> ventanaPrincipal.mostrarPanelAnalisis()); 
        panelPrincipal.getBotonTorneosActivos().addActionListener(e -> ventanaPrincipal.mostrarPanelTorneosActivos()); 

        // --- Listeners del Panel Registro ---
        PanelRegistro panelRegistro = ventanaPrincipal.getPanelRegistro();
        panelRegistro.getBotonRegistrarJugador().addActionListener(e -> ventanaPrincipal.mostrarPanelRegistroJugador());
        panelRegistro.getBotonRegistrarEntrenador().addActionListener(e -> ventanaPrincipal.mostrarPanelRegistroEntrenador());
        panelRegistro.getBotonRegistrarEquipo().addActionListener(e -> ventanaPrincipal.mostrarPanelRegistroEquipo());
        panelRegistro.getBotonRegistrarAdministrador().addActionListener(e -> ventanaPrincipal.mostrarPanelRegistroAdministrador());
        panelRegistro.getBotonRegistrarEmparejamiento().addActionListener(e -> ventanaPrincipal.mostrarPanelRegistrarEmparejamiento());
        panelRegistro.getBotonRegistrarTorneo().addActionListener(e -> ventanaPrincipal.mostrarPanelRegistrarTorneo());
        panelRegistro.getBotonVolver().addActionListener(e -> ventanaPrincipal.mostrarPanelPrincipal());

        // --- Listeners de los demás paneles de registro ---
        PanelRegistroJugador panelRegistroJugador = ventanaPrincipal.getPanelRegistroJugador();
        panelRegistroJugador.getBotonVolver().addActionListener(e -> ventanaPrincipal.mostrarPanelRegistro());

        PanelRegistroEntrenador panelRegistroEntrenador = ventanaPrincipal.getPanelRegistroEntrenador();
        panelRegistroEntrenador.getBotonVolver().addActionListener(e -> ventanaPrincipal.mostrarPanelRegistro());

        PanelRegistroEquipo panelRegistroEquipo = ventanaPrincipal.getPanelRegistroEquipo();
        panelRegistroEquipo.getBotonRegistrar().addActionListener(e -> { /* ... */ });
        panelRegistroEquipo.getBotonModificar().addActionListener(e -> { /* ... */ });
        panelRegistroEquipo.getBotonEliminar().addActionListener(e -> { /* ... */ });
        panelRegistroEquipo.getBotonVolver().addActionListener(e -> ventanaPrincipal.mostrarPanelRegistro());

        PanelRegistroAdministrador panelRegistroAdministrador = ventanaPrincipal.getPanelRegistroAdministrador();
        panelRegistroAdministrador.getBotonRegistrar().addActionListener(e -> { /* ... */ });
        panelRegistroAdministrador.getBotonModificar().addActionListener(e -> { /* ... */ });
        panelRegistroAdministrador.getBotonEliminar().addActionListener(e -> { /* ... */ });
        panelRegistroAdministrador.getBotonVolver().addActionListener(e -> ventanaPrincipal.mostrarPanelRegistro());

        PanelRegistrarEmparejamiento panelRegistrarEmparejamiento = ventanaPrincipal.getPanelRegistrarEmparejamiento();
        panelRegistrarEmparejamiento.getBotonRegistrar().addActionListener(e -> { /* ... */ });
        panelRegistrarEmparejamiento.getBotonModificar().addActionListener(e -> { /* ... */ });
        panelRegistrarEmparejamiento.getBotonEliminar().addActionListener(e -> { /* ... */ });
        panelRegistrarEmparejamiento.getBotonVolver().addActionListener(e -> ventanaPrincipal.mostrarPanelRegistro());

        PanelRegistrarTorneo panelRegistrarTorneo = ventanaPrincipal.getPanelRegistrarTorneo();
        panelRegistrarTorneo.getBotonRegistrar().addActionListener(e -> { /* ... */ });
        panelRegistrarTorneo.getBotonModificar().addActionListener(e -> { /* ... */ });
        panelRegistrarTorneo.getBotonEliminar().addActionListener(e -> { /* ... */ });
        panelRegistrarTorneo.getBotonVolver().addActionListener(e -> ventanaPrincipal.mostrarPanelRegistro());

        // --- Listeners del Panel Analisis ---
        PanelAnalisis panelAnalisis = ventanaPrincipal.getPanelAnalisis();
        panelAnalisis.getBotonVolver().addActionListener(e -> ventanaPrincipal.mostrarPanelPrincipal());


        // --- Listeners del Panel Torneos Activos ---
        PanelTorneosActivos panelTorneosActivos = ventanaPrincipal.getPanelTorneosActivos();
        panelTorneosActivos.getBotonVolver().addActionListener(e -> ventanaPrincipal.mostrarPanelPrincipal());
        panelTorneosActivos.getBotonVerDetalles().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTable tabla = panelTorneosActivos.getTablaTorneos();
                int filaSeleccionada = tabla.getSelectedRow();
                if (filaSeleccionada != -1) {
                    String nombreTorneo = (String) tabla.getValueAt(filaSeleccionada, 0);
                    JOptionPane.showMessageDialog(ventanaPrincipal, "Detalles de: " + nombreTorneo + " (en desarrollo)", "Detalles del Torneo", JOptionPane.INFORMATION_MESSAGE);
                    // Aquí iría la lógica para mostrar los detalles del torneo seleccionado
                } else {
                    JOptionPane.showMessageDialog(ventanaPrincipal, "Por favor, selecciona un torneo para ver los detalles.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        // --- Listeners del Panel Resultados ---
        PanelResultados panelResultados = ventanaPrincipal.getPanelResultados();
        panelResultados.getBotonVolver().addActionListener(e -> ventanaPrincipal.mostrarPanelPrincipal()); // AÑADIR ESTA LÍNEA
    }

    private boolean verificarCredenciales(String usuario, String contrasena) {
        return usuario.equals(USUARIO_CORRECTO) && contrasena.equals(CONTRASENA_CORRECTA);
    }
}