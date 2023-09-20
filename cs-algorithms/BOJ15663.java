import java.util.Arrays;

public class BOJ15663 {
    static FastReader SC = new FastReader();
    static StringBuilder SB = new StringBuilder();
    static int N, M;
    static int[] SELECTED, NUMS, USED;

    public static void main(String[] args) {
        N = SC.nextInt();
        M = SC.nextInt();
        SELECTED = new int[M + 1];
        NUMS = new int[N + 1];
        USED = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            NUMS[i] = SC.nextInt();
        }
        Arrays.sort(NUMS, 1, N + 1);
        recFunc(1);
        System.out.println(SB);
    }

    private static void recFunc(int num) {
        if (num == M + 1) {
            for (int i = 1; i <= M; i++) {
                SB.append(SELECTED[i]).append(' ');
            }
            SB.append('\n');
        }
        else {
            int lastCand = 0;
            for (int cand = 1; cand <= N; cand++) {
                if (USED[cand] == 1) {
                    continue;
                }
                if (NUMS[cand] == lastCand) {
                    continue;
                }

                lastCand = NUMS[cand];
                SELECTED[num] = NUMS[cand];
                USED[cand] = 1;
                recFunc(num + 1);
                SELECTED[num] = 0;
                USED[cand] = 0;
            }
        }
    }
}
