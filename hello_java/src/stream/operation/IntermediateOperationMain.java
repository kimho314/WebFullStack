package stream.operation;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class IntermediateOperationMain {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        numbers.stream()
                .filter(n -> n % 2 == 0)
                .forEach(n -> System.out.print(n));
        System.out.println("\n");

        numbers.stream()
                .map(n -> n * n)
                .forEach(n -> System.out.print(n + " "));
        System.out.println("\n");

        numbers.stream()
                .distinct()
                .forEach(n -> System.out.print(n + " "));
        System.out.println("\n");

        Stream.of(3, 1, 4, 5, 8)
                .sorted()
                .forEach(n -> System.out.print(n + " "));
        System.out.println("\n");

        Stream.of(3, 1, 4, 5, 8)
                .sorted(Comparator.reverseOrder())
                .forEach(n -> System.out.print(n + " "));
        System.out.println("\n");

        Stream.of(3, 1, 4, 5, 8)
                .peek(n -> System.out.println("before: " + n + ", "))
                .map(n -> n * n)
                .peek(n -> System.out.println("after: " + n + ", "))
                .limit(5)
                .forEach(n -> System.out.print(n + " "));
        System.out.println("\n");

        Stream.of(3, 1, 4, 5, 8)
                .skip(2)
                .forEach(n -> System.out.print(n + " "));
        System.out.println("\n");

        List<Integer> numbers2 = List.of(1, 2, 3, 4, 5, 1, 2, 3);
        numbers2.stream()
                .takeWhile(n -> n < 5)
                .forEach(n -> System.out.print(n + " "));
        System.out.println("\n");

        numbers2.stream()
                .dropWhile(n -> n < 5)
                .forEach(n -> System.out.print(n + " "));
        System.out.println("\n");
    }
}
