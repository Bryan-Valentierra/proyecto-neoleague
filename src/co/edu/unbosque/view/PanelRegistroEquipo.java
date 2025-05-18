package co.edu.unbosque.view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PanelRegistroEquipo extends JPanel {

    private JLabel labelTitulo;
    private JLabel labelIdEquipo;
    private JTextField campoIdEquipo;
    private JLabel labelNombreEquipo;
    private JTextField campoNombreEquipo;
    private JLabel labelFechaCreacion;
    private JTextField campoFechaCreacion;
    private JLabel labelNumeroJugadores;
    private JSpinner spinnerNumeroJugadores;
    private JLabel labelEntrenador;
    private JTextField campoEntrenador;
    private JLabel labelJuegoPrincipal;
    private JTextField campoJuegoPrincipal;
    private JLabel labelPatrocinador;
    private JTextField campoPatrocinador;
    private JLabel labelSedeEntrenamiento;
    private JTextField campoSedeEntrenamiento;

    private JButton botonRegistrar;
    private JButton botonModificar;
    private JButton botonEliminar;
    private JButton botonVolver;

    private JLabel mensajeLabel;

    // Constructor del panel para registrar, modificar o eliminar equipos.
    public PanelRegistroEquipo() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 1.0;

        labelTitulo = new JLabel("Registro de Equipo");
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(labelTitulo, gbc);

        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;

        labelIdEquipo = new JLabel("ID Equipo:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(labelIdEquipo, gbc);
        campoIdEquipo = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(campoIdEquipo, gbc);

        labelNombreEquipo = new JLabel("Nombre del Equipo:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(labelNombreEquipo, gbc);
        campoNombreEquipo = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(campoNombreEquipo, gbc);

        labelFechaCreacion = new JLabel("Fecha de Creación (YYYY-MM-DD):");
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(labelFechaCreacion, gbc);
        campoFechaCreacion = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(campoFechaCreacion, gbc);

        labelNumeroJugadores = new JLabel("Número de Jugadores:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(labelNumeroJugadores, gbc);
        spinnerNumeroJugadores = new JSpinner(new SpinnerNumberModel(1, 1, 30, 1));
        gbc.gridx = 1;
        gbc.gridy = 4;
        add(spinnerNumeroJugadores, gbc);

        labelEntrenador = new JLabel("Entrenador:");
        gbc.gridx = 0;
        gbc.gridy = 5;
        add(labelEntrenador, gbc);
        campoEntrenador = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 5;
        add(campoEntrenador, gbc);

        labelJuegoPrincipal = new JLabel("Juego Principal:");
        gbc.gridx = 0;
        gbc.gridy = 6;
        add(labelJuegoPrincipal, gbc);
        campoJuegoPrincipal = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 6;
        add(campoJuegoPrincipal, gbc);

        labelPatrocinador = new JLabel("Patrocinador:");
        gbc.gridx = 0;
        gbc.gridy = 7;
        add(labelPatrocinador, gbc);
        campoPatrocinador = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 7;
        add(campoPatrocinador, gbc);

        labelSedeEntrenamiento = new JLabel("Sede de Entrenamiento:");
        gbc.gridx = 0;
        gbc.gridy = 8;
        add(labelSedeEntrenamiento, gbc);
        campoSedeEntrenamiento = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 8;
        add(campoSedeEntrenamiento, gbc);

        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        botonRegistrar = new JButton("Registrar Equipo");
        add(botonRegistrar, gbc);

        gbc.gridy = 10;
        botonModificar = new JButton("Modificar Equipo");
        add(botonModificar, gbc);

        gbc.gridy = 11;
        botonEliminar = new JButton("Eliminar Equipo");
        add(botonEliminar, gbc);

        gbc.gridy = 12;
        botonVolver = new JButton("Volver a Registro");
        add(botonVolver, gbc);

        mensajeLabel = new JLabel("");
        mensajeLabel.setForeground(Color.RED);
        gbc.gridy = 13;
        add(mensajeLabel, gbc);
    }

    // Devuelve el botón para registrar un equipo.
    public JButton getBotonRegistrarEquipo() {
        return botonRegistrar;
    }

    // Devuelve el campo de texto para el ID del equipo.
    public JTextField getCampoIdEquipo() {
        return campoIdEquipo;
    }

    // Devuelve el campo de texto para el nombre del equipo.
    public JTextField getCampoNombreEquipo() {
        return campoNombreEquipo;
    }

    // Devuelve el campo de texto para la fecha de creación del equipo.
    public JTextField getCampoFechaCreacion() {
        return campoFechaCreacion;
    }

    // Devuelve el spinner para el número de jugadores del equipo.
    public JSpinner getSpinnerNumeroJugadores() {
        return spinnerNumeroJugadores;
    }

    // Devuelve el campo de texto para el entrenador del equipo.
    public JTextField getCampoEntrenador() {
        return campoEntrenador;
    }

    // Devuelve el campo de texto para el juego principal del equipo.
    public JTextField getCampoJuegoPrincipal() {
        return campoJuegoPrincipal;
    }

    // Devuelve el campo de texto para el patrocinador del equipo.
    public JTextField getCampoPatrocinador() {
        return campoPatrocinador;
    }

    // Devuelve el campo de texto para la sede de entrenamiento del equipo.
    public JTextField getCampoSedeEntrenamiento() {
        return campoSedeEntrenamiento;
    }

    // Devuelve el botón para registrar el equipo.
    public JButton getBotonRegistrar() {
        return botonRegistrar;
    }

    // Devuelve el botón para modificar el equipo.
    public JButton getBotonModificar() {
        return botonModificar;
    }

    // Devuelve el botón para eliminar el equipo.
    public JButton getBotonEliminar() {
        return botonEliminar;
    }

    // Devuelve el botón para volver a la pantalla de registro.
    public JButton getBotonVolver() {
        return botonVolver;
    }

    // Muestra un mensaje en la etiqueta de mensajes.
    public void mostrarMensaje(String mensaje) {
        mensajeLabel.setText(mensaje);
    }

    // Limpia los campos del formulario de registro de equipo.
    public void limpiarFormulario() {
        campoIdEquipo.setText("");
        campoNombreEquipo.setText("");
        campoFechaCreacion.setText("");
        spinnerNumeroJugadores.setValue(1);
        campoEntrenador.setText("");
        campoJuegoPrincipal.setText("");
        campoPatrocinador.setText("");
        campoSedeEntrenamiento.setText("");
        mensajeLabel.setText("");
    }

    // Devuelve una lista de los campos de texto para modificar un equipo.
    public List<JTextField> getCamposModificarEquipo() {
        List<JTextField> campos = new ArrayList<>();
        campos.add(campoIdEquipo);
        campos.add(campoNombreEquipo);
        campos.add(campoFechaCreacion);
        campos.add(new JTextField(spinnerNumeroJugadores.getValue().toString()));
        campos.add(campoEntrenador);
        campos.add(campoJuegoPrincipal);
        campos.add(campoPatrocinador);
        campos.add(campoSedeEntrenamiento);
        return campos;
    }
}