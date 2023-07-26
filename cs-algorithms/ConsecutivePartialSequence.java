import java.util.Arrays;

/**
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/178870">...</a>
 */
public class ConsecutivePartialSequence {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new ConsecutivePartialSequence().solution(new int[]{1, 2, 3, 4, 5}, 7)));
        System.out.println(Arrays.toString(new ConsecutivePartialSequence().solution(new int[]{1, 1, 1, 2, 3, 4, 5}, 5)));
        System.out.println(Arrays.toString(new ConsecutivePartialSequence().solution(new int[]{2, 2, 2, 2, 2}, 6)));
    }

    public int[] solution(int[] sequence, int k) {
        int[] answer = new int[2];
        answer[0] = -1;
        answer[1] = sequence.length;
        int left = 0;
        int right = 0;
        int sum = sequence[right];
        while (left <= right) {
            while (sum < k && right < sequence.length - 1) {
                sum += sequence[++right];
            }
            if (sum == k) {
                // right - left(길이) < answer[1] - answer[0] -> left/right을 answer에 저장
                // right - left == answer[1] - answer[0] && left < answer[0] -> left/right을 answer에 저장
                if (right - left < answer[1] - answer[0]) {
                    if (answer[0] < left) {
                        answer[0] = left;
                        answer[1] = right;
                    }
                }
            }
            sum -= sequence[left];
            left++;
        }
        return answer;
    }
}
