import java.util.ArrayList;

public class BOJ2606 {
    static FastReader SC = new FastReader();
    static int N, M;
    static ArrayList<Integer>[] MAP;
    static boolean[] VISITED;
    static int CNT;


    public static void main(String[] args) {
        N = SC.nextInt();
        M = SC.nextInt();
        MAP = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            MAP[i] = new ArrayList<>();
        }
        VISITED = new boolean[N + 1];
        for (int i = 1; i <= M; i++) {
            int num1 = SC.nextInt();
            int num2 = SC.nextInt();
            MAP[num1].add(num2);
            MAP[num2].add(num1);
        }

        CNT = 0;
        dfs(1);
        System.out.println(CNT);
    }

    private static void dfs(int start) {
        VISITED[start] = true;
        if (start != 1) {
            CNT++;
        }

        for (int x : MAP[start]) {
            if (VISITED[x]) {
                continue;
            }

            dfs(x);
        }
    }

}
