public class BOJ2156 {
    static FastReader SC = new FastReader();
    static int N;
    static int[] A;
    static int[] DP;

    public static void main(String[] args) {
        input();
        solve();
    }

    private static void solve() {
        DP[0] = 0;
        for (int i = 1; i <= N; i++) {
            if (i == 1) {
                DP[i] = A[i];
            }
            else if (i == 2) {
                DP[i] = A[i] + A[i - 1];
            }
            else {
                DP[i] = Math.max(DP[i - 1], Math.max(DP[i - 2] + A[i], DP[i - 3] + A[i] + A[i - 1]));
            }
        }
        System.out.println(DP[N]);
    }

    private static void input() {
        N = SC.nextInt();
        A = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            A[i] = SC.nextInt();
        }
        DP = new int[N + 1];
    }
}
