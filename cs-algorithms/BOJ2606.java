import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ2606 {
    static int N;
    static int M;
    static int[][] map;
    static boolean[] visited;
    static int CNT = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = Integer.parseInt(sc.nextLine());
        map = new int[N][N];
        visited = new boolean[N];
        M = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < M; i++) {
            String[] split = sc.nextLine().split("");
            int x = Integer.parseInt(split[0]) - 1;
            int y = Integer.parseInt(split[1]) - 1;
            map[x][y] = 1;
            map[y][x] = 1;
        }

        dfs(0);
        System.out.println(CNT);
        bfs(0);
        CNT = 0;
        for (int i = 1; i < N; i++) {
            if (visited[i]) {
                CNT++;
            }
        }
        System.out.println(CNT);

        sc.close();
    }

    public static void dfs(int start) {
        visited[start] = true;

        for (int y = 0; y < N; y++) {
            if (!visited[y] && map[start][y] == 1) {
                CNT++;
                dfs(y);
            }
        }
    }

    public static void bfs(int start) {
        Queue<Integer> needVisit = new LinkedList<>();
        needVisit.add(start);
        visited[start] = true;

        while (!needVisit.isEmpty()) {
            int x = needVisit.poll();

            for (int y = 0; y < N; y++) {
                if (!visited[y] && map[x][y] == 1) {
                    needVisit.add(y);
                    visited[y] = true;
                }
            }
        }
    }
}
