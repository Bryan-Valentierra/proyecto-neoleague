package co.edu.unbosque.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PanelAnalisis extends JPanel {

    private JLabel labelTitulo;
    private JTable tablaAnalisis;
    private JScrollPane scrollTablaAnalisis;
    private JButton botonVolver;
    private JButton botonMostrarRanking;

    // Constructor: Inicializa y organiza los componentes visuales del panel.
    public PanelAnalisis() {
        setLayout(new GridBagLayout()); // Establece el layout manager.
        GridBagConstraints gbc = new GridBagConstraints(); // Define restricciones para componentes.
        gbc.insets = new Insets(5, 5, 5, 5); // Define márgenes externos.
        gbc.fill = GridBagConstraints.HORIZONTAL; // Componentes se expanden horizontalmente.

        // Título del panel.
        labelTitulo = new JLabel("Análisis Estadístico y Ranking");
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 20)); // Estilo de fuente.
        labelTitulo.setHorizontalAlignment(SwingConstants.CENTER); // Alineación del texto.
        gbc.gridx = 0; // Posición en columna.
        gbc.gridy = 0; // Posición en fila.
        gbc.weightx = 1.0; // Distribución de espacio horizontal.
        gbc.weighty = 0.05; // Distribución de espacio vertical.
        add(labelTitulo, gbc); // Añade el componente al panel.

        // Tabla para mostrar datos de análisis.
        tablaAnalisis = new JTable();
        tablaAnalisis.setModel(new DefaultTableModel( // Modelo de datos inicial.
            new Object [][] {}, // Sin filas.
            new String [] {"ID Equipo", "Victorias"} // Nombres de columnas.
        ));
        scrollTablaAnalisis = new JScrollPane(tablaAnalisis); // Agrega scroll a la tabla.
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weighty = 0.4;
        gbc.fill = GridBagConstraints.BOTH; // Componente se expande en ambas direcciones.
        add(scrollTablaAnalisis, gbc);

        // Panel para agrupar botones.
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(2, 1, 0, 5)); // Layout para botones (2 filas, 1 columna).

        botonMostrarRanking = new JButton("Refrescar Ranking de Equipos");
        panelBotones.add(botonMostrarRanking); // Añade botón al panel de botones.

        botonVolver = new JButton("Volver al Menú Principal");
        panelBotones.add(botonVolver); // Añade botón al panel de botones.

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weighty = 0.1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH; // Ancla el componente al norte (arriba).
        add(panelBotones, gbc);

        // Componente invisible para rellenar espacio y empujar otros componentes.
        JPanel filler = new JPanel();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weighty = 0.45;
        gbc.fill = GridBagConstraints.BOTH;
        add(filler, gbc);
    }

    // Devuelve el botón 'Volver'.
    public JButton getBotonVolver() {
        return botonVolver;
    }

    // Devuelve el botón 'Mostrar Ranking'.
    public JButton getBotonMostrarRanking() {
        return botonMostrarRanking;
    }

    // Devuelve la tabla 'tablaAnalisis'.
    public JTable getTablaAnalisis() {
        return tablaAnalisis;
    }
}