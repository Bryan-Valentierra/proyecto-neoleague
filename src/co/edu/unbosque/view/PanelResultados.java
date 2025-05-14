package co.edu.unbosque.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PanelResultados extends JPanel {

    private JLabel labelTitulo;
    private JTable tablaResultados;
    private DefaultTableModel modeloTabla;
    private JScrollPane scrollTabla;
    private JButton botonVolver;
    private JPanel panelBotonVolver; // Panel para contener el botón Volver

    public PanelResultados() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 0.65; // La tabla ocupará el 65% del espacio vertical

        // Título del panel
        labelTitulo = new JLabel("Resultados de Torneos");
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(labelTitulo, gbc);

        // Tabla de resultados
        String[] columnas = {"Nombre del Torneo", "Equipo Ganador", "Segundo Lugar", "Fecha de Finalización"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaResultados = new JTable(modeloTabla);
        scrollTabla = new JScrollPane(tablaResultados);
        gbc.gridy = 1;
        add(scrollTabla, gbc);

        // Panel para el botón Volver
        panelBotonVolver = new JPanel(new FlowLayout(FlowLayout.CENTER));
        botonVolver = new JButton("Volver al Menú Principal");
        panelBotonVolver.add(botonVolver);

        gbc.gridy = 2;
        gbc.weighty = 0.35; // El panel del botón Volver ocupará el 35% del espacio vertical
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(panelBotonVolver, gbc);

        // Cargar datos de resultados (simulado por ahora)
        cargarResultadosSimulados();

        // Restablecer el peso vertical para futuros componentes si los hubiera
        gbc.weighty = 0.0;
    }

    private void cargarResultadosSimulados() {
        modeloTabla.addRow(new Object[]{"Torneo Primavera 2024", "Equipo A", "Equipo B", "2024-06-15"});
        modeloTabla.addRow(new Object[]{"Campeonato de Verano 2024", "Los Leones", "Las Águilas", "2024-07-30"});
        modeloTabla.addRow(new Object[]{"Copa Otoño 2024", "Team Alpha", "Team Beta", "2024-10-05"});
    }

    // Getter para el botón Volver
    public JButton getBotonVolver() {
        return botonVolver;
    }

    // Getter para la tabla de resultados
    public JTable getTablaResultados() {
        return tablaResultados;
    }
}