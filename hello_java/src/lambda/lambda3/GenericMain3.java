package lambda.lambda3;

public class GenericMain3 {
    public static void main(String[] args) {
        GenericFunction<String, String> stringFunction = s -> s.toUpperCase();
        GenericFunction<Integer, Integer> numberFunction = n -> n * 2;
        System.out.println(stringFunction.apply("Hello"));
        System.out.println(numberFunction.apply(10));
    }


    @FunctionalInterface
    interface GenericFunction<T, R> {
        R apply(T n);
    }
}
