import java.util.Scanner;

public class BOJ15649 {
    static StringBuilder sb = new StringBuilder();
    static Scanner sc = new Scanner(System.in);

    static int N, M;
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
        if (k == M + 1) { // 다 골랐다!
            // selected[1...M] 배열이 새롭게 탐색된 결과
            for (int i = 1; i <= M; i++) {
                sb.append(selected[i]).append(' ');
            }
            sb.append('\n');
        }
        else {
            for (int cand = 1; cand <= N; cand++) {
                if (used[cand] == 1) {
                    continue;
                }

                // k 번째에 cand 가 올 수 있으면
                selected[k] = cand;
                used[cand] = 1;
                // k+1 번부터 M 번까지 잘 채워주는 함수를 호출해준다.
                rec_func(k + 1);
                selected[k] = 0;
                used[cand] = 0;
            }
        }
    }
}
