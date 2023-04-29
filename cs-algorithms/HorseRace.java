import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/178871
 */
public class HorseRace {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new HorseRace().solution(new String[]{"mumu", "soe", "poe", "kai", "mine"}, new String[]{"kai", "kai", "mine", "mine"})));
    }

    public String[] solution(String[] players, String[] callings) {
        Map<String, Integer> rank = initialRank(players);
        for (String calledName : callings) {
            int calledRank = rank.get(calledName);
            changeRank(calledRank, calledName, players, rank);
        }
        //ver2: entrySet 사용
        String[] answer = new String[players.length];
        for (Map.Entry<String, Integer> entry : rank.entrySet()) {
            answer[entry.getValue()] = entry.getKey();
        }
        return answer;
    }

    // (이름, 등수) map 초기화
    private Map<String, Integer> initialRank(String[] players) {
        Map<String, Integer> rank = new HashMap<>();
        for (int idx = 0; idx < players.length; idx++) {
            rank.put(players[idx], idx);
        }
        return rank;
    }

    // 등수 바꾸기
    private void changeRank(int calledRank, String calledName, String[] players, Map<String, Integer> rank) {
        String front = players[calledRank - 1];
        players[calledRank - 1] = calledName;
        players[calledRank] = front;
        rank.put(calledName, calledRank - 1);
        rank.put(front, calledRank);
    }
}
