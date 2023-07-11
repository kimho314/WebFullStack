public class BOJ14888 {
    static int N;
    static int[] NUMS;
    static int[] OPERATORS = new int[4];
    static int[] ORDERS;
    static int MAX = Integer.MIN_VALUE;
    static int MIN = Integer.MAX_VALUE;
    static FastReader sc = new FastReader();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        N = sc.nextInt();
        NUMS = new int[N + 1];
        ORDERS = new int[N];
        for (int i = 1; i <= N; i++) {
            NUMS[i] = sc.nextInt();
        }

        for (int i = 0; i < 4; i++) {
            OPERATORS[i] = sc.nextInt();
        }

        recFunc(1);
        sb.append(MAX).append("\n").append(MIN);
        System.out.println(sb);
    }

    static void recFunc(int k) {
        if (k == N) { // 계산 결과 값 구하기
            int res = NUMS[1];
            for (int i = 1; i < N; i++) {
                if (ORDERS[i] == 1) {
                    res = res + NUMS[i + 1];
                }
                if (ORDERS[i] == 2) {
                    res = res - NUMS[i + 1];
                }
                if (ORDERS[i] == 3) {
                    res = res * NUMS[i + 1];
                }
                if (ORDERS[i] == 4) {
                    res = res / NUMS[i + 1];
                }
            }
            MIN = Math.min(res, MIN);
            MAX = Math.max(res, MAX);
        }
        else {
            for (int i = 0; i < 4; i++) {
                if (OPERATORS[i] != 0) {
                    OPERATORS[i]--;
                    ORDERS[k] = i + 1; // 1 = +, 2 = -, 3 = *, 4 = /
                    recFunc(k + 1);
                    OPERATORS[i]++;
                    ORDERS[k] = 0;
                }
            }
        }
    }
}
