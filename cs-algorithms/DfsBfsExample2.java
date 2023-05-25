import java.util.LinkedList;
import java.util.Queue;

public class DfsBfsExample2 {
    private static final int N = 5;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        int[][] map = new int[][]{
                {0, 0, 1, 1, 1},
                {0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0},
                {1, 0, 0, 0, 1},
                {1, 1, 0, 1, 0}
        };
        boolean[] visited = new boolean[N];
        dfs(2, map, visited);

        System.out.println(sb.toString());
        sb = new StringBuilder();
        System.out.println();
        visited = new boolean[N];

        bfs(2, map, visited);
        System.out.println(sb.toString());
    }

    public static void dfs(int x, int[][] map, boolean[] visited) {
        visited[x] = true;
        sb.append(x).append(" ");

        for (int idx = 0; idx < N; idx++) {
            if (map[x][idx] == 0) {
                continue;
            }

            if (visited[idx]) {
                continue;
            }

            dfs(idx, map, visited);
        }
    }

    public static void bfs(int x, int[][] map, boolean[] visited) {
        Queue<Integer> needVisit = new LinkedList<>();

        needVisit.add(x);
        visited[x] = true;

        while (!needVisit.isEmpty()) {
            Integer node = needVisit.poll();
            sb.append(node).append(" ");

            for (int y = 0; y < N; y++) {
                if (map[node][y] == 0) {
                    continue;
                }

                if (visited[y]) {
                    continue;
                }

                needVisit.add(y);
                visited[y] = true;
            }
        }
    }
}
