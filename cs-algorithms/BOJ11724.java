import java.util.ArrayList;
import java.util.List;

public class BOJ11724 {
    static FastReader sc = new FastReader();
    static int N, M;
    static List<Integer> GROUP;
    static int GROUP_CNT;
    static boolean[] VISITED;
    static ArrayList<Integer>[] LIST;

    public static void main(String[] args) {
        N = sc.nextInt();
        M = sc.nextInt();
        VISITED = new boolean[N + 1];
        LIST = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            LIST[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            LIST[u].add(v);
            LIST[v].add(u);
        }

        GROUP = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            if (!VISITED[i]) {
                GROUP_CNT = 0;
                dfs(i);
                GROUP.add(GROUP_CNT);
            }
        }
        System.out.println(GROUP.size());
    }

    private static void dfs(int start) {
        VISITED[start] = true;
        GROUP_CNT++;

        for (int x : LIST[start]) {
            if (VISITED[x]) {
                continue;
            }
            VISITED[x] = true;
            dfs(x);
        }
    }
}
