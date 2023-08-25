import java.util.LinkedList;
import java.util.Queue;

/**
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/159993">...</a>
 */
public class EscapingMaze {
    public static void main(String[] args) {
        System.out.println(new EscapingMaze().solution(new String[]{"SOOOL", "XXXXO", "OOOOO", "OXXXX", "OOOOE"}));
        System.out.println(new EscapingMaze().solution(new String[]{"LOOXS", "OOOOX", "OOOOO", "OOOOO", "EOOOO"}));
    }

    private static int[][] DIR = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int solution(String[] maps) {
        int startX = -1;
        int startY = -1;
        int leverX = -1;
        int leverY = -1;
        int endX = -1;
        int endY = -1;


        // find start point
        for (int i = 0; i < maps.length; i++) {
            if (maps[i].contains("S")) {
                startX = i;
                startY = maps[i].indexOf("S");
                break;
            }
        }

        // find lever point
        for (int i = 0; i < maps.length; i++) {
            if (maps[i].contains("L")) {
                leverX = i;
                leverY = maps[i].indexOf("L");
                break;
            }
        }

        // find end point
        for (int i = 0; i < maps.length; i++) {
            if (maps[i].contains("E")) {
                endX = i;
                endY = maps[i].indexOf("E");
                break;
            }
        }

        int leverCnt = bfs(maps, startX, startY, leverX, leverY);
        if (leverCnt == 0) {
            return -1;
        }

        int exitCnt = bfs(maps, leverX, leverY, endX, endY);
        if (exitCnt == 0) {
            return -1;
        }

        return leverCnt + exitCnt;
    }

    private int bfs(String[] maps, int startX, int startY, int endX, int endY) {
        Queue<Integer> needVisit = new LinkedList<>();
        needVisit.add(startX);
        needVisit.add(startY);

        boolean[][] visited = new boolean[maps.length][maps[0].length()];
        int[][] dist = new int[maps.length][maps[0].length()];
        visited[startX][startY] = true;
        dist[startX][startY] = 0;

        while (!needVisit.isEmpty()) {
            int x = needVisit.poll();
            int y = needVisit.poll();

            for (int i = 0; i < 4; i++) {
                int dx = x + DIR[i][0];
                int dy = y + DIR[i][1];

                if (dx < 0 || dy < 0 || dx >= maps.length || dy >= maps[0].length()) {
                    continue;
                }
                if (visited[dx][dy]) {
                    continue;
                }
                if (maps[dx].charAt(dy) == 'X') {
                    continue;
                }

                visited[dx][dy] = true;
                dist[dx][dy] = dist[x][y] + 1;
                needVisit.add(dx);
                needVisit.add(dy);
            }
        }

        return dist[endX][endY];
    }
}
