import java.util.Arrays;

public class BOJ10815 {
    static FastReader SC = new FastReader();
    static int N, M;
    static StringBuilder SB = new StringBuilder();
    static int[] A, B;

    public static void main(String[] args) {
        N = SC.nextInt();
        A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = SC.nextInt();
        }
        Arrays.sort(A);

        M = SC.nextInt();
        B = new int[M];
        for (int i = 0; i < M; i++) {
            B[i] = SC.nextInt();
        }

        for (int i = 0; i < M; i++) {
            int res = search(A, 0, N - 1, B[i]);
            SB.append(res).append(' ');
        }
        System.out.println(SB);
    }

    private static int search(int[] arr, int left, int right, int target) {
        int res = 0;

        while (left <= right) {
            int mid = (right + left) / 2;
            if (arr[mid] == target) {
                res = 1;
                break;
            }

            if (arr[mid] > target) {
                right = mid - 1;
            }
            else {
                left = mid + 1;
            }
        }

        return res;
    }
}
