package ejerciciosArrayList;

import java.util.ArrayList;

public class ActividadEjercicio4 {
    public static void main(String[] args){
        int inc = 0;
        ArrayList<String> pets = new ArrayList<>();
        pets.add("perro");
        pets.add("gato");
        pets.add("pajaro");
        pets.add("pez");
        pets.add("raton");

        for(String pet : pets){

            if(pet=="pajaro"){

                pets.set(inc,"hamster");
            }
            inc++;


        }
        System.out.println(pets);
    }
}
