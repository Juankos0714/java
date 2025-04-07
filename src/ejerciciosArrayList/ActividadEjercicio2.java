package ejerciciosArrayList;

import java.util.ArrayList;

public class ActividadEjercicio2 {

        public static void main(String[] args){
            int inc=0;
            String msj ="";
            ArrayList<Integer> num = new ArrayList<>();
            num.add(10);
            num.add(20);
            num.add(30);
            num.add(40);
            num.add(50);
            int busc = 30;
            for(int numero : num){

                if(busc==numero){
                    inc++;
                }

            }
            if(inc==1){
                msj="Encontrado";
            }else{
                msj="No encontrado";
            }
            System.out.println(msj);
        }

}
