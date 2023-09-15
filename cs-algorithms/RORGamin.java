import java.util.LinkedList;
import java.util.Queue;

/**
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/1844">...</a>
 */
public class RORGamin {
    public static void main(String[] args) {
        System.out.println(new RORGamin().solution(new int[][]{{1, 0, 1, 1, 1}, {1, 0, 1, 0, 1}, {1, 0, 1, 1, 1}, {1, 1, 1, 0, 1}, {0, 0, 0, 0, 1}}));
        System.out.println(new RORGamin().solution(new int[][]{{1, 0, 1, 1, 1}, {1, 0, 1, 0, 1}, {1, 0, 1, 1, 1}, {1, 1, 1, 0, 0}, {0, 0, 0, 0, 1}}));
    }

    public int solution(int[][] maps) {
        int answer = 0;
        boolean[][] visited = new boolean[maps.length][maps[0].length];
        int[][] dist = new int[maps.length][maps[0].length];
        for (int i = 0; i < dist.length; i++) {
            for (int j = 0; j < dist[0].length; j++) {
                dist[i][j] = -1;
            }
        }
        int[][] dir = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        bfs(0, 0, maps, visited, dist, dir);
        answer = dist[maps.length - 1][maps[0].length - 1];
        return answer;
    }

    private void bfs(int x, int y, int[][] maps, boolean[][] visited, int[][] dist, int[][] dir) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(x);
        queue.add(y);
        dist[x][y] = 1;
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            int tempX = queue.poll();
            int tempY = queue.poll();

            for (int i = 0; i < 4; i++) {
                int dx = tempX + dir[i][0];
                int dy = tempY + dir[i][1];

                if (dx < 0 || dy < 0 || dx >= maps.length || dy >= maps[0].length) {
                    continue;
                }
                if (visited[dx][dy] || maps[dx][dy] == 0) {
                    continue;
                }

                queue.add(dx);
                queue.add(dy);
                visited[dx][dy] = true;
                dist[dx][dy] = dist[tempX][tempY] + 1;
            }
        }
    }
}
