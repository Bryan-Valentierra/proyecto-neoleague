package co.edu.unbosque.view;

import javax.swing.*;
import java.awt.*;

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
    private JLabel mensajeLabel;

    public PanelRegistroEntrenador() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Espacio entre componentes
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST; // Alineación por defecto

        // Título
        labelTitulo = new JLabel("Registro de Entrenador");
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Ocupa dos columnas
        gbc.weightx = 1.0; // Se expande horizontalmente
        gbc.anchor = GridBagConstraints.CENTER;
        add(labelTitulo, gbc);
        gbc.anchor = GridBagConstraints.WEST; // Restablecer la alineación

        gbc.gridwidth = 1; // Volver a una columna
        gbc.weightx = 0.0; // No expandir por defecto

        // ID
        labelId = new JLabel("ID:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(labelId, gbc);
        campoId = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0; // Se expande horizontalmente
        add(campoId, gbc);
        gbc.weightx = 0.0; // Restablecer

        // Nombre
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

        // Apellido
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

        // Fecha de Nacimiento
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

        // Nacionalidad
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

        // Correo Electrónico
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

        // Experiencia
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

        // Equipo Asignado
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

        // Botón Registrar Entrenador
        botonRegistrarEntrenador = new JButton("Registrar Entrenador");
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 2; // Ocupa dos columnas
        gbc.weighty = 0.0; // No se expande verticalmente
        gbc.anchor = GridBagConstraints.CENTER;
        add(botonRegistrarEntrenador, gbc);

        // Botón Volver
        botonVolver = new JButton("Volver");
        gbc.gridy = 10;
        gbc.weighty = 0.0;
        add(botonVolver, gbc);

        // Mensaje Label
        mensajeLabel = new JLabel("");
        mensajeLabel.setForeground(Color.RED);
        gbc.gridy = 11;
        gbc.gridwidth = 2;
        gbc.weighty = 1.0; // Empujar hacia abajo
        gbc.anchor = GridBagConstraints.SOUTH;
        add(mensajeLabel, gbc);
    }

    // Getters
    public JTextField getCampoId() { return campoId; }
    public JTextField getCampoNombre() { return campoNombre; }
    public JTextField getCampoApellido() { return campoApellido; }
    public JTextField getCampoFechaNacimiento() { return campoFechaNacimiento; }
    public JTextField getCampoNacionalidad() { return campoNacionalidad; }
    public JTextField getCampoCorreo() { return campoCorreo; }
    public JTextField getCampoExperiencia() { return campoExperiencia; }
    public JTextField getCampoEquipoAsignado() { return campoEquipoAsignado; }
    public JButton getBotonRegistrarEntrenador() { return botonRegistrarEntrenador; }
    public JButton getBotonVolver() { return botonVolver; }

    public void mostrarMensaje(String mensaje) { mensajeLabel.setText(mensaje); }
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
}