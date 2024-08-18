public class BOJ15990 {
    static FastReader SC = new FastReader();
    static int T;
    static long[][] DP;
    static StringBuilder SB = new StringBuilder();

    public static void main(String[] args) {
        input();
        solve();
    }

    private static void solve() {
        DP[1][0] = 1;
        DP[1][1] = 0;
        DP[1][2] = 0;
        DP[2][0] = 0;
        DP[2][1] = 1;
        DP[2][2] = 0;
        DP[3][0] = 1;
        DP[3][1] = 1;
        DP[3][2] = 1;
        for (int i = 4; i <= 100_000; i++) {
            DP[i][0] = (DP[i - 1][1] + DP[i - 1][2]) % 1_000_000_009;
            DP[i][1] = (DP[i - 2][0] + DP[i - 2][2]) % 1_000_000_009;
            DP[i][2] = (DP[i - 3][0] + DP[i - 3][1]) % 1_000_000_009;
        }

        while (T > 0) {
            T--;
            int n = SC.nextInt();
            long res = (DP[n][0] + DP[n][1] + DP[n][2]) % 1_000_000_009;
            SB.append(res).append('\n');
        }

        System.out.println(SB.toString());
    }

    private static void input() {
        T = SC.nextInt();
        DP = new long[100_001][3];
    }
}
