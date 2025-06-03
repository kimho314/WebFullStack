package boj;

public class BOJ10972 {
    private static FastReader SC = new FastReader();
    private static int N;
    private static int[] NUMS;

    public static void main(String[] args) {
        input();
        solve();
    }

    private static void input() {
        N = SC.nextInt();
        NUMS = new int[N];
        for (int i = 0; i < N; i++) {
            NUMS[i] = SC.nextInt();
        }
    }

    private static void solve() {
        StringBuilder sb = new StringBuilder();
        if (nextPerm()) {
            for (int elem : NUMS) {
                sb.append(elem).append(" ");
            }
        } else {
            sb.append(-1);
        }

        System.out.println(sb);
    }

    private static boolean nextPerm() {
        int i = N - 1;
        while (i > 0 && NUMS[i - 1] > NUMS[i]) {
            i--;
        }
        if (i == 0) {
            return false;
        }

        int j = N - 1;
        while (NUMS[i - 1] >= NUMS[j]) {
            j--;
        }
        swap(i - 1, j);

        j = N - 1;
        while (i < j) {
            swap(i, j);
            i++;
            j--;
        }

        return true;
    }

    private static void swap(int idx1, int idx2) {
        int tmp = NUMS[idx1];
        NUMS[idx1] = NUMS[idx2];
        NUMS[idx2] = tmp;
    }
}
