public class BOJ9465 {
    private static FastReader SC = new FastReader();
    private static int T;
    private static StringBuilder SB = new StringBuilder();

    public static void main(String[] args) {
        input();
        while (T > 0) {
            T--;
            solve();
        }
        System.out.println(SB);
    }

    private static void solve() {
        int n = SC.nextInt();
        int[][] cost = new int[2][n];
        int[][] dp = new int[2][n];

        String[] st1 = SC.nextLine().split(" ");
        String[] st2 = SC.nextLine().split(" ");
        for (int i = 0; i < n; i++) {
            cost[0][i] = Integer.parseInt(st1[i]);
            cost[1][i] = Integer.parseInt(st2[i]);
        }

        dp[0][0] = cost[0][0];
        dp[1][0] = cost[1][0];
        int max = Math.max(dp[0][0], dp[1][0]);
        for (int i = 1; i < n; i++) {
            if (i == 1) {
                dp[0][i] = dp[1][0] + cost[0][1];
                dp[1][i] = dp[0][0] + cost[1][1];
                max = Math.max(dp[0][i], dp[1][i]);
                continue;
            }
            dp[0][i] = Math.max(dp[1][i - 1], dp[1][i - 2]) + cost[0][i];
            dp[1][i] = Math.max(dp[0][i - 1], dp[0][i - 2]) + cost[1][i];
            max = Math.max(max, Math.max(dp[0][i], dp[1][i]));
        }
        SB.append(max).append("\n");
    }

    private static void input() {
        T = SC.nextInt();
    }
}
