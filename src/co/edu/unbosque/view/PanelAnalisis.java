// PanelAnalisis.java
package co.edu.unbosque.view;

import javax.swing.*;
import java.awt.*;

public class PanelAnalisis extends JPanel {

    private JLabel labelTitulo;
    private JTextArea areaAnalisis;
    private JScrollPane scrollAnalisis;
    private JButton botonVolver; // Botón "Volver"

    public PanelAnalisis() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.BOTH; // Llenar ambas direcciones
        gbc.weightx = 1.0;
        gbc.weighty = 0.7; // El área de análisis ocupará el 70% del espacio vertical

        // Título
        labelTitulo = new JLabel("Análisis Estadístico");
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(labelTitulo, gbc);

        // Área de análisis con scroll
        areaAnalisis = new JTextArea("Aquí se mostrarán los análisis estadísticos.");
        areaAnalisis.setEditable(false);
        scrollAnalisis = new JScrollPane(areaAnalisis);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(scrollAnalisis, gbc);

        // Botón Volver
        botonVolver = new JButton("Volver al Menú Principal");
        gbc.gridy = 2;
        gbc.weighty = 0.3; // El botón volver ocupará el 30% restante (aproximadamente)
        gbc.fill = GridBagConstraints.HORIZONTAL; // Llenar horizontalmente
        add(botonVolver, gbc);

        // Restablecer el peso vertical para futuros componentes si los hubiera
        gbc.weighty = 0.0;
    }

    // Getters
    public JButton getBotonVolver() {
        return botonVolver;
    }
}