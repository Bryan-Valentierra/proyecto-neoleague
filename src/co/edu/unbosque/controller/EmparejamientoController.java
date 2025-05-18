// EmparejamientoController.java
package co.edu.unbosque.controller;

import co.edu.unbosque.model.Emparejamiento;
import co.edu.unbosque.view.PanelRegistrarEmparejamiento;
import co.edu.unbosque.view.VentanaPrincipal;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import jxl.Cell;
import jxl.WorkbookSettings;

public class EmparejamientoController {

    private PanelRegistrarEmparejamiento panelRegistrarEmparejamiento;
    private List<Emparejamiento> listaEmparejamientos;
    private final String ARCHIVOS_PATH = ".\\src\\co\\edu\\unbosque\\archivos\\";
    private VentanaPrincipal ventanaPrincipal;

    public EmparejamientoController(PanelRegistrarEmparejamiento panelRegistrarEmparejamiento, VentanaPrincipal ventanaPrincipal) {
        this.panelRegistrarEmparejamiento = panelRegistrarEmparejamiento;
        this.listaEmparejamientos = new ArrayList<>();
        this.ventanaPrincipal = ventanaPrincipal;
        configurarListeners();
    }

    private void configurarListeners() {
        panelRegistrarEmparejamiento.getBotonRegistrar().addActionListener(e -> registrarEmparejamiento());
        panelRegistrarEmparejamiento.getBotonVolver().addActionListener(e -> volver());
        panelRegistrarEmparejamiento.getBotonEliminar().addActionListener(e -> eliminarEmparejamiento());
        panelRegistrarEmparejamiento.getBotonModificar().addActionListener(e -> modificarEmparejamiento());
        panelRegistrarEmparejamiento.getBotonEmparejamientoRandom().addActionListener(e -> generarEmparejamientoRandom());
    }

    private void registrarEmparejamiento() {
        String idEmparejamiento = panelRegistrarEmparejamiento.getCampoIdEmparejamiento().getText();
        String idTorneo = panelRegistrarEmparejamiento.getCampoIdTorneo().getText();
        String equipo1 = panelRegistrarEmparejamiento.getCampoIdEquipo1().getText();
        String equipo2 = panelRegistrarEmparejamiento.getCampoIdEquipo2().getText();
        String fecha = panelRegistrarEmparejamiento.getCampoFechaTexto();
        String hora = panelRegistrarEmparejamiento.getCampoHoraTexto();
        String ronda = panelRegistrarEmparejamiento.getCampoRondaTexto();
        String estado = (String) panelRegistrarEmparejamiento.getComboEstado().getSelectedItem();
        String ganador = panelRegistrarEmparejamiento.getCampoGanador().getText();
        String perdedor = panelRegistrarEmparejamiento.getCampoPerdedor().getText();

        if (idEmparejamiento.isEmpty() || idTorneo.isEmpty() || equipo1.isEmpty() || equipo2.isEmpty()
                || fecha.isEmpty() || hora.isEmpty() || ronda.isEmpty() || estado == null
                || ganador.isEmpty() || perdedor.isEmpty()) {
            panelRegistrarEmparejamiento.mostrarMensaje("Todos los campos son obligatorios.");
            return;
        }

        if (buscarEmparejamiento(idEmparejamiento) != null) {
            panelRegistrarEmparejamiento.mostrarMensaje("Ya existe un emparejamiento con el ID " + idEmparejamiento);
            return;
        }

        Emparejamiento nuevoEmparejamiento = new Emparejamiento(idEmparejamiento, idTorneo, equipo1, equipo2, fecha, hora, ronda, estado, ganador, perdedor);
        listaEmparejamientos.add(nuevoEmparejamiento);
        guardarEmparejamientoEnExcel(nuevoEmparejamiento);
        panelRegistrarEmparejamiento.mostrarMensaje("Emparejamiento registrado con éxito.");
        panelRegistrarEmparejamiento.limpiarFormulario();
    }

