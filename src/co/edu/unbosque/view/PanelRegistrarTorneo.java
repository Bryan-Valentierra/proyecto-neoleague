package co.edu.unbosque.view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PanelRegistrarTorneo extends JPanel {

    private JLabel etiquetaTitulo;
    private JLabel etiquetaIdTorneo; // Nuevo
    private JTextField campoIdTorneo; // Nuevo
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

    public PanelRegistrarTorneo() {
        setLayout(null);

        etiquetaTitulo = new JLabel("Registrar Torneo");
        etiquetaIdTorneo = new JLabel("ID Torneo:"); // Nuevo
        campoIdTorneo = new JTextField(20); // Nuevo
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
        etiquetaIdTorneo.setBounds(200, 60, 100, 20); // Nuevo
        campoIdTorneo.setBounds(310, 60, 200, 25); // Nuevo
        etiquetaNombre.setBounds(200, 100, 100, 20); // Movido hacia abajo
        campoNombre.setBounds(310, 100, 200, 25); // Movido hacia abajo
        etiquetaFechaInicio.setBounds(200, 140, 100, 20); // Movido hacia abajo
        campoFechaInicio.setBounds(310, 140, 200, 25); // Movido hacia abajo
        etiquetaFechaFin.setBounds(200, 180, 100, 20); // Movido hacia abajo
        campoFechaFin.setBounds(310, 180, 200, 25); // Movido hacia abajo
        etiquetaReglamento.setBounds(200, 220, 100, 20); // Movido hacia abajo
        campoReglamento.setBounds(310, 220, 200, 25); // Movido hacia abajo
        etiquetaTipoJuego.setBounds(200, 260, 100, 20); // Movido hacia abajo
        campoTipoJuego.setBounds(310, 260, 200, 25); // Movido hacia abajo
        etiquetaMaxEquipos.setBounds(200, 300, 100, 20); // Movido hacia abajo
        spinnerMaxEquipos.setBounds(310, 300, 80, 25); // Movido hacia abajo
        botonRegistrar.setBounds(250, 340, 100, 30);
        botonVolver.setBounds(400, 340, 100, 30);
        botonModificar.setBounds(250, 380, 100, 30);
        botonEliminar.setBounds(400, 380, 100, 30);
        mensajeError.setBounds(250, 420, 300, 20);

        add(etiquetaTitulo);
        add(etiquetaIdTorneo); // Nuevo
        add(campoIdTorneo); // Nuevo
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

    public JTextField getCampoIdTorneo() { // Nuevo
        return campoIdTorneo;
    }

    public JTextField getCampoNombre() {
        return campoNombre;
    }

    public JTextField getCampoFechaInicio() {
        return campoFechaInicio;
    }

    public JTextField getCampoFechaFin() {
        return campoFechaFin;
    }

    public JTextField getCampoReglamento() {
        return campoReglamento;
    }

    public JTextField getCampoTipoJuego() {
        return campoTipoJuego;
    }

    public JSpinner getSpinnerMaxEquipos() {
        return spinnerMaxEquipos;
    }

    public JButton getBotonRegistrar() {
        return botonRegistrar;
    }

    public JButton getBotonVolver() {
        return botonVolver;
    }

    public JButton getBotonModificar() {
        return botonModificar;
    }

    public JButton getBotonEliminar() {
        return botonEliminar;
    }

    public void limpiarFormulario() {
        campoIdTorneo.setText(""); // Nuevo
        campoNombre.setText("");
        campoFechaInicio.setText("");
        campoFechaFin.setText("");
        campoReglamento.setText("");
        campoTipoJuego.setText("");
        spinnerMaxEquipos.setValue(2);
        mensajeError.setText("");
    }

    public void mostrarMensaje(String mensaje) {
        mensajeError.setText(mensaje);
    }

    public List<JTextField> getCamposModificarTorneo() {
        List<JTextField> campos = new ArrayList<>();
        campos.add(campoIdTorneo);
        campos.add(campoNombre);
        campos.add(campoFechaInicio);
        campos.add(campoFechaFin);
        campos.add(campoReglamento);
        campos.add(campoTipoJuego);
        campos.add(new JTextField(spinnerMaxEquipos.getValue().toString())); // Convertir valor del spinner a JTextField
        return campos;
    }
}