// En tu archivo PanelDisponibilidadEquipos.java
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

    // Constructor: Inicializa y configura los componentes del panel.
    public PanelDisponibilidadEquipos() {
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.decode("#F0F0F0"));

        etiquetaTitulo = new JLabel("Disponibilidad de Equipos");
        etiquetaTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        etiquetaTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        etiquetaTitulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(etiquetaTitulo, BorderLayout.NORTH);

        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("ID Equipo");
        modeloTabla.addColumn("Nombre Equipo");
        modeloTabla.addColumn("Estado");
        tablaEquipos = new JTable(modeloTabla);
        tablaEquipos.setFillsViewportHeight(true);
        tablaEquipos.setRowHeight(25);
        tablaEquipos.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(tablaEquipos);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        add(scrollPane, BorderLayout.CENTER);

        // Panel para la sección SUR, con GridBagLayout.
        JPanel panelSur = new JPanel(new GridBagLayout());
        panelSur.setOpaque(false);
        panelSur.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
        GridBagConstraints gbcSur = new GridBagConstraints();

        // Panel para el botón "Volver" con FlowLayout.
        JPanel panelBotonUnico = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 5));
        panelBotonUnico.setOpaque(false);
        
        botonVolver = new JButton("Volver");
        
        Dimension buttonSize = new Dimension(120, 30);
        Font buttonFont = new Font("Arial", Font.BOLD, 12);
        Color buttonColor = new Color(100, 100, 100);

        botonVolver.setPreferredSize(buttonSize);
        botonVolver.setFont(buttonFont);
        botonVolver.setBackground(buttonColor);
        botonVolver.setForeground(Color.WHITE);
        botonVolver.setFocusPainted(false);
        panelBotonUnico.add(botonVolver);
        
        // Configuración para el panel del botón dentro de panelSur.
        gbcSur.gridx = 0;
        gbcSur.gridy = 0;
        gbcSur.weightx = 1.0; // Ocupa el ancho.
        gbcSur.weighty = 0.3;  // El botón ocupa ~30-40% del espacio vertical de panelSur.
        gbcSur.fill = GridBagConstraints.HORIZONTAL;
        gbcSur.anchor = GridBagConstraints.NORTHEAST; // Ancla el panel del botón arriba y a la derecha.
        panelSur.add(panelBotonUnico, gbcSur);

        // Panel de relleno para ocupar el espacio restante.
        JPanel rellenoInferior = new JPanel();
        rellenoInferior.setOpaque(false);
        gbcSur.gridx = 0;
        gbcSur.gridy = 1;
        gbcSur.weighty = 0.7; // El relleno ocupa el ~60-70% restante.
        gbcSur.fill = GridBagConstraints.BOTH;
        panelSur.add(rellenoInferior, gbcSur);

        add(panelSur, BorderLayout.SOUTH);
    }

    // actualizarTabla: Actualiza la tabla con la lista de datos de equipos.
    public void actualizarTabla(List<Object[]> datosEquipos) {
        modeloTabla.setRowCount(0);
        if (datosEquipos != null) {
            for (Object[] equipo : datosEquipos) {
                modeloTabla.addRow(equipo);
            }
        }
    }

    // getBotonVolver: Devuelve el botón "Volver".
    public JButton getBotonVolver() {
        return botonVolver;
    }

    // getTablaEquipos: Devuelve la tabla de equipos.
    public JTable getTablaEquipos() {
        return tablaEquipos;
    }
}