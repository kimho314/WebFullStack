import java.util.ArrayList;
import java.util.Comparator;

public class BOJ2667 {
    static int N;
    static int GROUP_CNT = 0;
    static boolean[][] VISITED;
    static int[][] DIR = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    static int[][] MAP;

    static ArrayList<Integer> GROUP = new ArrayList<>();
    static FastReader sc = new FastReader();

    public static void main(String[] args) {
        N = sc.nextInt();
        VISITED = new boolean[N][N];
        MAP = new int[N][N];
        for (int i = 0; i < N; i++) {
            String[] next = sc.next().split("");
            for (int j = 0; j < N; j++) {
                MAP[i][j] = Integer.parseInt(next[j]);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (MAP[i][j] == 1 && !VISITED[i][j]) {
                    dfs(i, j);
                    GROUP.add(GROUP_CNT);
                    GROUP_CNT = 0;
                }
            }
        }

        GROUP.sort(Comparator.comparingInt(it -> it));
        System.out.println(GROUP.size());
        GROUP.forEach(System.out::println);
    }

    public static void dfs(int x, int y) {
        VISITED[x][y] = true;
        GROUP_CNT++;

        for (int i = 0; i < 4; i++) {
            int dx = x + DIR[i][0];
            int dy = y + DIR[i][1];

            if (dx < 0 || dy < 0 || dx >= N | dy >= N) {
                continue;
            }
            if (MAP[dx][dy] == 0) {
                continue;
            }
            if (VISITED[dx][dy]) {
                continue;
            }

            dfs(dx, dy);
        }
    }

}
