public class BOJ11727 {
    static FastReader SC = new FastReader();
    static int N;
    static int[] DP;

    public static void main(String[] args) {
        init();
        solve();
        System.out.println(DP[N]);
    }

    private static void init() {
        N = SC.nextInt();
        DP = new int[1001];
    }

    private static void solve() {
        DP[0] = 0;
        DP[1] = 1;
        DP[2] = 3;

        for (int i = 3; i <= 1000; i++) {
            DP[i] = (DP[i - 1] + (DP[i - 2] * 2)) % 10007;
        }
    }
}
