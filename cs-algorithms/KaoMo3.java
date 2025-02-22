import java.util.Arrays;

public class KaoMo3 {
    public static void main(String[] args) {
        KaoMo3 kaoMo3 = new KaoMo3();
        System.out.println(kaoMo3.solution(new int[]{3, 2, 3, 2, 3}));
    }

    public int solution(int[] A) {
        // Implement your solution here
        int max = 0;
        for (int i = 0; i < A.length; i++) {
            for (int j = i; j < A.length; j++) {
                int[] arr = Arrays.copyOfRange(A, i, j + 1);
                boolean flag1 = true;
                boolean flag2 = true;
                for (int k = 0; k < arr.length; k++) {
                    if (k % 2 == 0) {
                        if (k >= 2 && arr[k] != arr[k - 2]) {
                            flag1 = false;

                        }
                    }
                    if (k % 2 == 1) {
                        if (k >= 2 && arr[k] != arr[k - 2]) {
                            flag2 = false;

                        }
                    }
                }
                if (flag1 && flag2) {
                    max = Math.max(max, j - i + 1);
                }
            }
        }
        return max;
    }
}