    private void guardarEmparejamientoEnExcel(Emparejamiento emparejamiento) {
        File file = new File(ARCHIVOS_PATH + "emparejamientos.xls");
        WritableWorkbook workbook = null;
        WritableSheet sheet = null;
        try {
            WorkbookSettings settings = new WorkbookSettings();
            settings.setEncoding("UTF-8");

            if (file.exists() && file.length() > 0) {
                Workbook existingWorkbook = Workbook.getWorkbook(file);
                workbook = Workbook.createWorkbook(file, existingWorkbook, settings);
                existingWorkbook.close();
                sheet = workbook.getSheet("Emparejamientos");
                if (sheet == null) {
                    sheet = workbook.createSheet("Emparejamientos", 0);
                    crearCabecera(sheet);
                }
            } else {
                workbook = Workbook.createWorkbook(file, settings);
                sheet = workbook.createSheet("Emparejamientos", 0);
                crearCabecera(sheet);
            }

            int fila = sheet.getRows();

            sheet.addCell(new Label(0, fila, ""));
            sheet.addCell(new Label(1, fila, emparejamiento.getIdEmparejamiento()));
            sheet.addCell(new Label(2, fila, emparejamiento.getEquipo1()));
            sheet.addCell(new Label(3, fila, emparejamiento.getEquipo2()));
            sheet.addCell(new Label(4, fila, emparejamiento.getIdTorneo()));
            sheet.addCell(new Label(5, fila, emparejamiento.getFecha()));
            sheet.addCell(new Label(6, fila, emparejamiento.getHora()));
            sheet.addCell(new Label(7, fila, emparejamiento.getRonda()));
            sheet.addCell(new Label(8, fila, emparejamiento.getEstado()));
            sheet.addCell(new Label(9, fila, emparejamiento.getGanador()));
            sheet.addCell(new Label(10, fila, emparejamiento.getPerdedor()));

            workbook.write();
            workbook.close();
        } catch (IOException | WriteException | BiffException e) {
            System.err.println("Error al escribir en el archivo de emparejamientos: " + e.getMessage());
            e.printStackTrace();
            panelRegistrarEmparejamiento.mostrarMensaje("Error al guardar el emparejamiento en el archivo.");
        } finally {
            if (workbook != null) {
                try {
                    workbook.close();
                } catch (IOException | WriteException e) {
                    System.err.println("Error al cerrar el archivo Excel: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
    }

    private void crearCabecera(WritableSheet sheet) throws WriteException {
        sheet.addCell(new Label(0, 0, "ID"));
        sheet.addCell(new Label(1, 0, "ID Empare"));
        sheet.addCell(new Label(2, 0, "ID Equipo 1"));
        sheet.addCell(new Label(3, 0, "ID Equipo 2"));
        sheet.addCell(new Label(4, 0, "ID Torneo"));
        sheet.addCell(new Label(5, 0, "Fecha"));
        sheet.addCell(new Label(6, 0, "Hora"));
        sheet.addCell(new Label(7, 0, "Ronda"));
        sheet.addCell(new Label(8, 0, "Estado"));
        sheet.addCell(new Label(9, 0, "Ganador"));
        sheet.addCell(new Label(10, 0, "Perdedor"));
    }

    private Emparejamiento buscarEmparejamiento(String idEmparejamiento) {
        for (Emparejamiento emparejamiento : listaEmparejamientos) {
            if (emparejamiento.getIdEmparejamiento().equals(idEmparejamiento)) {
                return emparejamiento;
            }
        }
        return null;
    }

    private void volver() {
        panelRegistrarEmparejamiento.mostrarMensaje("Volviendo...");
        panelRegistrarEmparejamiento.limpiarFormulario();
        ventanaPrincipal.mostrarPanelPrincipal();
    }

    private void eliminarEmparejamiento() {
        String idEmparejamiento = panelRegistrarEmparejamiento.getCampoIdEmparejamiento().getText();
        if (idEmparejamiento.isEmpty()) {
            panelRegistrarEmparejamiento.mostrarMensaje("Por favor, ingrese el ID del emparejamiento a eliminar.");
            return;
        }

        Emparejamiento emparejamientoAEliminar = buscarEmparejamiento(idEmparejamiento);
        if (emparejamientoAEliminar == null) {
            panelRegistrarEmparejamiento.mostrarMensaje("No se encontró ningún emparejamiento con el ID " + idEmparejamiento);
            return;
        }

        listaEmparejamientos.remove(emparejamientoAEliminar);
        eliminarEmparejamientoDeExcel(idEmparejamiento);
        panelRegistrarEmparejamiento.mostrarMensaje("Emparejamiento eliminado con éxito.");
        panelRegistrarEmparejamiento.limpiarFormulario();
    }

    private void eliminarEmparejamientoDeExcel(String idEmparejamiento) {
        File file = new File(ARCHIVOS_PATH + "emparejamientos.xls");
        WritableWorkbook workbook;
        try {
            Workbook existingWorkbook = Workbook.getWorkbook(file);
            workbook = Workbook.createWorkbook(file, existingWorkbook);
            WritableSheet sheet = workbook.getSheet("Emparejamientos");
            if (sheet != null) {
                for (int i = 1; i < sheet.getRows(); i++) {
                    Cell idCell = sheet.getCell(1, i);
                    if (idCell.getContents().equals(idEmparejamiento)) {
                        sheet.removeRow(i);
                        break;
                    }
                }
                workbook.write();
                workbook.close();
            }
        } catch (IOException | WriteException | BiffException e) {
            System.err.println("Error al eliminar el emparejamiento del archivo Excel: " + e.getMessage());
            e.printStackTrace();
            panelRegistrarEmparejamiento.mostrarMensaje("Error al eliminar el emparejamiento del archivo.");
        }
    }

    private void modificarEmparejamiento() {
        String idEmparejamiento = panelRegistrarEmparejamiento.getCampoIdEmparejamiento().getText();
        if (idEmparejamiento.isEmpty()) {
            panelRegistrarEmparejamiento.mostrarMensaje("Por favor, ingrese el ID del emparejamiento a modificar.");
            return;
        }

        Emparejamiento emparejamientoAModificar = buscarEmparejamiento(idEmparejamiento);
        if (emparejamientoAModificar == null) {
            panelRegistrarEmparejamiento.mostrarMensaje("No se encontró ningún emparejamiento con el ID " + idEmparejamiento);
            return;
        }

        String nuevoIdTorneo = panelRegistrarEmparejamiento.getCampoIdTorneo().getText();
        String nuevoEquipo1 = panelRegistrarEmparejamiento.getCampoIdEquipo1().getText();
        String nuevoEquipo2 = panelRegistrarEmparejamiento.getCampoIdEquipo2().getText();
        String nuevaFecha = panelRegistrarEmparejamiento.getCampoFechaTexto();
        String nuevaHora = panelRegistrarEmparejamiento.getCampoHoraTexto();
        String nuevaRonda = panelRegistrarEmparejamiento.getCampoRondaTexto();
        String nuevoEstado = (String) panelRegistrarEmparejamiento.getComboEstado().getSelectedItem();
        String nuevoGanador = panelRegistrarEmparejamiento.getCampoGanador().getText();
        String nuevoPerdedor = panelRegistrarEmparejamiento.getCampoPerdedor().getText();

        emparejamientoAModificar.setIdTorneo(nuevoIdTorneo);
        emparejamientoAModificar.setEquipo1(nuevoEquipo1);
        emparejamientoAModificar.setEquipo2(nuevoEquipo2);
        emparejamientoAModificar.setFecha(nuevaFecha);
        emparejamientoAModificar.setHora(nuevaHora);
        emparejamientoAModificar.setRonda(nuevaRonda);
        emparejamientoAModificar.setEstado(nuevoEstado);
        emparejamientoAModificar.setGanador(nuevoGanador);
        emparejamientoAModificar.setPerdedor(nuevoPerdedor);

        actualizarEmparejamientoEnExcel(emparejamientoAModificar);
        panelRegistrarEmparejamiento.mostrarMensaje("Emparejamiento modificado con éxito.");
        panelRegistrarEmparejamiento.limpiarFormulario();
    }

    private void actualizarEmparejamientoEnExcel(Emparejamiento emparejamiento) {
        File file = new File(ARCHIVOS_PATH + "emparejamientos.xls");
        WritableWorkbook workbook;
        try {
            Workbook existingWorkbook = Workbook.getWorkbook(file);
            workbook = Workbook.createWorkbook(file, existingWorkbook);
            WritableSheet sheet = workbook.getSheet("Emparejamientos");
            if (sheet != null) {
                for (int i = 1; i < sheet.getRows(); i++) {
                    Cell idCell = sheet.getCell(1, i);
                    if (idCell.getContents().equals(emparejamiento.getIdEmparejamiento())) {
                        sheet.addCell(new Label(2, i, emparejamiento.getEquipo1()));
                        sheet.addCell(new Label(3, i, emparejamiento.getEquipo2()));
                        sheet.addCell(new Label(4, i, emparejamiento.getIdTorneo()));
                        sheet.addCell(new Label(5, i, emparejamiento.getFecha()));
                        sheet.addCell(new Label(6, i, emparejamiento.getHora()));
                        sheet.addCell(new Label(7, i, emparejamiento.getRonda()));
                        sheet.addCell(new Label(8, i, emparejamiento.getEstado()));
                        sheet.addCell(new Label(9, i, emparejamiento.getGanador()));
                        sheet.addCell(new Label(10, i, emparejamiento.getPerdedor()));
                        break;
                    }
                }
            }
            workbook.write();
            workbook.close();
        } catch (IOException | WriteException | BiffException e) {
            System.err.println("Error al actualizar el emparejamiento en el archivo Excel: " + e.getMessage());
            e.printStackTrace();
            panelRegistrarEmparejamiento.mostrarMensaje("Error al actualizar el emparejamiento en el archivo.");
        }
    }

    private String generarIdAleatorio() {
        return String.valueOf(new Random().nextInt(1000000));
    }

    private String generarIdAleatorioNumerico() {
        return String.valueOf(new Random().nextInt(900000) + 100000);
    }

    private String generarHoraAleatoria() {
        Random random = new Random();
        int hora = random.nextInt(24);
        int minutos = random.nextInt(60);
        return String.format("%02d:%02d", hora, minutos);
    }

    private List<String[]> leerEquiposDesdeExcel() {
        List<String[]> equipos = new ArrayList<>();
        File file = new File(ARCHIVOS_PATH + "equipos.xls");
        try {
            Workbook workbook = Workbook.getWorkbook(file);
            Sheet sheet = workbook.getSheet("Equipos");
            if (sheet != null && sheet.getRows() > 1) {
                for (int i = 1; i < sheet.getRows(); i++) {
                    String[] equipo = new String[3];
                    equipo[0] = sheet.getCell(0, i).getContents(); // ID Equipo
                    equipo[1] = sheet.getCell(1, i).getContents(); // Nombre Equipo
                    equipo[2] = sheet.getCell(5, i).getContents(); // Juego Principal
                    equipos.add(equipo);
                }
            }
            workbook.close();
        } catch (IOException | BiffException e) {
            System.err.println("Error al leer el archivo de equipos: " + e.getMessage());
            e.printStackTrace();
        }
        return equipos;
    }

    private List<String[]> filtrarEquiposPorJuego(List<String[]> equipos) {
        List<String[]> equiposMismoJuego = new ArrayList<>();
        if (equipos != null && !equipos.isEmpty()) {
            String juegoABuscar = equipos.get(0)[2];
            for (String[] equipo : equipos) {
                if (equipo[2].equalsIgnoreCase(juegoABuscar)) {
                    equiposMismoJuego.add(equipo);
                }
            }
        }
        return equiposMismoJuego;
    }

    private String[] obtenerEquipoAleatorio(List<String[]> equipos) {
        if (equipos == null || equipos.isEmpty()) {
            return null;
        }
        Random random = new Random();
        int index = random.nextInt(equipos.size());
        return equipos.get(index);
    }

    public void generarEmparejamientoRandom() {
        List<String[]> equipos = leerEquiposDesdeExcel();
        if (equipos.isEmpty()) {
            JOptionPane.showMessageDialog(ventanaPrincipal, "No hay equipos registrados.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        List<String[]> equiposMismoJuego = filtrarEquiposPorJuego(equipos);
        if (equiposMismoJuego.size() < 2) {
            JOptionPane.showMessageDialog(ventanaPrincipal, "No hay suficientes equipos del mismo juego para emparejar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String[] equipo1Data = obtenerEquipoAleatorio(equiposMismoJuego);
        String[] equipo2Data = null;
        do {
            equipo2Data = obtenerEquipoAleatorio(equiposMismoJuego);
        } while (equipo1Data[0].equals(equipo2Data[0]));

        String idEmparejamiento = generarIdAleatorio();
        String idTorneoAleatorio = generarIdAleatorioNumerico();
        String fechaActual = new java.text.SimpleDateFormat("yyyy/MM/dd").format(new java.util.Date());
        String horaAleatoria = generarHoraAleatoria();
        String rondaAleatoria = String.valueOf(new Random().nextInt(5) + 1);
        String estado = "Pendiente";

        // Generar ganador y perdedor aleatorio
        String ganadorId = new Random().nextBoolean() ? equipo1Data[0] : equipo2Data[0];
        String perdedorId = ganadorId.equals(equipo1Data[0]) ? equipo2Data[0] : equipo1Data[0];

        guardarEnExcelRandom(idEmparejamiento, idTorneoAleatorio, equipo1Data[0], equipo2Data[0], fechaActual, horaAleatoria, rondaAleatoria, estado, ganadorId, perdedorId);

        String mensaje = "Emparejamiento generado:\nID Emparejamiento: " + idEmparejamiento +
                         "\nID Torneo: " + idTorneoAleatorio +
                         "\nEquipo 1: " + equipo1Data[1] + " vs Equipo 2: " + equipo2Data[1] +
                         "\nFecha: " + fechaActual +
                         "\nHora: " + horaAleatoria +
                         "\nRonda: " + rondaAleatoria +
                         "\nEstado: " + estado +
                         "\nGanador (ID): " + ganadorId + " (" + (ganadorId.equals(equipo1Data[0]) ? equipo1Data[1] : equipo2Data[1]) + ")" +
                         "\nPerdedor (ID): " + perdedorId + " (" + (perdedorId.equals(equipo1Data[0]) ? equipo1Data[1] : equipo2Data[1]) + ")";
        JOptionPane.showMessageDialog(ventanaPrincipal, mensaje, "Emparejamiento Generado", JOptionPane.INFORMATION_MESSAGE);

        panelRegistrarEmparejamiento.limpiarFormulario();
    }

    private void guardarEnExcelRandom(String idEmparejamiento, String idTorneo, String idEquipo1, String idEquipo2, String fecha, String hora, String ronda, String estado, String ganador, String perdedor) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("ID Empare");
        model.addColumn("ID Equipo 1");
        model.addColumn("ID Equipo 2");
        model.addColumn("ID Torneo");
        model.addColumn("Fecha");
        model.addColumn("Hora");
        model.addColumn("Ronda");
        model.addColumn("Estado");
        model.addColumn("Ganador");
        model.addColumn("Perdedor");
        model.addRow(new Object[]{generarIdAleatorio(), idEmparejamiento, idEquipo1, idEquipo2, idTorneo, fecha, hora, ronda, estado, ganador, perdedor});

        JTable table = new JTable(model);
        String excelFilePath = ARCHIVOS_PATH + "emparejamientos.xls";

        File file = new File(excelFilePath);
        WritableWorkbook workbook = null;
        Workbook existingWorkbook = null;
        try {
            WorkbookSettings wbSettings = new WorkbookSettings();
            wbSettings.setEncoding("UTF-8");

            if (file.exists()) {
                existingWorkbook = Workbook.getWorkbook(file);
                workbook = Workbook.createWorkbook(file, existingWorkbook, wbSettings);
            } else {
                workbook = Workbook.createWorkbook(file, wbSettings);
            }

            WritableSheet sheet = null;
            if (file.exists() && existingWorkbook.getNumberOfSheets() > 0) {
                sheet = workbook.getSheet(0);
            } else {
                sheet = workbook.createSheet("Hoja 1", 0);
                TableModel tableModel = table.getModel();
                for (int i = 0; i < tableModel.getColumnCount(); i++) {
                    Label label = new Label(i, 0, tableModel.getColumnName(i));
                    sheet.addCell(label);
                }
            }

            TableModel tableModel = table.getModel();
            int startRow = sheet.getRows();
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                for (int j = 0; j < tableModel.getColumnCount(); j++) {
                    Object value = tableModel.getValueAt(i, j);
                    String cellValue = (value != null) ? value.toString() : "";
                    Label label = new Label(j, startRow + i, cellValue);
                    sheet.addCell(label);
                }
            }

            workbook.write();
            System.out.println("Archivo Excel actualizado en: " + excelFilePath);

        } catch (IOException e) {
            System.err.println("Error de IO: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(ventanaPrincipal, "Error al guardar el emparejamiento.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (WriteException e) {
            System.err.println("Error de escritura: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(ventanaPrincipal, "Error al guardar el emparejamiento.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (BiffException e) {
            System.err.println("Error de Biff (problema al leer el archivo existente): " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(ventanaPrincipal, "Error al guardar el emparejamiento.", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (workbook != null) {
                    workbook.close();
                }
                if (existingWorkbook != null) {
                    existingWorkbook.close();
                }
            } catch (IOException | WriteException e) {
                System.err.println("Error al cerrar el libro de trabajo: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}