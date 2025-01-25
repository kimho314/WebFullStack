public class BOJ20922 {
    private static FastReader SC = new FastReader();
    private static int N, K;
    private static int[] A;

    public static void main(String[] args) {
        input();
        solve();
    }

    private static void solve() {
        int[] cnt = new int[100001];
        int ans = 0;

        int start = 0;
        int end = 0;
        while (start < N && end < N) {
            if (cnt[A[end]] < K) {
                cnt[A[end]]++;
                end++;
                ans = Math.max(ans, end - start);
            } else {
                cnt[A[start]]--;
                start++;
            }
        }

        System.out.println(ans);
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
