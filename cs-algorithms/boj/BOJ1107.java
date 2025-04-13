package boj;

public class BOJ1107 {
    private static FastReader SC = new FastReader();
    private static int N, M;
    private static int[] BUTTONS;

    public static void main(String[] args) {
        input();
        solve();
    }

    private static void solve() {
        int res = Math.abs(N - 100);

        for (int i = 0; i <= 999999; i++) {
            String str = String.valueOf(i);
            int len = str.length();

            boolean isBroken = false;
            for (int j = 0; j < len; j++) {
                if (BUTTONS[str.charAt(j) - '0'] == -1) {
                    isBroken = true;
                    break;
                }
            }

            if (!isBroken) {
                int min = Math.abs(N - i) + len;
                res = Math.min(res, min);
            }
        }

        System.out.println(res);
    }


    private static void input() {
        N = SC.nextInt();
        M = SC.nextInt();
        BUTTONS = new int[10];
        for (int i = 0; i < M; i++) {
            int n = SC.nextInt();
            BUTTONS[n] = -1;
        }
    }
}
