package co.edu.unbosque.view;

import javax.swing.*;
import java.awt.*;

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

    public PanelRegistroEquipo() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 1.0;

        // Inicializar componentes
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
        spinnerNumeroJugadores = new JSpinner(new SpinnerNumberModel(1, 1, 30, 1)); // Ejemplo de rango
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

        // Botones
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

    // Getters
    
     
public JButton getBotonRegistrarEquipo() {
    return botonRegistrar; 
}
    public JTextField getCampoIdEquipo() {
        return campoIdEquipo;
    }

    public JTextField getCampoNombreEquipo() {
        return campoNombreEquipo;
    }

    public JTextField getCampoFechaCreacion() {
        return campoFechaCreacion;
    }

    public JSpinner getSpinnerNumeroJugadores() {
        return spinnerNumeroJugadores;
    }

    public JTextField getCampoEntrenador() {
        return campoEntrenador;
    }

    public JTextField getCampoJuegoPrincipal() {
        return campoJuegoPrincipal;
    }

    public JTextField getCampoPatrocinador() {
        return campoPatrocinador;
    }

    public JTextField getCampoSedeEntrenamiento() {
        return campoSedeEntrenamiento;
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
}