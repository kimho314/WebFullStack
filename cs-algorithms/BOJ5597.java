import java.util.PriorityQueue;

public class BOJ5597 {
    private static FastReader SC = new FastReader();
    private static int[] STUDENTS;

    public static void main(String[] args) {
        input();
        solve();
    }

    private static void solve() {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 1; i <= 30; i++) {
            if (STUDENTS[i] == 0) {
                pq.add(i);
            }
        }

        for (int i = 0; i < 2; i++) {
            System.out.println(pq.poll());
        }
    }

    private static void input() {
        STUDENTS = new int[31];
        for (int i = 0; i < 28; i++) {
            int num = SC.nextInt();
            STUDENTS[num]++;
        }
    }
}
