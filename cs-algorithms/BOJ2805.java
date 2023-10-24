public class BOJ2805 {
    static FastReader FB = new FastReader();
    static int N;
    static int M;
    static int[] TREES;

    public static void main(String[] args) {
        N = FB.nextInt();
        M = FB.nextInt();
        TREES = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            TREES[i] = FB.nextInt();
        }

        long l = 1L;
        long r = 2000000000L;
        long height = 0L;
        while (l <= r) {
            long mid = (l + r) / 2;
            if (determination(mid)) {
                l = mid + 1;
                height = mid;
            }
            else {
                r = mid - 1;
            }
        }

        System.out.println(height);
    }

    private static boolean determination(long mid) {
        long sum = 0L;
        for (int i = 1; i <= N; i++) {
            if (mid <= TREES[i]) {
                sum += TREES[i] - mid;
            }
        }

        return sum >= M;
    }
}
