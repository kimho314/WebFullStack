import java.util.Arrays;

public class BOJ2470_2 {
    static int N;
    static int[] NUMS;
    static FastReader sc = new FastReader();

    public static void main(String[] args) {
        N = sc.nextInt();
        NUMS = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            NUMS[i] = sc.nextInt();
        }

        Arrays.sort(NUMS, 1, N + 1);

        int bestSum = Integer.MAX_VALUE;
        int left = 1, right = N;
        int v1 = 0, v2 = 0;
        while (left < right) {
            int sum = NUMS[left] + NUMS[right];
            if (Math.abs(sum) < bestSum) {
                bestSum = Math.abs(sum);
                v1 = NUMS[left];
                v2 = NUMS[right];
            }

            if (sum <= 0) {
                left++;
            }
            else {
                right--;
            }
        }

        System.out.println(v1 + " " + v2);
    }
}
