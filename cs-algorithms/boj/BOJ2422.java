package boj;

import java.util.*;

public class BOJ2422 {
    private static FastReader SC = new FastReader();
    private static int N, M;
    private static boolean[][] C;
    private static int CNT;

    public static void main(String[] args) {
        input();
        solve();
    }

    private static void solve() {
        for (int i = 1; i <= N - 2; i++) {
            for (int j = i + 1; j <= N - 1; j++) {
                if (!C[i][j]) {
                    for (int k = j + 1; k <= N; k++) {
                        if (!C[j][k] && !C[i][k]) {
                            // System.out.println(i + " " + j + " " + k);
                            CNT++;
                        }
                    }
                }
            }
        }
        System.out.println(CNT);
    }



    private static void input() {
        N = SC.nextInt();
        M = SC.nextInt();
        C = new boolean[N + 1][N + 1];
        for (int i = 0; i < M; i++) {
            int n1 = SC.nextInt();
            int n2 = SC.nextInt();
            C[n1][n2] = true;
            C[n2][n1] = true;
        }
        CNT = 0;
    }
}
