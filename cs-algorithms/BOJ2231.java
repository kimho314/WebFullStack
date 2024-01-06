public class BOJ2231 {
    static FastReader SC = new FastReader();
    static int N;

    public static void main(String[] args) {
        N = SC.nextInt();

        int res = 0;
        for (int i = 1; i < N; i++) {
            int sum = i;
            String num = String.valueOf(i);
            String[] split = num.split("");
            for (String s : split) {
                sum += Integer.parseInt(s);
            }

            if (sum == N) {
                res = i;
                break;
            }
        }

        System.out.println(res);
    }
}
