import java.util.LinkedList;
import java.util.Queue;

public class BOJ16953 {
    static FastReader SC = new FastReader();
    static long A, B;
    static long RES = -1;

    public static void main(String[] args) {
        A = SC.nextInt();
        B = SC.nextInt();

        bfs();
        System.out.println(RES);
    }

    private static void bfs() {
        Queue<Long> queue = new LinkedList<>();
        queue.add(A);
        queue.add(1L);

        while (!queue.isEmpty()) {
            long n = queue.poll();
            long cnt = queue.poll();
            if (n == B) {
                RES = cnt;
                break;
            }

            long dn = n * 2;
            if (dn <= B) {
                queue.add(dn);
                queue.add(cnt + 1);
            }
            dn = n * 10 + 1;
            if (dn <= B) {
                queue.add(dn);
                queue.add(cnt + 1);
            }
        }
    }
}
