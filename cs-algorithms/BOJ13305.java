public class BOJ13305 {
    static FastReader SC = new FastReader();
    static int N;
    static int[] PRICE, DIST;

    public static void main(String[] args) {
        N = SC.nextInt();
        DIST = new int[N - 1];
        PRICE = new int[N];
        for (int i = 0; i < N - 1; i++) {
            DIST[i] = SC.nextInt();
        }
        for (int i = 0; i < N; i++) {
            PRICE[i] = SC.nextInt();
        }

        long res = 0;
        int cheap = PRICE[0];
        for (int i = 0; i < N - 1; i++) {
            if (PRICE[i] < cheap) {
                cheap = PRICE[i];
            }
            res += (long) cheap * (long) DIST[i];
        }
        System.out.println(res);
    }
}
