package trabajoAutonomo.doWhile;
import java.util.Scanner;
public class ejemplo1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numero;

        do {
            System.out.print("Ingresa un número positivo: ");
            numero = scanner.nextInt();
        } while (numero < 0);

        System.out.println("Número válido ingresado: " + numero);
        scanner.close();
    }
}

