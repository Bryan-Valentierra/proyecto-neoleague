package co.edu.unbosque.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PanelPrincipal extends JPanel {

    private JButton botonRegistrar;
    private JButton botonAnalisis;
    private JButton botonTorneosActivos;
    private JButton botonResultados;
    private JLabel etiquetaBienvenida;

    private Dimension botonSize = new Dimension(180, 30); // Define el tamaño preferido de los botones

    public PanelPrincipal() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.NONE; // No redimensionar los botones
        gbc.anchor = GridBagConstraints.CENTER; // Centrar los botones
        gbc.weightx = 0.0; // No dar peso extra horizontal
        gbc.gridx = 0;
        gbc.weighty = 0.0; // Evitar estiramiento vertical

        // Etiqueta de bienvenida
        etiquetaBienvenida = new JLabel("Bienvenido al Menú Principal");
        etiquetaBienvenida.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridy = 0;
        gbc.gridwidth = 1; // Para centrar la etiqueta en una sola columna
        add(etiquetaBienvenida, gbc);

        // Botón Registrar Persona/Equipo
        botonRegistrar = new JButton("Registrar Persona/Equipo");
        botonRegistrar.setPreferredSize(botonSize);
        gbc.gridy = 1;
        add(botonRegistrar, gbc);

        // Botón Análisis
        botonAnalisis = new JButton("Análisis");
        botonAnalisis.setPreferredSize(botonSize);
        gbc.gridy = 2;
        add(botonAnalisis, gbc);

        // Botón Torneos Activos
        botonTorneosActivos = new JButton("Torneos Activos");
        botonTorneosActivos.setPreferredSize(botonSize);
        gbc.gridy = 3;
        add(botonTorneosActivos, gbc);

        // Botón Resultados
        botonResultados = new JButton("Resultados");
        botonResultados.setPreferredSize(botonSize);
        gbc.gridy = 4;
        add(botonResultados, gbc);
    }

   
    public JButton getBotonRegistrar() {
        return botonRegistrar;
    }

    public JButton getBotonAnalisis() {
        return botonAnalisis;
    }

    public JButton getBotonTorneosActivos() {
        return botonTorneosActivos;
    }

    public JButton getBotonResultados() {
        return botonResultados;
    }

    // Método para añadir ActionListener a los botones (puede ser llamado desde el Controller)
    public void agregarActionListener(ActionListener listener) {
        botonRegistrar.addActionListener(listener);
        botonAnalisis.addActionListener(listener);
        botonTorneosActivos.addActionListener(listener);
        botonResultados.addActionListener(listener);
    }
}
