public class BOJ20055 {
    private static FastReader SC = new FastReader();
    private static int N, K;
    private static int[] A;

    public static void main(String[] args) {
        input();
        solve();
    }

    private static void solve() {
        boolean[] robot = new boolean[N];

        int ans = 1;
        while (true) {
            int tmp = A[A.length - 1];
            for (int i = A.length - 1; i > 0; i--) {
                A[i] = A[i - 1];
            }
            A[0] = tmp;

            for (int i = robot.length - 1; i > 0; i--) {
                robot[i] = robot[i - 1];
            }
            robot[0] = false;

            robot[robot.length - 1] = false;
            for (int i = robot.length - 1; i > 0; i--) {
                if (robot[i - 1] && !robot[i] && A[i] != 0) {
                    robot[i] = true;
                    robot[i - 1] = false;
                    A[i]--;
                }
            }

            if (A[0] != 0) {
                robot[0] = true;
                A[0]--;
            }

            int count = 0;
            for (int a : A) {
                if (a == 0) {
                    count++;
                }
            }
            if (count >= K) {
                break;
            }
            else {
                ans++;
            }
        }

        System.out.println(ans);
    }

    private static void input() {
        N = SC.nextInt();
        K = SC.nextInt();
        A = new int[N * 2];
        for (int i = 0; i < A.length; i++) {
            A[i] = SC.nextInt();
        }
    }
}
