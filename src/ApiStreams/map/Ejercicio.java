package ApiStreams.map;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Double.parseDouble;

public class Ejercicio {
    public static void main (String[] args){
        List<String> numbers = Arrays.asList("1","2");
        List<Double> intNumbers = numbers.stream()
                .map(x -> parseDouble(x))
                .collect(Collectors.toList());
        System.out.println(intNumbers);
    }
}
