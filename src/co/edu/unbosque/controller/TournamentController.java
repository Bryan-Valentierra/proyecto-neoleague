package co.edu.unbosque.controller;

import co.edu.unbosque.model.Torneo;
import co.edu.unbosque.view.PanelTorneosActivos;
import co.edu.unbosque.view.VentanaPrincipal;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TournamentController {

    private VentanaPrincipal ventanaPrincipal;
    private final String ARCHIVOS_PATH = ".\\src\\co\\edu\\unbosque\\archivos\\";
    private List<Torneo> listaTorneos;
    
        public TournamentController() {
      
    }
        
        public List<Torneo> getListaTorneos() {
    return listaTorneos;
}

    public TournamentController( VentanaPrincipal ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
        this.listaTorneos = cargarTorneosActivosDesdeExcel(); // Método específico para torneos activos
        mostrarTorneosActivos();
    }

 

    private List<Torneo> cargarTorneosActivosDesdeExcel() {
        List<Object[]> datosTabla = new ArrayList<>();
        List<Torneo> torneosActivos = new ArrayList<>();
        File file = new File(ARCHIVOS_PATH + "torneos.xls");
        try {
            Workbook workbook = Workbook.getWorkbook(file);
            Sheet sheet = workbook.getSheet("Torneos");
            if (sheet != null && sheet.getRows() > 1) {
                for (int i = 1; i < sheet.getRows(); i++) {
                    String idTorneo = sheet.getCell(0, i).getContents();
                    String nombre = sheet.getCell(1, i).getContents();
                    String estado = sheet.getCell(6, i).getContents(); // Asumiendo que la columna de estado es la 7 (índice 6)

                    if (estado.equalsIgnoreCase("Activo")) {
                        Torneo torneo = new Torneo(idTorneo, nombre, null, null, "", "", 0); // Constructor con solo ID y Nombre
                        torneo.setIdTorneo(idTorneo); // Establecer el ID
                        torneosActivos.add(torneo);
                        datosTabla.add(new Object[]{nombre, idTorneo}); // Datos para la tabla
                    }
                }
            }
            workbook.close();
        } catch (IOException | BiffException e) {
            System.err.println("Error al leer el archivo de torneos: " + e.getMessage());
            JOptionPane.showMessageDialog(ventanaPrincipal, "Error al cargar la lista de torneos activos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return torneosActivos;
    }

    private void mostrarTorneosActivos() {
        // La tabla se actualiza directamente en cargarTorneosActivosDesdeExcel()
    }

    private void mostrarDetallesTorneo(Torneo torneo) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String detalles = "ID: " + torneo.getIdTorneo() + "\n" +
                         "Nombre: " + torneo.getNombre() + "\n" +
                         (torneo.getFechaInicio() != null ? "Fecha de Inicio: " + dateFormat.format(torneo.getFechaInicio()) + "\n" : "") +
                         (torneo.getFechaFin() != null ? "Fecha de Fin: " + dateFormat.format(torneo.getFechaFin()) + "\n" : "") +
                         "Tipo de Juego: " + torneo.getTipoJuego() + "\n" +
                         "Formato: " + torneo.getFormato() + "\n" +
                         "Reglamento: " + torneo.getReglamento() + "\n" +
                         "Máximo de Equipos: " + torneo.getMaxEquipos();
        JOptionPane.showMessageDialog(ventanaPrincipal, detalles, "Detalles del Torneo", JOptionPane.INFORMATION_MESSAGE);
    }

    private Torneo buscarTorneoPorId(String id) {
        File file = new File(ARCHIVOS_PATH + "torneos.xls");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Workbook workbook = Workbook.getWorkbook(file);
            Sheet sheet = workbook.getSheet("Torneos");
            if (sheet != null && sheet.getRows() > 1) {
                for (int i = 1; i < sheet.getRows(); i++) {
                    String idTorneo = sheet.getCell(0, i).getContents();
                    if (idTorneo.equals(id)) {
                        String nombre = sheet.getCell(1, i).getContents();
                        Date fechaInicio = null;
                        try {
                            fechaInicio = dateFormat.parse(sheet.getCell(2, i).getContents());
                        } catch (ParseException e) {
                            System.err.println("Error al parsear fecha de inicio para " + id + ": " + e.getMessage());
                        }
                        Date fechaFin = null;
                        try {
                            fechaFin = dateFormat.parse(sheet.getCell(3, i).getContents());
                        } catch (ParseException e) {
                            System.err.println("Error al parsear fecha de fin para " + id + ": " + e.getMessage());
                        }
                        String reglamento = sheet.getCell(4, i).getContents();
                        String tipoJuego = sheet.getCell(5, i).getContents();
                        int maxEquipos = Integer.parseInt(sheet.getCell(6, i).getContents());
                        String formato = sheet.getCell(7, i).getContents(); // Asumiendo que el formato está en la columna 8 (índice 7)
                        Torneo torneo = new Torneo(idTorneo, nombre, fechaInicio, fechaFin, reglamento, tipoJuego, maxEquipos);
                        torneo.setFormato(formato);
                        return torneo;
                    }
                }
            }
            workbook.close();
        } catch (IOException | BiffException e) {
            System.err.println("Error al leer el archivo de torneos: " + e.getMessage());
        }
        return null;
    }
}