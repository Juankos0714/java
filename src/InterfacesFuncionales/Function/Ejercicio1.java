package InterfacesFuncionales.Function;

import java.util.ArrayList;
import java.util.function.Function;
import java.util.Collections;

public class Ejercicio1 {
    public static void main(String[] args) {
        Function<ArrayList<Integer>, ArrayList<Integer>> ordenar = list -> {
            Collections.sort(list);
            return list;
        };

        ArrayList<Integer> numeros = new ArrayList<>();

        for (int i =0; i<5;i++){
            numeros.add(Supplierexample.randomNumSupplier.get());
        }


        System.out.println("Array original: " + numeros);

        ArrayList<Integer> numerosOrdenados = ordenar.apply(numeros);

        System.out.println("Array ordenado: " + numerosOrdenados);

    }
}
