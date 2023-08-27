import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/154540">...</a>
 */
public class TripToDesertIsland {
    public static void main(String[] args) {
        System.out.println(new TripToDesertIsland().solution(new String[]{"X591X", "X1X5X", "X231X", "1XXX1"}));
        System.out.println(new TripToDesertIsland().solution(new String[]{"XXX", "XXX", "XXX"}));
    }

    private static int[][] DIR = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private static boolean[][] VISITED;
    private static int GROUP_CNT;

    public List<Integer> solution(String[] maps) {
        List<Integer> answer = new ArrayList<>();
        VISITED = new boolean[maps.length][maps[0].length()];

        for (int i = 0; i < maps.length; i++) {
            for (int j = 0; j < maps[i].length(); j++) {
                if (maps[i].charAt(j) != 'X' && !VISITED[i][j]) {
                    GROUP_CNT = 0;
                    dfs(i, j, maps);
                    answer.add(GROUP_CNT);
                }
            }
        }

        if (answer.isEmpty()) {
            answer.add(-1);
            return answer;
        }
        return answer.stream().sorted().collect(Collectors.toList());
    }

    private static void dfs(int x, int y, String[] maps) {
        VISITED[x][y] = true;
        GROUP_CNT += Integer.parseInt(String.valueOf(maps[x].charAt(y)));

        for (int i = 0; i < 4; i++) {
            int dx = x + DIR[i][0];
            int dy = y + DIR[i][1];

            if (dx < 0 || dy < 0 || dx >= maps.length || dy >= maps[0].length()) {
                continue;
            }
            if (VISITED[dx][dy]) {
                continue;
            }
            if (maps[dx].charAt(dy) == 'X') {
                continue;
            }

            dfs(dx, dy, maps);
        }
    }
}
