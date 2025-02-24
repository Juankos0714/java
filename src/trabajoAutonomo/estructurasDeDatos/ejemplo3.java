package trabajoAutonomo.estructurasDeDatos;

public class ejemplo3 {
    public static void main(String[] args) {
        int[] numeros = {10, 20, 30, 40, 50};
        int resta = 0;

        for (int i = 0; i < numeros.length; i++) {
            resta -= numeros[i];
        }

        System.out.println("La resta de los números es: " + resta);
    }
}
