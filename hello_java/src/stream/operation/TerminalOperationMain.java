package stream.operation;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TerminalOperationMain {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        List<Integer> collect1 = numbers.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
        System.out.println("collect1: " + collect1);
        System.out.println();

        List<Integer> collect2 = numbers.stream()
                .filter(n -> n % 2 == 0)
                .toList();
        System.out.println("collect2: " + collect2);
        System.out.println();

        Integer[] collect3 = numbers.stream()
                .filter(n -> n % 2 == 0)
                .toArray(Integer[]::new);
        System.out.println("collect3: " + Arrays.toString(collect3));
        System.out.println();

        numbers.stream()
                .limit(5)
                .forEach(n -> System.out.print(n + " "));
        System.out.println("\n");

        long count = numbers.stream()
                .filter(n -> n > 5)
                .count();
        System.out.println("count: " + count);
        System.out.println();

        Optional<Integer> sum1 = numbers.stream()
                .reduce((a, b) -> a + b);
        System.out.println("sum1: " + sum1.get());
        System.out.println();

        Integer sum2 = numbers.stream()
                .reduce(10, (a, b) -> a + b);
        System.out.println("sum2: " + sum2);
        System.out.println();

        Optional<Integer> min = numbers.stream()
                .min(Integer::compareTo);
        System.out.println("min: " + min.get());
        System.out.println();

        Optional<Integer> max = numbers.stream()
                .max(Integer::compareTo);
        System.out.println("max: " + max.get());
        System.out.println();

        Optional<Integer> first = numbers.stream()
                .filter(n -> n > 5)
                .findFirst();
        System.out.println("first: " + first.get());
        System.out.println();

        Optional<Integer> any = numbers.stream()
                .filter(n -> n > 5)
                .findAny();
        System.out.println("any: " + any.get());
        System.out.println();

        boolean anyMatch = numbers.stream()
                .anyMatch(n -> n > 5);
        System.out.println("anyMatch: " + anyMatch);
        System.out.println();

        boolean allPositive = numbers.stream()
                .allMatch(n -> n > 0);
        System.out.println("allPositive: " + allPositive);
        System.out.println();

        boolean allNegative = numbers.stream()
                .allMatch(n -> n < 0);
        System.out.println("allNegative: " + allNegative);
        System.out.println();
    }
}
