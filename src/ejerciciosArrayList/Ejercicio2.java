package ejerciciosArrayList;

import java.util.ArrayList;

public class Ejercicio2 {
    public static void main(String[] args){
        int suma = 0;
        ArrayList<Integer> num = new ArrayList<>();
        num.add(1);
        num.add(2);
        num.add(3);
        num.add(4);
        num.add(5);
        for(int numero : num){
            suma+=numero;
        }
        System.out.println("El valor de la suma del array es "+suma
        );

    }
}
