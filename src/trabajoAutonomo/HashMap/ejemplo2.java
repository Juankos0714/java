package trabajoAutonomo.HashMap;
import java.util.HashMap;
public class ejemplo2 {

        public static void main(String[] args) {
            HashMap<String, Integer> edades = new HashMap<>();

            edades.put("Carlos", 25);
            edades.put("Ana", 30);
            edades.put("Miguel", 22);

            System.out.println("Edad de Ana: " + edades.get("Ana"));
        }
    }

