public class BOJ12852 {
    static FastReader SC = new FastReader();
    static int N;
    static boolean[] VISITED;
    static int[] SELECTED;
    static int MIN = Integer.MAX_VALUE;
    static String ANS = "";

    public static void main(String[] args) {
        N = SC.nextInt();
        VISITED = new boolean[N];
        SELECTED = new int[N];

        dfs(N, 0, 0);

        System.out.println(MIN);
        System.out.println(ANS);
    }

    private static void dfs(int n, int k, int cnt) {
        SELECTED[k] = n;
        if (n <= 1) {
            if (n == 1 && cnt < MIN) {
                MIN = cnt;
                ANS = "";
                for (int i = 0; i <= k; i++) {
                    ANS += SELECTED[i] + " ";
                }
            }
        }
        else {
            if (n % 3 == 0) {
                int dn = n / 3;
                dfs(dn, k + 1, cnt + 1);
            }
            if (n % 2 == 0) {
                int dn = n / 2;
                dfs(dn, k + 1, cnt + 1);
            }
            int dn = n - 1;
            dfs(dn, k + 1, cnt + 1);
        }
        SELECTED[k] = 0;
    }
}
