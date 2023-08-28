public class BOJ9095 {
    static FastReader sc = new FastReader();
    static StringBuilder sb = new StringBuilder();
    static int T;

    public static void main(String[] args) {
        T = sc.nextInt();
        while (T > 0) {
            T--;
            int n = sc.nextInt();
            int ans = findNumberOfSum(n);
            sb.append(ans).append('\n');
        }
        System.out.println(sb);
    }

    private static int findNumberOfSum(int n) {
        int[] D = new int[12];
        D[0] = 0;
        D[1] = 1;
        D[2] = 2;
        D[3] = 4;
        for (int i = 4; i <= n; i++) {
            D[i] = D[i - 1] + D[i - 2] + D[i - 3];
        }
        return D[n];
    }
}
