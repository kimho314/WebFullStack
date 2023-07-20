import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/12921">...</a>
 */
public class SearchPrimeNumber2 {
    public static void main(String[] args) {
        System.out.println(new SearchPrimeNumber2().solution(10));
        System.out.println(new SearchPrimeNumber2().solution(5));
        System.out.println(new SearchPrimeNumber2().solution(1000000));
    }


    public int solution(int n) {
        int answer;
        List<Boolean> list = new ArrayList<>(Collections.nCopies(n + 1, true));
        for (int i = 2; i <= (int) Math.sqrt(n); i++) {
            if (list.get(i)) {
                int tmp = 2;
                while (i * tmp <= n) {
                    list.set(i * tmp, false);
                    tmp++;
                }
            }
        }

        answer = (int) list.stream().skip(2).filter(it -> it).count();
        return answer;
    }
}
