package lambda.lambda3;

public class GenericMain2 {
    public static void main(String[] args) {
        ObjectFunction stringFunction = s -> ((String) s).toUpperCase();
        ObjectFunction numberFunction = n -> (Integer) n * (Integer) 2;
        System.out.println((String) stringFunction.apply("Hello"));
        System.out.println(numberFunction.apply(10));
    }


    @FunctionalInterface
    interface ObjectFunction {
        Object apply(Object n);
    }
}
