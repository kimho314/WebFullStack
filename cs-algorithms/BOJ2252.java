import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

public class BOJ2252 {
    static FastReader sc = new FastReader();
    static StringBuilder sb = new StringBuilder();
    static int N, M;
    static int[] INDEG;
    static ArrayList<Integer>[] ADJ;

    public static void main(String[] args) {
        N = sc.nextInt();
        M = sc.nextInt();
        ADJ = new ArrayList[N + 1];
        INDEG = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            ADJ[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            ADJ[x].add(y);
            INDEG[y]++;
        }

        Deque<Integer> queue = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            queue.add(i);
        }

        while (!queue.isEmpty()) {
            int x = queue.poll();
            sb.append(x).append(' ');
            for (int y : ADJ[x]) {
                INDEG[y]--;
                if (INDEG[y] == 0) {
                    queue.add(y);
                }
            }
        }
        System.out.println(sb);
    }
}
