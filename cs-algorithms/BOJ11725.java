import java.util.ArrayList;

public class BOJ11725 {
    static FastReader SC = new FastReader();
    static int N;
    static ArrayList<Integer>[] ADJ;
    static boolean[] VISITED;
    static int[] ROOT;

    public static void main(String[] args) {
        N = SC.nextInt();
        VISITED = new boolean[N + 1];
        ROOT = new int[N + 1];
        ADJ = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            ADJ[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            int p1 = SC.nextInt();
            int p2 = SC.nextInt();
            ADJ[p1].add(p2);
            ADJ[p2].add(p1);
        }

        dfs(1, 1);
        for (int i = 2; i <= N; i++) {
            System.out.println(ROOT[i]);
        }
    }

    private static void dfs(int start, int parent) {
        VISITED[start] = true;
        ROOT[start] = parent;

        for (int x : ADJ[start]) {
            if (VISITED[x]) {
                continue;
            }

            VISITED[x] = true;
            dfs(x, start);
        }
    }
}
