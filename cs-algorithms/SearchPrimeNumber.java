import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/42839">...</a>
 */
public class SearchPrimeNumber {
    Set<Integer> set = new HashSet<>();

    public static void main(String[] args) {
        System.out.println(new SearchPrimeNumber().solution("17"));
        System.out.println(new SearchPrimeNumber().solution("011"));
    }

    public int solution(String numbers) {
        int answer = 0;
        permutation("", numbers);
        List<Integer> list = new ArrayList<>(set);
        for (Integer elem : list) {
            if (isPrimeNumber(elem)) {
                answer++;
            }
        }
        return answer;
    }

    // 숫자 조합을 만드는 재귀함수
    public void permutation(String comb, String other) {
        if (!comb.equals("")) {
            set.add(Integer.parseInt(comb));
        }

        for (int i = 0; i < other.length(); i++) {
            permutation(comb + other.charAt(i), other.substring(0, i) + other.substring(i + 1));
        }
    }

    public boolean isPrimeNumber(Integer num) {
        if (num <= 1) {
            return false;
        }
        else {
            boolean result = true;
            for (int i = 2; i <= num; i++) {
                if (num != i && num % i == 0) {
                    result = false;
                    break;
                }
            }
            return result;
        }
    }
}
