import java.util.*;

/**
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/42888">...</a>
 */
public class OpenChatting {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new OpenChatting().solution(new String[]{"Enter uid1234 Muzi", "Enter uid4567 Prodo", "Leave uid1234", "Enter uid1234 Prodo", "Change uid4567 Ryan"})));
    }

    public String[] solution(String[] record) {
        List<String> answer = new ArrayList<>();
        // command uid nickname
        Map<String, String> user = new HashMap<>();
        List<String> messages = new ArrayList<>(Arrays.asList("%s님이 들어왔습니다.", "%s님이 나갔습니다."));

        for (String s : record) {
            String[] split = s.split(" ");
            if (split.length == 3) {
                user.put(split[1], split[2]);
            }
        }

        for (String s : record) {
            String[] split = s.split(" ");
            String command = split[0];
            String uid = split[1];
            switch (command) {
                case "Enter":
                    answer.add(String.format(messages.get(0), user.get(uid)));
                    break;
                case "Leave":
                    answer.add(String.format(messages.get(1), user.get(uid)));
                    break;
                default:
                    break;
            }
        }

        return answer.toArray(String[]::new);
    }
}
