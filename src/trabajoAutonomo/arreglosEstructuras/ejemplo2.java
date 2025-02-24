package trabajoAutonomo.arreglosEstructuras;

public class ejemplo2 {
    public static void main(String[] args) {
        int[] numeros = {12, 45, 7, 89, 23, 56};
        int mayor = numeros[0]; // Se asume que el primero es el mayor

        for (int i = 1; i < numeros.length; i++) {
            if (numeros[i] > mayor) {
                mayor = numeros[i];
            }
        }

        System.out.println("El número mayor es: " + mayor);
    }
}

