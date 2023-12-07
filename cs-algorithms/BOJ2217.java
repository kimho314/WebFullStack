import java.util.Arrays;

public class BOJ2217 {
    static FastReader SC = new FastReader();
    static int N;

    public static void main(String[] args) {
        N = SC.nextInt();
        int[] ROPES = new int[N];
        for (int i = 0; i < N; i++) {
            ROPES[i] = SC.nextInt();
        }

        Arrays.sort(ROPES);
        long max = Long.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            int cnt = 1;
            for (int j = i + 1; j < N; j++) {
                if (ROPES[j] >= ROPES[i]) {
                    cnt++;
                }
            }
            long sum = (long) ROPES[i] * cnt;
//            System.out.println("i = " + ROPES[i] + " sum = " + sum);
            max = Math.max(max, sum);
        }
        System.out.println(max);
    }
}
