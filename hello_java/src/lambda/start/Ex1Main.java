package lambda.start;

import java.util.Random;

public class Ex1Main {
    private static void func(Runnable runnable) {
        long startNs = System.nanoTime();
        runnable.run();
        long endNs = System.nanoTime();
        System.out.println("Time: " + (endNs - startNs) / 1000000 + "ms");
    }

    public static void main(String[] args) {
        Runnable runnable1 = () -> {
            for (int i = 0; i <= 3; i++) {
                System.out.println("i = " + i);
            }
        };
        func(runnable1);

        Runnable runnable2 = () -> {
            int randomValue = new Random().nextInt(6) + 1;
            System.out.println("dice = " + randomValue);
        };
        func(runnable2);
    }
}
