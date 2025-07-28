package InterfacesFuncionales.Consumer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Ejercicio1 {
    public static void  main(String[] args){
        List<String> mercado = new ArrayList<>();
        mercado.add("Arroz");
        mercado.add("lentejas");
        mercado.add("Leche");
        mercado.add("Huevo");

        Consumer<String> consumer = producto -> System.out.println("Comprar "+mercado+"!");
        mercado.forEach(consumer);

    }
}
