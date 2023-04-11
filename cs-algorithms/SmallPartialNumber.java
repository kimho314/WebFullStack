import java.util.ArrayList;
import java.util.List;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/147355
 */
public class SmallPartialNumber {
    public static void main(String[] args) {
        System.out.println(new SmallPartialNumber().solution("3141592", "271"));
        System.out.println(new SmallPartialNumber().solution("500220839878", "7"));
        System.out.println(new SmallPartialNumber().solution("10203", "15"));
        System.out.println(new SmallPartialNumber().solution("10", "15"));
    }

    public int solution(String t, String p) {
        int answer = 0;
        long pNum = Long.parseLong(p);
        int pLen = p.length();

        List<Long> nums = new ArrayList<>();
        for (int i = 0; i < t.length() - (pLen - 1); i++) {
            String tmpNum = (i + pLen) >= t.length() ? t.substring(i) : t.substring(i, i + pLen);
            nums.add(Long.parseLong(tmpNum));
        }

        answer = (int) nums.stream().filter(it -> it <= pNum).count();
        return answer;
    }
}
