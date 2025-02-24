package trabajoAutonomo.HashMap;
import java.util.HashMap;
import java.util.Map;
public class ejemplo3 {

        public static void main(String[] args) {
            HashMap<String, String> capitales = new HashMap<>();

            capitales.put("Colombia", "Bogotá");
            capitales.put("España", "Madrid");
            capitales.put("Argentina", "Buenos Aires");

            for (Map.Entry<String, String> entrada : capitales.entrySet()) {
                System.out.println("La capital de " + entrada.getKey() + " es " + entrada.getValue());
            }
        }
    }

