package co.edu.unbosque.model;

import java.io.Serializable;

public class Emparejamiento implements Serializable {

    private static final long serialVersionUID = 1L;

    private String idEmparejamiento;
    private String idTorneo;
    private String equipo1;
    private String equipo2;
    private String fecha;
    private String hora;
    private String ronda;
    private String estado;
    private String ganador;
    private String perdedor;

    // Constructor que acepta todos los parámetros
    public Emparejamiento(String idEmparejamiento, String idTorneo, String equipo1, String equipo2,
                         String fecha, String hora, String ronda, String estado, String ganador, String perdedor) {
        this.idEmparejamiento = idEmparejamiento;
        this.idTorneo = idTorneo;
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
        this.fecha = fecha;
        this.hora = hora;
        this.ronda = ronda;
        this.estado = estado;
        this.ganador = ganador;
        this.perdedor = perdedor;
    }

    public Emparejamiento() {
    }

    public String getIdEmparejamiento() {
        return idEmparejamiento;
    }

    public void setIdEmparejamiento(String idEmparejamiento) {
        this.idEmparejamiento = idEmparejamiento;
    }

    public String getIdTorneo() {
        return idTorneo;
    }

    public void setIdTorneo(String idTorneo) {
        this.idTorneo = idTorneo;
    }

    public String getEquipo1() {
        return equipo1;
    }

    public void setEquipo1(String equipo1) {
        this.equipo1 = equipo1;
    }

    public String getEquipo2() {
        return equipo2;
    }

    public void setEquipo2(String equipo2) {
        this.equipo2 = equipo2;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getRonda() {
        return ronda;
    }

    public void setRonda(String ronda) {
        this.ronda = ronda;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getGanador() {
        return ganador;
    }

    public void setGanador(String ganador) {
        this.ganador = ganador;
    }

    public String getPerdedor() {
        return perdedor;
    }

    public void setPerdedor(String perdedor) {
        this.perdedor = perdedor;
    }

    @Override
    public String toString() {
        return "Emparejamiento{" +
                "idEmparejamiento='" + idEmparejamiento + '\'' +
                ", idTorneo='" + idTorneo + '\'' +
                ", equipo1='" + equipo1 + '\'' +
                ", equipo2='" + equipo2 + '\'' +
                ", fecha='" + fecha + '\'' +
                ", hora='" + hora + '\'' +
                ", ronda='" + ronda + '\'' +
                ", estado='" + estado + '\'' +
                ", ganador='" + ganador + '\'' +
                ", perdedor='" + perdedor + '\'' +
                '}';
    }
}
