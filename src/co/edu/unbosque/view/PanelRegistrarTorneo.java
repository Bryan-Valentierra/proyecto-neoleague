package co.edu.unbosque.view;

import javax.swing.*;
import java.awt.*;

public class PanelRegistrarTorneo extends JPanel {

    private JLabel labelTitulo;
    private JLabel labelIdTorneo;
    private JTextField campoIdTorneo;
    private JLabel labelNombreTorneo;
    private JTextField campoNombreTorneo;
    private JLabel labelFechaInicio;
    private JTextField campoFechaInicio;
    private JLabel labelFechaFin;
    private JTextField campoFechaFin;
    private JLabel labelReglamento;
    private JTextArea areaReglamento;
    private JScrollPane scrollReglamento;
    private JLabel labelTipoJuego;
    private JTextField campoTipoJuego;
    private JLabel labelMaxEquipos;
    private JSpinner spinnerMaxEquipos;

    private JButton botonRegistrar;
    private JButton botonModificar;
    private JButton botonEliminar;
    private JButton botonVolver;

    private JLabel mensajeLabel;

    public PanelRegistrarTorneo() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 1.0;

        // Inicializar componentes
        labelTitulo = new JLabel("Registrar Torneo");
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(labelTitulo, gbc);

        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;

        labelIdTorneo = new JLabel("ID Torneo:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(labelIdTorneo, gbc);
        campoIdTorneo = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(campoIdTorneo, gbc);

        labelNombreTorneo = new JLabel("Nombre del Torneo:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(labelNombreTorneo, gbc);
        campoNombreTorneo = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(campoNombreTorneo, gbc);

        labelFechaInicio = new JLabel("Fecha de Inicio (YYYY-MM-DD):");
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(labelFechaInicio, gbc);
        campoFechaInicio = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(campoFechaInicio, gbc);

        labelFechaFin = new JLabel("Fecha de Fin (YYYY-MM-DD):");
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(labelFechaFin, gbc);
        campoFechaFin = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 4;
        add(campoFechaFin, gbc);

        labelReglamento = new JLabel("Reglamento:");
        gbc.gridx = 0;
        gbc.gridy = 5;
        add(labelReglamento, gbc);
        // Área de texto reducida en un 30% (de 5 filas a 3.5 filas, redondeado a 3)
        areaReglamento = new JTextArea(3, 20); // Reducido de 5 a 3 filas
        scrollReglamento = new JScrollPane(areaReglamento);
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.weighty = 1.0; // Permitir que el área de texto crezca verticalmente
        gbc.fill = GridBagConstraints.BOTH; // Llenar ambas direcciones
        add(scrollReglamento, gbc);
        gbc.weighty = 0.0; // Restablecer el peso vertical

        gbc.fill = GridBagConstraints.HORIZONTAL; // Restablecer el fill

        labelTipoJuego = new JLabel("Tipo de Juego:");
        gbc.gridx = 0;
        gbc.gridy = 6;
        add(labelTipoJuego, gbc);
        campoTipoJuego = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 6;
        add(campoTipoJuego, gbc);

        labelMaxEquipos = new JLabel("Número Máximo de Equipos:");
        gbc.gridx = 0;
        gbc.gridy = 7;
        add(labelMaxEquipos, gbc);
        spinnerMaxEquipos = new JSpinner(new SpinnerNumberModel(2, 2, 100, 1)); // Ejemplo de rango
        gbc.gridx = 1;
        gbc.gridy = 7;
        add(spinnerMaxEquipos, gbc);

        // Botones
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        botonRegistrar = new JButton("Registrar Torneo");
        add(botonRegistrar, gbc);

        gbc.gridy = 9;
        botonModificar = new JButton("Modificar Torneo");
        add(botonModificar, gbc);

        gbc.gridy = 10;
        botonEliminar = new JButton("Eliminar Torneo");
        add(botonEliminar, gbc);

        gbc.gridy = 11;
        botonVolver = new JButton("Volver a Registro");
        add(botonVolver, gbc);

        mensajeLabel = new JLabel("");
        mensajeLabel.setForeground(Color.RED);
        gbc.gridy = 12;
        add(mensajeLabel, gbc);
    }

    // Getters
    public JTextField getCampoIdTorneo() {
        return campoIdTorneo;
    }

    public JTextField getCampoNombreTorneo() {
        return campoNombreTorneo;
    }

    public JTextField getCampoFechaInicio() {
        return campoFechaInicio;
    }

    public JTextField getCampoFechaFin() {
        return campoFechaFin;
    }

    public String getAreaReglamento() {
        return areaReglamento.getText();
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
        campoIdTorneo.setText("");
        campoNombreTorneo.setText("");
        campoFechaInicio.setText("");
        campoFechaFin.setText("");
        areaReglamento.setText("");
        campoTipoJuego.setText("");
        spinnerMaxEquipos.setValue(2);
        mensajeLabel.setText("");
    }
}