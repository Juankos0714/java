package ejerciciosArrayList;

import java.util.ArrayList;

public class ActividadEjercicio3 {
    public static void main(String[] args){
        int sum=0;
        String msj ="";
        int inc=0;
        ArrayList<Integer> num = new ArrayList<>();
        num.add(5);
        num.add(10);
        num.add(15);
        num.add(20);
        num.add(25);

        for(int numero : num) {
            sum+=numero;
            inc++;

        }
        int prom = sum/inc;
        System.out.println("El promedio del arrayList es de "+prom);
    }
}
