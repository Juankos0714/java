package estructurasDeDatos;
//Escribe un programa que reciba un array de números enteros y calcule la suma de todos sus elementos.
public class Ejercicio1 {
    public static void main(String[] args){
        int suma=0;
        int[] numbers = {1,2,3,4,5};

        for (int i=0; i < numbers.length;i++){
            System.out.println(numbers[i]);
            suma+=numbers[i];

        }
        System.out.println(suma);

    }
}
