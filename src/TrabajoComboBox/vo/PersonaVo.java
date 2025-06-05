package TrabajoComboBox.vo;

public class PersonaVo {
    private String documento;
    private String nombre;
    private int edad;
    private String direccion;

    public PersonaVo() {
        this.documento = "";
        this.nombre = "";
        this.edad = 0;
        this.direccion = "";
    }

    public PersonaVo(String documento, String nombre, int edad) {
        this.documento = documento != null ? documento : "";
        this.nombre = nombre != null ? nombre : "";
        this.edad = edad;
        this.direccion = "";
    }

    public PersonaVo(String documento, String nombre, int edad, String direccion) {
        this.documento = documento != null ? documento : "";
        this.nombre = nombre != null ? nombre : "";
        this.edad = edad;
        this.direccion = direccion != null ? direccion : "";
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion != null ? direccion : "";
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento != null ? documento : "";
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre != null ? nombre : "";
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        if (documento == null || documento.isEmpty()) {
            return nombre != null ? nombre : "";
        }
        return (nombre != null ? nombre : "") + " - " + documento;
    }
}

