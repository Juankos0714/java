package ejerciciosArrayList;

import java.util.ArrayList;

public class Ejercicio4 {
    public static void main(String[] args){
        int inc = 0;
        ArrayList<String> palabras = new ArrayList<>();
        palabras.add("uno");
        palabras.add("dos");
        palabras.add("tres");
        palabras.add("cuatro");
        palabras.add("cinco");
        int tamaño =palabras.size();

        for(int i =0;i < tamaño/2; i++){
            String temp = palabras.get(i);
            int posO = tamaño -1 -i;
            palabras.set(i, palabras.get(posO));
            palabras.set(posO,temp);

        }
        System.out.println(palabras);
    }
}