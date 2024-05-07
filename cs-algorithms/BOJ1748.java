public class BOJ1748 {
    static FastReader SC = new FastReader();
    static int N;

    public static void main(String[] args) {
        N = SC.nextInt();
        int res = 0;
        for (int i = 1; i <= N; i++) {
            if (i >= 1 && i < 10) {
                res += 1;
            }
            else if (i >= 10 && i < 100) {
                res += 2;
            }
            else if (i >= 100 && i < 1000) {
                res += 3;
            }
            else if (i >= 1000 && i < 10000) {
                res += 4;
            }
            else if (i >= 10000 && i < 100000) {
                res += 5;
            }
            else if (i >= 100000 && i < 1_000_000) {
                res += 6;
            }
            else if (i >= 1_000_000 && i < 10_000_000) {
                res += 7;
            }
            else if (i >= 10_000_000 && i < 100_000_000) {
                res += 8;
            }
            else if (i == 100_000_000) {
                res += 9;
            }
        }

        System.out.println(res);
    }
}
