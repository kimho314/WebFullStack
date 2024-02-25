public class BOJ1912 {
    static FastReader SC = new FastReader();
    static int N;
    static int[] A, DP;

    public static void main(String[] args) {
        N = SC.nextInt();
        A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = SC.nextInt();
        }

        DP = new int[N];
        DP[0] = A[0];
        for (int i = 1; i < N; i++) {
            DP[i] = Math.max(DP[i - 1] + A[i], A[i]);
        }

        int res = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            res = Math.max(res, DP[i]);
        }
        System.out.println(res);
    }
}
