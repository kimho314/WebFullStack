public class BOJ20365 {
    static FastReader SC = new FastReader();
    static int N;
    static String[] COLORS;

    public static void main(String[] args) {
        N = SC.nextInt();
        COLORS = new String[N];

        String input = SC.nextLine();
        char[] arr = input.toCharArray();
        for (int i = 0; i < N; i++) {
            COLORS[i] = String.valueOf(arr[i]);
        }

        int dominant = getDominantColorCnt();
        int res = dominant + 1;

        System.out.println(res);
    }

    private static int getDominantColorCnt() {
        int cntR = 0;
        int cntB = 0;
        String cur = "";
        for (int i = 0; i < N; i++) {
            if (!cur.equals(COLORS[i])) {
                if (COLORS[i].equals("R")) {
                    cntR++;
                }
                else {
                    cntB++;
                }
            }
            cur = COLORS[i];
        }

        return Math.min(cntR, cntB);
    }
}
