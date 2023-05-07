import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/12906">...</a>
 */
public class NoDuplicateNumber {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new NoDuplicateNumber().solution(new int[]{1, 1, 3, 3, 0, 1, 1})));
    }

    public int[] solution(int[] arr) {
        int[] answer;
        List<Integer> list = new ArrayList<>();
        list.add(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            if (list.get(list.size() - 1) != arr[i]) {
                list.add(arr[i]);
            }
        }
        answer = list.stream().mapToInt(Integer::intValue).toArray();
        return answer;
    }
}
