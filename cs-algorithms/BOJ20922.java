public class BOJ20922 {
    private static FastReader SC = new FastReader();
    private static int N, K;
    private static int[] A;

    public static void main(String[] args) {
        input();
        solve();
    }

    private static void solve() {
        int[] cnt = new int[100_001];
        int res = 0;
        int start = 0;
        int end = 0;

        while (end < N) {
            while (end < N && cnt[A[end]] + 1 <= K) {
                cnt[A[end]]++;
                end++;
            }

            int len = end - start;
            res = Math.max(res, len);
            cnt[A[start]]--;
            start++;
        }

        System.out.println(res);
    }

    private static void input() {
        N = SC.nextInt();
        K = SC.nextInt();
        A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = SC.nextInt();
        }
    }
}
