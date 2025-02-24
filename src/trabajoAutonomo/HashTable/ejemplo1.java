package trabajoAutonomo.HashTable;
import java.util.Hashtable;
public class ejemplo1 {

    public static void main(String[] args) {
        Hashtable<Integer, String> estudiantes = new Hashtable<>();

        estudiantes.put(1, "Carlos");
        estudiantes.put(2, "Ana");
        estudiantes.put(3, "Miguel");

        System.out.println("Lista de estudiantes: " + estudiantes);
    }
}

