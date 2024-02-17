public class BOJ1932 {
    static FastReader SC = new FastReader();
    static int[][] INPUTS;
    static int N;
    static int[][] DP;


    public static void main(String[] args) {
        N = SC.nextInt();
        INPUTS = new int[500][500];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= i; j++) {
                INPUTS[i][j] = SC.nextInt();
            }
        }

        DP = new int[N][N];
        DP[0][0] = INPUTS[0][0];
        for (int i = 1; i < N; i++) {
            DP[i][0] = DP[i - 1][0] + INPUTS[i][0];

            for (int j = 1; j < i; j++) {
                DP[i][j] = Math.max(DP[i - 1][j - 1], DP[i - 1][j]) + INPUTS[i][j];
            }

            DP[i][i] = DP[i - 1][i - 1] + INPUTS[i][i];
        }

        int res = 0;
        for (int i = 0; i < N; i++) {
            res = Math.max(res, DP[N - 1][i]);
        }
        System.out.println(res);
    }
}
