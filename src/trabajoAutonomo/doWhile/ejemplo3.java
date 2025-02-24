package trabajoAutonomo.doWhile;
import java.util.Scanner;
public class ejemplo3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\nMenú:");
            System.out.println("1. Ver productos");
            System.out.println("2. Hacer pedido");
            System.out.println("3. Salir");
            System.out.print("Elige una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("Mostrando productos...");
                    break;
                case 2:
                    System.out.println("Realizando pedido...");
                    break;
                case 3:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción inválida, intenta de nuevo.");
            }
        } while (opcion != 3);

        scanner.close();
    }
}

