public class BOJ11055 {
    static FastReader SC = new FastReader();
    static int[] DP;
    static int N;
    static int[] A;

    public static void main(String[] args) {
        init();

        solve();

        int max = Integer.MIN_VALUE;
        for (int i : DP) {
            max = Math.max(i, max);
        }
        System.out.println(max);
    }

    private static void init() {
        N = SC.nextInt();
        DP = new int[N + 1];
        A = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            A[i] = SC.nextInt();
        }
    }

    private static void solve() {
        DP[1] = A[1];

        for (int i = 1; i <= N; i++) {
            DP[i] = A[i];
            for (int j = 1; j < i; j++) {
                if (A[i] > A[j]) {
                    DP[i] = Math.max(DP[j] + A[i], DP[i]);
                }
            }
        }
    }
}
