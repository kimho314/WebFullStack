import java.util.Comparator;
import java.util.PriorityQueue;

public class BOJ2075 {
    static FastReader SC = new FastReader();
    static int N;
    static PriorityQueue<Integer> PQ = new PriorityQueue<>(Comparator.reverseOrder());
    ;

    public static void main(String[] args) {
        input();
        solve();
    }

    private static void solve() {
        int res = 0;
        int cnt = 0;
        while (cnt < N) {
            cnt++;
            res = PQ.poll();
        }

        System.out.println(res);
    }

    private static void input() {
        N = SC.nextInt();

        for (int i = 0; i < N * N; i++) {
            int num = SC.nextInt();
            PQ.add(num);
        }
    }
}
