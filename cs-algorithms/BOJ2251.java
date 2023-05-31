import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ2251 {
    static class State {
        int[] x;

        State(int[] _x) {
            x = new int[3];
            System.arraycopy(_x, 0, x, 0, 3);
        }

        State move(int from, int to, int[] limit) {
            int[] nx = new int[]{x[0], x[1], x[2]};
            if (x[from] + x[to] <= limit[to]) {
                nx[to] = nx[from] + nx[to];
                nx[from] = 0;
            }
            else {
                nx[from] -= limit[to] - nx[to];
                nx[to] = limit[to];
            }
            return new State(nx);
        }
    }

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    static int[] limit;
    static boolean[] possible;
    static boolean[][][] visit;

    public static void bfs(int x1, int x2, int x3) {
        Queue<State> needVisit = new LinkedList<>();
        visit[x1][x2][x3] = true;
        needVisit.add(new State(new int[]{x1, x2, x3}));

        while (!needVisit.isEmpty()) {
            State st = needVisit.poll();
            if (st.x[0] == 0) {
                possible[st.x[2]] = true;
            }

            for (int from = 0; from < 3; from++) {
                for (int to = 0; to < 3; to++) {
                    if (from == to) {
                        continue;
                    }

                    State nextState = st.move(from, to, limit);
                    if (!visit[nextState.x[0]][nextState.x[1]][nextState.x[2]]) {
                        visit[nextState.x[0]][nextState.x[1]][nextState.x[2]] = true;
                        needVisit.add(nextState);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        limit = new int[3];
        for (int i = 0; i < 3; i++) {
            limit[i] = sc.nextInt();
        }
        visit = new boolean[205][205][205];
        possible = new boolean[205];

        bfs(0, 0, limit[2]);
        for (int i = 0; i <= 200; i++) {
            if (possible[i]) {
                sb.append(i).append(" ");
            }
        }
        System.out.println(sb);
    }
}
