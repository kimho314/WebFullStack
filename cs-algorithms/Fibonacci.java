import java.util.ArrayList;
import java.util.List;

public class Fibonacci {

    public static void main(String[] args) {
        // if n == 0 -> 0
        // if n == 1 -> 1
        // if n > 1 -> f_n-1 + f_n-2
        // e.g. 2 -> 1, 3 -> 2, 4 -> 3
        System.out.println(getFibonacci1(2));
        System.out.println(getFibonacci1(3));
        System.out.println(getFibonacci1(4));
        System.out.println(getFibonacci1(10));
        System.out.println("------------------");
        System.out.println(getFibonacci2(2));
        System.out.println(getFibonacci2(3));
        System.out.println(getFibonacci2(4));
        System.out.println(getFibonacci2(10));
    }

    // without memoization
    public static int getFibonacci1(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            return getFibonacci1(n - 1) + getFibonacci1(n - 2);
        }
    }

    public static int getFibonacci2(int n) {
        List<Integer> list = new ArrayList<>();
        list.add(0, 0);
        list.add(1, 1);
        for (int i = 2; i <= n; i++) {
            list.add(i, list.get(i - 1) + list.get(i - 2));
        }
        return list.get(n);
    }
}
