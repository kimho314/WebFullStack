public class BOJ11047 {
    static FastReader SC = new FastReader();
    static int N, K;

    static int[] A;

    public static void main(String[] args) {
        N = SC.nextInt();
        K = SC.nextInt();
        A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = SC.nextInt();
        }

        int cnt = 0;
        for (int i = A.length - 1; i >= 0; i--) {
            if (A[i] > K) {
                continue;
            }

            int tmpCnt = K / A[i];
            cnt += tmpCnt;
            K %= (A[i] * tmpCnt);
        }

        System.out.println(cnt);
    }
}
