import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ11403 {
    static StringBuilder sb = new StringBuilder();

    static int N;
    static int[][] map;
    static boolean[] visited;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        N = Integer.parseInt(scanner.nextLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            String[] split = scanner.nextLine().split("");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(split[j]);
            }
        }

        for (int i = 0; i < N; i++) {
            visited = new boolean[N];
            bfs(i);
        }
        System.out.println(sb);
        scanner.close();
    }

    static void bfs(int start) {
        Queue<Integer> needVisit = new LinkedList<>();

        visited[start] = false;
        needVisit.add(start);

        while (!needVisit.isEmpty()) {
            int x = needVisit.poll();

            for (int y = 0; y < N; y++) {
                if (map[x][y] == 0) {
                    continue;
                }

                if (visited[y]) {
                    continue;
                }

                needVisit.add(y);
                visited[y] = true;
            }
        }

        for (int i = 0; i < N; i++) {
            sb.append(visited[i] ? 1 : 0).append(" ");
        }
        sb.append("\n");
    }
}
