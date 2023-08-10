import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

public class BOJ1005 {
    static FastReader sc = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N, M;
    static int[] indeg, T_done, T;
    static ArrayList<Integer>[] adj;

    public static void main(String[] args) {
        int Q = sc.nextInt();
        while (Q > 0) {
            Q--;
            input();
            process();
        }
    }

    private static void process() {
        Deque<Integer> queue = new LinkedList<>();
        // 제일 앞에 "정렬될 수 있는" 정점 찾기
        for (int i = 1; i <= N; i++)
            if (indeg[i] == 0) {
                queue.add(i);
                T_done[i] = T[i];
            }

        // 위상 정렬 순서대로 T_done 계산을 함께 해주기
        while (!queue.isEmpty()) {
            int x = queue.poll();
            for (int y : adj[x]) {
                indeg[y]--;
                if (indeg[y] == 0) {
                    queue.add(y);
                }
                T_done[y] = Math.max(T_done[y], T_done[x] + T[y]);
            }
        }
        int W = sc.nextInt();
        System.out.println(T_done[W]);
    }

    private static void input() {
        N = sc.nextInt();
        M = sc.nextInt();
        adj = new ArrayList[N + 1];
        indeg = new int[N + 1];
        T = new int[N + 1];
        T_done = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
            T[i] = sc.nextInt();
        }
        for (int i = 0; i < M; i++) {
            int x = sc.nextInt(), y = sc.nextInt();
            adj[x].add(y);
            // indegree 계산하기
            indeg[y]++;
        }

    }
}
