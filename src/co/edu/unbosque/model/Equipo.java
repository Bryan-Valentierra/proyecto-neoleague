package co.edu.unbosque.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Date; 

public class Equipo {
    private String idEquipo;
    private String nombreEquipo;
    private Date fechaCreacion;       
    private int numeroJugadores;     
    private String juegoPrincipal;    
    private String sedeEntrenamiento; 
    private String patrocinador;
    private List<Jugador> jugadores;
    private Entrenador entrenador;

    public Equipo() {
        this.jugadores = new ArrayList<>();
    }

    public Equipo(String idEquipo, String nombreEquipo, Date fechaCreacion, int numeroJugadores,
                  String juegoPrincipal, String sedeEntrenamiento, String patrocinador) {
        this.idEquipo = idEquipo;
        this.nombreEquipo = nombreEquipo;
        this.fechaCreacion = fechaCreacion;
        this.numeroJugadores = numeroJugadores;
        this.juegoPrincipal = juegoPrincipal;
        this.sedeEntrenamiento = sedeEntrenamiento;
        this.patrocinador = patrocinador;
        this.jugadores = new ArrayList<>();
    }

    // Getters y Setters

    public String getIdEquipo() { return idEquipo; }
    public void setIdEquipo(String idEquipo) { this.idEquipo = idEquipo; }
    public String getNombreEquipo() { return nombreEquipo; }
    public void setNombreEquipo(String nombreEquipo) { this.nombreEquipo = nombreEquipo; }
    public Date getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(Date fechaCreacion) { this.fechaCreacion = fechaCreacion; }
    public int getNumeroJugadores() { return numeroJugadores; }
    public void setNumeroJugadores(int numeroJugadores) { this.numeroJugadores = numeroJugadores; }
    public String getJuegoPrincipal() { return juegoPrincipal; }
    public void setJuegoPrincipal(String juegoPrincipal) { this.juegoPrincipal = juegoPrincipal; }
    public String getSedeEntrenamiento() { return sedeEntrenamiento; }
    public void setSedeEntrenamiento(String sedeEntrenamiento) { this.sedeEntrenamiento = sedeEntrenamiento; }
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
                ", fechaCreacion=" + fechaCreacion +
                ", numeroJugadores=" + numeroJugadores +
                ", juegoPrincipal='" + juegoPrincipal + '\'' +
                ", sedeEntrenamiento='" + sedeEntrenamiento + '\'' +
                ", patrocinador='" + patrocinador + '\'' +
                '}';
    }
}