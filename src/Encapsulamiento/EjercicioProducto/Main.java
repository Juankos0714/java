package Encapsulamiento.EjercicioProducto;

import Encapsulamiento.EjercicioEmpleado.Empleado;

public class Main {
    public static void main(String[] args) {
        // Crear un objeto de la clase Empleado
        Producto producto1 = new Producto("Pan tajado", 3000.0);
        // Mostrar la información del empleado
        producto1.mostrarInfo();
        // Cambiar el nombre y salario usando setters
        producto1.setNombre("Carlos Ruiz");
        producto1.setPrecio(3500.0);
        // Intentar establecer un salario negativo
        producto1.setPrecio(-1000.0); // Mostrará mensaje de error
        // Mostrar la información actualizada del empleado
        producto1.mostrarInfo();
    }
}
