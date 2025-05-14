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
    private JButton botonVolver; // Botón "Volver"

    private Dimension botonSize = new Dimension(180, 30); // Define el tamaño preferido de los botones

    public PanelRegistro() {
        setLayout(new GridBagLayout());

        // Inicializar botones
        botonRegistrarEquipo = new JButton("Registrar Equipo");
        botonRegistrarJugador = new JButton("Registrar Jugador");
        botonRegistrarEntrenador = new JButton("Registrar Entrenador");
        botonRegistrarAdministrador = new JButton("Registrar Administrador");
        botonRegistrarEmparejamiento = new JButton("Registrar Emparejamiento");
        botonRegistrarTorneo = new JButton("Registrar Torneo");
        botonVolver = new JButton("Volver al Menú Principal"); // Inicializar botón "Volver"

        // Establecer el tamaño preferido para cada botón
        botonRegistrarEquipo.setPreferredSize(botonSize);
        botonRegistrarJugador.setPreferredSize(botonSize);
        botonRegistrarEntrenador.setPreferredSize(botonSize);
        botonRegistrarAdministrador.setPreferredSize(botonSize);
        botonRegistrarEmparejamiento.setPreferredSize(botonSize);
        botonRegistrarTorneo.setPreferredSize(botonSize);
        botonVolver.setPreferredSize(botonSize);

        System.out.println("PanelRegistro: Instancia del panel creada: " + this);
        System.out.println("PanelRegistro: Botón Registrar Jugador creado: " + botonRegistrarJugador);
        System.out.println("PanelRegistro: Botón Registrar Entrenador creado: " + botonRegistrarEntrenador);
        System.out.println("PanelRegistro: Botón Volver creado: " + botonVolver);

        // Configurar GridBagConstraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.NONE; // No redimensionar los componentes
        gbc.anchor = GridBagConstraints.CENTER; // Centrar los componentes en su celda
        gbc.weightx = 0.0;
        gbc.gridx = 0;
        gbc.weighty = 0.0; // Asegurar que las filas no se estiren demasiado verticalmente

        // Añadir botones al panel
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
        add(botonVolver, gbc); // Añadir botón "Volver"
    }

    // Getters para los botones
    public JButton getBotonRegistrarEquipo() {
        return botonRegistrarEquipo;
    }

    public JButton getBotonRegistrarJugador() {
        return botonRegistrarJugador;
    }

    public JButton getBotonRegistrarEntrenador() {
        return botonRegistrarEntrenador;
    }

    public JButton getBotonRegistrarAdministrador() {
        return botonRegistrarAdministrador;
    }

    public JButton getBotonRegistrarEmparejamiento() {
        return botonRegistrarEmparejamiento;
    }

    public JButton getBotonRegistrarTorneo() {
        return botonRegistrarTorneo;
    }

    public JButton getBotonVolver() {
        return botonVolver;
    }
}