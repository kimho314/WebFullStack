import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static java.util.stream.Collectors.*;

/**
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/42578">...</a>
 * <a href="https://school.programmers.co.kr/questions/33347">explanation</a>
 */
public class ClothesSelection {
    public static void main(String[] args) {
        System.out.println(new ClothesSelection().solution(new String[][]{{"yellow_hat", "headgear"}, {"blue_sunglasses", "eyewear"}, {"green_turban", "headgear"}}));
        System.out.println(new ClothesSelection().solution(new String[][]{{"crow_mask", "face"}, {"blue_sunglasses", "face"}, {"smoky_makeup", "face"}}));
    }

    public int solution(String[][] clothes) {
        int answer = 1;
        Map<String, Integer> map = new HashMap<>();
        for (String[] clothe : clothes) {
            if (map.containsKey(clothe[1])) {
                map.put(clothe[1], map.get(clothe[1]) + 1);
                continue;
            }
            map.put(clothe[1], 1);
        }

        for (String s : map.keySet()) {
            // 착용안하는 경우의 수 = +1
            answer *= (map.get(s) + 1);
        }

        return answer - 1;
    }

    public int solution2(String[][] clothes) {
        return Arrays.stream(clothes)
                .collect(groupingBy(p -> p[1], mapping(p -> p[0], counting())))
                .values()
                .stream()
                .reduce(1L, (x, y) -> x * (y + 1)).intValue() - 1;
    }
}
