import java.util.Arrays;

public class BOJ20300 {
    static FastReader SC = new FastReader();
    static int N;
    static long[] T;

    public static void main(String[] args) {
        input();
        solve();
    }

    private static void solve() {
        Arrays.sort(T);

        long max = 0;
        if (N % 2 == 0) {
            for (int i = 0; i < N / 2; i++) {
                max = Math.max(max, T[i] + T[N - 1 - i]);
            }
        }
        else {
            max = T[N - 1];
            for (int i = 0; i < (N - 1) / 2; i++) {
                max = Math.max(max, T[i] + T[N - 2 - i]);
            }
        }
        System.out.println(max);
    }

    private static void input() {
        N = SC.nextInt();
        T = new long[N];
        for (int i = 0; i < N; i++) {
            T[i] = SC.nextLong();
        }
    }
}
