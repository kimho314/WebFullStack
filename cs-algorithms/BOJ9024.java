import java.util.Arrays;

public class BOJ9024 {
    static FastReader SC = new FastReader();
    static StringBuilder SB = new StringBuilder();

    public static void main(String[] args) {
        int t = SC.nextInt();
        while (t > 0) {
            t--;
            int n = SC.nextInt();
            int k = SC.nextInt();
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = SC.nextInt();
            }

            Arrays.sort(nums);
            int cnt = 0;
            int minDiff = Integer.MAX_VALUE;
            int right = 0;
            for (int left = 0; left < n; left++) {
                
            }
        }
    }
}
