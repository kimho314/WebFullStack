import java.util.Arrays;

/**
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/42860">...</a>
 */
public class JoyStick {
    public static void main(String[] args) {
        System.out.println(new JoyStick().solution("JEROEN"));
    }

    public int solution(String name) {
        int answer = 0;
        // make array list consisting of A with same size of name
        char[] charArray = new char[name.length()];
        Arrays.fill(charArray, 'A');

        for (int i = 0; i < charArray.length; i++) {
            if (name.charAt(i) == 'A') {
                continue;
            }
        }
        System.out.println(charArray[0] - charArray[1]);
        System.out.println('Z' - 'A');
        return answer;
    }
}
