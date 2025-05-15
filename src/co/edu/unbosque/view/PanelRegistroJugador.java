package co.edu.unbosque.view;

import javax.swing.*;
import java.awt.*;

public class PanelRegistroJugador extends JPanel {

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
    private JLabel labelJuegosParticipados;
    private JTextField campoJuegosParticipados;
    private JLabel labelVictorias;
    private JTextField campoVictorias;
    private JLabel labelDerrotas;
    private JTextField campoDerrotas;
    private JLabel labelRecord;
    private JTextField campoRecord;
    private JLabel labelJuegoFavorito;
    private JTextField campoJuegoFavorito;
    private JLabel labelRolPrincipal;
    private JTextField campoRolPrincipal;
    private JLabel labelEstadisticas;
    private JTextField campoEstadisticas;
    private JLabel labelHistorialCompetitivo;  // Nuevo label
    private JTextField campoHistorialCompetitivo;  // Nuevo campo
    private JLabel labelAnalisisJuegos;        // Nuevo label
    private JTextField campoAnalisisJuegos;          // Nuevo campo

    private JButton botonRegistrar;
    private JButton botonModificar;
    private JButton botonEliminar;
    private JButton botonVolver;

    private JLabel mensajeLabel;

    public PanelRegistroJugador() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 1.0; // Para que los campos se expandan

        // Inicializar componentes
        labelTitulo = new JLabel("Registro de Jugador");
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(labelTitulo, gbc);

        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;

