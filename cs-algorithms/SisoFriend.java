import java.util.HashSet;
import java.util.Set;

public class SisoFriend {
    public static void main(String[] args) {
        int[] distances = {2, 3, 4};
        Set<Double> ratio = new HashSet<>();
        for (int i = 0; i < distances.length; i++) {
            for (int j = 0; j < distances.length; j++) {
                ratio.add((double) distances[i] / (double) distances[j]);
            }
        }
        ratio.forEach(System.out::println);
    }

    public long solution(int[] weights) {
        long answer = 0;
        return answer;
    }
}
