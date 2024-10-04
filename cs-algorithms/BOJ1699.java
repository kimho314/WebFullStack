public class BOJ1699 {
    static FastReader SC = new FastReader();
    static int N;
    static int[] DP;

    public static void main(String[] args) {
        input();
        solve();
    }

    private static void solve() {
        for (int i = 0; i <= 100_000; i++) {
            DP[i] = i;
        }

        for (int i = 2; i <= 100_000; i++) {
            for (int j = 1; j * j <= i; j++) {
                int remainder = i - (j * j);
                DP[i] = Math.min(DP[i], DP[remainder] + 1);
            }
        }

        System.out.println(DP[N]);
    }

    private static void input() {
        N = SC.nextInt();
        DP = new int[100_000 + 1];
    }
}
