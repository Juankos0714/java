package InterfacesFuncionales.Function;
import java.util.function.Supplier;
public class Supplierexample {

    public static final Supplier<Integer> randomNumSupplier = () -> (int) (Math.random() * 100);
    int randomNum = randomNumSupplier.get();



}
