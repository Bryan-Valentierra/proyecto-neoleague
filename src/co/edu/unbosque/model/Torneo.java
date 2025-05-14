package co.edu.unbosque.model;

import java.util.Date;
import java.util.List;
import java.util.ArrayList; // Importar ArrayList

/**
 * Clase Torneo: Representa un torneo de E-Sports.
 * Contiene información sobre el torneo, como su nombre, fechas, tipo de juego, etc.
 */
public class Torneo {

    private String id; // Identificador único del torneo
    private String nombre; // Nombre del torneo
    private Date fechaInicio; // Fecha de inicio del torneo
    private Date fechaFin; // Fecha de fin del torneo
    private String tipoJuego; // Tipo de juego (ej. LoL, Dota 2)
    private String formato; // Formato del torneo (ej. Eliminación directa, Grupos)
    private List<Equipo> equipos; // Lista de equipos que participan en el torneo

    /**
     * Constructor de la clase Torneo.
     *
     * @param id          Identificador único del torneo.
     * @param nombre      Nombre del torneo.
     * @param fechaInicio Fecha de inicio del torneo.
     * @param fechaFin    Fecha de fin del torneo.
     * @param tipoJuego   Tipo de juego.
     * @param formato     Formato del torneo.
     */
    public Torneo(String id, String nombre, Date fechaInicio, Date fechaFin, String tipoJuego, String formato) {
        this.id = id;
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.tipoJuego = tipoJuego;
        this.formato = formato;
        this.equipos = new ArrayList<>(); // Inicializar la lista de equipos (importante)
    }

    // Getters y Setters

    /**
     * Obtiene el identificador único del torneo.
     *
     * @return El identificador del torneo.
     */
    public String getId() {
        return id;
    }

    /**
     * Establece el identificador único del torneo.
     *
     * @param id El identificador del torneo.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del torneo.
     *
     * @return El nombre del torneo.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del torneo.
     *
     * @param nombre El nombre del torneo.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la fecha de inicio del torneo.
     *
     * @return La fecha de inicio del torneo.
     */
    public Date getFechaInicio() {
        return fechaInicio;
    }

    /**
     * Establece la fecha de inicio del torneo.
     *
     * @param fechaInicio La fecha de inicio del torneo.
     */
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * Obtiene la fecha de fin del torneo.
     *
     * @return La fecha de fin del torneo.
     */
    public Date getFechaFin() {
        return fechaFin;
    }

    /**
     * Establece la fecha de fin del torneo.
     *
     * @param fechaFin La fecha de fin del torneo.
     */
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    /**
     * Obtiene el tipo de juego del torneo.
     *
     * @return El tipo de juego del torneo.
     */
    public String getTipoJuego() {
        return tipoJuego;
    }

    /**
     * Establece el tipo de juego del torneo.
     *
     * @param tipoJuego El tipo de juego del torneo.
     */
    public void setTipoJuego(String tipoJuego) {
        this.tipoJuego = tipoJuego;
    }

    /**
     * Obtiene el formato del torneo.
     *
     * @return El formato del torneo.
     */
    public String getFormato() {
        return formato;
    }

    /**
     * Establece el formato del torneo.
     *
     * @param formato El formato del torneo.
     */
    public void setFormato(String formato) {
        this.formato = formato;
    }

    /**
     * Obtiene la lista de equipos que participan en el torneo.
     *
     * @return La lista de equipos.
     */
    public List<Equipo> getEquipos() {
        return equipos;
    }


    public void setEquipos(List<Equipo> equipos) {
        this.equipos = equipos;
    }

    public void agregarEquipo(Equipo equipo) {
        this.equipos.add(equipo);
    }

    public void eliminarEquipo(Equipo equipo) {
        this.equipos.remove(equipo);
    }

    @Override
    public String toString() {
        return "Torneo{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", tipoJuego='" + tipoJuego + '\'' +
                ", formato='" + formato + '\'' +
                ", equipos=" + equipos +
                '}';
    }

    // Métodos adicionales (según los requerimientos)

    public int getNumeroEquiposInscritos() {
        return equipos != null ? equipos.size() : 0;
    }


    public boolean estaInscrito(Equipo equipo) {
        return equipos != null && equipos.contains(equipo);
    }

    // Puedes agregar más métodos según las necesidades de tu aplicación
    // Por ejemplo:
    // - obtenerEquiposPorFase()
    // - calcularNumeroRondas()
    // - generarEmparejamientos() (aunque esta lógica podría estar en un controlador o clase de servicio)
}