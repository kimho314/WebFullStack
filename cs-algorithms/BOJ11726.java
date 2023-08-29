public class BOJ11726 {
    static FastReader sc = new FastReader();
    static int DIVIDER = 10_007;

    public static void main(String[] args) {
        int n = sc.nextInt();
        int[] D = new int[1001];
        D[0] = 0;
        D[1] = 1;
        D[2] = 2;
        for (int i = 3; i <= 1000; i++) {
            D[i] = (D[i - 1] + D[i - 2]) % DIVIDER;
        }
        System.out.println(D[n]);
    }
}
