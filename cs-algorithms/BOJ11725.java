import java.util.ArrayList;

public class BOJ11725 {
    static FastReader sc = new FastReader();
    static StringBuilder sb = new StringBuilder();
    static int N;
    static ArrayList<Integer>[] ADJ;
    static int[] PARENT;


    public static void main(String[] args) {
        N = sc.nextInt();
        PARENT = new int[N + 1];
        ADJ = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            ADJ[i] = new ArrayList<>();
        }

        for (int i = 1; i < N; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            ADJ[x].add(y);
            ADJ[y].add(x);
        }

        dfs(1, -1);
        for (int i = 2; i <= N; i++) {
            sb.append(PARENT[i]).append("\n");
        }
        System.out.println(sb);
    }

    static void dfs(int x, int parent) {
        for (int y : ADJ[x]) {
            if (y == parent) {
                continue;
            }

            PARENT[y] = x;
            dfs(y, x);
        }
    }
}
