public class BOJ15652 {
    static StringBuilder sb = new StringBuilder();
    static FastReader sc = new FastReader();
    static int N, M;
    static int[] selected;

    public static void main(String[] args) {
        N = sc.nextInt();
        M = sc.nextInt();
        selected = new int[M + 1];

        recFunc(1);
        System.out.println(sb);
    }

    static void recFunc(int k) {
        if (k == M + 1) {
            for (int i = 1; i <= M; i++) {
                sb.append(selected[i]).append(" ");
            }
            sb.append("\n");
        }
        else {
            int start = selected[k - 1];
            if (start == 0) {
                start = 1;
            }
            for (int cand = start; cand <= N; cand++) {
                selected[k] = cand;
                recFunc(k + 1);
                selected[k] = 0;
            }
        }
    }
}
