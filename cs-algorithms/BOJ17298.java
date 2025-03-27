import java.util.Arrays;
import java.util.Stack;

public class BOJ17298 {
    private static FastReader SC = new FastReader();
    private static int N;
    private static int[] A;

    public static void main(String[] args) {
        input();
        solve();
    }

    private static void solve() {
        int[] res = new int[N];
        Arrays.fill(res, -1);

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty() && A[stack.peek()] < A[i]) {
                res[stack.pop()] = A[i];
            }

            stack.push(i);
        }

        while (!stack.isEmpty()) {
            res[stack.pop()] = -1;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(res[i]).append(" ");
        }
        System.out.println(sb);
    }

    private static void input() {
        N = SC.nextInt();
        A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = SC.nextInt();
        }
    }
}
