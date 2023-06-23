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

        long left = 0;
        long right = 2000000000;
        long answer = 0;

        while (left <= right) {
            int mid = (int) ((left + right) / 2);
            if (determination(mid)) {
                answer = mid;
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }

        System.out.println(answer);
    }

    private static boolean determination(int h) {
        long sum = 0;
        for (int i = 1; i <= N; i++) {
            if (TREES[i] > h) {
                sum += TREES[i] - h;
            }
        }
        return sum >= M;
    }
}
