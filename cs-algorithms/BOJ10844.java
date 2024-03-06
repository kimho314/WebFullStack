public class BOJ10844 {
    static FastReader SC = new FastReader();
    static int N;

    public static void main(String[] args) {
        N = SC.nextInt();
        long[][] dp = new long[101][10];
        for (int i = 1; i < 10; i++) {
            dp[1][i] = 1;
        }

        for (int i = 2; i <= 100; i++) {
            for (int j = 0; j < 10; j++) {
                if (j == 0) {
                    dp[i][j] = dp[i - 1][1];
                }
                else if (j == 9) {
                    dp[i][j] = dp[i - 1][8];
                }
                else {
                    dp[i][j] = dp[i - 1][j + 1] + dp[i - 1][j - 1];
                }

                dp[i][j] %= 1_000_000_000;
            }
        }

        long res = 0;
        for (int i = 0; i < 10; i++) {
            res += dp[N][i];
        }
        System.out.println(res % 1_000_000_000);
    }
}
