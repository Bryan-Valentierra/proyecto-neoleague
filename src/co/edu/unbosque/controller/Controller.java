// Controlador.java
package co.edu.unbosque.controller;

import co.edu.unbosque.view.*;
import co.edu.unbosque.model.*;
import co.edu.unbosque.util.ExcelExporterJExcel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.Sheet;
import jxl.Cell;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class Controller {

    private VentanaPrincipal ventanaPrincipal;
    private static final String USUARIO_CORRECTO = "admin";
    private static final String CONTRASENA_CORRECTA = "1234";
    private final String ARCHIVOS_PATH = ".\\src\\co\\edu\\unbosque\\archivos\\"; // Ruta correcta a la carpeta archivos

    public Controller(VentanaPrincipal ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
        File directorio = new File(ARCHIVOS_PATH);
        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                System.out.println("Directorio 'archivos' creado exitosamente.");
            } else {
                System.err.println("Error al crear el directorio 'archivos'.");
            }
        }
        cargarFechasDeTorneos();
        configurarListeners();
    }

    private void cargarFechasDeTorneos() {
        PanelInicioSesion panelInicioSesion = ventanaPrincipal.getPanelInicioSesion();
        JComboBox<String> comboFechasTorneos = panelInicioSesion.getComboBoxFechasTorneos();
        List<String> fechasTorneos = obtenerFechasDeTorneosDesdeExcel();
        panelInicioSesion.actualizarComboBoxTorneos(fechasTorneos);
    }

    private List<String> obtenerFechasDeTorneosDesdeExcel() {
        List<String> fechas = new ArrayList<>();
        File file = new File(ARCHIVOS_PATH + "torneos.xls");
        if (file.exists()) {
            try {
                Workbook existingWorkbook = Workbook.getWorkbook(file);
                Sheet sheet = existingWorkbook.getSheet("Torneos");
                if (sheet != null && sheet.getRows() > 1) {
                    for (int i = 1; i < sheet.getRows(); i++) {
                        Cell fechaInicioCell = sheet.getCell(2, i);
                        Cell fechaFinCell = sheet.getCell(3, i);
                        if (fechaInicioCell != null && fechaFinCell != null) {
                            fechas.add(fechaInicioCell.getContents() + " - " + fechaFinCell.getContents());
                        }
                    }
                }
                existingWorkbook.close();
            } catch (IOException | BiffException e) {
                System.err.println("Error al leer las fechas de torneos desde Excel: " + e.getMessage());
                e.printStackTrace();
            }
        }
        return fechas;
    }

    private void configurarListeners() {
        PanelInicioSesion panelInicioSesion = ventanaPrincipal.getPanelInicioSesion();
        panelInicioSesion.getBotonIniciarSesion().addActionListener(e -> {
            String usuario = panelInicioSesion.getCampoUsuario().getText();
            char[] passwordChars = panelInicioSesion.getCampoContrasena().getPassword();
            String contrasena = new String(passwordChars);
            if (verificarCredenciales(usuario, contrasena)) {
                ventanaPrincipal.mostrarPanelPrincipal();
                panelInicioSesion.limpiarFormulario();
            } else {
                panelInicioSesion.mostrarMensaje("Credenciales incorrectas. Intente de nuevo.");
            }
            Arrays.fill(passwordChars, ' ');
        });

        PanelPrincipal panelPrincipal = ventanaPrincipal.getPanelPrincipal();
        panelPrincipal.getBotonRegistrar().addActionListener(e -> ventanaPrincipal.mostrarPanelRegistro());
        panelPrincipal.getBotonAnalisis().addActionListener(e -> ventanaPrincipal.mostrarPanelAnalisis());
        panelPrincipal.getBotonTorneosActivos().addActionListener(e -> ventanaPrincipal.mostrarPanelTorneosActivos());
        panelPrincipal.getBotonResultados().addActionListener(e -> ventanaPrincipal.mostrarPanelResultados());
        panelPrincipal.getBotonDisponibilidadEquipos().addActionListener(e -> {
            List<Object[]> disponibilidad = obtenerDisponibilidadEquiposDesdeExcel();
            ventanaPrincipal.getPanelDisponibilidadEquipos().actualizarTabla(disponibilidad);
            ventanaPrincipal.mostrarPanelDisponibilidadEquipos();
        });

        PanelRegistro panelRegistro = ventanaPrincipal.getPanelRegistro();
        panelRegistro.getBotonRegistrarJugador().addActionListener(e -> ventanaPrincipal.mostrarPanelRegistroJugador());
        panelRegistro.getBotonRegistrarEntrenador().addActionListener(e -> ventanaPrincipal.mostrarPanelRegistroEntrenador());
        panelRegistro.getBotonRegistrarEquipo().addActionListener(e -> ventanaPrincipal.mostrarPanelRegistroEquipo());
        panelRegistro.getBotonRegistrarAdministrador().addActionListener(e -> ventanaPrincipal.mostrarPanelRegistroAdministrador());
        panelRegistro.getBotonRegistrarEmparejamiento().addActionListener(e -> ventanaPrincipal.mostrarPanelRegistrarEmparejamiento());
        panelRegistro.getBotonRegistrarTorneo().addActionListener(e -> ventanaPrincipal.mostrarPanelRegistrarTorneo());
        panelRegistro.getBotonVolver().addActionListener(e -> ventanaPrincipal.mostrarPanelPrincipal());

        PanelRegistroJugador panelRegistroJugador = ventanaPrincipal.getPanelRegistroJugador();
        panelRegistroJugador.getBotonRegistrar().addActionListener(e -> registrarJugador(panelRegistroJugador));
        panelRegistroJugador.getBotonVolver().addActionListener(e -> ventanaPrincipal.mostrarPanelRegistro());
        panelRegistroJugador.getBotonEliminar().addActionListener(e -> eliminarEntidadPorNombre("jugadores.xls", "Jugadores", 1, "jugador"));
        panelRegistroJugador.getBotonModificar().addActionListener(e -> modificarEntidadPorNombre("jugadores.xls", "Jugadores", 1, "jugador", panelRegistroJugador.getCamposModificarJugador()));

        PanelRegistroEntrenador panelRegistroEntrenador = ventanaPrincipal.getPanelRegistroEntrenador();
        panelRegistroEntrenador.getBotonRegistrarEntrenador().addActionListener(e -> registrarEntrenador(panelRegistroEntrenador));
        panelRegistroEntrenador.getBotonVolver().addActionListener(e -> ventanaPrincipal.mostrarPanelRegistro());
        panelRegistroEntrenador.getBotonEliminarEntrenador().addActionListener(e -> eliminarEntidadPorNombre("entrenadores.xls", "Entrenadores", 1, "entrenador"));
        panelRegistroEntrenador.getBotonModificarEntrenador().addActionListener(e -> modificarEntidadPorNombre("entrenadores.xls", "Entrenadores", 1, "entrenador", panelRegistroEntrenador.getCamposModificarEntrenador()));

        PanelRegistroEquipo panelRegistroEquipo = ventanaPrincipal.getPanelRegistroEquipo();
        panelRegistroEquipo.getBotonRegistrarEquipo().addActionListener(e -> registrarEquipo(panelRegistroEquipo));
        panelRegistroEquipo.getBotonVolver().addActionListener(e -> ventanaPrincipal.mostrarPanelRegistro());
        panelRegistroEquipo.getBotonEliminar().addActionListener(e -> eliminarEntidadPorNombre("equipos.xls", "Equipos", 1, "equipo"));
        panelRegistroEquipo.getBotonModificar().addActionListener(e -> modificarEntidadPorNombre("equipos.xls", "Equipos", 1, "equipo", panelRegistroEquipo.getCamposModificarEquipo()));

        PanelRegistroAdministrador panelRegistroAdministrador = ventanaPrincipal.getPanelRegistroAdministrador();
        panelRegistroAdministrador.getBotonRegistrar().addActionListener(e -> registrarAdministrador(panelRegistroAdministrador));
        panelRegistroAdministrador.getBotonVolver().addActionListener(e -> ventanaPrincipal.mostrarPanelRegistro());
        panelRegistroAdministrador.getBotonEliminar().addActionListener(e -> eliminarEntidadPorNombre("administradores.xls", "Administradores", 1, "administrador"));
        panelRegistroAdministrador.getBotonModificar().addActionListener(e -> modificarEntidadPorNombre("administradores.xls", "Administradores", 1, "administrador", panelRegistroAdministrador.getCamposModificarAdministrador()));

        PanelRegistrarEmparejamiento panelRegistrarEmparejamiento = ventanaPrincipal.getPanelRegistrarEmparejamiento();
        panelRegistrarEmparejamiento.getBotonRegistrar().addActionListener(e -> registrarEmparejamiento(panelRegistrarEmparejamiento));
        panelRegistrarEmparejamiento.getBotonVolver().addActionListener(e -> ventanaPrincipal.mostrarPanelRegistro());
        panelRegistrarEmparejamiento.getBotonEliminar().addActionListener(e -> eliminarEntidadPorNombre("emparejamientos.xls", "Emparejamientos", 0, "emparejamiento"));
        panelRegistrarEmparejamiento.getBotonModificar().addActionListener(e -> modificarEntidadPorNombre("emparejamientos.xls", "Emparejamientos", 0, "emparejamiento", panelRegistrarEmparejamiento.getCamposModificarEmparejamiento()));

        PanelRegistrarTorneo panelRegistrarTorneo = ventanaPrincipal.getPanelRegistrarTorneo();
        panelRegistrarTorneo.getBotonRegistrar().addActionListener(e -> registrarTorneo(panelRegistrarTorneo));
        panelRegistrarTorneo.getBotonVolver().addActionListener(e -> ventanaPrincipal.mostrarPanelRegistro());
        panelRegistrarTorneo.getBotonModificar().addActionListener(e -> modificarEntidadPorNombre("torneos.xls", "Torneos", 1, "torneo", panelRegistrarTorneo.getCamposModificarTorneo()));
        panelRegistrarTorneo.getBotonEliminar().addActionListener(e -> eliminarEntidadPorNombre("torneos.xls", "Torneos", 1, "torneo"));

        PanelAnalisis panelAnalisis = ventanaPrincipal.getPanelAnalisis();
        panelAnalisis.getBotonVolver().addActionListener(e -> ventanaPrincipal.mostrarPanelPrincipal());

        PanelTorneosActivos panelTorneosActivos = ventanaPrincipal.getPanelTorneosActivos();
        panelTorneosActivos.getBotonVolver().addActionListener(e -> ventanaPrincipal.mostrarPanelPrincipal());
        panelTorneosActivos.getBotonVerDetalles().addActionListener(e -> {
            int filaSeleccionada = panelTorneosActivos.getTablaTorneos().getSelectedRow();
            if (filaSeleccionada != -1) {
                String nombreTorneo = (String) panelTorneosActivos.getTablaTorneos().getValueAt(filaSeleccionada, 0);
                JOptionPane.showMessageDialog(ventanaPrincipal, "Detalles de: " + nombreTorneo, "Detalles del Torneo", JOptionPane.INFORMATION_MESSAGE);
                // Implementar lógica para mostrar detalles
            } else {
                JOptionPane.showMessageDialog(ventanaPrincipal, "Por favor, seleccione un torneo para ver los detalles.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        });

        PanelResultados panelResultados = ventanaPrincipal.getPanelResultados();
        panelResultados.getBotonVolver().addActionListener(e -> ventanaPrincipal.mostrarPanelPrincipal());

        PanelDisponibilidadEquipos panelDisponibilidadEquipos = ventanaPrincipal.getPanelDisponibilidadEquipos();
        panelDisponibilidadEquipos.getBotonVolver().addActionListener(e -> ventanaPrincipal.mostrarPanelPrincipal());
    }

    private void registrarJugador(PanelRegistroJugador panel) {
        String id = panel.getCampoId().getText();
        String nombre = panel.getCampoNombre().getText();
        String apellido = panel.getCampoApellido().getText();
        String fechaNacimiento = panel.getCampoFechaNacimiento().getText();
        String nacionalidad = panel.getCampoNacionalidad().getText();
        String correo = panel.getCampoCorreo().getText();
        String juegosParticipados = panel.getCampoJuegosParticipados().getText();
        String victorias = panel.getCampoVictorias().getText();
        String derrotas = panel.getCampoDerrotas().getText();
        String record = panel.getCampoRecord().getText();
        String juegoFavorito = panel.getCampoJuegoFavorito().getText();
        String rolPrincipal = panel.getCampoRolPrincipal().getText();
        String estadisticas = panel.getCampoEstadisticas().getText();
        String historialCompetitivo = panel.getCampoHistorialCompetitivo().getText();
        String analisisJuegos = panel.getCampoAnalisisJuegos().getText();

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nombre");
        model.addColumn("Apellido");
        model.addColumn("Fecha de Nacimiento");
        model.addColumn("Nacionalidad");
        model.addColumn("Correo");
        model.addColumn("Juegos Participados");
        model.addColumn("Victorias");
        model.addColumn("Derrotas");
        model.addColumn("Registro");
        model.addColumn("Juego Favorito");
        model.addColumn("Rol Principal");
        model.addColumn("Estadísticas");
        model.addColumn("Historial Competitivo");
        model.addColumn("Análisis Juegos");
        model.addRow(new Object[]{id, nombre, apellido, fechaNacimiento, nacionalidad, correo,
            juegosParticipados, victorias, derrotas, record, juegoFavorito, rolPrincipal, estadisticas,
            historialCompetitivo, analisisJuegos});
        agregarDatosExcel(ARCHIVOS_PATH + "jugadores.xls", model, "Jugadores");
        JOptionPane.showMessageDialog(ventanaPrincipal, "Jugador registrado y guardado en jugadores.xls", "Registro Exitoso", JOptionPane.INFORMATION_MESSAGE);
        panel.limpiarFormulario();
    }

    private void registrarEntrenador(PanelRegistroEntrenador panel) {
        String id = panel.getCampoId().getText();
        String nombre = panel.getCampoNombre().getText();
        String apellido = panel.getCampoApellido().getText();
        String fechaNacimiento = panel.getCampoFechaNacimiento().getText();
        String nacionalidad = panel.getCampoNacionalidad().getText();
        String correo = panel.getCampoCorreo().getText();
        String experiencia = panel.getCampoExperiencia().getText();
        String equipoAsignado = panel.getCampoEquipoAsignado().getText();

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nombre");
        model.addColumn("Apellido");
        model.addColumn("Fecha de Nacimiento");
        model.addColumn("Nacionalidad");
        model.addColumn("Correo");
        model.addColumn("Experiencia");
        model.addColumn("Equipo Asignado");
        model.addRow(new Object[]{id, nombre, apellido, fechaNacimiento, nacionalidad, correo,
            experiencia, equipoAsignado});
        agregarDatosExcel(ARCHIVOS_PATH + "entrenadores.xls", model, "Entrenadores");
        JOptionPane.showMessageDialog(ventanaPrincipal, "Entrenador registrado y guardado en entrenadores.xls", "Registro Exitoso", JOptionPane.INFORMATION_MESSAGE);
        panel.limpiarFormulario();
    }

    private void registrarEquipo(PanelRegistroEquipo panel) {
        String idEquipo = panel.getCampoIdEquipo().getText();
        String nombreEquipo = panel.getCampoNombreEquipo().getText();
        String fechaCreacion = panel.getCampoFechaCreacion().getText();
        int numeroJugadores = (int) panel.getSpinnerNumeroJugadores().getValue();
        String entrenador = panel.getCampoEntrenador().getText();
        String juegoPrincipal = panel.getCampoJuegoPrincipal().getText();
        String patrocinador = panel.getCampoPatrocinador().getText();
        String sedeEntrenamiento = panel.getCampoSedeEntrenamiento().getText();

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Equipo");
        model.addColumn("Nombre Equipo");
        model.addColumn("Fecha Creacion");
        model.addColumn("Numero Jugadores");
        model.addColumn("Entrenador");
        model.addColumn("Juego Principal");
        model.addColumn("Patrocinador");
        model.addColumn("Sede Entrenamiento");
        model.addRow(new Object[]{idEquipo, nombreEquipo, fechaCreacion, numeroJugadores,
            entrenador, juegoPrincipal, patrocinador, sedeEntrenamiento});
        agregarDatosExcel(ARCHIVOS_PATH + "equipos.xls", model, "Equipos");
        JOptionPane.showMessageDialog(ventanaPrincipal, "Equipo registrado y guardado en equipos.xls", "Registro Exitoso", JOptionPane.INFORMATION_MESSAGE);
        panel.limpiarFormulario();
    }

    private void registrarAdministrador(PanelRegistroAdministrador panel) {
        String idAdministrador = panel.getCampoIdAdministrador().getText();
        String nombre = panel.getCampoNombre().getText();
        String usuario = panel.getCampoUsuario().getText();
        char[] passwordChars = panel.getCampoContrasena().getPassword();
        String contrasena = new String(passwordChars);

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Administrador de ID");
        model.addColumn("Nombre");
        model.addColumn("Usuario");
        model.addColumn("Contraseña");
        model.addRow(new Object[]{idAdministrador, nombre, usuario, contrasena});
        agregarDatosExcel(ARCHIVOS_PATH + "administradores.xls", model, "Administradores");
        JOptionPane.showMessageDialog(ventanaPrincipal, "Administrador registrado y guardado en administradores.xls", "Registro Exitoso", JOptionPane.INFORMATION_MESSAGE);
        panel.limpiarFormulario();
        Arrays.fill(passwordChars, ' ');
    }

    private void registrarEmparejamiento(PanelRegistrarEmparejamiento panel) {
        String idEmparejamiento = panel.getCampoIdEmparejamiento().getText();
        String idEquipo1 = panel.getCampoIdEquipo1().getText();
        String idEquipo2 = panel.getCampoIdEquipo2().getText();
        String idTorneo = panel.getCampoIdTorneo().getText();
        String fecha = panel.getCampoFecha().getText();
        String hora = panel.getCampoHora().getText();
        String ronda = panel.getCampoRonda().getText();
        String estado = (String) panel.getComboEstado().getSelectedItem();

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Emparejamiento ID");
        model.addColumn("ID Equipo 1");
        model.addColumn("ID Equipo 2");
        model.addColumn("ID Torneo");
        model.addColumn("Fecha");
        model.addColumn("Hora");
        model.addColumn("Ronda");
        model.addColumn("Estado");
        model.addRow(new Object[]{idEmparejamiento, idEquipo1, idEquipo2, idTorneo, fecha, hora, ronda, estado});
        agregarDatosExcel(ARCHIVOS_PATH + "emparejamientos.xls", model, "Emparejamientos");
        JOptionPane.showMessageDialog(ventanaPrincipal, "Emparejamiento registrado y guardado en emparejamientos.xls", "Registro Exitoso", JOptionPane.INFORMATION_MESSAGE);
        panel.limpiarFormulario();
    }

    private void registrarTorneo(PanelRegistrarTorneo panel) {
        String idTorneo = panel.getCampoIdTorneo().getText();
        String nombreTorneo = panel.getCampoNombre().getText();
        String fechaInicio = panel.getCampoFechaInicio().getText();
        String fechaFin = panel.getCampoFechaFin().getText();
        String reglamento = panel.getCampoReglamento().getText();
        String tipoJuego = panel.getCampoTipoJuego().getText();
        int maxEquipos = (int) panel.getSpinnerMaxEquipos().getValue();

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Torneo");
        model.addColumn("Nombre Torneo");
        model.addColumn("Fecha Inicio");
        model.addColumn("Fecha Fin");
        model.addColumn("Reglamento");
        model.addColumn("Tipo Juego");
        model.addColumn("Max Equipos");
        model.addRow(new Object[]{idTorneo, nombreTorneo, fechaInicio, fechaFin, reglamento, tipoJuego, String.valueOf(maxEquipos)});
        agregarDatosExcel(ARCHIVOS_PATH + "torneos.xls", model, "Torneos");
        JOptionPane.showMessageDialog(ventanaPrincipal, "Torneo registrado y guardado en torneos.xls", "Registro Exitoso", JOptionPane.INFORMATION_MESSAGE);
        panel.limpiarFormulario();
        cargarFechasDeTorneos(); // Actualizar el combo box de torneos
    }

    private void eliminarEntidadPorNombre(String archivoNombre, String hojaNombre, int columnaNombre, String tipoEntidad) {
        String nombreEliminar = JOptionPane.showInputDialog(ventanaPrincipal, "Ingrese el nombre del " + tipoEntidad + " a eliminar:");
        if (nombreEliminar != null && !nombreEliminar.trim().isEmpty()) {
            File archivo = new File(ARCHIVOS_PATH + archivoNombre);
            File archivoTemp = new File(ARCHIVOS_PATH + "temp_" + archivoNombre);
            WritableWorkbook libroTrabajo = null;
            Workbook libroExistente = null;
            boolean encontrado = false;

            try {
                WorkbookSettings wbSettings = new WorkbookSettings();
                wbSettings.setEncoding("UTF-8");
                libroTrabajo = Workbook.createWorkbook(archivoTemp, wbSettings);
                libroExistente = Workbook.getWorkbook(archivo);
                Sheet hojaExistente = libroExistente.getSheet(hojaNombre);
                WritableSheet hojaNueva = libroTrabajo.createSheet(hojaNombre, 0);

                if (hojaExistente != null) {
                    for (int fila = 0; fila < hojaExistente.getRows(); fila++) {
                        Cell nombreCell = hojaExistente.getCell(columnaNombre, fila);
                        if (nombreCell != null && nombreCell.getContents().equalsIgnoreCase(nombreEliminar.trim())) {
                            encontrado = true;
                            continue; // Saltar esta fila
                        }
                        for (int columna = 0; columna < hojaExistente.getColumns(); columna++) {
                            Cell celda = hojaExistente.getCell(columna, fila);
                            hojaNueva.addCell(new Label(columna, fila, celda.getContents()));
                        }
                    }
                }

                libroTrabajo.write();
                libroTrabajo.close();
                libroExistente.close();

                if (encontrado) {
                    archivo.delete();
                    archivoTemp.renameTo(archivo);
                    JOptionPane.showMessageDialog(ventanaPrincipal, tipoEntidad.substring(0, 1).toUpperCase() + tipoEntidad.substring(1) + " '" + nombreEliminar + "' eliminado.", "Eliminación Exitosa", JOptionPane.INFORMATION_MESSAGE);
                    if (archivoNombre.equals("torneos.xls")) {
                        cargarFechasDeTorneos(); // Actualizar el combo box de torneos si se eliminó un torneo
                    }
                } else {
                    archivoTemp.delete();
                    JOptionPane.showMessageDialog(ventanaPrincipal, "No se encontró ningún " + tipoEntidad + " con el nombre '" + nombreEliminar + "'.", "Error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (IOException | WriteException | BiffException e) {
                System.err.println("Error al eliminar " + tipoEntidad + ": " + e.getMessage());
                e.printStackTrace();
                JOptionPane.showMessageDialog(ventanaPrincipal, "Error al eliminar el " + tipoEntidad + ".", "Error", JOptionPane.ERROR_MESSAGE);
                cerrarLibrosExcel(libroTrabajo, libroExistente, archivoTemp);
            } finally {
                cerrarLibrosExcel(libroTrabajo, libroExistente, archivoTemp);
            }
        } else if (nombreEliminar != null) {
            JOptionPane.showMessageDialog(ventanaPrincipal, "Por favor, ingrese un nombre.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void modificarEntidadPorNombre(String archivoNombre, String hojaNombre, int columnaNombre, String tipoEntidad, List<JTextField> camposModificar) {
        String nombreModificar = JOptionPane.showInputDialog(ventanaPrincipal, "Ingrese el nombre del " + tipoEntidad + " a modificar:");
        if (nombreModificar != null && !nombreModificar.trim().isEmpty()) {
            File archivo = new File(ARCHIVOS_PATH + archivoNombre);
            File archivoTemp = new File(ARCHIVOS_PATH + "temp_" + archivoNombre);
            WritableWorkbook libroTrabajo = null;
            Workbook libroExistente = null;
            boolean encontrado = false;

            try {
                WorkbookSettings wbSettings = new WorkbookSettings();
                wbSettings.setEncoding("UTF-8");
                libroTrabajo = Workbook.createWorkbook(archivoTemp, wbSettings);
                libroExistente = Workbook.getWorkbook(archivo);
                Sheet hojaExistente = libroExistente.getSheet(hojaNombre);
                WritableSheet hojaNueva = libroTrabajo.createSheet(hojaNombre, 0);

                if (hojaExistente != null) {
                    int numColumnas = hojaExistente.getColumns();
                    for (int fila = 0; fila < hojaExistente.getRows(); fila++) {
                        Cell nombreCell = hojaExistente.getCell(columnaNombre, fila);
                        if (nombreCell != null && nombreCell.getContents().equalsIgnoreCase(nombreModificar.trim())) {
                            encontrado = true;
                            if (camposModificar != null && camposModificar.size() == numColumnas) {
                                for (int columna = 0; columna < numColumnas; columna++) {
                                    String nuevoValor = camposModificar.get(columna).getText();
                                    hojaNueva.addCell(new Label(columna, fila, nuevoValor));
                                }
                                JOptionPane.showMessageDialog(ventanaPrincipal, tipoEntidad.substring(0, 1).toUpperCase() + tipoEntidad.substring(1) + " '" + nombreModificar + "' modificado.", "Modificación Exitosa", JOptionPane.INFORMATION_MESSAGE);
                                if (archivoNombre.equals("torneos.xls")) {
                                    cargarFechasDeTorneos(); // Actualizar el combo box de torneos si se modificó un torneo
                                }
                            } else {
                                JOptionPane.showMessageDialog(ventanaPrincipal, "Error: Número incorrecto de campos para modificar " + tipoEntidad + ".", "Error", JOptionPane.ERROR_MESSAGE);
                                for (int columna = 0; columna < numColumnas; columna++) {
                                    Cell celda = hojaExistente.getCell(columna, fila);
                                    hojaNueva.addCell(new Label(columna, fila, celda.getContents()));
                                }
                            }
                        } else {
                            for (int columna = 0; columna < numColumnas; columna++) {
                                Cell celda = hojaExistente.getCell(columna, fila);
                                hojaNueva.addCell(new Label(columna, fila, celda.getContents()));
                            }
                        }
                    }
                }

                libroTrabajo.write();
                libroTrabajo.close();
                libroExistente.close();

                if (!encontrado) {
                    archivoTemp.delete();
                    JOptionPane.showMessageDialog(ventanaPrincipal, "No se encontró ningún " + tipoEntidad + " con el nombre '" + nombreModificar + "'.", "Error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (IOException | WriteException | BiffException e) {
                System.err.println("Error al modificar " + tipoEntidad + ": " + e.getMessage());
                e.printStackTrace();
                JOptionPane.showMessageDialog(ventanaPrincipal, "Error al modificar el " + tipoEntidad + ".", "Error", JOptionPane.ERROR_MESSAGE);
                cerrarLibrosExcel(libroTrabajo, libroExistente, archivoTemp);
            } finally {
                cerrarLibrosExcel(libroTrabajo, libroExistente, archivoTemp);
            }
        } else if (nombreModificar != null) {
            JOptionPane.showMessageDialog(ventanaPrincipal, "Por favor, ingrese un nombre.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void cerrarLibrosExcel(WritableWorkbook libroTrabajo, Workbook libroExistente, File archivoTemp) {
        if (libroTrabajo != null) {
            try {
                libroTrabajo.close();
            } catch (IOException | WriteException ex) {
                ex.printStackTrace();
            }
        }
        if (libroExistente != null) {
            libroExistente.close();
        }
        if (archivoTemp.exists()) {
            archivoTemp.delete();
        }
    }

    private List<JTextField> getCamposModificarJugador() {
        PanelRegistroJugador panel = ventanaPrincipal.getPanelRegistroJugador();
        return Arrays.asList(panel.getCampoId(), panel.getCampoNombre(), panel.getCampoApellido(),
                panel.getCampoFechaNacimiento(), panel.getCampoNacionalidad(), panel.getCampoCorreo(),
                panel.getCampoJuegosParticipados(), panel.getCampoVictorias(), panel.getCampoDerrotas(),
                panel.getCampoRecord(), panel.getCampoJuegoFavorito(), panel.getCampoRolPrincipal(),
                panel.getCampoEstadisticas(), panel.getCampoHistorialCompetitivo(), panel.getCampoAnalisisJuegos());
    }

    private List<JTextField> getCamposModificarEntrenador() {
        PanelRegistroEntrenador panel = ventanaPrincipal.getPanelRegistroEntrenador();
        return Arrays.asList(panel.getCampoId(), panel.getCampoNombre(), panel.getCampoApellido(),
                panel.getCampoFechaNacimiento(), panel.getCampoNacionalidad(), panel.getCampoCorreo(),
                panel.getCampoExperiencia(), panel.getCampoEquipoAsignado());
    }

    private List<JTextField> getCamposModificarEquipo() {
        PanelRegistroEquipo panel = ventanaPrincipal.getPanelRegistroEquipo();
        List<JTextField> campos = new ArrayList<>(Arrays.asList(panel.getCampoIdEquipo(), panel.getCampoNombreEquipo(),
                panel.getCampoFechaCreacion(), panel.getCampoEntrenador(), panel.getCampoJuegoPrincipal(),
                panel.getCampoPatrocinador(), panel.getCampoSedeEntrenamiento()));
        JTextField campoNumeroJugadores = new JTextField(String.valueOf(panel.getSpinnerNumeroJugadores().getValue()));
        campos.add(3, campoNumeroJugadores); // Insertar después de Fecha Creacion
        return campos;
    }

    private List<JTextField> getCamposModificarAdministrador() {
        PanelRegistroAdministrador panel = ventanaPrincipal.getPanelRegistroAdministrador();
        List<JTextField> campos = new ArrayList<>(Arrays.asList(panel.getCampoIdAdministrador(), panel.getCampoNombre(), panel.getCampoUsuario()));
        JPasswordField campoContrasena = panel.getCampoContrasena();
        campos.add(campoContrasena != null ? new JTextField(new String(campoContrasena.getPassword())) : new JTextField());
        return campos;
    }

    private List<JTextField> getCamposModificarEmparejamiento() {
        PanelRegistrarEmparejamiento panel = ventanaPrincipal.getPanelRegistrarEmparejamiento();
        List<JTextField> campos = new ArrayList<>(Arrays.asList(panel.getCampoIdEmparejamiento(), panel.getCampoIdEquipo1(),
                panel.getCampoIdEquipo2(), panel.getCampoIdTorneo(), panel.getCampoFecha(), panel.getCampoHora(),
                panel.getCampoRonda(), new JTextField((String) panel.getComboEstado().getSelectedItem())));
        return campos;
    }

    private List<JTextField> getCamposModificarTorneo() {
        PanelRegistrarTorneo panel = ventanaPrincipal.getPanelRegistrarTorneo();
        List<JTextField> campos = new ArrayList<>(Arrays.asList(panel.getCampoIdTorneo(), panel.getCampoNombre(),
                panel.getCampoFechaInicio(), panel.getCampoFechaFin(), panel.getCampoReglamento(),
                panel.getCampoTipoJuego()));
        JTextField campoMaxEquipos = new JTextField(String.valueOf(panel.getSpinnerMaxEquipos().getValue()));
        campos.add(campoMaxEquipos);
        return campos;
    }

    private boolean verificarCredenciales(String usuario, String contrasena) {
        return usuario.equals(USUARIO_CORRECTO) && contrasena.equals(CONTRASENA_CORRECTA);
    }

    private void agregarDatosExcel(String filePath, DefaultTableModel model, String sheetName) {
        File archivo = new File(filePath);
        WritableWorkbook libroDeTrabajo = null;
        try {
            WorkbookSettings wbSettings = new WorkbookSettings();
            wbSettings.setEncoding("UTF-8");
            Workbook existingWorkbook = null;

            if (archivo.exists()) {
                try {
                    existingWorkbook = Workbook.getWorkbook(archivo);
                } catch (BiffException e) {
                    System.err.println("Error BiffException al leer el archivo Excel existente: " + e.getMessage());
                    e.printStackTrace();
                    libroDeTrabajo = Workbook.createWorkbook(archivo, wbSettings);
                }
            }
            if (existingWorkbook != null) {
                libroDeTrabajo = Workbook.createWorkbook(archivo, existingWorkbook);
            } else {
                libroDeTrabajo = Workbook.createWorkbook(archivo, wbSettings);
            }

            WritableSheet hoja = null;
            if (libroDeTrabajo.getSheet(sheetName) != null) {
                hoja = libroDeTrabajo.getSheet(sheetName);
            } else {
                hoja = libroDeTrabajo.createSheet(sheetName, libroDeTrabajo.getNumberOfSheets());
            }

            int startRow = hoja.getRows();
            if (!archivo.exists() || libroDeTrabajo.getSheet(sheetName) == null || startRow == 0) {
                for (int j = 0; j < model.getColumnCount(); j++) {
                    Label etiqueta = new Label(j, startRow, model.getColumnName(j));
                    hoja.addCell(etiqueta);
                }
                startRow++;
            }

            for (int i = 0; i < model.getRowCount(); i++) {
                for (int j = 0; j < model.getColumnCount(); j++) {
                    Object cellValue = model.getValueAt(i, j);
                    String cellString = (cellValue != null) ? cellValue.toString() : "";
                    Label etiqueta = new Label(j, startRow + i, cellString);
                    hoja.addCell(etiqueta);
                }
            }

            libroDeTrabajo.write();
        } catch (IOException | WriteException e) {
            System.err.println("Error al escribir en el archivo Excel: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (libroDeTrabajo != null) {
                try {
                    libroDeTrabajo.close();
                } catch (IOException | WriteException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private List<Object[]> obtenerDisponibilidadEquiposDesdeExcel() {
        List<Object[]> disponibilidad = new ArrayList<>();
        File file = new File(ARCHIVOS_PATH + "equipos.xls");

        if (file.exists()) {
            try {
                Workbook existingWorkbook = Workbook.getWorkbook(file);
                Sheet sheet = existingWorkbook.getSheet("Equipos");
                if (sheet != null && sheet.getRows() > 1) {
                    for (int i = 1; i < sheet.getRows(); i++) {
                        Cell idEquipoCell = sheet.getCell(0, i);
                        Cell nombreEquipoCell = sheet.getCell(1, i);
                        Cell estadoCell = sheet.getCell(5, i);
                        if (idEquipoCell != null && nombreEquipoCell != null && estadoCell != null) {
                            disponibilidad.add(new Object[]{
                                idEquipoCell.getContents(),
                                nombreEquipoCell.getContents(),
                                estadoCell.getContents()
                            });
                        }
                    }
                }
                existingWorkbook.close();
            } catch (IOException | BiffException e) {
                System.err.println("Error al leer el archivo de equipos: " + e.getMessage());
                e.printStackTrace();
                JOptionPane.showMessageDialog(ventanaPrincipal, "Error al cargar la disponibilidad de equipos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(ventanaPrincipal, "El archivo equipos.xls no se encontró.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
        return disponibilidad;
    }
}
