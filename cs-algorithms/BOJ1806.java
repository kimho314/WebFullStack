public class BOJ1806 {
    static FastReader sc = new FastReader();
    static int N, S;
    static int[] NUMS;

    public static void main(String[] args) {
        N = sc.nextInt();
        S = sc.nextInt();
        NUMS = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            NUMS[i] = sc.nextInt();
        }

        int right = 0;
        int sum = 0;
        int ans = N + 1;
        for (int left = 1; left <= N; left++) {
            sum -= NUMS[left - 1];

            while (right + 1 <= N && sum < S) {
                sum += NUMS[++right];
            }

            if (sum >= S) {
                ans = Math.min(ans, right - left + 1);
            }
        }

        if (ans == N + 1) {
            ans = 0;
        }
        System.out.println(ans);
    }
}
