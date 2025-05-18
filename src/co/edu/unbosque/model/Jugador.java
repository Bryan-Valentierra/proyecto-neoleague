package co.edu.unbosque.model;

import java.util.Date;
import java.util.List;

public class Jugador {

    private String id;
    private String nombre;
    private String apellido;
    private Date fechaNacimiento;
    private String nacionalidad;
    private String correo;
    private List<String> juegosParticipados;
    private int victorias;
    private int derrotas;
    private String record;
    private String juegoFavorito;
    private String rolPrincipal;
    private String estadisticas;

    // Constructor vacío de la clase Jugador.
    public Jugador() {
    }

    // Constructor con todos los atributos de la clase Jugador.
    public Jugador(String id, String nombre, String apellido, Date fechaNacimiento, String nacionalidad, String correo, List<String> juegosParticipados, int victorias, int derrotas, String record, String juegoFavorito, String rolPrincipal, String estadisticas) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidad = nacionalidad;
        this.correo = correo;
        this.juegosParticipados = juegosParticipados;
        this.victorias = victorias;
        this.derrotas = derrotas;
        this.record = record;
        this.juegoFavorito = juegoFavorito;
        this.rolPrincipal = rolPrincipal;
        this.estadisticas = estadisticas;
    }

    // Devuelve el ID del jugador.
    public String getId() {
        return id;
    }

    // Establece el ID del jugador.
    public void setId(String id) {
        this.id = id;
    }

    // Devuelve el nombre del jugador.
    public String getNombre() {
        return nombre;
    }

    // Establece el nombre del jugador.
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Devuelve el apellido del jugador.
    public String getApellido() {
        return apellido;
    }

    // Establece el apellido del jugador.
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    // Devuelve la fecha de nacimiento del jugador.
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    // Establece la fecha de nacimiento del jugador.
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    // Devuelve la nacionalidad del jugador.
    public String getNacionalidad() {
        return nacionalidad;
    }

    // Establece la nacionalidad del jugador.
    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    // Devuelve el correo electrónico del jugador.
    public String getCorreo() {
        return correo;
    }

    // Establece el correo electrónico del jugador.
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    // Devuelve la lista de juegos en los que ha participado el jugador.
    public List<String> getJuegosParticipados() {
        return juegosParticipados;
    }

    // Establece la lista de juegos en los que ha participado el jugador.
    public void setJuegosParticipados(List<String> juegosParticipados) {
        this.juegosParticipados = juegosParticipados;
    }

    // Devuelve el número de victorias del jugador.
    public int getVictorias() {
        return victorias;
    }

    // Establece el número de victorias del jugador.
    public void setVictorias(int victorias) {
        this.victorias = victorias;
    }

    // Devuelve el número de derrotas del jugador.
    public int getDerrotas() {
        return derrotas;
    }

    // Establece el número de derrotas del jugador.
    public void setDerrotas(int derrotas) {
        this.derrotas = derrotas;
    }

    // Devuelve el récord del jugador.
    public String getRecord() {
        return record;
    }

    // Establece el récord del jugador.
    public void setRecord(String record) {
        this.record = record;
    }

    // Devuelve el juego favorito del jugador.
    public String getJuegoFavorito() {
        return juegoFavorito;
    }

    // Establece el juego favorito del jugador.
    public void setJuegoFavorito(String juegoFavorito) {
        this.juegoFavorito = juegoFavorito;
    }

    // Devuelve el rol principal del jugador.
    public String getRolPrincipal() {
        return rolPrincipal;
    }

    // Establece el rol principal del jugador.
    public void setRolPrincipal(String rolPrincipal) {
        this.rolPrincipal = rolPrincipal;
    }

    // Devuelve las estadísticas del jugador.
    public String getEstadisticas() {
        return estadisticas;
    }

    // Establece las estadísticas del jugador.
    public void setEstadisticas(String estadisticas) {
        this.estadisticas = estadisticas;
    }

    // Devuelve una representación en cadena del objeto Jugador.
    @Override
    public String toString() {
        return "Jugador{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", nacionalidad='" + nacionalidad + '\'' +
                ", correo='" + correo + '\'' +
                ", juegosParticipados=" + juegosParticipados +
                ", victorias=" + victorias +
                ", derrotas=" + derrotas +
                ", record='" + record + '\'' +
                ", juegoFavorito='" + juegoFavorito + '\'' +
                ", rolPrincipal='" + rolPrincipal + '\'' +
                ", estadisticas='" + estadisticas + '\'' +
                '}';
    }
}