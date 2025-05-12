package boj;

import java.util.*;

public class BOJ16953 {
    private static FastReader SC = new FastReader();
    private static int A, B;


    public static void main(String[] args) {
        input();
        solve();
    }

    private static void input() {
        A = SC.nextInt();
        B = SC.nextInt();
    }

    private static void solve() {
        long res = bfs(A);
        System.out.println(res);
    }

    private static long bfs(int start) {
        Queue<Long> q = new LinkedList<>();
        q.add((long) start);
        q.add(1L);

        while (!q.isEmpty()) {
            long n = q.poll();
            long d = q.poll();
            // System.out.println(n + " " + d);
            if (n == B) {
                return d;
            }

            long dn = n * 2;
            long dd = d + 1;
            if (dn > 0 && dn <= B) {
                q.add(dn);
                q.add(dd);

            }
            dn = (n * 10) + 1;
            dd = d + 1;
            if (dn > 0 && dn <= B) {
                q.add(dn);
                q.add(dd);
            }
        }

        return -1;
    }
}
