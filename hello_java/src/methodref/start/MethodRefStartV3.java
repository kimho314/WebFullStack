package methodref.start;

import java.util.function.BinaryOperator;

public class MethodRefStartV3 {
    public static void main(String[] args) {
        BinaryOperator<Integer> add = MethodRefStartV3::add;
        System.out.println(add.apply(10, 20));
    }

    static int add(int a, int b) {
        return a + b;
    }
}
