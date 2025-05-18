package co.edu.unbosque.model;

public class Entrenador extends Persona {
    private String experiencia;
    private String equipoAsignado; 

    public Entrenador() {
    }
    //Constructor
    public Entrenador(String id, String nombre, String apellido, String fechaNacimiento, String nacionalidad, String correo, String experiencia, String equipoAsignado) {
        super(id, nombre, apellido, fechaNacimiento, nacionalidad, correo);
        this.experiencia = experiencia;
        this.equipoAsignado = equipoAsignado;
    }

    // Getters y Setters
    public String getExperiencia() { return experiencia; }
    public void setExperiencia(String experiencia) { this.experiencia = experiencia; }
    public String getEquipoAsignado() { return equipoAsignado; }
    public void setEquipoAsignado(String equipoAsignado) { this.equipoAsignado = equipoAsignado; }

    @Override
    public String toString() {
        return "Entrenador{" +
                "id='" + getId() + '\'' +
                ", nombre='" + getNombre() + '\'' +
                ", apellido='" + getApellido() + '\'' +
                ", experiencia='" + experiencia + '\'' +
                ", equipoAsignado='" + equipoAsignado + '\'' +
                '}';
    }
}