import java.util.Arrays;

public class BOJ11053 {
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
        Arrays.fill(DP, 1);

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (A[i] > A[j]) {
                    DP[i] = Math.max(DP[i], DP[j] + 1);
                }
            }
        }

        int res = DP[0];
        for (int i = 1; i < N; i++) {
            res = Math.max(res, DP[i]);
        }
        System.out.println(res);
    }
}
