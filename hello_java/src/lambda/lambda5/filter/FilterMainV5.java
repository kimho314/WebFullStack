package lambda.lambda5.filter;

import java.util.List;

public class FilterMainV5 {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        List<Integer> evenNumbers = GenericFilter.filter(numbers, (value) -> value % 2 == 0);
        System.out.println("evenNumbers = " + evenNumbers);

        List<String> strings = List.of("A", "BB", "CCC");
        List<String> filtered = GenericFilter.filter(strings, (value) -> value.length() > 1);
        System.out.println("filtered = " + filtered);

    }
}
