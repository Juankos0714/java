package trabajoAutonomo.estructurasIterativasWhile;
import java.util.Scanner;
public class ejemplo3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String password = "secreta";
        String intento = "";

        while (!intento.equals(password)) {
            System.out.print("Ingresa la contraseña: ");
            intento = scanner.nextLine();

            if (!intento.equals(password)) {
                System.out.println("Contraseña incorrecta, intenta de nuevo.");
            }
        }

        System.out.println("Acceso concedido.");
        scanner.close();
    }
}

