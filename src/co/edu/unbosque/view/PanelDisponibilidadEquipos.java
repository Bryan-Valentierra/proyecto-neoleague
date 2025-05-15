// PanelDisponibilidadEquipos.java
package co.edu.unbosque.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PanelDisponibilidadEquipos extends JPanel {

    private JLabel etiquetaTitulo;
    private JTable tablaEquipos;
    private DefaultTableModel modeloTabla;
    private JButton botonVolver;

    public PanelDisponibilidadEquipos() {
        setLayout(new BorderLayout());

        etiquetaTitulo = new JLabel("Disponibilidad de Equipos");
        etiquetaTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        add(etiquetaTitulo, BorderLayout.NORTH);

        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("ID Equipo");
        modeloTabla.addColumn("Nombre Equipo");
        modeloTabla.addColumn("Estado"); // Activo/Inactivo
        tablaEquipos = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaEquipos);
        add(scrollPane, BorderLayout.CENTER);

        botonVolver = new JButton("Volver");
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelInferior.add(botonVolver);
        add(panelInferior, BorderLayout.SOUTH);
    }

    public void actualizarTabla(List<Object[]> datosEquipos) {
        modeloTabla.setRowCount(0); // Limpiar la tabla
        for (Object[] equipo : datosEquipos) {
            modeloTabla.addRow(equipo);
        }
        revalidate();
        repaint();
    }

    public JButton getBotonVolver() {
        return botonVolver;
    }
}