// VentanaPrincipal.java
package co.edu.unbosque.view;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {

    private PanelInicioSesion panelInicioSesion;
    private PanelPrincipal panelPrincipal;
    private PanelRegistro panelRegistro;
    private PanelRegistroJugador panelRegistroJugador;
    private PanelRegistroEntrenador panelRegistroEntrenador;
    private PanelRegistroEquipo panelRegistroEquipo;
    private PanelRegistroAdministrador panelRegistroAdministrador;
    private PanelRegistrarEmparejamiento panelRegistrarEmparejamiento;
    private PanelRegistrarTorneo panelRegistrarTorneo;
    private PanelAnalisis panelAnalisis;
    private PanelTorneosActivos panelTorneosActivos;
    private PanelResultados panelResultados; // Nuevo panel

    private CardLayout cardLayout; // Layout para cambiar entre paneles

    public VentanaPrincipal() {
        // 1. Inicializar el CardLayout
        cardLayout = new CardLayout();

        // 2. Establecer el CardLayout como el LayoutManager del contentPane
        getContentPane().setLayout(cardLayout);

        // 3. Inicializar los paneles
        panelInicioSesion = new PanelInicioSesion();
        panelPrincipal = new PanelPrincipal();
        panelRegistro = new PanelRegistro();
        panelRegistroJugador = new PanelRegistroJugador();
        panelRegistroEntrenador = new PanelRegistroEntrenador();
        panelRegistroEquipo = new PanelRegistroEquipo();
        panelRegistroAdministrador = new PanelRegistroAdministrador();
        panelRegistrarEmparejamiento = new PanelRegistrarEmparejamiento();
        panelRegistrarTorneo = new PanelRegistrarTorneo();
        panelAnalisis = new PanelAnalisis();
        panelTorneosActivos = new PanelTorneosActivos();
        panelResultados = new PanelResultados(); // Inicializar

        // 4. Añadir los paneles al contentPane con nombres para el CardLayout
        getContentPane().add(panelInicioSesion, "InicioSesion");
        getContentPane().add(panelPrincipal, "Principal");
        getContentPane().add(panelRegistro, "Registro");
        getContentPane().add(panelRegistroJugador, "RegistroJugador");
        getContentPane().add(panelRegistroEntrenador, "RegistroEntrenador");
        getContentPane().add(panelRegistroEquipo, "RegistroEquipo");
        getContentPane().add(panelRegistroAdministrador, "RegistroAdministrador");
        getContentPane().add(panelRegistrarEmparejamiento, "RegistrarEmparejamiento");
        getContentPane().add(panelRegistrarTorneo, "RegistrarTorneo");
        getContentPane().add(panelAnalisis, "Analisis");
        getContentPane().add(panelTorneosActivos, "TorneosActivos");
        getContentPane().add(panelResultados, "Resultados"); // Añadir

        // 5. Mostrar el panel de inicio de sesión al principio
        mostrarPanelInicioSesion();
    }

    // Métodos para cambiar entre paneles usando CardLayout
    public void mostrarPanelInicioSesion() {
        cardLayout.show(getContentPane(), "InicioSesion");
    }

    public void mostrarPanelPrincipal() {
        cardLayout.show(getContentPane(), "Principal");
    }

    public void mostrarPanelRegistro() {
        cardLayout.show(getContentPane(), "Registro");
    }

    public void mostrarPanelRegistroJugador() {
        cardLayout.show(getContentPane(), "RegistroJugador");
    }

    public void mostrarPanelRegistroEntrenador() {
        cardLayout.show(getContentPane(), "RegistroEntrenador");
    }

    public void mostrarPanelRegistroEquipo() {
        cardLayout.show(getContentPane(), "RegistroEquipo");
    }

    public void mostrarPanelRegistroAdministrador() {
        cardLayout.show(getContentPane(), "RegistroAdministrador");
    }

    public void mostrarPanelRegistrarEmparejamiento() {
        cardLayout.show(getContentPane(), "RegistrarEmparejamiento");
    }

    public void mostrarPanelRegistrarTorneo() {
        cardLayout.show(getContentPane(), "RegistrarTorneo");
    }

    public void mostrarPanelAnalisis() {
        cardLayout.show(getContentPane(), "Analisis");
    }

    public void mostrarPanelTorneosActivos() {
        cardLayout.show(getContentPane(), "TorneosActivos");
    }

    public void mostrarPanelResultados() { // Nuevo método
        System.out.println("VentanaPrincipal: Método mostrarPanelResultados() llamado.");
        cardLayout.show(getContentPane(), "Resultados");
        System.out.println("VentanaPrincipal: CardLayout mostrando 'Resultados'.");
    }

    // Getters para los paneles
    public PanelInicioSesion getPanelInicioSesion() {
        return panelInicioSesion;
    }

    public PanelPrincipal getPanelPrincipal() {
        return panelPrincipal;
    }

    public PanelRegistro getPanelRegistro() {
        return panelRegistro;
    }

    public PanelRegistroJugador getPanelRegistroJugador() {
        return panelRegistroJugador;
    }

    public PanelRegistroEntrenador getPanelRegistroEntrenador() {
        return panelRegistroEntrenador;
    }

    public PanelRegistroEquipo getPanelRegistroEquipo() {
        return panelRegistroEquipo;
    }

    public PanelRegistroAdministrador getPanelRegistroAdministrador() {
        return panelRegistroAdministrador;
    }

    public PanelRegistrarEmparejamiento getPanelRegistrarEmparejamiento() {
        return panelRegistrarEmparejamiento;
    }

    public PanelRegistrarTorneo getPanelRegistrarTorneo() {
        return panelRegistrarTorneo;
    }

    public PanelAnalisis getPanelAnalisis() {
        return panelAnalisis;
    }

    public PanelTorneosActivos getPanelTorneosActivos() {
        return panelTorneosActivos;
    }

    public PanelResultados getPanelResultados() { // Nuevo getter
        return panelResultados;
    }
}