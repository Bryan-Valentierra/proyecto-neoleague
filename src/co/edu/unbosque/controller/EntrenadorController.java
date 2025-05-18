package co.edu.unbosque.controller;

import co.edu.unbosque.model.Entrenador;
import co.edu.unbosque.view.PanelRegistroEntrenador;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EntrenadorController {
    private Entrenador entrenador;
    private PanelRegistroEntrenador panelRegistroEntrenador;
    private List<Entrenador> listaEntrenadores;

    // Crea un nuevo controlador, guarda la pantalla y prepara la lista de entrenadores y los botones.
    public EntrenadorController(PanelRegistroEntrenador panelRegistroEntrenador) {
        this.panelRegistroEntrenador = panelRegistroEntrenador;
        this.entrenador = new Entrenador();
        this.listaEntrenadores = new ArrayList<>();
        configurarListeners();
    }

    // Indica qué hacer cuando se presionan los botones de la pantalla.
    private void configurarListeners() {
        panelRegistroEntrenador.getBotonRegistrarEntrenador().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarEntrenador(); // Al presionar "Registrar", llama a la función para guardar el entrenador.
            }
        });
        panelRegistroEntrenador.getBotonVolver().addActionListener(e -> volver()); // Al presionar "Volver", llama a la función para regresar.
    }

    // Toma los datos de la pantalla, verifica que estén completos, guarda el nuevo entrenador en la lista y avisa si todo salió bien.
    private void registrarEntrenador() {
        String id = panelRegistroEntrenador.getCampoId().getText();
        String nombre = panelRegistroEntrenador.getCampoNombre().getText();
        String apellido = panelRegistroEntrenador.getCampoApellido().getText();
        String fechaNacimiento = panelRegistroEntrenador.getCampoFechaNacimiento().getText();
        String nacionalidad = panelRegistroEntrenador.getCampoNacionalidad().getText();
        String correo = panelRegistroEntrenador.getCampoCorreo().getText();
        String experiencia = panelRegistroEntrenador.getCampoExperiencia().getText();
        String equipoAsignado = panelRegistroEntrenador.getCampoEquipoAsignado().getText();

        if (id.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || fechaNacimiento.isEmpty() ||
                nacionalidad.isEmpty() || correo.isEmpty() || experiencia.isEmpty() || equipoAsignado.isEmpty()) {
            panelRegistroEntrenador.mostrarMensaje("Faltan datos.");
            return;
        }

        if (buscarEntrenador(id) != null) {
            panelRegistroEntrenador.mostrarMensaje("Este ID ya existe.");
            return;
        }

        Entrenador nuevoEntrenador = new Entrenador(id, nombre, apellido, fechaNacimiento, nacionalidad, correo, experiencia, equipoAsignado);
        listaEntrenadores.add(nuevoEntrenador);
        panelRegistroEntrenador.mostrarMensaje("Registro exitoso.");
        panelRegistroEntrenador.limpiarFormulario();

        System.out.println("Entrenadores: " + listaEntrenadores);
    }

    // Busca un entrenador en la lista por su ID.
    private Entrenador buscarEntrenador(String id) {
        for (Entrenador entrenador : listaEntrenadores) {
            if (entrenador.getId().equals(id)) {
                return entrenador;
            }
        }
        return null;
    }

    // Muestra un mensaje y borra los campos de la pantalla al volver.
    private void volver() {
        panelRegistroEntrenador.mostrarMensaje("Volviendo...");
        panelRegistroEntrenador.limpiarFormulario();
    }
}