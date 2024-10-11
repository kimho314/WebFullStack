public class BOJ19637 {
    static FastReader SC = new FastReader();
    static int N, M;
    static String[] TITLES;
    static int[] POWERS;
    static int[] INPUTS;

    public static void main(String[] args) {
        input();
        solve();
    }

    private static void solve() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < M; i++) {
            int L = 0;
            int R = N - 1;
            while (L <= R) {
                int mid = (L + R) / 2;

                if (POWERS[mid] < INPUTS[i]) {
                    L = mid + 1;
                }
                else {
                    R = mid - 1;
                }
            }
            sb.append(TITLES[L]).append('\n');
        }
        System.out.println(sb);
    }

    private static void input() {
        N = SC.nextInt();
        M = SC.nextInt();
        TITLES = new String[N];
        POWERS = new int[N];
        for (int i = 0; i < N; i++) {
            TITLES[i] = SC.next();
            POWERS[i] = SC.nextInt();
        }
        INPUTS = new int[M];
        for (int i = 0; i < M; i++) {
            INPUTS[i] = SC.nextInt();
        }
    }
}
