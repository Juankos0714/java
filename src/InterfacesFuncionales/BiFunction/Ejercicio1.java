package InterfacesFuncionales.BiFunction;
import java.util.function.BiFunction;

import static InterfacesFuncionales.Supplier.Ejercicio1.tokenSupplier;

public class Ejercicio1 {

    public static void main(String[] args) {

        BiFunction<String, String, String> generateUserToken = (username, password) -> {
            String token = tokenSupplier.get();
            return "Usuario: " + username + ", Contraseña : " + password + ", Token de Ingreso: " + token;
        };

        String userDataWithToken = generateUserToken.apply("camilo", "1234567890");
        System.out.println(userDataWithToken);


    }
}

