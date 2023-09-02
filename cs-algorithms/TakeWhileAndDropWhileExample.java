import java.util.stream.Stream;

public class TakeWhileAndDropWhileExample {
    public static void main(String[] args) {
        Stream.of("India", "Australia", "Newzealand", "", "England", "Srilanka")
                .takeWhile(o -> !o.isEmpty())
                .forEach(it -> System.out.print(it + " "));

        System.out.println();
        System.out.println("========");

        Stream.of("India", "Australia", "Newzealand", "", "England", "Srilanka")
                .dropWhile(o -> !o.isEmpty())
                .forEach(it -> System.out.print(it + " "));

        System.out.println();
        System.out.println("========");

        Stream.of("India", "", "Australia", "", "England", "Srilanka")
                .dropWhile(o -> !o.isEmpty())
                .forEach(it -> System.out.print(it + " "));
    }
}
