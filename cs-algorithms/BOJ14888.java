import java.util.Scanner;

public class BOJ14888 {
    static int N;
    static int[] NUMS;
    static int[] OPERATORS = new int[4];
    static int[] ORDERS;
    static int MAX = Integer.MIN_VALUE;
    static int MIN = Integer.MAX_VALUE;
    static Scanner sc = new Scanner(System.in);
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

//        rec_func(1);
        rec_func2(1, NUMS[1]);
        sb.append(MAX).append("\n").append(MIN);
        System.out.println(sb);
    }

    static int calc() {
        int value = NUMS[0];
        for (int i = 0; i < N; i++) {
            if (ORDERS[i] == 0) {
                value += NUMS[i + 1];
            }
            else if (ORDERS[i] == 1) {
                value -= NUMS[i + 1];
            }
            else if (ORDERS[i] == 2) {
                value *= NUMS[i + 1];
            }
            else {
                value /= NUMS[i + 1];
            }
        }
        return value;
    }

    static void rec_func(int k) {
        if (k == N) { // 다 골랐다!
            int value = calc();
            MAX = Math.max(MAX, value);
            MIN = Math.min(MIN, value);
        }
        else {
            for (int cand = 0; cand < 4; cand++) {
                if (OPERATORS[cand] >= 1) {
                    OPERATORS[cand]--;
                    ORDERS[k] = cand;
                    rec_func(k + 1);
                    OPERATORS[cand]++;
                    ORDERS[k] = 0;
                }
            }
        }
    }

    static int calc2(int operand1, int operator, int operand2) {
        if (operator == 0) {
            return operand1 + operand2;
        }
        else if (operator == 1) {
            return operand1 - operand2;
        }
        else if (operator == 2) {
            return operand1 * operand2;
        }
        else {
            return operand1 / operand2;
        }
    }

    static void rec_func2(int k, int value) {
        if (k == N) { // 다 골랐다!
            MAX = Math.max(MAX, value);
            MIN = Math.min(MIN, value);
        }
        else {
            for (int cand = 0; cand < 4; cand++) {
                if (OPERATORS[cand] >= 1) {
                    OPERATORS[cand]--;
                    rec_func2(k + 1, calc2(value, cand, NUMS[k + 1]));
                    OPERATORS[cand]++;
                }
            }
        }
    }
}
