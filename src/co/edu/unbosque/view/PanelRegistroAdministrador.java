package co.edu.unbosque.view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PanelRegistroAdministrador extends JPanel {

    private JLabel labelTitulo;
    private JLabel labelIdAdministrador;
    private JTextField campoIdAdministrador;
    private JLabel labelNombre;
    private JTextField campoNombre;
    private JLabel labelUsuario;
    private JTextField campoUsuario;
    private JLabel labelContrasena;
    private JPasswordField campoContrasena;

    private JButton botonRegistrar;
    private JButton botonModificar;
    private JButton botonEliminar;
    private JButton botonVolver;

    private JLabel mensajeLabel;

    public PanelRegistroAdministrador() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 1.0;

        // Inicializar componentes
        labelTitulo = new JLabel("Registro de Administrador");
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(labelTitulo, gbc);

        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;

        labelIdAdministrador = new JLabel("ID Administrador:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(labelIdAdministrador, gbc);
        campoIdAdministrador = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(campoIdAdministrador, gbc);

        labelNombre = new JLabel("Nombre:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(labelNombre, gbc);
        campoNombre = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(campoNombre, gbc);

        labelUsuario = new JLabel("Usuario:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(labelUsuario, gbc);
        campoUsuario = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(campoUsuario, gbc);

        labelContrasena = new JLabel("Contraseña:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(labelContrasena, gbc);
        campoContrasena = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 4;
        add(campoContrasena, gbc);

        // Botones
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        botonRegistrar = new JButton("Registrar Administrador");
        add(botonRegistrar, gbc);

        gbc.gridy = 6;
        botonModificar = new JButton("Modificar Administrador");
        add(botonModificar, gbc);

        gbc.gridy = 7;
        botonEliminar = new JButton("Eliminar Administrador");
        add(botonEliminar, gbc);

        gbc.gridy = 8;
        botonVolver = new JButton("Volver a Registro");
        add(botonVolver, gbc);

        mensajeLabel = new JLabel("");
        mensajeLabel.setForeground(Color.RED);
        gbc.gridy = 9;
        add(mensajeLabel, gbc);
    }

    // Getters
    public JTextField getCampoIdAdministrador() {
        return campoIdAdministrador;
    }

    public JTextField getCampoNombre() {
        return campoNombre;
    }

    public JTextField getCampoUsuario() {
        return campoUsuario;
    }

    public JPasswordField getCampoContrasena() {
        return campoContrasena;
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
        campoIdAdministrador.setText("");
        campoNombre.setText("");
        campoUsuario.setText("");
        campoContrasena.setText("");
        mensajeLabel.setText("");
    }

    public List<JTextField> getCamposModificarAdministrador() {
        List<JTextField> campos = new ArrayList<>();
        campos.add(campoIdAdministrador);
        campos.add(campoNombre);
        campos.add(campoUsuario);
        campos.add(new JTextField(new String(campoContrasena.getPassword()))); // Obtener la contraseña como String en un JTextField temporal
        return campos;
    }
}