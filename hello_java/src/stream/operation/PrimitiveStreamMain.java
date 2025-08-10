package stream.operation;

import java.util.IntSummaryStatistics;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class PrimitiveStreamMain {
    public static void main(String[] args) {
        IntStream stream = IntStream.of(1, 2, 3, 4, 5);
        stream.forEach(i -> System.out.print(i + " "));
        System.out.println();

        IntStream range1 = IntStream.range(1, 6);
        IntStream range2 = IntStream.rangeClosed(1, 5);
        range1.forEach(i -> System.out.print(i + " "));
        System.out.println();
        range2.forEach(i -> System.out.print(i + " "));
        System.out.println();

        int sum = IntStream.range(1, 6).sum();
        System.out.println("sum = " + sum);
        System.out.println();

        double avg = IntStream.range(1, 6)
                .average()
                .getAsDouble();
        System.out.println("avg = " + avg);
        System.out.println();

        IntSummaryStatistics stats = IntStream.range(1, 6).summaryStatistics();
        System.out.println("sum: " + stats.getSum());
        System.out.println("avg: " + stats.getAverage());
        System.out.println("max: " + stats.getMax());
        System.out.println("min: " + stats.getMin());
        System.out.println("count: " + stats.getCount());
        System.out.println();

        LongStream longStream = IntStream.range(1, 5).asLongStream();
        DoubleStream doubleStream = IntStream.range(1, 5).asDoubleStream();
        Stream<Integer> boxedStream = IntStream.range(1, 5).boxed();

        LongStream mappedLong = IntStream.range(1, 5)
                .mapToLong(i -> i * 10L);

        DoubleStream mappedDouble = IntStream.range(1, 5)
                .mapToDouble(i -> i * 1.5);

        Stream<String> mappedObj = IntStream.range(1, 5)
                .mapToObj(i -> "Number: " + i);

        Stream<Integer> intergerStream = Stream.of(1, 2, 3, 4, 5);
        IntStream intStream = intergerStream.mapToInt(i -> i);
        int sum1 = intStream.sum();
    }
}
