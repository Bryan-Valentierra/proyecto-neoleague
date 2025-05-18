package co.edu.unbosque.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PanelPrincipal extends JPanel {

    private JButton botonRegistrar;
    private JButton botonAnalisis;
    private JButton botonTorneosActivos;
    private JButton botonResultados;
    private JButton botonDisponibilidadEquipos;
    private JButton botonCerrarSesion;
    private JLabel etiquetaBienvenida;

    private Dimension botonSize = new Dimension(180, 30);

    // Constructor del panel principal, configura su diseño y añade los botones de la aplicación.
    public PanelPrincipal() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weightx = 0.0;
        gbc.gridx = 0;
        gbc.weighty = 0.0;

        etiquetaBienvenida = new JLabel("Bienvenido al Menú Principal");
        etiquetaBienvenida.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        add(etiquetaBienvenida, gbc);

        botonRegistrar = new JButton("Registrar Persona/Equipo");
        botonRegistrar.setPreferredSize(botonSize);
        gbc.gridy = 1;
        add(botonRegistrar, gbc);

        botonAnalisis = new JButton("Análisis");
        botonAnalisis.setPreferredSize(botonSize);
        gbc.gridy = 2;
        add(botonAnalisis, gbc);

        botonTorneosActivos = new JButton("Torneos Activos");
        botonTorneosActivos.setPreferredSize(botonSize);
        gbc.gridy = 3;
        add(botonTorneosActivos, gbc);

        botonResultados = new JButton("Resultados");
        botonResultados.setPreferredSize(botonSize);
        gbc.gridy = 4;
        add(botonResultados, gbc);

        botonDisponibilidadEquipos = new JButton("Disponibilidad Equipos");
        botonDisponibilidadEquipos.setPreferredSize(botonSize);
        gbc.gridy = 5;
        add(botonDisponibilidadEquipos, gbc);

        botonCerrarSesion = new JButton("Cerrar Sesión");
        botonCerrarSesion.setPreferredSize(botonSize);
        gbc.gridy = 6;
        add(botonCerrarSesion, gbc);
    }

    // Devuelve el botón para registrar personas o equipos.
    public JButton getBotonRegistrar() {
        return botonRegistrar;
    }

    // Devuelve el botón para acceder a la funcionalidad de análisis.
    public JButton getBotonAnalisis() {
        return botonAnalisis;
    }

    // Devuelve el botón para ver los torneos activos.
    public JButton getBotonTorneosActivos() {
        return botonTorneosActivos;
    }

    // Devuelve el botón para ver los resultados de los torneos.
    public JButton getBotonResultados() {
        return botonResultados;
    }

    // Devuelve el botón para ver la disponibilidad de los equipos.
    public JButton getBotonDisponibilidadEquipos() {
        return botonDisponibilidadEquipos;
    }

    // Devuelve el botón para cerrar la sesión actual.
    public JButton getBotonCerrarSesion() {
        return botonCerrarSesion;
    }

    // Asigna un ActionListener a cada uno de los botones del panel principal.
    public void agregarActionListener(ActionListener listener) {
        botonRegistrar.addActionListener(listener);
        botonAnalisis.addActionListener(listener);
        botonTorneosActivos.addActionListener(listener);
        botonResultados.addActionListener(listener);
        botonDisponibilidadEquipos.addActionListener(listener);
        botonCerrarSesion.addActionListener(listener);
    }
}