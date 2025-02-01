import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

public class BOJ14002 {
    private static FastReader SC = new FastReader();
    private static int N;
    private static int[] A;

    public static void main(String[] args) {
        input();
        solve();
    }

    private static void solve() {
        int[] dp = new int[N];
        Arrays.fill(dp, 1);
        LinkedList<Integer>[] list = new LinkedList[N];
        for (int i = 0; i < N; i++) {
            list[i] = new LinkedList<>();
            list[i].add(A[i]);
        }

        for (int i = 1; i < N; i++) {
            int maxIdx = i;
            for (int j = 0; j < i; j++) {
                if (A[j] < A[i]) {
                    if (dp[i] < dp[j] + 1) {
                        maxIdx = j;
                    }
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

//            System.out.println(i + " " + maxIdx);
            if (maxIdx < i) {
                list[i].addAll(list[maxIdx]);
            }
        }

        int max = 0;
        int maxIdx = 0;
        for (int i = 0; i < N; i++) {
            if (max < dp[i]) {
                maxIdx = i;
            }
            max = Math.max(max, dp[i]);
        }
        System.out.println(max);
        StringBuilder sb = new StringBuilder();
        Iterator<Integer> iter = list[maxIdx].descendingIterator();
        while (iter.hasNext()) {
            sb.append(iter.next()).append(" ");
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
