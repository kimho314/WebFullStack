public class Factorial {
    public static void main(String[] args) {
        // factorial
        // if n > 1 then f(n) = n * (n-1)
        // if n == 1 then f(n) == 1
        // if n == 0 then f(n) == 1
        int num = 5;
        var result = factorial(num);
        System.out.println("result : " + result);
    }

    public static int factorial(int n) {
        if (n <= 1) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }
}
