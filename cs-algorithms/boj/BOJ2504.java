package boj;

import java.util.*;

public class BOJ2504 {
    private static FastReader SC = new FastReader();
    private static char[] STR;

    public static void main(String[] args) {
        input();
        solve();
    }

    private static void input() {
        STR = SC.nextLine().toCharArray();
    }

    private static void solve() {
        ArrayDeque<Character> dq = new ArrayDeque<>();

        int result = 0;
        int value = 1;

        for (int i = 0; i < STR.length; i++) {
            if (STR[i] == '(') {
                dq.addLast(STR[i]);
                value *= 2;
            } else if (STR[i] == '[') {
                dq.addLast(STR[i]);
                value *= 3;
            } else if (STR[i] == ')') {
                if (dq.isEmpty() || dq.peekLast() != '(') {
                    result = 0;
                    break;
                } else if (STR[i - 1] == '(') {
                    result += value;
                }
                dq.pollLast();
                value /= 2;
            } else if (STR[i] == ']') {
                if (dq.isEmpty() || dq.peekLast() != '[') {
                    result = 0;
                    break;
                } else if (STR[i - 1] == '[') {
                    result += value;
                }
                dq.pollLast();
                value /= 3;
            }
        }

        if (!dq.isEmpty()) {
            System.out.println(0);
        } else {
            System.out.println(result);
        }
    }
}
