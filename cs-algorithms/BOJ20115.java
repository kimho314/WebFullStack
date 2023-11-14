import java.util.Arrays;

public class BOJ20115 {
    static FastReader SC = new FastReader();
    static int N;
    static int[] X;

    public static void main(String[] args) {
        N = SC.nextInt();
        X = new int[N];
        for (int i = 0; i < N; i++) {
            X[i] = SC.nextInt();
        }
        Arrays.sort(X);

        double pick = X[X.length - 1];
        for (int i = 0; i < N - 1; i++) {
            pick += X[i] / 2.0;
        }
        System.out.println(pick);
    }
}
