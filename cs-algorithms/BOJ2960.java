import java.util.ArrayList;

public class BOJ2960 {
    private static FastReader SC = new FastReader();
    private static int N, K;

    public static void main(String[] args) {
        input();
        solve();
    }

    private static void solve() {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 2; i <= N; i++) {
            list.add(i);
        }

        int cnt = 0;
        int res = 0;

        while (!list.isEmpty()) {
            int n = list.remove(0);
            cnt++;
            if (cnt == K) {
                res = n;
                break;
            }

            boolean isK = false;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) % n == 0) {
                    cnt++;
                    int x = list.remove(i);
                    if (cnt == K) {
                        res = x;
                        isK = true;
                        break;
                    }
                }
            }
            if (isK) {
                break;
            }
        }

        System.out.println(res);
    }

    private static void input() {
        N = SC.nextInt();
        K = SC.nextInt();
    }
}
