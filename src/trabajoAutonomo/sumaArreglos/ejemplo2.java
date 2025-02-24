package trabajoAutonomo.sumaArreglos;
import java.util.Scanner;
public class ejemplo2 {
    public static void main(String[] args) {
        int[] arreglo1 = {1, 2, 3, 4, 5};
        int[] arreglo2 = {5, 4, 3, 2, 1};
        int[] resultado = new int[arreglo1.length];

        for (int i = 0; i < arreglo1.length; i++) {
            resultado[i] = arreglo1[i] + arreglo2[i];
        }

        System.out.println("Suma de los arreglos:");
        for (int num : resultado) {
            System.out.print(num + " ");
        }
    }
}


