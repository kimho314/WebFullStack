public class KaoMo3 {
    public static void main(String[] args) {
        KaoMo3 kaoMo3 = new KaoMo3();
        System.out.println(kaoMo3.solution(new int[]{3, 2, 3, 2, 3}));
        System.out.println(kaoMo3.solution(new int[]{7, 4, -2, 4, -2, -9}));
        System.out.println(kaoMo3.solution(new int[]{7, -5, -5, -5, 7, -1, 7}));
        System.out.println(kaoMo3.solution(new int[]{4}));
    }

    public int solution(int[] A) {
        // Handle edge cases
        if (A == null || A.length == 0) {
            return 0;
        }
        if (A.length == 1) {
            return 1;
        }

        int maxLength = 1;
        int left = 0;  // Left pointer of current slice

        while (left < A.length) {
            // Initialize values for even and odd positions
            int evenValue = A[left];
            int oddValue = Integer.MIN_VALUE;
            boolean oddInitialized = false;

            // Right pointer starts at left and extends as far as possible
            int right = left;

            while (right < A.length) {
                // Check if current element follows the pattern
                if ((right - left) % 2 == 0) {  // Even position relative to left
                    if (A[right] != evenValue) {
                        break;
                    }
                }
                else {  // Odd position relative to left
                    if (!oddInitialized) {
                        oddValue = A[right];
                        oddInitialized = true;
                    }
                    else if (A[right] != oddValue) {
                        break;
                    }
                }
                right++;
            }

            // Update max length
            maxLength = Math.max(maxLength, right - left);

            // Optimization: If we found a valid slice longer than 1, we can
            // skip ahead. The next potential start could be right-1 at the earliest
            if (right > left + 1) {
                left = right - 1;
            }
            else {
                left++;
            }
        }

        return maxLength;
    }
}
