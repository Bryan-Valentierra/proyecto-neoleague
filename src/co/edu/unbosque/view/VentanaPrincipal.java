package co.edu.unbosque.view;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {

    private PanelInicioSesion panelInicioSesion;
    private PanelPrincipal panelPrincipal;
    private PanelRegistro panelRegistro;
    private PanelAnalisis panelAnalisis;
    private PanelResultados panelResultados;
    private PanelRegistroJugador panelRegistroJugador;
    private PanelRegistroEntrenador panelRegistroEntrenador;
    private PanelRegistroEquipo panelRegistroEquipo;
    private PanelRegistroAdministrador panelRegistroAdministrador;
    private PanelRegistrarEmparejamiento panelRegistrarEmparejamiento;
    private PanelRegistrarTorneo panelRegistrarTorneo;
    private PanelTorneosActivos panelTorneosActivos; // Declarado
    private PanelDisponibilidadEquipos panelDisponibilidadEquipos;

    // Constructor de la ventana principal de la aplicación.
    public VentanaPrincipal() {
        setTitle("Gestión de Torneos E-Sports");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new CardLayout());

        // Inicialización de todos los paneles
        panelInicioSesion = new PanelInicioSesion();
        panelPrincipal = new PanelPrincipal();
        panelRegistro = new PanelRegistro();
        panelAnalisis = new PanelAnalisis();
        panelResultados = new PanelResultados();
        panelRegistroJugador = new PanelRegistroJugador();
        panelRegistroEntrenador = new PanelRegistroEntrenador();
        panelRegistroEquipo = new PanelRegistroEquipo();
        panelRegistroAdministrador = new PanelRegistroAdministrador();
        panelRegistrarEmparejamiento = new PanelRegistrarEmparejamiento();
        panelRegistrarTorneo = new PanelRegistrarTorneo();
        panelTorneosActivos = new PanelTorneosActivos(); // **CORRECCIÓN AQUÍ: Inicializar el panel**
        panelDisponibilidadEquipos = new PanelDisponibilidadEquipos();

        // Agregar todos los paneles al CardLayout
        add(panelInicioSesion, "inicioSesion");
        add(panelPrincipal, "principal");
        add(panelRegistro, "registro");
        add(panelAnalisis, "analisis");
        add(panelResultados, "resultados");
        add(panelRegistroJugador, "registroJugador");
        add(panelRegistroEntrenador, "registroEntrenador");
        add(panelRegistroEquipo, "registroEquipo");
        add(panelRegistroAdministrador, "registroAdministrador");
        add(panelRegistrarEmparejamiento, "registrarEmparejamiento");
        add(panelRegistrarTorneo, "registrarTorneo");
        add(panelTorneosActivos, "torneosActivos"); // **CORRECCIÓN AQUÍ: Agregar el panel al layout**
        add(panelDisponibilidadEquipos, "disponibilidadEquipos");

        mostrarPanelInicioSesion();
    }

    // Muestra el panel de inicio de sesión.
    public void mostrarPanelInicioSesion() {
        CardLayout cl = (CardLayout) getContentPane().getLayout();
        cl.show(getContentPane(), "inicioSesion");
    }

    // Muestra el panel principal.
    public void mostrarPanelPrincipal() {
        CardLayout cl = (CardLayout) getContentPane().getLayout();
        cl.show(getContentPane(), "principal");
    }

    // Muestra el panel de registro.
    public void mostrarPanelRegistro() {
        CardLayout cl = (CardLayout) getContentPane().getLayout();
        cl.show(getContentPane(), "registro");
    }

    // Muestra el panel de análisis.
    public void mostrarPanelAnalisis() {
        CardLayout cl = (CardLayout) getContentPane().getLayout();
        cl.show(getContentPane(), "analisis");
    }

    // Muestra el panel de torneos activos.
    public void mostrarPanelTorneosActivos() {
        CardLayout cl = (CardLayout) getContentPane().getLayout();
        cl.show(getContentPane(), "torneosActivos");
    }

    // Muestra el panel de resultados.
    public void mostrarPanelResultados() {
        CardLayout cl = (CardLayout) getContentPane().getLayout();
        cl.show(getContentPane(), "resultados");
    }

    // Muestra el panel de registro de jugador.
    public void mostrarPanelRegistroJugador() {
        CardLayout cl = (CardLayout) getContentPane().getLayout();
        cl.show(getContentPane(), "registroJugador");
    }

    // Muestra el panel de registro de entrenador.
    public void mostrarPanelRegistroEntrenador() {
        CardLayout cl = (CardLayout) getContentPane().getLayout();
        cl.show(getContentPane(), "registroEntrenador");
    }

    // Muestra el panel de registro de equipo.
    public void mostrarPanelRegistroEquipo() {
        CardLayout cl = (CardLayout) getContentPane().getLayout();
        cl.show(getContentPane(), "registroEquipo");
    }

    // Muestra el panel de registro de administrador.
    public void mostrarPanelRegistroAdministrador() {
        CardLayout cl = (CardLayout) getContentPane().getLayout();
        cl.show(getContentPane(), "registroAdministrador");
    }

    // Muestra el panel de registrar emparejamiento.
    public void mostrarPanelRegistrarEmparejamiento() {
        CardLayout cl = (CardLayout) getContentPane().getLayout();
        cl.show(getContentPane(), "registrarEmparejamiento");
    }

    // Muestra el panel de registrar torneo.
    public void mostrarPanelRegistrarTorneo() {
        CardLayout cl = (CardLayout) getContentPane().getLayout();
        cl.show(getContentPane(), "registrarTorneo");
    }

    // Muestra el panel de disponibilidad de equipos.
    public void mostrarPanelDisponibilidadEquipos() {
        CardLayout cl = (CardLayout) getContentPane().getLayout();
        cl.show(getContentPane(), "disponibilidadEquipos");
    }

    // Devuelve el panel de inicio de sesión.
    public PanelInicioSesion getPanelInicioSesion() {
        return panelInicioSesion;
    }

    // Devuelve el panel principal.
    public PanelPrincipal getPanelPrincipal() {
        return panelPrincipal;
    }

    // Devuelve el panel de registro.
    public PanelRegistro getPanelRegistro() {
        return panelRegistro;
    }

    // Devuelve el panel de análisis.
    public PanelAnalisis getPanelAnalisis() {
        return panelAnalisis;
    }

    // Devuelve el panel de resultados.
    public PanelResultados getPanelResultados() {
        return panelResultados;
    }

    // Devuelve el panel de registro de jugador.
    public PanelRegistroJugador getPanelRegistroJugador() {
        return panelRegistroJugador;
    }

    // Devuelve el panel de registro de entrenador.
    public PanelRegistroEntrenador getPanelRegistroEntrenador() {
        return panelRegistroEntrenador;
    }
    
    // Devuelve el panel de torneos activos. (El comentario anterior era incorrecto)
    public PanelTorneosActivos getPanelTorneosActivos() {
        return panelTorneosActivos;
    }

    // Devuelve el panel de registro de equipo.
    public PanelRegistroEquipo getPanelRegistroEquipo() {
        return panelRegistroEquipo;
    }

    // Devuelve el panel de registro de administrador.
    public PanelRegistroAdministrador getPanelRegistroAdministrador() {
        return panelRegistroAdministrador;
    }

    // Devuelve el panel de registrar emparejamiento.
    public PanelRegistrarEmparejamiento getPanelRegistrarEmparejamiento() {
        return panelRegistrarEmparejamiento;
    }

    // Devuelve el panel de registrar torneo.
    public PanelRegistrarTorneo getPanelRegistrarTorneo() {
        return panelRegistrarTorneo;
    }

    // Devuelve el panel de disponibilidad de equipos.
    public PanelDisponibilidadEquipos getPanelDisponibilidadEquipos() {
        return panelDisponibilidadEquipos;
    }
}