public class BOJ11057 {
    static FastReader SC = new FastReader();

    static int N;
    static long[][] DP;

    public static void main(String[] args) {
        input();
        solve();
    }

    private static void solve() {
        for (int i = 0; i < 10; i++) {
            DP[1][i] = 1;
        }

        for (int i = 2; i <= N; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k <= j; k++) {
                    DP[i][j] += DP[i - 1][k];
                }
                DP[i][j] %= 10_007;
            }
        }

        long sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += DP[N][i];
        }
        System.out.println(sum % 10_007);
    }

    private static void input() {
        N = SC.nextInt();
        DP = new long[N + 1][10];
    }
}
