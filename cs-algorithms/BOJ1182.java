import java.util.Arrays;

public class BOJ1182 {
    static FastReader SC = new FastReader();
    static int N, S;
    static int[] A;
    static int[] SELECTED;
    static int CNT = 0;


    public static void main(String[] args) {
        N = SC.nextInt();
        S = SC.nextInt();
        A = new int[N + 1];
        SELECTED = new int[N + 1];
        Arrays.fill(SELECTED, Integer.MIN_VALUE);
        for (int i = 1; i <= N; i++) {
            A[i] = SC.nextInt();
        }

        Arrays.sort(A, 1, A.length);
        for (int i = 1; i <= N; i++) {
            recFunc(1, 0, i);
        }
        System.out.println(CNT);
    }

    private static void recFunc(int k, int prevIdx, int maxLen) {
        if (k == maxLen + 1) {
            int[] copy = Arrays.copyOfRange(SELECTED, 1, maxLen + 1);
            System.out.println(Arrays.toString(copy));
            int sum = Arrays.stream(copy).sum();
            if (sum == S) {
                CNT++;
            }
        }
        else {
            for (int cand = prevIdx + 1; cand <= N; cand++) {
                SELECTED[k] = A[cand];
                recFunc(k + 1, cand, maxLen);
                SELECTED[k] = Integer.MIN_VALUE;
            }
        }
    }
}
