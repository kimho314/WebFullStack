import java.util.PriorityQueue;

public class BOJ1463 {
    static FastReader SC = new FastReader();
    static int N;
    static int[] DP = new int[1000001];

    public static void main(String[] args) {
        N = SC.nextInt();
        DP[0] = 0;
        DP[1] = 0;
        DP[2] = 1;
        DP[3] = 1;
        for (int i = 4; i <= 1000000; i++) {
            PriorityQueue<Integer> pq = new PriorityQueue<>();
            if (i % 3 == 0) {
                pq.add(DP[i / 3]);
            }
            if (i % 2 == 0) {
                pq.add(DP[i / 2]);
            }
            pq.add(DP[i - 1]);

            Integer min = pq.poll();
            DP[i] = min + 1;
        }
        System.out.println(DP[N]);
    }
}
