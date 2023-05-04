import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/42578">...</a>
 */
public class ClothesSelection {
    public static void main(String[] args) {
        System.out.println(new ClothesSelection().solution(new String[][]{{"yellow_hat", "headgear"}, {"blue_sunglasses", "eyewear"}, {"green_turban", "headgear"}}));
        System.out.println(new ClothesSelection().solution(new String[][]{{"crow_mask", "face"}, {"blue_sunglasses", "face"}, {"smoky_makeup", "face"}}));
    }

    public int solution(String[][] clothes) {
        int answer = 0;
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        for (String[] cloth : clothes) {
            String name = cloth[0];
            String kind = cloth[1];
            ArrayList<String> names = map.getOrDefault(kind, null);
            if (names == null) {
                map.put(kind, new ArrayList<>(List.of(name)));
            }
            else {
                names.add(name);
            }
        }

        for (int i = 0; i < map.size(); i++) {
            List<String> keys = map.keySet().stream().toList();
            for (int j = 0; j < keys.size() - i; j++) {

            }
        }

        return answer;
    }
}
