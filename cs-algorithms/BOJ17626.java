public class BOJ17626 {
    static FastReader SC = new FastReader();
    static int N;
    static int[] DP;

    public static void main(String[] args) {
        input();
        solve();
    }

    private static void solve() {
        DP[1] = 1;

        for (int i = 2; i <= 50_000; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {
                min = Math.min(min, DP[i - j * j]);
            }
            DP[i] = min + 1;
        }

        System.out.println(DP[N]);
    }

    private static void input() {
        N = SC.nextInt();
        DP = new int[50_001];
    }
}
