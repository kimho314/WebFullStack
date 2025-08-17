package functional;

import java.util.function.Function;

public class SideEffectMain1 {
    public static int count = 0;

    public static void main(String[] args) {
        System.out.println("before count = " + count);

        // 함수가 count라는 전역 변수를 변경하고 있다. 외부 상태 변화가 부수 효과의 대표적인 예시이다.
        Function<Integer, Integer> func = x -> {
            count++;
            return x * 2;
        };
        func.apply(10);
        System.out.println("after count = " + count);
    }
}
