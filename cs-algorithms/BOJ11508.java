import java.util.Arrays;
import java.util.Collections;

public class BOJ11508 {
    static FastReader SC = new FastReader();
    static int N;
    static int[] C;

    public static void main(String[] args) {
        N = SC.nextInt();
        C = new int[N];
        for (int i = 0; i < N; i++) {
            C[i] = SC.nextInt();
        }


        long sum = 0;
        for (int i = 0; i < N; i++) {
            sum += C[i];
        }

        int[] sortedArr = Arrays.stream(C).boxed()
                .sorted(Collections.reverseOrder())
                .mapToInt(Integer::intValue)
                .toArray();

        // 3 3 2 2
        // 0 1 2 3

        // 6 5 5 5 5 3
        // 0 1 2 3 4 5

        // 10 9 4 4 3 2
        // 0  1 2 3 4 5
        for (int i = 2; i < N; i += 3) {
            sum -= sortedArr[i];
        }
        System.out.println(sum);
    }
}
