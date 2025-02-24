package trabajoAutonomo.HashTable;
import java.util.Hashtable;
import java.util.Enumeration;
public class ejemplo3 {

        public static void main(String[] args) {
            Hashtable<String, String> capitales = new Hashtable<>();

            capitales.put("Colombia", "Bogotá");
            capitales.put("España", "Madrid");
            capitales.put("Argentina", "Buenos Aires");

            Enumeration<String> claves = capitales.keys();
            while (claves.hasMoreElements()) {
                String clave = claves.nextElement();
                System.out.println("La capital de " + clave + " es " + capitales.get(clave));
            }
        }
    }

