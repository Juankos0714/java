package ejerciciosArrayList;

import java.util.ArrayList;

public class Ejercicio3 {
    public static void main(String[] args){
        int inc = 0;
        ArrayList<Integer> num = new ArrayList<>();
        num.add(3);
        num.add(8);
        num.add(1);
        num.add(5);
        num.add(12);
        int mayor = num.get(inc);
        for(int numero : num){
            mayor = num.get(inc);
            inc++;
            if(mayor>numero){
                numero=mayor;



            }

        }
        System.out.println("El valor mayor del array es "+mayor);
    }
}