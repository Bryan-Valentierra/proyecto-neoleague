package co.edu.unbosque.controller;

import co.edu.unbosque.model.Jugador;
import co.edu.unbosque.view.PanelRegistroJugador;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class JugadorController {

    private Jugador jugador;
    private PanelRegistroJugador panelRegistroJugador;
    private List<Jugador> listaJugadores;

    // Inicializa el controlador, guarda la pantalla de registro, crea una lista para los jugadores y prepara los botones.
    public JugadorController(PanelRegistroJugador panelRegistroJugador) {
        this.panelRegistroJugador = panelRegistroJugador;
        this.jugador = new Jugador();
        this.listaJugadores = new ArrayList<>();
        configurarListeners();
    }

    // Asigna una acción a cada botón de la pantalla de registro.
    private void configurarListeners() {
        panelRegistroJugador.getBotonRegistrar().addActionListener(e -> registrarJugador());
        panelRegistroJugador.getBotonModificar().addActionListener(e -> modificarJugador());
        panelRegistroJugador.getBotonEliminar().addActionListener(e -> eliminarJugador());
        panelRegistroJugador.getBotonVolver().addActionListener(e -> volver());
    }

    // Toma los datos de la pantalla, los organiza para crear un nuevo jugador, lo guarda en la lista y muestra un mensaje. También maneja errores si los datos no son correctos.
    private void registrarJugador() {
        try {
            String id = panelRegistroJugador.getCampoId().getText();
            String nombre = panelRegistroJugador.getCampoNombre().getText();
            String apellido = panelRegistroJugador.getCampoApellido().getText();
            Date fechaNacimiento = parseFecha(panelRegistroJugador.getCampoFechaNacimiento().getText());
            String nacionalidad = panelRegistroJugador.getCampoNacionalidad().getText();
            String correo = panelRegistroJugador.getCampoCorreo().getText();
            List<String> juegosParticipados = parseJuegosParticipados(panelRegistroJugador.getCampoJuegosParticipados().getText());
            int victorias = Integer.parseInt(panelRegistroJugador.getCampoVictorias().getText());
            int derrotas = Integer.parseInt(panelRegistroJugador.getCampoDerrotas().getText());
            String record = panelRegistroJugador.getCampoRecord().getText();
            String juegoFavorito = panelRegistroJugador.getCampoJuegoFavorito().getText();
            String rolPrincipal = panelRegistroJugador.getCampoRolPrincipal().getText();
            String estadisticas = panelRegistroJugador.getCampoEstadisticas().getText();

            jugador = new Jugador(id, nombre, apellido, fechaNacimiento, nacionalidad, correo, juegosParticipados, victorias,
                    derrotas, record, juegoFavorito, rolPrincipal, estadisticas);

            listaJugadores.add(jugador);
            panelRegistroJugador.mostrarMensaje("Jugador registrado con éxito.");
            panelRegistroJugador.limpiarFormulario();

        } catch (ParseException e) {
            panelRegistroJugador.mostrarMensaje("Error: Formato de fecha incorrecto (dd/MM/yyyy).");
        } catch (NumberFormatException e) {
            panelRegistroJugador.mostrarMensaje("Error: Ingresa números válidos para victorias y derrotas.");
        } catch (Exception e) {
            panelRegistroJugador.mostrarMensaje("Error al registrar jugador: " + e.getMessage());
        }
    }

    // Busca un jugador por su ID y actualiza su información con los datos de la pantalla. Muestra un mensaje si el jugador se modificó o si no se encontró.
    private void modificarJugador() {
        String id = panelRegistroJugador.getCampoId().getText();
        Jugador jugadorAModificar = buscarJugador(id);

        if (jugadorAModificar != null) {
            try {
                jugadorAModificar.setNombre(panelRegistroJugador.getCampoNombre().getText());
                jugadorAModificar.setApellido(panelRegistroJugador.getCampoApellido().getText());
                jugadorAModificar.setFechaNacimiento(parseFecha(panelRegistroJugador.getCampoFechaNacimiento().getText()));
                jugadorAModificar.setNacionalidad(panelRegistroJugador.getCampoNacionalidad().getText());
                jugadorAModificar.setCorreo(panelRegistroJugador.getCampoCorreo().getText());
                jugadorAModificar.setJuegosParticipados(parseJuegosParticipados(panelRegistroJugador.getCampoJuegosParticipados().getText()));

                String victoriasText = panelRegistroJugador.getCampoVictorias().getText();
                String derrotasText = panelRegistroJugador.getCampoDerrotas().getText();

                int victorias = 0;
                int derrotas = 0;

                if (!victoriasText.isEmpty()) {
                    try {
                        victorias = Integer.parseInt(victoriasText);
                    } catch (NumberFormatException e) {
                        panelRegistroJugador.mostrarMensaje("Error: Victorias debe ser un número válido.");
                        return;
                    }
                }
                if (!derrotasText.isEmpty()) {
                    try {
                        derrotas = Integer.parseInt(derrotasText);
                    } catch (NumberFormatException e) {
                        panelRegistroJugador.mostrarMensaje("Error: Derrotas debe ser un número válido.");
                        return;
                    }
                }

                jugadorAModificar.setVictorias(victorias);
                jugadorAModificar.setDerrotas(derrotas);

                jugadorAModificar.setRecord(panelRegistroJugador.getCampoRecord().getText());
                jugadorAModificar.setJuegoFavorito(panelRegistroJugador.getCampoJuegoFavorito().getText());
                jugadorAModificar.setRolPrincipal(panelRegistroJugador.getCampoRolPrincipal().getText());
                jugadorAModificar.setEstadisticas(panelRegistroJugador.getCampoEstadisticas().getText());

                panelRegistroJugador.mostrarMensaje("Jugador modificado con éxito.");
                panelRegistroJugador.limpiarFormulario();

            } catch (ParseException e) {
                panelRegistroJugador.mostrarMensaje("Error: Formato de fecha incorrecto (dd/MM/yyyy).");
            }
        } else {
            panelRegistroJugador.mostrarMensaje("Error: No se encontró el jugador con ID " + id);
        }
    }

    // Busca un jugador por su ID y lo elimina de la lista. Muestra un mensaje si el jugador se eliminó o si no se encontró.
    private void eliminarJugador() {
        String id = panelRegistroJugador.getCampoId().getText();
        Jugador jugadorAEliminar = buscarJugador(id);

        if (jugadorAEliminar != null) {
            listaJugadores.remove(jugadorAEliminar);
            panelRegistroJugador.mostrarMensaje("Jugador eliminado con éxito.");
            panelRegistroJugador.limpiarFormulario();
        } else {
            panelRegistroJugador.mostrarMensaje("Error: No se encontró el jugador con ID " + id);
        }
    }

    // Muestra un mensaje indicando que la función de volver no está implementada.
    private void volver() {
        panelRegistroJugador.mostrarMensaje("Funcionalidad Volver no implementada.");
    }

    // Convierte un texto en formato "dd/MM/yyyy" a un objeto de tipo Date. Si el formato es incorrecto, lanza un error.
    private Date parseFecha(String fecha) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.parse(fecha);
    }

    // Convierte un texto con juegos separados por comas en una lista de nombres de juegos.
    private List<String> parseJuegosParticipados(String juegos) {
        List<String> juegosParticipados = new ArrayList<>();
        if (juegos != null && !juegos.trim().isEmpty()) {
            String[] juegosArray = juegos.split(",");
            for (String juego : juegosArray) {
                juegosParticipados.add(juego.trim());
            }
        }
        return juegosParticipados;
    }

    // Busca un jugador en la lista por su ID y lo devuelve si lo encuentra, de lo contrario devuelve nada.
    private Jugador buscarJugador(String id) {
        for (Jugador j : listaJugadores) {
            if (j.getId().equals(id)) {
                return j;
            }
        }
        return null;
    }

    // Métodos para obtener y cambiar los valores de las variables privadas (si se necesitan desde otras partes del código).
    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public PanelRegistroJugador getPanelRegistroJugador() {
        return panelRegistroJugador;
    }

    public void setPanelRegistroJugador(PanelRegistroJugador panelRegistroJugador) {
        this.panelRegistroJugador = panelRegistroJugador;
    }

    public List<Jugador> getListaJugadores() {
        return listaJugadores;
    }

    public void setListaJugadores(List<Jugador> listaJugadores) {
        this.listaJugadores = listaJugadores;
    }
}