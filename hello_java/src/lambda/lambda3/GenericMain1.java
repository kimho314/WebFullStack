package lambda.lambda3;

public class GenericMain1 {
    public static void main(String[] args) {
        StringFunction stringFunction = s -> s.toUpperCase();
        NumberFunction numberFunction = n -> n * 2;
        System.out.println(stringFunction.apply("Hello"));
        System.out.println(numberFunction.apply(10));
    }

    @FunctionalInterface
    interface StringFunction {
        String apply(String s);
    }

    @FunctionalInterface
    interface NumberFunction {
        Integer apply(Integer n);
    }
}
