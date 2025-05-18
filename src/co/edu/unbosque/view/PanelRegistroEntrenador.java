package co.edu.unbosque.view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PanelRegistroEntrenador extends JPanel {

    private JLabel labelTitulo;
    private JLabel labelId;
    private JTextField campoId;
    private JLabel labelNombre;
    private JTextField campoNombre;
    private JLabel labelApellido;
    private JTextField campoApellido;
    private JLabel labelFechaNacimiento;
    private JTextField campoFechaNacimiento;
    private JLabel labelNacionalidad;
    private JTextField campoNacionalidad;
    private JLabel labelCorreo;
    private JTextField campoCorreo;
    private JLabel labelExperiencia;
    private JTextField campoExperiencia;
    private JLabel labelEquipoAsignado;
    private JTextField campoEquipoAsignado;
    private JButton botonRegistrarEntrenador;
    private JButton botonVolver;
    private JButton botonEliminarEntrenador;
    private JButton botonModificarEntrenador;
    private JLabel mensajeLabel;

    // Constructor del panel para registrar, modificar o eliminar entrenadores.
    public PanelRegistroEntrenador() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        labelTitulo = new JLabel("Registro de Entrenador");
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;
        add(labelTitulo, gbc);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridwidth = 1;
        gbc.weightx = 0.0;

        labelId = new JLabel("ID:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(labelId, gbc);
        campoId = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        add(campoId, gbc);
        gbc.weightx = 0.0;

        labelNombre = new JLabel("Nombre:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(labelNombre, gbc);
        campoNombre = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        add(campoNombre, gbc);
        gbc.weightx = 0.0;

        labelApellido = new JLabel("Apellido:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(labelApellido, gbc);
        campoApellido = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        add(campoApellido, gbc);
        gbc.weightx = 0.0;

        labelFechaNacimiento = new JLabel("Fecha de Nacimiento:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(labelFechaNacimiento, gbc);
        campoFechaNacimiento = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.weightx = 1.0;
        add(campoFechaNacimiento, gbc);
        gbc.weightx = 0.0;

        labelNacionalidad = new JLabel("Nacionalidad:");
        gbc.gridx = 0;
        gbc.gridy = 5;
        add(labelNacionalidad, gbc);
        campoNacionalidad = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.weightx = 1.0;
        add(campoNacionalidad, gbc);
        gbc.weightx = 0.0;

        labelCorreo = new JLabel("Correo Electrónico:");
        gbc.gridx = 0;
        gbc.gridy = 6;
        add(labelCorreo, gbc);
        campoCorreo = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.weightx = 1.0;
        add(campoCorreo, gbc);
        gbc.weightx = 0.0;

        labelExperiencia = new JLabel("Experiencia:");
        gbc.gridx = 0;
        gbc.gridy = 7;
        add(labelExperiencia, gbc);
        campoExperiencia = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.weightx = 1.0;
        add(campoExperiencia, gbc);
        gbc.weightx = 0.0;

        labelEquipoAsignado = new JLabel("Equipo Asignado:");
        gbc.gridx = 0;
        gbc.gridy = 8;
        add(labelEquipoAsignado, gbc);
        campoEquipoAsignado = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 8;
        gbc.weightx = 1.0;
        add(campoEquipoAsignado, gbc);
        gbc.weightx = 0.0;

        botonRegistrarEntrenador = new JButton("Registrar Entrenador");
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 2;
        gbc.weighty = 0.0;
        gbc.anchor = GridBagConstraints.CENTER;
        add(botonRegistrarEntrenador, gbc);

        botonVolver = new JButton("Volver");
        gbc.gridy = 10;
        gbc.weighty = 0.0;
        add(botonVolver, gbc);

        botonEliminarEntrenador = new JButton("Eliminar Entrenador");
        gbc.gridy = 11;
        gbc.weighty = 0.0;
        add(botonEliminarEntrenador, gbc);

        botonModificarEntrenador = new JButton("Modificar Entrenador");
        gbc.gridy = 12;
        gbc.weighty = 0.0;
        add(botonModificarEntrenador, gbc);

        mensajeLabel = new JLabel("");
        mensajeLabel.setForeground(Color.RED);
        gbc.gridy = 13;
        gbc.gridwidth = 2;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.SOUTH;
        add(mensajeLabel, gbc);
    }

    // Devuelve el campo de texto para el ID del entrenador.
    public JTextField getCampoId() {
        return campoId;
    }

    // Devuelve el campo de texto para el nombre del entrenador.
    public JTextField getCampoNombre() {
        return campoNombre;
    }

    // Devuelve el campo de texto para el apellido del entrenador.
    public JTextField getCampoApellido() {
        return campoApellido;
    }

    // Devuelve el campo de texto para la fecha de nacimiento del entrenador.
    public JTextField getCampoFechaNacimiento() {
        return campoFechaNacimiento;
    }

    // Devuelve el campo de texto para la nacionalidad del entrenador.
    public JTextField getCampoNacionalidad() {
        return campoNacionalidad;
    }

    // Devuelve el campo de texto para el correo electrónico del entrenador.
    public JTextField getCampoCorreo() {
        return campoCorreo;
    }

    // Devuelve el campo de texto para la experiencia del entrenador.
    public JTextField getCampoExperiencia() {
        return campoExperiencia;
    }

    // Devuelve el campo de texto para el equipo asignado al entrenador.
    public JTextField getCampoEquipoAsignado() {
        return campoEquipoAsignado;
    }

    // Devuelve el botón para registrar un nuevo entrenador.
    public JButton getBotonRegistrarEntrenador() {
        return botonRegistrarEntrenador;
    }

    // Devuelve el botón para volver a la pantalla anterior.
    public JButton getBotonVolver() {
        return botonVolver;
    }

    // Devuelve el botón para eliminar un entrenador existente.
    public JButton getBotonEliminarEntrenador() {
        return botonEliminarEntrenador;
    }

    // Devuelve el botón para modificar un entrenador existente.
    public JButton getBotonModificarEntrenador() {
        return botonModificarEntrenador;
    }

    // Muestra un mensaje en la etiqueta de mensajes.
    public void mostrarMensaje(String mensaje) {
        mensajeLabel.setText(mensaje);
    }

    // Limpia los campos del formulario de registro de entrenador.
    public void limpiarFormulario() {
        campoId.setText("");
        campoNombre.setText("");
        campoApellido.setText("");
        campoFechaNacimiento.setText("");
        campoNacionalidad.setText("");
        campoCorreo.setText("");
        campoExperiencia.setText("");
        campoEquipoAsignado.setText("");
    }

    // Devuelve una lista de los campos de texto para modificar un entrenador.
    public List<JTextField> getCamposModificarEntrenador() {
        List<JTextField> campos = new ArrayList<>();
        campos.add(campoId);
        campos.add(campoNombre);
        campos.add(campoApellido);
        campos.add(campoFechaNacimiento);
        campos.add(campoNacionalidad);
        campos.add(campoCorreo);
        campos.add(campoExperiencia);
        campos.add(campoEquipoAsignado);
        return campos;
    }
}