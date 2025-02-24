package trabajoAutonomo.arrayList;
import java.util.ArrayList;
public class ejemplo1 {
    public static void main(String[] args) {
        ArrayList<Integer> numeros = new ArrayList<>();
        numeros.add(3);
        numeros.add(4);
        numeros.add(7);
        numeros.add(2);
        numeros.add(1);

        int suma = 0;

        for (int num : numeros) {
            suma += num;
        }

        System.out.println("La suma de los elementos del ArrayList es: " + suma);
    }
}

