package co.edu.unbosque.model;

import java.util.ArrayList;
import java.util.List;

public class Equipo {
    private String idEquipo;
    private String nombreEquipo;
    private String patrocinador;
    private List<Jugador> jugadores;  // Relación con Jugador
    private Entrenador entrenador;    // Relación con Entrenador

    public Equipo() {
        this.jugadores = new ArrayList<>();
    }

    public Equipo(String idEquipo, String nombreEquipo, String patrocinador) {
        this.idEquipo = idEquipo;
        this.nombreEquipo = nombreEquipo;
        this.patrocinador = patrocinador;
        this.jugadores = new ArrayList<>();
    }

    // Getters y Setters
    public String getIdEquipo() { return idEquipo; }
    public void setIdEquipo(String idEquipo) { this.idEquipo = idEquipo; }
    public String getNombreEquipo() { return nombreEquipo; }
    public void setNombreEquipo(String nombreEquipo) { this.nombreEquipo = nombreEquipo; }
    public String getPatrocinador() { return patrocinador; }
    public void setPatrocinador(String patrocinador) { this.patrocinador = patrocinador; }
    public List<Jugador> getJugadores() { return jugadores; }
    public void setJugadores(List<Jugador> jugadores) { this.jugadores = jugadores; }
    public Entrenador getEntrenador() { return entrenador; }
    public void setEntrenador(Entrenador entrenador) { this.entrenador = entrenador; }

    @Override
    public String toString() {
        return "Equipo{" +
                "idEquipo='" + idEquipo + '\'' +
                ", nombreEquipo='" + nombreEquipo + '\'' +
                ", patrocinador='" + patrocinador + '\'' +
                '}';
    }
}