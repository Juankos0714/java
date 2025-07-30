package ApiStreams.sorted;

import java.util.Arrays;
import java.util.List;

public class Ejercicio {
    public static void main(String[] args) {
        List<Double> numbers = Arrays.asList(1.2, 5.4, 5.3, 2.1, 4.2);
        List<Double> sortedDoubles = numbers.stream()
                .sorted()
                .toList();
        System.out.println(sortedDoubles);

    }
}
