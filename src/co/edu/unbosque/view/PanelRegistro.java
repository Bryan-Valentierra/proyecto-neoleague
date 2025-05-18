package co.edu.unbosque.view;

import javax.swing.*;
import java.awt.*;

public class PanelRegistro extends JPanel {

    private JButton botonRegistrarEquipo;
    private JButton botonRegistrarJugador;
    private JButton botonRegistrarEntrenador;
    private JButton botonRegistrarAdministrador;
    private JButton botonRegistrarEmparejamiento;
    private JButton botonRegistrarTorneo;
    private JButton botonVolver;

    private Dimension botonSize = new Dimension(180, 30);

    // Constructor del panel de registro, inicializa y posiciona los botones para las diferentes opciones de registro.
    public PanelRegistro() {
        setLayout(new GridBagLayout());

        botonRegistrarEquipo = new JButton("Registrar Equipo");
        botonRegistrarJugador = new JButton("Registrar Jugador");
        botonRegistrarEntrenador = new JButton("Registrar Entrenador");
        botonRegistrarAdministrador = new JButton("Registrar Administrador");
        botonRegistrarEmparejamiento = new JButton("Registrar Emparejamiento");
        botonRegistrarTorneo = new JButton("Registrar Torneo");
        botonVolver = new JButton("Volver al Menú Principal");

        botonRegistrarEquipo.setPreferredSize(botonSize);
        botonRegistrarJugador.setPreferredSize(botonSize);
        botonRegistrarEntrenador.setPreferredSize(botonSize);
        botonRegistrarAdministrador.setPreferredSize(botonSize);
        botonRegistrarEmparejamiento.setPreferredSize(botonSize);
        botonRegistrarTorneo.setPreferredSize(botonSize);
        botonVolver.setPreferredSize(botonSize);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weightx = 0.0;
        gbc.gridx = 0;
        gbc.weighty = 0.0;

        gbc.gridy = 0;
        add(botonRegistrarEquipo, gbc);

        gbc.gridy = 1;
        add(botonRegistrarJugador, gbc);

        gbc.gridy = 2;
        add(botonRegistrarEntrenador, gbc);

        gbc.gridy = 3;
        add(botonRegistrarAdministrador, gbc);

        gbc.gridy = 4;
        add(botonRegistrarEmparejamiento, gbc);

        gbc.gridy = 5;
        add(botonRegistrarTorneo, gbc);

        gbc.gridy = 6;
        add(botonVolver, gbc);
    }

    // Devuelve el botón para registrar un equipo.
    public JButton getBotonRegistrarEquipo() {
        return botonRegistrarEquipo;
    }

    // Devuelve el botón para registrar un jugador.
    public JButton getBotonRegistrarJugador() {
        return botonRegistrarJugador;
    }

    // Devuelve el botón para registrar un entrenador.
    public JButton getBotonRegistrarEntrenador() {
        return botonRegistrarEntrenador;
    }

    // Devuelve el botón para registrar un administrador.
    public JButton getBotonRegistrarAdministrador() {
        return botonRegistrarAdministrador;
    }

    // Devuelve el botón para registrar un emparejamiento.
    public JButton getBotonRegistrarEmparejamiento() {
        return botonRegistrarEmparejamiento;
    }

    // Devuelve el botón para registrar un torneo.
    public JButton getBotonRegistrarTorneo() {
        return botonRegistrarTorneo;
    }

    // Devuelve el botón para volver al menú principal.
    public JButton getBotonVolver() {
        return botonVolver;
    }
}