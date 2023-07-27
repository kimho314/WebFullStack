import java.util.LinkedList;
import java.util.Queue;

public class BOJ2178 {
    static FastReader sc = new FastReader();
    static int N, M;
    static int[][] MAP;
    static int[][] DIR = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    static int[][] DIST;
    static boolean[][] VISIT;

    public static void main(String[] args) {
        N = sc.nextInt();
        M = sc.nextInt();
        MAP = new int[N][M];
        DIST = new int[N][M];
        VISIT = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            String[] split = sc.next().split("");
            for (int j = 0; j < M; j++) {
                MAP[i][j] = Integer.parseInt(split[j]);
            }
        }

        bfs(0, 0);
        System.out.println(DIST[N - 1][M - 1]);
    }

    static void bfs(int x, int y) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                DIST[i][j] = -1;
            }
        }

        Queue<Integer> Q = new LinkedList<>();
        Q.add(x);
        Q.add(y);
        DIST[x][y] = 1;
        VISIT[x][y] = true;

        while (!Q.isEmpty()) {
            x = Q.poll();
            y = Q.poll();
            for (int k = 0; k < 4; k++) {
                int nx = x + DIR[k][0];
                int ny = y + DIR[k][1];
                if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                    continue;  // 지도를 벗어나는 곳으로 가는가?
                }
                if (MAP[nx][ny] == 0) {
                    continue;  // 갈 수 있는 칸인지 확인해야 한다.
                }
                if (VISIT[nx][ny]) {
                    continue;  // 이미 방문한 적이 있는 곳인가?
                }
                Q.add(nx);
                Q.add(ny);
                VISIT[nx][ny] = true;
                DIST[nx][ny] = DIST[x][y] + 1;
            }
        }
    }
}
