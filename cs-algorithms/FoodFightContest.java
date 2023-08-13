import java.util.ArrayList;
import java.util.List;

public class FoodFightContest {
    public static void main(String[] args) {
        System.out.println(new FoodFightContest().solution(new int[]{1, 3, 4, 6}));
        System.out.println(new FoodFightContest().solution(new int[]{1, 7, 1, 2}));
    }

    public String solution(int[] food) {
        StringBuilder answer = new StringBuilder();

        // 0번 인덱스 제외한 반만 먼저 만들자
        // 두명이서 하는 시합이므로 2로 나눠서 1이상만
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i < food.length; i++) {
            int elem = food[i] / 2;
            if (elem > 0) {
                for (int j = 0; j < elem; j++) {
                    list.add(i);
                }
            }
        }

        for (int i = 0; i < list.size(); i++) {
            answer.append(list.get(i));
        }
        answer.append(0);
        for (int i = list.size() - 1; i >= 0; i--) {
            answer.append(list.get(i));
        }

        return answer.toString();
    }

    public String solution2(int[] food) {
        String answer = "0";

        for (int i = food.length - 1; i > 0; i--) {
            for (int j = 0; j < food[i] / 2; j++) {
                answer = i + answer + i;
            }
        }

        return answer;
    }
}
