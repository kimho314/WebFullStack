import java.util.*;
import java.util.stream.Collectors;

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
        List<String> messages = new ArrayList<>(Arrays.asList("%s %s님이 들어왔습니다.", "%s %s님이 나갔습니다."));

        for (String s : record) {
            String[] split = s.split(" ");
            String command = split[0];
            String uid = split[1];
            switch (command) {
                case "Enter":
                    String nickname = split[2];
                    if (!user.containsKey(uid)) {
                        user.put(uid, nickname);
                    }
                    else {
                        if (!user.get(uid).equals(nickname)) {
                            answer = answer.stream()
                                    .map(it -> {
                                        if (it.contains(uid)) {
                                            return it.replace(user.get(uid), nickname);
                                        }
                                        return it;
                                    })
                                    .collect(Collectors.toList());
                        }
                    }
                    answer.add(String.format(messages.get(0), uid, nickname));
                    break;
                case "Leave":
                    answer.add(String.format(messages.get(1), uid, user.get(uid)));
                    break;
                case "Change":
                    String newNickname = split[2];
                    answer = answer.stream()
                            .map(it -> {
                                if (it.contains(uid)) {
                                    return it.replace(user.get(uid), newNickname);
                                }
                                return it;
                            })
                            .collect(Collectors.toList());
                    break;
                default:
                    throw new IllegalArgumentException("illegal command");
            }
        }
        String collect = String.join("|", user.keySet());
        return answer.stream()
                .map(it -> it.replaceAll(collect, "").trim())
                .toArray(String[]::new);
    }
}
