public class BOJ3085 {
    static FastReader SC = new FastReader();
    static int N;

    public static void main(String[] args) {
        N = SC.nextInt();
        char[][] map = new char[N][N];
        for (int i = 0; i < N; i++) {
            char[] arr = SC.nextLine().toCharArray();
            for (int j = 0; j < N; j++) {
                map[i][j] = arr[j];
            }
        }

        int res = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i + 1 < N && map[i][j] != map[i + 1][j]) {
                    char temp = map[i][j];
                    map[i][j] = map[i + 1][j];
                    map[i + 1][j] = temp;

                    int cnt = check(map);
                    res = Math.max(res, cnt);

                    temp = map[i][j];
                    map[i][j] = map[i + 1][j];
                    map[i + 1][j] = temp;
                }

                if (j + 1 < N && map[i][j] != map[i][j + 1]) {
                    char temp = map[i][j];
                    map[i][j] = map[i][j + 1];
                    map[i][j + 1] = temp;

                    int cnt = check(map);
                    res = Math.max(res, cnt);

                    temp = map[i][j];
                    map[i][j] = map[i][j + 1];
                    map[i][j + 1] = temp;
                }
            }
        }
        System.out.println(res);
    }

    private static int check(char[][] arr) {
        int ans = 1;
        for (int i = 0; i < N; i++) {
            int cnt = 1;
            for (int j = 0; j < N - 1; j++) {
                if (arr[i][j] == arr[i][j + 1]) {
                    ++cnt;
                }
                else {
                    cnt = 1;
                }

                ans = Math.max(cnt, ans);
            }

            cnt = 1;
            for (int j = 0; j < N - 1; j++) {
                if (arr[j][i] == arr[j + 1][i]) {
                    ++cnt;
                }
                else {
                    cnt = 1;
                }

                ans = Math.max(cnt, ans);
            }
        }

        return ans;
    }
}
