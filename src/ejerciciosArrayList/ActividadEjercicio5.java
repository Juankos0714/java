package ejerciciosArrayList;

import java.util.ArrayList;

public class ActividadEjercicio5 {
    public static void main(String[] args){

        ArrayList<Integer> num = new ArrayList<>();
        for(int cant =0;cant<11;cant++) {
            num.add(cant);


        }



        System.out.println("La sublista se veria asi "+num.subList(4,8));
    }
}
