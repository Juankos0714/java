package Encapsulamiento.EjercicioEmpleado;

public class Empleado {
    // Atributos privados
    private String nombre;
    private double salario;
    // Constructor
    public Empleado(String nombre, double salario) {
        this.nombre = nombre;
        setSalario( salario);
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
    public double getSalario() {
        return salario;
    }
    // Setter para modificar el salario
    public void setSalario(double salario) {
        if (salario > 0) {
            this.salario = salario;
        } else {
            System.out.println("El salario debe ser positivo.");
        }
    }
//     Método para mostrar la información del empleado
    public void mostrarInfo() {
        System.out.println("Nombre: " + nombre);
        System.out.println("Salario: $" + salario);
    }
}



