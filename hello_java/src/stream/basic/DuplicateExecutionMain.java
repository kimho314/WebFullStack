package stream.basic;

import java.util.List;
import java.util.stream.Stream;

public class DuplicateExecutionMain {
    public static void main(String[] args) {
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5, 6);
        stream.forEach(System.out::println);

//        stream.forEach(System.out::println); // Exception in thread "main" java.lang.IllegalStateException: stream has already been operated upon or close

        List<Integer> list = List.of(1, 2, 3, 4);
        Stream.of(list).forEach(System.out::println);
        Stream.of(list).forEach(System.out::println);
    }
}
