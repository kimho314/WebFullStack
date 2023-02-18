import java.util.ArrayList;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/155652
 */
public class SecretCode {

    public static void main(String[] args) {
        int index = 5;
        String s = "aukks";
        String skip = "wbqd";
        String result = solution(s, skip, index);
        System.out.println(result);
        index = 20;
        s = "usf";
        skip = "wbqd";
        result = solution(s, skip, index);
        System.out.println(result);
    }

    public static String solution(String s, String skip, int index) {
        String answer = "";
        String[] split = s.split("");
        char startChar = 'a';
        char endChar = 'z';
        int charRange = endChar - startChar;
        ArrayList<Integer> charList = new ArrayList<>();
        for (int i = 0; i <= charRange; i++) {
            charList.add(startChar + i);
        }

        StringBuilder stringBuilder = new StringBuilder();

        for (String string : split) {
            int count = 0;
            int step = charList.indexOf((int) string.charAt(0));
            while (count < index) {
                int curIdx = ++step % charList.size();
                char curChar = (char) charList.get(curIdx).intValue();
                if (skip.contains(String.valueOf(curChar))) {
                    continue;
                }
                count++;
            }
            Character foundChar = (char) charList.get(step % charList.size()).intValue();
            stringBuilder.append(foundChar);
        }
        answer = stringBuilder.toString();
        return answer;
    }
}
