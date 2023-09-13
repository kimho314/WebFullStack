import java.util.ArrayList;
import java.util.List;

public class MakingBurger {
    public static void main(String[] args) {
        System.out.println(new MakingBurger().solution(new int[]{2, 1, 1, 2, 3, 1, 2, 3, 1}));
        System.out.println(new MakingBurger().solution(new int[]{1, 3, 2, 1, 2, 1, 3, 1, 2}));
    }

    public int solution(int[] ingredient) {
        int answer = 0;
        List<Integer> list = new ArrayList<>();
        for (int elem : ingredient) {
            list.add(elem);
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == 1 && i >= 3) {
                if (list.get(i - 1) == 3 && list.get(i - 2) == 2 && list.get(i - 3) == 1) {
                    answer++;
                    list.remove(i);
                    list.remove(i - 1);
                    list.remove(i - 2);
                    list.remove(i - 3);
                    i -= 4;
                }
            }
        }
        return answer;
    }
}
