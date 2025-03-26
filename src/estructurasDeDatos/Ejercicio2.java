package estructurasDeDatos;
//Escribe un programa que reciba un array de números enteros y encuentre el número máximo en el array.
public class Ejercicio2 {
    public static void main(String[] args){
        int max=0;
        int[] numbers = {1,2,3,4,5};

        for (int i=0; i < numbers.length;i++){
            System.out.println(numbers[i]);
            if(numbers[i]>max) {
                max = numbers[i];
            }

        }
        System.out.println("El valor mayo del array es "+max);

    }
}
