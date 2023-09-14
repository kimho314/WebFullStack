/**
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/132267">...</a>
 */
public class ColaProblem {
    public static void main(String[] args) {
        System.out.println(new ColaProblem().solution(2, 1, 20));
        System.out.println(new ColaProblem().solution(3, 1, 20));
    }

    public int solution(int a, int b, int n) {
        int answer = 0;
        while (n / a > 0) {
            answer += (n / a) * b;
            n = (n / a) * b + n % a;
        }
        return answer;
    }
}
