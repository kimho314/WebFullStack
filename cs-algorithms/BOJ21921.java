public class BOJ21921 {
    private static FastReader SC = new FastReader();
    private static int N, X;
    private static int[] VISITORS;

    public static void main(String[] args) {
        input();
        solve();
    }

    private static void solve() {
        int[] sums = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            sums[i] = VISITORS[i] + sums[i - 1];
        }

        int sum = 0;
        for (int i = 1; i <= N; i++) {
            int num = sums[i] - sums[Math.max(i - X, 0)];

            sum = Math.max(sum, num);
        }

        if (sum == 0) {
            System.out.println("SAD");
            return;
        }

        int res = 0;
        for (int i = 1; i <= N; i++) {
            int num = sums[i] - sums[Math.max(i - X, 0)];
            if (num == sum) {
                res++;
            }
        }

        System.out.println(sum);
        System.out.println(res);
    }

    private static void input() {
        N = SC.nextInt();
        X = SC.nextInt();
        VISITORS = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            VISITORS[i] = SC.nextInt();
        }
    }
}
