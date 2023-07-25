/**
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/178870">...</a>
 */
public class ConsecutivePartialSequence {
    public static void main(String[] args) {

    }

    public int[] solution(int[] sequence, int k) {
        int[] answer = new int[2];
        answer[1] = sequence.length;
        int left = 0;
        int right = 0;
        int sum = sequence[right];
        while (left <= right) {
            while (sum < k) {
                sum += sequence[++right];
            }
            if (sum == k) {
                // right - left(길이) < answer[1] - answer[0] -> left/right을 answer에 저장
                // right - left == answer[1] - answer[0] && left < answer[0] -> left/right을 answer에 저장
                left = right;
            }
            else {
                left++;
            }

        }
        return answer;
    }
}
