import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MemoryScore {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new MemoryScore().solution(new String[]{"may", "kein", "kain", "radi"}, new int[]{5, 10, 1, 3}, new String[][]{{"may", "kein", "kain", "radi"}, {"may", "kein", "brin", "deny"}, {"kon", "kain", "may", "coni"}})));
    }

    public int[] solution(String[] name, int[] yearning, String[][] photo) {
        int[] answer = new int[photo.length];
        Map<String, Integer> scoreMap = makeScoreMap(name, yearning);
        for (int i = 0; i < photo.length; i++) {
            int sum = 0;
            for (String elem : photo[i]) {
                int score = scoreMap.getOrDefault(elem, 0);
                sum += score;
            }
            answer[i] = sum;
        }
        return answer;
    }

    private Map<String, Integer> makeScoreMap(String[] name, int[] yearning) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < name.length; i++) {
            map.put(name[i], yearning[i]);
        }
        return map;
    }
}
