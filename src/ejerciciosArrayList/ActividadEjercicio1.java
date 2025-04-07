package ejerciciosArrayList;

import java.util.ArrayList;
//Crear un ArrayList de cadenas y contar las palabras

public class ActividadEjercicio1 {
    public static void main(String[] args){
        int inc = 0;
        ArrayList<String> colors = new ArrayList<>();
        colors.add("rojo");
        colors.add("azul");
        colors.add("verde");
        colors.add("amarillo");
        colors.add("negro");

        for(String col : colors){
            inc++;


        }
        System.out.println("La cantidad de elementos en la lista es de "+inc);
    }
}
