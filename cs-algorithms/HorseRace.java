import java.util.Arrays;
import java.util.Collections;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/178871
 */
public class HorseRace {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new HorseRace().solution(new String[]{"mumu", "soe", "poe", "kai", "mine"}, new String[]{"kai", "kai", "mine", "mine"})));
    }

    public String[] solution(String[] players, String[] callings) {
        String[] answer;
        for (String calling : callings) {
            int idx = 0;
            for (int j = 0; j < players.length; j++) {
                if (calling.equals(players[j])) {
                    idx = j;
                    break;
                }
            }

            Collections.swap(Arrays.asList(players), idx, idx - 1);
        }

        answer = players;
        return answer;
    }
}
