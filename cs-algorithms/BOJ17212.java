public class BOJ17212 {
    private static FastReader SC = new FastReader();
    private static int N;

    public static void main(String[] args) {
        input();
        solve();
    }

    private static void solve() {
        int res = 0;
        int[] coins = {7, 5, 2, 1};
        for (int i = 0; i < 4; i++) {
            int cnt = N / coins[i];
            N -= coins[i] * cnt;
            res += cnt;
        }
        System.out.println(res);
    }

    private static void input() {
        N = SC.nextInt();
    }
}
