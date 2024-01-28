import java.util.Arrays;

public class BOJ1758 {
    static FastReader SC = new FastReader();
    static int N;
    static int[] A;

    public static void main(String[] args) {
        N = SC.nextInt();
        A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = SC.nextInt();
        }

        Arrays.sort(A);
        int sum = 0;
        for (int i = A.length - 1; i >= 0; i--) {
            int score = A[i] - (A.length - 1 - i);
            if (score < 0) {
                score = 0;
            }
            sum += score;
        }
        System.out.println(sum);
    }
}
