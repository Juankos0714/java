package trabajoAutonomo.estructurasIterativasWhile;
import java.util.Scanner;
public class ejemplos2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = "";

        while (!input.equalsIgnoreCase("salir")) {
            System.out.print("Escribe algo (o 'salir' para terminar): ");
            input = scanner.nextLine();
            System.out.println("Ingresaste: " + input);
        }

        System.out.println("Programa terminado.");
        scanner.close();
    }
}

