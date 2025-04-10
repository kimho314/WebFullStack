package boj;

import java.util.Arrays;

public class BOJ10815 {
    private static FastReader SC = new FastReader();
    private static int N, M;
    private static int[] NUMS1, NUMS2;

    public static void main(String[] args) {
        input();
        solve();
    }

    private static void solve() {
        Arrays.sort(NUMS1);

        int[] res = new int[M];
        for (int i = 0; i < M; i++) {
            int idx = binarySearch(NUMS2[i]);
            if (idx == -1) {
                res[i] = 0;
            }
            else {
                res[i] = 1;
            }
        }

        for (int i = 0; i < M; i++) {
            System.out.print(res[i] + " ");
        }
    }

    private static int binarySearch(int target) {
        int res = -1;
        int left = 0;
        int right = N - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (NUMS1[mid] == target) {
                res = mid;
                break;
            }
            else if (NUMS1[mid] > target) {
                right = mid - 1;
            }
            else {
                left = mid + 1;
            }
        }

        return res;
    }

    private static void input() {
        N = SC.nextInt();
        NUMS1 = new int[N];
        for (int i = 0; i < N; i++) {
            NUMS1[i] = SC.nextInt();
        }
        M = SC.nextInt();
        NUMS2 = new int[M];
        for (int i = 0; i < M; i++) {
            NUMS2[i] = SC.nextInt();
        }
    }
}
