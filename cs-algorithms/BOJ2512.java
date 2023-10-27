import java.util.Arrays;

public class BOJ2512 {
    static int N, M;
    static int[] NUMS;
    static FastReader SC = new FastReader();

    public static void main(String[] args) {
        N = SC.nextInt();
        NUMS = new int[N];
        for (int i = 0; i < N; i++) {
            NUMS[i] = SC.nextInt();
        }
        Arrays.sort(NUMS);
        M = SC.nextInt();

        int r = 0;
        int l = 0;
        for (int i = 0; i < N; i++) {
            r = Math.max(r, NUMS[i]);
        }
        int result = search(l, r);
        System.out.println(result);
    }

    private static int search(int l, int r) {
        int res = 0;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (determination(mid)) {
                l = mid + 1;
                res = mid;
            }
            else {
                r = mid - 1;
            }
        }

        return res;
    }

    private static boolean determination(int mid) {
        int sum = 0;
        for (int i = 0; i < N; i++) {
            int min = Math.min(NUMS[i], mid);
            sum += min;
        }

        return sum <= M;
    }
}
