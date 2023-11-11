import java.util.Arrays;

public class BOJ15665 {
    static FastReader SC = new FastReader();
    static int N, M;
    static int[] A, SELECTED;
    static StringBuilder SB = new StringBuilder();

    public static void main(String[] args) {
        N = SC.nextInt();
        M = SC.nextInt();
        A = new int[N + 1];
        SELECTED = new int[M + 1];
        for (int i = 1; i <= N; i++) {
            A[i] = SC.nextInt();
        }
        Arrays.sort(A);

        recFunc(1);
        System.out.println(SB);
    }

    private static void recFunc(int k) {
        if (k == M + 1) {
            for (int i = 1; i <= M; i++) {
                SB.append(SELECTED[i]).append(' ');
            }
            SB.append('\n');
        }
        else {
            int num = 0;
            for (int i = 1; i <= N; i++) {
                if (num == A[i]) {
                    continue;
                }

                num = A[i];
                SELECTED[k] = A[i];
                recFunc(k + 1);
                SELECTED[k] = 0;
            }
        }
    }
}
