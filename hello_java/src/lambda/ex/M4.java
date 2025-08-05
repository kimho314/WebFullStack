package lambda.ex;

import java.util.List;

public class M4 {
    @FunctionalInterface
    interface MyReducer {
        int reduce(int a, int b);
    }

    public static int reduce(List<Integer> list, int initial, MyReducer reducer) {
        int prev = initial;
        for (int i : list) {
            prev = reducer.reduce(prev, i);
        }
        return prev;
    }

    public static void main(String[] args) {
        List<Integer> list = List.of(1, 2, 3, 4);
        int result = reduce(list, 0, (a, b) -> a + b);
        System.out.println("result = " + result);
    }
}
