package functional;

import java.util.function.Function;

public class SideEffectMain2 {

    public static void main(String[] args) {

        Function<Integer, Integer> func = x -> {
            int result = x * 2;

            // side effect : 외부 세계(콘솔)에 영향을 미치므로 side effect
            System.out.println("x = " + x + ", result = " + (x * 2));
            return result;
        };
        func.apply(10);
        func.apply(10);
        func.apply(10);
    }
}
