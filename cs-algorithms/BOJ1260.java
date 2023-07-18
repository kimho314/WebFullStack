import java.util.*;

public class BOJ1260 {
    static FastReader sc = new FastReader();
    static int N, M, V;
    static boolean[] visited;
    static List<List<Integer>> list = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        N = sc.nextInt();
        M = sc.nextInt();
        V = sc.nextInt();
        visited = new boolean[N + 1];
        for (int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
        }
        for (int i = 1; i <= M; i++) {
            int node1 = sc.nextInt();
            int node2 = sc.nextInt();
            List<Integer> subList1 = list.remove(node1);
            subList1.add(node2);
            list.add(node1, subList1);
            List<Integer> subList2 = list.remove(node2);
            subList2.add(node1);
            list.add(node2, subList2);
        }
        list.forEach(it -> it.sort(Comparator.comparingInt(each -> each)));

        dfs(V);
        System.out.println(sb);
        sb = new StringBuilder();
        visited = new boolean[N + 1];
        bfs(V);
        System.out.println(sb);
    }

    static void bfs(int start) {
        Queue<Integer> needVisit = new LinkedList<>();

        needVisit.add(start);
        visited[start] = true;

        while (!needVisit.isEmpty()) {
            int x = needVisit.poll();
            sb.append(x).append(" ");

            for (Integer elem : list.get(x)) {
                if (visited[elem]) {
                    continue;
                }

                needVisit.add(elem);
                visited[elem] = true;
            }
        }
    }

    static void dfs(int start) {
        visited[start] = true;
        sb.append(start).append(" ");

        for (Integer elem : list.get(start)) {
            if (visited[elem]) {
                continue;
            }

            dfs(elem);
        }
    }
}
