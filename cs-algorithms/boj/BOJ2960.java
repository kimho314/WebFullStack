package boj;

import java.util.*;

public class BOJ2960 {
    private static FastReader SC = new FastReader();
    private static int N, K;

    public static void main(String[] args) {
        input();
        solve();
    }

    private static void input() {
        N = SC.nextInt();
        K = SC.nextInt();
    }

    private static void solve() {
        boolean[] nums = new boolean[1001];
        int cnt = 0;
        int res = 0;
        for (int i = 2; i <= N; i++) {
            if (!nums[i] && isPrime(i)) {
                nums[i] = true;
                cnt++;
                // System.out.println(i + " " + cnt);
                if (cnt == K) {
                    res = i;
                }
                for (int j = 2; j * i <= N; j++) {
                    if (nums[i * j]) {
                        continue;
                    }
                    nums[i * j] = true;
                    cnt++;
                    // System.out.println(i * j + " " + cnt);
                    if (cnt == K) {
                        res = i * j;
                    }
                }
            }
        }

        System.out.println(res);
    }

    private static boolean isPrime(int num) {
        for (int i = 2; i <= num / 2; i++) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }
}
