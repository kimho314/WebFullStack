import java.util.Arrays;

public class BOJ16938 {
    static FastReader SC = new FastReader();
    static int N, L, R, X;
    static int ANS = 0;
    static int[] A;
    static int[] SELECTED;

    public static void main(String[] args) {
        N = SC.nextInt();
        L = SC.nextInt();
        R = SC.nextInt();
        X = SC.nextInt();
        A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = SC.nextInt();
        }
        for (int i = 2; i <= N; i++) {
            SELECTED = new int[i + 1];
            recFunc(1, i);
        }
        System.out.println(ANS);
    }

    private static void recFunc(int k, int maxLen) {
        if (k == maxLen + 1) {
            int sum = 0;
            int max = Integer.MIN_VALUE;
            int min = Integer.MAX_VALUE;
            for (int i = 1; i <= maxLen; i++) {
                sum += SELECTED[i];
                max = Math.max(max, SELECTED[i]);
                min = Math.min(min, SELECTED[i]);
            }
            int range = max - min;
            if ((sum >= L && sum <= R) && (range >= X)) {
                System.out.println(Arrays.toString(Arrays.copyOfRange(SELECTED, 1, SELECTED.length)));
                ANS++;
            }
        }
        else {
            for (int i = 0; i < N; i++) {
                if (A[i] <= SELECTED[k - 1]) {
                    continue;
                }
                SELECTED[k] = A[i];
                recFunc(k + 1, maxLen);
                SELECTED[k] = 0;
            }
        }
    }
}
