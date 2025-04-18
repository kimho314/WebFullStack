package boj;

import java.util.*;
import java.io.*;

public class BOJ2217 {
    private static FastReader SC = new FastReader();
    private static int N;
    private static int[] W;

    public static void main(String[] args) {
        input();
        solve();
    }

    private static void solve() {
        Arrays.sort(W);

        int res = W[0] * N;
        for (int i = 1; i < N; i++) {
            if (W[i] * (N - i) > res) {
                res = W[i] * (N - i);
            }
        }
        System.out.println(res);
    }

    private static void input() {
        N = SC.nextInt();
        W = new int[N];
        for (int i = 0; i < N; i++) {
            W[i] = SC.nextInt();
        }
    }
}
