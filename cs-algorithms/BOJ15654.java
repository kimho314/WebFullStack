import java.util.Arrays;

public class BOJ15654 {
    static FastReader SC = new FastReader();
    static StringBuilder SB = new StringBuilder();
    static int N, M;
    static int[] SELECTED;
    static int[] A;

    public static void main(String[] args) {
        N = SC.nextInt();
        M = SC.nextInt();
        SELECTED = new int[M + 1];
        A = new int[N + 1];
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
            for (int cand = 1; cand <= N; cand++) {
                boolean isUsed = false;
                for (int i = 0; i < k; i++) {
                    if (A[cand] == SELECTED[i]) {
                        isUsed = true;
                        break;
                    }
                }
                if (!isUsed) {
                    SELECTED[k] = A[cand];
                    recFunc(k + 1);
                    SELECTED[k] = 0;
                }
            }
        }
    }
}
