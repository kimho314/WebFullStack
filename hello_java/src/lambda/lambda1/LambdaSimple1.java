package lambda.lambda1;

import lambda.MyFunction;

public class LambdaSimple1 {
    public static void main(String[] args) {
        MyFunction function1 = (int a, int b) -> {
            return a + b;
        };
        System.out.println("function1: " + function1.apply(1, 2));

        MyFunction function2 = (int a, int b) -> a + b;
        System.out.println("function2: " + function2.apply(1, 2));

        MyFunction function3 = (int a, int b) -> {
            System.out.println("Hello, Lambda!");
            return a + b;
        };
        System.out.println("function3: " + function3.apply(1, 2));
    }
}
