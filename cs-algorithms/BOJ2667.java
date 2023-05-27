import java.util.ArrayList;
import java.util.Collections;

public class BOJ2667 {
    static final int N = 7;
    static int GROUP_CNT = 0;
    static boolean[][] VISITED = new boolean[N][N];
    static int[][] DIR = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    static int[][] MAP = {
            {0, 1, 1, 0, 1, 0, 0},
            {0, 1, 1, 0, 1, 0, 1},
            {1, 1, 1, 0, 1, 0, 1},
            {0, 0, 0, 0, 1, 1, 1},
            {0, 1, 0, 0, 0, 0, 0},
            {0, 1, 1, 1, 1, 1, 0},
            {0, 1, 1, 1, 0, 0, 0}
    };

    static ArrayList<Integer> GROUP = new ArrayList<>();
    ;

    public static void main(String[] args) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!VISITED[i][j] && MAP[i][j] == 1) {
                    GROUP_CNT = 0;
                    dfs(i, j);
                    GROUP.add(GROUP_CNT);
                }
            }
        }

        Collections.sort(GROUP);
        for (Integer gr : GROUP) {
            System.out.print(gr + " ");
        }
    }

    public static void dfs(int i, int j) {
        GROUP_CNT++;
        VISITED[i][j] = true;
        for (int k = 0; k < 4; k++) {
            int nx = i + DIR[k][0];
            int ny = j + DIR[k][1];
            if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
                continue;  // 지도를 벗어나는 곳으로 가는가?
            }
            if (MAP[nx][ny] == 0) {
                continue;  // 갈 수 있는 칸인지 확인해야 한다.
            }
            if (VISITED[nx][ny]) {
                continue;  // 이미 방문한 적이 있는 곳인가?
            }
            dfs(nx, ny);
        }
    }

}
