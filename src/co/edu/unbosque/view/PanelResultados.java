// En tu archivo PanelResultados.java
package co.edu.unbosque.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Arrays; // Para la lista de botones al aplicar estilo
import java.util.List;

public class PanelResultados extends JPanel {

    private JLabel labelTitulo;
    private JTable tablaResultados;
    private DefaultTableModel modeloTabla;
    // private JScrollPane scrollTabla; // Ya está implícito al añadir tabla a JScrollPane
    private JButton botonVolver;
    // private JPanel panelBotonVolver; // No es necesario si usamos panelSur con BoxLayout/GridBagLayout
    private JButton botonExportarResultados; // Mantendremos este botón por si lo implementas después

    // Constructor: Inicializa y configura los componentes del panel.
    public PanelResultados() {
        setLayout(new BorderLayout(10, 10)); // Usar BorderLayout para estructura general.
        setBackground(Color.decode("#F0F0F0"));

        labelTitulo = new JLabel("Resultados de Emparejamientos"); // Título ajustado.
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        labelTitulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(labelTitulo, BorderLayout.NORTH);

        // Define las columnas según emparejamientos.xls.
        String[] columnas = {
            "ID Fila", "ID Emparejamiento", "Equipo 1", "Equipo 2", "ID Torneo", 
            "Fecha", "Hora", "Ronda", "Estado", "Ganador", "Perdedor"
        };
        modeloTabla = new DefaultTableModel(columnas, 0); // Crea el modelo sin filas iniciales.
        tablaResultados = new JTable(modeloTabla);
        tablaResultados.setFillsViewportHeight(true);
        tablaResultados.setRowHeight(25);
        tablaResultados.setFont(new Font("Arial", Font.PLAIN, 12)); // Fuente más pequeña si hay muchas columnas.
        tablaResultados.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // Para permitir scroll horizontal si hay muchas columnas.
        
        JScrollPane scrollPane = new JScrollPane(tablaResultados);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        add(scrollPane, BorderLayout.CENTER);

        // Panel para la sección SUR, con GridBagLayout para control de weighty.
        JPanel panelSur = new JPanel(new GridBagLayout());
        panelSur.setOpaque(false);
        panelSur.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
        GridBagConstraints gbcSur = new GridBagConstraints();

        // Panel para los botones con FlowLayout.
        JPanel panelBotonesFlotante = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        panelBotonesFlotante.setOpaque(false);

        botonVolver = new JButton("Volver");
        botonExportarResultados = new JButton("Exportar"); // Texto más corto.

        // Estilo para los botones.
        Dimension buttonSize = new Dimension(120, 30);
        Font buttonFont = new Font("Arial", Font.BOLD, 12);
        Color buttonColor = new Color(70, 130, 180); // SteelBlue
        Color exportButtonColor = new Color(34, 139, 34); // ForestGreen

        // Aplica estilo y añade botones.
        List<JButton> botones = Arrays.asList(botonExportarResultados, botonVolver);
        for (JButton btn : botones) {
            btn.setPreferredSize(buttonSize);
            btn.setFont(buttonFont);
            btn.setForeground(Color.WHITE);
            btn.setFocusPainted(false);
            if (btn == botonExportarResultados) {
                btn.setBackground(exportButtonColor);
            } else {
                btn.setBackground(buttonColor);
            }
            panelBotonesFlotante.add(btn);
        }
        
        // Configuración para el panel de botones dentro de panelSur.
        gbcSur.gridx = 0;
        gbcSur.gridy = 0;
        gbcSur.weightx = 1.0; 
        gbcSur.weighty = 0.3;  // Botones ocupan ~30% del espacio vertical de panelSur.
        gbcSur.fill = GridBagConstraints.HORIZONTAL;
        gbcSur.anchor = GridBagConstraints.NORTH; 
        panelSur.add(panelBotonesFlotante, gbcSur);

        // Panel de relleno para ocupar el espacio restante.
        JPanel rellenoInferior = new JPanel();
        rellenoInferior.setOpaque(false); 
        gbcSur.gridx = 0;
        gbcSur.gridy = 1;
        gbcSur.weighty = 0.7; // Relleno ocupa ~70% restante.
        gbcSur.fill = GridBagConstraints.BOTH; 
        panelSur.add(rellenoInferior, gbcSur);
        
        add(panelSur, BorderLayout.SOUTH);

        // Ya no cargamos resultados simulados aquí, se hará desde el Controller.
        // cargarResultadosSimulados(); 
    }

    // actualizarTablaResultados: Actualiza la tabla con los datos de emparejamientos.
    public void actualizarTablaResultados(List<Object[]> datosEmparejamientos) {
        modeloTabla.setRowCount(0); // Limpia filas existentes.
        if (datosEmparejamientos != null) { // Verifica que los datos no sean nulos.
            for (Object[] fila : datosEmparejamientos) { // Itera y añade cada fila.
                modeloTabla.addRow(fila);
            }
        }
    }

    // getBotonVolver: Devuelve el botón "Volver".
    public JButton getBotonVolver() {
        return botonVolver;
    }

    // getTablaResultados: Devuelve la tabla de resultados.
    public JTable getTablaResultados() {
        return tablaResultados;
    }

    // getBotonExportarResultados: Devuelve el botón "Exportar Resultados".
    public JButton getBotonExportarResultados() {
        return botonExportarResultados;
    }
}