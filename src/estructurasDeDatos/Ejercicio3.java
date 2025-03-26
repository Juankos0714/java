package estructurasDeDatos;

import java.util.Arrays;

public class Ejercicio3 {
    public static void main(String[] args){

        int[] numbers = {1,2,3,4,5};
        int[] newNumbers =new int [numbers.length];
        int a = 0,b=0;


        

        for (int i=0; i < numbers.length;i++){
            System.out.println(numbers[i]);
            if(i > numbers.length) {
                a = numbers[i];
                b = numbers[i++];
            }if(a<b) {
                newNumbers=moverElemento(numbers,1,2);
            }


        }
        System.out.println(Arrays.toString(newNumbers));

    }
    public static int[] moverElemento(int[] numbers, int posActual, int posNueva){
        int[] newNumbers =new int [numbers.length];
        for(int i = 0; i >numbers.length; i++) {
            if (i < posActual) {
                newNumbers[i]=numbers[i];
            }else if(i > posActual) {
                newNumbers [i-1]=numbers[i];
            }
        }
        newNumbers[posNueva]=numbers[posActual];
        return newNumbers;
    }
}
