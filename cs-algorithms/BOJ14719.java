public class BOJ14719 {
    private static FastReader SC = new FastReader();
    private static int H, W;
    private static int[] HEIGHTS;

    public static void main(String[] args) {
        input();
        solve();
    }

    private static void solve() {
        int res = 0;
        for (int i = 1; i < W - 1; i++) {
            int left = 0;
            int right = 0;
            for (int j = 0; j < i; j++) {
                left = Math.max(HEIGHTS[j], left);
            }

            for (int j = i + 1; j < W; j++) {
                right = Math.max(HEIGHTS[j], right);
            }

            if (HEIGHTS[i] < left && HEIGHTS[i] < right) {
                res += Math.min(left, right) - HEIGHTS[i];
            }
        }

        System.out.println(res);
    }

    private static void input() {
        H = SC.nextInt();
        W = SC.nextInt();
        HEIGHTS = new int[W];
        for (int i = 0; i < W; i++) {
            HEIGHTS[i] = SC.nextInt();
        }
    }
}
