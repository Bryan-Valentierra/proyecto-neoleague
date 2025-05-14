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

    public EntrenadorController(PanelRegistroEntrenador panelRegistroEntrenador) {
        this.panelRegistroEntrenador = panelRegistroEntrenador;
        this.entrenador = new Entrenador();
        this.listaEntrenadores = new ArrayList<>();
        configurarListeners();
    }

    private void configurarListeners() {
        panelRegistroEntrenador.getBotonRegistrarEntrenador().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarEntrenador();
            }
        });
        panelRegistroEntrenador.getBotonVolver().addActionListener(e -> volver());
    }

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
            panelRegistroEntrenador.mostrarMensaje("Todos los campos son obligatorios.");
            return;
        }

        // Verificar si el entrenador ya existe (por ID)
        if (buscarEntrenador(id) != null) {
            panelRegistroEntrenador.mostrarMensaje("Ya existe un entrenador con el ID " + id);
            return;
        }

        Entrenador nuevoEntrenador = new Entrenador(id, nombre, apellido, fechaNacimiento, nacionalidad, correo, experiencia, equipoAsignado);
        listaEntrenadores.add(nuevoEntrenador);
        panelRegistroEntrenador.mostrarMensaje("Entrenador registrado con éxito.");
        panelRegistroEntrenador.limpiarFormulario();

        // Opcional: Imprimir la lista de entrenadores para verificar
        System.out.println("Lista de Entrenadores: " + listaEntrenadores);
    }

    private Entrenador buscarEntrenador(String id) {
        for (Entrenador entrenador : listaEntrenadores) {
            if (entrenador.getId().equals(id)) {
                return entrenador;
            }
        }
        return null;
    }

    private void volver() {
        panelRegistroEntrenador.mostrarMensaje("Volviendo...");
        panelRegistroEntrenador.limpiarFormulario();
        //  Aquí iría la lógica para cambiar de panel o ventana
    }


}