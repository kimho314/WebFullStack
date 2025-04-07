package boj;

import java.util.ArrayList;

public class BOJ21937 {
    private static FastReader SC = new FastReader();
    private static int N, M, X;
    private static ArrayList<Integer>[] ADJ;
    private static boolean[] VISITED;
    private static int CNT = 0;

    public static void main(String[] args) {
        input();
        solve();
    }

    private static void solve() {
        dfs(X);
        System.out.println(CNT);
    }

    private static void dfs(int x) {
        VISITED[x] = true;
        for (Integer n : ADJ[x]) {
            if (!VISITED[n]) {
                CNT++;
                dfs(n);
            }
        }
    }


    private static void input() {
        N = SC.nextInt();
        M = SC.nextInt();
        ADJ = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            ADJ[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            int a = SC.nextInt();
            int b = SC.nextInt();
            ADJ[b].add(a);
        }
        X = SC.nextInt();
        VISITED = new boolean[N + 1];
    }
}
