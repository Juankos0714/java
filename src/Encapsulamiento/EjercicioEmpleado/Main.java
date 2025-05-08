package Encapsulamiento.EjercicioEmpleado;

public class Main {
    public static void main(String[] args) {
        // Crear un objeto de la clase Empleado
        Empleado empleado1 = new Empleado("Juan Pérez", 3000.0);
        // Mostrar la información del empleado
        empleado1.mostrarInfo();
        // Cambiar el nombre y salario usando setters
        empleado1.setNombre("Carlos Ruiz");
        empleado1.setSalario(3500.0);
        // Intentar establecer un salario negativo
        empleado1.setSalario(-1000.0); // Mostrará mensaje de error
        // Mostrar la información actualizada del empleado
        empleado1.mostrarInfo();
    }
}

