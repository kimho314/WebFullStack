public class BOJ9663 {

    static Integer N, ANS;

    static int[] COL;
    static FastReader SC = new FastReader();

    public static void main(String[] args) {
        N = SC.nextInt();
        ANS = 0;
        COL = new int[N + 1];
        recFunc(1);
        System.out.println(ANS);
    }

    private static void recFunc(int row) {
        if (row == N + 1) {
            if (validityCheck()) {
                ANS++;
            }
        }
        else {
            for (int c = 1; c <= N; c++) {
                COL[row] = c;
                recFunc(row + 1);
                COL[row] = 0;
            }
        }
    }

    private static boolean validityCheck() {
        for (int i = 1; i < N; i++) {
            for (int j = i + 1; j <= N; j++) {
                if (COL[i] == COL[j]) {
                    return false;
                }
                if (COL[i] + i == COL[j] + j) {
                    return false;
                }
                if (COL[i] - i == COL[j] - j) {
                    return false;
                }
            }
        }
        return true;
    }
}
