import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ16953 {
    static FastReader SC = new FastReader();
    static int A, B;
    static int[] VISITED;

    public static void main(String[] args) {
        A = SC.nextInt();
        B = SC.nextInt();
        VISITED = new int[B + 1];
        Arrays.fill(VISITED, -1);

        bfs();
        System.out.println(VISITED[B] == -1 ? -1 : VISITED[B] + 1);
    }

    private static void bfs() {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(A);
        VISITED[A] = 0;

        while (!queue.isEmpty()) {
            int n = queue.poll();

            int dn = n * 2;
            if (dn <= B && VISITED[dn] == -1) {
                VISITED[dn] = VISITED[n] + 1;
                queue.add(dn);
            }
            dn = Integer.parseInt(n + String.valueOf(1));
            if (dn <= B && VISITED[dn] == -1) {
                VISITED[dn] = VISITED[n] + 1;
                queue.add(dn);
            }
        }
    }
}
