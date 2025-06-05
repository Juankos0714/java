package TrabajoComboBox.vo;

public class PersonaVo {
    private String documento;
    private String nombre;
    private int edad;
    private String direccion;

    public PersonaVo() {
    }

    public PersonaVo(String s, String s1, int i) {
    }


    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public PersonaVo(String documento, String nombre, int edad, String direccion) {
        this.documento = documento;
        this.nombre = nombre;
        this.edad = edad;
        this.direccion= direccion;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        if (documento.isEmpty()) {
            return nombre;
        }
        return nombre + " - " + documento;
    }
}

