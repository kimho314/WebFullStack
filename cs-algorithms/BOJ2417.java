import java.math.BigInteger;

public class BOJ2417 {
    static FastReader SC = new FastReader();
    static Long N;

    public static void main(String[] args) {
        input();
        solve();
    }

    private static void solve() {
        BigInteger l = BigInteger.ZERO;
        BigInteger r = BigInteger.valueOf(Long.MAX_VALUE);
        BigInteger res = BigInteger.ZERO;
        while (l.compareTo(r) <= 0) {
            BigInteger mid = new BigInteger(String.valueOf(l.add(r).divide(BigInteger.valueOf(2))));
            if (determine(mid)) {
                r = mid.subtract(BigInteger.ONE);
                res = mid;
            }
            else {
                l = mid.add(BigInteger.ONE);
            }
        }
        System.out.println(res);
    }

    private static boolean determine(BigInteger mid) {
        return mid.pow(2).compareTo(new BigInteger(String.valueOf(N))) >= 0;
    }

    private static void input() {
        N = SC.nextLong();
    }
}
