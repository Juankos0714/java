package InterfacesFuncionales.Predicate;

import java.util.function.Predicate;
import static InterfacesFuncionales.Supplier.Ejercicio1.tokenSupplier;

public class Ejercicio1 {

    private static final String TRUE_GLOBAL_TOKEN = tokenSupplier.get();

    public static void main(String[] args) {

        System.out.println("Este es el TRUE_GLOBAL_TOKEN al iniciar: " + TRUE_GLOBAL_TOKEN);

        Predicate<String> isValidToken = token -> token != null && token.equals(TRUE_GLOBAL_TOKEN);

        String correctTokenForTest = TRUE_GLOBAL_TOKEN;
        System.out.println("\nIntentando con el token correcto: " + correctTokenForTest);
        if (isValidToken.test(correctTokenForTest)) {
            System.out.println("Ingreso valido");
        } else {
            System.out.println("Error");
        }

        String incorrectTokenForTest = tokenSupplier.get();
        System.out.println("\nIntentando con un token incorrecto (generado nuevo): " + incorrectTokenForTest);
        if (isValidToken.test(incorrectTokenForTest)) {
            System.out.println("Ingreso valido");
        } else {
            System.out.println("Error");
        }

        String manuallyWrongToken = "wefwef-werf-wefwe-fw-fwe-fwe";
        System.out.println("\nIntentando con un token incorrecto (ingresado de manera manual): " + manuallyWrongToken);
        if (isValidToken.test(manuallyWrongToken)) {
            System.out.println("Ingreso valido");
        } else {
            System.out.println("Error");
        }
    }

    public static Predicate<String> getIsValidTokenPredicate() {
        return token -> token != null && token.equals(TRUE_GLOBAL_TOKEN);
    }

    public static String getTrueGlobalToken() {
        return TRUE_GLOBAL_TOKEN;
    }
}
