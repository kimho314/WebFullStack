import java.util.HashSet;
import java.util.Set;

public class BOJ5568 {
    static FastReader SC = new FastReader();
    static int N, K;
    static int[] SELECTED;
    static int[] NUMS = new int[100];
    static Set<String> set = new HashSet<>();

    public static void main(String[] args) {
        N = SC.nextInt();
        K = SC.nextInt();
        for (int i = 0; i < N; i++) {
            int input = SC.nextInt();
            NUMS[input]++;
        }
        SELECTED = new int[K + 1];

        recFunc(1, K);
        System.out.println(set.size());
    }

    private static void recFunc(int k, int len) {
        if (k == len + 1) {
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i <= K; i++) {
                sb.append(SELECTED[i]);
            }
//            System.out.println(sb.toString());
            set.add(sb.toString());
        }
        else {
            for (int i = 1; i <= 99; i++) {
                if (NUMS[i] <= 0) {
                    continue;
                }

                SELECTED[k] = i;
                NUMS[i]--;
                recFunc(k + 1, len);
                SELECTED[k] = 0;
                NUMS[i]++;
            }
        }
    }
}
