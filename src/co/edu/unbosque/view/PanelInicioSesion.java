// PanelInicioSesion.java
package co.edu.unbosque.view;

import javax.swing.*;
import java.awt.*;

public class PanelInicioSesion extends JPanel {

    private JLabel etiquetaBienvenido;
    private JLabel etiquetaUsuario;
    private JTextField campoUsuario;
    private JLabel etiquetaContrasena;
    private JPasswordField campoContrasena;
    private JButton botonIniciarSesion;
    private JLabel etiquetaInformacionTorneos;
    private JButton botonTorneosActivos;
    private JButton botonResultados;
    private JButton botonPremiaciones;
    private JLabel etiquetaCalendarios;
    private JLabel etiquetaDisponibilidadEquipos;
    private JLabel etiquetaEmparejamientos;
    private JLabel etiquetaHistoricoPartidas;

    public PanelInicioSesion() {
        // 1. Establecer el LayoutManager del panel
        setLayout(null); // Usamos null layout para control preciso (puedes cambiarlo)

        // 2. Inicializar los componentes
        etiquetaBienvenido = new JLabel("Bienvenido!");
        etiquetaUsuario = new JLabel("Usuario:");
        campoUsuario = new JTextField(20);
        etiquetaContrasena = new JLabel("Contraseña:");
        campoContrasena = new JPasswordField(20);
        botonIniciarSesion = new JButton("Iniciar Sesión");
        etiquetaInformacionTorneos = new JLabel("Información de Torneos");
        botonTorneosActivos = new JButton("Torneos Activos");
        botonResultados = new JButton("Resultados");
        botonPremiaciones = new JButton("Premiaciones");
        etiquetaCalendarios = new JLabel("Calendarios");
        etiquetaDisponibilidadEquipos = new JLabel("Disponibilidad Equipos");
        etiquetaEmparejamientos = new JLabel("Emparejamientos");
        etiquetaHistoricoPartidas = new JLabel("Histórico Partidas");

        // 3. Establecer las posiciones y tamaños de los componentes (¡¡¡SI USAS NULL LAYOUT!!!)
        etiquetaBienvenido.setBounds(50, 20, 100, 20); // x, y, ancho, alto
        etiquetaUsuario.setBounds(50, 60, 80, 20);
        campoUsuario.setBounds(150, 60, 150, 25);
        etiquetaContrasena.setBounds(50, 100, 80, 20);
        campoContrasena.setBounds(150, 100, 150, 25);
        botonIniciarSesion.setBounds(150, 140, 120, 30);
        etiquetaInformacionTorneos.setBounds(50, 200, 150, 20);
        botonTorneosActivos.setBounds(50, 230, 120, 30);
        botonResultados.setBounds(180, 230, 120, 30);
        botonPremiaciones.setBounds(310, 230, 120, 30);
        etiquetaCalendarios.setBounds(50, 280, 100, 20);
        etiquetaDisponibilidadEquipos.setBounds(50, 310, 150, 20);
        etiquetaEmparejamientos.setBounds(50, 340, 120, 20);
        etiquetaHistoricoPartidas.setBounds(50, 370, 120, 20);

        // 4. Añadir los componentes al panel
        add(etiquetaBienvenido);
        add(etiquetaUsuario);
        add(campoUsuario);
        add(etiquetaContrasena);
        add(campoContrasena);
        add(botonIniciarSesion);
        add(etiquetaInformacionTorneos);
        add(botonTorneosActivos);
        add(botonResultados);
        add(botonPremiaciones);
        add(etiquetaCalendarios);
        add(etiquetaDisponibilidadEquipos);
        add(etiquetaEmparejamientos);
        add(etiquetaHistoricoPartidas);
    }

    // Getters para los componentes
    public JTextField getCampoUsuario() {
        return campoUsuario;
    }

    public JPasswordField getCampoContrasena() {
        return campoContrasena;
    }

    public JButton getBotonIniciarSesion() {
        return botonIniciarSesion;
    }
}