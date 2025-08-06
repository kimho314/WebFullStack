package lambda.lambda4;

import java.util.function.Consumer;

public class ConsumerMain {
    public static void main(String[] args) {
        Consumer<String> consumner1 = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        consumner1.accept("Hello");

        Consumer<String> consumner2 = s -> System.out.println(s);
        consumner2.accept("Hello");
    }
}
