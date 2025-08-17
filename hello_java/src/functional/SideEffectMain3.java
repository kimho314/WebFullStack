package functional;

import java.util.function.Function;

public class SideEffectMain3 {

    public static void main(String[] args) {

        Function<Integer, Integer> func = x -> x * 2; // pure function
        int x = 10;
        Integer result = func.apply(x);

        // side effect를 순수 함수와 분리해서 실행
        // 출력을 별도로 처리
        System.out.println("x = " + x + ", result = " + result);
    }
}
