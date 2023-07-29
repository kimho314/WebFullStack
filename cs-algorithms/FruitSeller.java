import java.util.Arrays;

public class FruitSeller {
    public static void main(String[] args) {
        System.out.println(new FruitSeller().solution(3, 4, new int[]{1, 2, 3, 1, 2, 3, 1}));
        System.out.println(new FruitSeller().solution(4, 3, new int[]{4, 1, 2, 2, 4, 4, 4, 4, 1, 2, 4, 2}));
    }

    public int solution(int k, int m, int[] score) {
        int answer = 0;

        // 최대 박스의 갯수 구하고
        int maxNumOfBoxes = score.length / m;
        if (maxNumOfBoxes == 0) {
            return answer;
        }

        // score 배열을 내림차순으로 정렬
        Arrays.sort(score);

        int cur = score.length - 1;
        int[][] arr = new int[maxNumOfBoxes][m];
        for (int i = 0; i < maxNumOfBoxes; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = score[cur--];
            }
        }

        for (int i = 0; i < maxNumOfBoxes; i++) {
            int min = k;
            for (int j = 0; j < m; j++) {
                min = Math.min(min, arr[i][j]);
            }
            answer += (min * m);
        }

        return answer;
    }
}
