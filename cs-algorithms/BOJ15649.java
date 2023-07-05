public class BOJ15649 {
    static StringBuilder sb = new StringBuilder();
    static FastReader sc = new FastReader();
    static int N;
    static int M;
    static int[] selected;
    static int[] used;

    public static void main(String[] args) {
        N = sc.nextInt();
        M = sc.nextInt();
        selected = new int[M + 1];
        used = new int[N + 1];

        rec_func(1);
        System.out.println(sb);
    }

    static void rec_func(int k) {
        if (k == M + 1) {
            for (int i = 1; i <= M; i++) {
                sb.append(selected[i]).append(" ");
            }
            sb.append("\n");
        }
        else {
            for (int cand = 1; cand <= N; cand++) {
                if (used[cand] == 0) {
                    selected[k] = cand;
                    used[cand] = 1;
                    rec_func(k + 1);
                    selected[k] = 0;
                    used[cand] = 0;
                }
            }
        }
    }
}
