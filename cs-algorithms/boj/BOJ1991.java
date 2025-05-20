package boj;

import java.util.*;

public class BOJ1991 {
    private static FastReader SC = new FastReader();
    private static int N;
    private static boolean[] VISITED;
    private static ArrayList<Character>[] ADJ;
    private static StringBuilder SB = new StringBuilder();

    public static void main(String[] args) {
        input();
        solve();
    }

    private static void input() {
        N = SC.nextInt();
        VISITED = new boolean[N];
        ADJ = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            ADJ[i] = new ArrayList<>();
        }
        for (int i = 0; i < N; i++) {
            char n = SC.next().charAt(0);
            char l = SC.next().charAt(0);
            char r = SC.next().charAt(0);
            ADJ[n - 'A'].add(l);
            ADJ[n - 'A'].add(r);
        }
    }

    private static void solve() {
        preOrder('A' - 'A');
        SB.append('\n');

        inOrder('A' - 'A');
        SB.append('\n');

        postOrder('A' - 'A');
        SB.append('\n');

        System.out.println(SB);
    }

    private static void inOrder(int n) {
        if ((char) (n + 'A') == '.') {
            return;
        }
        inOrder(ADJ[n].get(0) - 'A');
        SB.append((char) (n + 'A'));
        inOrder(ADJ[n].get(1) - 'A');
    }

    private static void preOrder(int n) {
        if ((char) (n + 'A') == '.') {
            return;
        }
        if (!VISITED[n]) {
            SB.append((char) (n + 'A'));
        }
        VISITED[n] = true;

        for (char elem : ADJ[n]) {
            preOrder(elem - 'A');
        }
    }

    private static void postOrder(int n) {
        if ((char) (n + 'A') == '.') {
            return;
        }
        for (char elem : ADJ[n]) {
            postOrder(elem - 'A');
        }
        SB.append((char) (n + 'A'));
    }
}
