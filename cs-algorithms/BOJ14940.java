import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ14940 {
    static FastReader SC = new FastReader();
    static int N, M;
    static int[][] DIR = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    static int[][] MAP;
    static int[][] VISITED;

    public static void main(String[] args) {
        N = SC.nextInt();
        M = SC.nextInt();
        MAP = new int[N][M];
        int targetY = -1;
        int targetX = -1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                MAP[i][j] = SC.nextInt();
                if (MAP[i][j] == 2) {
                    targetY = i;
                    targetX = j;
                }
            }
        }
        VISITED = new int[N][M];
        for (int[] row : VISITED) {
            Arrays.fill(row, -1);
        }

        bfs(targetY, targetX);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (MAP[i][j] == 0) {
                    System.out.print(0 + " ");
                }
                else {
                    System.out.print(VISITED[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    private static void bfs(int startY, int startX) {
        Queue<Integer> q = new LinkedList<>();
        q.add(startY);
        q.add(startX);
        VISITED[startY][startX] = 0;

        while (!q.isEmpty()) {
            int y = q.poll();
            int x = q.poll();

            for (int i = 0; i < 4; i++) {
                int dy = y + DIR[i][0];
                int dx = x + DIR[i][1];

                if (dy < 0 || dx < 0 || dy >= N || dx >= M) {
                    continue;
                }
                if (VISITED[dy][dx] != -1 || MAP[dy][dx] == 0) {
                    continue;
                }

                VISITED[dy][dx] = VISITED[y][x] + 1;
                q.add(dy);
                q.add(dx);
            }
        }
    }
}
