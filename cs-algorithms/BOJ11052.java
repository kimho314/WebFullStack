public class BOJ11052 {
    static FastReader SC = new FastReader();
    static int N;
    static int[] P;
    static int[] DP;

    public static void main(String[] args) {
        N = SC.nextInt();
        P = new int[N + 1];
        DP = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            P[i] = SC.nextInt();
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= i; j++) {
                DP[i] = Math.max(DP[i], DP[i - j] + P[j]);
            }
        }
        System.out.println(DP[N]);
    }
}
