import java.util.Arrays;

public class BOJ1654 {
    static FastReader SC = new FastReader();
    static int K, N;
    static int[] WIRES;

    public static void main(String[] args) {
        K = SC.nextInt();
        N = SC.nextInt();
        WIRES = new int[K];
        for (int i = 0; i < K; i++) {
            WIRES[i] = SC.nextInt();
        }
        Arrays.sort(WIRES);

        long l = 1;
        long r = Integer.MAX_VALUE;
        long res = 0;
        while (l <= r) {
            long mid = (r + l) / 2;
            if (determination(mid)) {
                l = mid + 1;
                res = mid;
            }
            else {
                r = mid - 1;
            }
        }

        System.out.println(res);
    }

    private static boolean determination(long mid) {
        long cnt = 0;
        for (int i = 0; i < K; i++) {
            cnt += WIRES[i] / mid;
        }

        return cnt >= N;
    }
}
