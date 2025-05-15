package co.edu.unbosque.view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PanelRegistrarEmparejamiento extends JPanel {

    private JLabel labelTitulo;
    private JLabel labelIdEmparejamiento;
    private JTextField campoIdEmparejamiento;
    private JLabel labelIdEquipo1;
    private JTextField campoIdEquipo1;
    private JLabel labelIdEquipo2;
    private JTextField campoIdEquipo2;
    private JLabel labelIdTorneo;
    private JTextField campoIdTorneo;
    private JLabel labelFecha;
    private JTextField campoFecha;
    private JLabel labelHora;
    private JTextField campoHora;
    private JLabel labelRonda;
    private JTextField campoRonda;
    private JLabel labelEstado;
    private JComboBox<String> comboEstado;

    private JButton botonRegistrar;
    private JButton botonModificar;
    private JButton botonEliminar;
    private JButton botonVolver;

    private JLabel mensajeLabel;

    public PanelRegistrarEmparejamiento() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 1.0;

        // Inicializar componentes
        labelTitulo = new JLabel("Registrar Emparejamiento");
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(labelTitulo, gbc);

        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;

        labelIdEmparejamiento = new JLabel("ID Emparejamiento:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(labelIdEmparejamiento, gbc);
        campoIdEmparejamiento = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(campoIdEmparejamiento, gbc);

        labelIdEquipo1 = new JLabel("ID Equipo 1:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(labelIdEquipo1, gbc);
        campoIdEquipo1 = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(campoIdEquipo1, gbc);

        labelIdEquipo2 = new JLabel("ID Equipo 2:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(labelIdEquipo2, gbc);
        campoIdEquipo2 = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(campoIdEquipo2, gbc);

        labelIdTorneo = new JLabel("ID Torneo:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(labelIdTorneo, gbc);
        campoIdTorneo = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 4;
        add(campoIdTorneo, gbc);

        labelFecha = new JLabel("Fecha (YYYY-MM-DD):");
        gbc.gridx = 0;
        gbc.gridy = 5;
        add(labelFecha, gbc);
        campoFecha = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 5;
        add(campoFecha, gbc);

        labelHora = new JLabel("Hora (HH:MM):");
        gbc.gridx = 0;
        gbc.gridy = 6;
        add(labelHora, gbc);
        campoHora = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 6;
        add(campoHora, gbc);

        labelRonda = new JLabel("Ronda:");
        gbc.gridx = 0;
        gbc.gridy = 7;
        add(labelRonda, gbc);
        campoRonda = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 7;
        add(campoRonda, gbc);

        labelEstado = new JLabel("Estado:");
        gbc.gridx = 0;
        gbc.gridy = 8;
        add(labelEstado, gbc);
        String[] estados = {"Pendiente", "En Curso", "Finalizado"};
        comboEstado = new JComboBox<>(estados);
        gbc.gridx = 1;
        gbc.gridy = 8;
        add(comboEstado, gbc);

        // Botones
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        botonRegistrar = new JButton("Registrar Emparejamiento");
        add(botonRegistrar, gbc);

        gbc.gridy = 10;
        botonModificar = new JButton("Modificar Emparejamiento");
        add(botonModificar, gbc);

        gbc.gridy = 11;
        botonEliminar = new JButton("Eliminar Emparejamiento");
        add(botonEliminar, gbc);

        gbc.gridy = 12;
        botonVolver = new JButton("Volver a Registro");
        add(botonVolver, gbc);

        mensajeLabel = new JLabel("");
        mensajeLabel.setForeground(Color.RED);
        gbc.gridy = 13;
        add(mensajeLabel, gbc);
    }

    // Getters
    public JTextField getCampoIdEmparejamiento() {
        return campoIdEmparejamiento;
    }

    public JTextField getCampoIdEquipo1() {
        return campoIdEquipo1;
    }

    public JTextField getCampoIdEquipo2() {
        return campoIdEquipo2;
    }

    public JTextField getCampoIdTorneo() {
        return campoIdTorneo;
    }

    public JTextField getCampoFecha() {
        return campoFecha;
    }

    public JTextField getCampoHora() {
        return campoHora;
    }

    public JTextField getCampoRonda() {
        return campoRonda;
    }

    public JComboBox<String> getComboEstado() {
        return comboEstado;
    }

    public JButton getBotonRegistrar() {
        return botonRegistrar;
    }

    public JButton getBotonModificar() {
        return botonModificar;
    }

    public JButton getBotonEliminar() {
        return botonEliminar;
    }

    public JButton getBotonVolver() {
        return botonVolver;
    }

    public void mostrarMensaje(String mensaje) {
        mensajeLabel.setText(mensaje);
    }

    public void limpiarFormulario() {
        campoIdEmparejamiento.setText("");
        campoIdEquipo1.setText("");
        campoIdEquipo2.setText("");
        campoIdTorneo.setText("");
        campoFecha.setText("");
        campoHora.setText("");
        campoRonda.setText("");
        comboEstado.setSelectedIndex(0);
        mensajeLabel.setText("");
    }

    public List<JTextField> getCamposModificarEmparejamiento() {
        List<JTextField> campos = new ArrayList<>();
        campos.add(campoIdEmparejamiento);
        campos.add(campoIdEquipo1);
        campos.add(campoIdEquipo2);
        campos.add(campoIdTorneo);
        campos.add(campoFecha);
        campos.add(campoHora);
        campos.add(campoRonda);
        campos.add(new JTextField(comboEstado.getSelectedItem().toString())); // Convertir valor del JComboBox a JTextField
        return campos;
    }
}