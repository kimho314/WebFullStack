import java.util.ArrayList;
import java.util.List;

public class Coin {
    public static void main(String[] args) {
        int remain = 4720;
        List<Integer> coins = new ArrayList<>(List.of(500, 100, 50, 1));
        List<Integer> counts = new ArrayList<>(4);

        for (int i = 0; i < coins.size(); i++) {
            Integer maxCount = Math.toIntExact(remain / coins.get(i));
            counts.add(i, maxCount);
            remain -= coins.get(i) * maxCount;
        }

        counts.forEach(System.out::println);
        int totalCount = 0;
        for (Integer count : counts) {
            totalCount += count;
        }
        System.out.println("totalCount : " + totalCount);
    }

}
