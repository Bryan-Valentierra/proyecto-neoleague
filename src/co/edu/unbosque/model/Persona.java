package co.edu.unbosque.model;

public class Persona {
    private String id;
    private String nombre;
    private String apellido;
    private String fechaNacimiento;
    private String nacionalidad;
    private String correo;

    // Constructor vacío de la clase Persona.
    public Persona() {
    }

    // Constructor con todos los atributos de la clase Persona.
    public Persona(String id, String nombre, String apellido, String fechaNacimiento, String nacionalidad, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidad = nacionalidad;
        this.correo = correo;
    }

    // Devuelve el ID de la persona.
    public String getId() {
        return id;
    }

    // Establece el ID de la persona.
    public void setId(String id) {
        this.id = id;
    }

    // Devuelve el nombre de la persona.
    public String getNombre() {
        return nombre;
    }

    // Establece el nombre de la persona.
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Devuelve el apellido de la persona.
    public String getApellido() {
        return apellido;
    }

    // Establece el apellido de la persona.
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    // Devuelve la fecha de nacimiento de la persona.
    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    // Establece la fecha de nacimiento de la persona.
    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    // Devuelve la nacionalidad de la persona.
    public String getNacionalidad() {
        return nacionalidad;
    }

    // Establece la nacionalidad de la persona.
    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    // Devuelve el correo electrónico de la persona.
    public String getCorreo() {
        return correo;
    }

    // Establece el correo electrónico de la persona.
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    // Devuelve una representación en cadena del objeto Persona.
    @Override
    public String toString() {
        return "Persona{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", fechaNacimiento='" + fechaNacimiento + '\'' +
                ", nacionalidad='" + nacionalidad + '\'' +
                ", correo='" + correo + '\'' +
                '}';
    }
}