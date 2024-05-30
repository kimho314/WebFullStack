public class BOJ14501 {
    static FastReader SC = new FastReader();
    static int N;
    static int[] T, P;
    static int[] DP;

    public static void main(String[] args) {
        N = SC.nextInt();
        T = new int[N];
        P = new int[N];
        for (int i = 0; i < N; i++) {
            T[i] = SC.nextInt();
            P[i] = SC.nextInt();
        }

        DP = new int[N + 1];
        for (int i = 0; i < N; i++) {
            if (i + T[i] <= N) {
                DP[i + T[i]] = Math.max(DP[i + T[i]], DP[i] + P[i]);
            }
            DP[i + 1] = Math.max(DP[i + 1], DP[i]);
        }
        System.out.println(DP[N]);
    }
}
