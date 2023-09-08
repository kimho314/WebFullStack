import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ11725 {
    static FastReader SC = new FastReader();
    static int N;
    static ArrayList<Integer>[] ADJ;
    static boolean[] VISITED;
    static int[] ROOT;

    public static void main(String[] args) {
        N = SC.nextInt();
        VISITED = new boolean[N + 1];
        ROOT = new int[N + 1];
        ADJ = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            ADJ[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            int p1 = SC.nextInt();
            int p2 = SC.nextInt();
            ADJ[p1].add(p2);
            ADJ[p2].add(p1);
        }

        bfs(1);

        for (int i = 2; i <= N; i++) {
            System.out.println(ROOT[i]);
        }
    }

    private static void bfs(int start) {
        Queue<Integer> needVisit = new LinkedList<>();
        needVisit.add(start);

        while (!needVisit.isEmpty()) {
            int x = needVisit.poll();
            VISITED[x] = true;

            for (int y : ADJ[x]) {
                if (VISITED[y]) {
                    continue;
                }

                VISITED[y] = true;
                ROOT[y] = x;
                needVisit.add(y);
            }
        }
    }
}
