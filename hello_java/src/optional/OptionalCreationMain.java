package optional;

import java.util.Optional;

public class OptionalCreationMain {
    public static void main(String[] args) {
        String nonNullValue = "Hello Optional!";
        Optional<String> opt1 = Optional.of(nonNullValue);
        System.out.println("opt1 = " + opt1);

        Optional<String> opt2 = Optional.ofNullable("Hello");
        Optional<String> opt3 = Optional.ofNullable(null);
        System.out.println("opt2 = " + opt2);
        System.out.println("opt3 = " + opt3);


        Optional<Object> empty = Optional.empty();
        System.out.println("empty = " + empty);
    }
}
