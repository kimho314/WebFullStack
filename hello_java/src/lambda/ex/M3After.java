package lambda.ex;

import java.util.Arrays;
import java.util.function.Consumer;

public class M3After {
    public static void measureTime(Consumer<Integer> consumer, int n) {
        long start = System.currentTimeMillis();
        consumer.accept(n);
        long end = System.currentTimeMillis();
        System.out.println("Time: " + (end - start) + "ms");
    }

    public static void main(String[] args) {
        measureTime((n) -> {
            int sum = 0;
            for (int i = 1; i <= n; i++) {
                sum += i;
            }
        }, 1_000_000);

        measureTime((n) -> {
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = n - i;
            }
            Arrays.sort(arr);
        }, 1_000_000);
    }
}
