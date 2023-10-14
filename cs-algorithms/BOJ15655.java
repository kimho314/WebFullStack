import java.util.Arrays;

public class BOJ15655 {
    static FastReader SC = new FastReader();
    static int N, M;
    static int[] S, SELECTED;
    static StringBuilder SB = new StringBuilder();

    public static void main(String[] args) {
        N = SC.nextInt();
        M = SC.nextInt();
        S = new int[N + 1];
        SELECTED = new int[M + 1];
        for (int i = 1; i <= N; i++) {
            S[i] = SC.nextInt();
        }

        Arrays.sort(S);
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
            for (int i = 1; i <= N; i++) {
                if (S[i] > SELECTED[k - 1]) {
                    SELECTED[k] = S[i];
                    recFunc(k + 1);
                    SELECTED[k] = 0;
                }
            }
        }
    }
}
