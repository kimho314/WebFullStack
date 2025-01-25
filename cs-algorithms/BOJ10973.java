public class BOJ10973 {
    private static FastReader SC = new FastReader();
    private static int N;
    private static int[] NUMS;

    public static void main(String[] args) {
        input();
        solve();
    }

    private static void solve() {
        boolean isPrev = getPrevPerm();
        if (!isPrev) {
            System.out.println(-1);
        } else {
            for (int num : NUMS) {
                System.out.print(num + " ");
            }
        }
    }

    private static boolean getPrevPerm() {
        int i = N - 1;
        while (i > 0 && NUMS[i - 1] <= NUMS[i]) {
            i--;
        }

        if (i <= 0) {
            return false;
        }

        int j = i;
        while (j < N - 1 && NUMS[i - 1] > NUMS[j + 1]) {
            j++;
        }

        swap(i - 1, j);

        j = N - 1;
        while (i < j) {
            swap(i, j);
            i += 1;
            j -= 1;
        }

        return true;
    }

    private static void swap(int i, int j) {
        int temp = NUMS[i];
        NUMS[i] = NUMS[j];
        NUMS[j] = temp;
    }

    private static void input() {
        N = SC.nextInt();
        NUMS = new int[N];
        for (int i = 0; i < N; i++) {
            NUMS[i] = SC.nextInt();
        }
    }
}
