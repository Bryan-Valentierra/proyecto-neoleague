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

    public Jugador() {
    }

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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public List<String> getJuegosParticipados() {
        return juegosParticipados;
    }

    public void setJuegosParticipados(List<String> juegosParticipados) {
        this.juegosParticipados = juegosParticipados;
    }

    public int getVictorias() {
        return victorias;
    }

    public void setVictorias(int victorias) {
        this.victorias = victorias;
    }

    public int getDerrotas() {
        return derrotas;
    }

    public void setDerrotas(int derrotas) {
        this.derrotas = derrotas;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public String getJuegoFavorito() {
        return juegoFavorito;
    }

    public void setJuegoFavorito(String juegoFavorito) {
        this.juegoFavorito = juegoFavorito;
    }

    public String getRolPrincipal() {
        return rolPrincipal;
    }

    public void setRolPrincipal(String rolPrincipal) {
        this.rolPrincipal = rolPrincipal;
    }

    public String getEstadisticas() {
        return estadisticas;
    }

    public void setEstadisticas(String estadisticas) {
        this.estadisticas = estadisticas;
    }

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