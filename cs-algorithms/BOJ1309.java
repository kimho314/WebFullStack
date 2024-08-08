public class BOJ1309 {
    static FastReader SC = new FastReader();
    static int N;
    static long[][] DP;

    public static void main(String[] args) {
        init();
        solve();
    }

    private static void solve() {
        DP[1][0] = 1;
        DP[1][1] = 1;
        DP[1][2] = 1;
        DP[2][0] = 3;
        DP[2][1] = 2;
        DP[2][2] = 2;
        for (int i = 3; i <= 100_000; i++) {
            DP[i][0] = (DP[i - 1][0] + DP[i - 1][1] + DP[i - 1][2]) % 9901;
            DP[i][1] = (DP[i - 1][0] + DP[i - 1][2]) % 9901;
            DP[i][2] = (DP[i - 1][0] + DP[i - 1][1]) % 9901;
        }

        long res = (DP[N][0] + DP[N][1] + DP[N][2]) % 9901;
        System.out.println(res);
    }

    private static void init() {
        N = SC.nextInt();
        DP = new long[100_000 + 1][3];
    }
}
