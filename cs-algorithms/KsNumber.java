import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/42748">...</a>
 */
public class KsNumber {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new KsNumber().solution(new int[]{1, 5, 2, 6, 3, 7, 4}, new int[][]{{2, 5, 3}, {4, 4, 1}, {1, 7, 3}})));
    }

    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];

        List<Integer> kList = new ArrayList<>();
        for (int[] command : commands) {
            int start = command[0];
            int end = command[1];
            int k = command[2];

            List<Integer> subList = new ArrayList<>();
            for (int idx2 = start - 1; idx2 <= end - 1; idx2++) {
                subList.add(array[idx2]);
            }

            subList.sort(Comparator.comparingInt(o -> o));

            kList.add(subList.get(k - 1));
        }

        for (int i = 0; i < kList.size(); i++) {
            answer[i] = kList.get(i);
        }
        return answer;
    }
}
