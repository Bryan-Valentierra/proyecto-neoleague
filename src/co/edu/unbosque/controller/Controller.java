// En tu archivo Controller.java
package co.edu.unbosque.controller;

import co.edu.unbosque.view.*;
import co.edu.unbosque.model.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JTable;

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
    private final String ARCHIVOS_PATH = ".\\src\\co\\edu\\unbosque\\archivos\\";

    private EmparejamientoController emparejamientoController;

    // Constructor: Inicializa el controlador principal, la ventana y otros sub-controladores.
    public Controller(VentanaPrincipal ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
        File directorio = new File(ARCHIVOS_PATH);
        if (!directorio.exists()) {
            if (!directorio.mkdirs()) {
                System.err.println("Error al crear el directorio 'archivos'.");
            }
        }

        if (this.ventanaPrincipal.getPanelRegistrarEmparejamiento() != null) {
            this.emparejamientoController = new EmparejamientoController(
                    this.ventanaPrincipal.getPanelRegistrarEmparejamiento(),
                    this.ventanaPrincipal
            );
        } else {
            System.err.println("CRITICO: PanelRegistrarEmparejamiento es null. EmparejamientoController no inicializado.");
        }

        cargarFechasDeTorneos();
        configurarListeners();
    }

    // Carga fechas de torneos desde Excel al ComboBox de inicio de sesión.
    private void cargarFechasDeTorneos() {
        PanelInicioSesion panel = ventanaPrincipal.getPanelInicioSesion();
        if (panel != null && panel.getComboBoxFechasTorneos() != null) {
            panel.actualizarComboBoxTorneos(obtenerFechasDeTorneosDesdeExcel());
        } else {
             System.err.println("Error: PanelInicioSesion o ComboBoxFechasTorneos es null en cargarFechasDeTorneos.");
        }
    }

    // Lee fechas de inicio y fin de torneos desde el archivo 'torneos.xls'.
    private List<String> obtenerFechasDeTorneosDesdeExcel() {
        List<String> fechas = new ArrayList<>();
        File file = new File(ARCHIVOS_PATH + "torneos.xls");
        Workbook wb = null;
        if (file.exists()) {
            try {
                wb = Workbook.getWorkbook(file);
                Sheet sheet = wb.getSheet("Torneos"); 
                if (sheet != null && sheet.getRows() > 1) {
                    for (int i = 1; i < sheet.getRows(); i++) { 
                        Cell fIni = sheet.getCell(2, i); 
                        Cell fFin = sheet.getCell(3, i);
                        if (fIni != null && fFin != null && !fIni.getContents().trim().isEmpty() && !fFin.getContents().trim().isEmpty()) {
                            fechas.add(fIni.getContents() + " - " + fFin.getContents());
                        }
                    }
                }
            } catch (IOException | BiffException e) {
                System.err.println("Error leyendo fechas de torneos desde Excel: " + e.getMessage());
                e.printStackTrace();
            } finally {
                if (wb != null) wb.close();
            }
        }
        return fechas;
    }

    // Configura todos los ActionListeners para los botones de la aplicación.
    private void configurarListeners() {
        // PanelInicioSesion
        PanelInicioSesion panelInicioSesion = ventanaPrincipal.getPanelInicioSesion();
        if (panelInicioSesion != null && panelInicioSesion.getBotonIniciarSesion() != null) {
            panelInicioSesion.getBotonIniciarSesion().addActionListener(e -> {
                String u = panelInicioSesion.getCampoUsuario().getText();
                char[] pArr = panelInicioSesion.getCampoContrasena().getPassword();
                String p = new String(pArr);
                if (verificarCredenciales(u, p)) { 
                    ventanaPrincipal.mostrarPanelPrincipal();
                    panelInicioSesion.limpiarFormulario();
                } else {
                    panelInicioSesion.mostrarMensaje("Credenciales incorrectas.");
                }
                Arrays.fill(pArr, ' '); 
            });
        }

        // PanelPrincipal
        PanelPrincipal panelPrincipal = ventanaPrincipal.getPanelPrincipal();
        if (panelPrincipal != null) {
            if(panelPrincipal.getBotonRegistrar()!=null) panelPrincipal.getBotonRegistrar().addActionListener(e -> ventanaPrincipal.mostrarPanelRegistro());
            if(panelPrincipal.getBotonAnalisis()!=null) panelPrincipal.getBotonAnalisis().addActionListener(e -> ventanaPrincipal.mostrarPanelAnalisis());
            if(panelPrincipal.getBotonTorneosActivos()!=null) panelPrincipal.getBotonTorneosActivos().addActionListener(e -> cargarYMostrarTorneosActivos());
            if(panelPrincipal.getBotonResultados()!=null) panelPrincipal.getBotonResultados().addActionListener(e -> cargarYMostrarResultadosEmparejamientos());
            if(panelPrincipal.getBotonDisponibilidadEquipos()!=null) panelPrincipal.getBotonDisponibilidadEquipos().addActionListener(e -> {
                PanelDisponibilidadEquipos pDisp = ventanaPrincipal.getPanelDisponibilidadEquipos();
                if(pDisp != null) pDisp.actualizarTabla(obtenerDisponibilidadEquiposDesdeExcel()); 
                ventanaPrincipal.mostrarPanelDisponibilidadEquipos();
            });
            // <<--- AÑADIR LISTENER PARA CERRAR SESIÓN ---INI--- >>
            if(panelPrincipal.getBotonCerrarSesion() != null) {
                panelPrincipal.getBotonCerrarSesion().addActionListener(e -> {
                    // Simplemente mostramos el panel de inicio de sesión
                    ventanaPrincipal.mostrarPanelInicioSesion();
                    // Opcional: Limpiar algún estado de sesión si lo tuvieras
                    System.out.println("Sesión cerrada. Mostrando panel de inicio.");
                });
            }
            // <<--- AÑADIR LISTENER PARA CERRAR SESIÓN ---FIN--- >>
        }

        // PanelRegistro
        PanelRegistro panelRegistro = ventanaPrincipal.getPanelRegistro();
        if (panelRegistro != null) {
            if(panelRegistro.getBotonRegistrarJugador()!=null) panelRegistro.getBotonRegistrarJugador().addActionListener(e -> ventanaPrincipal.mostrarPanelRegistroJugador());
            if(panelRegistro.getBotonRegistrarEntrenador()!=null) panelRegistro.getBotonRegistrarEntrenador().addActionListener(e -> ventanaPrincipal.mostrarPanelRegistroEntrenador());
            if(panelRegistro.getBotonRegistrarEquipo()!=null) panelRegistro.getBotonRegistrarEquipo().addActionListener(e -> ventanaPrincipal.mostrarPanelRegistroEquipo());
            if(panelRegistro.getBotonRegistrarAdministrador()!=null) panelRegistro.getBotonRegistrarAdministrador().addActionListener(e -> ventanaPrincipal.mostrarPanelRegistroAdministrador());
            if(panelRegistro.getBotonRegistrarEmparejamiento()!=null) panelRegistro.getBotonRegistrarEmparejamiento().addActionListener(e -> ventanaPrincipal.mostrarPanelRegistrarEmparejamiento());
            if(panelRegistro.getBotonRegistrarTorneo()!=null) panelRegistro.getBotonRegistrarTorneo().addActionListener(e -> ventanaPrincipal.mostrarPanelRegistrarTorneo());
            if(panelRegistro.getBotonVolver()!=null) panelRegistro.getBotonVolver().addActionListener(e -> ventanaPrincipal.mostrarPanelPrincipal());
        }

        // PanelRegistroJugador
        PanelRegistroJugador panelRegJugador = ventanaPrincipal.getPanelRegistroJugador();
        if (panelRegJugador != null) {
            if(panelRegJugador.getBotonRegistrar()!=null) panelRegJugador.getBotonRegistrar().addActionListener(e -> registrarJugador(panelRegJugador));
            if(panelRegJugador.getBotonVolver()!=null) panelRegJugador.getBotonVolver().addActionListener(e -> ventanaPrincipal.mostrarPanelRegistro());
            if(panelRegJugador.getBotonEliminar()!=null) panelRegJugador.getBotonEliminar().addActionListener(e -> eliminarEntidadPorNombre("jugadores.xls", "Jugadores", 1, "jugador"));
            if(panelRegJugador.getBotonModificar()!=null) panelRegJugador.getBotonModificar().addActionListener(e -> modificarEntidadPorNombre("jugadores.xls", "Jugadores", 1, "jugador", panelRegJugador.getCamposModificarJugador()));
        }

        // PanelRegistroEntrenador
        PanelRegistroEntrenador panelRegEntrenador = ventanaPrincipal.getPanelRegistroEntrenador();
        if (panelRegEntrenador != null) {
            if(panelRegEntrenador.getBotonRegistrarEntrenador()!=null) panelRegEntrenador.getBotonRegistrarEntrenador().addActionListener(e -> registrarEntrenador(panelRegEntrenador));
            if(panelRegEntrenador.getBotonVolver()!=null) panelRegEntrenador.getBotonVolver().addActionListener(e -> ventanaPrincipal.mostrarPanelRegistro());
            if(panelRegEntrenador.getBotonEliminarEntrenador()!=null) panelRegEntrenador.getBotonEliminarEntrenador().addActionListener(e -> eliminarEntidadPorNombre("entrenadores.xls", "Entrenadores", 1, "entrenador"));
            if(panelRegEntrenador.getBotonModificarEntrenador()!=null) panelRegEntrenador.getBotonModificarEntrenador().addActionListener(e -> modificarEntidadPorNombre("entrenadores.xls", "Entrenadores", 1, "entrenador", panelRegEntrenador.getCamposModificarEntrenador()));
        }

        // PanelRegistroEquipo
        PanelRegistroEquipo panelRegEquipo = ventanaPrincipal.getPanelRegistroEquipo();
        if (panelRegEquipo != null) {
            JButton btnRegEquipo = panelRegEquipo.getBotonRegistrarEquipo() != null ? panelRegEquipo.getBotonRegistrarEquipo() : panelRegEquipo.getBotonRegistrar();
            if(btnRegEquipo !=null) btnRegEquipo.addActionListener(e -> registrarEquipo(panelRegEquipo));
            
            if(panelRegEquipo.getBotonVolver()!=null) panelRegEquipo.getBotonVolver().addActionListener(e -> ventanaPrincipal.mostrarPanelRegistro());
            if(panelRegEquipo.getBotonEliminar()!=null) panelRegEquipo.getBotonEliminar().addActionListener(e -> eliminarEntidadPorNombre("equipos.xls", "Equipos", 1, "equipo"));
            if(panelRegEquipo.getBotonModificar()!=null) panelRegEquipo.getBotonModificar().addActionListener(e -> modificarEntidadPorNombre("equipos.xls", "Equipos", 1, "equipo", panelRegEquipo.getCamposModificarEquipo()));
        }

        // PanelRegistroAdministrador
        PanelRegistroAdministrador panelRegAdmin = ventanaPrincipal.getPanelRegistroAdministrador();
        if (panelRegAdmin != null) {
            if(panelRegAdmin.getBotonRegistrar()!=null) panelRegAdmin.getBotonRegistrar().addActionListener(e -> registrarAdministrador(panelRegAdmin));
            if(panelRegAdmin.getBotonVolver()!=null) panelRegAdmin.getBotonVolver().addActionListener(e -> ventanaPrincipal.mostrarPanelRegistro());
            if(panelRegAdmin.getBotonEliminar()!=null) panelRegAdmin.getBotonEliminar().addActionListener(e -> eliminarEntidadPorNombre("administradores.xls", "Administradores", 1, "administrador"));
            if(panelRegAdmin.getBotonModificar()!=null) panelRegAdmin.getBotonModificar().addActionListener(e -> modificarEntidadPorNombre("administradores.xls", "Administradores", 1, "administrador", panelRegAdmin.getCamposModificarAdministrador()));
        }
        
        // PanelRegistrarTorneo
        PanelRegistrarTorneo panelRegTorneo = ventanaPrincipal.getPanelRegistrarTorneo();
        if (panelRegTorneo != null) {
            if(panelRegTorneo.getBotonRegistrar()!=null) panelRegTorneo.getBotonRegistrar().addActionListener(e -> registrarTorneo(panelRegTorneo));
            if(panelRegTorneo.getBotonVolver()!=null) panelRegTorneo.getBotonVolver().addActionListener(e -> ventanaPrincipal.mostrarPanelRegistro());
            if(panelRegTorneo.getBotonModificar()!=null) panelRegTorneo.getBotonModificar().addActionListener(e -> modificarEntidadPorNombre("torneos.xls", "Torneos", 0, "torneo", panelRegTorneo.getCamposModificarTorneo()));
            if(panelRegTorneo.getBotonEliminar()!=null) panelRegTorneo.getBotonEliminar().addActionListener(e -> eliminarEntidadPorNombre("torneos.xls", "Torneos", 0, "torneo"));
        }

        // PanelAnalisis
        PanelAnalisis panelAnalisis = ventanaPrincipal.getPanelAnalisis();
        if (panelAnalisis != null) {
            if(panelAnalisis.getBotonVolver()!=null) panelAnalisis.getBotonVolver().addActionListener(e -> ventanaPrincipal.mostrarPanelPrincipal());
            if(panelAnalisis.getBotonMostrarRanking()!=null) panelAnalisis.getBotonMostrarRanking().addActionListener(e -> obtenerRanking());
        }

        // PanelTorneosActivos
        PanelTorneosActivos panelTorneosActivos = ventanaPrincipal.getPanelTorneosActivos();
        if (panelTorneosActivos != null) {
            if(panelTorneosActivos.getBotonVolver() != null) {
                panelTorneosActivos.getBotonVolver().addActionListener(e -> ventanaPrincipal.mostrarPanelPrincipal());
            }
            if(panelTorneosActivos.getBotonVerDetalles() != null && panelTorneosActivos.getTablaTorneos() != null) {
                panelTorneosActivos.getBotonVerDetalles().addActionListener(e -> {
                    int filaSeleccionada = panelTorneosActivos.getTablaTorneos().getSelectedRow();
                    if (filaSeleccionada != -1) {
                        String idTorneoSeleccionado = (String) panelTorneosActivos.getTablaTorneos().getValueAt(filaSeleccionada, 1); 
                        mostrarDetallesDeTorneo(idTorneoSeleccionado); 
                    } else {
                        JOptionPane.showMessageDialog(ventanaPrincipal, "Seleccione un torneo para ver detalles.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    }
                });
            }
             if(panelTorneosActivos.getBotonModificar() != null) {
                panelTorneosActivos.getBotonModificar().addActionListener(e -> {
                    int filaSeleccionada = panelTorneosActivos.getTablaTorneos().getSelectedRow();
                    if (filaSeleccionada != -1) {
                        String idTorneo = (String) panelTorneosActivos.getTablaTorneos().getValueAt(filaSeleccionada, 1); 
                         cargarDatosTorneoEnPanelRegistro(idTorneo);
                         ventanaPrincipal.mostrarPanelRegistrarTorneo();
                    } else {
                        JOptionPane.showMessageDialog(ventanaPrincipal, "Seleccione un torneo para modificar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    }
                });
            }
            if(panelTorneosActivos.getBotonEliminar() != null) { 
                panelTorneosActivos.getBotonEliminar().addActionListener(e -> {
                    int filaSeleccionada = panelTorneosActivos.getTablaTorneos().getSelectedRow();
                    if (filaSeleccionada != -1) {
                        String idTorneo = (String) panelTorneosActivos.getTablaTorneos().getValueAt(filaSeleccionada, 1); 
                        int confirm = JOptionPane.showConfirmDialog(ventanaPrincipal,
                                "¿Seguro que desea eliminar el torneo con ID: " + idTorneo + "?",
                                "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
                        if (confirm == JOptionPane.YES_OPTION) {
                            eliminarEntidadPorNombre("torneos.xls", "Torneos", 0, "torneo", idTorneo); 
                            cargarYMostrarTorneosActivos(); 
                        }
                    } else {
                        JOptionPane.showMessageDialog(ventanaPrincipal, "Seleccione un torneo para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    }
                });
            }
        }

        // PanelResultados
        PanelResultados panelResultados = ventanaPrincipal.getPanelResultados();
        if (panelResultados != null) {
            if(panelResultados.getBotonVolver()!=null) {
                panelResultados.getBotonVolver().addActionListener(e -> ventanaPrincipal.mostrarPanelPrincipal());
            }
            if(panelResultados.getBotonExportarResultados()!=null) { 
                panelResultados.getBotonExportarResultados().addActionListener(e -> exportarResultadosAExcel());
            }
        }

        // PanelDisponibilidadEquipos
        PanelDisponibilidadEquipos panelDispEquipos = ventanaPrincipal.getPanelDisponibilidadEquipos();
        if (panelDispEquipos != null && panelDispEquipos.getBotonVolver()!=null) {
            panelDispEquipos.getBotonVolver().addActionListener(e -> ventanaPrincipal.mostrarPanelPrincipal());
        }
    }

    // Verifica las credenciales del usuario.
    private boolean verificarCredenciales(String usuario, String contrasena) {
        return USUARIO_CORRECTO.equals(usuario) && CONTRASENA_CORRECTA.equals(contrasena);
    }
    
    // Carga datos de un torneo en PanelRegistrarTorneo para edición.
    private void cargarDatosTorneoEnPanelRegistro(String idTorneo) {
        PanelRegistrarTorneo panelRegTorneo = ventanaPrincipal.getPanelRegistrarTorneo();
        if (panelRegTorneo == null) { System.err.println("PanelRegistrarTorneo es null."); return; }
        File file = new File(ARCHIVOS_PATH + "torneos.xls");
        Workbook wb = null;
        if (file.exists()) {
            try {
                wb = Workbook.getWorkbook(file);
                Sheet sheet = wb.getSheet("Torneos");
                if (sheet != null && sheet.getRows() > 1) {
                    for (int i = 1; i < sheet.getRows(); i++) {
                        if (sheet.getCell(0, i).getContents().equals(idTorneo)) { 
                            panelRegTorneo.getCampoIdTorneo().setText(sheet.getCell(0, i).getContents());
                            panelRegTorneo.getCampoNombre().setText(sheet.getCell(1, i).getContents());
                            panelRegTorneo.getCampoFechaInicio().setText(sheet.getCell(2, i).getContents());
                            panelRegTorneo.getCampoFechaFin().setText(sheet.getCell(3, i).getContents());
                            panelRegTorneo.getCampoReglamento().setText(sheet.getCell(4, i).getContents());
                            panelRegTorneo.getCampoTipoJuego().setText(sheet.getCell(5, i).getContents());
                            try {
                                panelRegTorneo.getSpinnerMaxEquipos().setValue(Integer.parseInt(sheet.getCell(6, i).getContents()));
                            } catch (NumberFormatException ex) { panelRegTorneo.getSpinnerMaxEquipos().setValue(2); } 
                            panelRegTorneo.getCampoIdTorneo().setEditable(false); 
                            return;
                        }
                    }
                }
            } catch (Exception e) { System.err.println("Error cargando datos torneo: " + e.getMessage()); e.printStackTrace(); } 
            finally { if (wb != null) wb.close(); }
        }
    }

    // Carga y muestra la lista de torneos en PanelTorneosActivos.
    private void cargarYMostrarTorneosActivos() {
        PanelTorneosActivos panelTA = ventanaPrincipal.getPanelTorneosActivos();
        if (panelTA == null) { System.err.println("PanelTorneosActivos es null."); return; }

        List<Object[]> datosParaTabla = new ArrayList<>();
        File file = new File(ARCHIVOS_PATH + "torneos.xls");
        Workbook wb = null;
        if (file.exists()) {
            try {
                wb = Workbook.getWorkbook(file);
                Sheet sheet = wb.getSheet("Torneos"); 
                if (sheet != null && sheet.getRows() > 1) {
                    for (int i = 1; i < sheet.getRows(); i++) { 
                        Cell idCell = sheet.getCell(0, i); 
                        Cell nombreCell = sheet.getCell(1, i); 
                        if (idCell != null && nombreCell != null &&
                            !idCell.getContents().trim().isEmpty() &&
                            !nombreCell.getContents().trim().isEmpty()) {
                            datosParaTabla.add(new Object[]{nombreCell.getContents(), idCell.getContents()});
                        }
                    }
                }
            } catch (IOException | BiffException e) { System.err.println("Error leyendo torneos: " + e.getMessage()); e.printStackTrace(); } 
            finally { if (wb != null) wb.close(); }
        } else { JOptionPane.showMessageDialog(ventanaPrincipal, "'torneos.xls' no encontrado.", "Advertencia", JOptionPane.WARNING_MESSAGE); }
        
        panelTA.actualizarTabla(datosParaTabla); 
        ventanaPrincipal.mostrarPanelTorneosActivos(); 
    }

    // Muestra los detalles completos de un torneo seleccionado.
    private void mostrarDetallesDeTorneo(String idTorneoSeleccionado) {
        File file = new File(ARCHIVOS_PATH + "torneos.xls");
        Workbook wb = null;
        if (file.exists()) {
            try {
                wb = Workbook.getWorkbook(file);
                Sheet sheet = wb.getSheet("Torneos");
                if (sheet != null && sheet.getRows() > 1) {
                    for (int i = 1; i < sheet.getRows(); i++) { 
                        Cell idCell = sheet.getCell(0, i); 
                        if (idCell != null && idCell.getContents().equals(idTorneoSeleccionado)) {
                            String nombre = sheet.getCell(1, i).getContents();       
                            String fechaIni = sheet.getCell(2, i).getContents();     
                            String fechaFin = sheet.getCell(3, i).getContents();     
                            String reglamento = sheet.getCell(4, i).getContents(); 
                            String tipoJuego = sheet.getCell(5, i).getContents();  
                            String maxEquipos = sheet.getCell(6, i).getContents(); 

                            StringBuilder detalles = new StringBuilder("Detalles del Torneo:\n\n");
                            detalles.append("ID Torneo: ").append(idTorneoSeleccionado).append("\n");
                            detalles.append("Nombre: ").append(nombre).append("\n");
                            detalles.append("Fecha Inicio: ").append(fechaIni).append("\n");
                            detalles.append("Fecha Fin: ").append(fechaFin).append("\n");
                            detalles.append("Reglamento: ").append(reglamento).append("\n");
                            detalles.append("Tipo de Juego: ").append(tipoJuego).append("\n");
                            detalles.append("Máx. Equipos: ").append(maxEquipos).append("\n");

                            JOptionPane.showMessageDialog(ventanaPrincipal, detalles.toString(), "Detalles - " + nombre, JOptionPane.INFORMATION_MESSAGE);
                            return; 
                        }
                    }
                    JOptionPane.showMessageDialog(ventanaPrincipal, "No hay detalles para ID: " + idTorneoSeleccionado, "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (IOException | BiffException e) { System.err.println("Error leyendo detalles torneo: " + e.getMessage()); e.printStackTrace(); } 
            finally { if (wb != null) wb.close(); }
        } else { JOptionPane.showMessageDialog(ventanaPrincipal, "'torneos.xls' no encontrado.", "Advertencia", JOptionPane.WARNING_MESSAGE); }
    }

    // Carga y muestra los resultados de emparejamientos en PanelResultados.
    private void cargarYMostrarResultadosEmparejamientos() {
        PanelResultados panelRes = ventanaPrincipal.getPanelResultados();
        if (panelRes == null) { System.err.println("PanelResultados es null."); return; }

        List<Object[]> datosParaTabla = new ArrayList<>();
        File file = new File(ARCHIVOS_PATH + "emparejamientos.xls"); 
        Workbook wb = null;
        if (file.exists()) {
            try {
                wb = Workbook.getWorkbook(file);
                Sheet sheet = wb.getSheet("Emparejamientos"); 
                if (sheet != null && sheet.getRows() > 1) { 
                    int numColumnasExcel = sheet.getColumns();
                    int columnasAMostrar = 11; 

                    for (int i = 1; i < sheet.getRows(); i++) { 
                        Object[] fila = new Object[columnasAMostrar];
                        for(int j=0; j < columnasAMostrar; j++){ 
                            if (j < numColumnasExcel) { 
                                Cell cell = sheet.getCell(j, i);
                                fila[j] = (cell != null) ? cell.getContents() : "";
                            } else {
                                fila[j] = ""; 
                            }
                        }
                        datosParaTabla.add(fila);
                    }
                } else {
                    System.out.println("Hoja 'Emparejamientos' vacía o no encontrada en emparejamientos.xls");
                }
            } catch (IOException | BiffException e) {
                System.err.println("Error al leer emparejamientos.xls para PanelResultados: " + e.getMessage());
                e.printStackTrace();
                JOptionPane.showMessageDialog(ventanaPrincipal, "Error al cargar resultados de emparejamientos.", "Error de Archivo", JOptionPane.ERROR_MESSAGE);
            } finally {
                if (wb != null) wb.close();
            }
        } else {
            JOptionPane.showMessageDialog(ventanaPrincipal, "Archivo 'emparejamientos.xls' no encontrado.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
        
        panelRes.actualizarTablaResultados(datosParaTabla); 
        ventanaPrincipal.mostrarPanelResultados(); 
    }
    
    // Exporta los resultados (emparejamientos) actualmente mostrados en PanelResultados a un nuevo archivo Excel.
    private void exportarResultadosAExcel() {
        PanelResultados panelRes = ventanaPrincipal.getPanelResultados();
        if (panelRes == null || panelRes.getTablaResultados() == null) {JOptionPane.showMessageDialog(ventanaPrincipal, "No hay tabla de resultados para exportar.", "Error", JOptionPane.ERROR_MESSAGE); return; }
        JTable tabla = panelRes.getTablaResultados();
        if (tabla.getRowCount() == 0) {JOptionPane.showMessageDialog(ventanaPrincipal, "No hay datos para exportar.", "Información", JOptionPane.INFORMATION_MESSAGE); return; }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar Resultados Como");
        fileChooser.setSelectedFile(new File("Resultados_Exportados.xls")); 
        if (fileChooser.showSaveDialog(ventanaPrincipal) == JFileChooser.APPROVE_OPTION) {
            File archivoParaGuardar = fileChooser.getSelectedFile();
            if (!archivoParaGuardar.getAbsolutePath().toLowerCase().endsWith(".xls")) {
                archivoParaGuardar = new File(archivoParaGuardar.getAbsolutePath() + ".xls");
            }
            WritableWorkbook workbook = null;
            try {
                workbook = Workbook.createWorkbook(archivoParaGuardar);
                WritableSheet sheet = workbook.createSheet("ResultadosEmparejamientos", 0);
                DefaultTableModel model = (DefaultTableModel) tabla.getModel();
                for (int i = 0; i < model.getColumnCount(); i++) { sheet.addCell(new Label(i, 0, model.getColumnName(i))); }
                for (int i = 0; i < model.getRowCount(); i++) {
                    for (int j = 0; j < model.getColumnCount(); j++) {
                        Object valorCelda = model.getValueAt(i, j);
                        sheet.addCell(new Label(j, i + 1, valorCelda != null ? valorCelda.toString() : ""));
                    }
                }
                workbook.write();
                JOptionPane.showMessageDialog(ventanaPrincipal, "Resultados exportados a:\n" + archivoParaGuardar.getAbsolutePath(), "Exportación Exitosa", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException | WriteException ex) { ex.printStackTrace(); JOptionPane.showMessageDialog(ventanaPrincipal, "Error al exportar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); } 
            finally {
                if (workbook != null) { try { workbook.close(); } catch (Exception ex) { ex.printStackTrace(); } }
            }
        }
    }

    // Registra un nuevo jugador.
    private void registrarJugador(PanelRegistroJugador panel) {
        if (panel == null) { System.err.println("PanelRegistroJugador es null."); return; }
        String id = panel.getCampoId().getText(); String n = panel.getCampoNombre().getText(); String a = panel.getCampoApellido().getText();
        if (id.isEmpty() || n.isEmpty() || a.isEmpty()) { panel.mostrarMensaje("ID, Nombre y Apellido son obligatorios."); return; }
        DefaultTableModel m = new DefaultTableModel();
        m.addColumn("ID"); m.addColumn("Nombre"); m.addColumn("Apellido"); m.addColumn("FechaNacimiento"); m.addColumn("Nacionalidad"); m.addColumn("Correo");
        m.addColumn("JuegosParticipados"); m.addColumn("Victorias"); m.addColumn("Derrotas"); m.addColumn("Record"); m.addColumn("JuegoFavorito"); m.addColumn("RolPrincipal");
        m.addColumn("Estadisticas"); m.addColumn("HistorialCompetitivo"); m.addColumn("AnalisisJuegos");
        m.addRow(new Object[]{id,n,a,panel.getCampoFechaNacimiento().getText(),panel.getCampoNacionalidad().getText(),panel.getCampoCorreo().getText(),
            panel.getCampoJuegosParticipados().getText(),panel.getCampoVictorias().getText(),panel.getCampoDerrotas().getText(),panel.getCampoRecord().getText(),
            panel.getCampoJuegoFavorito().getText(),panel.getCampoRolPrincipal().getText(),panel.getCampoEstadisticas().getText(),
            panel.getCampoHistorialCompetitivo().getText(),panel.getCampoAnalisisJuegos().getText()});
        agregarDatosExcel(ARCHIVOS_PATH + "jugadores.xls", m, "Jugadores");
        panel.mostrarMensaje("Jugador registrado."); panel.limpiarFormulario();
    }

    // Registra un nuevo entrenador.
    private void registrarEntrenador(PanelRegistroEntrenador panel) {
        if (panel == null) { System.err.println("PanelRegistroEntrenador es null."); return; }
        String id = panel.getCampoId().getText(); String n = panel.getCampoNombre().getText();
        if (id.isEmpty() || n.isEmpty()) { panel.mostrarMensaje("ID y Nombre obligatorios."); return; }
        DefaultTableModel m = new DefaultTableModel();
        m.addColumn("ID"); m.addColumn("Nombre"); m.addColumn("Apellido"); m.addColumn("FechaNacimiento"); m.addColumn("Nacionalidad"); m.addColumn("Correo");
        m.addColumn("Experiencia"); m.addColumn("EquipoAsignado");
        m.addRow(new Object[]{id,n,panel.getCampoApellido().getText(),panel.getCampoFechaNacimiento().getText(),panel.getCampoNacionalidad().getText(),
            panel.getCampoCorreo().getText(),panel.getCampoExperiencia().getText(),panel.getCampoEquipoAsignado().getText()});
        agregarDatosExcel(ARCHIVOS_PATH + "entrenadores.xls", m, "Entrenadores");
        panel.mostrarMensaje("Entrenador registrado."); panel.limpiarFormulario();
    }

    // Registra un nuevo equipo.
    private void registrarEquipo(PanelRegistroEquipo panel) {
        if (panel == null) { System.err.println("PanelRegistroEquipo es null."); return; }
        String id = panel.getCampoIdEquipo().getText(); String n = panel.getCampoNombreEquipo().getText();
        if (id.isEmpty() || n.isEmpty()) { panel.mostrarMensaje("ID y Nombre de Equipo obligatorios."); return; }
        DefaultTableModel m = new DefaultTableModel();
        m.addColumn("IDEquipo");m.addColumn("NombreEquipo");m.addColumn("FechaCreacion");m.addColumn("NumeroJugadores");m.addColumn("Entrenador");
        m.addColumn("JuegoPrincipal");m.addColumn("Patrocinador");m.addColumn("SedeEntrenamiento");
        m.addRow(new Object[]{id,n,panel.getCampoFechaCreacion().getText(),panel.getSpinnerNumeroJugadores().getValue().toString(),
            panel.getCampoEntrenador().getText(),panel.getCampoJuegoPrincipal().getText(),panel.getCampoPatrocinador().getText(),panel.getCampoSedeEntrenamiento().getText()});
        agregarDatosExcel(ARCHIVOS_PATH + "equipos.xls", m, "Equipos");
        panel.mostrarMensaje("Equipo registrado."); panel.limpiarFormulario();
    }

    // Registra un nuevo administrador.
    private void registrarAdministrador(PanelRegistroAdministrador panel) {
        if (panel == null) { System.err.println("PanelRegistroAdministrador es null."); return; }
        String id = panel.getCampoIdAdministrador().getText(); String n = panel.getCampoNombre().getText(); String u = panel.getCampoUsuario().getText(); String p = new String(panel.getCampoContrasena().getPassword());
        if (id.isEmpty()||n.isEmpty()||u.isEmpty()||p.isEmpty()) { panel.mostrarMensaje("Todos los campos obligatorios."); return; }
        DefaultTableModel m = new DefaultTableModel();
        m.addColumn("IDAdmin");m.addColumn("Nombre");m.addColumn("Usuario");m.addColumn("Contrasena");
        m.addRow(new Object[]{id,n,u,p});
        agregarDatosExcel(ARCHIVOS_PATH + "administradores.xls", m, "Administradores");
        panel.mostrarMensaje("Administrador registrado."); panel.limpiarFormulario();
    }

    // Registra un nuevo torneo.
    private void registrarTorneo(PanelRegistrarTorneo panel) {
        if (panel == null) { System.err.println("PanelRegistrarTorneo es null."); return; }
        String id = panel.getCampoIdTorneo().getText(); String n = panel.getCampoNombre().getText();
        if (id.isEmpty() || n.isEmpty()) { panel.mostrarMensaje("ID y Nombre de Torneo obligatorios."); return; }
        
        DefaultTableModel m = new DefaultTableModel();
        m.addColumn("ID Torneo");m.addColumn("Nombre Torneo");m.addColumn("Fecha Inicio");m.addColumn("Fecha Fin");
        m.addColumn("Reglamento");m.addColumn("Tipo Juego");m.addColumn("Max Equipos"); 
        m.addRow(new Object[]{id,n,panel.getCampoFechaInicio().getText(),panel.getCampoFechaFin().getText(),panel.getCampoReglamento().getText(),
            panel.getCampoTipoJuego().getText(),panel.getSpinnerMaxEquipos().getValue().toString()});
        agregarDatosExcel(ARCHIVOS_PATH + "torneos.xls", m, "Torneos");
        panel.mostrarMensaje("Torneo registrado."); panel.limpiarFormulario(); panel.getCampoIdTorneo().setEditable(true); 
        cargarFechasDeTorneos();
    }
    
    // Agrega datos de un DefaultTableModel a un archivo Excel.
    private void agregarDatosExcel(String filePath, DefaultTableModel model, String sheetName) {
        File archivo = new File(filePath); WritableWorkbook libro = null; Workbook existente = null;
        try {
            WorkbookSettings ws = new WorkbookSettings(); ws.setEncoding("UTF-8"); 
            if (archivo.exists()) { existente = Workbook.getWorkbook(archivo); libro = Workbook.createWorkbook(archivo, existente, ws); }
            else { libro = Workbook.createWorkbook(archivo, ws); }
            WritableSheet hoja = libro.getSheet(sheetName); boolean hojaNueva = false;
            if (hoja == null) { hoja = libro.createSheet(sheetName, libro.getNumberOfSheets()); hojaNueva = true; }
            int filaInicio = hoja.getRows(); 
            if ((hojaNueva || filaInicio == 0) && model.getColumnCount() > 0) {
                for (int j=0; j<model.getColumnCount(); j++) { hoja.addCell(new Label(j,0,model.getColumnName(j)));}
                filaInicio = (filaInicio == 0) ? 1 : filaInicio;
            }
            for (int i=0; i<model.getRowCount(); i++) {
                for (int j=0; j<model.getColumnCount(); j++) {
                    Object valObj = model.getValueAt(i,j);
                    String val = valObj != null ? valObj.toString() : "";
                    hoja.addCell(new Label(j, filaInicio+i, val));
                }
            }
            libro.write(); 
        } catch (Exception e) { System.err.println("Err escribiendo Excel ("+filePath+"): "+e.getMessage()); e.printStackTrace(); } 
        finally {
            try { if (libro!=null) libro.close(); if (existente!=null) existente.close(); }
            catch (Exception e) { System.err.println("Err cerrando Excel (agregar): "+e.getMessage());}
        }
    }

    // Obtiene la disponibilidad de equipos desde 'equipos.xls'.
    private List<Object[]> obtenerDisponibilidadEquiposDesdeExcel() {
        List<Object[]> disp = new ArrayList<>(); File f = new File(ARCHIVOS_PATH + "equipos.xls"); Workbook wb = null;
        if (f.exists()) {
            try {
                wb = Workbook.getWorkbook(f); Sheet s = wb.getSheet("Equipos");
                if (s!=null && s.getRows()>1) {
                    for (int i=1; i<s.getRows(); i++) {
                        Cell cId=s.getCell(0,i); Cell cNom=s.getCell(1,i); Cell cEst=s.getCell(5,i); 
                        if (cId!=null&&cNom!=null&&cEst!=null&&!cId.getContents().trim().isEmpty()&&!cNom.getContents().trim().isEmpty()) {
                            disp.add(new Object[]{cId.getContents(),cNom.getContents(),cEst.getContents()});
                        }
                    }
                }
            } catch (Exception e) { System.err.println("Err leyendo disponibilidad: "+e.getMessage()); e.printStackTrace(); } 
            finally { if (wb!=null) wb.close(); }
        }
        return disp;
    }

    // Obtiene y muestra el ranking de equipos/jugadores en PanelAnalisis.
    private void obtenerRanking() {
        DefaultTableModel m = new DefaultTableModel(); m.addColumn("Equipo"); m.addColumn("Victorias"); 
        File fEmp = new File(ARCHIVOS_PATH + "emparejamientos.xls"); Workbook wb = null;
        if (fEmp.exists()) {
            try {
                wb = Workbook.getWorkbook(fEmp); Sheet s = wb.getSheet("Emparejamientos"); 
                if (s!=null && s.getRows()>1) {
                    Map<String,Integer> vict = new java.util.HashMap<>();
                    for (int i=1; i<s.getRows(); i++) {
                        Cell cGan = s.getCell(9,i); 
                        if (cGan!=null && !cGan.getContents().trim().isEmpty()) {
                            String g = cGan.getContents().trim();
                            if (!g.equalsIgnoreCase("N/A") && !g.equals("-")) { vict.put(g, vict.getOrDefault(g,0)+1); }
                        }
                    }
                    List<Map.Entry<String,Integer>> rank = new ArrayList<>(vict.entrySet());
                    rank.sort((e1,e2) -> e2.getValue().compareTo(e1.getValue())); 
                    for (Map.Entry<String,Integer> entry : rank) { m.addRow(new Object[]{entry.getKey(),entry.getValue()}); }
                } else { m.addRow(new Object[]{"No hay datos emparej.",0}); }
            } catch (Exception e) { m.addRow(new Object[]{"Error al cargar rank.",0}); System.err.println("Err obteniendo ranking: "+e.getMessage()); e.printStackTrace(); } 
            finally { if (wb!=null) wb.close(); }
        } else { m.addRow(new Object[]{"emparejamientos.xls no encontrado",0}); }
        PanelAnalisis pAna = ventanaPrincipal.getPanelAnalisis();
        if (pAna!=null && pAna.getTablaAnalisis()!=null) { pAna.getTablaAnalisis().setModel(m); }
    }
    
    // Elimina entidad (usado por botones de eliminar y por el PanelTorneosActivos).
    // Sobrecarga que pide input al usuario.
    private void eliminarEntidadPorNombre(String archivoNombre, String hojaNombre, int columnaBusquedaEnExcel, String tipoEntidad) {
        String valorBusqueda = JOptionPane.showInputDialog(ventanaPrincipal, "Ingrese el valor de '" + tipoEntidad.toUpperCase() + "' (columna "+(columnaBusquedaEnExcel+1)+") a eliminar:");
        if (valorBusqueda == null || valorBusqueda.trim().isEmpty()) { 
            JOptionPane.showMessageDialog(ventanaPrincipal, "Operación cancelada o valor no ingresado.", "Información", JOptionPane.INFORMATION_MESSAGE);
            return; 
        }
        eliminarEntidadPorNombre(archivoNombre, hojaNombre, columnaBusquedaEnExcel, tipoEntidad, valorBusqueda);
    }

    // Elimina una entidad del archivo Excel dado un valor de búsqueda.
    private void eliminarEntidadPorNombre(String archivoNombre, String hojaNombre, int columnaBusquedaEnExcel, String tipoEntidad, String valorBusqueda) {
        File archivo=new File(ARCHIVOS_PATH+archivoNombre); File temp=new File(ARCHIVOS_PATH+"temp_del_"+archivoNombre); 
        WritableWorkbook wkb=null; Workbook rWkb=null; boolean found=false;
        if (!archivo.exists()) { JOptionPane.showMessageDialog(ventanaPrincipal, "Archivo "+archivoNombre+" no existe.","Error",JOptionPane.ERROR_MESSAGE); return; }
        try {
            rWkb=Workbook.getWorkbook(archivo); wkb=Workbook.createWorkbook(temp,rWkb);
            WritableSheet sheet=wkb.getSheet(hojaNombre);
            if (sheet==null) { JOptionPane.showMessageDialog(ventanaPrincipal,"Hoja '"+hojaNombre+"' no existe.","Error",JOptionPane.ERROR_MESSAGE); if(wkb!=null)wkb.close(); if(rWkb!=null)rWkb.close(); temp.delete(); return; }
            for (int i=sheet.getRows()-1; i>=0; i--) {
                if (i==0 && sheet.getCell(columnaBusquedaEnExcel,i)!=null) { // No borrar cabecera
                     if(sheet.getRows() > 1) continue; 
                }
                Cell cell=sheet.getCell(columnaBusquedaEnExcel,i);
                if (cell!=null && cell.getContents().equalsIgnoreCase(valorBusqueda.trim())) { sheet.removeRow(i); found=true; break; }
            }
            wkb.write();
        } catch (Exception e) { System.err.println("Err eliminando Excel ("+archivoNombre+"): "+e.getMessage()); e.printStackTrace(); try{if(wkb!=null)wkb.close();if(rWkb!=null)rWkb.close();temp.delete();}catch(Exception ex){System.err.println("Err cerrando Excel (eliminar-error): "+ex.getMessage());} return; }
        finally { try{if(wkb!=null)wkb.close();if(rWkb!=null)rWkb.close();}catch(Exception e){System.err.println("Err cerrando Excel (eliminar-finally): "+e.getMessage());}}
        if(found){
            if(archivo.delete()){ if(!temp.renameTo(archivo)){ JOptionPane.showMessageDialog(ventanaPrincipal, "Error renombrando archivo temporal.", "Error", JOptionPane.ERROR_MESSAGE); } else {JOptionPane.showMessageDialog(ventanaPrincipal,tipoEntidad+" '"+valorBusqueda+"' eliminado."); if(archivoNombre.equals("torneos.xls"))cargarFechasDeTorneos();}}
            else{JOptionPane.showMessageDialog(ventanaPrincipal, "Error borrando archivo original.", "Error", JOptionPane.ERROR_MESSAGE);}
        } else { JOptionPane.showMessageDialog(ventanaPrincipal,"No se encontró "+tipoEntidad+" con valor '"+valorBusqueda+"'."); temp.delete(); }
    }
    
    // Modifica una entidad existente en el archivo Excel.
    private void modificarEntidadPorNombre(String archivoNombre, String hojaNombre, int columnaBusquedaEnExcel, String tipoEntidad, List<JTextField> camposNuevosValores) {
        PanelRegistrarTorneo panelRegTorneo = tipoEntidad.equals("torneo") ? ventanaPrincipal.getPanelRegistrarTorneo() : null; 
        String valorBusqueda;

        if (tipoEntidad.equals("torneo") && panelRegTorneo != null && !panelRegTorneo.getCampoIdTorneo().isEditable()) { 
            valorBusqueda = panelRegTorneo.getCampoIdTorneo().getText(); 
        } else { 
            // Para las entidades que no son torneo, o si el ID del torneo es editable, se pide el valor de búsqueda.
            // La columna de búsqueda (columnaBusquedaEnExcel) es importante aquí.
            // Por ejemplo, si para "jugador" la búsqueda es por nombre (col 1), pero para "torneo" es por ID (col 0).
            String mensajeInput = "Ingrese valor de '" + tipoEntidad.toUpperCase() + "' (en columna " + (columnaBusquedaEnExcel + 1) + ") a modificar:";
            valorBusqueda = JOptionPane.showInputDialog(ventanaPrincipal, mensajeInput);
        }

        if (valorBusqueda==null||valorBusqueda.trim().isEmpty()) { 
            if(panelRegTorneo!=null && tipoEntidad.equals("torneo"))panelRegTorneo.getCampoIdTorneo().setEditable(true); 
            JOptionPane.showMessageDialog(ventanaPrincipal, "Operación cancelada o valor no ingresado.", "Información", JOptionPane.INFORMATION_MESSAGE);
            return; 
        }
        File archivo=new File(ARCHIVOS_PATH+archivoNombre); File temp=new File(ARCHIVOS_PATH+"temp_mod_"+archivoNombre);
        WritableWorkbook wkb=null; Workbook rWkb=null; boolean found=false;

        if(!archivo.exists()){JOptionPane.showMessageDialog(ventanaPrincipal,"Archivo "+archivoNombre+" no existe.","Error",JOptionPane.ERROR_MESSAGE); if(panelRegTorneo!=null && tipoEntidad.equals("torneo"))panelRegTorneo.getCampoIdTorneo().setEditable(true); return;} 
        if(camposNuevosValores==null||camposNuevosValores.isEmpty()){JOptionPane.showMessageDialog(ventanaPrincipal,"No hay datos nuevos para modificar.","Error",JOptionPane.ERROR_MESSAGE); if(panelRegTorneo!=null && tipoEntidad.equals("torneo"))panelRegTorneo.getCampoIdTorneo().setEditable(true); return;}
        
        try {
            rWkb=Workbook.getWorkbook(archivo); wkb=Workbook.createWorkbook(temp,rWkb);
            WritableSheet sheet=wkb.getSheet(hojaNombre);
            if(sheet==null){JOptionPane.showMessageDialog(ventanaPrincipal,"Hoja '"+hojaNombre+"' no existe.","Error",JOptionPane.ERROR_MESSAGE); if(wkb!=null)wkb.close(); if(rWkb!=null)rWkb.close(); temp.delete(); if(panelRegTorneo!=null && tipoEntidad.equals("torneo"))panelRegTorneo.getCampoIdTorneo().setEditable(true); return;}
            
            int numColsExcel=sheet.getColumns();
            // Es crucial que la lista camposNuevosValores tenga el mismo número de JTextFields que columnas hay en el Excel.
            if (camposNuevosValores.size() != numColsExcel) {
                 System.err.println("ALERTA CRÍTICA: Mismatch columnas! Excel: " + numColsExcel + ", Campos GUI: " + camposNuevosValores.size() + " para " + tipoEntidad);
                 JOptionPane.showMessageDialog(ventanaPrincipal, "Error interno: Discrepancia en número de campos. No se puede modificar.", "Error Crítico", JOptionPane.ERROR_MESSAGE);
                 if(panelRegTorneo!=null && tipoEntidad.equals("torneo"))panelRegTorneo.getCampoIdTorneo().setEditable(true);
                 try{if(wkb!=null)wkb.close();if(rWkb!=null)rWkb.close();temp.delete();}catch(Exception ex){System.err.println("Err cerrando Excel (modificar-alert): "+ex.getMessage());}
                 return;
            }

            for (int i=1; i<sheet.getRows(); i++) { 
                Cell cell=sheet.getCell(columnaBusquedaEnExcel,i);
                if (cell!=null && cell.getContents().equalsIgnoreCase(valorBusqueda.trim())) {
                    for(int col=0; col < camposNuevosValores.size(); col++) { 
                        if(col < numColsExcel) { 
                             sheet.addCell(new Label(col,i,camposNuevosValores.get(col).getText()));
                        }
                    }
                    found=true; break;
                }
            }
            wkb.write();
        } catch (Exception e) { 
            if(panelRegTorneo!=null && tipoEntidad.equals("torneo"))panelRegTorneo.getCampoIdTorneo().setEditable(true); 
            System.err.println("Err mod Excel ("+archivoNombre+"): "+e.getMessage()); 
            e.printStackTrace(); 
            JOptionPane.showMessageDialog(ventanaPrincipal, "Error al procesar el archivo para modificar.", "Error Excel", JOptionPane.ERROR_MESSAGE);
            try{if(wkb!=null)wkb.close();if(rWkb!=null)rWkb.close();temp.delete();}catch(Exception ex){System.err.println("Err cerrando Excel (modificar-error): "+ex.getMessage());}
            return; 
        } finally { 
            try{if(wkb!=null)wkb.close();if(rWkb!=null)rWkb.close();}catch(Exception e){System.err.println("Err cerrando Excel (modificar-finally): "+e.getMessage());}
        }
        if(found){
            if(archivo.delete()){ 
                if(!temp.renameTo(archivo)){ JOptionPane.showMessageDialog(ventanaPrincipal, "Error renombrando archivo temporal.", "Error", JOptionPane.ERROR_MESSAGE); } 
                else { 
                    JOptionPane.showMessageDialog(ventanaPrincipal,tipoEntidad+" '"+valorBusqueda+"' modificado con éxito."); 
                    if(archivoNombre.equals("torneos.xls"))cargarFechasDeTorneos(); 
                    if(panelRegTorneo!=null && tipoEntidad.equals("torneo")){panelRegTorneo.limpiarFormulario();panelRegTorneo.getCampoIdTorneo().setEditable(true);}
                }
            } else{ JOptionPane.showMessageDialog(ventanaPrincipal, "Error borrando archivo original.", "Error", JOptionPane.ERROR_MESSAGE); }
        } else { 
            JOptionPane.showMessageDialog(ventanaPrincipal,"No se encontró "+tipoEntidad+" con valor '"+valorBusqueda+"' para modificar."); 
            temp.delete(); 
            if(panelRegTorneo!=null && tipoEntidad.equals("torneo"))panelRegTorneo.getCampoIdTorneo().setEditable(true);
        }
    }
}