package trabajoAutonomo.HashTable;
import java.util.Hashtable;
public class ejemplo2 {

    public static void main(String[] args) {
        Hashtable<String, Integer> edades = new Hashtable<>();

        edades.put("Carlos", 25);
        edades.put("Ana", 30);
        edades.put("Miguel", 22);

        System.out.println("La edad de Ana es: " + edades.get("Ana"));
    }
}

