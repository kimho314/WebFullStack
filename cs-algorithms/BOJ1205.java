import java.util.Arrays;

public class BOJ1205 {
    private static FastReader SC = new FastReader();
    private static int N, SCORE, P;
    private static int[] RANK;

    public static void main(String[] args) {
        input();
        solve();
    }

    private static void solve() {
        int res = 1;
        for (int i = 0; i < P; i++) {
            if (RANK[i] < SCORE) {
                break;
            }

            if (RANK[i] == SCORE) {
                if (i == P - 1) {
                    res = -1;
                }
            }
            if (RANK[i] > SCORE) {
                if (i == P - 1) {
                    res = -1;
                }
                else {
                    res++;
                }
            }
        }

        System.out.println(res);
    }

    private static void input() {
        N = SC.nextInt();
        SCORE = SC.nextInt();
        P = SC.nextInt();
        RANK = new int[P];
        Arrays.fill(RANK, -1);
        for (int i = 0; i < N; i++) {
            RANK[i] = SC.nextInt();
        }
    }
}
