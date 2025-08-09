package stream.start;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamStartMain {
    public static void main(String[] args) {
        List<String> names = List.of("Aplle", "Banana", "Berry", "Tomato");
        Stream<String> stream = names.stream();
        List<String> result = stream.filter(name -> name.startsWith("B"))
                .map(s -> s.toUpperCase())
                .collect(Collectors.toList());

        System.out.println("=== 외부 반복 ===");
        for (String s : result) {
            System.out.println(s);
        }

        System.out.println("=== 내부 반복 ===");
        names.stream().filter(name -> name.startsWith("B"))
                .map(s -> s.toUpperCase())
                .forEach(System.out::println);
    }
}
