/**
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/42890">...</a>
 */
public class CandidateKey {
    public static void main(String[] args) {

    }

    public int solution(String[][] relation) {
        int answer = 0;

        // get all permutation of column combination
        // column : 1, 2, 3
        // 1, 2, 3, 12, 13, 23, 123
        // column : 1, 2, 3, 4, 5
        // 1, 2, 3, 4, 5, 12, 13, 14, 15, 23, 24, 25, 34, 35, 45, 123, 124, 125, 234, 235, 345, 1234, 1235, 2345, 12345


        // for loop permutation of column combination
        // check if column combination is candidate key
        // condition for candidate key : uniqueness

        return answer;
    }
}
