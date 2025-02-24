package trabajoAutonomo.arreglosEstructuras;

public class ejemplo1 {
    public static void main(String[] args) {
        int[] numeros = {1, 2, 3, 4, 5};
        System.out.println("Arreglo original:");

        for (int num : numeros) {
            System.out.print(num + " ");
        }
        System.out.println("\nArreglo invertido:");
        for (int i = numeros.length - 1; i >= 0; i--) {
            System.out.print(numeros[i] + " ");
        }
    }
}