        labelId = new JLabel("ID:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(labelId, gbc);
        campoId = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(campoId, gbc);

        labelNombre = new JLabel("Nombre:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(labelNombre, gbc);
        campoNombre = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(campoNombre, gbc);

        labelApellido = new JLabel("Apellido:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(labelApellido, gbc);
        campoApellido = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(campoApellido, gbc);

        labelFechaNacimiento = new JLabel("Fecha de Nacimiento:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(labelFechaNacimiento, gbc);
        campoFechaNacimiento = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 4;
        add(campoFechaNacimiento, gbc);

        labelNacionalidad = new JLabel("Nacionalidad:");
        gbc.gridx = 0;
        gbc.gridy = 5;
        add(labelNacionalidad, gbc);
        campoNacionalidad = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 5;
        add(campoNacionalidad, gbc);

        labelCorreo = new JLabel("Correo Electrónico:");
        gbc.gridx = 0;
        gbc.gridy = 6;
        add(labelCorreo, gbc);
        campoCorreo = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 6;
        add(campoCorreo, gbc);

        labelJuegosParticipados = new JLabel("Juegos Participados (separados por comas):");
        gbc.gridx = 0;
        gbc.gridy = 7;
        add(labelJuegosParticipados, gbc);
        campoJuegosParticipados = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 7;
        add(campoJuegosParticipados, gbc);

        labelVictorias = new JLabel("Victorias:");
        gbc.gridx = 0;
        gbc.gridy = 8;
        add(labelVictorias, gbc);
        campoVictorias = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 8;
        add(campoVictorias, gbc);

        labelDerrotas = new JLabel("Derrotas:");
        gbc.gridx = 0;
        gbc.gridy = 9;
        add(labelDerrotas, gbc);
        campoDerrotas = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 9;
        add(campoDerrotas, gbc);

        labelRecord = new JLabel("Récord:");
        gbc.gridx = 0;
        gbc.gridy = 10;
        add(labelRecord, gbc);
        campoRecord = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 10;
        add(campoRecord, gbc);

        labelJuegoFavorito = new JLabel("Juego Favorito:");
        gbc.gridx = 0;
        gbc.gridy = 11;
        add(labelJuegoFavorito, gbc);
        campoJuegoFavorito = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 11;
        add(campoJuegoFavorito, gbc);

        labelRolPrincipal = new JLabel("Rol Principal:");
        gbc.gridx = 0;
        gbc.gridy = 12;
        add(labelRolPrincipal, gbc);
        campoRolPrincipal = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 12;
        add(campoRolPrincipal, gbc);

        labelEstadisticas = new JLabel("Estadísticas del Juego:");
        gbc.gridx = 0;
        gbc.gridy = 13;
        add(labelEstadisticas, gbc);
        campoEstadisticas = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 13;
        add(campoEstadisticas, gbc);

        labelHistorialCompetitivo = new JLabel("Historial Competitivo:");  // Nuevo label
        gbc.gridx = 0;
        gbc.gridy = 14;
        add(labelHistorialCompetitivo, gbc);
        campoHistorialCompetitivo = new JTextField(20);  // Nuevo campo
        gbc.gridx = 1;
        gbc.gridy = 14;
        add(campoHistorialCompetitivo, gbc);

        labelAnalisisJuegos = new JLabel("Análisis de Juegos:");        // Nuevo label
        gbc.gridx = 0;
        gbc.gridy = 15;
        add(labelAnalisisJuegos, gbc);
        campoAnalisisJuegos = new JTextField(20);          // Nuevo campo
        gbc.gridx = 1;
        gbc.gridy = 15;
        add(campoAnalisisJuegos, gbc);

        // Botones
        gbc.gridx = 0;
        gbc.gridy = 16;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        botonRegistrar = new JButton("Registrar Jugador");
        add(botonRegistrar, gbc);

        gbc.gridy = 17;
        botonModificar = new JButton("Modificar Jugador");
        add(botonModificar, gbc);

        gbc.gridy = 18;
        botonEliminar = new JButton("Eliminar Jugador");
        add(botonEliminar, gbc);

        gbc.gridy = 19;
        botonVolver = new JButton("Volver a Registro");
        add(botonVolver, gbc);

        mensajeLabel = new JLabel("");
        mensajeLabel.setForeground(Color.RED);
        gbc.gridy = 20;
        add(mensajeLabel, gbc);
    }

    // Getters
    public JTextField getCampoId() {
        return campoId;
    }

    public JTextField getCampoNombre() {
        return campoNombre;
    }

    public JTextField getCampoApellido() {
        return campoApellido;
    }

    public JTextField getCampoFechaNacimiento() {
        return campoFechaNacimiento;
    }

    public JTextField getCampoNacionalidad() {
        return campoNacionalidad;
    }

    public JTextField getCampoCorreo() {
        return campoCorreo;
    }

    public JTextField getCampoJuegosParticipados() {
        return campoJuegosParticipados;
    }

    public JTextField getCampoVictorias() {
        return campoVictorias;
    }

    public JTextField getCampoDerrotas() {
        return campoDerrotas;
    }

    public JTextField getCampoRecord() {
        return campoRecord;
    }

    public JTextField getCampoJuegoFavorito() {
        return campoJuegoFavorito;
    }

    public JTextField getCampoRolPrincipal() {
        return campoRolPrincipal;
    }

    public JTextField getCampoEstadisticas() {
        return campoEstadisticas;
    }

    public JTextField getCampoHistorialCompetitivo() {
        return campoHistorialCompetitivo;
    }

    public JTextField getCampoAnalisisJuegos() {
        return campoAnalisisJuegos;
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
        campoId.setText("");
        campoNombre.setText("");
        campoApellido.setText("");
        campoFechaNacimiento.setText("");
        campoNacionalidad.setText("");
        campoCorreo.setText("");
        campoJuegosParticipados.setText("");
        campoVictorias.setText("");
        campoDerrotas.setText("");
        campoRecord.setText("");
        campoJuegoFavorito.setText("");
        campoRolPrincipal.setText("");
        campoEstadisticas.setText("");
        campoHistorialCompetitivo.setText("");
        campoAnalisisJuegos.setText("");
    }
    
     public java.util.List<JTextField> getCamposModificarJugador() {
        java.util.List<JTextField> campos = new java.util.ArrayList<>();
        campos.add(campoId);
        campos.add(campoNombre);
        campos.add(campoApellido);
        campos.add(campoFechaNacimiento);
        campos.add(campoNacionalidad);
        campos.add(campoCorreo);
        campos.add(campoJuegosParticipados);
        campos.add(campoVictorias);
        campos.add(campoDerrotas);
        campos.add(campoRecord);
        campos.add(campoJuegoFavorito);
        campos.add(campoRolPrincipal);
        campos.add(campoEstadisticas);
        campos.add(campoHistorialCompetitivo);
        campos.add(campoAnalisisJuegos);
        return campos;
    }
}