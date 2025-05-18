package co.edu.unbosque.controller;

import co.edu.unbosque.util.ExcelExporter;
import co.edu.unbosque.model.Equipo;
import co.edu.unbosque.view.PanelRegistroEquipo;
import co.edu.unbosque.util.ExcelExporter; // Asegúrate de tener esta importación
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class EquipoController {
    private List<Equipo> listaEquipos;
    private final String ARCHIVOS_PATH = ".\\src\\co\\edu\\unbosque\\archivos\\"; // Asegúrate de que esta ruta sea correcta
    private PanelRegistroEquipo panelRegistroEquipo;

    public EquipoController(PanelRegistroEquipo panelRegistroEquipo) {
        this.panelRegistroEquipo = panelRegistroEquipo;
        this.listaEquipos = new ArrayList<>();
        configurarListeners();
    }

    private void configurarListeners() {
        panelRegistroEquipo.getBotonRegistrarEquipo().addActionListener(e -> registrarEquipo());
        panelRegistroEquipo.getBotonVolver().addActionListener(e -> volver());
    }

    private void registrarEquipo() {
        String idEquipo = panelRegistroEquipo.getCampoIdEquipo().getText();
        String nombreEquipo = panelRegistroEquipo.getCampoNombreEquipo().getText();
        String fechaCreacionString = panelRegistroEquipo.getCampoFechaCreacion().getText();
        int numeroJugadores = (int) panelRegistroEquipo.getSpinnerNumeroJugadores().getValue();
        String juegoPrincipal = panelRegistroEquipo.getCampoJuegoPrincipal().getText();
        String sedeEntrenamiento = panelRegistroEquipo.getCampoSedeEntrenamiento().getText();
        String patrocinador = panelRegistroEquipo.getCampoPatrocinador().getText();

        if (idEquipo.isEmpty() || nombreEquipo.isEmpty() || fechaCreacionString.isEmpty() ||
            juegoPrincipal.isEmpty() || sedeEntrenamiento.isEmpty() || patrocinador.isEmpty()) {
            panelRegistroEquipo.mostrarMensaje("Todos los campos son obligatorios.");
            return;
        }

        Date fechaCreacion = parseFecha(fechaCreacionString);
        if (fechaCreacion == null) {
            panelRegistroEquipo.mostrarMensaje("Formato de fecha incorrecto (YYYY-MM-DD).");
            return;
        }

        if (buscarEquipo(idEquipo) != null) {
            panelRegistroEquipo.mostrarMensaje("Ya existe un equipo con el ID " + idEquipo);
            return;
        }

        Equipo nuevoEquipo = new Equipo(idEquipo, nombreEquipo, fechaCreacion, numeroJugadores,
                                      juegoPrincipal, sedeEntrenamiento, patrocinador);
        listaEquipos.add(nuevoEquipo);
        panelRegistroEquipo.mostrarMensaje("Equipo registrado con éxito.");
        panelRegistroEquipo.limpiarFormulario();

        // Llama al método para escribir en Excel desde el Controller
        escribirEquipoEnExcel(nuevoEquipo);

        // Opcional: Imprimir la lista de equipos para verificar
        System.out.println("Lista de Equipos: " + listaEquipos);
    }

    private Date parseFecha(String fechaString) {
        try {
            return java.sql.Date.valueOf(fechaString); // Convierte String a Date (YYYY-MM-DD)
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    private Equipo buscarEquipo(String idEquipo) {
        for (Equipo equipo : listaEquipos) {
            if (equipo.getIdEquipo().equals(idEquipo)) {
                return equipo;
            }
        }
        return null;
    }

    private void volver() {
        panelRegistroEquipo.mostrarMensaje("Volviendo...");
        panelRegistroEquipo.limpiarFormulario();
        //  Aquí iría la lógica para cambiar de panel o ventana
    }

private void escribirEquipoEnExcel(Equipo equipo) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Equipo");
        model.addColumn("Nombre Equipo");
        model.addColumn("Fecha Creacion");
        model.addColumn("Numero Jugadores");
        model.addColumn("Juego Principal");
        model.addColumn("Sede Entrenamiento");
        model.addColumn("Patrocinador");

        model.addRow(new Object[]{
                equipo.getIdEquipo(),
                equipo.getNombreEquipo(),
                equipo.getFechaCreacion(),
                equipo.getNumeroJugadores(),
                equipo.getJuegoPrincipal(),
                equipo.getSedeEntrenamiento(),
                equipo.getPatrocinador()
        });

        // Crear un JTable a partir del TableModel
        JTable table = new JTable(model);

        ExcelExporter.writeDataToExcel(ARCHIVOS_PATH + "equipos.xls", table);
        JOptionPane.showMessageDialog(panelRegistroEquipo, "Equipo guardado en equipos.xls", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }
}