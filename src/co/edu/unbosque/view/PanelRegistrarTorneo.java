package co.edu.unbosque.view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PanelRegistrarTorneo extends JPanel {

    private JLabel etiquetaTitulo;
    private JLabel etiquetaIdTorneo;
    private JTextField campoIdTorneo;
    private JLabel etiquetaNombre;
    private JTextField campoNombre;
    private JLabel etiquetaFechaInicio;
    private JTextField campoFechaInicio;
    private JLabel etiquetaFechaFin;
    private JTextField campoFechaFin;
    private JLabel etiquetaReglamento;
    private JTextField campoReglamento;
    private JLabel etiquetaTipoJuego;
    private JTextField campoTipoJuego;
    private JLabel etiquetaMaxEquipos;
    private JSpinner spinnerMaxEquipos;
    private JButton botonRegistrar;
    private JButton botonVolver;
    private JButton botonModificar;
    private JButton botonEliminar;
    private JLabel mensajeError;

    // Constructor del panel para registrar, modificar o eliminar torneos.
    public PanelRegistrarTorneo() {
        setLayout(null);

        etiquetaTitulo = new JLabel("Registrar Torneo");
        etiquetaIdTorneo = new JLabel("ID Torneo:");
        campoIdTorneo = new JTextField(20);
        etiquetaNombre = new JLabel("Nombre:");
        campoNombre = new JTextField(20);
        etiquetaFechaInicio = new JLabel("Fecha Inicio:");
        campoFechaInicio = new JTextField(20);
        etiquetaFechaFin = new JLabel("Fecha Fin:");
        campoFechaFin = new JTextField(20);
        etiquetaReglamento = new JLabel("Reglamento:");
        campoReglamento = new JTextField(20);
        etiquetaTipoJuego = new JLabel("Tipo de Juego:");
        campoTipoJuego = new JTextField(20);
        etiquetaMaxEquipos = new JLabel("Max. Equipos:");
        spinnerMaxEquipos = new JSpinner(new SpinnerNumberModel(2, 2, 100, 1));
        botonRegistrar = new JButton("Registrar");
        botonVolver = new JButton("Volver");
        botonModificar = new JButton("Modificar");
        botonEliminar = new JButton("Eliminar");
        mensajeError = new JLabel("");
        mensajeError.setForeground(Color.RED);

        etiquetaTitulo.setBounds(300, 20, 150, 20);
        etiquetaIdTorneo.setBounds(200, 60, 100, 20);
        campoIdTorneo.setBounds(310, 60, 200, 25);
        etiquetaNombre.setBounds(200, 100, 100, 20);
        campoNombre.setBounds(310, 100, 200, 25);
        etiquetaFechaInicio.setBounds(200, 140, 100, 20);
        campoFechaInicio.setBounds(310, 140, 200, 25);
        etiquetaFechaFin.setBounds(200, 180, 100, 20);
        campoFechaFin.setBounds(310, 180, 200, 25);
        etiquetaReglamento.setBounds(200, 220, 100, 20);
        campoReglamento.setBounds(310, 220, 200, 25);
        etiquetaTipoJuego.setBounds(200, 260, 100, 20);
        campoTipoJuego.setBounds(310, 260, 200, 25);
        etiquetaMaxEquipos.setBounds(200, 300, 100, 20);
        spinnerMaxEquipos.setBounds(310, 300, 80, 25);
        botonRegistrar.setBounds(250, 340, 100, 30);
        botonVolver.setBounds(400, 340, 100, 30);
        botonModificar.setBounds(250, 380, 100, 30);
        botonEliminar.setBounds(400, 380, 100, 30);
        mensajeError.setBounds(250, 420, 300, 20);

        add(etiquetaTitulo);
        add(etiquetaIdTorneo);
        add(campoIdTorneo);
        add(etiquetaNombre);
        add(campoNombre);
        add(etiquetaFechaInicio);
        add(campoFechaInicio);
        add(etiquetaFechaFin);
        add(campoFechaFin);
        add(etiquetaReglamento);
        add(campoReglamento);
        add(etiquetaTipoJuego);
        add(campoTipoJuego);
        add(etiquetaMaxEquipos);
        add(spinnerMaxEquipos);
        add(botonRegistrar);
        add(botonVolver);
        add(botonModificar);
        add(botonEliminar);
        add(mensajeError);
    }

    // Devuelve el campo de texto para el ID del torneo.
    public JTextField getCampoIdTorneo() {
        return campoIdTorneo;
    }

    // Devuelve el campo de texto para el nombre del torneo.
    public JTextField getCampoNombre() {
        return campoNombre;
    }

    // Devuelve el campo de texto para la fecha de inicio del torneo.
    public JTextField getCampoFechaInicio() {
        return campoFechaInicio;
    }

    // Devuelve el campo de texto para la fecha de fin del torneo.
    public JTextField getCampoFechaFin() {
        return campoFechaFin;
    }

    // Devuelve el campo de texto para el reglamento del torneo.
    public JTextField getCampoReglamento() {
        return campoReglamento;
    }

    // Devuelve el campo de texto para el tipo de juego del torneo.
    public JTextField getCampoTipoJuego() {
        return campoTipoJuego;
    }

    // Devuelve el JSpinner para la cantidad máxima de equipos del torneo.
    public JSpinner getSpinnerMaxEquipos() {
        return spinnerMaxEquipos;
    }

    // Devuelve el botón para registrar un nuevo torneo.
    public JButton getBotonRegistrar() {
        return botonRegistrar;
    }

    // Devuelve el botón para volver al menú principal.
    public JButton getBotonVolver() {
        return botonVolver;
    }

    // Devuelve el botón para modificar un torneo existente.
    public JButton getBotonModificar() {
        return botonModificar;
    }

    // Devuelve el botón para eliminar un torneo existente.
    public JButton getBotonEliminar() {
        return botonEliminar;
    }

    // Limpia todos los campos del formulario de registro de torneos.
    public void limpiarFormulario() {
        campoIdTorneo.setText("");
        campoNombre.setText("");
        campoFechaInicio.setText("");
        campoFechaFin.setText("");
        campoReglamento.setText("");
        campoTipoJuego.setText("");
        spinnerMaxEquipos.setValue(2);
        mensajeError.setText("");
    }

    // Muestra un mensaje de error en la etiqueta correspondiente.
    public void mostrarMensaje(String mensaje) {
        mensajeError.setText(mensaje);
    }

    // Devuelve una lista de todos los campos de texto para facilitar la modificación.
    public List<JTextField> getCamposModificarTorneo() {
        List<JTextField> campos = new ArrayList<>();
        campos.add(campoIdTorneo);
        campos.add(campoNombre);
        campos.add(campoFechaInicio);
        campos.add(campoFechaFin);
        campos.add(campoReglamento);
        campos.add(campoTipoJuego);
        campos.add(new JTextField(spinnerMaxEquipos.getValue().toString()));
        return campos;
    }
}