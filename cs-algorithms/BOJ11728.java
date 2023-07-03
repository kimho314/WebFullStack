public class BOJ11728 {
    static FastReader sc = new FastReader();
    static int N;
    static int M;
    static int[] A, B;

    public static void main(String[] args) {
        N = sc.nextInt();
        M = sc.nextInt();
        A = new int[N + 1];
        B = new int[M + 1];
        for (int i = 1; i <= N; i++) {
            A[i] = sc.nextInt();
        }
        for (int i = 1; i <= M; i++) {
            B[i] = sc.nextInt();
        }

        StringBuilder ans = new StringBuilder();
        int left = 1, right = 1;
        while (left <= N && right <= M) {
            if (A[left] <= B[right]) {
                ans.append(A[left]).append(" ");
                left++;
            }
            else {
                ans.append(B[right]).append(" ");
                right++;
            }
        }

        while (left <= N) {
            ans.append(A[left++]).append(' ');
        }
        while (right <= M) {
            ans.append(B[right++]).append(' ');
        }

        System.out.println(ans);
    }
}
