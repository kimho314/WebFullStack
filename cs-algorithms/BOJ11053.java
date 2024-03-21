import java.util.ArrayList;

public class BOJ11053 {
    static FastReader SC = new FastReader();
    static int N;
    static int[] A;

    public static void main(String[] args) {
        N = SC.nextInt();
        A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = SC.nextInt();
        }

        ArrayList<Integer>[] DP = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            DP[i] = new ArrayList<>();
        }
        DP[0].add(A[0]);
        for (int i = 1; i < N; i++) {
            DP[i].addAll(DP[i - 1]);
            if (A[i] > DP[i - 1].get(DP[i - 1].size() - 1)) {
                DP[i].add(A[i]);
            }
        }

        System.out.println(DP[N - 1].size());
    }
}
