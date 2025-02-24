package trabajoAutonomo.sumaArreglos;
import java.util.Scanner;
public class ejemplo3 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese el tamaño de los arreglos: ");
        int n = sc.nextInt();

        int[] arreglo1 = new int[n];
        int[] arreglo2 = new int[n];
        int[] resultado = new int[n];

        System.out.println("Ingrese los valores del primer arreglo:");
        for (int i = 0; i < n; i++) {
            System.out.print("Elemento " + (i+1) + ": ");
            arreglo1[i] = sc.nextInt();
        }

        System.out.println("Ingrese los valores del segundo arreglo:");
        for (int i = 0; i < n; i++) {
            System.out.print("Elemento " + (i+1) + ": ");
            arreglo2[i] = sc.nextInt();
        }

        for (int i = 0; i < n; i++) {
            resultado[i] = arreglo1[i] + arreglo2[i];
        }

        System.out.println("Resultado de la suma de los arreglos:");
        for (int num : resultado) {
            System.out.print(num + " ");
        }

        sc.close();
    }
}
