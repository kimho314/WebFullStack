public class BOJ15988 {
    static FastReader SC = new FastReader();
    static int T;
    static long[] D;
    static StringBuilder SB = new StringBuilder();

    public static void main(String[] args) {
        T = SC.nextInt();
        D = new long[1000001];
        D[0] = 0;
        D[1] = 1;
        D[2] = 2;
        D[3] = 4;
        for (int i = 4; i <= 1000000; i++) {
            D[i] = (D[i - 1] + D[i - 2] + D[i - 3]) % 1000000009;
        }
        while (T > 0) {
            T--;
            int n = SC.nextInt();
            SB.append(D[n]).append('\n');
        }
        System.out.println(SB);
    }
}
