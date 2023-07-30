import java.util.LinkedList;
import java.util.Queue;

public class BOJ1697 {
    static FastReader sc = new FastReader();
    static int N, K;
    static int[] DIST;
    static boolean[] VISIT;

    public static void main(String[] args) {
        N = sc.nextInt();
        K = sc.nextInt();
        VISIT = new boolean[100005];
        DIST = new int[100005];

        bfs();
        System.out.println(DIST[K]);
    }

    static void bfs() {
        Queue<Integer> needVisit = new LinkedList<>();
        needVisit.add(N);
        VISIT[N] = true;
        DIST[N] = 0;

        while (!needVisit.isEmpty()) {
            int x = needVisit.poll();
            if (x - 1 >= 0 && !VISIT[x - 1]) {
                VISIT[x - 1] = true;
                DIST[x - 1] = DIST[x] + 1;
                needVisit.add(x - 1);
            }
            if (x + 1 <= 100000 && !VISIT[x + 1]) {
                VISIT[x + 1] = true;
                DIST[x + 1] = DIST[x] + 1;
                needVisit.add(x + 1);
            }
            if (x * 2 <= 100000 && !VISIT[x * 2]) {
                VISIT[x * 2] = true;
                DIST[x * 2] = DIST[x] + 1;
                needVisit.add(x * 2);
            }
        }
    }
}
