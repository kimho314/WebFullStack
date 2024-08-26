public class BOJ17521 {
    static FastReader SC = new FastReader();
    static int N, W;
    static long[] PRICE;

    public static void main(String[] args) {
        input();
        solve();
    }

    private static void solve() {
        long money = W;
        long coin = 0;
        for (int i = 0; i < N - 1; i++) {
            if (coin == 0 && PRICE[i] < PRICE[i + 1]) {
                coin = money / PRICE[i];
                money -= coin * PRICE[i];
            }

            if (coin > 0 && PRICE[i] > PRICE[i + 1]) {
                money += coin * PRICE[i];
                coin = 0;
            }
        }

        money += coin * PRICE[N - 1];
        System.out.println(money);
    }

    private static void input() {
        N = SC.nextInt();
        W = SC.nextInt();
        PRICE = new long[N];
        for (int i = 0; i < N; i++) {
            PRICE[i] = SC.nextInt();
        }
    }
}
