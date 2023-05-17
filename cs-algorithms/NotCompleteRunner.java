import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/42576">...</a>
 */
public class NotCompleteRunner {
    public static void main(String[] args) {
        System.out.println(new NotCompleteRunner().solution(new String[]{"leo", "kiki", "eden"}, new String[]{"eden", "kiki"}));
        System.out.println(new NotCompleteRunner().solution(new String[]{"marina", "josipa", "nikola", "vinko", "filipa"}, new String[]{"josipa", "filipa", "marina", "nikola"}));
        System.out.println(new NotCompleteRunner().solution(new String[]{"mislav", "stanko", "mislav", "ana"}, new String[]{"stanko", "ana", "mislav"}));
    }

    public String solution(String[] participant, String[] completion) {
        String answer = "";
        HashMap<String, Integer> map = new HashMap<>();
        for (String person : participant) {
            map.put(person, map.getOrDefault(person, 0) + 1);
        }

        for (String person : completion) {
            if (map.containsKey(person)) {
                map.replace(person, map.get(person) - 1);
            }
        }
        Optional<Map.Entry<String, Integer>> maybePersonMap = map.entrySet().stream()
                .filter(each -> each.getValue() > 0)
                .findFirst();
        if (maybePersonMap.isPresent()) {
            answer = maybePersonMap.get().getKey();
        }
        return answer;
    }
}
