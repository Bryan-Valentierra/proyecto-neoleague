package co.edu.unbosque.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PanelRegistrarEmparejamiento extends JPanel {

    private JLabel etiquetaTitulo;
    private JLabel etiquetaIdEmparejamiento;
    private JTextField campoIdEmparejamiento;
    private JLabel etiquetaIdTorneo;
    private JTextField campoIdTorneo;
    private JLabel etiquetaIdEquipo1;
    private JTextField campoIdEquipo1;
    private JLabel etiquetaIdEquipo2;
    private JTextField campoIdEquipo2;
    private JLabel etiquetaFecha;
    private JTextField campoFecha;
    private JLabel etiquetaHora;
    private JTextField campoHora;
    private JLabel etiquetaRonda;
    private JTextField campoRonda;
    private JLabel etiquetaEstado;
    private JComboBox<String> comboEstado;
    private JLabel etiquetaGanador;
    private JTextField campoGanador;
    private JLabel etiquetaPerdedor;
    private JTextField campoPerdedor;
    private JButton botonRegistrar;
    private JButton botonVolver;
    private JButton botonEliminar;
    private JButton botonModificar;
    private JButton botonEmparejamientoRandom;
    private JLabel mensajeError;

    // Constructor del panel para registrar, modificar o eliminar emparejamientos.
    public PanelRegistrarEmparejamiento() {
        setLayout(null);

        etiquetaTitulo = new JLabel("Registrar Emparejamiento");
        etiquetaIdEmparejamiento = new JLabel("ID Emparejamiento:");
        campoIdEmparejamiento = new JTextField(20);
        etiquetaIdTorneo = new JLabel("ID Torneo:");
        campoIdTorneo = new JTextField(20);
        etiquetaIdEquipo1 = new JLabel("ID Equipo 1:");
        campoIdEquipo1 = new JTextField(20);
        etiquetaIdEquipo2 = new JLabel("ID Equipo 2:");
        campoIdEquipo2 = new JTextField(20);
        etiquetaFecha = new JLabel("Fecha (dd/MM/yyyy):");
        campoFecha = new JTextField(20);
        etiquetaHora = new JLabel("Hora (HH:mm):");
        campoHora = new JTextField(20);
        etiquetaRonda = new JLabel("Ronda:");
        campoRonda = new JTextField(20);
        etiquetaEstado = new JLabel("Estado:");
        comboEstado = new JComboBox<>(new String[]{"Pendiente", "En Curso", "Finalizado"});
        etiquetaGanador = new JLabel("Ganador:");
        campoGanador = new JTextField(20);
        etiquetaPerdedor = new JLabel("Perdedor:");
        campoPerdedor = new JTextField(20);
        botonRegistrar = new JButton("Registrar");
        botonVolver = new JButton("Volver");
        botonEliminar = new JButton("Eliminar");
        botonModificar = new JButton("Modificar");
        botonEmparejamientoRandom = new JButton("Emp. Random");
        mensajeError = new JLabel("");
        mensajeError.setForeground(Color.RED);

        etiquetaTitulo.setBounds(10, 10, 200, 25);
        etiquetaIdEmparejamiento.setBounds(10, 40, 120, 25);
        campoIdEmparejamiento.setBounds(140, 40, 120, 25);
        etiquetaIdTorneo.setBounds(10, 70, 80, 25);
        campoIdTorneo.setBounds(100, 70, 160, 25);
        etiquetaIdEquipo1.setBounds(10, 100, 80, 25);
        campoIdEquipo1.setBounds(100, 100, 160, 25);
        etiquetaIdEquipo2.setBounds(10, 130, 80, 25);
        campoIdEquipo2.setBounds(100, 130, 160, 25);
        etiquetaFecha.setBounds(10, 160, 120, 25);
        campoFecha.setBounds(140, 160, 120, 25);
        etiquetaHora.setBounds(10, 190, 80, 25);
        campoHora.setBounds(100, 190, 80, 25);
        etiquetaRonda.setBounds(10, 220, 80, 25);
        campoRonda.setBounds(100, 220, 80, 25);
        etiquetaEstado.setBounds(10, 250, 80, 25);
        comboEstado.setBounds(100, 250, 160, 25);
        etiquetaGanador.setBounds(10, 280, 80, 25);
        campoGanador.setBounds(100, 280, 160, 25);
        etiquetaPerdedor.setBounds(10, 310, 80, 25);
        campoPerdedor.setBounds(100, 310, 160, 25);
        botonRegistrar.setBounds(10, 340, 100, 25);
        botonVolver.setBounds(120, 340, 80, 25);
        botonEliminar.setBounds(210, 340, 100, 25);
        botonModificar.setBounds(320, 340, 100, 25);
        botonEmparejamientoRandom.setBounds(430, 340, 120, 25);
        mensajeError.setBounds(10, 370, 300, 25);

        add(etiquetaTitulo);
        add(etiquetaIdEmparejamiento);
        add(campoIdEmparejamiento);
        add(etiquetaIdTorneo);
        add(campoIdTorneo);
        add(etiquetaIdEquipo1);
        add(campoIdEquipo1);
        add(etiquetaIdEquipo2);
        add(campoIdEquipo2);
        add(etiquetaFecha);
        add(campoFecha);
        add(etiquetaHora);
        add(campoHora);
        add(etiquetaRonda);
        add(campoRonda);
        add(etiquetaEstado);
        add(comboEstado);
        add(etiquetaGanador);
        add(campoGanador);
        add(etiquetaPerdedor);
        add(campoPerdedor);
        add(botonRegistrar);
        add(botonVolver);
        add(botonEliminar);
        add(botonModificar);
        add(botonEmparejamientoRandom);
        add(mensajeError);
    }

    // Devuelve el botón para registrar un nuevo emparejamiento.
    public JButton getBotonRegistrar() {
        return botonRegistrar;
    }

    // Devuelve el botón para volver al menú principal.
    public JButton getBotonVolver() {
        return botonVolver;
    }

    // Devuelve el botón para eliminar un emparejamiento existente.
    public JButton getBotonEliminar() {
        return botonEliminar;
    }

    // Devuelve el botón para modificar un emparejamiento existente.
    public JButton getBotonModificar() {
        return botonModificar;
    }

    // Devuelve el botón para generar un emparejamiento aleatorio.
    public JButton getBotonEmparejamientoRandom() {
        return botonEmparejamientoRandom;
    }

    // Devuelve el campo de texto para el ID del emparejamiento.
    public JTextField getCampoIdEmparejamiento() {
        return campoIdEmparejamiento;
    }

    // Devuelve el campo de texto para el ID del torneo.
    public JTextField getCampoIdTorneo() {
        return campoIdTorneo;
    }

    // Devuelve el campo de texto para el ID del primer equipo.
    public JTextField getCampoIdEquipo1() {
        return campoIdEquipo1;
    }

    // Devuelve el campo de texto para el ID del segundo equipo.
    public JTextField getCampoIdEquipo2() {
        return campoIdEquipo2;
    }

    // Devuelve el campo de texto para la fecha del emparejamiento.
    public JTextField getCampoFecha() {
        return campoFecha;
    }

    // Devuelve el campo de texto para la hora del emparejamiento.
    public JTextField getCampoHora() {
        return campoHora;
    }

    // Devuelve el campo de texto para la ronda del emparejamiento.
    public JTextField getCampoRonda() {
        return campoRonda;
    }

    // Devuelve el JComboBox para el estado del emparejamiento.
    public JComboBox<String> getComboEstado() {
        return comboEstado;
    }

    // Devuelve el campo de texto para el ganador del emparejamiento.
    public JTextField getCampoGanador() {
        return campoGanador;
    }

    // Devuelve el campo de texto para el perdedor del emparejamiento.
    public JTextField getCampoPerdedor() {
        return campoPerdedor;
    }

    // Muestra un mensaje de error en la etiqueta correspondiente.
    public void mostrarMensaje(String mensaje) {
        mensajeError.setText(mensaje);
    }

    // Limpia todos los campos del formulario de registro de emparejamientos.
    public void limpiarFormulario() {
        campoIdEmparejamiento.setText("");
        campoIdTorneo.setText("");
        campoIdEquipo1.setText("");
        campoIdEquipo2.setText("");
        campoFecha.setText("");
        campoHora.setText("");
        campoRonda.setText("");
        comboEstado.setSelectedIndex(0);
        campoGanador.setText("");
        campoPerdedor.setText("");
        mensajeError.setText("");
    }

    // Devuelve el texto ingresado en el campo de ID del emparejamiento.
    public String getIdEmparejamiento() {
        return campoIdEmparejamiento.getText();
    }

    // Devuelve el texto ingresado en el campo de ID del torneo.
    public String getIdTorneo() {
        return campoIdTorneo.getText();
    }

    // Devuelve el texto ingresado en el campo de ID del primer equipo.
    public String getIdEquipo1() {
        return campoIdEquipo1.getText();
    }

    // Devuelve el texto ingresado en el campo de ID del segundo equipo.
    public String getIdEquipo2() {
        return campoIdEquipo2.getText();
    }

    // Devuelve el texto ingresado en el campo de fecha.
    public String getCampoFechaTexto() {
        return campoFecha.getText();
    }

    // Devuelve el texto ingresado en el campo de hora.
    public String getCampoHoraTexto() {
        return campoHora.getText();
    }

    // Devuelve el texto ingresado en el campo de ronda.
    public String getCampoRondaTexto() {
        return campoRonda.getText();
    }

    // Devuelve el estado seleccionado en el JComboBox.
    public String getEstadoSeleccionado() {
        return (String) comboEstado.getSelectedItem();
    }

    // Devuelve el texto ingresado en el campo de ganador.
    public String getGanador() {
        return campoGanador.getText();
    }

    // Devuelve el texto ingresado en el campo de perdedor.
    public String getPerdedor() {
        return campoPerdedor.getText();
    }

    // Devuelve una lista de todos los campos de texto para facilitar la modificación.
    public List<JTextField> getCamposModificarEmparejamiento() {
        List<JTextField> campos = new ArrayList<>();
        campos.add(campoIdEmparejamiento);
        campos.add(campoIdTorneo);
        campos.add(campoIdEquipo1);
        campos.add(campoIdEquipo2);
        campos.add(campoFecha);
        campos.add(campoHora);
        campos.add(campoRonda);
        campos.add(campoGanador);
        campos.add(campoPerdedor);
        return campos;
    }

    // Asigna un ActionListener a cada uno de los botones del panel.
    public void agregarActionListener(ActionListener listener) {
        botonRegistrar.addActionListener(listener);
        botonVolver.addActionListener(listener);
        botonEliminar.addActionListener(listener);
        botonModificar.addActionListener(listener);
        botonEmparejamientoRandom.addActionListener(listener);
    }
}