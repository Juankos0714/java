package trabajoAutonomo.doWhile;
import java.util.Scanner;
public class ejemplo2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String usuarioCorrecto = "admin";
        String passwordCorrecta = "1234";
        String usuario, password;

        do {
            System.out.print("Usuario: ");
            usuario = scanner.nextLine();
            System.out.print("Contraseña: ");
            password = scanner.nextLine();

            if (!usuario.equals(usuarioCorrecto) || !password.equals(passwordCorrecta)) {
                System.out.println("Credenciales incorrectas, intenta de nuevo.");
            }
        } while (!usuario.equals(usuarioCorrecto) || !password.equals(passwordCorrecta));

        System.out.println("Acceso concedido.");
        scanner.close();
    }
}

