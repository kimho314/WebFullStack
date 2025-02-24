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

        // For each position, calculate the longest switching slice ending at that position
        for (int i = 0; i < A.length; i++) {
            // Try to extend as far as possible to the right
            int evenVal = A[i];
            int oddVal = Integer.MIN_VALUE;
            boolean oddInitialized = false;

            int j = i;
            while (j < A.length) {
                if ((j - i) % 2 == 0) {  // Even position relative to i
                    if (A[j] != evenVal) {
                        break;
                    }
                }
                else {  // Odd position relative to i
                    if (!oddInitialized) {
                        oddVal = A[j];
                        oddInitialized = true;
                    }
                    else if (A[j] != oddVal) {
                        break;
                    }
                }
                j++;
            }

            maxLength = Math.max(maxLength, j - i);

            // Skip to the position where we found a mismatch minus 1
            // This optimization prevents rechecking sequences we know won't be valid
            if (j > i + 1) {
                i = j - 2;
            }
        }

        return maxLength;
    }
}
