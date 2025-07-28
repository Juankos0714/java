package InterfacesFuncionales.Biconsumer;

import java.util.function.BiConsumer;

public class Ejercicio1 {
    public static void main(String[] args){
        BiConsumer<String, Boolean> printNotes = (name, note) -> System.out.println(name +" tiene como nota: "+note);
        printNotes.accept("Sebastian", 1.3);
        printNotes.accept("Nicolas", 3.3);
    }
}
