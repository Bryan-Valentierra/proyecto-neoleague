package co.edu.unbosque.controller;

import co.edu.unbosque.model.Equipo;
import co.edu.unbosque.view.PanelRegistroEquipo;
import java.util.ArrayList;
import java.util.List;

public class EquipoController {
    private Equipo equipo;
    private PanelRegistroEquipo panelRegistroEquipo;
    private List<Equipo> listaEquipos;

    public EquipoController(PanelRegistroEquipo panelRegistroEquipo) {
        this.panelRegistroEquipo = panelRegistroEquipo;
        this.equipo = new Equipo();
        this.listaEquipos = new ArrayList<>();
        configurarListeners();
    }

    private void configurarListeners() {
        panelRegistroEquipo.getBotonRegistrarEquipo().addActionListener(e -> registrarEquipo());
        panelRegistroEquipo.getBotonVolver().addActionListener(e -> volver());
    }

    private void registrarEquipo() {
        String idEquipo = panelRegistroEquipo.getCampoIdEquipo().getText();
        String nombreEquipo = panelRegistroEquipo.getCampoNombreEquipo().getText();
        String patrocinador = panelRegistroEquipo.getCampoPatrocinador().getText();

        if (idEquipo.isEmpty() || nombreEquipo.isEmpty() || patrocinador.isEmpty()) {
            panelRegistroEquipo.mostrarMensaje("Todos los campos son obligatorios.");
            return;
        }

        // Verificar si el equipo ya existe (por ID)
        if (buscarEquipo(idEquipo) != null) {
            panelRegistroEquipo.mostrarMensaje("Ya existe un equipo con el ID " + idEquipo);
            return;
        }

        Equipo nuevoEquipo = new Equipo(idEquipo, nombreEquipo, patrocinador);
        listaEquipos.add(nuevoEquipo);
        panelRegistroEquipo.mostrarMensaje("Equipo registrado con éxito.");
        panelRegistroEquipo.limpiarFormulario();

        // Opcional: Imprimir la lista de equipos para verificar
        System.out.println("Lista de Equipos: " + listaEquipos);
    }

    private Equipo buscarEquipo(String idEquipo) {
        for (Equipo equipo : listaEquipos) {
            if (equipo.getIdEquipo().equals(idEquipo)) {
                return equipo;
            }
        }
        return null;
    }

    private void volver() {
        panelRegistroEquipo.mostrarMensaje("Volviendo...");
        panelRegistroEquipo.limpiarFormulario();
        //  Aquí iría la lógica para cambiar de panel o ventana
    }


}