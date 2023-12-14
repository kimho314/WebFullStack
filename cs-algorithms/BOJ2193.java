public class BOJ2193 {
    static FastReader SC = new FastReader();
    static int N;

    public static void main(String[] args) {
        N = SC.nextInt();
        long[] nums = new long[91];

        nums[0] = 0;
        nums[1] = 1;
        nums[2] = 1;
        for (int i = 3; i <= 90; i++) {
            nums[i] = nums[i - 1] + nums[i - 2];
        }
        System.out.println(nums[N]);
    }
}
