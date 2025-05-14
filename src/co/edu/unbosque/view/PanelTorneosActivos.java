package co.edu.unbosque.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PanelTorneosActivos extends JPanel {

    private JLabel labelTitulo;
    private JTable tablaTorneos;
    private DefaultTableModel modeloTabla;
    private JScrollPane scrollTabla;
    private JButton botonVolver;
    private JButton botonVerDetalles;
    private JPanel panelBotones; // Panel para contener los botones

    public PanelTorneosActivos() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 0.70; // La tabla ocupará el 70% del espacio vertical

        // Título del panel
        labelTitulo = new JLabel("Torneos Activos");
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(labelTitulo, gbc);

        // Tabla de torneos
        String[] columnas = {"Nombre del Torneo", "Fecha de Inicio", "Fecha de Fin", "Estado"};
        modeloTabla = new DefaultTableModel(columnas, 0); // 0 filas iniciales
        tablaTorneos = new JTable(modeloTabla);
        scrollTabla = new JScrollPane(tablaTorneos);
        gbc.gridy = 1;
        add(scrollTabla, gbc);

        // Panel para los botones
        panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        botonVolver = new JButton("Volver al Menú Principal");
        botonVerDetalles = new JButton("Ver Detalles");
        panelBotones.add(botonVolver);
        panelBotones.add(botonVerDetalles);

        gbc.gridy = 2;
        gbc.weighty = 0.30; // El panel de botones ocupará el 30% del espacio vertical
        gbc.fill = GridBagConstraints.HORIZONTAL; // Llenar horizontalmente
        add(panelBotones, gbc);

        // Cargar datos de torneos activos (simulado por ahora)
        cargarTorneosActivosSimulado();

        // Restablecer el peso vertical para futuros componentes si los hubiera
        gbc.weighty = 0.0;
    }

    // Método simulado para cargar torneos activos
    private void cargarTorneosActivosSimulado() {
        modeloTabla.addRow(new Object[]{"Torneo Primavera 2025", "2025-06-01", "2025-06-15", "Inscripciones Abiertas"});
        modeloTabla.addRow(new Object[]{"Campeonato de Verano", "2025-07-01", "2025-07-30", "En Curso"});
        modeloTabla.addRow(new Object[]{"Copa Otoño", "2025-09-10", "2025-10-05", "Pendiente"});
        modeloTabla.addRow(new Object[]{"Final de Año", "2025-11-15", "2025-12-20", "Inscripciones Cerradas"});
    }

    // Métodos getter para los botones (para el Controller)
    public JButton getBotonVolver() {
        return botonVolver;
    }

    public JButton getBotonVerDetalles() {
        return botonVerDetalles;
    }

    // Getter para la tabla
    public JTable getTablaTorneos() {
        return tablaTorneos;
    }
}