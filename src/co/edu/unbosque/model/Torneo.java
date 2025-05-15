package co.edu.unbosque.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Torneo {

    private String id;
    private String nombre;
    private Date fechaInicio;
    private Date fechaFin;
    private String tipoJuego;
    private String formato;
    private List<Jugador> jugadores; // Lista de jugadores inscritos
    private List<Partido> historialPartidos; // Historial de partidos jugados

    /**
     * Constructor de la clase Torneo.
     *
     * @param id          Identificador único del torneo.
     * @param nombre      Nombre del torneo.
     * @param fechaInicio Fecha de inicio del torneo.
     * @param fechaFin    Fecha de fin del torneo.
     * @param tipoJuego   Tipo de juego del torneo (ej. LoL, Dota 2).
     * @param formato     Formato del torneo (ej. Eliminación directa, Grupos).
     */
    public Torneo(String id, String nombre, Date fechaInicio, Date fechaFin, String tipoJuego, String formato) {
        this.id = id;
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.tipoJuego = tipoJuego;
        this.formato = formato;
        this.jugadores = new ArrayList<>();
        this.historialPartidos = new ArrayList<>();
    }

    // Getters y Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getTipoJuego() {
        return tipoJuego;
    }

    public void setTipoJuego(String tipoJuego) {
        this.tipoJuego = tipoJuego;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public List<Partido> getHistorialPartidos() {
        return historialPartidos;
    }

    public void setHistorialPartidos(List<Partido> historialPartidos) {
        this.historialPartidos = historialPartidos;
    }

    /**
     * Agrega un jugador a la lista de jugadores inscritos en el torneo.
     *
     * @param jugador El jugador a agregar.
     */
    public void agregarJugador(Jugador jugador) {
        this.jugadores.add(jugador);
    }

    /**
     * Elimina un jugador de la lista de jugadores inscritos en el torneo.
     *
     * @param jugador El jugador a eliminar.
     */
    public void eliminarJugador(Jugador jugador) {
        this.jugadores.remove(jugador);
    }

    /**
     * Genera una lista de partidos emparejando aleatoriamente a los jugadores inscritos.
     * Si hay un número impar de jugadores, uno de ellos se quedará sin pareja en esta ronda.
     *
     * @return Una lista de objetos Partido representando los emparejamientos.
     */
    public List<Partido> generarEmparejamientosAleatorios() {
        List<Partido> emparejamientos = new ArrayList<>();
        if (jugadores.size() < 2) {
            System.out.println("No hay suficientes jugadores para generar emparejamientos.");
            return emparejamientos;
        }

        // Crear una copia de la lista de jugadores para no modificar la original al barajar
        List<Jugador> jugadoresAleatorios = new ArrayList<>(jugadores);
        Collections.shuffle(jugadoresAleatorios);

        // Emparejar a los jugadores en parejas
        for (int i = 0; i < jugadoresAleatorios.size() - 1; i += 2) {
            Jugador jugador1 = jugadoresAleatorios.get(i);
            Jugador jugador2 = jugadoresAleatorios.get(i + 1);
            emparejamientos.add(new Partido(jugador1, jugador2));
        }

        // Manejar el caso de un número impar de jugadores
        if (jugadoresAleatorios.size() % 2 != 0) {
            Jugador jugadorSinPareja = jugadoresAleatorios.get(jugadoresAleatorios.size() - 1);
            System.out.println("El jugador " + jugadorSinPareja.getNombre() + " se queda sin pareja en esta ronda.");
            // Aquí podrías implementar lógica adicional para el jugador sin pareja,
            // como darle un "bye" (victoria automática) o hacerlo esperar para la siguiente ronda.
        }

        return emparejamientos;
    }

    /**
     * Clase interna para representar un partido entre dos jugadores.
     */
    public static class Partido {
        private Jugador jugador1;
        private Jugador jugador2;
        private Jugador ganador;

        /**
         * Constructor de la clase Partido.
         *
         * @param jugador1 El primer jugador del partido.
         * @param jugador2 El segundo jugador del partido.
         */
        public Partido(Jugador jugador1, Jugador jugador2) {
            this.jugador1 = jugador1;
            this.jugador2 = jugador2;
            this.ganador = null; // Inicialmente no hay ganador
        }

        // Getters para los jugadores del partido

        public Jugador getJugador1() {
            return jugador1;
        }

        public Jugador getJugador2() {
            return jugador2;
        }

        // Getter y Setter para el ganador del partido

        public Jugador getGanador() {
            return ganador;
        }

        public void setGanador(Jugador ganador) {
            this.ganador = ganador;
        }

        @Override
        public String toString() {
            return jugador1.getNombre() + " vs " + jugador2.getNombre() + (ganador != null ? " (Ganador: " + ganador.getNombre() + ")" : "");
        }
    }
}