public class BOJ15650 {
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
        if (k == M + 1) { // 다 골랐다!
            // selected[1...M] 배열이 새롭게 탐색된 결과
            for (int i = 1; i <= M; i++) {
                sb.append(selected[i]).append(' ');
            }
            sb.append('\n');
        }
        else {
            for (int cand = 1; cand <= N; cand++) {
                if (cand <= selected[k - 1]) {
                    continue;
                }
                selected[k] = cand;
                recFunc(k + 1);
                selected[k] = 0;
            }
        }
    }
}
