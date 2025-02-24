package trabajoAutonomo.arreglosEstructuras;
import java.util.Scanner;
public class ejemplo3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] nombres = new String[3];

        for(int i = 0; i < nombres.length; i++) {
            System.out.print("Ingrese un nombre: ");
            nombres[i] = sc.nextLine();
        }

        System.out.println("\nNombres ingresados:");
        for(String nombre : nombres) {
            System.out.println(nombre);
        }

        sc.close();
    }
}

