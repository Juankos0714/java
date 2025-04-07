package ejerciciosArrayList;
import java.util.ArrayList;
public class Ejercicio5 {
    public static void main(String[] args) {
// 1. Crear un ArrayList de cadenas
        ArrayList<String> frutas = new ArrayList<>();
// 2. Añadir las palabras "manzana", "banana", "cereza", "datil", y "uva" al ArrayLt
        frutas.add("manzana");
        frutas.add("banana");
        frutas.add("cereza");
        frutas.add("datil");
        frutas.add("uva");
// 3. Eliminar la palabra "cereza" del ArrayList
        frutas.remove("cereza");
// 4. Imprimir los elementos restantes del ArrayList
        for (String fruta : frutas) {
            System.out.println(fruta);
        }
    }
}
