package co.edu.unbosque.controller;
import co.edu.unbosque.controller.Controller;
import co.edu.unbosque.view.VentanaPrincipal;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
            ventanaPrincipal.setTitle("NeoLeague Arena");
            ventanaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ventanaPrincipal.setPreferredSize(new Dimension(800, 900));
            ventanaPrincipal.pack();
            ventanaPrincipal.setLocationRelativeTo(null);
            ventanaPrincipal.setVisible(true);

            // Instanciar explícitamente el panel que da error para forzar su creación
            ventanaPrincipal.getPanelTorneosActivos();

            // Crear el controlador después de que los paneles estén inicializados
            Controller controller = new Controller(ventanaPrincipal);
        });
    }
}
