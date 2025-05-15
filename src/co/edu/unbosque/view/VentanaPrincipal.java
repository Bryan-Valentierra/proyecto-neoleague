// VentanaPrincipal.java
package co.edu.unbosque.view;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {

    private PanelInicioSesion panelInicioSesion;
    private PanelPrincipal panelPrincipal;
    private PanelRegistro panelRegistro;
    private PanelAnalisis panelAnalisis;
    private PanelTorneosActivos panelTorneosActivos;
    private PanelResultados panelResultados;
    private PanelRegistroJugador panelRegistroJugador;
    private PanelRegistroEntrenador panelRegistroEntrenador;
    private PanelRegistroEquipo panelRegistroEquipo;
    private PanelRegistroAdministrador panelRegistroAdministrador;
    private PanelRegistrarEmparejamiento panelRegistrarEmparejamiento;
    private PanelRegistrarTorneo panelRegistrarTorneo;
    private PanelDisponibilidadEquipos panelDisponibilidadEquipos; // Nuevo panel

    public VentanaPrincipal() {
        setTitle("Gestión de Torneos E-Sports");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new CardLayout());

        panelInicioSesion = new PanelInicioSesion();
        panelPrincipal = new PanelPrincipal();
        panelRegistro = new PanelRegistro();
        panelAnalisis = new PanelAnalisis();
        panelTorneosActivos = new PanelTorneosActivos();
        panelResultados = new PanelResultados();
        panelRegistroJugador = new PanelRegistroJugador();
        panelRegistroEntrenador = new PanelRegistroEntrenador();
        panelRegistroEquipo = new PanelRegistroEquipo();
        panelRegistroAdministrador = new PanelRegistroAdministrador();
        panelRegistrarEmparejamiento = new PanelRegistrarEmparejamiento();
        panelRegistrarTorneo = new PanelRegistrarTorneo();
        panelDisponibilidadEquipos = new PanelDisponibilidadEquipos(); // Inicializar el nuevo panel

        add(panelInicioSesion, "inicioSesion");
        add(panelPrincipal, "principal");
        add(panelRegistro, "registro");
        add(panelAnalisis, "analisis");
        add(panelTorneosActivos, "torneosActivos");
        add(panelResultados, "resultados");
        add(panelRegistroJugador, "registroJugador");
        add(panelRegistroEntrenador, "registroEntrenador");
        add(panelRegistroEquipo, "registroEquipo");
        add(panelRegistroAdministrador, "registroAdministrador");
        add(panelRegistrarEmparejamiento, "registrarEmparejamiento");
        add(panelRegistrarTorneo, "registrarTorneo");
        add(panelDisponibilidadEquipos, "disponibilidadEquipos"); // Agregar el nuevo panel

        mostrarPanelInicioSesion();
    }

    public void mostrarPanelInicioSesion() {
        CardLayout cl = (CardLayout) getContentPane().getLayout();
        cl.show(getContentPane(), "inicioSesion");
    }

    public void mostrarPanelPrincipal() {
        CardLayout cl = (CardLayout) getContentPane().getLayout();
        cl.show(getContentPane(), "principal");
    }

    public void mostrarPanelRegistro() {
        CardLayout cl = (CardLayout) getContentPane().getLayout();
        cl.show(getContentPane(), "registro");
    }

    public void mostrarPanelAnalisis() {
        CardLayout cl = (CardLayout) getContentPane().getLayout();
        cl.show(getContentPane(), "analisis");
    }

    public void mostrarPanelTorneosActivos() {
        CardLayout cl = (CardLayout) getContentPane().getLayout();
        cl.show(getContentPane(), "torneosActivos");
    }

    public void mostrarPanelResultados() {
        CardLayout cl = (CardLayout) getContentPane().getLayout();
        cl.show(getContentPane(), "resultados");
    }

    public void mostrarPanelRegistroJugador() {
        CardLayout cl = (CardLayout) getContentPane().getLayout();
        cl.show(getContentPane(), "registroJugador");
    }

    public void mostrarPanelRegistroEntrenador() {
        CardLayout cl = (CardLayout) getContentPane().getLayout();
        cl.show(getContentPane(), "registroEntrenador");
    }

    public void mostrarPanelRegistroEquipo() {
        CardLayout cl = (CardLayout) getContentPane().getLayout();
        cl.show(getContentPane(), "registroEquipo");
    }

    public void mostrarPanelRegistroAdministrador() {
        CardLayout cl = (CardLayout) getContentPane().getLayout();
        cl.show(getContentPane(), "registroAdministrador");
    }

    public void mostrarPanelRegistrarEmparejamiento() {
        CardLayout cl = (CardLayout) getContentPane().getLayout();
        cl.show(getContentPane(), "registrarEmparejamiento");
    }

    public void mostrarPanelRegistrarTorneo() {
        CardLayout cl = (CardLayout) getContentPane().getLayout();
        cl.show(getContentPane(), "registrarTorneo");
    }

    public void mostrarPanelDisponibilidadEquipos() {
        CardLayout cl = (CardLayout) getContentPane().getLayout();
        cl.show(getContentPane(), "disponibilidadEquipos");
    }

    public PanelInicioSesion getPanelInicioSesion() {
        return panelInicioSesion;
    }

    public PanelPrincipal getPanelPrincipal() {
        return panelPrincipal;
    }

    public PanelRegistro getPanelRegistro() {
        return panelRegistro;
    }

    public PanelAnalisis getPanelAnalisis() {
        return panelAnalisis;
    }

    public PanelTorneosActivos getPanelTorneosActivos() {
        return panelTorneosActivos;
    }

    public PanelResultados getPanelResultados() {
        return panelResultados;
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

    public PanelDisponibilidadEquipos getPanelDisponibilidadEquipos() {
        return panelDisponibilidadEquipos;
    }
}