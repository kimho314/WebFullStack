import java.util.Arrays;
import java.util.PriorityQueue;

public class HallOfFame1 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new HallOfFame1().solution(3, new int[]{10, 100, 20, 150, 1, 100, 200})));
        System.out.println(Arrays.toString(new HallOfFame1().solution(4, new int[]{0, 300, 40, 300, 20, 70, 150, 50, 500, 1000})));
    }

    public int[] solution(int k, int[] score) {
        int[] answer = new int[score.length];
        int[] hof = new int[k];
        Arrays.fill(hof, -1);

        for (int i = 0; i < score.length; i++) {
            boolean suitableForHOF = isSuitableForHOF(hof, score[i]);
            if (suitableForHOF) {
                hof[0] = score[i];
                Arrays.sort(hof);
            }
            if (hof[0] == -1) {
                int min = Arrays.stream(hof).filter(it -> it != -1).min().orElseThrow();
                answer[i] = min;
            }
            else {
                answer[i] = hof[0];
            }
        }
        return answer;
    }

    private boolean isSuitableForHOF(int[] hof, int point) {
        if (hof == null || hof.length == 0) {
            return true;
        }
        int count = (int) Arrays.stream(hof).filter(it -> it == 0).count();
        if (count == hof.length) {
            return true;
        }
        Arrays.sort(hof);
        int lowestScore = hof[0];
        return lowestScore < point;
    }

    public int[] solution2(int k, int[] score) {
        int[] answer = new int[score.length];

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

        for (int i = 0; i < score.length; i++) {

            priorityQueue.add(score[i]);
            if (priorityQueue.size() > k) {
                priorityQueue.poll();
            }

            answer[i] = priorityQueue.peek();
        }


        return answer;
    }
}
