package trabajoAutonomo.sumaArreglos;

public class ejemplo1 {
    public static void main(String[] args) {
        int[] numeros = {3, 5, 7, 2, 8};
        int suma = 0;
        for (int num : numeros) {
            suma += num;
        }
        System.out.println("La suma de los elementos del arreglo es: " + suma);
    }
}

