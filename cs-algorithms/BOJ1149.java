public class BOJ1149 {
    static FastReader SC = new FastReader();
    static int N;

    public static void main(String[] args) {
        N = SC.nextInt();
        int[][] colors = new int[N + 1][3];
        int[][] dp = new int[N + 1][3];
        for (int i = 1; i <= N; i++) {
            colors[i][0] = SC.nextInt();
            colors[i][1] = SC.nextInt();
            colors[i][2] = SC.nextInt();
        }

        dp[1][0] = colors[1][0];
        dp[1][1] = colors[1][1];
        dp[1][2] = colors[1][2];

        for (int i = 2; i <= N; i++) {
            dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + colors[i][0];
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + colors[i][1];
            dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + colors[i][2];
        }
        System.out.println(Math.min(Math.min(dp[N][0], dp[N][1]), dp[N][2]));
    }
}
