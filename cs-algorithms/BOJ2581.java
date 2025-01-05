public class BOJ2581 {
    private static FastReader SC = new FastReader();
    private static int M, N;

    public static void main(String[] args) {
        input();
        solve();
    }

    // 1 ... 10
    // 2,3,5,7
    // 17
    private static void solve() {
        int min = Integer.MAX_VALUE;
        int sum = 0;
        for (int i = M; i <= N; i++) {
            if (isPrime(i)) {
                min = Math.min(min, i);
                sum += i;
            }
        }

        if (min == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(sum);
            System.out.println(min);
        }
    }

    private static boolean isPrime(int num) {
        if (num == 1) {
            return false;
        }

        boolean isPrime = true;
        for (int i = 2; i <= (int) Math.sqrt(num); i++) {
            if (num % i == 0 || num % (num / i) == 0) {
                isPrime = false;
                break;
            }
        }

        return isPrime;
    }

    private static void input() {
        M = SC.nextInt();
        N = SC.nextInt();
    }
}
