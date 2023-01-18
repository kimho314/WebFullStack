public class CombinationOf123 {
    public static void main(String[] args) {
        // n == 1 -> the number of combination is 1
        // n == 2 -> 2
        // n == 3 -> 4
        // n == 4 -> 7
        // n == 7 -> 13
        // n > 3, f(n) = f(n-1) + f(n-2) + f(n-3)
        int num = 5;
        var result = getCombination(num);
        System.out.println("result : " + result);
    }

    public static int getCombination(int n) {
        if (n <= 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        } else if (n == 3) {
            return 4;
        } else {
            return getCombination(n - 1) + getCombination(n - 2) + getCombination(n - 3);
        }
    }
}
