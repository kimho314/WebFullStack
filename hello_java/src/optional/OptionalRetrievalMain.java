package optional;

import java.util.Optional;

public class OptionalRetrievalMain {
    public static void main(String[] args) {
        Optional<String> optValue = Optional.of("Hello");
        Optional<Object> optEmpty = Optional.empty();

        System.out.println("=== 1. isPresent() / isEmpty() ===");
        System.out.printf("optValue.isPresent() = %s", optValue.isPresent());
        System.out.println();
        System.out.printf("optEmpty.isPresent() = %s", optEmpty.isPresent());
        System.out.println();
        System.out.printf("optEmpty.isEmpty() = %s", optEmpty.isEmpty());
        System.out.println();

        String value1 = optValue.orElse("Default");
        Object value2 = optEmpty.orElse("Default");
        System.out.println("value1 = " + value1);
        System.out.println("value2 = " + value2);

        String value3 = optValue.orElseGet(() -> {
            System.out.println("optValue");
            return "New Value";
        });
        Object value4 = optEmpty.orElseGet(() -> {
            System.out.println("optEmpty");
            return "New Value";
        });
        System.out.println("value3 = " + value3);
        System.out.println("value4 = " + value4);

        String value5 = optValue.orElseThrow(() -> new RuntimeException("null value"));
        System.out.println("value5 = " + value5);

        try {
            Object value6 = optEmpty.orElseThrow(() -> new RuntimeException("null value"));
        }
        catch (RuntimeException e) {
            System.out.println("Exception occurred");
        }

    }
}
