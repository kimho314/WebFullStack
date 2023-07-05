public class BOJ15651 {
    static StringBuilder sb = new StringBuilder();
    static FastReader sc = new FastReader();
    static int N;
    static int M;
    static int[] selected;

    public static void main(String[] args) {
        N = sc.nextInt();
        M = sc.nextInt();
        selected = new int[M + 1];
        rec_fun(1);
        System.out.println(sb);
    }

    static void rec_fun(int k) {
        if (k == M + 1) {
            for (int i = 1; i <= M; i++) {
                sb.append(selected[i]).append(" ");
            }
            sb.append("\n");
        }
        else {
            for (int cand = 1; cand <= N; cand++) {
                selected[k] = cand;
                rec_fun(k + 1);
                selected[k] = 0;
            }
        }
    }
}
