package Model;

public class Persona {
    private String nombre;
    private String apellido;
    private String barrio;
    private String dni;
    private String ocupacion;
    private Integer edad;
    private Reactivo kit;

    public Persona(String nombre, String apellido, String barrio, String dni, Integer edad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.barrio = barrio;
        this.dni = dni;
        this.ocupacion = null;
        this.edad = edad;
        this.kit = new Reactivo();
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public Integer getEdad() {
        return edad;
    }

    public String getBarrio() {
        return barrio;
    }

    public String getDni() {
        return dni;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    public Reactivo getKit() {
        return kit;
    }

    public void setKit(Reactivo kit) {
        this.kit = kit;
    }
}
