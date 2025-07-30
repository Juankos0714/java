package InterfacesFuncionales.Supplier;
import java.util.UUID;
import java.util.function.Supplier;
public class Ejercicio1 {
    //generador de tokens

        public static final Supplier<String> tokenSupplier = () -> UUID.randomUUID().toString();





}
