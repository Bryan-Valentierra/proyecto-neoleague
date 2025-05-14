// Main.java
package co.edu.unbosque.controller;

import co.edu.unbosque.view.VentanaPrincipal;
import javax.swing.SwingUtilities;
import java.awt.Dimension;
import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
            ventanaPrincipal.setTitle("NeoLeague Arena");
            ventanaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ventanaPrincipal.setPreferredSize(new Dimension(800, 900)); // Aumenté la altura
            ventanaPrincipal.pack();
            ventanaPrincipal.setLocationRelativeTo(null);
            ventanaPrincipal.setVisible(true);
            Controller controller = new Controller(ventanaPrincipal);
        });
    }
}


