public class BOJ10972 {
    private static FastReader SC = new FastReader();
    private static int N;
    private static int[] NUMS;

    public static void main(String[] args) {
        input();
        solve();
    }

    private static void solve() {
        if (hasNextPerm()) {
            StringBuilder sb = new StringBuilder();
            for (int i : NUMS) {
                sb.append(i).append(" ");
            }
            sb.append("\n");
            System.out.println(sb);
        } else {
            System.out.println(-1);
        }
    }

    private static boolean hasNextPerm() {

        int i = N - 1;
        while (i > 0 && NUMS[i - 1] > NUMS[i]) {
            i--;
        }

        if (i == 0) {
            return false;
        }

        int j = N - 1;
        while (NUMS[i - 1] > NUMS[j]) {
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

    private static void swap(int i, int j) {
        int tmp = NUMS[i];
        NUMS[i] = NUMS[j];
        NUMS[j] = tmp;
    }

    private static void input() {
        N = SC.nextInt();
        NUMS = new int[N];
        for (int i = 0; i < N; i++) {
            NUMS[i] = SC.nextInt();
        }
    }
}
