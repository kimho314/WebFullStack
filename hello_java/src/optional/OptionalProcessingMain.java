package optional;

import java.util.Optional;

public class OptionalProcessingMain {
    public static void main(String[] args) {
        Optional<String> optValue = Optional.ofNullable("Hello");
        Optional<String> optEmpty = Optional.ofNullable(null);

        optValue.ifPresentOrElse(
                v -> System.out.println("optValue : " + v),
                () -> System.out.println("optValue is empty")
        );
        optEmpty.ifPresentOrElse(
                v -> System.out.println("optEmpty : " + v),
                () -> System.out.println("optEmpty is empty")
        );

        Integer lengthOpt1 = optValue.map(String::length)
                .orElse(0);
        System.out.println("lengthOpt1 = " + lengthOpt1);

        Optional<Integer> lengthOpt2 = optEmpty.map(String::length);
        System.out.println("lengthOpt2 = " + lengthOpt2);

        Optional<Optional<String>> nestedOpt = optValue.map(s -> Optional.of(s));
        System.out.println("nestedOpt = " + nestedOpt);

        Optional<String> flattenedOpt = optValue.flatMap(s -> Optional.of(s));
        System.out.println("flattenedOpt = " + flattenedOpt);

        Optional<String> filtered1 = optValue.filter(s -> s.startsWith("H"));
        Optional<String> filtered2 = optValue.filter(s -> s.startsWith("X"));
        System.out.println("filtered1 = " + filtered1 + ", filtered2 = " + filtered2);

        optValue.stream()
                .forEach(s -> System.out.println("optValue.stream() -> " + s));
    }
}
