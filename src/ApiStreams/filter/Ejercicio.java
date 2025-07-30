package ApiStreams.filter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Locale.filter;

public class Ejercicio {
    public static void main (String[] args){
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        List<Integer> impar = numbers.stream()
            .filter(n -> n % 2 ==1)
            .toList();
        System.out.println("Numeros impares: "+impar);

    }
}
