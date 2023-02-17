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
        s = "usf";
        skip = "wbqd";
        result = solution(s, skip, index);
        System.out.println(result);
    }

    public static String solution(String s, String skip, int index) {
        String answer = "";
        String[] split = s.split("");
        StringBuilder stringBuilder = new StringBuilder();
        for (String each1 : split) {
            char limit = (char) (each1.charAt(0) + index);
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = each1.charAt(0) + 1; i <= (int) limit; i++) {
                int num;
                if (i > (int) 'z') {
                    num = (int) 'a' + (i - 1 - (int) 'z');
                } else {
                    num = i;
                }
                list.add(num);
            }
            System.out.println("list1 : " + list);
            for (int i = 0; i < list.size(); i++) {
                char ch = (char) list.get(i).intValue();
                if (skip.contains(Character.valueOf(ch).toString())) {
                    list.remove(list.get(i));
                    int tmp = list.get(list.size() - 1) + 1 > (int) 'z' ?
                        (int) 'a' + (list.get(list.size() - 1) - (int) 'z') :
                        list.get(list.size() - 1) + 1;
                    list.add(tmp);
                }
            }
            System.out.println("list2 : " + list);
            stringBuilder.append((char) list.get(list.size() - 1).intValue());
        }
        answer = stringBuilder.toString();
        return answer;
    }
}
