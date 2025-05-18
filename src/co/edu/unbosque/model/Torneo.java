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
    private List<Jugador> jugadores;
    private List<Partido> historialPartidos;
    private final int maxEquipos;
    private final String reglamento;
    private final String idTorneo;
    private List<Torneo> listTorneo; // Agregamos la lista de torneos (aunque su uso aquí no es claro)

    public Torneo(String idTorneo, String nombre, Date fechaInicio, Date fechaFin, String reglamento, String tipoJuego, int maxEquipos) {
        this.idTorneo = idTorneo;
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.reglamento = reglamento;
        this.tipoJuego = tipoJuego;
        this.maxEquipos = maxEquipos;
        this.jugadores = new ArrayList<>();
        this.historialPartidos = new ArrayList<>();
        this.listTorneo = new ArrayList<>(); // Inicializamos la lista de torneos
    }

    public String getIdTorneo() {
        return idTorneo;
    }

    public List<Torneo> getListTorneo() {
        return listTorneo;
    }

    public void setListTorneo(List<Torneo> listTorneo) {
        this.listTorneo = listTorneo;
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setIdTorneo(String id) {
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

    public void agregarJugador(Jugador jugador) {
        this.jugadores.add(jugador);
    }

    public void eliminarJugador(Jugador jugador) {
        this.jugadores.remove(jugador);
    }

    public String getReglamento() {
        return reglamento;
    }

    public int getMaxEquipos() {
        return maxEquipos;
    }

    public List<Partido> generarEmparejamientosAleatorios() {
        List<Partido> emparejamientos = new ArrayList<>();
        if (jugadores.size() < 2) {
            System.out.println("No hay suficientes jugadores para generar emparejamientos.");
            return emparejamientos;
        }

        List<Jugador> jugadoresAleatorios = new ArrayList<>(jugadores);
        Collections.shuffle(jugadoresAleatorios);

        for (int i = 0; i < jugadoresAleatorios.size() - 1; i += 2) {
            Jugador jugador1 = jugadoresAleatorios.get(i);
            Jugador jugador2 = jugadoresAleatorios.get(i + 1);
            emparejamientos.add(new Partido(jugador1, jugador2));
        }

        if (jugadoresAleatorios.size() % 2 != 0) {
            Jugador jugadorSinPareja = jugadoresAleatorios.get(jugadoresAleatorios.size() - 1);
            System.out.println("El jugador " + jugadorSinPareja.getNombre() + " se queda sin pareja en esta ronda.");
        }

        return emparejamientos;
    }

    public static class Partido {

        private Jugador jugador1;
        private Jugador jugador2;
        private Jugador ganador;

        public Partido(Jugador jugador1, Jugador jugador2) {
            this.jugador1 = jugador1;
            this.jugador2 = jugador2;
            this.ganador = null;
        }

        public Jugador getJugador1() {
            return jugador1;
        }

        public Jugador getJugador2() {
            return jugador2;
        }

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
