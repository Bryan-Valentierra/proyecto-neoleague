// En tu archivo PanelTorneosActivos.java
package co.edu.unbosque.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class PanelTorneosActivos extends JPanel {

    private JTable tablaTorneos;
    private DefaultTableModel modeloTabla;
    private JButton botonVerDetalles;
    private JButton botonVolver;
    private JButton botonModificar;
    private JButton botonEliminar;

    // Constructor: Inicializa y configura los componentes del panel.
    public PanelTorneosActivos() {
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.decode("#F0F0F0"));

        JLabel labelTitulo = new JLabel("Torneos Registrados");
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        labelTitulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(labelTitulo, BorderLayout.NORTH);

        String[] columnas = {"Nombre", "ID"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaTorneos = new JTable(modeloTabla);
        tablaTorneos.setFillsViewportHeight(true);
        tablaTorneos.setRowHeight(25);
        tablaTorneos.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(tablaTorneos);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        add(scrollPane, BorderLayout.CENTER);

        // Panel para la sección SUR, ahora con GridBagLayout para control de weighty.
        JPanel panelSur = new JPanel(new GridBagLayout());
        panelSur.setOpaque(false);
        panelSur.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
        GridBagConstraints gbcSur = new GridBagConstraints();

        // Panel para los botones con FlowLayout.
        JPanel panelBotonesFlotante = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        panelBotonesFlotante.setOpaque(false);

        botonVerDetalles = new JButton("Ver Detalles");
        botonVolver = new JButton("Volver");
        botonModificar = new JButton("Modificar");
        botonEliminar = new JButton("Eliminar");

        Dimension buttonSize = new Dimension(120, 30);
        Font buttonFont = new Font("Arial", Font.BOLD, 12);
        Color buttonColor = new Color(70, 130, 180);

        for (JButton btn : Arrays.asList(botonVerDetalles, botonModificar, botonEliminar, botonVolver)) {
            btn.setPreferredSize(buttonSize);
            btn.setFont(buttonFont);
            btn.setBackground(buttonColor);
            btn.setForeground(Color.WHITE);
            btn.setFocusPainted(false);
            panelBotonesFlotante.add(btn);
        }

        // Configuración para el panel de botones dentro de panelSur.
        gbcSur.gridx = 0;
        gbcSur.gridy = 0;
        gbcSur.weightx = 1.0; // Ocupa el ancho.
        gbcSur.weighty = 0.3;  // Los botones ocupan ~30-40% del espacio vertical de panelSur.
        gbcSur.fill = GridBagConstraints.HORIZONTAL;
        gbcSur.anchor = GridBagConstraints.NORTH; // Ancla los botones arriba de su celda.
        panelSur.add(panelBotonesFlotante, gbcSur);

        // Panel de relleno para ocupar el espacio restante.
        JPanel rellenoInferior = new JPanel();
        rellenoInferior.setOpaque(false); // Lo hacemos transparente.
        gbcSur.gridx = 0;
        gbcSur.gridy = 1;
        gbcSur.weighty = 0.7; // El relleno ocupa el ~60-70% restante del espacio vertical.
        gbcSur.fill = GridBagConstraints.BOTH; // Se expande en ambas direcciones para llenar.
        panelSur.add(rellenoInferior, gbcSur);
        
        add(panelSur, BorderLayout.SOUTH);
    }

    // actualizarTabla: Actualiza la tabla con la lista de datos de torneos.
    public void actualizarTabla(List<Object[]> datos) {
        modeloTabla.setRowCount(0);
        if (datos != null) {
            for (Object[] fila : datos) {
                modeloTabla.addRow(fila);
            }
        }
    }

    // getTablaTorneos: Devuelve la tabla de torneos.
    public JTable getTablaTorneos() {
        return tablaTorneos;
    }

    // getModeloTabla: Devuelve el modelo de la tabla.
    public DefaultTableModel getModeloTabla() {
        return modeloTabla;
    }

    // getBotonVerDetalles: Devuelve el botón "Ver Detalles".
    public JButton getBotonVerDetalles() {
        return botonVerDetalles;
    }

    // getBotonVolver: Devuelve el botón "Volver".
    public JButton getBotonVolver() {
        return botonVolver;
    }

    // getBotonModificar: Devuelve el botón "Modificar".
    public JButton getBotonModificar() {
        return botonModificar;
    }

    // getBotonEliminar: Devuelve el botón "Eliminar".
    public JButton getBotonEliminar() {
        return botonEliminar;
    }
}