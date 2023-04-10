import java.util.HashMap;
import java.util.Map;

public class SisoFriend {
    public static void main(String[] args) {
        int[] weights = {100, 180, 360, 100, 270};
        System.out.println(new SisoFriend().solution(weights));
    }

    public long solution(int[] weights) {
        long answer = 0;
        double[] ratios = {1.0, 2.0, 3.0 / 2.0, 4.0 / 3.0, 1.0 / 2.0, 2.0 / 3.0, 3.0 / 4.0};
        // key : weight, value : count
        Map<Double, Integer> map = new HashMap<>();

        for (double w : weights) {
            answer += map.getOrDefault(w * ratios[0], 0)
                    + map.getOrDefault(w * ratios[1], 0)
                    + map.getOrDefault(w * ratios[2], 0)
                    + map.getOrDefault(w * ratios[3], 0)
                    + map.getOrDefault(w * ratios[4], 0)
                    + map.getOrDefault(w * ratios[5], 0)
                    + map.getOrDefault(w * ratios[6], 0);
            map.put(w, map.getOrDefault(w, 0) + 1);
        }
        return answer;
    }
}
