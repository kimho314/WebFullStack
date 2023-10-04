import java.util.Arrays;

public class BOJ1028 {
    static FastReader SC = new FastReader();
    static int N;
    static int[] A, B;
    static int SUM = Integer.MAX_VALUE;
    static int[] SELECTED;
    static boolean[] USED;

    public static void main(String[] args) {
        N = SC.nextInt();
        A = new int[N + 1];
        B = new int[N + 1];
        SELECTED = new int[N + 1];
        USED = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            A[i] = SC.nextInt();
        }
        for (int i = 1; i <= N; i++) {
            B[i] = SC.nextInt();
        }

        recFunc(1, 0);
        System.out.println(SUM);
    }

    private static void recFunc(int k, int prevIdx) {
        if (k == N + 1) {
            System.out.println(Arrays.toString(Arrays.copyOfRange(SELECTED, 1, SELECTED.length)));
            int sum = 0;
            for (int i = 1; i <= N; i++) {
                sum += (SELECTED[i] * B[i]);
            }
            SUM = Math.min(SUM, sum);
        }
        else {
            for (int i = 1; i <= N; i++) {
                if (!USED[i]) {
                    SELECTED[k] = A[i];
                    USED[i] = true;
                    recFunc(k + 1, i);
                    SELECTED[k] = Integer.MIN_VALUE;
                    USED[i] = false;
                }
            }
        }
    }
}
