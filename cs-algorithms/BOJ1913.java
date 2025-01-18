public class BOJ1913 {
    private static FastReader SC = new FastReader();
    private static int N;
    private static int TARGET;

    public static void main(String[] args) {
        input();
        solve();
    }

    private static void solve() {
        int[][] map = new int[N][N];
        int value = 1;
        int limit = 1;

        int x = N / 2, y = N / 2;

        while (true) {
            for (int i = 0; i < limit; i++) {
                map[y--][x] = value++;
            }
            if (value - 1 == N * N) {
                break;
            }
            for (int i = 0; i < limit; i++) {
                map[y][x++] = value++;
            }

            limit++;
            for (int i = 0; i < limit; i++) {
                map[y++][x] = value++;
            }

            for (int i = 0; i < limit; i++) {
                map[y][x--] = value++;
            }
            limit++;
        }

        StringBuilder sb = new StringBuilder();
        int ty = -1, tx = -1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(map[i][j]).append(' ');
                if (TARGET == map[i][j]) {
                    ty = i + 1;
                    tx = j + 1;
                }
            }
            sb.append('\n');
        }
        sb.append(ty).append(' ').append(tx);

        System.out.println(sb);
    }

    private static void input() {
        N = SC.nextInt();
        TARGET = SC.nextInt();
    }
}