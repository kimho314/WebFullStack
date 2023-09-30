import java.util.Arrays;

public class BOJ11399 {
    static FastReader SC = new FastReader();
    static int N;
    static int[] P;
    static int[] S;

    public static void main(String[] args) {
        N = SC.nextInt();
        P = new int[N];
        for (int i = 0; i < N; i++) {
            P[i] = SC.nextInt();
        }
        S = new int[N];

        Arrays.sort(P);

        S[0] = P[0];
        for (int i = 1; i < N; i++) {
            S[i] = S[i - 1] + P[i];
        }

        int sum = 0;
        for (int i = 0; i < N; i++) {
            sum += S[i];
        }
        System.out.println(sum);
    }
}
