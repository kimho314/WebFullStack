import java.util.LinkedList;
import java.util.Queue;

public class BOJ11403 {
    static FastReader SC = new FastReader();
    static StringBuilder SB = new StringBuilder();
    static int[][] ADJ;
    static boolean[] VISITED;
    static int N;

    public static void main(String[] args) {
        N = SC.nextInt();
        ADJ = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                ADJ[i][j] = SC.nextInt();
            }
        }

        for (int i = 1; i <= N; i++) {
            VISITED = new boolean[N + 1];
            bfs(i);
        }
        System.out.println(SB);
    }

    private static void bfs(int start) {
        Queue<Integer> needVisit = new LinkedList<>();
        needVisit.add(start);
        VISITED[start] = false;

        while (!needVisit.isEmpty()) {
            int x = needVisit.poll();

            for (int y = 1; y <= N; y++) {
                if (ADJ[x][y] == 0) {
                    continue;
                }
                if (VISITED[y]) {
                    continue;
                }

                needVisit.add(y);
                VISITED[y] = true;
            }
        }

        for (int i = 1; i <= N; i++) {
            SB.append(VISITED[i] ? 1 : 0).append(' ');
        }
        SB.append('\n');
    }
}
