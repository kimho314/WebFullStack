import java.util.Arrays;

public class BOJ11663 {
    static FastReader SC = new FastReader();
    static int N, M;
    static long[] POS;
    static StringBuilder SB = new StringBuilder();

    public static void main(String[] args) {
        N = SC.nextInt();
        M = SC.nextInt();
        POS = new long[N];
        for (int i = 0; i < N; i++) {
            POS[i] = SC.nextLong();
        }

        Arrays.sort(POS);
        for (int i = 0; i < M; i++) {
            solve();
        }
        System.out.println(SB.toString());
    }

    private static void solve() {
        long min = SC.nextLong();
        long max = SC.nextLong();

        int lowerBound = getLowerBound(min);
        int upperBound = getUpperBound(max);

        int cnt = upperBound - lowerBound;

        SB.append(cnt).append('\n');
    }

    private static int getUpperBound(long max) {
        int l = 0;
        int r = N - 1;
        while (l <= r) {
            int mid = (r + l) / 2;
            if (POS[mid] > max) {
                r = mid - 1;
            }
            else {
                l = mid + 1;
            }
        }
        return r + 1;
    }

    private static int getLowerBound(long min) {
        int l = 0;
        int r = N - 1;
        while (l <= r) {
            int mid = (r + l) / 2;
            if (POS[mid] < min) {
                l = mid + 1;
            }
            else {
                r = mid - 1;
            }
        }
        return l;
    }
}
