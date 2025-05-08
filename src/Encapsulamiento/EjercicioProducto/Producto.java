package Encapsulamiento.EjercicioProducto;

public class Producto {
    // Atributos privados
    private String nombre;
    private double precio;
    // Constructor
    public Producto(String nombre, double precio) {
        this.nombre = nombre;
        setPrecio( precio);
    }
    // Getter para obtener el nombre
    public String getNombre() {
        return nombre;
    }
    // Setter para modificar el nombre
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    // Getter para obtener el salario
    public double getPrecio() {
        return precio;
    }
    // Setter para modificar el salario
    public void setPrecio(double precio) {
        if (precio > 0) {
            this.precio = precio;
        } else {
            System.out.println("El salario debe ser positivo.");
        }
    }
    //     Método para mostrar la información del empleado
    public void mostrarInfo() {
        System.out.println("Nombre: " + nombre);
        System.out.println("Salario: $" + precio);
    }
}
